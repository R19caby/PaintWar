package com.paintwar.client.view.components;

import java.awt.FlowLayout;
import javax.swing.JPanel;

import com.paintwar.client.view.pages.PageName;

public class Header extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3670573890343632722L;
	
	private ButtonFactory home;
	private ButtonFactory collection;
	private ButtonFactory battlepass;
	private ButtonFactory shop;
	private ButtonFactory parameters;
	private ButtonFactory quit;	
	
	public Header() {
		
		this.setLayout(new FlowLayout());
		
		home = new ButtonFactory("Home", PageName.HOME);
		this.add(home);
		
		collection = new ButtonFactory("Collection", PageName.COLLECTION);
		this.add(collection);
		
		battlepass = new ButtonFactory("BattlePass", PageName.BATTLEPASS);
		this.add(battlepass);
		
		shop = new ButtonFactory("Shop", PageName.SHOP);
		this.add(shop);
		
		parameters = new ParametersButton();
		this.add(parameters);
		
		quit = new ButtonFactory("Quit", PageName.CONNEXION_CHOICE);
		this.add(quit);
		
	}

}
