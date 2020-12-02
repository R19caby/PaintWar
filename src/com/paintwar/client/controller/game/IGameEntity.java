package com.paintwar.client.controller.game;

import java.awt.Color;
import java.awt.Point;

public interface IGameEntity {

	public void startGame();
	public String paint(Point p1, Point p2, Color c);
	public void updateCoordPaint(String name, Point p1, Point p2);
	public void updateEndPointPaint(String name, Point p);
	
}
