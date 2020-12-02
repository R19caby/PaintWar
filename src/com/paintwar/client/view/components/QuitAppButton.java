package com.paintwar.client.view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class QuitAppButton extends JButton {

	private static final long serialVersionUID = -5498137514568853518L;
	public QuitAppButton() {
		super("Quitter");
		/*
		 * Ici les modifs graphiques genre couleur etc
		 */
	}
	
	public class EndAppListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
		
	}
}
