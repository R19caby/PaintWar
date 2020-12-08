package com.paintwar.client.view.components;

import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.pages.PageName;

public class BackButton extends ButtonFactory {

	private static final long serialVersionUID = -5498137597461053518L;
	public BackButton(PageName pageName, MainWindow manager) {
		super("Retour", pageName, manager);
		/*
		 * Ici les modifs graphiques genre couleur etc
		 */
	}
}
