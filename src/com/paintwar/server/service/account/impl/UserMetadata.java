package com.paintwar.server.service.account.impl;

import java.rmi.RemoteException;

import com.paintwar.server.service.account.interfaces.IGameHistory;
import com.paintwar.server.service.account.interfaces.ILevel;
import com.paintwar.server.service.account.interfaces.IUserMetadata;
import com.paintwar.server.service.account.interfaces.IUserStats;

public class UserMetadata implements IUserMetadata
{
	private String username;
	private String cryptedPassword;
	private byte[] salt;
	private ILevel level;
	private IUserStats stats;
	private IGameHistory history;

	public UserMetadata(String username, String cryptedPassword, byte[] salt, ILevel level, IUserStats stats,
			IGameHistory history)
	{
		this.username = username;
		this.cryptedPassword = cryptedPassword;
		this.salt = salt;
		this.level = level;
		this.stats = stats;
		this.history = history;
	}

	@Override
	public ILevel getLevel() throws RemoteException
	{
		return level;
	}

	@Override
	public IUserStats getStats() throws RemoteException
	{
		return stats;
	}

	@Override
	public String getCryptedPassword() throws RemoteException
	{
		return cryptedPassword;
	}

	@Override
	public byte[] getSalt() throws RemoteException
	{
		return salt;
	}

	@Override
	public String getIdentificationToken() throws RemoteException
	{
		return username;
	}

	@Override
	public void changeUsername(String newUsername) throws RemoteException
	{
		username = newUsername;
	}

	@Override
	public void changePassword(String cryptedPassword) throws RemoteException
	{
		this.cryptedPassword = cryptedPassword;
	}

	@Override
	public IGameHistory getGameHistory() throws RemoteException
	{
		return history;
	}

}
