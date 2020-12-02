package com.paintwar.client.view.pages.game;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import com.paintwar.client.controller.game.GameEntity;
import com.paintwar.client.view.pages.game.elements.DrawZone;
import com.paintwar.client.view.pages.game.elements.DrawZonePlaceholder;
import com.paintwar.client.view.pages.game.listeners.CameraMvtListener;

public class GamePage extends JPanel {
	
	public static int CAMERA_SENSIBILITY = 20;
	public static int CAMERA_HITBOX_WIDTH = 100;
	private GameEntity gameEnt;
	private DrawZonePlaceholder DZPlaceholder;
	
	public GamePage(GameEntity gameEnt) {
		super();
		this.gameEnt = gameEnt;
		
		DrawZone drawPanel = new DrawZone(gameEnt);
		setLayout(new BorderLayout());
		DZPlaceholder = new DrawZonePlaceholder(drawPanel);
		
		add(DZPlaceholder, BorderLayout.CENTER);
	}
	
	public void updatePage() {
		DZPlaceholder.updateFrame();
	}
}
