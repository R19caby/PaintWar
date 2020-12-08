package com.paintwar.client.view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.paintwar.client.view.MainWindow;

public class QuitAppButton extends JButton {

	private static final long serialVersionUID = -5498137514568853518L;
	private MainWindow manager;
	
	public QuitAppButton(MainWindow parent) {
		super("Quitter");
		manager = parent;
		addActionListener(new EndAppListener());
		/*
		 * Ici les modifs graphiques genre couleur etc
		 */
	}
	
	public class EndAppListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			manager.dispose();
			System.exit(0);
			
			
		}
		
	}
}
