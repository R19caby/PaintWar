package com.paintwar.client.view.components;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.pages.PageName;

/**
 * A voir si c'est utile d'avoir des boutons non ronds.
 * @author R19Caby
 *
 */
public class ButtonFactoryNotRound extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2910361933495135715L;
	
	private String cardName;
	private MainWindow manager;
	
	public ButtonFactoryNotRound(String name, PageName pageName, MainWindow parent) {
		super(name);
		manager = parent;
		addActionListener(new RedirectActionListener(pageName.toString()));
	}
	
	public class RedirectActionListener implements ActionListener {
		private String pageToReach;
		
		public RedirectActionListener(String category) {
			this.pageToReach = category;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cl = (CardLayout) (manager.getContentContainer().getLayout());
			cl.show(manager.getContentContainer(), pageToReach);
		}
		
	}
}
