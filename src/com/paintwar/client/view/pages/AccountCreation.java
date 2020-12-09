package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.components.ButtonFactory;

public class AccountCreation extends JPanel {
	
	private static final long serialVersionUID = 6599217595831008888L;
	private MainWindow manager;
	private char defaultEchoChar;
	private static final Dimension DIMENSION_NICKNAME = new Dimension(415, 30);
	private static final Dimension DIMENSION_ADDRESS = new Dimension(415, 30);
	private static final Dimension DIMENSION_PASSWORD = new Dimension(200, 30);
	
	public AccountCreation(MainWindow parent) {
		super();
		JPanel myPanel = new JPanel()
		{
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                ImageIcon m = null;
                try
                {
                	m = new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir") + "/src/graphicResources/paint.png")));
                }
                catch (IOException e)
                {
                	e.printStackTrace();
                }
                Image monImage = m.getImage();
                g.drawImage(monImage, 0, 0,this);
            }
        };
		manager = parent;
		setLayout(new BorderLayout(0, 50));
		setOpaque(false);
		
		JPanel shortcuts = new JPanel();
		shortcuts.setLayout(new FlowLayout(FlowLayout.RIGHT));
		shortcuts.add(ButtonFactory.getInstance().getParameterButton(manager));
		shortcuts.add(ButtonFactory.getInstance().getButton("Back", PageName.CONNEXION_CHOICE, manager));
		add(shortcuts, BorderLayout.NORTH);
		shortcuts.setAlignmentX(RIGHT_ALIGNMENT);
		
		JPanel center = new JPanel();
		JPanel logoDataPanel = new JPanel(new BorderLayout(0, 100));
		JPanel logoPanel = new JPanel();
		JPanel dataToEnter = new JPanel();
		JPanel nicknameAddressPanel = new JPanel();
		JPanel nicknamePanel = new JPanel();
		JPanel addressPanel = new JPanel();
		JPanel passwordsCheckBoxesPanel = new JPanel();
		JPanel passwordsPanel = new JPanel();
		JPanel checkBoxesPanel = new JPanel();
		JPanel enterPanel = new JPanel();
		JPanel confirmPanel = new JPanel();
		JPanel showFirstPanel = new JPanel();
		JPanel showSecondPanel = new JPanel();
		
		add(center, BorderLayout.CENTER);
		center.add(logoDataPanel);
		logoDataPanel.add(logoPanel, BorderLayout.NORTH);
		logoDataPanel.add(dataToEnter, BorderLayout.CENTER);
		dataToEnter.add(nicknameAddressPanel);
		nicknameAddressPanel.add(nicknamePanel);
		nicknameAddressPanel.add(addressPanel);
		dataToEnter.add(passwordsCheckBoxesPanel);
		passwordsCheckBoxesPanel.add(passwordsPanel);
		passwordsCheckBoxesPanel.add(checkBoxesPanel);
		passwordsPanel.add(enterPanel);
		passwordsPanel.add(confirmPanel);
		checkBoxesPanel.add(showFirstPanel);
		checkBoxesPanel.add(showSecondPanel);
		
		dataToEnter.setLayout(new BoxLayout(dataToEnter, BoxLayout.Y_AXIS));
		nicknameAddressPanel.setLayout(new BoxLayout(nicknameAddressPanel, BoxLayout.Y_AXIS));
		passwordsCheckBoxesPanel.setLayout(new BoxLayout(passwordsCheckBoxesPanel, BoxLayout.Y_AXIS));
		
		
		center.setOpaque(false);
		logoDataPanel.setOpaque(false);
		nicknamePanel.setOpaque(false);
		logoPanel.setOpaque(false);
		dataToEnter.setOpaque(false);
		nicknameAddressPanel.setOpaque(false);
		addressPanel.setOpaque(false);
		
		JLabel logo = new JLabel("Paint War");
		logo.setFont(new Font(logo.getFont().getName(), logo.getFont().getSize(), logo.getFont().getSize() + 100));
		logoPanel.add(logo);
		
		JTextField nickname = new JTextField();
		nicknamePanel.add(nickname);
		nickname.setText("Pseudo");
		nickname.addMouseListener(new ResetSimpleText(nickname, "Pseudo"));
		nickname.setPreferredSize(DIMENSION_NICKNAME);
		
		JTextField address = new JTextField();
		addressPanel.add(address);
		address.setText("Adresse mail");
		address.addMouseListener(new ResetSimpleText(address, "Adresse mail"));
		address.setPreferredSize(DIMENSION_ADDRESS);
		
		JPasswordField enter = new JPasswordField();
		enterPanel.add(enter);
		enter.setText("Entrez le mot de passe");
		defaultEchoChar = enter.getEchoChar();
		enter.setEchoChar((char) 0);
		enter.setPreferredSize(DIMENSION_PASSWORD);
		
		JCheckBox showFirst = new JCheckBox("Afficher le mot de passe");
		showFirst.addActionListener(new HideText(showFirst, enter, "Entrez le mot de passe"));
		enter.addMouseListener(new ResetPasswordText(enter, "Entrez le mot de passe", showFirst));
		showFirstPanel.add(showFirst);
		
		JPasswordField confirm = new JPasswordField();
		confirmPanel.add(confirm);
		confirm.setText("Confirmez le mot de passe");
		confirm.setEchoChar((char) 0);
		confirm.setPreferredSize(DIMENSION_PASSWORD);
		
		JCheckBox showSecond = new JCheckBox("Afficher la confirmation");
		showSecond.addActionListener(new HideText(showSecond, confirm, "Confirmez le mot de passe"));
		confirm.addMouseListener(new ResetPasswordText(confirm, "Confirmez le mot de passe", showSecond));
		showSecondPanel.add(showSecond);

		
		JPanel south = new JPanel();
		add(south, BorderLayout.SOUTH);
		JButton validate = ButtonFactory.getInstance().getButton("Valider", PageName.HOME, parent);
		south.add(validate, BorderLayout.SOUTH);
		
		
	}
	
	/*
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir") + "/src/graphicResources/paint.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(icon != null)
			g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
		
	*/
	
	
	public class HideText implements ActionListener {
		
		private JCheckBox checkbox;
		private JPasswordField associatedField;
		private String printing;
		
		public HideText(JCheckBox box, JPasswordField field, String text) {
			checkbox = box;
			associatedField = field;
			printing = text;
		}
		
		public void actionPerformed(ActionEvent e) {
			if (checkbox.isSelected()) {
				associatedField.setEchoChar((char) 0);
			} else {
				if (!associatedField.getText().equals(printing))
				associatedField.setEchoChar(defaultEchoChar);
			}
		}
		
	}
	
	public class ResetSimpleText implements MouseListener {
		
		private JTextField associatedField;
		private String printing;
		
		
		public ResetSimpleText(JTextField field, String text) {
			associatedField = field;
			printing = text;
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (associatedField.getText().equals("") || associatedField.getText().equals(printing)) {
				associatedField.setText("");
			}
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			if (associatedField.getText().equals("")) {
				this.associatedField.setText(printing);
			}
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}

	public class ResetPasswordText implements MouseListener {
		
		private JPasswordField associatedField;
		private String printing;
		private JCheckBox checkbox;
		
		public ResetPasswordText(JPasswordField field, String text, JCheckBox box) {
			associatedField = field;
			printing = text;
			checkbox = box;
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (associatedField.getText().equals("") || associatedField.getText().equals(printing)) {
				associatedField.setText("");
				associatedField.setEchoChar(defaultEchoChar);
				if (checkbox.isSelected()) {
					associatedField.setEchoChar((char) 0);
				}
			}
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			if (associatedField.getText().equals("")) {
				associatedField.setText(printing);
				associatedField.setEchoChar((char) 0);
			}
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}
	
}









