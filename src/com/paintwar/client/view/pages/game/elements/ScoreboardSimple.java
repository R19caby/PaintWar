package com.paintwar.client.view.pages.game.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.paintwar.client.controller.game.GameEntity;
import com.paintwar.client.view.pages.game.threads.GameDataRetreiver;
import com.paintwar.server.logger.Logger;
import com.paintwar.server.service.game.elements.Team;

public class ScoreboardSimple extends JPanel {
	
	private static int BAR_WIDTH = 200;
	private static int BAR_HEIGHT = 10;
	private GameDataRetreiver gameRetreiver;
	private JPanel scoreBar;
	private JPanel leadingTeamPane;
	private Map<Color, Team> teamScores = new HashMap<Color, Team>();
	
	public ScoreboardSimple(GameEntity gameEnt, List<Thread> threads) {
		super();
		this.gameRetreiver = new GameDataRetreiver(gameEnt, this);
		this.gameRetreiver.start();
		threads.add(gameRetreiver);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		leadingTeamPane = new JPanel();
		leadingTeamPane.add(new JLabel("No team leading."));
		scoreBar = new JPanel();
		scoreBar.setLayout(new GridBagLayout());
		scoreBar.setBackground(Color.gray);
		scoreBar.setSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
		JPanel greyBar = new JPanel();
		greyBar.setBackground(Color.gray);
		scoreBar.add(greyBar);
		
		add(leadingTeamPane);
		add(scoreBar);
		
		Dimension d = this.getPreferredSize();
		d.width = 200;
		this.setPreferredSize(d);
	}

	public void updateTeamScores(Map<Color, Team> teamData) {
		this.teamScores = teamData;
		Integer totalScore = 0;
		Team leadingTeam = null;
		Integer maxScore = 0;
		
		//generate all panels for bar
		List<JPanel> colorBars = new ArrayList<JPanel>();
		for (Entry<Color, Team> team : teamData.entrySet()) {
			Team currentTeam = team.getValue();
			Integer teamScore = currentTeam.getScore();
			if (teamScore >= maxScore) {
				leadingTeam = currentTeam;
				maxScore = teamScore;
			}
			totalScore += teamScore;
			JPanel currentTeamBar = new JPanel();
			currentTeamBar.setBackground(team.getKey());
			colorBars.add(currentTeamBar);
		}
		
		//update scoreboard panel
		scoreBar.removeAll();
		if (totalScore > 0) {

			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridy = 0;
		    c.weighty = 0.5;
			for (int i=0; i<colorBars.size(); i++) {
				JPanel bar = colorBars.get(i);
				double teamScore = teamData.get(bar.getBackground()).getScore();
				if (teamScore > 0) {
					Double widthBar = teamScore/totalScore;
					c.gridx = i;
					c.weightx = widthBar;
					scoreBar.add(bar, c);
				}
			}
			
			JLabel leadingTeamLabel = new JLabel(leadingTeam.getName());
			leadingTeamLabel.setForeground(leadingTeam.getColor());
			JLabel isLeading = new JLabel("is leading !");
			leadingTeamPane.removeAll();
			leadingTeamPane.add(leadingTeamLabel);
			leadingTeamPane.add(isLeading);
		} else {
			JLabel leadingTeamLabel = new JLabel("No team leading.");
			leadingTeamPane.removeAll();
			leadingTeamPane.add(leadingTeamLabel);
			JPanel greyBar = new JPanel();
			greyBar.setBackground(Color.gray);
			greyBar.setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
			scoreBar.add(greyBar);
		}

		scoreBar.repaint();
		scoreBar.revalidate();
		
	}
	
}
