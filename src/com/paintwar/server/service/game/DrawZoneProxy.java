package com.paintwar.server.service.game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.paintwar.server.logger.Logger;
import com.paintwar.server.service.game.elements.DrawingServerProxy;
import com.paintwar.server.service.game.elements.Team;

public class DrawZoneProxy {
	
	private static final int DEL_THRESHOLD = 50;
	private Map<String, DrawingServerProxy> drawings;
	private Map<Color, Team> teams;
	
	public DrawZoneProxy(Map<Color, Team> teams) {
		drawings = new HashMap<String, DrawingServerProxy>();
		this.teams = teams;
	}
	
	public int getAreaValue(Area a) {
		PathIterator path = a.getPathIterator(null);
		double[] coords = new double[6];
		List<Double> x = new ArrayList<Double>();
		List<Double> y = new ArrayList<Double>();

		//get coords of all points
		while(!path.isDone()) {
			path.currentSegment(coords);
			x.add(coords[0]);
			y.add(coords[1]);
			path.next();
		}
		
		//calculate area
		double sum = 0;
		int nPoints = x.size();
        for (int i = 0; i < nPoints ; i++) {
            sum = sum + x.get(i)*y.get((i+1)%nPoints) - y.get(i)*x.get((i+1)%nPoints);
        }

        return (int) Math.abs(sum / 2);
	}
	
	//add a drawing to the list
	public synchronized void addDrawing(String name, DrawingServerProxy box) {
		drawings.put(name, box);
	}
	
	//check if can expand box
	public synchronized Double updateDrawing(String name) {
		DrawingServerProxy drawing = drawings.get(name);
		Double newPercent = null;
		int scoreToIgnore = 0;
		
		if (!drawing.isDrawFixed()) {
			DrawingServerProxy newDrawing = drawing.copy();
			Boolean canUpdate = true;
			newDrawing.upPercent();
			//Logger.print("[Server/DrawZoneProxy] Checking hitbox " + drawing.getBox() + " going to " + newDrawing.getBox());
			
			for (Entry<String, DrawingServerProxy> currentDrawItem : drawings.entrySet()) {
				if (!currentDrawItem.getKey().equals(name)) {
					DrawingServerProxy currentDraw = currentDrawItem.getValue();
					//Logger.print("[Server/DrawZoneProxy] checking with " + currentDraw.getBox());
					//if intersects
					if (newDrawing.getBox().intersects(currentDraw.getBox())) {
						//Logger.print("boxes intersect");
						if (currentDraw.isDrawFixed()) {
							//Logger.print("can paint over");
							//update paint score for other team
							Team teamToRemove = teams.get(currentDraw.getColor());
							
							//generating the rectangle of the new part added to the drawing
							Point newP2 = newDrawing.getCurrentP2();
							Point oldP2 = drawing.getCurrentP2();
							if (newP2.x != oldP2.x) {
								//expanding horizontally,lk setting oldP2 to be the other angle of the rectangle
								oldP2.setLocation(oldP2.x, drawing.getCurrentP1().y);
							} else {
								oldP2.setLocation(drawing.getCurrentP1().x, oldP2.y);
							}
							Rectangle addedArea = new Rectangle(oldP2);
							addedArea.add(newP2);
							
							//getting intersection with the team draw
							Rectangle rectangleOvertaken = addedArea.intersection(currentDraw.getBox());
							Area areaOvertaken = new Area(rectangleOvertaken);
							int scoreToRemove = getAreaValue(areaOvertaken);
							//Logger.print("Score to remove : " + scoreToRemove);
							
							
							//getting section from areaOvertaken that has already been overtaken
							Area areaToCheck = (Area) currentDraw.getRemovedArea().clone();
							//Logger.print("AreaTocheck before inter : " + getAreaValue(areaToCheck));
							areaToCheck.intersect(new Area(areaOvertaken));
							//Logger.print("AreaTocheck after inter : " + getAreaValue(areaToCheck));
							
							//remove area already overtaken
							scoreToRemove = scoreToRemove - getAreaValue(areaToCheck);
							
							//Logger.print("[Server/DrawZoneProxy] Removing " + scoreToRemove + " from " + teamToRemove.getScore());
							teamToRemove.addScore(-scoreToRemove);
							//Logger.print("Removed area of " + currentDrawItem.getKey() + " : " + currentDraw.getRemovedArea().isEmpty());
							currentDraw.addRemovedArea(new Area(areaOvertaken));
							//Logger.print("Removed area of " + currentDrawItem.getKey() + " : " + getAreaValue(currentDraw.getRemovedArea()));

						//if intersects and not same color then stop it
						} else if (!drawing.getColor().equals(currentDraw.getColor())) {
							if (drawing.getPercent() != 0) {
								//calculate leftover to expand (cross product)
								Rectangle areaToDelete = newDrawing.getBox().intersection(currentDraw.getBox());
								if (newDrawing.getBox().getHeight() == drawing.getBox().getHeight()) {
									Double finalWidth = newDrawing.getBox().getWidth()-areaToDelete.getWidth() - 1; //-1 to avoid mutual blocking 
									newPercent = finalWidth*drawing.getPercent()/drawing.getBox().getWidth();
								} else {
									Double finalHeight = newDrawing.getBox().getHeight()-areaToDelete.getHeight() - 1;
									newPercent = finalHeight*drawing.getPercent()/drawing.getBox().getHeight();	
								}
							} else {
								//the drawing hasn't started
								newPercent = 0.;
							}
							canUpdate = false;
							break;
						}
					}
				}
			}
			
			Double oldPercent = drawing.getPercent();
			if (canUpdate) {
				//update drawing in the list and get the new percent for clients
				newPercent = drawing.upPercent();
			} else {
				//fill the leftover area before collision
				drawing.setPercent(newPercent);
				newPercent = -newPercent;
			}
			
			//update scores
			Team currentTeam = teams.get(drawing.getColor());
			Rectangle drawSizes = drawing.getFinalBox();
			Integer scoreToAdd = (int) ((Math.abs(newPercent)-oldPercent)*drawSizes.width*drawSizes.height);
			currentTeam.addScore(Math.max(0, scoreToAdd - scoreToIgnore));
		}
		
		return newPercent;
	}
	
	public synchronized void removeDrawing(String name) {
		drawings.remove(name);
	}

	public synchronized boolean stopAndDoRemoveDrawing(String drawName) {
		DrawingServerProxy drawing = drawings.get(drawName);
		if (drawing != null) {
			if (drawing.getPercent().equals(0.)) {
				//removing drawing that hasn't even started
				removeDrawing(drawName);
				return true;
			} else {
				drawing.setDrawFixed(true);
				return false;
			}
		}
		return true;
	}

	public synchronized DrawingServerProxy getDrawing(String drawName) {
		DrawingServerProxy drawing = drawings.get(drawName);
		return drawing;
	}

	public synchronized List<String> removeOverwrittenDrawings() {
		ArrayList<String> names = new ArrayList<String>();
		
		for (Entry<String, DrawingServerProxy> drawingEntry : drawings.entrySet()) {
			DrawingServerProxy drawing = drawingEntry.getValue();
			Area leftover = new Area(drawing.getBox());
			leftover.subtract(drawing.getRemovedArea());
			
			if(getAreaValue(leftover) < DEL_THRESHOLD && drawing.isDrawFixed()) {
				names.add(drawingEntry.getKey());
			}
		}
		
		return names;
	}
	
}
