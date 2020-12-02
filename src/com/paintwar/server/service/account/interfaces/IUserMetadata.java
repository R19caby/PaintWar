package com.paintwar.server.service.account.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUserMetadata extends Remote
{
	public ILevel getLevel() throws RemoteException;

	public IUserStats getStats() throws RemoteException;

	public IGameHistory getGameHistory() throws RemoteException;

	public String getCryptedPassword() throws RemoteException;

	public byte[] getSalt() throws RemoteException;

	public String getIdentificationToken() throws RemoteException;

	public void changeUsername(String newUsername) throws RemoteException;

	public void changePassword(String cryptedPassword) throws RemoteException;
}
