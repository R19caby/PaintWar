package com.paintwar.client.view.pages.game.listeners ;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

import com.paintwar.client.controller.game.GameEntity;
import com.paintwar.client.view.pages.game.elements.DrawZone;

public class DrawListener implements MouseListener, MouseMotionListener {

	private DrawZone drawZone;
	private String entityDrawnName;
	
	public DrawListener(DrawZone drawZone) {
		super();
		this.drawZone = drawZone;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			Point p = e.getPoint().getLocation();
			if (p != null) {
				drawZone.updateEndPointDraw(entityDrawnName, p);
				drawZone.repaint();
			}
		}
		
		//sending event to other listeners
		e.translatePoint(drawZone.getX(), drawZone.getY());
		drawZone.getParent().dispatchEvent(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//sending event to other listeners
		e.translatePoint(drawZone.getX(), drawZone.getY());
		drawZone.getParent().dispatchEvent(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			entityDrawnName = drawZone.initializeDraw(e.getPoint());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (drawZone.isBigEnough(entityDrawnName)
					&& drawZone.isOnTopFriendlyZone(entityDrawnName)
					&& drawZone.isAffordable(entityDrawnName)) {
				drawZone.startFilling(entityDrawnName);
				entityDrawnName = null;
			} else {
				drawZone.deleteDrawingRequest(entityDrawnName);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
