package com.paintwar.server.service.game;

public interface IGamesManager {
	
	public String createGame();
	public void stopGame(String gameName);
	public GameServerEntity getGameInfo(String gameName);
	
}
