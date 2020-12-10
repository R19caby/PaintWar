package com.paintwar.client.controller.game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import com.paintwar.server.service.game.DrawingRemote;
import com.paintwar.server.service.game.IDrawServerRemote;

public class DrawingProxy {
	
	public static int RECTANGLE=0;
	
	private int x;
	private int y;
	private int h;
	private int w;
	private Color color;
	private int type;
	private double completion;
	private boolean drawn;

	public DrawingProxy(int x, int y, int h, int w, Color color, int type) {
		super();
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.color = color;
		this.type = type;
		this.drawn = false;
	}
	
	public DrawingProxy(Point p1, Point p2, Color color, int type) {
		super();
		this.x = (int) p1.getX();
		this.y = (int) p1.getY();
		this.h = (int) (p2.getX() - p1.getX());
		this.w = (int) (p2.getY() - p1.getY());
		this.color = color;
		this.type = type;
	}
	
	public Point getCoord() {
		return new Point(x, y);
	}
	

	public int getHeight() {
		return h;
	}

	public void setHeight(int h) {
		this.h = h;
	}

	public int getWeight() {
		return w;
	}

	public void setWeight(int w) {
		this.w = w;
	}

	public void setBounds(int x, int y, int h, int w) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
	}
	
	public void setBounds(Point p1, Point p2) {
		this.x = (int) p1.getX();
		this.y = (int) p1.getY();
		this.h = (int) (p2.getX() - p1.getX());
		this.w = (int) (p2.getY() - p1.getY());
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public double getCompletion() {
		return completion;
	}

	public void setCompletion(double completion) {
		this.completion = completion;
	}

	public boolean isDrawn() {
		return drawn;
	}

	public void setDrawn(boolean drawn) {
		this.drawn = drawn;
	}
	
}
