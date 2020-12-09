package com.paintwar.server.service.game;

import java.awt.Color;
import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDrawServerProxy extends Remote {

   void setBounds (int x, int y, int w, int h) throws RemoteException ;
   void setBounds(Point p1, Point p2) throws RemoteException ;
   void setColor (Color color) throws RemoteException ;
   String getName () throws RemoteException ;
   int getX () throws RemoteException ;
   int getY () throws RemoteException ;
   int getWidth () throws RemoteException ;
   int getHeight () throws RemoteException ;
   Color getColor () throws RemoteException ;
   void startFilling() throws RemoteException ;
   void stopFilling() throws RemoteException ;


}
