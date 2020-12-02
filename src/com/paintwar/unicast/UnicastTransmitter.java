package com.paintwar.unicast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.util.HashMap;

import com.paintwar.server.logger.Logger;

public class UnicastTransmitter implements Serializable
{
	private int transmissionPort;
	private InetAddress transmissionAddress;
	private transient DatagramSocket transmissionSocket;

	public UnicastTransmitter(final InetAddress targetAddress, final int transmissionPort) throws RemoteException
	{
		this.transmissionPort = transmissionPort;
		Logger.print("Transmition on port " + transmissionPort + " to client : " + targetAddress);
		transmissionAddress = targetAddress;
		transmissionSocket = null;

		try
		{
			transmissionSocket = new DatagramSocket();
		} catch (IOException e)
		{
			Logger.print(e);
		}

		Logger.print("Socket emission : " + transmissionSocket.getLocalPort() + " " + transmissionAddress);
	}

	public void diffuseMessage(String command, String name, HashMap<String, Object> hm)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try
		{
			oos = new ObjectOutputStream(baos);
			oos.writeObject(command);
			oos.writeObject(name);
			oos.writeObject(hm);
			oos.flush();
		} catch (IOException e)
		{
			Logger.print(e);
		}

		DatagramPacket packet = new DatagramPacket(baos.toByteArray(), baos.toByteArray().length, transmissionAddress,
				transmissionPort);

		try
		{
			transmissionSocket.send(packet);
		} catch (IOException e)
		{
			Logger.print(e);
		}
	}

	public int getTransmissionPort() throws RemoteException
	{
		return transmissionPort;
	}
}
