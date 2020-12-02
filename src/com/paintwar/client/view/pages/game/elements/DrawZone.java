package com.paintwar.client.view.pages.game.elements;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.paintwar.client.controller.game.GameEntity;
import com.paintwar.client.logger.Logger;
import com.paintwar.client.view.pages.game.listeners.DrawListener;

public class DrawZone extends JPanel {
	
	private GameEntity gameEntity;
	private HashMap<String, Drawing> drawPanels;
	private String currentDrawingByUser;
	
	public DrawZone(GameEntity gameEntity) {
		this.gameEntity = gameEntity;
		this.drawPanels = new HashMap<String, Drawing>();
		
		this.setBounds(0, 0, 3000, 3000);
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 10));
		this.setLayout(null);
		DrawListener drawLis = new DrawListener(this);
		this.addMouseListener(drawLis);
		this.addMouseMotionListener(drawLis);
	}
	
	public String initializeDraw(Point p) {
		String entiName = gameEntity.paint(p, p, Color.black);
		Drawing newDraw = new Drawing(Color.black);
		drawPanels.put(entiName, newDraw);
		newDraw.setBounds(new Rectangle(p));
		newDraw.setInitPoint(p);
		add(newDraw);
		this.currentDrawingByUser = entiName;
		
		return entiName;
	}
	
	public void updateEndPointDraw(String name, Point p) {
		Drawing drawToUpdate = drawPanels.get(name);
		if (drawToUpdate != null) {
			Rectangle r = new Rectangle(drawToUpdate.getInitPoint());
			r.add(p);
			drawToUpdate.setBounds(r);
			drawToUpdate.setEndPoint(p);
			gameEntity.updateEndPointPaint(name, p);
		} else if (name != null) {
			Logger.print("[Game] Couldn't find drawing to change coord");
		}
	}
	
	public void setCurrentDrawing(String name) {
		this.currentDrawingByUser = name;
	}
	
	public void updateEndPointDraw(int x, int y) {
		Drawing currentDraw = drawPanels.get(currentDrawingByUser);
		if (currentDraw != null) {
			Point endPoint = currentDraw.getEndPoint();
			if (endPoint == null) {
				endPoint = currentDraw.getInitPoint();
			}
			endPoint.translate(-x, -y);
			Rectangle r = new Rectangle(currentDraw.getInitPoint());
			r.add(endPoint);
			currentDraw.setBounds(r);
			currentDraw.setEndPoint(endPoint);
			gameEntity.updateEndPointPaint(currentDrawingByUser, endPoint);
		}
		
	}
	
}
