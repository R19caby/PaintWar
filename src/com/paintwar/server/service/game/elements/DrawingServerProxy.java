package com.paintwar.server.service.game.elements;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.paintwar.server.logger.Logger;


public class DrawingServerProxy {
	
	private String name;
	private Color color;
	private Point finalP1;
	private Point finalP2;
	private Point currentP1;
	private Point currentP2;
	private Double percent;
	private Double areaFillPerTick = 1000.;
	private Double percentPerTick;
	private boolean drawFixed;
	private Function<Double, Point> fillDraw;
	private Area removedArea;
	private String creatorID;
	
	public DrawingServerProxy(String name, Color color, Point p1, Point p2, String creatorID) {
		super();
		this.name = name;
		this.color = color;
		this.finalP1 = p1;
		this.finalP2 = p2;
		this.drawFixed = false;
		this.removedArea = new Area();
		this.creatorID = creatorID;
		updateFillDraw();
		calculatePercentPerTick();
	}

	private void updateFillDraw() {
		int w = (int) (finalP2.getX() - finalP1.getX());
		int h = (int) (finalP2.getY() - finalP1.getY());
		if ((w > 0 && h > 0) || (w<0 && h>0 && Math.abs(w) < Math.abs(h)) || (w>0 && h<0 && Math.abs(w) > Math.abs(h))) {
			/*Expand either down or right*/
			if (Math.abs(w) > Math.abs(h)) {
				/*Expand right*/
				currentP1 = new Point(0, 0);
				currentP2 = new Point(0, Math.abs(h));
			} else {
				/*Expand down*/
				currentP1 = new Point(0, 0);
				currentP2 = new Point(Math.abs(w), 0);
			}
		} else {
			/*Expand either up or left*/
			if (Math.abs(w) > Math.abs(h)) {
				/*Expand left*/
				currentP1 = new Point(Math.abs(w), 0);
				currentP2 = new Point(Math.abs(w), Math.abs(h));
			} else {
				/*Expand up*/
				currentP1 = new Point(0, Math.abs(h));
				currentP2 = new Point(Math.abs(w), Math.abs(h));
			}
		}
		
		if (Math.abs(w) > Math.abs(h)) {
			/*Need to modify 2nd point x to fill*/
			fillDraw = (Double percent) -> {
				int newX = currentP1.x + (int) (percent*(Math.abs(w)-(2*currentP1.x)));
				int newY = currentP2.y;
				return new Point(newX, newY);
			};
		} else {
			/*need to modify y*/
			fillDraw = (Double percent) -> {
				int newX = currentP2.x ;
				int newY = currentP1.y + (int) (percent*(Math.abs(h)-(2*currentP1.y)));
				return new Point(newX, newY);
			};
		}
		setPercent(0.);
	}
	
	
	private void calculatePercentPerTick() {
		int w = Math.abs((int) (finalP2.getX() - finalP1.getX()));
		int h = Math.abs((int) (finalP2.getY() - finalP1.getY()));
		percentPerTick = areaFillPerTick/(w*h);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Point getCurrentP2() {
		//return currentP2 in drawZone
		Point p = currentP2.getLocation();
		p.translate(Math.min(finalP1.x, finalP2.x), Math.min(finalP1.y, finalP2.y));
		return p;
	}
	
	public Point getCurrentP1() {
		//return currentP1 in drawZone
		Point p = currentP1.getLocation();
		p.translate(Math.min(finalP1.x, finalP2.x), Math.min(finalP1.y, finalP2.y));
		return p;
	}
	
	public Rectangle getBox() {
		//generate hitbox
		Rectangle r = new Rectangle(currentP1);
		r.add(currentP2);
		
		//translate it to have the real position in drawzone
		r.translate(Math.min(finalP1.x, finalP2.x), Math.min(finalP1.y, finalP2.y));
		return r;
	}
	
	public Rectangle getFinalBox() {
		//generate hitbox
		Rectangle r = new Rectangle(finalP1);
		r.add(finalP2);
		
		return r;
	}

	public Double getPercent() {
		return percent;
	}

	public Double upPercent() {
		this.percent = percent + percentPerTick;
		if (percent >= 1) {
			percent = 1.;
		}
		currentP2 = fillDraw.apply(percent);
		return percent;
	}
	
	public void setPercent(Double percent) {
		this.percent = percent;
		if (percent >= 1) {
			percent = 1.;
		}
		currentP2 = fillDraw.apply(percent);
	}
	
	public DrawingServerProxy copy() {
		DrawingServerProxy copy = new DrawingServerProxy(name, color, finalP1, finalP2, creatorID);
		copy.setPercent(percent);
		copy.addRemovedArea(removedArea);
		return copy;
	}

	public Double getAreaFillPerTick() {
		return areaFillPerTick;
	}

	public void setAreaFillPerTick(Double areaFillPerTick) {
		this.areaFillPerTick = areaFillPerTick;
		calculatePercentPerTick();
	}

	public boolean isDrawFixed() {
		return drawFixed;
	}

	public void setDrawFixed(boolean drawFixed) {
		this.drawFixed = drawFixed;
	}
	
	public Area getRemovedArea() {
		return removedArea;
	}
	
	public void addRemovedArea(Area a) {
		removedArea.add(a);
	}
	
}
