package com.paintwar.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	private static final long serialVersionUID = -1263567319246462529L;
	private ConnexionChoice connexionChoicePage;
	private LogIn logInPage;
	private AccountCreation accountCreationPage;
	private Home homePage;
	private Parameters parametersPage;
	private Collection collectionPage;
	private SetEnd setEndPage;
	private Shop shopPage;
	
	private JPanel contentContainer;
	private String playerName;
	
	public MainWindow() {
		super();
		getContentPane().setLayout(new BorderLayout());
		addWindowListener(new MyWindowListener());
		setVisible(true);
		
		/*
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir") + "/src/graphicResources/paint.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel contentPane = new JLabel(icon) {
			private static final long serialVersionUID = -9148089519644484286L;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
			    if(getIcon() != null)
			    	g.drawImage(((ImageIcon)getIcon()).getImage(), 0, 0, getWidth(), getHeight(), null);
			  }
		};
		setContentPane(contentPane);
		*/
		
		contentContainer = new JPanel();
		getContentPane().add(contentContainer, BorderLayout.CENTER);
		contentContainer.setLayout(new CardLayout());
		
		connexionChoicePage = new ConnexionChoice(this);
		logInPage = new LogIn(this);
		accountCreationPage = new AccountCreation(this);
		parametersPage = new Parameters(this);
		contentContainer.add(PageName.CONNEXION_CHOICE.toString(), connexionChoicePage);
		contentContainer.add(PageName.LOG_IN.toString(), logInPage);
		contentContainer.add(PageName.SIGN_IN.toString(), accountCreationPage);
		contentContainer.add(PageName.PARAMETERS.toString(), parametersPage);
		
		
	}
	
	public JPanel getContentContainer() {
		return contentContainer;
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
	
	public static void main(String[] args) {
		MainWindow window = new MainWindow();
		window.setSize(new Dimension(1800, 900));
	}
	
}
