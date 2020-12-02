package com.paintwar.client.view.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonFactory extends JButton {
	
	private String cardName;
	public ButtonFactory(String name, String cardName) {
		super(name);
		addActionListener(new RedirectActionListener());
	}
	
	public class RedirectActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Changement dans le card layout de la Frame principale.
			// acc�s au cardName
		}
		
	}
}
