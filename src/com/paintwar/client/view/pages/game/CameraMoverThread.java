package com.paintwar.client.view.pages.game;

import com.paintwar.client.view.pages.game.elements.DrawZonePlaceholder;
import com.paintwar.server.logger.Logger;

public class CameraMoverThread extends Thread implements Runnable {
	
	private int deltaX;
	private int deltaY;
	private DrawZonePlaceholder DZPlaceholder;
	
	public CameraMoverThread(DrawZonePlaceholder dZPlaceholder) {
		super();
		DZPlaceholder = dZPlaceholder;
		deltaX = 0;
		deltaY = 0;
	}
	
	public void setDeltas(int newX, int newY) {
		deltaX = newX;
		deltaY = newY;
	}
	
	public void run () {
		while (true) {
			//Logger.print("Moving camera by : " + deltaX + " " + deltaY);
			if (deltaX != 0 || deltaY != 0) {
				DZPlaceholder.moveZone(deltaX, deltaY);	
			}
			try {
				sleep(20);
			} catch (InterruptedException e) {
				Logger.print("[Camera] Thread for camera interrupted while sleeping");
				this.interrupt();
			}
		}
	}
	
}
