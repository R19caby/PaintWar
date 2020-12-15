package com.paintwar.server.service.game;

import java.awt.Color;
import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDrawServerRemote {
	
   String getName ();
   int getX1();
   int getY1();
   int getX2();
   int getY2();
   Color getColor ();
   Double isCompleted();
   String getCreatorID();

}
