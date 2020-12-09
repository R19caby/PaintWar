package com.paintwar.server.service.game;

import java.util.HashMap;
import java.util.List;

import com.paintwar.server.logger.Logger;
import com.paintwar.unicast.UnicastTransmitter;

public class DrawFillerThread extends Thread {
	
	private String drawName;
	private Double percent;
	private Double fillPerTick;
	private volatile boolean running = true;
	private List<UnicastTransmitter> transmitters;
	
	public DrawFillerThread(String drawName, Double fillPerTick, List<UnicastTransmitter> transmitters) {
		super();
		this.drawName = drawName;
		this.percent = .0;
		this.fillPerTick = fillPerTick;
		this.transmitters = transmitters;
	}

	public void run () {
		while (running) {
			if (percent < 1) {
				percent += fillPerTick;
				Logger.print("[Server/filler] Filling at " + percent);
				HashMap<String, Object> hm = new HashMap <String, Object> () ;
				hm.put ("percent", percent) ;
				for (UnicastTransmitter emetteur : transmitters) {
					emetteur.diffuseMessage (this.getClass().getPackageName(), "Fill", drawName, hm) ;
				}
			} else {
				Logger.print("[Server/filler] Finished filling " + drawName);
				this.interrupt();
			}
			try {
				sleep(30);
			} catch (InterruptedException e) {
				Logger.print("[Server/filler] Thread for filler stopped successfully");
				running = false;
			}
		}
	}
	
}
