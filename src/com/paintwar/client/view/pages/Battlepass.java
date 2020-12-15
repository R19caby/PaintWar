package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.components.Header;

public class Battlepass extends JPanel {
	
	private static final long serialVersionUID = -3998504917418757786L;
	private MainWindow manager;
	private String playerName;
	
	
	public Battlepass(String name, MainWindow parent) {
		super();
		manager = parent;
		playerName = name;
		this.setOpaque(false);
		this.setLayout(new BorderLayout(50,  50));
		
		add(new Header(manager), BorderLayout.NORTH);
		
		//Panels principaux
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		center.setOpaque(false);
		south.setOpaque(false);
		center.setLayout(new BorderLayout());
		south.setLayout(new BorderLayout());
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		//Panel centre
		JPanel roadmap = createRoadMap();
		JPanel playerData = new JPanel();
		roadmap.setOpaque(false);
		playerData.setOpaque(false);
		center.add(roadmap, BorderLayout.CENTER);
		center.add(playerData, BorderLayout.SOUTH);
		playerData.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		JLabel player = new JLabel("Ici seront affichées les informations principales concernant le joueur");
		playerData.add(player);
		
		//Panel sud
		JPanel balance = new JPanel();
		balance.setOpaque(false);
		south.add(balance, BorderLayout.WEST);
		balance.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		JLabel explanation = new JLabel("Ici sera affiché le solde du joueur");
		balance.add(explanation);
		}
	
	//Création de la partie Roadmap de la page
	private JPanel createRoadMap() {
		//Initialisation
		JPanel result = new JPanel();
		JPanel titlePane = new JPanel();
		JPanel rewards = new JPanel();
		result.setOpaque(false);
		titlePane.setOpaque(false);
		rewards.setOpaque(false);
		
		//Ajout de la scrollbarre
		JScrollPane scrollPane = new JScrollPane(rewards, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(false);
		result.setLayout(new BorderLayout());
		rewards.setLayout(new FlowLayout());
		result.add(titlePane, BorderLayout.NORTH);
		result.add(scrollPane, BorderLayout.CENTER);
		rewards.setAutoscrolls(true);
		
		//Paramétrages et titre
		rewards.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
		JLabel roadmapTitle = new JLabel("Roadmap des récompenses");
		roadmapTitle.setFont(new Font(roadmapTitle.getFont().getName(), roadmapTitle.getFont().getSize(), roadmapTitle.getFont().getSize()+10));
		titlePane.add(roadmapTitle);
		
		//Ajout de tous les panneaux de récompenses
		JPanel lvl;
		JTextArea title;
		for (int i=0; i<=20; i++) {
			lvl = new JPanel();
			lvl.setBackground(Color.white);
			lvl.setBorder(BorderFactory.createLineBorder(Color.red, 2, true));
			lvl.setPreferredSize(new Dimension(100, 200));
			title = new JTextArea("Récompenses \ndu lvl" + i);
			title.setBackground(lvl.getBackground());
			lvl.add(title);
			rewards.add(lvl);
		}
		return result;
		
		
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

}
