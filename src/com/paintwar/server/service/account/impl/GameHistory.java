package com.paintwar.server.service.account.impl;

import java.util.List;

import com.paintwar.server.service.account.interfaces.IGameHistory;
import com.paintwar.server.service.account.interfaces.IGameRecap;

public class GameHistory implements IGameHistory
{
	private List<IGameRecap> gameRecaps;

	public GameHistory(List<IGameRecap> gameRecaps)
	{
		this.gameRecaps = gameRecaps;
	}
}
