package com.paintwar.client.view.pages.game.elements;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.paintwar.client.controller.game.GameEntity;
import com.paintwar.client.logger.Logger;
import com.paintwar.client.view.pages.game.listeners.DrawListener;
import com.paintwar.server.service.game.GameConfig;

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
		
		this.setBounds(0, 0, GameConfig.DRAWZONE_AREA_WIDTH, (int) (GameConfig.DRAWZONE_AREA_WIDTH/GameConfig.ASPECT_RATIO));
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 10));
		this.setLayout(null);
		DrawListener drawLis = new DrawListener(this);
		this.addMouseListener(drawLis);
		this.addMouseMotionListener(drawLis);
	}
	
	/*Add a drawing to zone and gameEntity*/
	public String initializeDraw(Point p) {
		Color color = gameEntity.getClientTeamColor();
		String entiName = gameEntity.paintClient(p, p, color);
		minimap.paint(entiName, p, p, color, SCHEMA_OPACITY, null);
		Drawing newDraw = new Drawing(color, SCHEMA_OPACITY);
		drawPanels.put(entiName, newDraw);
		newDraw.setBounds(new Rectangle(p));
		newDraw.setInitPoint(p);

		newDraw.setDisplayColor(Color.black);
		add(newDraw, 0);
		this.setComponentZOrder(newDraw, 0);
		this.currentDrawingByUser = entiName;
		
		return entiName;
	}
	
	/*Update a drawing 2nd point*/
	public void updateEndPointDraw(String name, Point p) {
		Drawing drawToUpdate = drawPanels.get(name);
		if (drawToUpdate != null) {
			Rectangle r = new Rectangle(drawToUpdate.getInitPoint());
			r.add(p);
			drawToUpdate.setDisplayColor(Color.red);
			drawToUpdate.setBounds(r);
			drawToUpdate.setEndPoint(p);
			
			if (isBigEnough(name)
					&& isOnTopFriendlyZone(name)
					&& isAffordable(name)) { //send updates if big enough
				drawToUpdate.setDisplayColor(Color.black);
				minimap.updateEndPointPaint(name, p);
				gameEntity.updateCoordPaintClient(name, p);
			}
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
			currentDraw.setDisplayColor(Color.red);
			currentDraw.setBounds(r);
			currentDraw.setEndPoint(endPoint);
			if (isBigEnough(currentDrawingByUser)
					&& isOnTopFriendlyZone(currentDrawingByUser)
					&& isAffordable(currentDrawingByUser)) { //send updates if big enough
				currentDraw.setDisplayColor(Color.black);
				gameEntity.updateCoordPaintClient(currentDrawingByUser, endPoint);
				minimap.updateEndPointPaint(currentDrawingByUser, endPoint);
			}
			
		}
		
	}
	
	public void addDrawing(String name, Point p1, Point p2, Color c, Double percent) {
		minimap.paint(name, p1, p2, c, SCHEMA_OPACITY, percent);
		Drawing newDraw = new Drawing(c, SCHEMA_OPACITY);
		drawPanels.put(name, newDraw);
		
		//generate physical drawing
		Rectangle r = new Rectangle(p1);
		r.add(p2);
		newDraw.setBounds(r);
		newDraw.setInitPoint(p1);
		newDraw.setEndPoint(p2);
		
		//filling the drawing 
		if (percent != null) {
			newDraw.setFilling(percent);
			newDraw.setDrawn();
		}
		add(newDraw);
		this.setComponentZOrder(newDraw, 0);
	}
	
	public void deleteDrawing(String name) {
		minimap.deleteDraw(name);
		Drawing drawing = drawPanels.get(name);
		if (drawing != null) {
			this.remove(drawing);
			this.repaint();
		}
		drawPanels.remove(name);
	}
	
	public void deleteDrawingRequest(String entityDrawnName) {
		gameEntity.removeDrawingClient(entityDrawnName);
	}

	public void updateFilling(String name, Double percent) {
		Drawing drawing = drawPanels.get(name);
		if (drawing != null) {
			drawing.setFilling(percent);
			this.setComponentZOrder(drawing, 0);
			minimap.updateFilling(name, percent);
		}
	}

	public void startFilling(String entityDrawnName) {
		currentDrawingByUser = null;
		gameEntity.startFilling(entityDrawnName);
	}

	public void setDrawn(String name) {
		Drawing drawing = drawPanels.get(name);
		if (drawing != null)
			drawing.setDrawn();
		minimap.setDrawn(name);
	}

	public boolean isAffordable(String name) {
		Drawing currentDraw = drawPanels.get(name);
		Point p1 = currentDraw.getInitPoint();
		Point p2 = currentDraw.getEndPoint();
		Rectangle r = new Rectangle(p1);
		r.add(p2);
		return r.getWidth()*r.getHeight()*GameConfig.INK_AREA_COST <= gameEntity.getClientInk();
	}
	
	public boolean isBigEnough(String entityDrawnName) {
		Drawing currentDraw = drawPanels.get(entityDrawnName);
		Rectangle r = currentDraw.getBounds();
		return r.getWidth()*r.getHeight() > GameConfig.MINIMUM_AREA
				&& r.getWidth() > GameConfig.MINIMUM_AREA_WIDTH_HEIGHT
				&& r.getHeight() > GameConfig.MINIMUM_AREA_WIDTH_HEIGHT;
	}
	
	public boolean isOnTopFriendlyZone(String name) {
		Drawing drawToCheck = drawPanels.get(name);
		
		boolean isDrawnOnFriendlyZone = false;
		int lastZoneZOrder = this.getComponentCount();
		for (Entry<String, Drawing> otherDrawingEntry : drawPanels.entrySet()) {
			Drawing otherDrawing = otherDrawingEntry.getValue();
			int currentZOrder = this.getComponentZOrder(otherDrawing);
			//if not same name, zOrder over last tested drawing,  same color and initPoint on top of it => all good
			if (!otherDrawingEntry.getKey().equals(name)
					&& otherDrawing.getColor().equals(drawToCheck.getColor()) 
					&& otherDrawing.getFillingBox().contains(drawToCheck.getInitPoint())
					&& currentZOrder < lastZoneZOrder) {
				isDrawnOnFriendlyZone = true;
			
			//update z order only if overlapping an ennemy zone
			} else if (!otherDrawingEntry.getKey().equals(name)
					&& !otherDrawing.getColor().equals(drawToCheck.getColor()) 
					&& otherDrawing.getFillingBox().contains(drawToCheck.getInitPoint())){
				lastZoneZOrder = Math.min(currentZOrder, lastZoneZOrder);
			}
		}
		
		return isDrawnOnFriendlyZone;
	}

	public void updateInkLimit() {
		//re-set currentdrawing
		Drawing currentDraw = drawPanels.get(currentDrawingByUser);
		if (currentDraw != null) {
			Point p = currentDraw.getEndPoint();
			if (p != null)
				updateEndPointDraw(0, 0);
		}
	}
	
	
}
