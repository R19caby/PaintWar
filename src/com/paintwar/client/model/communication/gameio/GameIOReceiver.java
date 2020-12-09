package com.paintwar.client.model.communication.gameio;

import java.awt.Color;
import java.awt.Point;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.paintwar.client.controller.game.GameEntity;
import com.paintwar.server.logger.Logger;
import com.paintwar.server.service.game.DrawServerProxy;
import com.paintwar.server.service.game.GameServerEntity;
import com.paintwar.server.service.game.IDrawServerProxy;
import com.paintwar.server.service.game.IGameServerEntity;
import com.paintwar.unicast.UnicastReceiver;

public class GameIOReceiver {
	
	// thread to receive all messages to update the state of the game
	private Thread receiverThread ;
	
	//receiver to get all messages
	private UnicastReceiver unicastReceiver ;
	
	// game server to communicate with
	private IGameServerEntity server ;
	
	// name of the client, pseudo?
	protected String clientIP ;
	
	// RMI port of server
	protected int RMIPort ;
	
	// name of the server, should be ip adress
	protected String serverIp ;
	
	// the local game entity
	private GameEntity gameEntity ;
	
	private int transmissionPort;

	// Constructor for the receiver
	// - name of the client
	// - name of the game
	// - name of the server
	// - RMIPort of the game
	public GameIOReceiver (String clientIP, String gameName, String serverIp, int serverRMIPort, GameEntity gameEntity) {
		this.clientIP = clientIP;
		this.gameEntity = gameEntity;
		
		try {
			// connecting to server
			server = (IGameServerEntity)Naming.lookup ("//" + serverIp + ":" + serverRMIPort + "/" + gameName);
			// getting all drawings on server
			ArrayList<IDrawServerProxy> proxiesDrawings = server.getDrawingProxies() ;
			Logger.print("[Client/Communication/GameIO] Received drawing proxies : " + proxiesDrawings);
			// ajout de tous les dessins dans la zone de dessin
			for (IDrawServerProxy rd : proxiesDrawings) {
				addDrawing (rd.getName(), rd.getX (), rd.getY (), rd.getWidth(), rd. getHeight ()) ;
			}
		} catch (Exception e) {
			Logger.print ("[Client/Communication/GameIO] couldn't connect to " + "//" + serverIp + ":" + serverRMIPort + "/" + gameName) ;
			JOptionPane.showInternalMessageDialog(null, "Couldn't connect to server");
			e.printStackTrace () ;
		}
		try {
			// creating unicast server by asking server port
			// and sending client IP to server so that it can send messages
			transmissionPort = server.getPortEmission (clientIP, InetAddress.getByName (clientIP));
			unicastReceiver = new UnicastReceiver (InetAddress.getByName (clientIP), transmissionPort) ;
			// on aimerait bien demander automatiquement quel est l'adresse IP de la machine du client,
			// mais le problème est que celle-ci peut avoir plusieurs adresses IP (filaire, wifi, ...)
			// et qu'on ne sait pas laquelle sera retournée par InetAddress.getLocalHost ()...
			
			//add all commands to unicast
			unicastReceiver.addClientCommandReceiver(new GameIOCommandReceiver(this));
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// Creating thread to receive all messages
		receiverThread = new Thread (unicastReceiver) ;
		// Start the thread
		receiverThread.start () ;
	}
	
	// Drawing creator, sending the new drawing to server
	public synchronized String createDrawing (Point p1, Point p2, Color color, int formType) {
		IDrawServerProxy proxy = null ;
		String proxyName = null ;
		try {
			// Creating new drawing on server
			proxy = server.addDrawingProxy(formType, color);
			// getting name from proxy
			proxyName = proxy.getName();
		} catch (RemoteException e) {
			e.printStackTrace();
		} 
	
		Logger.print("[Client/Communication/GameIO] creating new drawing " + proxyName) ;
		// adding new drawing in the list if not already in� :
		// -> Could have received a new drawing while we are adding that one
		if (! gameEntity.hasDrawing(proxyName)) {
			gameEntity.addDrawing(proxyName, p1, p2, color);
		} else {
			Logger.print ("[Client/Communication/GameIO] drawing " + proxyName + " already there") ;
		}
		return proxyName;
	}

	// Adding a drawing to client
	public void addDrawing (String proxyName, int x, int y, int w, int h) {
		//generating coords
		Point p1 = new Point(x, y);
		Point p2 = p1.getLocation();
		p2.translate(w, h);
		
		//adding drawing to gameEntity
		gameEntity.addDrawing(proxyName, p1, p2, Color.black);
		
	}
	
	// used to update the bounds of a drawing
	// -> called when receiving a message from server
	public synchronized void objectUpdateBounds (String objectName, int x, int y, int w, int h) {
		//generating coords
		Point p1 = new Point(x, y);
		Point p2 = p1.getLocation();
		p2.translate(w, h);
		
		//updating drawing
		gameEntity.updateCoordPaint(objectName, p1, p2);
	}
	
	public void updateBoundsrequest(String name, Point p1, Point p2) {
		try {
			server.updateBoundsDrawing(name, p1, p2, clientIP+":"+transmissionPort);
		} catch (RemoteException e) {
			Logger.print("[Client/GameIO] Couldn't update proxy " + name + " bounds on server");
			e.printStackTrace();
		}
	}
	
	//asking server to delete drawing 
	public void deleteDrawingRequest(String objectName) {
		try {
			server.deleteDrawing(objectName);
		} catch (RemoteException e) {
			Logger.print("[Client/GameIO] Couldn't remove proxy of " + objectName + " on server");
			e.printStackTrace();
		}
	}
	
	//deleting drawing on client -> used after receiving message
	public synchronized void deleteDessin(String objectName) {
		gameEntity.removeDrawing(objectName);
	}
	

	
	//stop receiver
	public void stop() {
		//stop thread
		receiverThread.interrupt();
		//TODO is that all?
	}

	//ask server to start filling the draw
	public void startFilling(String name) {
		try {
			server.startFillingDraw(name);
		} catch (RemoteException e) {
			Logger.print("[Client/GameIO] Couldn't start filling of " + name + " on server");
			e.printStackTrace();
		}
	}
	
	//update filling -> after receiving message
	public synchronized void updateFilling(String name, Double percent) {
		gameEntity.updateFilling(name, percent);
	}

}
