package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.components.ButtonFactory;

public class Parameters extends JPanel {
	private static final long serialVersionUID = -2023081651434102668L;
	private MainWindow manager;
	private JLabel pageLabel;
	private JPanel parametersContainer;
	private JButton gameplayCategory;
	private JButton accountcategory;
	private JButton audioCategory;
	private JButton videoCategory;

	public Parameters(MainWindow parent) {
		super();
		manager = parent;
		setLayout(new BorderLayout());
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		add(north, BorderLayout.NORTH);
		add(south, BorderLayout.SOUTH);
		add(center, BorderLayout.CENTER);

		JLabel parametersTitle = new JLabel("Paramètres");
		parametersTitle.setFont(new Font(parametersTitle.getFont().getName(),parametersTitle.getFont().getSize(),parametersTitle.getFont().getSize() + 15));
		JPanel titlePanel = new JPanel();
		titlePanel.add(parametersTitle);
		north.add(titlePanel);

		ButtonFactory validateButton = new ButtonFactory("Valider", PageName.PARAMETERS, manager);
		south.add(validateButton);

		gameplayCategory = new JButton("Gameplay");
		gameplayCategory.setEnabled(false);
		accountcategory = new JButton("Account");
		audioCategory = new JButton("Audio");
		videoCategory = new JButton("Video");
		gameplayCategory.addActionListener(new CategoryChooserListener("Gameplay", gameplayCategory));
		accountcategory.addActionListener(new CategoryChooserListener("Account", accountcategory));
		audioCategory.addActionListener(new CategoryChooserListener("Audio", audioCategory));
		videoCategory.addActionListener(new CategoryChooserListener("Video", videoCategory));
		JPanel categoryPanel = new JPanel();
		categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
		categoryPanel.add(gameplayCategory);
		categoryPanel.add(accountcategory);
		categoryPanel.add(audioCategory);
		categoryPanel.add(videoCategory);

		pageLabel = new JLabel("Gameplay");
		pageLabel.setFont(new Font(pageLabel.getFont().getName(),pageLabel.getFont().getSize(),pageLabel.getFont().getSize() + 7));
		JPanel pageLabelPanel = new JPanel();
		parametersContainer = new JPanel();
		parametersContainer.setLayout(new CardLayout());

		JPanel parametersGameplay = buildGameplay();
		JPanel parametersAccount = buildAccount();
		JPanel parametersVideo = buildVideo();
		JPanel parametersAudio = buildAudio();

		parametersContainer.add(parametersGameplay, "Gameplay");
		parametersContainer.add(parametersAccount, "Account" );
		parametersContainer.add(parametersAudio, "Audio");
		parametersContainer.add(parametersVideo, "Video");

		JScrollPane scrollPane = new JScrollPane(parametersContainer, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		
		parametersContainer.setAutoscrolls(true);
		scrollPane.setPreferredSize(new Dimension( 700,600));
		JPanel parametersPanel = new JPanel();
		parametersPanel.setLayout(new BoxLayout(parametersPanel, BoxLayout.Y_AXIS));

		pageLabelPanel.add(pageLabel);
		parametersPanel.add(pageLabelPanel);
		parametersPanel.add(scrollPane);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setPreferredSize(new Dimension(800, 600));
		splitPane.setLeftComponent(categoryPanel);
		splitPane.setRightComponent(parametersPanel);
		center.add(splitPane);
	}

	public JPanel buildGameplay() {
		// Ajout des différentes catégories : Gameplay
		JPanel parametersGameplay = new JPanel();
		parametersGameplay.setLayout(new BoxLayout(parametersGameplay, BoxLayout.Y_AXIS));
		JPanel param1PanelGameplay = new JPanel();
		JPanel param2PanelGameplay = new JPanel();
		JPanel param3PanelGameplay = new JPanel();
		JPanel param4PanelGameplay = new JPanel();
		JPanel param5PanelGameplay = new JPanel();
		JLabel param1Gameplay = new JLabel("Param1Gameplay");
		JLabel param2Gameplay = new JLabel("Param2Gameplay");
		JLabel param3Gameplay = new JLabel("Param3Gameplay");
		JLabel param4Gameplay = new JLabel("Param4Gameplay");
		JLabel param5Gameplay = new JLabel("Param5Gameplay");
		param1PanelGameplay.add(param1Gameplay);
		param2PanelGameplay.add(param2Gameplay);
		param3PanelGameplay.add(param3Gameplay);
		param4PanelGameplay.add(param4Gameplay);
		param5PanelGameplay.add(param5Gameplay);

		param1PanelGameplay.setMaximumSize(new Dimension(700, 75));
		param2PanelGameplay.setMaximumSize(new Dimension(700, 75));
		param3PanelGameplay.setMaximumSize(new Dimension(700, 75));
		param4PanelGameplay.setMaximumSize(new Dimension(700, 75));
		param5PanelGameplay.setMaximumSize(new Dimension(700, 75));
		param1PanelGameplay.setMinimumSize(new Dimension(700, 25));
		param2PanelGameplay.setMinimumSize(new Dimension(700, 25));
		param3PanelGameplay.setMinimumSize(new Dimension(700, 25));
		param4PanelGameplay.setMinimumSize(new Dimension(700, 25));
		param5PanelGameplay.setMinimumSize(new Dimension(700, 25));
		param1PanelGameplay.setBorder(BorderFactory.createLineBorder(Color.black));
		param2PanelGameplay.setBorder(BorderFactory.createLineBorder(Color.black));
		param3PanelGameplay.setBorder(BorderFactory.createLineBorder(Color.black));
		param4PanelGameplay.setBorder(BorderFactory.createLineBorder(Color.black));
		param5PanelGameplay.setBorder(BorderFactory.createLineBorder(Color.black));

		parametersGameplay.add(param1PanelGameplay);
		parametersGameplay.add(param2PanelGameplay);
		parametersGameplay.add(param3PanelGameplay);
		parametersGameplay.add(param4PanelGameplay);
		parametersGameplay.add(param5PanelGameplay);

		return parametersGameplay;
	}

	public JPanel buildAccount() {
		// Ajout des différentes catégories : Account
		JPanel parametersAccount = new JPanel();
		parametersAccount.setLayout(new BoxLayout(parametersAccount, BoxLayout.Y_AXIS));
		JPanel param1PanelAccount = new JPanel();
		JPanel param2PanelAccount = new JPanel();
		JPanel param3PanelAccount = new JPanel();
		JLabel param1Account = new JLabel("Param1Account");
		JLabel param2Account = new JLabel("Param2Account");
		JLabel param3Account = new JLabel("Param3Account");
		param1PanelAccount.add(param1Account);
		param2PanelAccount.add(param2Account);
		param3PanelAccount.add(param3Account);

		param1PanelAccount.setMaximumSize(new Dimension(700, 75));
		param2PanelAccount.setMaximumSize(new Dimension(700, 75));
		param3PanelAccount.setMaximumSize(new Dimension(700, 75));
		param1PanelAccount.setMinimumSize(new Dimension(700, 25));
		param2PanelAccount.setMinimumSize(new Dimension(700, 25));
		param3PanelAccount.setMinimumSize(new Dimension(700, 25));
		param1PanelAccount.setBorder(BorderFactory.createLineBorder(Color.black));
		param2PanelAccount.setBorder(BorderFactory.createLineBorder(Color.black));
		param3PanelAccount.setBorder(BorderFactory.createLineBorder(Color.black));

		parametersAccount.add(param1PanelAccount);
		parametersAccount.add(param2PanelAccount);
		parametersAccount.add(param3PanelAccount);

		return parametersAccount;
	}

	public JPanel buildVideo() {
		// Ajout des différentes catégories : Video
		JPanel parametersVideo = new JPanel();
		parametersVideo.setLayout(new BoxLayout(parametersVideo, BoxLayout.Y_AXIS));
		JPanel param1PanelVideo = new JPanel();
		JPanel param2PanelVideo = new JPanel();
		JPanel param3PanelVideo = new JPanel();
		JLabel param1Video = new JLabel("Param1Video");
		JLabel param2Video = new JLabel("Param2Video");
		JLabel param3Video = new JLabel("Param3Video");
		param1PanelVideo.add(param1Video);
		param2PanelVideo.add(param2Video);
		param3PanelVideo.add(param3Video);

		param1PanelVideo.setMaximumSize(new Dimension(700, 75));
		param2PanelVideo.setMaximumSize(new Dimension(700, 75));
		param3PanelVideo.setMaximumSize(new Dimension(700, 75));
		param1PanelVideo.setMinimumSize(new Dimension(700, 25));
		param2PanelVideo.setMinimumSize(new Dimension(700, 25));
		param3PanelVideo.setMinimumSize(new Dimension(700, 25));
		param1PanelVideo.setBorder(BorderFactory.createLineBorder(Color.black));
		param2PanelVideo.setBorder(BorderFactory.createLineBorder(Color.black));
		param3PanelVideo.setBorder(BorderFactory.createLineBorder(Color.black));

		parametersVideo.add(param1PanelVideo);
		parametersVideo.add(param2PanelVideo);
		parametersVideo.add(param3PanelVideo);

		return parametersVideo;
	}

	public JPanel buildAudio() {
		// Ajout des différentes catégories : Audio
		JPanel parametersAudio = new JPanel();
		parametersAudio.setLayout(new BoxLayout(parametersAudio, BoxLayout.Y_AXIS));
		JPanel param1PanelAudio = new JPanel();
		JPanel param2PanelAudio = new JPanel();
		JPanel param3PanelAudio = new JPanel();
		JLabel param1Audio = new JLabel("Param1Audio");
		JLabel param2Audio = new JLabel("Param2Audio");
		JLabel param3Audio = new JLabel("Param3Audio");
		param1PanelAudio.add(param1Audio);
		param2PanelAudio.add(param2Audio);
		param3PanelAudio.add(param3Audio);

		param1PanelAudio.setMaximumSize(new Dimension(700, 75));
		param2PanelAudio.setMaximumSize(new Dimension(700, 75));
		param3PanelAudio.setMaximumSize(new Dimension(700, 75));
		param1PanelAudio.setMinimumSize(new Dimension(700, 25));
		param2PanelAudio.setMinimumSize(new Dimension(700, 25));
		param3PanelAudio.setMinimumSize(new Dimension(700, 25));
		param1PanelAudio.setBorder(BorderFactory.createLineBorder(Color.black));
		param2PanelAudio.setBorder(BorderFactory.createLineBorder(Color.black));
		param3PanelAudio.setBorder(BorderFactory.createLineBorder(Color.black));

		parametersAudio.add(param1PanelAudio);
		parametersAudio.add(param2PanelAudio);
		parametersAudio.add(param3PanelAudio);

		return parametersAudio;
	}

	public class CategoryChooserListener implements ActionListener {
		private String category;
		private JButton button;

		public CategoryChooserListener(String category, JButton button) {
			this.category = category;
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cl = (CardLayout) (parametersContainer.getLayout());
            cl.show(parametersContainer, category);
			pageLabel.setText(category);
			gameplayCategory.setEnabled(true);
			accountcategory.setEnabled(true);
			audioCategory.setEnabled(true);
			videoCategory.setEnabled(true);
			button.setEnabled(false);
		}

	}
}
