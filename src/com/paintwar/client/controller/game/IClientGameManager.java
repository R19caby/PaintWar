package com.paintwar.client.controller.game;

public interface IClientGameManager {
	public void joinGame(String gameName, String serverIp, int serverRMIPort);
	public void disconnect();
}
