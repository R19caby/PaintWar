package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

public class SetEnd extends JPanel{
	
	private JPanel statsDisplayerPanel;
	private static final String BALANCE_STATS = "Balance";
	private static final String PLAY_LEAD_STATS = "Player lead.";
	private static final String MAP_STATE_STATS = "Map State";
	private static final Dimension DIMENSION_STAT = new Dimension(1800, 100);
	private static final Dimension DIMENSION_TIMER = new Dimension(300, 100);
	private static final Dimension DIMENSION_LEADERBOARD = new Dimension(600, 200);
	private static final Dimension DIMENSION_RESULT = new Dimension(500, 200);
	private static final Dimension DIMENSION_STATS_DISPLAYER = new Dimension(1900, 700);
	private static final Dimension DIMENSION_TEAM_LEADERBOARD = new Dimension(150, 30);
	private static final Dimension DIMENSION_BUTTON_CHANGE_STAT_CATEGORY = new Dimension(250, 40);
	private static final Color COLOR_STAT = Color.LIGHT_GRAY;
	public SetEnd() {
		setLayout(new BorderLayout(10, 100));
		JPanel center = new JPanel();
		JPanel north = new JPanel();
		add(center, BorderLayout.CENTER);
		add(north, BorderLayout.NORTH);
		north.setLayout(new GridLayout(1, 3));
		center.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel timerPanelContainer = new JPanel();
		JPanel leaderboardPanel = new JPanel(new BorderLayout());
		leaderboardPanel.setPreferredSize(DIMENSION_LEADERBOARD);
		JPanel resultPanel = new JPanel();
		resultPanel.setPreferredSize(DIMENSION_RESULT);
		resultPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel categoryChoosePanel = new JPanel();
		statsDisplayerPanel = new JPanel(new CardLayout());
		categoryChoosePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setLeftComponent(categoryChoosePanel);
		splitPane.setRightComponent(statsDisplayerPanel);
		splitPane.setPreferredSize(DIMENSION_STATS_DISPLAYER);
		north.add(resultPanel);
		north.add(timerPanelContainer);
		north.add(leaderboardPanel);
		center.add(splitPane);
		
		JPanel balancePanel = new JPanel();
		JPanel playerLeadPanel = new JPanel();
		JPanel mapstatePanel = new JPanel();
		categoryChoosePanel.add(balancePanel);
		categoryChoosePanel.add(playerLeadPanel);
		categoryChoosePanel.add(mapstatePanel);
		ButtonCategoryStat balanceButton = new ButtonCategoryStat(BALANCE_STATS);
		ButtonCategoryStat playerLeadButton = new ButtonCategoryStat(PLAY_LEAD_STATS);
		ButtonCategoryStat mapstateButton = new ButtonCategoryStat(MAP_STATE_STATS);
		balancePanel.add(balanceButton);
		playerLeadPanel.add(playerLeadButton);
		mapstatePanel.add(mapstateButton);
		
		JPanel statsBalancePanel = buildBalancePanel();
		JPanel statsPlayerLeadPanel = buildPlayerLeadPanel();
		JPanel statsMapStatePanel = buildMapStatePanel();
		
		statsDisplayerPanel.add(statsBalancePanel, BALANCE_STATS);
		statsDisplayerPanel.add(statsPlayerLeadPanel, PLAY_LEAD_STATS);
		statsDisplayerPanel.add(statsMapStatePanel, MAP_STATE_STATS);
		
		JLabel timerLabel = new JLabel("Manche suivante dans 29s");
		JPanel timerPanel = new JPanel();
		timerPanel.setPreferredSize(DIMENSION_TIMER);
		timerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		timerPanel.add(timerLabel);
		timerPanelContainer.add(timerPanel);
		
		JLabel leaderboardLabel = new JLabel("Leaderboard");
		leaderboardLabel.setHorizontalAlignment(SwingConstants.LEFT);
		leaderboardLabel.setPreferredSize(new Dimension(600, 25));
		incrFontSize(10, leaderboardLabel);
		JPanel leaderboardLabelPanel = new JPanel();
		leaderboardLabelPanel.add(leaderboardLabel);
		
		JPanel teamsPanel = new JPanel(new GridLayout(4, 3));
		TeamLeaderboard team1Label = new TeamLeaderboard("1st - Red", Color.red);
		TeamLeaderboard team1ScoreLabel = new TeamLeaderboard("4", Color.red);
		TeamLeaderboard team2Label = new TeamLeaderboard("2nd - Blue", Color.blue);
		TeamLeaderboard team2ScoreLabel = new TeamLeaderboard("2", Color.blue);
		TeamLeaderboard team3Label = new TeamLeaderboard("3rd - Yellow", Color.orange);
		TeamLeaderboard team3ScoreLabel = new TeamLeaderboard("1", Color.orange);
		TeamLeaderboard team4Label = new TeamLeaderboard("4th - Green", Color.green);
		TeamLeaderboard team4ScoreLabel = new TeamLeaderboard("0", Color.green);
		JPanel team11Panel = new JPanel();
		team11Panel.add(team1Label);
		JPanel team12Panel = new JPanel(); //void
		JPanel team13Panel = new JPanel();
		team13Panel.add(team1ScoreLabel);
		JPanel team21Panel = new JPanel();
		team21Panel.add(team2Label);
		JPanel team22Panel = new JPanel(); //void
		JPanel team23Panel = new JPanel();
		team23Panel.add(team2ScoreLabel);
		JPanel team31Panel = new JPanel();
		team31Panel.add(team3Label);
		JPanel team32Panel = new JPanel(); //void
		JPanel team33Panel = new JPanel();
		team33Panel.add(team3ScoreLabel);
		JPanel team41Panel = new JPanel();
		team41Panel.add(team4Label);
		JPanel team42Panel = new JPanel(); //void
		JPanel team43Panel = new JPanel();
		team43Panel.add(team4ScoreLabel);
		
		teamsPanel.add(team11Panel);
		teamsPanel.add(team12Panel);
		teamsPanel.add(team13Panel);
		teamsPanel.add(team21Panel);
		teamsPanel.add(team22Panel);
		teamsPanel.add(team23Panel);
		teamsPanel.add(team31Panel);
		teamsPanel.add(team32Panel);
		teamsPanel.add(team33Panel);
		teamsPanel.add(team41Panel);
		teamsPanel.add(team42Panel);
		teamsPanel.add(team43Panel);
		
		leaderboardPanel.add(leaderboardLabelPanel, BorderLayout.NORTH);
		leaderboardPanel.add(teamsPanel, BorderLayout.CENTER);
		
		JLabel resultLabel = new JLabel("Raphaël a gagné !");
		incrFontSize(10, resultLabel);
		resultPanel.add(resultLabel);
		
	}
	
	private void incrFontSize(int deltaSize, Component component) {
		component.setFont(new Font(component.getFont().getName(),component.getFont().getSize(),component.getFont().getSize() + deltaSize));
	}
	
	private JPanel buildBalancePanel() {
		JPanel statsBalancePanel = new JPanel();
		statsBalancePanel.setLayout(new GridLayout(0, 1));
		JPanel stats1BalancePanel = new JPanel();
		JPanel stats2BalancePanel = new JPanel();
		JPanel stats3BalancePanel = new JPanel();
		JPanel stats4BalancePanel = new JPanel();
		JPanel stats5BalancePanel = new JPanel();
		statsBalancePanel.add(stats1BalancePanel);
		statsBalancePanel.add(stats2BalancePanel);
		statsBalancePanel.add(stats3BalancePanel);
		statsBalancePanel.add(stats4BalancePanel);
		statsBalancePanel.add(stats5BalancePanel);
		LabelStat stats1BalanceLabel = new LabelStat("Stats1Balance");
		LabelStat stats2BalanceLabel = new LabelStat("Stats2Balance");
		LabelStat stats3BalanceLabel = new LabelStat("Stats3Balance");
		LabelStat stats4BalanceLabel = new LabelStat("Stats4Balance");
		LabelStat stats5BalanceLabel = new LabelStat("Stats5Balance");
		stats1BalancePanel.add(stats1BalanceLabel);
		stats2BalancePanel.add(stats2BalanceLabel);
		stats3BalancePanel.add(stats3BalanceLabel);
		stats4BalancePanel.add(stats4BalanceLabel);
		stats5BalancePanel.add(stats5BalanceLabel);
		return statsBalancePanel;
	}
	
	private JPanel buildPlayerLeadPanel() {
		JPanel statsPlayerLeadPanel = new JPanel();
		
		statsPlayerLeadPanel.setLayout(new GridLayout(0, 1));
		JPanel stats1PlayerLeadPanel = new JPanel();
		JPanel stats2PlayerLeadPanel = new JPanel();
		JPanel stats3PlayerLeadPanel = new JPanel();
		JPanel stats4PlayerLeadPanel = new JPanel();
		JPanel stats5PlayerLeadPanel = new JPanel();
		statsPlayerLeadPanel.add(stats1PlayerLeadPanel);
		statsPlayerLeadPanel.add(stats2PlayerLeadPanel);
		statsPlayerLeadPanel.add(stats3PlayerLeadPanel);
		statsPlayerLeadPanel.add(stats4PlayerLeadPanel);
		statsPlayerLeadPanel.add(stats5PlayerLeadPanel);
		LabelStat stats1PlayerLeadLabel = new LabelStat("Stats1PlayerLead");
		LabelStat stats2PlayerLeadLabel = new LabelStat("Stats2PlayerLead");
		LabelStat stats3PlayerLeadLabel = new LabelStat("Stats3PlayerLead");
		LabelStat stats4PlayerLeadLabel = new LabelStat("Stats4PlayerLead");
		LabelStat stats5PlayerLeadLabel = new LabelStat("Stats5PlayerLead");
		stats1PlayerLeadPanel.add(stats1PlayerLeadLabel);
		stats2PlayerLeadPanel.add(stats2PlayerLeadLabel);
		stats3PlayerLeadPanel.add(stats3PlayerLeadLabel);
		stats4PlayerLeadPanel.add(stats4PlayerLeadLabel);
		stats5PlayerLeadPanel.add(stats5PlayerLeadLabel); 
		return statsPlayerLeadPanel;
	}
	
	private JPanel buildMapStatePanel() {
		JPanel statsMapStatePanel = new JPanel();
		statsMapStatePanel.setLayout(new GridLayout(0, 1));
		JPanel stats1MapStatePanel = new JPanel();
		JPanel stats2MapStatePanel = new JPanel();
		JPanel stats3MapStatePanel = new JPanel();
		JPanel stats4MapStatePanel = new JPanel();
		JPanel stats5MapStatePanel = new JPanel();
		statsMapStatePanel.add(stats1MapStatePanel);
		statsMapStatePanel.add(stats2MapStatePanel);
		statsMapStatePanel.add(stats3MapStatePanel);
		statsMapStatePanel.add(stats4MapStatePanel);
		statsMapStatePanel.add(stats5MapStatePanel);
		LabelStat stats1MapStateLabel = new LabelStat("Stats1MapState");
		LabelStat stats2MapStateLabel = new LabelStat("Stats2MapState");
		LabelStat stats3MapStateLabel = new LabelStat("Stats3MapState");
		LabelStat stats4MapStateLabel = new LabelStat("Stats4MapState");
		LabelStat stats5MapStateLabel = new LabelStat("Stats5MapState");
		stats1MapStatePanel.add(stats1MapStateLabel);
		stats2MapStatePanel.add(stats2MapStateLabel);
		stats3MapStatePanel.add(stats3MapStateLabel);
		stats4MapStatePanel.add(stats4MapStateLabel);
		stats5MapStatePanel.add(stats5MapStateLabel); 
		return statsMapStatePanel;
	}
	
	private class ButtonCategoryStat extends JButton {
		public ButtonCategoryStat(String text) {
			super(text);
			addActionListener(new ChangeStatsCategoryDisplayed());
			setPreferredSize(DIMENSION_BUTTON_CHANGE_STAT_CATEGORY);
		}
	}
	private class TeamLeaderboard extends JLabel {
		public TeamLeaderboard(String text, Color colorTeam) {
			super(text);
			setForeground(colorTeam);
			setOpaque(true);
			setHorizontalAlignment(SwingConstants.LEFT);
			setPreferredSize(DIMENSION_TEAM_LEADERBOARD);
			incrFontSize(4, this);
		}
	}
		
	
	private class LabelStat extends JLabel {
		public LabelStat(String text) {
			super(text);
			setBackground(COLOR_STAT);
			setOpaque(true);
			setBorder(BorderFactory.createLineBorder(Color.black));
			setHorizontalAlignment(SwingConstants.CENTER);
			setPreferredSize(DIMENSION_STAT);
		}
	}
	
	private class ChangeStatsCategoryDisplayed implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			String buttonText = button.getText();
			CardLayout cl = (CardLayout) statsDisplayerPanel.getLayout();
			cl.show(statsDisplayerPanel, buttonText);
			
		}
		
	}
}
