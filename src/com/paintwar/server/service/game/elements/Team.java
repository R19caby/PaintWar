package com.paintwar.server.service.game.elements;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class Team {

	private Color color;
	private Map<String, Player> playerList;
	private int score;
	
	public Team(Color color) {
		super();
		this.color = color;
		this.playerList = new HashMap<String, Player>();
		this.score = 0;
	}
	
	public Color getColor() {
		return color;
	}

	public Map<String, Player> getPlayerList() {
		return playerList;
	}
	
	public void addPlayer(Player p) {
		p.setColor(color);
		playerList.put(p.getIpId(), p);
	}
	
	public Player getPlayerById(String id) {
		return playerList.get(id);
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore(int score) {
		this.score = this.score + score;
	}
	
	public int getScore() {
		return score;
	}
	
}
