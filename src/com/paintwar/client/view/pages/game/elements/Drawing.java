package com.paintwar.client.view.pages.game.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class Drawing extends JPanel {
	
	private Color color;
	private Point initPoint;
	private Point endPoint;
	
	Drawing(Color color) {
		this.color = color;
		setOpaque(false);
	}
	
	public Point getInitPoint() {
		return initPoint;
	}

	public void setInitPoint(Point initPoint) {
		this.initPoint = initPoint;
	}

	@Override
	public void paint(Graphics g) {

        super.paint(g); //reset drawing panel

        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 50));
    	g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

}
