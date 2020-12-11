package com.paintwar.client.view.pages.game.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.paintwar.server.logger.Logger;

public class Drawing extends JPanel {
	
	private Color color;
	private Point initPoint;
	private Point endPoint;
	private Function<Double, Point> fillDraw;
	private Point fillP1;
	private Point fillP2;
	private Drawing fillingDraw;
	private int opacity;
	private Color displayColor = Color.black;
	
	Drawing(Color color, int opacity) {
		this.opacity = opacity;
		this.color = color;
		setOpaque(false);
		if (opacity != 255) {
			fillingDraw = new Drawing(color, 255);
			setLayout(null);
			add(fillingDraw);
		}
	}
	
	private void updateFillDraw() {
		int w = (int) (endPoint.getX() - initPoint.getX());
		int h = (int) (endPoint.getY() - initPoint.getY());
		if ((w > 0 && h > 0) || (w<0 && h>0 && Math.abs(w) < Math.abs(h)) || (w>0 && h<0 && Math.abs(w) > Math.abs(h))) {
			/*Expand either down or right*/
			if (Math.abs(w) > Math.abs(h)) {
				/*Expand right*/
				fillP1 = new Point(0, 0);
				fillP2 = new Point(0, Math.abs(h));
			} else {
				/*Expand down*/
				fillP1 = new Point(0, 0);
				fillP2 = new Point(Math.abs(w), 0);
			}
		} else {
			/*Expand either up or left*/
			if (Math.abs(w) > Math.abs(h)) {
				/*Expand left*/
				fillP1 = new Point(Math.abs(w), 0);
				fillP2 = new Point(Math.abs(w), Math.abs(h));
			} else {
				/*Expand up*/
				fillP1 = new Point(0, Math.abs(h));
				fillP2 = new Point(Math.abs(w), Math.abs(h));
			}
		}
		
		if (Math.abs(w) > Math.abs(h)) {
			/*Need to modify 2nd point x to fill*/
			fillDraw = (Double percent) -> {
				int newX = fillP1.x + (int) (percent*(Math.abs(w)-(2*fillP1.x)));
				int newY = fillP2.y;
				return new Point(newX, newY);
			};
		} else {
			/*need to modify y*/
			fillDraw = (Double percent) -> {
				int newX = fillP2.x ;
				int newY = fillP1.y + (int) (percent*(Math.abs(h)-(2*fillP1.y)));
				return new Point(newX, newY);
			};
		}
		setFilling(.0);
	}
	
	public Point getInitPoint() {
		return initPoint;
	}

	public void setInitPoint(Point initPoint) {
		this.initPoint = initPoint;
	}

	public void setDisplayColor(Color color) {
		if (color.equals(Color.red)) {
			this.setBorder(BorderFactory.createLineBorder(color, 1));
		} else {
			this.setBorder(BorderFactory.createLineBorder(color, 1));
		}
		this.displayColor = color;
	}
	
	@Override
	public void paint(Graphics g) {

        super.paint(g); //reset drawing panel

        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity));
    	g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
		if (opacity != 255)
			updateFillDraw();
	}
	
	public void setFilling(Double percent) {
		if (fillDraw == null) {
			updateFillDraw();
		}
		if (percent >= 1) {
			percent = 1.;
			//fillingDraw.setOpacity(100);
		}
		fillP2 = fillDraw.apply(percent);
		Rectangle r = new Rectangle(fillP1);
		r.add(fillP2);

		fillingDraw.setBounds(r);
		r.translate(Math.min(this.initPoint.x, this.endPoint.x), Math.min(this.initPoint.y, this.endPoint.y));
		/*if (percent > 0) {
			Logger.print("[Client/Draw] Current shadow bounds " + this.getBounds());
			Logger.print("[Client/Draw] filling to with " + r + " at " + percent);
		}*/
		this.repaint();
	}
	
	public void setDrawn() {
		this.opacity = 0;
		this.setBorder(null);
		this.repaint();
	}


}
