package com.paintwar.server.service.account.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ILevel extends Remote
{
	public int getValue() throws RemoteException;

	public int getXP() throws RemoteException;

	public int getXPRequired() throws RemoteException;

	public void addXP(int xpAmount) throws RemoteException;
}
