package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.components.BackButton;
import com.paintwar.client.view.components.ButtonFactory;
import com.paintwar.client.view.components.ParametersButton;

public class AccountCreation extends JPanel {
	
	private static final long serialVersionUID = 6599217595831008888L;
	private MainWindow manager;
	
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
		nickname.addMouseListener(new ResetText(nickname, "Pseudo"));
		JTextField address = new JTextField();
		dataToEnter.add(address);
		address.setPreferredSize(new Dimension(200, 20));
		address.setMaximumSize(new Dimension(200, 20));
		address.setText("Adresse mail");
		address.addMouseListener(new ResetText(address, "Adresse mail"));
		
		JPanel passwords = new JPanel();
		dataToEnter.add(passwords);
		passwords.setLayout(new FlowLayout());
		JTextField enter = new JTextField();
		passwords.add(enter);
		enter.setPreferredSize(new Dimension(200, 20));
		enter.setText("Entrez le mot de passe");
		enter.addMouseListener(new ResetText(enter, "Entrez le mot de passe"));
		JTextField confirm = new JTextField();
		passwords.add(confirm);
		confirm.setPreferredSize(new Dimension(200, 20));
		confirm.setText("Confirmez le mot de passe");
		confirm.addMouseListener(new ResetText(confirm, "Confirmez le mot de passe"));
		
		JPanel south = new JPanel();
		add(south, BorderLayout.SOUTH);
		ButtonFactory validate = new ButtonFactory("Valider", PageName.HOME, parent);
		south.add(validate, BorderLayout.SOUTH);
		
		
	}
	
	public class ResetText implements MouseListener {
		
		private JTextField associatedField;
		private String printing;
		
		public ResetText(JTextField field, String text) {
			this.associatedField = field;
			this.printing = text;
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			associatedField.setText("");
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
	
}









