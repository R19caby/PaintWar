package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.components.BackButton;
import com.paintwar.client.view.components.ButtonFactory;
import com.paintwar.client.view.components.ParametersButton;

public class AccountCreation extends JPanel {
	
	private static final long serialVersionUID = 6599217595831008888L;
	private MainWindow manager;
	private char defaultEchoChar;
	
	public AccountCreation(MainWindow parent) {
		super();
		manager = parent;
		setLayout(new BorderLayout());
		
		JPanel shortcuts = new JPanel();
		shortcuts.setLayout(new FlowLayout(FlowLayout.RIGHT));
		shortcuts.add(new ParametersButton(manager));
		shortcuts.add(new BackButton(PageName.CONNEXION_CHOICE, manager));
		add(shortcuts, BorderLayout.NORTH);
		shortcuts.setAlignmentX(RIGHT_ALIGNMENT);
		
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
		add(center, BorderLayout.CENTER);
		
		JLabel logo = new JLabel("Logo");
		center.add(logo);
		logo.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel dataToEnter = new JPanel();
		center.add(dataToEnter);
		dataToEnter.setLayout(new BoxLayout(dataToEnter, BoxLayout.PAGE_AXIS));
		JTextField nickname = new JTextField();
		dataToEnter.add(nickname);
		nickname.setPreferredSize(new Dimension(200, 20));
		nickname.setMaximumSize(new Dimension(200, 20));
		nickname.setText("Pseudo");
		nickname.addMouseListener(new ResetSimpleText(nickname, "Pseudo"));
		JTextField address = new JTextField();
		dataToEnter.add(address);
		address.setPreferredSize(new Dimension(200, 20));
		address.setMaximumSize(new Dimension(200, 20));
		address.setText("Adresse mail");
		address.addMouseListener(new ResetSimpleText(address, "Adresse mail"));
		
		JPanel passwords = new JPanel();
		dataToEnter.add(passwords);
		passwords.setMaximumSize(new Dimension(800, 50));
		passwords.setLayout(new GridLayout(2, 2));
		JPanel enterP = new JPanel();
		JPasswordField enter = new JPasswordField();
		enterP.add(enter);
		enter.setPreferredSize(new Dimension(200, 20));
		enter.setText("Entrez le mot de passe");
		defaultEchoChar = enter.getEchoChar();
		enter.setEchoChar((char) 0);
		JCheckBox showFirst = new JCheckBox("Afficher le mot de passe");
		showFirst.addActionListener(new HideText(showFirst, enter, "Entrez le mot de passe"));
		enter.addMouseListener(new ResetPasswordText(enter, "Entrez le mot de passe", showFirst));
		
		JPanel confirmP = new JPanel();
		JPasswordField confirm = new JPasswordField();
		confirmP.add(confirm);
		confirm.setPreferredSize(new Dimension(200, 20));
		confirm.setText("Confirmez le mot de passe");
		confirm.setEchoChar((char) 0);
		JCheckBox showSecond = new JCheckBox("Afficher la confirmation");
		showSecond.addActionListener(new HideText(showSecond, confirm, "Confirmez le mot de passe"));
		confirm.addMouseListener(new ResetPasswordText(confirm, "Confirmez le mot de passe", showSecond));
		

		passwords.add(enterP);
		passwords.add(confirmP);
		passwords.add(showFirst);
		passwords.add(showSecond);
		
		JPanel south = new JPanel();
		add(south, BorderLayout.SOUTH);
		ButtonFactory validate = new ButtonFactory("Valider", PageName.HOME, parent);
		south.add(validate, BorderLayout.SOUTH);
		
		
	}
	
	
	
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









