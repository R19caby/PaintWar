package com.paintwar.server.service.game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

import com.paintwar.unicast.UnicastTransmitter;

public class DrawServerProxy extends UnicastRemoteObject implements IDrawServerProxy, Serializable {

	// les attributs du proxy
	private int x ;
	private int y ;
	private int w ;
	private int h ;
	private String name ;
	private Color currentColor;

	// un attribut permettant au Dessin de diffuser directement ses mises à jour, sans passer par le serveur associé
	// - cet attribut n'est pas Serializable, du coup on le déclare transient pour qu'il ne soit pas inclu dans la sérialisation
	protected transient List<UnicastTransmitter> emetteurs ;

	private static final long serialVersionUID = 1L ;

	// constructeur du Dessin sur le serveur : il diffuse alors qu'il faut crÃ©er un nouveau dessin sur tous les clients 
	public DrawServerProxy (String name, Color color, List<UnicastTransmitter> senders) throws RemoteException {
		this.emetteurs = senders ;
		this.name = name ;
		this.currentColor = color;
	}

	public String getName () throws RemoteException {
		return name ;
	}

	// mÃ©thode qui met Ã  jour les limites du Dessin, qui diffuse ensuite ce changement Ã  tous les Ã©diteurs clients
	public void setBounds (int x, int y, int w, int h) throws RemoteException {
		//System.out.println (getName() + " setBounds : " + x + " " + y + " " + w + " " + h) ;
		this.x = x ;
		this.y = y ;
		this.w = w ;
		this.h = h ;
	}
	
	public void setBounds (Point p1, Point p2) throws RemoteException {
		//System.out.println (getName() + " setBounds : " + x + " " + y + " " + w + " " + h) ;
		Rectangle r = new Rectangle(p1);
		r.add(p2);
		this.x = (int) r.getX();
		this.y = (int) r.getY();
		this.h = (int) r.getHeight();
		this.w = (int) r.getWidth();
	}
	
	public void setColor (Color color) throws RemoteException {
		this.currentColor = color;
		HashMap<String, Object> hm = new HashMap <String, Object> () ;
		hm.put ("color", color) ;
		for (UnicastTransmitter emetteur : emetteurs) {
			emetteur.diffuseMessage (this.getClass().getPackageName(), "Color", getName (), hm) ;
		}
	}

	@Override
	public int getX() throws RemoteException {
		return x ;
	}

	@Override
	public int getY() throws RemoteException {
		return y ;
	}

	@Override
	public int getWidth() throws RemoteException {
		return w ;
	}

	@Override
	public int getHeight() throws RemoteException {
		return h ;
	}

	@Override
	public Color getColor() throws RemoteException {
		return currentColor;
	}

}
