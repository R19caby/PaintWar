package com.paintwar.server.service.game.elements;

import java.awt.Color;
import java.io.Serializable;

import com.paintwar.server.service.game.GameConfig;

public class Player implements Serializable {
	
	private String name;
	private String ipId;
	private Color color;
	private double ink= (int) GameConfig.MAX_INK_PLAYER;
	private int maxInk=GameConfig.MAX_INK_PLAYER;
	
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
	public double getInk() {
		return ink;
	}
	public void setInk(double ink) {
		this.ink = ink;
	}
	public int getMaxInk() {
		return maxInk;
	}
	public void setMaxInk(int maxInk) {
		this.maxInk = maxInk;
	}
	public void addInk(double i) {
		this.ink = Math.min(ink + i, maxInk);
	}
	
}
