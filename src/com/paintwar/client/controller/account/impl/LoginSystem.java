package com.paintwar.client.controller.account.impl;

import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.paintwar.client.controller.account.interfaces.ILoginSystem;
import com.paintwar.client.logger.Logger;
import com.paintwar.server.exception.AlreadyExistingUsernameException;
import com.paintwar.server.exception.NoSuchUsernameException;
import com.paintwar.server.exception.PasswordDoNotMatchException;
import com.paintwar.server.service.account.interfaces.IAccountSystem;
import com.paintwar.server.service.account.interfaces.IUser;

public class LoginSystem implements ILoginSystem
{
	private final String cryptingAlgorithm = "SHA-256";

	private IAccountSystem remoteAccountSystem;
	private String loginToken;

	public LoginSystem(IAccountSystem as)
	{
		remoteAccountSystem = as;
		loginToken = null;
	}

	@Override
	public void login(String username, String password)
			throws RemoteException, NoSuchUsernameException, PasswordDoNotMatchException
	{
		IUser user = remoteAccountSystem.getUser(username);
		byte[] salt = user.getUserMetadata().getSalt();
		String cryptedPassword = cryptPassword(password, salt);

		loginToken = remoteAccountSystem.login(username, cryptedPassword);
	}

	@Override
	public void createUser(String username, String password) throws RemoteException, AlreadyExistingUsernameException
	{
		byte[] salt = generateSalt();
		String cryptedPassword = cryptPassword(password, salt);
		remoteAccountSystem.createUser(username, cryptedPassword, salt);
	}

	@Override
	public void deleteUser(String username) throws RemoteException, NoSuchUsernameException
	{
		remoteAccountSystem.deleteUser(username);
	}

	/**
	 * The salt is used to secure a crypted password. Without a salt, a hacker could
	 * intercept the crypted password and check with a database of crypted password
	 * if he can find a match. This way, he can decrypt the password. With a salt,
	 * he can no longer use a database because it made the crypting algorithm unique
	 * to the salt.
	 * 
	 * @return
	 */
	private byte[] generateSalt()
	{
		SecureRandom sr = null;
		try
		{
			sr = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e)
		{
			Logger.print(e);
		}
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	/**
	 * The method that can encrypt a password.
	 * <p>
	 * The algorithm used is SHA-256.
	 * <p>
	 * See documentation here :
	 * {@link https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples}
	 * 
	 * @param password
	 * @param salt
	 * @return
	 */
	private String cryptPassword(String password, byte[] salt)
	{
		String generatedPassword = null;
		try
		{
			MessageDigest md = MessageDigest.getInstance(cryptingAlgorithm);
			md.update(salt);
			byte[] bytes = md.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return generatedPassword;
	}

}
