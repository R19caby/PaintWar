package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.components.Header;
import com.paintwar.client.view.pages.Home.ResetText;
import com.paintwar.client.view.pages.Home.SelectType;

public class Guest extends JPanel {
	
	private static final long serialVersionUID = -1539937711095867060L;
	
	private String playerName;
	private MainWindow manager;
	private static final Dimension DIMENSION_PLAYGAME = new Dimension(200, 75);
	private static final Dimension DIMENSION_CREATE_GAME = new Dimension(200, 100);
	private static final Dimension DIMENSION_JOIN_GAME = new Dimension(200, 75);
	private static final Dimension DIMENSION_GAME_TYPE = new Dimension(200, 25);
	private static final Dimension DIMENSION_GAME_PASSWORD = new Dimension(200, 25);
	private static final Dimension DIMENSION_PLAYER_DATA = new Dimension(500, 300);
	private static final Dimension DIMENSION_EAST_TITLE = new Dimension(500, 25);
	private static final Dimension DIMENSION_EAST_SCROLLER = new Dimension(500, 300);
	// Note : 75 + 25 = 100 -> (Ergo = <3)
	public Guest(String name, MainWindow parent) {
		super();
		manager = parent;
		this.playerName = name;
		setLayout(new BorderLayout(25, 25));
		
		add(new Header(manager), BorderLayout.NORTH);
		
		
		JPanel center = new JPanel();
		JPanel centerNorthContainer = new JPanel();
		JPanel centerNorth = new JPanel();
		JPanel playGamePanel = new JPanel();
		JPanel centerCenter = new JPanel();
		JPanel gameTypePanel = new JPanel();
		JPanel centerSouthContainer = new JPanel();
		JPanel centerSouth = new JPanel();
		JPanel joinGamePanel = new JPanel();
		JPanel gamePasswordPanel = new JPanel();
		JPanel west = new JPanel();
		JPanel playerData = new JPanel();
		JPanel news = new JPanel();
		JPanel east = new JPanel();
		JPanel eastTitlePanel = new JPanel();
		JPanel warningPanel = new JPanel();
		
		center.setOpaque(false);
		centerNorthContainer.setOpaque(false);
		centerNorth.setOpaque(false);
		playGamePanel.setOpaque(false);
		centerCenter.setOpaque(false);
		gameTypePanel.setOpaque(false);
		centerSouthContainer.setOpaque(false);
		centerSouth.setOpaque(false);
		joinGamePanel.setOpaque(false);
		gamePasswordPanel.setOpaque(false);
		west.setOpaque(false);
		east.setOpaque(false);
		eastTitlePanel.setOpaque(false);
		warningPanel.setOpaque(false);
		
		playerData.setBackground(Color.white);
		news.setBackground(Color.white);
		
		
		add(center, BorderLayout.CENTER);
		center.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
		center.setLayout(new GridLayout(0, 1));
		
		centerNorth.setLayout(new BoxLayout(centerNorth, BoxLayout.PAGE_AXIS));
		center.add(centerNorthContainer);
		centerNorthContainer.add(centerNorth);
		JButton playGame = new JButton("Jouer");
		playGamePanel.add(playGame);
		playGame.setPreferredSize(DIMENSION_PLAYGAME);
		centerNorth.add(playGamePanel);
		playGame.setAlignmentX(CENTER_ALIGNMENT);
		JComboBox<String> gameType = new JComboBox<>();
		gameTypePanel.add(gameType);
		centerNorth.add(gameTypePanel);
		gameType.setPreferredSize(DIMENSION_GAME_TYPE);
		gameType.addItemListener(new SelectType(gameType));
		gameType.addItem("Partie classée");
		gameType.addItem("Partie non classée");
		
		center.add(centerCenter);
		JButton createGame = new JButton("Créer une partie");
		createGame.setPreferredSize(DIMENSION_CREATE_GAME);
		centerCenter.add(createGame, BorderLayout.CENTER);
		createGame.setAlignmentX(CENTER_ALIGNMENT);
		
		center.add(centerSouthContainer);
		centerSouthContainer.add(centerSouth);
		centerSouth.setLayout(new BoxLayout(centerSouth, BoxLayout.PAGE_AXIS));
		JButton joinGame = new JButton("Rejoindre une partie");
		joinGame.setPreferredSize(DIMENSION_JOIN_GAME);
		centerSouth.add(joinGamePanel);
		joinGamePanel.add(joinGame);
		JTextField gamePassword = new JTextField("Code de la partie");
		centerSouth.add(gamePasswordPanel);
		gamePasswordPanel.add(gamePassword);
		gamePassword.setPreferredSize(DIMENSION_GAME_PASSWORD);
		gamePassword.addMouseListener(new ResetText(gamePassword, "Code de la partie"));
		
		add(west, BorderLayout.WEST);
		west.setLayout(new BorderLayout(10, 10));
		west.add(playerData, BorderLayout.NORTH);
		JLabel id = new JLabel("Nom du joueur : " + playerName);
		JLabel toBecome = new JLabel("Ici seront affichées les informations sur le compte du joueur");
		playerData.setPreferredSize(DIMENSION_PLAYER_DATA);
		playerData.setLayout(new BoxLayout(playerData, BoxLayout.PAGE_AXIS));
		playerData.setBorder(BorderFactory.createLineBorder(Color.black));
		playerData.add(id);
		playerData.add(toBecome);
		
		west.add(news, BorderLayout.SOUTH);
		news.setPreferredSize(DIMENSION_PLAYER_DATA);
		news.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel title = new JLabel("Actualités du jeu");
		incrFontSize(title, 8);
		news.add(title);
		
		
		add(east, BorderLayout.EAST);
		//east.setLayout(new BoxLayout(east, BoxLayout.PAGE_AXIS));
		east.setLayout(new GridLayout(2, 1));
		east.setBorder(BorderFactory.createLineBorder(Color.black));
		east.add(eastTitlePanel);
		JLabel eastTitle = new JLabel("Liste d'amis");
		eastTitle.setPreferredSize(DIMENSION_EAST_TITLE);
		incrFontSize(eastTitle, 10); 
		eastTitlePanel.add(eastTitle);
		
		east.add(warningPanel);
		JTextArea warning = new JTextArea("La liste d'amis n'est disponible que si vous avez un compte !");
		warning.setBackground(Color.white);
		warning.setBorder(BorderFactory.createLineBorder(Color.black));
		incrFontSize(warning, 5);
		warningPanel.add(warning);
		
		
	}
	
	private void incrFontSize(Component component, int delta) {
		component.setFont(new Font(component.getFont().getName(), component.getFont().getSize(), component.getFont().getSize() + delta));
	}
	
	public class SelectType implements ItemListener {
		
		private JComboBox<String> choice;
		
		public SelectType(JComboBox<String> possibilities) {
			choice = possibilities;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			String newSelected = (String) e.getItem();
			choice.setSelectedItem(newSelected);
			
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image icon = null;
		try {
			icon = ImageIO.read(new File(System.getProperty("user.dir") + "/src/graphicResources/paint_HQ.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(icon != null)
			g.drawImage(icon, 0, 0, getWidth(), getHeight(), null);
		}
	
public class ResetText implements MouseListener {
		
		private JTextField associatedField;
		private String printing;
		
		
		public ResetText(JTextField field, String text) {
			associatedField = field;
			printing = text;
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (associatedField.getText().equals("") || associatedField.getText().equals(printing)) {
				associatedField.setText("");
			}
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			if (associatedField.getText().equals("")) {
				this.associatedField.setText(printing);
			}
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}

}
