package com.paintwar.server.service.game;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.paintwar.server.logger.Logger;

public class DrawZoneProxy {
	
	private Map<String, HitboxProxy> hitboxes;
	
	public DrawZoneProxy() {
		hitboxes = new HashMap<String, HitboxProxy>();
	}
	
	//add a box
	public void addBox(String name, HitboxProxy box) {
		hitboxes.put(name, box);
	}
	
	//check if can expand box
	public synchronized boolean updateBox(String name, Double percent) {
		HitboxProxy hitbox = hitboxes.get(name);
		HitboxProxy newHitbox = hitbox.copy();
		Boolean canUpdate = true;
		newHitbox.setPercent(percent);
		Logger.print("[Server/DrawZoneProxy] Checking hitbox " + hitbox.getBox() + " going to " + newHitbox.getBox() + " at " + percent);
		
		for (Entry<String, HitboxProxy> currentBox : hitboxes.entrySet()) {
			if (!currentBox.getKey().equals(name)) {
				//if intersects and not same color
				HitboxProxy currentHitbox = currentBox.getValue();
				Logger.print("[Server/DrawZoneProxy] checking with " + currentHitbox.getBox());
				if (newHitbox.getBox().intersects(currentHitbox.getBox()) /*&& newHitbox.getColor() != currentHitbox.getColor()*/) {
					Logger.print("boxes intersect");
					canUpdate = false;
					break;
				};
			}
		}
		
		if (canUpdate) {
			//update hitbox in list
			hitbox.setPercent(percent);
		}
		
		return canUpdate;
	}
	
	//remove hitbox if drawing finished
	public void removeBox(String name) {
		hitboxes.remove(name);
	}
	
}
