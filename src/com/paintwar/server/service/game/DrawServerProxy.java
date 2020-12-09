package com.paintwar.server.service.game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

import com.paintwar.server.logger.Logger;
import com.paintwar.unicast.UnicastTransmitter;

public class DrawServerProxy extends UnicastRemoteObject implements IDrawServerProxy, Serializable {

	// les attributs du proxy
	private int x ;
	private int y ;
	private int w ;
	private int h ;
	private String name ;
	private Color currentColor;
	
	//thread to start fillers
	private transient GameLoop filler;

	// un attribut permettant au Dessin de diffuser directement ses mises � jour, sans passer par le serveur associ�
	// - cet attribut n'est pas Serializable, du coup on le d�clare transient pour qu'il ne soit pas inclu dans la s�rialisation
	protected transient List<UnicastTransmitter> emetteurs ;

	private static final long serialVersionUID = 1L ;

	// constructeur du Dessin sur le serveur : il diffuse alors qu'il faut créer un nouveau dessin sur tous les clients 
	public DrawServerProxy (String name, Color color, List<UnicastTransmitter> senders) throws RemoteException {
		this.emetteurs = senders ;
		this.name = name ;
		this.currentColor = color;
	}

	public String getName () throws RemoteException {
		return name ;
	}

	// méthode qui met à jour les limites du Dessin, qui diffuse ensuite ce changement à tous les éditeurs clients
	public void setBounds (int x, int y, int w, int h) throws RemoteException {
		//System.out.println (getName() + " setBounds : " + x + " " + y + " " + w + " " + h) ;
		this.x = x ;
		this.y = y ;
		this.w = w ;
		this.h = h ;
	}
	
	public void setBounds (Point p1, Point p2) throws RemoteException {
		//Logger.print("[Server/drawProxy] Setbounds " + getName() + " setBounds : " + x + " " + y + " " + w + " " + h) ;
		this.x = (int) p1.getX();
		this.y = (int) p1.getY();
		this.w = (int) (p2.getX() - p1.getX());
		this.h = (int) (p2.getY() - p1.getY());
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
	
	@Override
	public void startFilling(DrawZoneProxy dz) throws RemoteException {
		filler = new GameLoop(name, 0.01, emetteurs, dz);
		filler.start();
	}
	
	@Override
	public void stopFilling() throws RemoteException {
		if (filler != null) {
			filler.interrupt();
			filler = null;
		}
	}

}