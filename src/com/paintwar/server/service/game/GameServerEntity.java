package com.paintwar.server.service.game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.paintwar.server.logger.Logger;
import com.paintwar.unicast.UnicastTransmitter;

public class GameServerEntity extends UnicastRemoteObject implements IGameServerEntity, Serializable {
	
	// le nom du serveur
	protected String gameName ;

	// le port sur lequel est déclaré le serveur
	protected int RMIPort ;
	
	// la machine sur laquelle se trouve le serveur
	protected String serverIP ;

	// un entier pour générer des noms de dessins différents
	protected int drawingID ;

	// un entier pour générer des noms de dessins différents
	protected int transmitterPort ;
	
	// un diffuseur à une liste d'abonnés
	private List<UnicastTransmitter> transmitters ;
	
	// une strutcure pour stocker tous les dessins et y accéder facilement 
	private HashMap<String, DrawServerProxy> drawingProxies = new HashMap<String, DrawServerProxy> () ;

	// le constructeur du serveur : il le d�clare sur un port rmi de la machine d'ex�cution
	protected GameServerEntity (String gameName, String serverIP, int RMIPort, int transmitterPort) throws RemoteException {
		this.gameName = gameName ;
		this.serverIP = serverIP ;
		this.RMIPort = RMIPort ;
		this.transmitterPort = transmitterPort ;
		transmitters = new ArrayList<UnicastTransmitter> () ;
		try {
			// attachcement sur serveur sur un port identifi� de la machine d'exécution
			Naming.rebind ("//" + serverIP + ":" + RMIPort + "/" + gameName, this) ;
			Logger.print("[Server/GameEntity] RMI ready on " + "//" + serverIP + ":" + RMIPort + "/" + gameName);
		} catch (Exception e) {
			Logger.print("[Server/GameEntity] Error on RMI " + e) ;
		}
	}

	// méthode indiquant quel est le port d'émission/réception à utiliser pour le client qui rejoint le serveur
	// on utilise une valeur arbitraitre de port qu'on incrémente de 1 à chaque arrivée d'un nouveau client
	// cela permettra d'avoir plusieurs clients sur la même machine, chacun avec un canal de communication distinct
	// sur un port différent des autres clients
	@Override
	public int getPortEmission (String clientIP, InetAddress clientAdress) throws RemoteException {
		UnicastTransmitter emetteur = new UnicastTransmitter (clientAdress, transmitterPort++, clientIP) ;
		transmitters.add (emetteur) ;
		return (emetteur.getTransmissionPort()) ;
	}
	
	// m�thode permettant juste de v�rifier que le serveur est lanc�
	@Override
	public void answer (String question) throws RemoteException {
		Logger.print("[Server/GameEntity] the question was : " + question) ;   
	}

	@Override
	public int getRMIPort() throws RemoteException {
		return RMIPort;
	}
	
	// m�thode permettant d'enregistrer un dessin sur un port rmi sur la machine du serveur :
	// - comme cela on pourra également invoquer directement des méthodes en rmi également sur chaque dessin
	public void registerObject (DrawServerProxy drawing) {
		try {
			Naming.rebind ("//" + serverIP + ":" + RMIPort + "/" + drawing.getName (), drawing) ;
			Logger.print("[Server/GameEntity] Adding " + drawing.getName () + " with x " + drawing.getX () + " and y " + drawing.getY ()) ;
		} catch (Exception e) {
			e.printStackTrace () ;
			try {
				System.out.println ("[Server/GameEntity] Couldn't add drawing " + drawing.getName () + " on server " + serverIP + "/"+ RMIPort) ;
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@Override
	public DrawServerProxy addDrawingProxy(int formType, Color color) throws RemoteException {
		// création d'un nouveau nom, unique, destiné à servir de clé d'accès au dessin
		// et création d'un nouveau dessin de ce nom et associé également à un émetteur multicast...
		// attention : la classe Dessin utilisée ici est celle du package serveur (et pas celle du package client)
		DrawServerProxy dessin = new DrawServerProxy ("draw" + nextId (), color, transmitters) ;
		// enregistrement du dessin pour accès rmi distant
		registerObject (dessin) ;
		// ajout du dessin dans la liste des dessins pour accès plus efficace au dessin
		drawingProxies.put (dessin.getName (), dessin) ;
		
		//Envoi du message � tous les �diteurs
		HashMap<String, Object> hm = new HashMap <String, Object> () ;
		hm.put ("x", Integer.valueOf(0)) ;
		hm.put ("y", Integer.valueOf(0)) ;
		hm.put ("w", Integer.valueOf(0)) ;
		hm.put ("h", Integer.valueOf(0)) ;
		hm.put ("color", color) ;
		hm.put ("type", formType) ;
		// envoi des mises à jour à tous les clients, via la liste des émetteurs
		for (UnicastTransmitter sender : transmitters) {
			sender.diffuseMessage(this.getClass().getPackageName(), "Draw", dessin.getName (), hm) ;
		}
		
		// renvoi du dessin à l'éditeur local appelant : l'éditeur local récupèrera seulement un RemoteDessin
		// sur lequel il pourra invoquer des méthodes en rmi et qui seront relayées au référent associé sur le serveur  
		return dessin ;
	}
	
	// m�thode qui incr�mente le compteur de dessins pour avoir un id unique pour chaque dessin :
	// dans une version ult�rieure avec r�cup�ration de dessins � partir d'une sauvegarde, il faudra également avoir sauvegardé ce nombre...
	public int nextId () {
		drawingID++ ; 
		return drawingID ; 
	}
	
	@Override
	public ArrayList<IDrawServerProxy> getDrawingProxies() throws RemoteException {
		ArrayList<IDrawServerProxy> data = new ArrayList<IDrawServerProxy> (drawingProxies.values());
		return data;
	}

	@Override
	public DrawServerProxy getDrawing(String name) throws RemoteException {
		return drawingProxies.get (name) ;
	}
	
	public void updateBoundsDrawing(String name, Point p1, Point p2, String clientID) {
		DrawServerProxy drawing = drawingProxies.get(name);
		if (drawing != null) {
			try {
				//update bounds
				drawing.setBounds(p1, p2);
				
				//get int values
				Rectangle r = new Rectangle(p1);
				r.add(p2);
				int x = (int) r.getX();
				int y = (int) r.getY();
				int h = (int) r.getHeight();
				int w = (int) r.getWidth();
				
				//send message to update bounds
				HashMap<String, Object> hm = new HashMap <String, Object> () ;
				hm.put ("x", Integer.valueOf(x)) ;
				hm.put ("y", Integer.valueOf(y)) ;
				hm.put ("w", Integer.valueOf(w)) ;
				hm.put ("h", Integer.valueOf(h)) ;
				// send to all clients
				for (UnicastTransmitter sender : transmitters) {
					String senderID = sender.getClientIP() + ":" + sender.getTransmissionPort();
					if (!senderID.equals(clientID)) {
						sender.diffuseMessage (this.getClass().getPackageName(), "Bounds", name, hm);
					};
				}
			} catch (RemoteException e) {
				Logger.print("[Server/GameEntity] Couldn't update bounds of entity");
				e.printStackTrace();
			}
		} else {
			Logger.print("[Server/GameEntity] Entity not found");
		}
	}
	
	@Override
	public void deleteDrawing(String name) throws RemoteException {
		drawingProxies.remove(name);
		// envoi des mises à jour à tous les clients, via la liste des émetteurs
		for (UnicastTransmitter sender : transmitters) {
			HashMap<String, Object> hm = new HashMap <String, Object> () ;
			sender.diffuseMessage (this.getClass().getPackageName(), "Delete", name, hm);
		}
	}

	public void stopServer() throws RemoteException {
		try {
			Naming.unbind("//" + serverIP + ":" + RMIPort + "/" + gameName);
		} catch (Exception e) {
			Logger.print("[Server/GameEntity] Couldn't remove RMI binding " + "//" + serverIP + ":" + RMIPort + "/" + gameName);
			e.printStackTrace();
		}
	}

}
