package com.paintwar.client.controller.game;

import com.paintwar.client.model.communication.gameio.GameIOReceiver;

public class ClientGameManager implements IClientGameManager {

	private String clientIp;
	private String gameName;
	private String serverName;
	private int serverRMIPort;
	private GameEntity currentLocalGame;
	private GameIOReceiver currentReceiver;
	
	public ClientGameManager() {
		
	}
	
	public void setClientIp(String newIp) {
		clientIp = newIp;
	}
	
	public void joinGame(String gameName, String serverIp, int serverRMIPort) {
		currentLocalGame = new GameEntity();
		currentReceiver = new GameIOReceiver(clientIp, gameName, serverIp, serverRMIPort, currentLocalGame);
		currentLocalGame.setIoClient(currentReceiver);
		currentLocalGame.openGame(); //the client shouldn't start the game, the server should tell him to
	}
	
	public void disconnect() {
		currentLocalGame.closeGame();
	}
	
	public static void main(String[] args) {
		ClientGameManager manag = new ClientGameManager();
		manag.setClientIp("localhost");
		manag.joinGame("game0", "localhost", 2020);
	}
	
}
