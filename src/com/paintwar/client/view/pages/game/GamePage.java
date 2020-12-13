package com.paintwar.client.view.pages.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.paintwar.client.controller.game.GameEntity;
import com.paintwar.client.view.pages.game.elements.DrawZone;
import com.paintwar.client.view.pages.game.elements.DrawZonePlaceholder;
import com.paintwar.client.view.pages.game.elements.GameLayerUI;
import com.paintwar.client.view.pages.game.elements.Minimap;
import com.paintwar.client.view.pages.game.elements.ScoreboardSimple;
import com.paintwar.client.view.pages.game.listeners.CameraMvtListener;

public class GamePage extends JLayeredPane {
	
	public static int CAMERA_SENSIBILITY = 20;
	public static int CAMERA_HITBOX_WIDTH = 60;
	private GameEntity gameEnt;
	private DrawZonePlaceholder DZPlaceholder;
	private GameLayerUI layerUI;
	private DrawZone drawZone;
	
	public GamePage(GameEntity gameEnt, List<Thread> threads) {
		super();
		this.gameEnt = gameEnt;
		
		this.layerUI = new GameLayerUI(gameEnt, threads);
		
		drawZone = new DrawZone(gameEnt, layerUI.getMinimap());
		setLayout(null);
		DZPlaceholder = new DrawZonePlaceholder(drawZone, layerUI.getMinimap(), threads);
		layerUI.setDZPlaceHolder(DZPlaceholder);
		
		add(DZPlaceholder, Integer.valueOf(1));
		add(layerUI, Integer.valueOf(2));
		
	}
	
	public void updatePage(int newFrameW, int newFrameH) {
		layerUI.setBounds(0, 0, newFrameW, newFrameH);
		DZPlaceholder.setBounds(0, 0, newFrameW, newFrameH);
		layerUI.validate();
		layerUI.repaint();
		
		DZPlaceholder.updateFrame(newFrameW, newFrameH);
		layerUI.getMinimap().updateCameraFrame(newFrameW, newFrameH);
	}

	public DrawZone getDrawZone() {
		return drawZone;
	}

	public void updateInk(double d, int maxInk) {
		layerUI.updateInk(d, maxInk);
	}
}
