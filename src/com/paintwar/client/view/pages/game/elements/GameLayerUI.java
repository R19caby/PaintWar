package com.paintwar.client.view.pages.game.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.paintwar.client.controller.game.GameEntity;
import com.paintwar.client.view.pages.game.threads.GameDataRetreiver;

public class GameLayerUI extends JPanel {
	
	private Minimap minimap;
	private DrawZonePlaceholder DZPlaceholder;
	private List<Thread> threads;
	private GameEntity gameEnt;

	public GameLayerUI(GameEntity gameEnt, List<Thread> threads) {
		this.gameEnt = gameEnt;
		this.threads = threads;
		
		JPanel rightPane = new JPanel();
		rightPane.setOpaque(false);
		
		JPanel centerPane = new JPanel();
		centerPane.setOpaque(false);
		centerPane.setBounds(this.getBounds());
		
		JPanel topPane = new JPanel();
		topPane.setOpaque(false);
		
		ScoreboardSimple scoreboard = new ScoreboardSimple(gameEnt, threads);
		topPane.add(scoreboard);
		
		this.minimap = new Minimap(1.0, 3000, 100);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		rightPane.add(minimap, c);
		
		setLayout(new BorderLayout());
		setOpaque(false);
		add(rightPane, BorderLayout.LINE_END);
		add(centerPane, BorderLayout.CENTER);
		add(topPane, BorderLayout.NORTH);
	}
	
	public void setDZPlaceHolder(DrawZonePlaceholder DZPlaceholder) {
		this.DZPlaceholder = DZPlaceholder;
		minimap.setDZPlaceholder(DZPlaceholder);
	}
	
	public Minimap getMinimap() {
		return minimap;
	}
	
}
