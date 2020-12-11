package com.paintwar.client.controller.game;

import java.awt.Color;
import java.awt.Point;

import com.paintwar.server.service.game.IDrawServerRemote;
import com.paintwar.server.service.game.elements.DrawingRemote;

public interface IGameEntity {

	public void startGame();
	public String paintClient(Point p1, Point p2, Color c);
	public void updateCoordPaint(String name, Point p1, Point p2);
	public void updateCoordPaint(String name, Point p);
	DrawingProxy addDrawing(String name, Point p1, Point p2, Color c);
	void removeDrawing(String objectName);
	void updateCoordPaintClient(String name, Point p);
	
}
