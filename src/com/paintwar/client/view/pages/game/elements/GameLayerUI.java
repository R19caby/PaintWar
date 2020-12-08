package com.paintwar.client.view.pages.game.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GameLayerUI extends JPanel {
	
	private Minimap minimap;
	private DrawZonePlaceholder DZPlaceholder;

	public GameLayerUI() {
		JPanel rightPane = new JPanel();
		rightPane.setOpaque(false);
		
		JPanel centerPane = new JPanel();
		centerPane.setOpaque(false);
		centerPane.setBounds(this.getBounds());
		
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
	}
	
	public void setDZPlaceHolder(DrawZonePlaceholder DZPlaceholder) {
		this.DZPlaceholder = DZPlaceholder;
		minimap.setDZPlaceholder(DZPlaceholder);
	}
	
	public Minimap getMinimap() {
		return minimap;
	}
	
}
