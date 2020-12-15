package com.paintwar.client.controller.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import com.paintwar.client.logger.Logger;
import com.paintwar.client.model.communication.gameio.GameIOReceiver;
import com.paintwar.client.view.pages.game.GamePage;
import com.paintwar.client.view.pages.game.elements.DrawZone;
import com.paintwar.client.view.pages.game.listeners.FrameResizeListener;
import com.paintwar.server.service.game.IDrawServerRemote;
import com.paintwar.server.service.game.elements.DrawingRemote;
import com.paintwar.server.service.game.elements.Team;


public class GameEntity implements IGameEntity {

	private DrawZone drawZone;
	private GameIOReceiver ioClient;
	private HashMap<String, DrawingProxy> drawings;
	private List<Thread> threads;
	private JFrame currentWindow;
	private GamePage gamepage;
	private Color clientTeamColor;
	private Map<Color, Team> teamData;
	private double clientInk;
	private int clientMaxInk;
	
	GameEntity() {
		drawings = new HashMap<String, DrawingProxy>();
		threads = new ArrayList<Thread>();
		currentWindow = new JFrame();
		gamepage = new GamePage(this, threads);
		this.drawZone = gamepage.getDrawZone();
		
		currentWindow.addComponentListener(new FrameResizeListener(gamepage));
		currentWindow.getContentPane().add(gamepage, BorderLayout.CENTER);
	}
	
	public void setIoClient(GameIOReceiver ioClient) {
		this.ioClient = ioClient;
	}

	public void setClientTeamColor(Color clientTeamColor) {
		this.clientTeamColor = clientTeamColor;
	}
	
	public Color getClientTeamColor() {
		return clientTeamColor;
	}
	
	private void deleteAllDrawings() {
		drawings.clear();
	}
	
	@Override
	public void startGame() {
		this.deleteAllDrawings();
	}

	@Override 
	public String paintClient(Point p1, Point p2, Color color) {
		//create drawing on server and retrieve draw name 
		String drawName = ioClient.createDrawing(p1, p2, color, DrawingProxy.RECTANGLE);
		Logger.print("[Game] Putting drawing named " + drawName);
		return drawName;
	}
	
	@Override
	public DrawingProxy addDrawing(String name, Point p1, Point p2, Color c, Double percent) {
		//create drawing
		DrawingProxy drawing = new DrawingProxy(p1, p2, c, DrawingProxy.RECTANGLE);
		
		if (percent != null) {
			drawing.setCompletion(percent);
			drawing.setDrawn(true);
		}
		
		//add to the list
		drawings.put(name, drawing);
		
		//update zone
		drawZone.addDrawing(name, p1, p2, c, percent);
		
		return drawing;
	}
	
	public void removeDrawingClient(String name) {
		ioClient.deleteDrawingRequest(name);
	}
	
	@Override
	public void removeDrawing(String name) {
		drawZone.deleteDrawing(name);
		drawings.remove(name);
	}
	
	@Override
	public void updateCoordPaint(String name, Point p1, Point p2) {
		drawZone.updateDraw(name, p1, p2);
	}
	
	@Override
	public void updateCoordPaint(String name, Point p) {
		drawZone.updateEndPointDraw(name, p);
		DrawingProxy drawToChange = drawings.get(name);
	}
	
	@Override
	public void updateCoordPaintClient(String name, Point p) {
		DrawingProxy drawToChange = drawings.get(name);
		if (drawToChange != null) {
			ioClient.updateBoundsrequest(name, drawToChange.getCoord(), p);
		} else {
			Logger.print("[Game] Couldn't find drawing proxy to change coord");
		}
	}

	public void startFilling(String name) {
		ioClient.startFilling(name);
	}
	
	public void updateFilling(String name, Double percent) {
		drawZone.updateFilling(name, percent);
	}
	
	public void setDrawn(String name) {
		DrawingProxy drawToChange = drawings.get(name);
		if (drawToChange != null) {
			drawToChange.setDrawn(true);
			drawZone.setDrawn(name);
		} else {
			Logger.print("[Game] Couldn't find drawing proxy to set it to drawn");
		}
	}
	
	public boolean hasDrawing(String name) {
		return drawings.containsKey(name);
	}
	
	public void openGame() {
		currentWindow.setVisible(true);
		currentWindow.setSize(1200, 600);
		gamepage.updatePage(1200, 600);
	}
	
	public void closeGame() {
		//close window
		currentWindow.setVisible(false);
		currentWindow.dispose();
		currentWindow = null;
		
		//stop all threads
		for (Thread thread : threads) {
			thread.interrupt();
		}
	}

	public Map<Color, Team> getTeamData() {
		if (ioClient != null) {
			teamData = ioClient.getTeamData();
		}
		return teamData;
	}

	public void updateInk(double d, int maxInk) {
		this.clientInk = d;
		this.clientMaxInk = maxInk;
		gamepage.updateInk(d, maxInk);
		drawZone.updateInkLimit();
	}

	public double getClientInk() {
		return clientInk;
	}

	public void changeName(String drawName, String realName) {
		DrawingProxy draw = drawings.remove(drawName);
		drawings.put(realName, draw);
		drawZone.changeName(drawName, realName);
	}


}
