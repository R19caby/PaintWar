package com.paintwar.client.view.pages;
import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.components.ButtonFactory;
import com.paintwar.client.view.components.QuitAppButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConnexionChoice extends JPanel {
	
	private static final long serialVersionUID = 7891834666568908781L;
	private MainWindow manager;

	public ConnexionChoice(MainWindow parent) {
		super();
		manager = parent;
		setLayout(new BorderLayout(0, 50));
		JPanel center = new JPanel();
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JLabel logo = new JLabel("Paint War");
		logo.setFont(new Font(logo.getFont().getName(), logo.getFont().getSize(), logo.getFont().getSize() + 100));
		JButton logInButton = ButtonFactory.getInstance().getButton("Se connecter", PageName.LOG_IN, manager);
		JButton signInButton = ButtonFactory.getInstance().getButton("Rejoindre", PageName.SIGN_IN, manager);
		JButton guestButton = ButtonFactory.getInstance().getButton("Continuer en tant qu'invité", PageName.GUEST, manager);
		logInButton.setPreferredSize(new Dimension(200, 40));
		signInButton.setPreferredSize(new Dimension(200, 40));
		guestButton.setPreferredSize(new Dimension(200, 40));
		JPanel logInButtonPanel = new JPanel();
		JPanel signInButtonPanel = new JPanel();
		JPanel guestButtonPanel = new JPanel();
		logInButtonPanel.add(logInButton);
		signInButtonPanel.add(signInButton);
		guestButtonPanel.add(guestButton);
		JButton param = ButtonFactory.getInstance().getParameterButton(manager);
		QuitAppButton quit = new QuitAppButton(manager);
		
		JPanel logoPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		JPanel buttonTopPanel = new JPanel();		
		JPanel logoButtonsPanel = new JPanel();
		
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
	
	
}
