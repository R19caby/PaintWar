package com.paintwar.client.view.pages.game.listeners;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import com.paintwar.client.view.pages.game.GamePage;

public class FrameResizeListener implements ComponentListener {

	private GamePage gamePage;
	
    public FrameResizeListener(GamePage gamePage) {
		super();
		this.gamePage = gamePage;
	}
    
	public void componentHidden(ComponentEvent e) {}
    public void componentMoved(ComponentEvent e) {}
    public void componentShown(ComponentEvent e) {}

    public void componentResized(ComponentEvent e) {
    	gamePage.updatePage();
    }   
}