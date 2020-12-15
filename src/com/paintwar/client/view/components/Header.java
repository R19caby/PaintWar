package com.paintwar.client.view.components;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.pages.PageName;

public class Header extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3670573890343632722L;

	private JButton home;
	private JButton collection;
	private JButton battlepass;
	private JButton shop;
	private JButton parameters;
	private JButton quit;

	public Header(MainWindow manager)
	{
		this.setOpaque(false);
		this.setLayout(new FlowLayout());

		home = ButtonFactory.getInstance().getButton("Home", PageName.HOME, manager);
		this.add(home);

		collection = ButtonFactory.getInstance().getButton("Collection", PageName.COLLECTION, manager);
		this.add(collection);

		battlepass = ButtonFactory.getInstance().getButton("BattlePass", PageName.BATTLEPASS, manager);
		this.add(battlepass);

		shop = ButtonFactory.getInstance().getButton("Shop", PageName.SHOP, manager);
		this.add(shop);

		parameters = ButtonFactory.getInstance().getParameterButton(manager);
		this.add(parameters);

		quit = ButtonFactory.getInstance().getButton("Quit", PageName.CONNEXION_CHOICE, manager);
		this.add(quit);

	}

}
