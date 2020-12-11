package com.paintwar.server.service.game.elements;

import java.awt.Color;

public class Player {
	
	private String name;
	private String ipId;
	private Color color;
	
	public Player(String name, String ipId) {
		super();
		this.name = name;
		this.ipId = ipId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIpId() {
		return ipId;
	}
	public void setIpId(String ipId) {
		this.ipId = ipId;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
}
