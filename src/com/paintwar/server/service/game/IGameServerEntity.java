package com.paintwar.server.service.game;

import java.awt.Color;
import java.awt.Point;
import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IGameServerEntity extends Remote {
	
	int getPortEmission (String clientIP, InetAddress clientAdress) throws RemoteException ;
	void answer (String question) throws RemoteException ;
	int getRMIPort () throws RemoteException ;
	IDrawServerRemote addDrawingProxy (Point p1, Point p2, int formType, Color color) throws RemoteException ;
	ArrayList <IDrawServerRemote> getDrawingProxies () throws RemoteException ;
	IDrawServerRemote getDrawing (String name) throws RemoteException ;
	void updateBoundsDrawing(String name, Point p1, Point p2, String clientIP) throws RemoteException;
	void deleteDrawing(String name) throws RemoteException ;
	void stopServer() throws RemoteException ;
	void startFillingDraw(String name) throws RemoteException;
	
}
