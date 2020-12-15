package com.paintwar.server.service.game;

import java.awt.Color;
import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDrawServerRemote extends Remote {
	
   String getName () throws RemoteException ;
   int getX1() throws RemoteException;
   int getY1() throws RemoteException;
   int getX2() throws RemoteException;
   int getY2() throws RemoteException;
   Color getColor () throws RemoteException ;
   Double isCompleted() throws RemoteException;
   String getCreatorID() throws RemoteException;

}
