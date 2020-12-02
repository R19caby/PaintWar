package com.paintwar.server.service.account.impl;

import java.util.List;

import com.paintwar.server.service.account.interfaces.IUser;
import com.paintwar.server.service.account.interfaces.IUserFriendlist;

public class UserFriendlist implements IUserFriendlist
{
	private List<IUser> friends;

	public UserFriendlist(List<IUser> friends)
	{
		this.friends = friends;
	}
}
