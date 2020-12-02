package com.paintwar.server.service.account.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import com.paintwar.server.exception.NoSuchUsernameException;
import com.paintwar.server.exception.PasswordDoNotMatchException;
import com.paintwar.server.service.account.interfaces.IAccountSystem;
import com.paintwar.server.service.account.interfaces.IGameRecap;
import com.paintwar.server.service.account.interfaces.IUser;
import com.paintwar.server.service.account.interfaces.IUserFriendlist;
import com.paintwar.server.service.account.interfaces.IUserInventory;
import com.paintwar.server.service.account.interfaces.IUserMetadata;

public class AccountSystem implements IAccountSystem
{
	private HashMap<String, IUser> users;

	public AccountSystem()
	{
		users = new HashMap<String, IUser>();

		// TODO get the users from disk
		// for(IUser user : disk)
		// {
		// users.put(user.getUsername(), user);
		// }
	}

	@Override
	public String login(String username, String cryptedPassword)
			throws RemoteException, NoSuchUsernameException, PasswordDoNotMatchException
	{
		IUserMetadata umd = users.get(username).getUserMetadata();
		if (umd.getCryptedPassword().equals(cryptedPassword))
		{
			return umd.getIdentificationToken();
		} else
		{
			throw new PasswordDoNotMatchException();
		}
	}

	@Override
	public byte[] getSalt(String username) throws RemoteException, NoSuchUsernameException
	{
		return users.get(username).getUserMetadata().getSalt();
	}

	@Override
	public void createUser(String username, String cryptedPassword, byte[] salt) throws RemoteException
	{
		IUserMetadata metadata = new UserMetadata(username, cryptedPassword, salt, new Level(), new UserStats(),
				new GameHistory(new ArrayList<IGameRecap>()));
		IUserInventory inventory = new UserInventory();
		IUserFriendlist friendlist = new UserFriendlist(new ArrayList<IUser>());

		IUser user = new User(metadata, inventory, friendlist);

		users.put(username, user);
	}

	@Override
	public void deleteUser(String username) throws RemoteException, NoSuchUsernameException
	{
		users.remove(username);
	}

	@Override
	public void changeUsername(String oldUsername, String newUsername) throws RemoteException, NoSuchUsernameException
	{
		IUser user = users.get(oldUsername);
		if (user != null)
		{
			user.changeUsername(newUsername);
		} else
		{
			throw new NoSuchUsernameException();
		}
	}

	@Override
	public void changePassword(String username, String newCryptedPassword)
			throws RemoteException, NoSuchUsernameException
	{
		IUser user = users.get(username);
		if (user != null)
		{
			user.changePassword(newCryptedPassword);
		} else
		{
			throw new NoSuchUsernameException();
		}
	}

}
