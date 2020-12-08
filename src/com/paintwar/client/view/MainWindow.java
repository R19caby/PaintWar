package com.paintwar.client.view;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.paintwar.client.view.pages.AccountCreation;
import com.paintwar.client.view.pages.Collection;
import com.paintwar.client.view.pages.ConnexionChoice;
import com.paintwar.client.view.pages.Home;
import com.paintwar.client.view.pages.LogIn;
import com.paintwar.client.view.pages.PageName;
import com.paintwar.client.view.pages.Parameters;
import com.paintwar.client.view.pages.SetEnd;
import com.paintwar.client.view.pages.Shop;


public class MainWindow extends JFrame {
	
	private ConnexionChoice connexionChoicePage;
	private LogIn logInPage;
	private AccountCreation accountCreationPage;
	private Home homePage;
	private Parameters parametersPage;
	private Collection collectionPage;
	private SetEnd setEndPage;
	private Shop shopPage;
	
	private String playerName;
	
	
	public MainWindow() {
		super();
		addWindowListener(new MyWindowListener());
		setLayout(new BorderLayout());
		setVisible(true);
		
		JPanel contentContainer = new JPanel();
		add(contentContainer, BorderLayout.CENTER);
		
		connexionChoicePage = new ConnexionChoice(this);
		logInPage = new LogIn(this);
		accountCreationPage = new AccountCreation(this);
		contentContainer.add(PageName.CONNEXION_CHOICE.toString(), connexionChoicePage);
		contentContainer.add(PageName.LOG_IN.toString(), logInPage);
		contentContainer.add(PageName.SIGN_IN.toString(), accountCreationPage);
		
		
		
	}
	
	
	
	
	public class MyWindowListener implements WindowListener {
		public void windowActivated(WindowEvent arg0) {
		}
		public void windowClosed(WindowEvent arg0) {
		}
		@Override
		public void windowClosing(WindowEvent arg0) {
			System.exit(0);
		}
		public void windowDeactivated(WindowEvent arg0) {
		}
		public void windowDeiconified(WindowEvent arg0) {
		}
		public void windowIconified(WindowEvent arg0) {
		}
		public void windowOpened(WindowEvent arg0) {
		}
	}
	
}
