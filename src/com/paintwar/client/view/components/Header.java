package com.paintwar.client.view.components;

import java.awt.FlowLayout;
import javax.swing.JPanel;

public class Header extends JPanel {
	
	private ButtonFactory home;
	private ButtonFactory collection;
	private ButtonFactory battlepass;
	private ButtonFactory shop;
	private ButtonFactory parameters;
	private ButtonFactory quit;	
	
	public Header() {
		
		this.setLayout(new FlowLayout());
		
		home = new ButtonFactory("Home", "Homepage");
		this.add(home);
		
		collection = new ButtonFactory("Collection", "Collection");
		this.add(collection);
		
		battlepass = new ButtonFactory("BattlePass", "BattlePass");
		this.add(battlepass);
		
		shop = new ButtonFactory("Shop", "Shop");
		this.add(shop);
		
		parameters = new ButtonFactory("Parameters", "Parameters");
		this.add(parameters);
		
		quit = new ButtonFactory("Quit", "Connexion");
		this.add(quit);
		
	}

}
