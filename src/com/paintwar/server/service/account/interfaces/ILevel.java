package com.paintwar.server.service.account.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Represents the level of an user. This class is used to store and manage xp.
 * 
 * @author jkerl
 *
 */
public interface ILevel extends Remote
{
	/**
	 * Gets the level of the user.
	 * 
	 * @return The value of the level.
	 * @throws RemoteException When the server can't be reached.
	 */
	public int getValue() throws RemoteException;

	/**
	 * Gets the actual amount of xp of the user.
	 * 
	 * @return The actual amount of xp.
	 * @throws RemoteException When the server can't be reached.
	 */
	public int getXP() throws RemoteException;

	/**
	 * Gets the xp required to level up.
	 * 
	 * @return The xp required.
	 * @throws RemoteException When the server can't be reached.
	 */
	public int getXPRequired() throws RemoteException;

	/**
	 * Add xp to the level. If the xp reaches the next threshold, the user levels
	 * up. If the user reaches the max level, then the xp is wasted and the user
	 * can't gain xp anymore.
	 * 
	 * @param xpAmount The xp amount to add.
	 * @throws RemoteException When the server can't be reached.
	 */
	public void addXP(int xpAmount) throws RemoteException;
}
