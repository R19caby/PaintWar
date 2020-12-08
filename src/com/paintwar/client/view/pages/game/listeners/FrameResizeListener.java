package com.paintwar.client.view.pages.game.listeners;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

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
    	JFrame currentFrame = (JFrame) e.getComponent();
    	//System.out.println("resized " + currentFrame.getContentPane().getWidth() + " " + currentFrame.getContentPane().getHeight());
    	
    	gamePage.updatePage(currentFrame.getContentPane().getWidth(), currentFrame.getContentPane().getHeight());
    }   
}