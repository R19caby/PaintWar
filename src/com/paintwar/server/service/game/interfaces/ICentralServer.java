package com.paintwar.server.service.game.interfaces;

import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Used to access the remote server and access every other virtual servers from
 * here
 * 
 * @author jkerl
 *
 */
public interface ICentralServer extends Remote
{
	public int getTransmissionPort(InetAddress clientAddress) throws RemoteException;

	public int getRMIPort() throws RemoteException;

	// public IAccountSystem getAccountSystem() throws RemoteException;

}
