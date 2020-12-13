package com.paintwar.server.service.game;

import java.awt.Color;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.paintwar.server.logger.Logger;
import com.paintwar.server.service.game.elements.DrawingServerProxy;
import com.paintwar.server.service.game.elements.Team;
import com.paintwar.unicast.UnicastTransmitter;

public class GameLoop extends Thread {
	
	private List<String> drawFillingNames;
	private List<String> drawToStopNames;
	private volatile boolean running = true;
	private DrawZoneProxy dz;
	private int tick;
	
	private List<UnicastTransmitter> transmitters;
	private GameServerEntity gameEntity;
	private Map<Color, Team> teams;
	
	public GameLoop(GameServerEntity gameEntity, List<UnicastTransmitter> transmitters, Map<Color, Team> teams) {
		super();
		this.drawFillingNames = new ArrayList<String>();
		this.drawToStopNames = new ArrayList<String>();
		this.transmitters = transmitters;
		this.teams = teams;
		this.dz = new DrawZoneProxy(teams);
		this.gameEntity = gameEntity;
	}
	
	public void addTransmitter(UnicastTransmitter emetteur) {
		transmitters.add(emetteur);
	}

	private void updateFillDrawing(String drawName) {
		Double newPercent = dz.updateDrawing(drawName);
		//check if it isn't stopped and doesn't need to be stopped
		if (newPercent != null && !drawToStopNames.contains(drawName)) {
			if (newPercent <= 0.) { //colliding but still needs to be filled to maximum
				newPercent = -newPercent;
				//Logger.print("[Server/filler] Collision adding in stopping list : " + drawName);
				drawToStopNames.add(drawName);
			} else if (newPercent.equals(1.)) { //finished filling, stopping TODO do I have to put in toStop list or stop right away?
				//Logger.print("[Server/filler] Finished filling for " + drawName);
				stopFillingDrawing(drawName);
			}
			//sending message to fill to everyone
			//Logger.print("[Server/gameloop " + tick + " ] Updating drawing " + drawName + " fill to " + newPercent);
			HashMap<String, Object> hm = new HashMap <String, Object> () ;
			hm.put ("percent", newPercent) ;
			for (UnicastTransmitter emetteur : transmitters) {
				emetteur.diffuseMessage (this.getClass().getPackageName(), "Fill", drawName, hm) ;
			}
		}
	}
	
	public void addDrawing(String name, DrawingServerProxy proxy) {
		dz.addDrawing(name, proxy);
		drawFillingNames.add(name);
	}
	
	public void stopFillingDrawing(String name) {
		Double percent = dz.getDrawing(name).getPercent();
		Boolean doRemove = dz.stopAndDoRemoveDrawing(name);
		drawFillingNames.remove(name);
		if (doRemove) {
			try {
				gameEntity.deleteDrawing(name);
			} catch (RemoteException e) {
				Logger.print("[Server/Gameloop] Couldn't delete drawing " + name);
				e.printStackTrace();
			}
		} else {
			for (UnicastTransmitter emetteur : transmitters) {
				emetteur.diffuseMessage (this.getClass().getPackageName(), "Drawn", name, null) ;
			}
			gameEntity.setDrawn(name, percent);
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
			
			//remove overwritten drawings
			List<String> drawNames = dz.removeOverwrittenDrawings();
			for (String name : drawNames) {
				deleteDrawing(name);
				try {
					gameEntity.deleteDrawing(name);
				} catch (RemoteException e) {
					Logger.print("[Server/Gameloop] Couldn't delete drawing " + name);
					e.printStackTrace();
				}
				Logger.print("[Server/Gameloop] Removed overwritten " + name);
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
