package com.paintwar.server.service.game;

import java.util.HashMap;
import java.util.List;

import com.paintwar.server.logger.Logger;
import com.paintwar.unicast.UnicastTransmitter;

public class GameLoop extends Thread {
	
	private String drawName;
	private Double percent;
	private Double fillPerTick;
	private volatile boolean running = true;
	private DrawZoneProxy dz;
	private List<UnicastTransmitter> transmitters;
	
	public GameLoop(String drawName, Double fillPerTick, List<UnicastTransmitter> transmitters, DrawZoneProxy dz) {
		super();
		this.drawName = drawName;
		this.percent = .0;
		this.fillPerTick = fillPerTick;
		this.transmitters = transmitters;
		this.dz = dz;
	}

	private void fillRect() throws InterruptedException {
		//check if can update
		if (dz.updateBox(drawName, percent)) {
			Logger.print("[Server/filler] Filling at " + percent);
			HashMap<String, Object> hm = new HashMap <String, Object> () ;
			hm.put ("percent", percent) ;
			for (UnicastTransmitter emetteur : transmitters) {
				emetteur.diffuseMessage (this.getClass().getPackageName(), "Fill", drawName, hm) ;
			}
		} else {
			Logger.print("[Server/filler] Collision stopping filling " + drawName);
			sleep(100);
			this.interrupt();
		}
	}
	
	public void run () {
		while (running) {
			try {
				if (percent < 1) {
					percent += fillPerTick;
					fillRect();
				} else {
					percent = 1.;
					fillRect();
					Logger.print("[Server/filler] Finished filling " + drawName);
					this.interrupt();
				}
				sleep(30);
			} catch (InterruptedException e) {
				Logger.print("[Server/filler] Thread for filler stopped successfully");
				dz.removeBox(drawName);
				running = false;
			}
		}
	}
	
}
