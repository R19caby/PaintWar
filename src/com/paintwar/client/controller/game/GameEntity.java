package com.paintwar.client.controller.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;

import javax.swing.JFrame;

import com.paintwar.client.logger.Logger;
import com.paintwar.client.view.pages.game.GamePage;
import com.paintwar.client.view.pages.game.listeners.FrameResizeListener;


public class GameEntity implements IGameEntity {
	
	private int drawCount;
	private HashMap<String, DrawingProxy> drawings;
	
	GameEntity() {
		drawings = new HashMap<String, DrawingProxy>();
		drawCount = 0;
	}
	
	private void deleteAllDrawings() {
		drawings.clear();
		drawCount = 0;
	}
	
	@Override
	public void startGame() {
		this.deleteAllDrawings();
	}

	@Override
	public String paint(Point p1, Point p2, Color c) {
		String drawName = "draw" + drawCount++;
		
		drawings.put(drawName, new DrawingProxy(p1, p2, c, DrawingProxy.RECTANGLE));
		Logger.print("[Game] Putting drawing named " + drawName);
		return drawName;
	}
	
	@Override
	public void updateCoordPaint(String name, Point p1, Point p2) {
		DrawingProxy drawToChange = drawings.get(name);
		if (drawToChange != null) {
			drawToChange.setBounds(p1, p2);
		} else {
			Logger.print("[Game] Couldn't find drawing proxy to change coord");
		}
	}
	
	@Override
	public void updateEndPointPaint(String name, Point p) {
		DrawingProxy drawToChange = drawings.get(name);
		if (drawToChange != null) {
			drawToChange.setBounds(drawToChange.getCoord(), p);
		} else {
			Logger.print("[Game] Couldn't find drawing proxy to change coord");
		}
	}
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		GameEntity ge = new GameEntity();
		GamePage gp = new GamePage(ge);
		
		window.addComponentListener(new FrameResizeListener(gp));
		window.getContentPane().add(gp, BorderLayout.CENTER);
		
		window.setVisible(true);
		window.setSize(700, 400);
		gp.updatePage();
	}
}
