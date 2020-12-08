package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.components.ButtonFactory;

public class LogIn extends JPanel {

	private static final long serialVersionUID = 7402238299632129019L;
	private MainWindow manager;
	private char defaultEchoChar;
	
	public LogIn(MainWindow parent) {
		super();
		manager = parent;
		this.setLayout(new BorderLayout());
		
		JPanel shortcuts = new JPanel();
		shortcuts.setLayout(new FlowLayout(FlowLayout.RIGHT));
		shortcuts.add(ButtonFactory.getInstance().getButton("Paramètres", PageName.PARAMETERS, manager));
		shortcuts.add(ButtonFactory.getInstance().getButton("Retour", PageName.CONNEXION_CHOICE, manager));
		add(shortcuts, BorderLayout.NORTH);
		shortcuts.setAlignmentX(RIGHT_ALIGNMENT);
		
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
		add(center, BorderLayout.CENTER);
		
		JLabel logo = new JLabel("Logo");
		center.add(logo);
		logo.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel loginData = new JPanel();
		center.add(loginData);
		loginData.setLayout(new BoxLayout(loginData, BoxLayout.PAGE_AXIS));
		JTextField userID = new JTextField();
		loginData.add(userID);
		userID.setPreferredSize(new Dimension(300, 20));
		userID.setMaximumSize(new Dimension(300, 20));
		userID.setText("Pseudo/Adresse mail");
		userID.addMouseListener(new ResetSimpleText(userID, "Pseudo/Adresse mail"));
		JPasswordField mdp = new JPasswordField();
		loginData.add(mdp);
		mdp.setPreferredSize(new Dimension(300, 20));
		mdp.setMaximumSize(new Dimension(300, 20));
		defaultEchoChar = mdp.getEchoChar();
		mdp.setEchoChar((char) 0);
		mdp.setText("Mot de passe");
		JCheckBox showPassword = new JCheckBox("Afficher le mot de passe");
		loginData.add(showPassword);
		mdp.addMouseListener(new ResetPasswordText(mdp, "Mot de passe", showPassword));
		showPassword.addActionListener(new HideText(showPassword, mdp, "Mot de passe"));
		
		
		JButton validate = new JButton("Valider");
		add(validate, BorderLayout.SOUTH);
		validate.setPreferredSize(new Dimension(100, 50));
		validate.setMaximumSize(new Dimension(100, 50));
		
		
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
