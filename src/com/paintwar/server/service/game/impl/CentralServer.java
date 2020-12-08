package com.paintwar.server.service.game.impl;

import java.io.Serializable;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import com.paintwar.server.logger.Logger;
import com.paintwar.server.service.game.interfaces.ICentralServer;
import com.paintwar.unicast.UnicastTransmitter;

public class CentralServer extends UnicastRemoteObject implements ICentralServer, Serializable
{
	protected String serverName;
	protected int portRMI;
	protected String serverNameRMI;
	protected int transmissionPort;
	protected List<UnicastTransmitter> transmitters;

	public CentralServer(final String serverName, final String serverNameRMI, final int portRMI,
			final int transmissionPortUpdates) throws RemoteException
	{
		this.serverName = serverName;
		this.serverNameRMI = serverNameRMI;
		this.portRMI = portRMI;
		this.transmissionPort = transmissionPortUpdates;
		transmitters = new ArrayList<UnicastTransmitter>();

		try
		{
			Naming.rebind("//" + serverNameRMI + ":" + portRMI + "/" + serverName, this);
			Logger.print("Server ready");
		} catch (Exception e)
		{
			Logger.print("Problem with RMICentralManager");
		}
	}

	@Override
	public int getTransmissionPort(InetAddress clientAddress) throws RemoteException
	{
		UnicastTransmitter transmitter = new UnicastTransmitter(clientAddress, transmissionPort++);
		transmitters.add(transmitter);
		return transmitter.getTransmissionPort();
	}

	@Override
	public int getRMIPort() throws RemoteException
	{
		return portRMI;
	}
}
