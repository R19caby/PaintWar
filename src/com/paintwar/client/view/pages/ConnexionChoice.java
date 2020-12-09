package com.paintwar.client.view.pages;
import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.components.ButtonFactory;
import com.paintwar.client.view.components.ParametersButton;
import com.paintwar.client.view.components.QuitAppButton;

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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.PanelUI;

public class ConnexionChoice extends JPanel {
	
	private static final long serialVersionUID = 7891834666568908781L;
	private MainWindow manager;

	public ConnexionChoice(MainWindow parent) {
		super();
		manager = parent;
		setLayout(new BorderLayout(0, 50));
		setOpaque(false);
		JPanel center = new JPanel();
		center.setOpaque(false);
		JPanel north = new JPanel();
		north.setOpaque(false);
		north.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		
		JLabel logo = new JLabel("Paint War");
		logo.setFont(new Font(logo.getFont().getName(), logo.getFont().getSize(), logo.getFont().getSize() + 100));
		ButtonFactory logInButton = new ButtonFactory("Se connecter", PageName.LOG_IN, manager);
		ButtonFactory signInButton = new ButtonFactory("Rejoindre", PageName.SIGN_IN, manager);
		ButtonFactory guestButton = new ButtonFactory("Continuer en tant qu'invité", PageName.GUEST, manager);
		logInButton.setPreferredSize(new Dimension(200, 40));
		signInButton.setPreferredSize(new Dimension(200, 40));
		guestButton.setPreferredSize(new Dimension(200, 40));
		JPanel logInButtonPanel = new JPanel();
		JPanel signInButtonPanel = new JPanel();
		JPanel guestButtonPanel = new JPanel();
		logInButtonPanel.setOpaque(false);
		signInButtonPanel.setOpaque(false);
		guestButtonPanel.setOpaque(false);
		logInButtonPanel.add(logInButton);
		signInButtonPanel.add(signInButton);
		guestButtonPanel.add(guestButton);
		ParametersButton param = new ParametersButton(manager);
		QuitAppButton quit = new QuitAppButton(manager);
		
		JPanel logoPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		JPanel buttonTopPanel = new JPanel();		
		JPanel logoButtonsPanel = new JPanel();
		logoPanel.setOpaque(false);
		buttonsPanel.setOpaque(false);
		buttonTopPanel.setOpaque(false);
		logoButtonsPanel.setOpaque(false);
		
		logoPanel.add(logo);
		buttonsPanel.add(logInButtonPanel);
		buttonsPanel.add(signInButtonPanel);
		buttonsPanel.add(guestButtonPanel);
		
		
		buttonsPanel.setLayout(new GridLayout(3, 1));
		logoButtonsPanel.setLayout(new GridLayout(2, 1));
		
		logoButtonsPanel.add(logoPanel);
		logoButtonsPanel.add(buttonsPanel);
		logoButtonsPanel.setPreferredSize(new Dimension(800, 500));

		center.add(logoButtonsPanel);
		
		buttonTopPanel.add(param);
		buttonTopPanel.add(quit);
		north.add(buttonTopPanel);
		
		add(center, BorderLayout.CENTER);
		add(north, BorderLayout.NORTH);
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
