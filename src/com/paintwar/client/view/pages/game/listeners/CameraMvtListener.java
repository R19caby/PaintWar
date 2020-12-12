package com.paintwar.client.view.pages.game.listeners;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.paintwar.client.view.pages.game.GamePage;
import com.paintwar.client.view.pages.game.threads.CameraMoverThread;

public class CameraMvtListener implements MouseMotionListener, MouseListener {

	private int frameH;
	private int frameW;
	private int hitboxWidth = GamePage.CAMERA_HITBOX_WIDTH;
	private int moveSensi = GamePage.CAMERA_SENSIBILITY;
	private Rectangle topHitbox;
	private Rectangle botHitbox;
	private Rectangle leftHitbox;
	private Rectangle rightHitbox;
	private CameraMoverThread camThread;
	
	public CameraMvtListener(CameraMoverThread camThread) {
		super();
		this.camThread = camThread;
	}

	public void updateFrame(int newH, int newW) {
		frameH = newH;
		frameW = newW;
		updateHitboxes();
	}
	
	private void updateHitboxes() {
		topHitbox = new Rectangle(new Point(0,0));
		topHitbox.add(new Point(frameW, hitboxWidth));
		
		botHitbox = new Rectangle(new Point(0, frameH - hitboxWidth - 40)); //barre windows au dessus
		botHitbox.add(new Point(frameW, frameH));
		
		leftHitbox = new Rectangle(new Point(0,0));
		leftHitbox.add(new Point(hitboxWidth, frameH));
		
		rightHitbox = new Rectangle(new Point(frameW - hitboxWidth, 0));
		rightHitbox.add(new Point(frameW, frameH));
	}
	
	private void calculateAndMove(MouseEvent e) {
		Point p = e.getPoint();
		int dragX = 0;
		int dragY = 0;
		
		if (topHitbox.contains(p)) {
			dragY += moveSensi;
		}
		if (botHitbox.contains(p)) {
			dragY -= moveSensi;
		}
		if (leftHitbox.contains(p)) {
			dragX += moveSensi;
		}
		if (rightHitbox.contains(p)) {
			dragX -= moveSensi;
		}
		
		camThread.setDeltas(dragX, dragY);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		calculateAndMove(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		calculateAndMove(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		camThread.setDeltas(0, 0);
	}

}
