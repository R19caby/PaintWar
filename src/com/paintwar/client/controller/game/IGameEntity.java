package com.paintwar.client.controller.game;

import java.awt.Color;
import java.awt.Point;

import com.paintwar.server.service.game.DrawServerProxy;
import com.paintwar.server.service.game.IDrawServerProxy;

public interface IGameEntity {

	public void startGame();
	public String paintClient(Point p1, Point p2, Color c);
	public void updateCoordPaint(String name, Point p1, Point p2);
	public void updateCoordPaint(String name, Point p);
	DrawingProxy addDrawing(IDrawServerProxy proxy, String name, Point p1, Point p2, Color c);
	void removeDrawing(String objectName);
	void updateCoordPaintClient(String name, Point p);
	
}
