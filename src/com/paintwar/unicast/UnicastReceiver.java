package com.paintwar.unicast;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

import com.paintwar.client.logger.Logger;
import com.paintwar.client.model.communication.IClientUnicastReceiver;

public class UnicastReceiver extends Thread
{
	private transient DatagramSocket receptionSocket;
	private IClientUnicastReceiver localClient;

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

	public void setLocalClient(IClientUnicastReceiver localClient)
	{
		this.localClient = localClient;
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
			if (command.equals("aMethod"))
			{
				// localClient.callAMethod(name, (TypeA) hm.get("argumentA"), (TypeB)
				// hm.get("argumentB"));
			} else if (command.equals("anotherMethod"))
			{

			}
		}
	}
}
