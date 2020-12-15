package com.paintwar.server.service.account.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * User Metadata are the username, the crypted password and this salt used to
 * crypt the password. It is responsible for the change of passwords and
 * usernames.
 * 
 * @author jkerl
 *
 */
public interface IUserMetadata extends Remote
{
	/**
	 * Gets the level of the user.
	 * 
	 * @return The level.
	 * @throws RemoteException When the server can't be reached.
	 */
	public ILevel getLevel() throws RemoteException;

	/**
	 * Gets the stats of the user.
	 * 
	 * @return The stats.
	 * @throws RemoteException When the server can't be reached.
	 */
	public IUserStats getStats() throws RemoteException;

	/**
	 * Gets the game history.
	 * 
	 * @return The game history.
	 * @throws RemoteException When the server can't be reached.
	 */
	public IGameHistory getGameHistory() throws RemoteException;

	/**
	 * Gets the stored crypted password.
	 * 
	 * @return The crypted password.
	 * @throws RemoteException When the server can't be reached.
	 */
	public String getCryptedPassword() throws RemoteException;

	/**
	 * Gets the salt used to encrypt a password. To ensure the server can compare
	 * the crypted password provided by the user and the one stored in the database,
	 * the request must provide the username, to make sure the salt is the same as
	 * the one used to generate the crypted password.
	 * <p>
	 * More details here :
	 * <p>
	 * {@link https://en.wikipedia.org/wiki/Salt_(cryptography)}
	 * 
	 * @return The salt used to encrypt the user's password.
	 * @throws RemoteException When the server can't be reached.
	 */
	public byte[] getSalt() throws RemoteException;

	/**
	 * Gets an unique identification token.
	 * 
	 * @return The identification linked to the user.
	 * @throws RemoteException When the server can't be reached.
	 */
	public String getIdentificationToken() throws RemoteException;

	/**
	 * Gets the username of the user.
	 * 
	 * @return The username linked to the user.
	 * @throws RemoteException When the server can't be reached.
	 */
	public String getUsername() throws RemoteException;

	/**
	 * Changes the username to a new username.
	 * 
	 * @param newUsername The new username.
	 * @throws RemoteException When the server can't be reached.
	 */
	public void changeUsername(String newUsername) throws RemoteException;

	/**
	 * Changes the user's password.
	 * <p>
	 * The salt used to encrypt the password remains the same.
	 * 
	 * @param cryptedPassword The new crypted password.
	 * @throws RemoteException When the server can't be reached.
	 */
	public void changePassword(String cryptedPassword) throws RemoteException;
}
