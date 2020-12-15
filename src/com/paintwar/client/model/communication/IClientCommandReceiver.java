package com.paintwar.client.model.communication;

import java.util.HashMap;

/**
 * Used to divide messages into more specific classes. This class must be
 * implemented in different packages. In this class' constructor, it must add
 * itself to the UnicastReceiver it depends on.
 * 
 * @author jkerl
 *
 */
public interface IClientCommandReceiver
{
	public boolean isCommandAvailable(String command);

	public void executeCommand(String command, String name, HashMap<String, Object> args);

	public String getOriginPackage();
}
