package com.paintwar.server.service.game;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;

import com.paintwar.server.logger.Logger;

public class GamesManager implements IGamesManager {

	private static int MAX_PLAYERS = 16;
	private String serverIP;
	private int RMIPort;
	private int transmitterPorts;
	private int gameCount;
	
	private Map<String, GameServerEntity> games;
	
	public GamesManager(String serverIP, String onlineHostName, int RMIPort, int transmitterPorts) {
		games = new HashMap<String, GameServerEntity>();
		this.serverIP = serverIP;
		this.RMIPort = RMIPort;
		this.transmitterPorts = transmitterPorts;
		this.gameCount = 0;
		System.setProperty ("java.rmi.server.hostname", onlineHostName) ;
	}
	
	@Override
	public String createGame() {
		String gameName = "Undefined";
		try {
			LocateRegistry.createRegistry(RMIPort);
			gameName = "game" + gameCount;
			GameServerEntity game = new GameServerEntity(gameName, serverIP, RMIPort, transmitterPorts);
			transmitterPorts += MAX_PLAYERS;
			RMIPort++;
			gameCount++;
			games.put(gameName, game);
		} catch (RemoteException e) {
			Logger.print("[Server/GameManager] Couldn't create game");
			e.printStackTrace();
		}
		return gameName;
	}

	@Override
	public void stopGame(String gameName) {
		GameServerEntity game = games.get(gameName);
		if (game != null) {
			try {
				game.stopServer();
			} catch (RemoteException e) {
				Logger.print("[Server/Service/GamesManager] Couldn't stop game RMI server");
				e.printStackTrace();
			}
			games.remove(gameName);
		}
	}

	@Override
	public GameServerEntity getGameInfo(String gameName) {
		return games.get(gameName);
	}
	
	public static void main(String[] args) {
		GamesManager gm = new GamesManager(GameConfig.SERVER_LOCAL_IP, GameConfig.SERVER_HOSTNAME_IP, GameConfig.RMI_PORT, GameConfig.UNICAST_PORT);
		
		String newGame = gm.createGame();
		GameServerEntity currentGame = gm.getGameInfo(newGame);
		try {
			Logger.print("Creating " + newGame + " with RMIPort " + currentGame.getRMIPort());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
