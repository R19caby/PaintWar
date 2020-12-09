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
	private Minimap minimap;
	private static int SCHEMA_OPACITY = 30;
	
	public DrawZone(GameEntity gameEntity, Minimap minimap) {
		this.gameEntity = gameEntity;
		this.minimap = minimap;
		this.drawPanels = new HashMap<String, Drawing>();
		
		this.setBounds(0, 0, 3000, 3000);
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 10));
		this.setLayout(null);
		DrawListener drawLis = new DrawListener(this);
		this.addMouseListener(drawLis);
		this.addMouseMotionListener(drawLis);
	}
	
	/*Add a drawing to zone and gameEntity*/
	public String initializeDraw(Point p, Color color) {
		String entiName = gameEntity.paintClient(p, p, color);
		minimap.paint(entiName, p, p, color, SCHEMA_OPACITY);
		Drawing newDraw = new Drawing(color, SCHEMA_OPACITY);
		drawPanels.put(entiName, newDraw);
		newDraw.setBounds(new Rectangle(p));
		newDraw.setInitPoint(p);
		add(newDraw);
		this.currentDrawingByUser = entiName;
		
		return entiName;
	}
	
	/*Update a drawing 2nd point*/
	public void updateEndPointDraw(String name, Point p) {
		Drawing drawToUpdate = drawPanels.get(name);
		if (drawToUpdate != null) {
			Rectangle r = new Rectangle(drawToUpdate.getInitPoint());
			r.add(p);
			drawToUpdate.setBounds(r);
			drawToUpdate.setEndPoint(p);
			minimap.updateEndPointPaint(name, p);
			gameEntity.updateCoordPaintClient(name, p);
		} else if (name != null) {
			Logger.print("[Game] Couldn't find drawing to change coord");
		}
	}
	
	/*Update a drawing points*/
	public void updateDraw(String name, Point p1, Point p2) {
		Drawing drawToUpdate = drawPanels.get(name);
		if (drawToUpdate != null) {
			Rectangle r = new Rectangle(p1);
			r.add(p2);
			drawToUpdate.setBounds(r);
			drawToUpdate.setInitPoint(p1);
			drawToUpdate.setEndPoint(p2);
			minimap.updatePointPaint(name, p1, p2);
		} else if (name != null) {
			Logger.print("[Game] Couldn't find drawing to change coord");
		}
	}
	
	public void setCurrentDrawing(String name) {
		this.currentDrawingByUser = name;
	}
	
	/*Update current drawing 2nd point*/
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
			gameEntity.updateCoordPaintClient(currentDrawingByUser, endPoint);
			minimap.updateEndPointPaint(currentDrawingByUser, endPoint);
		}
		
	}
	
	public void addDrawing(String name, Point p1, Point p2, Color c) {
		minimap.paint(name, p1, p2, c, SCHEMA_OPACITY);
		Drawing newDraw = new Drawing(c, SCHEMA_OPACITY);
		drawPanels.put(name, newDraw);
		
		//generate physical drawing
		Rectangle r = new Rectangle(p1);
		r.add(p2);
		newDraw.setBounds(r);
		newDraw.setInitPoint(p1);
		newDraw.setEndPoint(p2);
		add(newDraw);
	}
	
	public void deleteDrawing(String name) {
		minimap.deleteDraw(name);
		Drawing drawing = drawPanels.get(name);
		if (drawing != null) {
			this.remove(drawing);
			this.repaint();
		}
	}

	public void updateFilling(String name, Double percent) {
		Drawing drawing = drawPanels.get(name);
		if (drawing != null)
			drawing.setFilling(percent);
		minimap.updateFilling(name, percent);
	}

	public void startFilling(String entityDrawnName) {
		gameEntity.startFilling(entityDrawnName);
	}
	
}
