package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.paintwar.client.view.components.Header;

public class Home extends JPanel {
	
	private static final long serialVersionUID = -1539937711095867060L;
	
	String playerName;
	
	public Home(String name) {
		super();
		this.playerName = name;
		setLayout(new BorderLayout());
		
		
		add(new Header(), BorderLayout.NORTH);
		
		
		JPanel center = new JPanel();
		add(center, BorderLayout.CENTER);
		center.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		center.setLayout(new BorderLayout(200, 200));
		
		JPanel centerNorth = new JPanel();
		centerNorth.setLayout(new BoxLayout(centerNorth, BoxLayout.PAGE_AXIS));
		center.add(centerNorth, BorderLayout.NORTH);
		JButton playGame = new JButton("Jouer");
		centerNorth.add(playGame);
		playGame.setAlignmentX(CENTER_ALIGNMENT);
		JComboBox<String> gameType = new JComboBox<>();
		centerNorth.add(gameType);
		gameType.setAlignmentX(CENTER_ALIGNMENT);
		gameType.setPreferredSize(new Dimension(200, 20));
		gameType.setMaximumSize(new Dimension(200, 20));
		gameType.addItemListener(new SelectType(gameType));
		gameType.addItem("Partie classée");
		gameType.addItem("Partie non classée");
		
		JPanel centerCenter = new JPanel();
		center.add(centerCenter);
		JButton createGame = new JButton("Créer une partie");
		centerCenter.add(createGame, BorderLayout.CENTER);
		createGame.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel centerSouth = new JPanel();
		center.add(centerSouth, BorderLayout.SOUTH);
		centerSouth.setLayout(new BoxLayout(centerSouth, BoxLayout.PAGE_AXIS));
		JButton joinGame = new JButton("Rejoindre une partie");
		centerSouth.add(joinGame);
		joinGame.setAlignmentX(CENTER_ALIGNMENT);
		JTextField gamePassword = new JTextField("Code de la partie");
		centerSouth.add(gamePassword);
		gamePassword.setAlignmentX(CENTER_ALIGNMENT);
		gamePassword.setPreferredSize(new Dimension(200, 20));
		gamePassword.setMaximumSize(new Dimension(200, 20));
		gamePassword.addMouseListener(new ResetText(gamePassword, "Code de la partie"));
		
		JPanel west = new JPanel();
		add(west, BorderLayout.WEST);
		west.setLayout(new BorderLayout(10, 10));
		JPanel playerData = new JPanel();
		west.add(playerData, BorderLayout.NORTH);
		playerData.setPreferredSize(new Dimension(300, 300));
		playerData.setMaximumSize(new Dimension(300, 300));
		playerData.setLayout(new BoxLayout(playerData, BoxLayout.PAGE_AXIS));
		playerData.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel id = new JLabel("Nom du joueur : " + playerName);
		playerData.add(id);
		JLabel toBecome = new JLabel("Ici seront affichées les informations sur le compte du joueur");
		playerData.add(toBecome);
		JPanel news = new JPanel();
		west.add(news, BorderLayout.SOUTH);
		news.setPreferredSize(new Dimension(300, 300));
		news.setMaximumSize(new Dimension(300, 300));
		news.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel title = new JLabel("Actualité du jeu");
		news.add(title);
		
		
		JPanel east = new JPanel();
		add(east, BorderLayout.EAST);
		east.setBorder(BorderFactory.createLineBorder(Color.black));
		east.setLayout(new BoxLayout(east, BoxLayout.PAGE_AXIS));
		JLabel eastTitle = new JLabel("Liste d'amis");
		east.add(eastTitle);
		
		JPanel favoriteFriends = new JPanel();
		east.add(favoriteFriends);
		favoriteFriends.setLayout(new BorderLayout(10, 10));
		JLabel favoritesTitle = new JLabel("Favoris");
		favoriteFriends.add(favoritesTitle, BorderLayout.NORTH);
		JScrollPane favoriteScroller = new JScrollPane();
		favoriteFriends.add(favoriteScroller, BorderLayout.CENTER);
		favoriteScroller.setPreferredSize(new Dimension(300, 300));
		favoriteScroller.setMaximumSize(new Dimension(300, 300));
		
		JPanel allFriends = new JPanel();
		east.add(allFriends);
		allFriends.setLayout(new BorderLayout(10, 10));
		JLabel allTitle = new JLabel("Tous");
		allFriends.add(allTitle, BorderLayout.NORTH);
		JScrollPane allScroller = new JScrollPane();
		allFriends.add(allScroller, BorderLayout.CENTER);
		allScroller.setPreferredSize(new Dimension(300, 300));
		allScroller.setMaximumSize(new Dimension(300, 300));
		
		
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
	
public class ResetText implements MouseListener {
		
		private JTextField associatedField;
		private String printing;
		
		public ResetText(JTextField field, String text) {
			this.associatedField = field;
			this.printing = text;
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			associatedField.setText("");
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
