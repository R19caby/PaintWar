package com.paintwar.server.service.account.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.paintwar.server.exception.AlreadyExistingUsernameException;
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
	 * This method creates a new User in the data base. It must also store the salt
	 * used to generate the crypted password, the server doesn't have access to is
	 * otherwise.
	 * 
	 * @param username        The new user's username.
	 * @param cryptedPassword The new user's crypted password.
	 * @param salt            The salt used to create the user's crypted password.
	 * @throws RemoteException                  When the server can't be reached.
	 * @throws AlreadyExistingUsernameException When an user with the same username
	 *                                          already exists
	 */
	public void createUser(String username, String cryptedPassword, byte[] salt)
			throws RemoteException, AlreadyExistingUsernameException;

	/**
	 * Deletes the specified user form the database.
	 * 
	 * @param username The user to delete.
	 * @throws RemoteException         When the server can't be reached.
	 * @throws NoSuchUsernameException When the username doesn't exist.
	 */
	public void deleteUser(String username) throws RemoteException, NoSuchUsernameException;

	/**
	 * Gets the desired user from the database.
	 * 
	 * @param username The user's username.
	 * @return The remote representation of the user.
	 * @throws RemoteException         When the server can't be reached.
	 * @throws NoSuchUsernameException When the username doesn't exit.
	 */
	public IUser getUser(String username) throws RemoteException, NoSuchUsernameException;

}
