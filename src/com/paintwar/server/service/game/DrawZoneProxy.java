package com.paintwar.server.service.game;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.paintwar.server.logger.Logger;
import com.paintwar.server.service.game.elements.DrawingServerProxy;
import com.paintwar.server.service.game.elements.Team;

public class DrawZoneProxy {
	
	private Map<String, DrawingServerProxy> drawings;
	private Map<Color, Team> teams;
	
	public DrawZoneProxy(Map<Color, Team> teams) {
		drawings = new HashMap<String, DrawingServerProxy>();
		this.teams = teams;
	}
	
	//add a drawing to the list
	public void addDrawing(String name, DrawingServerProxy box) {
		drawings.put(name, box);
	}
	
	//check if can expand box
	public synchronized Double updateDrawing(String name) {
		DrawingServerProxy drawing = drawings.get(name);
		Double newPercent = null;
		
		if (!drawing.isDrawFixed()) {
			DrawingServerProxy newDrawing = drawing.copy();
			Boolean canUpdate = true;
			newDrawing.upPercent();
			//Logger.print("[Server/DrawZoneProxy] Checking hitbox " + drawing.getBox() + " going to " + newDrawing.getBox());
			
			for (Entry<String, DrawingServerProxy> currentDrawItem : drawings.entrySet()) {
				if (!currentDrawItem.getKey().equals(name)) {
					DrawingServerProxy currentDraw = currentDrawItem.getValue();
					//Logger.print("[Server/DrawZoneProxy] checking with " + currentDraw.getBox());
					//if intersects and not same color
					if (newDrawing.getBox().intersects(currentDraw.getBox()) && !newDrawing.getColor().equals(currentDraw.getColor())) {
						//Logger.print("boxes intersect");
						if (currentDraw.isDrawFixed()) {
							//Logger.print("can paint over");
							//update paint score for other team
							/*Team teamToRemove = teams.get(currentDraw.getColor());
							Rectangle drawSizes = drawing.getFinalBox();
							Integer scoreToRemove = (int) ((newPercent-oldPercent)*drawSizes.width*drawSizes.height);
							Integer scoreToRemove = removedZone.width*removedZone.height;
							Logger.print("[Server/DrawZoneProxy] Removing " + scoreToRemove + " from " + teamToRemove.getScore());
							teamToRemove.addScore(-scoreToRemove);*/
							
						} else {
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
					};
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
			Integer scoreToAdd = (int) ((newPercent-oldPercent)*drawSizes.width*drawSizes.height);
			currentTeam.addScore(scoreToAdd);
		}
		
		return newPercent;
	}
	
	public void removeDrawing(String name) {
		drawings.remove(name);
	}

	public boolean stopAndDoRemoveDrawing(String drawName) {
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

	public DrawingServerProxy getDrawing(String drawName) {
		DrawingServerProxy drawing = drawings.get(drawName);
		return drawing;
	}
	
}
