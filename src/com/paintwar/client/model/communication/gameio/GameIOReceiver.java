package com.paintwar.client.model.communication.gameio;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;

import com.paintwar.client.controller.game.GameEntity;
import com.paintwar.server.logger.Logger;
import com.paintwar.server.service.game.GameServerEntity;
import com.paintwar.server.service.game.IDrawServerRemote;
import com.paintwar.server.service.game.IGameServerEntity;
import com.paintwar.server.service.game.elements.DrawingRemote;
import com.paintwar.server.service.game.elements.Player;
import com.paintwar.server.service.game.elements.Team;
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

	//port used to send/receive messages
	private int unicastReceiverPort;
	
	private int pendingDraw;

	// Constructor for the receiver
	// - name of the client
	// - name of the game
	// - name of the server
	// - RMIPort of the game
	public GameIOReceiver (String clientIP, String clientLocalIP, String gameName, String serverIp, int serverRMIPort, GameEntity gameEntity) {
		this.clientIP = clientIP;
		this.gameEntity = gameEntity;
		
		try {
			// connecting to server
			server = (IGameServerEntity)Naming.lookup ("//" + serverIp + ":" + serverRMIPort + "/" + gameName);
			// getting all drawings on server

			Logger.print("[Client/GameIO] Getting all drawings");
			ArrayList<IDrawServerRemote> proxiesDrawings = server.getDrawingProxies() ;
			Logger.print("[Client/Communication/GameIO] Received drawing proxies : " + proxiesDrawings);
			// ajout de tous les dessins dans la zone de dessin
			for (IDrawServerRemote rd : proxiesDrawings) {
				addDrawing (rd.getName(), rd.getX1 (), rd.getY1 (), rd.getX2(), rd. getY2(), rd.getColor(), rd.isCompleted()) ;
			}
		} catch (Exception e) {
			Logger.print ("[Client/Communication/GameIO] couldn't connect to " + "//" + serverIp + ":" + serverRMIPort + "/" + gameName) ;
			JOptionPane.showInternalMessageDialog(null, "Couldn't connect to server");
			e.printStackTrace () ;
		}
		try {
			
			
			// creating unicast server by asking server port
			// and sending client IP to server so that it can send messages
			int serverUnicastPort = server.getUnicastPort();
			
			//create unicast receiver
			unicastReceiver = new UnicastReceiver (clientIP, serverIp, serverUnicastPort) ;
			unicastReceiverPort = unicastReceiver.getSocket().getLocalPort();
			
			//add all commands to unicast
			unicastReceiver.addClientCommandReceiver(new GameIOCommandReceiver(this));
			
			unicastReceiver.receive();
			
			Logger.print("[Client/GameIO] Getting client team color " + clientIP + ":" + unicastReceiverPort);
			Color clientTeamColor = server.getTeamColor(clientIP + ":" + unicastReceiverPort);
			Logger.print("[Client/GameIO] Got client color");
			gameEntity.setClientTeamColor(clientTeamColor);
			
			// on aimerait bien demander automatiquement quel est l'adresse IP de la machine du client,
			// mais le problème est que celle-ci peut avoir plusieurs adresses IP (filaire, wifi, ...)
			// et qu'on ne sait pas laquelle sera retournée par InetAddress.getLocalHost ()...


			Logger.print("[Client/GameIO] Generating team zone");
			boolean bool = server.generateTeamZone(clientIP + ":" + unicastReceiverPort);
			Logger.print("[Client/GameIO] Done generating team zone " + bool);
			

			Logger.print("[Client/GameIO] Getting player data");
			Player clientData = server.getPlayerData(clientIP + ":" + unicastReceiverPort);
			Logger.print("[Client/GameIO] Player data received");
			this.updateInk(clientData.getInk(), clientData.getMaxInk());
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		// Creating thread to receive all messages
		receiverThread = new Thread (unicastReceiver) ;
		// Start the thread
		receiverThread.start () ;
	}
	
	private void createDrawingServerRequest(String drawName, Point p1, Point p2, int formType) {
		Thread t = new Thread(() -> {
			try {
				// Creating new drawing on server
				Logger.print("[Client/GameIO] Creating drawing");
				String realName = server.addDrawingProxy(p1, p2, formType, clientIP+":"+unicastReceiverPort);
				
				Logger.print("[Client/GameIO] Got drawing name " + realName);
				
				gameEntity.changeName(drawName, realName);
			} catch (RemoteException e) {
				e.printStackTrace();
			} 
		});
		t.setDaemon(true);
		t.start();
	}
	
	// Drawing creator, sending the new drawing to server
	public synchronized String createDrawing (Point p1, Point p2, Color color, int formType) {
		
		String proxyName = "pending" + pendingDraw++;
		
		createDrawingServerRequest(proxyName, p1, p2, formType);
		
		Logger.print("[Client/Communication/GameIO] creating new drawing " + proxyName) ;
		// adding new drawing in the list if not already in� :
		// -> Could have received a new drawing while we are adding that one
		if (! gameEntity.hasDrawing(proxyName)) {
			gameEntity.addDrawing(proxyName, p1, p2, color, null);
		} else {
			Logger.print ("[Client/Communication/GameIO] drawing " + proxyName + " already there") ;
		}
		return proxyName;
	}

	// Adding a drawing to client
	public void addDrawing (String proxyName, int x1, int y1, int x2, int y2, Color c, Double percent) {
		Logger.print("[Client] adding drawing " + proxyName + " at " + x1 + "; " + y1);
		//generating coords
		Point p1 = new Point(x1, y1);
		Point p2 = new Point(x2, y2);
		
		//adding drawing to gameEntity
		gameEntity.addDrawing(proxyName, p1, p2, c, percent);
		
	}
	
	// used to update the bounds of a drawing
	// -> called when receiving a message from server
	public synchronized void objectUpdateBounds (String objectName, int x1, int y1, int x2, int y2) {
		//generating coords
		Point p1 = new Point(x1, y1);
		Point p2 = new Point(x2, y2);
		
		//updating drawing
		gameEntity.updateCoordPaint(objectName, p1, p2);
	}
	
	public void updateBoundsrequest(String name, Point p1, Point p2) {
		try {
			server.updateBoundsDrawing(name, p1, p2, clientIP+":"+unicastReceiverPort);
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
	public synchronized void deleteDrawing(String objectName) {
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

	//set to drawn for drawing
	public synchronized void setDrawn(String name) {
		gameEntity.setDrawn(name);
	}
	
	public Map<Color, Team> getTeamData() {
		try {
			return server.getTeamData();
		} catch (RemoteException e) {
			Logger.print("[Client/GameIOReceiever] Couldn't retreive team scores");
			e.printStackTrace();	
			return null;
		}
	}

	public synchronized void updateInk(double d, int maxInk) {
		gameEntity.updateInk(d, maxInk);
	}

}
