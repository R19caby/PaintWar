package com.paintwar.client.view.pages.game.elements;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.paintwar.client.controller.game.GameEntity;
import com.paintwar.server.logger.Logger;
import com.paintwar.server.service.game.GameConfig;

public class StatusBar extends JPanel {
	
	private static int BAR_WIDTH = 200;
	private static int BAR_HEIGHT = 10;
	private GameEntity gameEntity;
	private JLabel playerName;
	private JPanel fillInkBar;
	private JPanel inkBar;
	private JPanel emptyInkBar;
	private JLabel inkDisplay;
	private GridBagLayout barLayout;
	
	public StatusBar(GameEntity gameEntity) {
		super();
		
		this.gameEntity = gameEntity;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		playerName = new JLabel("Player");
		playerName.setForeground(gameEntity.getClientTeamColor());
		
		inkBar = new JPanel();
		barLayout = new GridBagLayout();
		inkBar.setLayout(barLayout);
		inkBar.setSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
		
		fillInkBar = new JPanel();
		fillInkBar.setBackground(gameEntity.getClientTeamColor());
		emptyInkBar = new JPanel();
		emptyInkBar.setOpaque(false);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
	    c.weighty = 0.5;
		c.gridx = 0;
		c.weightx = 0.;
		inkBar.add(fillInkBar, c);
		c.gridx = 1;
		c.weightx = 1.;
		inkBar.add(emptyInkBar, c);
		
		inkDisplay = new JLabel(GameConfig.MAX_INK_PLAYER + "/" + GameConfig.MAX_INK_PLAYER);
		
		playerName.setAlignmentX(Component.LEFT_ALIGNMENT);
		inkBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		inkDisplay.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(playerName);
		add(inkBar);
		add(inkDisplay);
		
		Dimension d = this.getPreferredSize();
		d.width = BAR_WIDTH;
		this.setPreferredSize(d);
	}
	
	public void updateInkBar(double ink, int maxInk) {

		Color color = gameEntity.getClientTeamColor();
		fillInkBar.setBackground(color);
		playerName.setForeground(color);
		
		if (ink == maxInk) {
			inkBar.remove(emptyInkBar);
		} else {
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridy = 0;
		    c.weighty = 0.5;
			c.gridx = 1;
			c.weightx = 1.;
			inkBar.add(emptyInkBar, c);
		}
		
		Logger.print("[Client/statusbar] updated ink to " + ink + "/" + maxInk);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
	    c.weighty = 0.5;
		c.gridx = 0;
		c.weightx = ink/maxInk;
		barLayout.setConstraints(fillInkBar, c);
		c.gridx = 1;
		c.weightx = 1-(ink/maxInk);
		barLayout.setConstraints(emptyInkBar, c);
		inkBar.revalidate();
		inkBar.repaint();
		
		inkDisplay.setText(((int) ink*10)/10.  + "/" + maxInk);
	}
	
}
