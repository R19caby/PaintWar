package com.paintwar.client.view.pages;
import com.paintwar.client.view.components.ButtonFactory;
import com.paintwar.client.view.components.ParametersButton;
import com.paintwar.client.view.components.QuitAppButton;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConnexionChoice extends JPanel {
	
	private static final long serialVersionUID = 7891834666568908781L;

	public ConnexionChoice() {
		super();
		setLayout(new BorderLayout());
		JPanel center = new JPanel();
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JLabel logo = new JLabel("Logo");
		ButtonFactory logInButton = new ButtonFactory("Se connecter", PageName.LOG_IN);
		ButtonFactory signInButton = new ButtonFactory("Rejoindre", PageName.SIGN_IN);
		ButtonFactory guestButton = new ButtonFactory("Continuer en tant qu'invité", PageName.GUEST);
		ParametersButton param = new ParametersButton();
		QuitAppButton quit = new QuitAppButton();
		
		
		JPanel logoPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		JPanel buttonTopPanel = new JPanel();		
		JPanel logoButtonsPanel = new JPanel();
		
		logoPanel.add(logo);
		buttonsPanel.add(logInButton);
		buttonsPanel.add(signInButton);
		buttonsPanel.add(guestButton);
		
		
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		logoButtonsPanel.setLayout(new BoxLayout(logoButtonsPanel, BoxLayout.Y_AXIS));
		
		logoButtonsPanel.add(logoPanel);
		logoButtonsPanel.add(buttonsPanel);

		center.add(logoButtonsPanel);
		
		buttonTopPanel.add(param);
		buttonTopPanel.add(quit);
		north.add(buttonTopPanel);
		
		add(center, BorderLayout.CENTER);
		add(north, BorderLayout.NORTH);
	}
	
	
}
