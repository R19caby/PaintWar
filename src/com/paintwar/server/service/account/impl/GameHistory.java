package com.paintwar.server.service.account.impl;

import java.io.Serializable;
import java.util.List;

import com.paintwar.server.service.account.interfaces.IGameHistory;
import com.paintwar.server.service.account.interfaces.IGameRecap;

public class GameHistory implements IGameHistory, Serializable
{
	private List<IGameRecap> gameRecaps;

	public GameHistory(List<IGameRecap> gameRecaps)
	{
		this.gameRecaps = gameRecaps;
	}
}
