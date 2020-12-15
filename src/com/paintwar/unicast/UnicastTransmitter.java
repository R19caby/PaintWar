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

public class UnicastTransmitter
{

	private DatagramSocket transmissionSocket;
	private DatagramPacket clientPacket;
	private String clientIPID;

	public UnicastTransmitter(final DatagramSocket serverSocket, DatagramPacket clientPacket, String clientIPID)
	{
		this.clientIPID = clientIPID;
		Logger.print("Transmition to client : " + clientIPID);
		transmissionSocket = serverSocket;
		this.clientPacket = clientPacket;
	}

	public void diffuseMessage(String packageName, String command, String name, HashMap<String, Object> hm)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try
		{
			oos = new ObjectOutputStream(baos);
			oos.writeObject(packageName);
			oos.writeObject(command);
			oos.writeObject(name);
			oos.writeObject(hm);
			oos.flush();
		} catch (IOException e)
		{
			Logger.print(e);
		}
		
		clientPacket.setData(baos.toByteArray());
		
		try
		{
			transmissionSocket.send(clientPacket);
		} catch (IOException e)
		{
			Logger.print(e);
		}
	}

	public int getTransmissionPort() throws RemoteException
	{
		return transmissionSocket.getLocalPort();
	}
	
	public String getClientIPID() throws RemoteException
	{
		return clientIPID;
	}
}
