package com.paintwar.server.service.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.paintwar.server.logger.Logger;
import com.paintwar.unicast.UnicastTransmitter;

public class GameLoop extends Thread {
	
	private List<String> drawFillingNames;
	private List<String> drawToStopNames;
	private volatile boolean running = true;
	private DrawZoneProxy dz;
	private int tick;
	
	private List<UnicastTransmitter> transmitters;
	
	public GameLoop(List<UnicastTransmitter> transmitters) {
		super();
		this.drawFillingNames = new ArrayList<String>();
		this.drawToStopNames = new ArrayList<String>();
		this.transmitters = transmitters;
		this.dz = new DrawZoneProxy();
	}
	
	public void addTransmitter(UnicastTransmitter emetteur) {
		transmitters.add(emetteur);
	}

	private void updateFillDrawing(String drawName) {
		//check if can update
		if (!drawToStopNames.contains(drawName)) {
			Double newPercent = dz.updateDrawing(drawName);
			if (newPercent != null) {
				Logger.print("[Server/gameloop " + tick + " ] Updating drawing " + drawName + " fill to " + newPercent);
				HashMap<String, Object> hm = new HashMap <String, Object> () ;
				hm.put ("percent", newPercent) ;
				for (UnicastTransmitter emetteur : transmitters) {
					emetteur.diffuseMessage (this.getClass().getPackageName(), "Fill", drawName, hm) ;
				}
				if (newPercent.equals(1.)) {
					Logger.print("[Server/filler] Finished filling for " + drawName);
					stopFillingDrawing(drawName);
				}
			} else {
				Logger.print("[Server/filler] Collision adding in stopping list : " + drawName);
				drawToStopNames.add(drawName);
			}
		}
	}
	
	public void addDrawing(String name, DrawingServerProxy proxy) {
		dz.addDrawing(name, proxy);
		drawFillingNames.add(name);
		
	}
	
	public void stopFillingDrawing(String name) {
		dz.stopDrawing(name);
		drawFillingNames.remove(name);
		for (UnicastTransmitter emetteur : transmitters) {
			emetteur.diffuseMessage (this.getClass().getPackageName(), "Drawn", name, null) ;
		}
	}
	
	public void deleteDrawing(String name) {
		dz.removeDrawing(name);
		drawFillingNames.remove(name);
	}
	
	//main game loop
	public void run () {
		while (running) {
			List<String> currentDrawFillingNames = List.copyOf(drawFillingNames);
			List<String> currentDrawToStopNames = List.copyOf(drawToStopNames);
			for (String drawing : currentDrawFillingNames) {
				updateFillDrawing(drawing);
			}
			//removing drawings to stop
			for (String drawing : currentDrawToStopNames) {
				stopFillingDrawing(drawing);
			}
			tick++;
			try {
				sleep(30);
			} catch (InterruptedException e) {
				Logger.print("[Server/filler] Thread of the game stopped successfully");
				running = false;
			}
		}
	}



	
}
