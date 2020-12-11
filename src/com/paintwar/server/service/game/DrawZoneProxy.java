package com.paintwar.server.service.game;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.paintwar.server.logger.Logger;

public class DrawZoneProxy {
	
	private Map<String, DrawingServerProxy> drawings;
	
	public DrawZoneProxy() {
		drawings = new HashMap<String, DrawingServerProxy>();
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
			Logger.print("[Server/filler] Filling drawing " + name);
			DrawingServerProxy newDrawing = drawing.copy();
			Boolean canUpdate = true;
			newDrawing.upPercent();
			Logger.print("[Server/DrawZoneProxy] Checking hitbox " + drawing.getBox() + " going to " + newDrawing.getBox());
			
			for (Entry<String, DrawingServerProxy> currentDrawItem : drawings.entrySet()) {
				if (!currentDrawItem.getKey().equals(name)) {
					DrawingServerProxy currentDraw = currentDrawItem.getValue();
					Logger.print("[Server/DrawZoneProxy] checking with " + currentDraw.getBox());
					//if intersects and not same color
					if (newDrawing.getBox().intersects(currentDraw.getBox()) /*&& newHitbox.getColor() != currentHitbox.getColor()*/) {
						Logger.print("boxes intersect");
						if (currentDraw.isDrawFixed()) {
							Logger.print("can paint over");
							//update paint score here for other team
						} else {
							//calculate leftover to expand
							
							canUpdate = false;
							break;
						}
					};
				}
			}
			
			if (canUpdate) {
				//update drawing in the list and get the new percent for clients
				newPercent = drawing.upPercent();
			}
		}
		
		return newPercent;
	}
	
	public void removeDrawing(String name) {
		drawings.remove(name);
	}

	public void stopDrawing(String drawName) {
		DrawingServerProxy drawing = drawings.get(drawName);
		if (drawing != null) {
			drawing.setDrawFixed(true);
		}
	}
	
}
