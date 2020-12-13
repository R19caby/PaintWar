package com.paintwar.client.view.pages.game.threads;

import java.awt.Color;
import java.util.Map;

import com.paintwar.client.controller.game.GameEntity;
import com.paintwar.client.view.pages.game.elements.ScoreboardSimple;
import com.paintwar.server.logger.Logger;
import com.paintwar.server.service.game.elements.Team;

public class GameDataRetreiver extends Thread {
	
	private volatile boolean running = true;
	private GameEntity gameEntity;
	private Map<Color, Team> teamData;
	private ScoreboardSimple scoreboardPane;
	
	public GameDataRetreiver(GameEntity gameEntity, ScoreboardSimple scoreboardPane) {
		super();
		this.gameEntity = gameEntity;
		this.scoreboardPane = scoreboardPane;
	}
	
	public void run() {
		while(running) {
			teamData = gameEntity.getTeamData();
			if (teamData != null) {
				scoreboardPane.updateTeamScores(teamData);
			}
			
			try {
				sleep(500);
			} catch (InterruptedException e) {
				running = false;
				Logger.print("[Client/GameDataRetrieverLoop] Interrupted");
				e.printStackTrace();
			}
		}
	}
	
	
}
