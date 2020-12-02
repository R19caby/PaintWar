package com.paintwar.server.service.account.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This class holds all the data relative to an user. It defines methods to
 * access the data from a local client.
 * 
 * @author jkerl
 *
 */
public interface IUser extends Remote
{

	public void changeUsername(String newUsername) throws RemoteException;

	/**
	 * Changes the password and holds the new crypted password. The salt used to
	 * encrypt the password remains the same as the old one.
	 * 
	 * @param cryptedPassword The new crypted password.
	 * @throws RemoteException When the server can't be reached.
	 */
	public void changePassword(String cryptedPassword) throws RemoteException;

	public IUserInventory getUserInventory() throws RemoteException;

	public IUserMetadata getUserMetadata() throws RemoteException;

	public IUserFriendlist getUserFriendList() throws RemoteException;
}
