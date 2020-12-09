package com.paintwar.server.service.account.impl;

import java.io.Serializable;
import java.util.List;

import com.paintwar.server.service.account.interfaces.IUser;
import com.paintwar.server.service.account.interfaces.IUserFriendlist;

public class UserFriendlist implements IUserFriendlist, Serializable
{
	private List<IUser> friends;

	public UserFriendlist(List<IUser> friends)
	{
		this.friends = friends;
	}
}
