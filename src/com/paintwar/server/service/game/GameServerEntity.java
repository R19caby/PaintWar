package com.paintwar.server.service.game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.paintwar.server.logger.Logger;
import com.paintwar.server.service.game.elements.DrawingRemote;
import com.paintwar.server.service.game.elements.DrawingServerProxy;
import com.paintwar.server.service.game.elements.Player;
import com.paintwar.server.service.game.elements.Team;
import com.paintwar.unicast.UnicastTransmitter;

public class GameServerEntity implements IGameServerEntity, Serializable {

	// name of the game
	protected String gameName ;

	// RMI port for this server
	protected int RMIPort ;
	
	// IP of the server
	protected String serverIP ;

	// int to generate the ids of the drawings
	protected int drawingID ;
	
	// int to generate team id
	protected int teamID ;
	
	// list of transmitters to send to every players
	private List<UnicastTransmitter> transmitters = new ArrayList<UnicastTransmitter> () ;
	
	// hashmap to save all data of drawings to send to clients
	private Map<String, DrawingRemote> drawingRemotes = new HashMap<String, DrawingRemote> () ;
	
	//teams
	private Map<Color, Team> teams = new HashMap<Color, Team>();
	
	//players
	private Map<String, Player> players = new HashMap<String, Player>();
	
	//main thread for the game
	private GameLoop gameLoop;

	private DatagramSocket transmissionSocket;

	private Thread playerConnecter;

	// le constructeur du serveur : il le d�clare sur un port rmi de la machine d'ex�cution
	protected GameServerEntity (String gameName, String serverIP, int RMIPort, int transmitterPort) throws RemoteException {
		this.gameName = gameName ;
		this.serverIP = serverIP ;
		this.RMIPort = RMIPort ;
		
		try {
			this.transmissionSocket = new DatagramSocket(transmitterPort);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		
		this.playerConnecter = new Thread(() -> {
			while(true)  {
			    DatagramPacket dp = new DatagramPacket(new byte[1000], 1000);

		    	try {
					transmissionSocket.receive(dp);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    String[] data = new String(dp.getData()).trim().split(";");
			    if (data[0].equals("init co")) {
			    	String[] clientData = data[1].split(":");
			    	transmitters.add(new UnicastTransmitter(transmissionSocket, dp, data[1]));
			    	try {
						this.addPlayer(clientData[0], Integer.parseInt(clientData[1]));
					} catch (NumberFormatException | RemoteException e) {
						Logger.print("[Server/playerConnecter] fucked");
						e.printStackTrace();
					}
			    }
			}
		});
		playerConnecter.setDaemon(true);
		playerConnecter.start();

		this.gameLoop = new GameLoop(this, transmitters, teams, players);
		try {
			// attachcement sur serveur sur un port identifi� de la machine d'exécution
			System.setProperty("java.security.policy","Server.policy");
			UnicastRemoteObject.exportObject(this, GameConfig.RMI_OBJECT_PORT);
			Naming.rebind ("//" + serverIP + ":" + RMIPort + "/" + gameName, this) ;
			Logger.print("[Server/GameEntity] RMI ready on " + "//" + serverIP + ":" + RMIPort + "/" + gameName);
			gameLoop.setDaemon(true);
			gameLoop.start();
		} catch (Exception e) {
			Logger.print("[Server/GameEntity] Error on RMI " + e) ;
		}
	}

	//set drawn a remote for future players connecting
	protected void setDrawn(String drawing, Double drawPercent) {
		DrawingRemote draw = drawingRemotes.get(drawing);
		draw.setCompleted(drawPercent);
	}
	
	@Override
	public int getUnicastPort() throws RemoteException {
		return transmissionSocket.getLocalPort();
	}
	
	// indicates what port to use to emit messages for client (increased by 1 for each client)
	// used for multiple clients on a single machine
	// also creates the player
	@Override
	public int addPlayer (String clientIP, int clientPort) throws RemoteException {
		String clientIPID = clientIP + ":" + clientPort;
		Logger.print(clientIPID);
		Color newColor = Color.getHSBColor((float) Math.random(), (float) Math.random(), (float) Math.random());
		Team newTeam = new Team("Team" + teamID++,newColor);
		Player newPlayer = new Player("Player", clientIPID);
		newTeam.addPlayer(newPlayer);
		teams.put(newColor, newTeam);
		players.put(clientIPID, newPlayer);

		return transmissionSocket.getLocalPort();
	}
	
	@Override
	public Color getTeamColor(String clientID) throws RemoteException {
		Player client = players.get(clientID);
		return client.getColor();
	}
	
	@Override
	public boolean generateTeamZone(String clientID) throws RemoteException {
		//generate 1st drawing zone
		Logger.print("[Server] Generating point team zone");
		Point p1 = new Point((int) (Math.random()*GameConfig.DRAWZONE_AREA_WIDTH), (int) (Math.random()*GameConfig.DRAWZONE_AREA_WIDTH/GameConfig.ASPECT_RATIO));
		Point p2 = p1.getLocation();
		p2.translate(100, 100);
		String drawName = addDrawingProxy(p1, p2, -1, clientID);
		updateBoundsDrawing(drawName, p1, p2, clientID);
		startFillingDraw(drawName);
		return true;
	}
	
	// méthode permettant juste de vérifier que le serveur est lancé
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
	/*public void registerObject (DrawingRemote drawing) {

		Logger.print("[Server] Binding RMI object " + drawing);
		try {
			Naming.rebind ("//" + serverIP + ":" + RMIPort + "/" + drawing.getName (), drawing) ;
			Logger.print("[Server/GameEntity] Adding " + drawing.getName () + " at x " + drawing.getX1 () + " and y " + drawing.getY1 ()) ;
		} catch (Exception e) {
			e.printStackTrace () ;
			try {
				System.out.println ("[Server/GameEntity] Couldn't add drawing " + drawing.getName () + " on server " + serverIP + "/"+ RMIPort) ;
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}*/
	
	@Override
	public String addDrawingProxy(Point p1, Point p2, int formType, String clientID) throws RemoteException {
		// création d'un nouveau nom, unique, destiné à servir de clé d'accès au dessin
		// et création d'un nouveau dessin de ce nom et associé également à un émetteur multicast...
		// attention : la classe Dessin utilisée ici est celle du package serveur (et pas celle du package client)
		Player client = players.get(clientID);
		DrawingRemote dessin = new DrawingRemote ("draw" + nextId (), client.getColor(), clientID) ;

		Logger.print("[Server/GameEntity] Creating new drawing " + dessin.getName());
		// enregistrement du dessin pour accès rmi distant 
		//registerObject (dessin) ;
		// ajout du dessin dans la liste des dessins pour accès plus efficace au dessin
		drawingRemotes.put (dessin.getName (), dessin) ;
		//Envoi du message � tous les �diteurs
		HashMap<String, Object> hm = new HashMap <String, Object> () ;
		hm.put ("x1", Integer.valueOf(p1.x)) ;
		hm.put ("y1", Integer.valueOf(p1.y)) ;
		hm.put ("x2", Integer.valueOf(p2.x)) ;
		hm.put ("y2", Integer.valueOf(p2.y)) ;
		hm.put ("color", client.getColor()) ;
		hm.put ("type", formType) ;
		// envoi des mises à jour à tous les clients, via la liste des émetteurs
		for (UnicastTransmitter sender : transmitters) {
			if (!sender.getClientIPID().equals(clientID) || formType == -1) {
				sender.diffuseMessage(this.getClass().getPackageName(), "Draw", dessin.getName (), hm) ;
			}
		}
		Logger.print("[Server/GameEntity] send message to all for creating " + dessin.getName());
		// renvoi du dessin à l'éditeur local appelant : l'éditeur local récupèrera seulement un RemoteDessin
		// sur lequel il pourra invoquer des méthodes en rmi et qui seront relayées au référent associé sur le serveur  
		return dessin.getName() ;
	}
	
	// m�thode qui incr�mente le compteur de dessins pour avoir un id unique pour chaque dessin :
	// dans une version ult�rieure avec r�cup�ration de dessins � partir d'une sauvegarde, il faudra également avoir sauvegardé ce nombre...
	public int nextId () {
		drawingID++ ; 
		return drawingID ; 
	}
	
	@Override
	public ArrayList<IDrawServerRemote> getDrawingProxies() throws RemoteException {
		ArrayList<IDrawServerRemote> data = new ArrayList<IDrawServerRemote> (drawingRemotes.values());
		return data;
	}

	@Override
	public DrawingRemote getDrawing(String name) throws RemoteException {
		return drawingRemotes.get (name) ;
	}
	
	public void updateBoundsDrawing(String name, Point p1, Point p2, String clientID) {
		DrawingRemote drawing = drawingRemotes.get(name);
		if (drawing != null) {
			try {
				//update bounds
				drawing.setBounds(p1, p2);
				
				//send message to update bounds
				HashMap<String, Object> hm = new HashMap <String, Object> () ;
				hm.put ("x1", Integer.valueOf(p1.x)) ;
				hm.put ("y1", Integer.valueOf(p1.y)) ;
				hm.put ("x2", Integer.valueOf(p2.x)) ;
				hm.put ("y2", Integer.valueOf(p2.y)) ;
				// send to all clients
				for (UnicastTransmitter sender : transmitters) {
					String senderID = sender.getClientIPID() + ":" + sender.getTransmissionPort();
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
	public void startFillingDraw(String name) throws RemoteException {
		
		DrawingRemote drawing = drawingRemotes.get(name);

		if (drawing != null) {
			Logger.print("[Server/GameEntity] Starting filling " + name + " with x=" + drawing.getX1() + " and y=" + drawing.getY1());
			
			//generate area of drawing
			Point p1 = new Point(drawing.getX1(), drawing.getY1());
			Point p2 = new Point(drawing.getX2(), drawing.getY2());
			Rectangle r = new Rectangle(p1);
			r.add(p2);
			
			if (!drawing.getCreatorID().equals("server")) {
				//calculate ink to remove
				Player playerPainting = players.get(drawing.getCreatorID());
				playerPainting.addInk((int) (-r.height*r.width*GameConfig.INK_AREA_COST));
				
				//update ink for player
				for (UnicastTransmitter sender : transmitters) {
					if (sender.getClientIPID().equals(playerPainting.getIpId())) {
						HashMap<String, Object> hm = new HashMap <String, Object> () ;
						hm.put("ink", playerPainting.getInk());
						hm.put("maxInk", playerPainting.getMaxInk());
						sender.diffuseMessage (this.getClass().getPackageName(), "UpInk", playerPainting.getIpId(), hm);
					}
				}
			}
			
			gameLoop.addDrawing(
					drawing.getName(), 
					new DrawingServerProxy(
							drawing.getName(), 
							drawing.getColor(), 
							p1, p2, 
							drawing.getCreatorID()));
		}
	}
	
	@Override
	public void deleteDrawing(String name) throws RemoteException {
		Logger.print("[Server/GameEntity] deleting " + name);
		gameLoop.deleteDrawing(name);
		drawingRemotes.remove(name);
		// envoi des mises à jour à tous les clients, via la liste des émetteurs
		for (UnicastTransmitter sender : transmitters) {
			HashMap<String, Object> hm = new HashMap <String, Object> () ;
			sender.diffuseMessage (this.getClass().getPackageName(), "Delete", name, hm);
		}
	}

	public void stopServer() throws RemoteException {
		//stop game loop
		gameLoop.interrupt();
		playerConnecter.interrupt();
		
		try {
			Naming.unbind("//" + serverIP + ":" + RMIPort + "/" + gameName);
		} catch (Exception e) {
			Logger.print("[Server/GameEntity] Couldn't remove RMI binding " + "//" + serverIP + ":" + RMIPort + "/" + gameName);
			e.printStackTrace();
		}
	}

	@Override
	public Map<Color, Team> getTeamData() throws RemoteException {
		return teams;
	}
	
	@Override
	public Player getPlayerData(String name) throws RemoteException {
		return players.get(name);
	}


}
