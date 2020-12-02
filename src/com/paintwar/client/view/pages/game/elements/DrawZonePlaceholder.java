package com.paintwar.client.view.pages.game.elements;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.paintwar.client.view.pages.game.CameraMoverThread;
import com.paintwar.client.view.pages.game.listeners.CameraMvtListener;
import com.paintwar.server.logger.Logger;

public class DrawZonePlaceholder extends JPanel {
	
	private DrawZone zoneToScout;
	private JFrame currentFrame;
	private CameraMvtListener camLis;
	
	public DrawZonePlaceholder(DrawZone zone) {
		super();
		setLayout(null);
		this.zoneToScout = zone;
		add(zoneToScout);
		
		CameraMoverThread camThread = new CameraMoverThread(this);
		camLis = new CameraMvtListener(camThread);
		addMouseMotionListener(camLis);
		
		camThread.start();
	}
	
	public void updateFrame() {
		currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		camLis.updateFrame(currentFrame.getHeight(), currentFrame.getWidth());
		centerZone();
	}
	
	private void centerZone() {
		int centerX = currentFrame.getWidth()/2 - zoneToScout.getWidth()/2;
		int centerY = currentFrame.getHeight()/2 - zoneToScout.getHeight()/2;
		zoneToScout.setLocation(centerX, centerY);
	}
	
	public void moveZone(int x, int y) {
		//bad code below
		/*Rectangle zoneHitbox = zoneToScout.getBounds();
		zoneHitbox.translate(x, y);
		
		Rectangle cameraZone = new Rectangle(new Point(0, 0));
		cameraZone.add(new Point(currentFrame.getHeight(), currentFrame.getWidth())); //barre windows au dessus
		Logger.print("cameraRect : " + cameraZone);
		Logger.print("ZoneREct : " + zoneHitbox);
		
		
		
		if (zoneHitbox.contains(cameraZone)) {
			zoneToScout.setLocation(zoneToScout.getX() + x, zoneToScout.getY() + y);
		} else if (x != 0 && y != 0) {
			zoneHitbox.translate(0, -y); //move by x only
			if (zoneHitbox.contains(cameraZone)) {
				zoneToScout.setLocation(zoneToScout.getX() + x, zoneToScout.getY());
			} else {
				zoneHitbox.translate(-x, y); //move by y only
				if (zoneHitbox.contains(cameraZone)) {
					zoneToScout.setLocation(zoneToScout.getX(), zoneToScout.getY() + y);
				}
			}
		}*/
		
		int newX = zoneToScout.getX() + x;
		int miniX = currentFrame.getWidth() - zoneToScout.getWidth() - 15; //wtf it needs 15px to be kinda good
		if (newX > 0) {
			newX = 0;
			x = 0;
		} else if (newX < miniX) {
			newX = miniX;
			x = 0;
		}
		
		int newY = zoneToScout.getY() + y;
		int miniY = currentFrame.getHeight() - zoneToScout.getHeight() - 35; //there too
		if (newY > 0) {
			newY = 0;
			y = 0;
		} else if (newY < miniY) {
			newY = miniY;
			y = 0;
		}
		
		zoneToScout.setLocation(newX, newY);
		zoneToScout.updateEndPointDraw(x, y);
	}
	
}
