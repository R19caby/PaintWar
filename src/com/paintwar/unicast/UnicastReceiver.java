package com.paintwar.unicast;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;

import com.paintwar.client.logger.Logger;
import com.paintwar.client.model.communication.IClientCommandReceiver;

public class UnicastReceiver extends Thread
{
	private transient DatagramSocket receptionSocket;

	private List<IClientCommandReceiver> commandReceivers;

	private String packageName; // the name of the package responsible for the command
	private String command; // the name of the method to use on local client
	private String name; // the name of the object to apply the command on
	private HashMap<String, Object> hm; // the arguments of the method

	public UnicastReceiver(final InetAddress receptionAddress, final int receptionPort)
	{
		receptionSocket = null;
		command = new String();
		name = new String();
		hm = new HashMap<String, Object>();

		try
		{
			receptionSocket = new DatagramSocket(receptionPort, receptionAddress);
			Logger.print(
					"reception socket : " + receptionSocket.getLocalPort() + " " + receptionSocket.getInetAddress());

		} catch (Exception e)
		{
			Logger.print(e);
		}
	}

	public void receive()
	{
		try
		{
			byte[] message = new byte[1024];
			DatagramPacket packet = new DatagramPacket(message, message.length);
			receptionSocket.receive(packet);
			ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
			ObjectInputStream ois = new ObjectInputStream(bais);
			packageName = (String) ois.readObject();
			command = (String) ois.readObject();
			name = (String) ois.readObject();
			hm = (HashMap<String, Object>) ois.readObject();
		} catch (Exception e)
		{
			Logger.print(e);
		}
	}

	@Override
	public void run()
	{
		while (true)
		{
			receive();

			// treat all different message here -->
			for (IClientCommandReceiver ccm : commandReceivers)
			{
				if (ccm.getPackageName().equals(packageName))
				{
					ccm.executeCommand(command, name, hm);
				}
			}
		}
	}

	public void addClientCommandReceiver(IClientCommandReceiver ccm)
	{
		commandReceivers.add(ccm);
	}
}
