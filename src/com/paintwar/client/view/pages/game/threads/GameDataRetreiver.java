package com.paintwar.client.view.pages.game.threads;

import java.awt.Color;
import java.util.Map;

import com.paintwar.client.controller.game.GameEntity;
import com.paintwar.client.view.pages.game.elements.ScoreboardSimple;
import com.paintwar.server.logger.Logger;

public class GameDataRetreiver extends Thread {
	
	private volatile boolean running = true;
	private GameEntity gameEntity;
	private Map<Color, Integer> teamScores;
	private ScoreboardSimple scoreboardPane;
	
	public GameDataRetreiver(GameEntity gameEntity, ScoreboardSimple scoreboardPane) {
		super();
		this.gameEntity = gameEntity;
		this.scoreboardPane = scoreboardPane;
	}
	
	public void run() {
		while(running) {
			teamScores = gameEntity.getTeamScores();
			if (teamScores != null) {
				scoreboardPane.updateTeamScores(teamScores);
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
