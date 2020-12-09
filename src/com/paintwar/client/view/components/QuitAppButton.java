package com.paintwar.client.view.components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.Border;

import com.paintwar.client.view.MainWindow;

public class QuitAppButton extends JButton {

	private static final long serialVersionUID = -5498137514568853518L;
	private MainWindow manager;
	
	public QuitAppButton(MainWindow parent) {
		super("Quitter");
		manager = parent;
		setBorder(new RoundedBorder(10));
		setOpaque(false);
		addActionListener(new EndAppListener());
		/*
		 * Ici les modifs graphiques genre couleur etc
		 */
	}
	
	private class RoundedBorder implements Border
	{
		private int radius;

		public RoundedBorder(int radius)
		{
			this.radius = radius;
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
		{
			g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
		}

		@Override
		public Insets getBorderInsets(Component c)
		{
			return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
		}

		@Override
		public boolean isBorderOpaque()
		{
			return true;
		}
	}
	
	public class EndAppListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			manager.dispose();
			System.exit(0);
			
			
		}
		
	}
}
