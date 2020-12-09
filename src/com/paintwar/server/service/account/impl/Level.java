package com.paintwar.server.service.account.impl;

import java.io.Serializable;
import java.rmi.RemoteException;

import com.paintwar.server.service.account.interfaces.ILevel;

public class Level implements ILevel, Serializable
{
	private final static int[] XP_TABLE =
	{ 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private final static int MAX_LEVEL = 10;

	private int value;
	private int xp;

	public Level()
	{
		value = 1;
		xp = 0;
	}

	@Override
	public void addXP(int xpAmount) throws RemoteException
	{
		if (value != MAX_LEVEL)
		{
			xp += xpAmount;
			while (value < MAX_LEVEL && xp > XP_TABLE[value - 1])
			{
				xp -= XP_TABLE[value - 1];
				value++;
			}
			if (value == MAX_LEVEL)
			{
				xp = XP_TABLE[MAX_LEVEL - 1];
			}
		}
	}

	@Override
	public int getValue() throws RemoteException
	{
		return value;
	}

	@Override
	public int getXP() throws RemoteException
	{
		return xp;
	}

	@Override
	public int getXPRequired() throws RemoteException
	{
		return XP_TABLE[value - 1]; // levels start at 1
	}

}
