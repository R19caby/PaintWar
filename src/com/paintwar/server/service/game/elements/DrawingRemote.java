package com.paintwar.server.service.game.elements;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

import com.paintwar.server.logger.Logger;
import com.paintwar.server.service.game.IDrawServerRemote;
import com.paintwar.unicast.UnicastTransmitter;

public class DrawingRemote implements IDrawServerRemote, Serializable {

	// les attributs du proxy
	private int x1 ;
	private int y1 ;
	private int x2 ;
	private int y2 ;
	private String name ;
	private String playerCreatorID;
	private Color currentColor;
	private Double percent;

	private static final long serialVersionUID = 1L ;

	// constructeur du Dessin sur le serveur : il diffuse alors qu'il faut créer un nouveau dessin sur tous les clients 
	public DrawingRemote (String name, Color color, String player) {
		this.name = name ;
		this.currentColor = color;
		this.playerCreatorID = player;
	}

	public void setCompleted(Double percent) {
		this.percent = percent;
	}
	
	public String getName () {
		return name ;
	}

	public void setBounds (Point p1, Point p2) {
		this.x1 = p1.x;
		this.y1 = p1.y;
		this.x2 = p2.x;
		this.y2 = p2.y;
	}
	
	public void setColor (Color color) {
		this.currentColor = color;
	}

	@Override
	public int getX1() {
		return x1 ;
	}

	@Override
	public int getY1() {
		return y1 ;
	}

	@Override
	public int getX2() {
		return x2 ;
	}

	@Override
	public int getY2() {
		return y2 ;
	}

	@Override
	public Color getColor() {
		return currentColor;
	}

	@Override 
	public Double isCompleted() {
		return percent;
	}
	
	@Override
	public String getCreatorID() {
		return playerCreatorID;
	}
	
}
