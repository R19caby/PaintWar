package com.paintwar.server.service.account.impl;

import java.io.Serializable;
import java.rmi.RemoteException;

import com.paintwar.server.logger.Logger;
import com.paintwar.server.service.account.interfaces.IUser;
import com.paintwar.server.service.account.interfaces.IUserFriendlist;
import com.paintwar.server.service.account.interfaces.IUserInventory;
import com.paintwar.server.service.account.interfaces.IUserMetadata;

public class User implements IUser, Serializable
{
	private IUserMetadata metadata;
	private IUserInventory inventory;
	private IUserFriendlist friendlist;

	public User(IUserMetadata metadata, IUserInventory inventory, IUserFriendlist friendlist)
	{
		this.metadata = metadata;
		this.inventory = inventory;
		this.friendlist = friendlist;
	}

	@Override
	public void changeUsername(String newUsername) throws RemoteException
	{
		metadata.changeUsername(newUsername);
	}

	@Override
	public void changePassword(String cryptedPassword) throws RemoteException
	{
		metadata.changePassword(cryptedPassword);
	}

	@Override
	public IUserInventory getUserInventory() throws RemoteException
	{
		return inventory;
	}

	@Override
	public IUserMetadata getUserMetadata() throws RemoteException
	{
		return metadata;
	}

	@Override
	public IUserFriendlist getUserFriendList() throws RemoteException
	{
		return friendlist;
	}

	@Override
	public String toString()
	{
		String username = "";
		int level = 0;
		try
		{
			username = metadata.getUsername();
			level = metadata.getLevel().getValue();
		} catch (RemoteException e)
		{
			Logger.print(e);
		}

		return String.format("User {0}, level {1}.", username, level);
	}
}
