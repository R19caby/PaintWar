package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SetEnd extends JPanel{
	public SetEnd() {
		setLayout(new BorderLayout());
		JPanel center = new JPanel();
		JPanel north = new JPanel();
		add(center, BorderLayout.CENTER);
		add(north, BorderLayout.NORTH);
		
		JPanel timerPanel = new JPanel();
		JPanel leaderboardPanel = new JPanel();
		JPanel resultPanel = new JPanel();
		JPanel categoryChoosePanel = new JPanel();
		JPanel statsDisplayerPanel = new JPanel();
		categoryChoosePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		statsDisplayerPanel.setLayout(new CardLayout());
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setLeftComponent(categoryChoosePanel);
		splitPane.setRightComponent(statsDisplayerPanel);
		north.add(resultPanel);
		north.add(timerPanel);
		north.add(leaderboardPanel);
		center.add(splitPane);
		
		JPanel balancePanel = new JPanel();
		JPanel playerLeadPanel = new JPanel();
		JPanel mapstatePanel = new JPanel();
		categoryChoosePanel.add(balancePanel);
		categoryChoosePanel.add(playerLeadPanel);
		categoryChoosePanel.add(mapstatePanel);
		JButton balanceButton = new JButton("Balance");
		JButton playerLeadButton = new JButton("Player lead.");
		JButton mapstateButton = new JButton("Map State");
		balancePanel.add(balanceButton);
		playerLeadPanel.add(playerLeadButton);
		mapstatePanel.add(mapstateButton);
		
		
		JPanel statsBalancePanel = buildBalancePanel();
		JPanel statsPlayerLeadPanel = buildPlayerLeadPanel();
		JPanel statsMapStatePanel = buildMapStatePanel();
		
		statsDisplayerPanel.add(statsBalancePanel, "Balance");
		statsDisplayerPanel.add(statsPlayerLeadPanel, "PlayerLead");
		statsDisplayerPanel.add(statsMapStatePanel, "MapState");
		
		JLabel timerLabel = new JLabel("Timer : 3 semaines avant les vacances");
		timerPanel.add(timerLabel);
		
		JLabel leaderboardLabel = new JLabel("Leaderboard");
		leaderboardPanel.add(leaderboardLabel);
		
		JLabel resultLabel = new JLabel("Raphaël a gagné !");
		resultPanel.add(resultLabel);
		
	}
	
	private JPanel buildBalancePanel() {
		JPanel statsBalancePanel = new JPanel();
		statsBalancePanel.setLayout(new BoxLayout(statsBalancePanel, BoxLayout.Y_AXIS));
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
		JLabel stats1BalanceLabel = new JLabel("Stats1Balance");
		JLabel stats2BalanceLabel = new JLabel("Stats2Balance");
		JLabel stats3BalanceLabel = new JLabel("Stats3Balance");
		JLabel stats4BalanceLabel = new JLabel("Stats4Balance");
		JLabel stats5BalanceLabel = new JLabel("Stats5Balance");
		stats1BalancePanel.add(stats1BalanceLabel);
		stats2BalancePanel.add(stats2BalanceLabel);
		stats3BalancePanel.add(stats3BalanceLabel);
		stats4BalancePanel.add(stats4BalanceLabel);
		stats5BalancePanel.add(stats5BalanceLabel);
		return statsBalancePanel;
	}
	
	private JPanel buildPlayerLeadPanel() {
		JPanel statsPlayerLeadPanel = new JPanel();
		statsPlayerLeadPanel.setLayout(new BoxLayout(statsPlayerLeadPanel, BoxLayout.Y_AXIS));
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
		JLabel stats1PlayerLeadLabel = new JLabel("Stats1PlayerLead");
		JLabel stats2PlayerLeadLabel = new JLabel("Stats2PlayerLead");
		JLabel stats3PlayerLeadLabel = new JLabel("Stats3PlayerLead");
		JLabel stats4PlayerLeadLabel = new JLabel("Stats4PlayerLead");
		JLabel stats5PlayerLeadLabel = new JLabel("Stats5PlayerLead");
		stats1PlayerLeadPanel.add(stats1PlayerLeadLabel);
		stats2PlayerLeadPanel.add(stats2PlayerLeadLabel);
		stats3PlayerLeadPanel.add(stats3PlayerLeadLabel);
		stats4PlayerLeadPanel.add(stats4PlayerLeadLabel);
		stats5PlayerLeadPanel.add(stats5PlayerLeadLabel); 
		return statsPlayerLeadPanel;
	}
	
	private JPanel buildMapStatePanel() {
		JPanel statsMapStatePanel = new JPanel();
		statsMapStatePanel.setLayout(new BoxLayout(statsMapStatePanel, BoxLayout.Y_AXIS));
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
		JLabel stats1MapStateLabel = new JLabel("Stats1Balance");
		JLabel stats2MapStateLabel = new JLabel("Stats2Balance");
		JLabel stats3MapStateLabel = new JLabel("Stats3Balance");
		JLabel stats4MapStateLabel = new JLabel("Stats4Balance");
		JLabel stats5MapStateLabel = new JLabel("Stats5Balance");
		stats1MapStatePanel.add(stats1MapStateLabel);
		stats2MapStatePanel.add(stats2MapStateLabel);
		stats3MapStatePanel.add(stats3MapStateLabel);
		stats4MapStatePanel.add(stats4MapStateLabel);
		stats5MapStatePanel.add(stats5MapStateLabel); 
		return statsMapStatePanel;
	}
}
