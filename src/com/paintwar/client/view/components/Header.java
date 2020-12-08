package com.paintwar.client.view.components;

import java.awt.FlowLayout;
import javax.swing.JPanel;

import com.paintwar.client.view.MainWindow;
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
	
	public Header(MainWindow manager) {
		
		this.setLayout(new FlowLayout());
		
		home = new ButtonFactory("Home", PageName.HOME, manager);
		this.add(home);
		
		collection = new ButtonFactory("Collection", PageName.COLLECTION, manager);
		this.add(collection);
		
		battlepass = new ButtonFactory("BattlePass", PageName.BATTLEPASS, manager);
		this.add(battlepass);
		
		shop = new ButtonFactory("Shop", PageName.SHOP, manager);
		this.add(shop);
		
		parameters = new ParametersButton(manager);
		this.add(parameters);
		
		quit = new ButtonFactory("Quit", PageName.CONNEXION_CHOICE, manager);
		this.add(quit);
		
	}

}
