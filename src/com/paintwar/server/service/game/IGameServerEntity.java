package com.paintwar.server.service.game;

import java.awt.Color;
import java.awt.Point;
import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

import com.paintwar.server.service.game.elements.Player;
import com.paintwar.server.service.game.elements.Team;

public interface IGameServerEntity extends Remote {
	
	int addPlayer (String clientIP, int clientPort) throws RemoteException ;
	void answer (String question) throws RemoteException ;
	int getRMIPort () throws RemoteException ;
	String addDrawingProxy (Point p1, Point p2, int formType, String clientID) throws RemoteException ;
	ArrayList <IDrawServerRemote> getDrawingProxies () throws RemoteException ;
	IDrawServerRemote getDrawing (String name) throws RemoteException ;
	void updateBoundsDrawing(String name, Point p1, Point p2, String clientIP) throws RemoteException;
	void deleteDrawing(String name) throws RemoteException ;
	void stopServer() throws RemoteException ;
	void startFillingDraw(String name) throws RemoteException;
	Color getTeamColor(String clientID) throws RemoteException;
	Map<Color, Team> getTeamData() throws RemoteException;
	boolean generateTeamZone(String clientID) throws RemoteException;
	Player getPlayerData(String name) throws RemoteException;
	int getUnicastPort() throws RemoteException;
	
}
