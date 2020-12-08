package com.paintwar.client.view.pages.game.listeners;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.paintwar.client.view.pages.game.elements.DrawZonePlaceholder;
import com.paintwar.client.view.pages.game.elements.Minimap;

public class MinimapMvtListener implements MouseListener, MouseMotionListener {

	private DrawZonePlaceholder dzPlaceHolder;
	private Minimap minimap;
	
	public MinimapMvtListener(DrawZonePlaceholder dzPlaceHolder, Minimap minimap) {
		super();
		this.dzPlaceHolder = dzPlaceHolder;
		this.minimap = minimap;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point newPos = e.getPoint();
		minimap.unScaleAndCenter(newPos);
		dzPlaceHolder.moveZoneCoord(-newPos.x, -newPos.y);
		minimap.moveCameraFrame(newPos.x, newPos.y);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point newPos = e.getPoint();
		minimap.unScaleAndCenter(newPos);
		dzPlaceHolder.moveZoneCoord(-newPos.x, -newPos.y);
		minimap.moveCameraFrame(newPos.x, newPos.y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
