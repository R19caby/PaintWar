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

	/**
	 * Changes the user's username.
	 * 
	 * @param newUsername The new username.
	 * @throws RemoteException When the server can't be reached.
	 */
	public void changeUsername(String newUsername) throws RemoteException;

	/**
	 * Changes the password and holds the new crypted password. The salt used to
	 * encrypt the password remains the same as the old one.
	 * 
	 * @param cryptedPassword The new crypted password.
	 * @throws RemoteException When the server can't be reached.
	 */
	public void changePassword(String cryptedPassword) throws RemoteException;

	/**
	 * Gets the user's inventory.
	 * 
	 * @return The inventory.
	 * @throws RemoteException When the server can't be reached.
	 */
	public IUserInventory getUserInventory() throws RemoteException;

	/**
	 * Gets the user's metadata. Metadata represent sensitive data, such as the
	 * username, the crypted password and the salt used to encrypt the password. It
	 * may also hold personal informations such as the email.
	 * 
	 * @return The user's metadata.
	 * @throws RemoteException When the server can't be reached.
	 */
	public IUserMetadata getUserMetadata() throws RemoteException;

	/**
	 * Gets the user's friendlist.
	 * 
	 * @return The friendlist.
	 * @throws RemoteException When the server can't be reached.
	 */
	public IUserFriendlist getUserFriendList() throws RemoteException;
}
