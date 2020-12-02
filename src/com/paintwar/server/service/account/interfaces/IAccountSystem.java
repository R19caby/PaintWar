package com.paintwar.server.service.account.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.paintwar.server.exception.NoSuchUsernameException;
import com.paintwar.server.exception.PasswordDoNotMatchException;

/**
 * The login system must be secure. In order to send a request to the server,
 * the user must be identified, or the request will be send as guest user.
 * 
 * @author jkerl
 *
 */
public interface IAccountSystem extends Remote
{
	/**
	 * The method used to login. This method is hack-proof because it uses a strong
	 * security system.
	 * <p>
	 * In order to encrypt a password, user needs to use a specific hashing
	 * algorithm with a randomly generated salt. The server then get the hashed
	 * password and compare its hash value with its own stored hashed password in
	 * its database. The salt is generated once and is stored in the database. User
	 * must request the salt before hashing the password.
	 * <p>
	 * More details here :
	 * <p>
	 * {@link https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/}
	 * <p>
	 * 
	 * The method returns an identification token that is later used by the client
	 * to send requests to the server.
	 * 
	 * @param username        The username
	 * @param cryptedPassword The crypted password.
	 * @return The identification token.
	 * 
	 * @throws RemoteException             When the server can't be reached.
	 * @throws NoSuchUsernameException     When the username doesn't exist.
	 * @throws PasswordDoNotMatchException When the crypted password does not match
	 *                                     user's password.
	 */
	public String login(String username, String cryptedPassword)
			throws RemoteException, NoSuchUsernameException, PasswordDoNotMatchException;

	/**
	 * Gets the salt used to encrypt a password. To ensure the server can compare
	 * the crypted password provided by the user and the one stored in the database,
	 * the request must provide the username, to male sure the salt is the same as
	 * the one used to generate the crypted password.
	 * <p>
	 * More details here :
	 * <p>
	 * {@link https://en.wikipedia.org/wiki/Salt_(cryptography)}
	 * 
	 * @param username The username.
	 * @return The salt used to encrypt the username's password.
	 * @throws RemoteException         When the server can't be reached.
	 * @throws NoSuchUsernameException When the username doesn't exist.
	 */
	public byte[] getSalt(String username) throws RemoteException, NoSuchUsernameException;

	/**
	 * This method creates a new User in the data base. It must also store the salt
	 * used to generate the crypted password, the server doesn't have access to is
	 * otherwise.
	 * 
	 * @param username        The new user's username.
	 * @param cryptedPassword The new user's crypted password.
	 * @param salt            The salt used to create the user's crypted password.
	 * @throws RemoteException When the server can't be reached.
	 */
	public void createUser(String username, String cryptedPassword, byte[] salt) throws RemoteException;

	/**
	 * Deletes the specified user form the database.
	 * 
	 * @param username The user to delete.
	 * @throws RemoteException         When the server can't be reached.
	 * @throws NoSuchUsernameException When the username doesn't exist.
	 */
	public void deleteUser(String username) throws RemoteException, NoSuchUsernameException;

	/**
	 * Changes the username to a new username.
	 * 
	 * @param oldUsername The old username.
	 * @param newUsername The new username.
	 * @throws RemoteException         When the server can't be reached.
	 * @throws NoSuchUsernameException When the username doesn't exist.
	 */
	public void changeUsername(String oldUsername, String newUsername) throws RemoteException, NoSuchUsernameException;

	/**
	 * Changes the user's password with a new password. The same salt as the
	 * previous password is being used to encrypt the new password.
	 * 
	 * @param username           The user's username.
	 * @param newCryptedPassword The new crypted password, using the same salt as
	 *                           the previous password.
	 * @throws RemoteException         When the server can't be reached.
	 * @throws NoSuchUsernameException When the username doesn't exist.
	 */
	public void changePassword(String username, String newCryptedPassword)
			throws RemoteException, NoSuchUsernameException;

}
