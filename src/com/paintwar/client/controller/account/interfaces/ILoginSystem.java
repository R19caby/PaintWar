package com.paintwar.client.controller.account.interfaces;

import java.rmi.RemoteException;

import com.paintwar.server.exception.AlreadyExistingUsernameException;
import com.paintwar.server.exception.NoSuchUsernameException;
import com.paintwar.server.exception.PasswordDoNotMatchException;

public interface ILoginSystem
{
	public void login(String username, String password)
			throws RemoteException, NoSuchUsernameException, PasswordDoNotMatchException;

	public void createUser(String username, String password) throws RemoteException, AlreadyExistingUsernameException;

	public void deleteUser(String username) throws RemoteException, NoSuchUsernameException;
}
