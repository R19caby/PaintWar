package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
		this.setLayout(new BorderLayout(0, 50));

		JPanel shortcuts = new JPanel();
		shortcuts.setOpaque(false);
		shortcuts.setLayout(new FlowLayout(FlowLayout.RIGHT));
		shortcuts.add(ButtonFactory.getInstance().getParameterButton(manager));
		shortcuts.add(ButtonFactory.getInstance().getButton("Retour", PageName.CONNEXION_CHOICE, manager));
		add(shortcuts, BorderLayout.NORTH);
		shortcuts.setAlignmentX(RIGHT_ALIGNMENT);

		JPanel center = new JPanel();
		center.setOpaque(false);
		add(center, BorderLayout.CENTER);

		JPanel logoDataPanel = new JPanel(new BorderLayout(0, 100));
		logoDataPanel.setOpaque(false);
		center.add(logoDataPanel);

		JLabel logo = new JLabel("Paint War");
		logo.setFont(new Font(logo.getFont().getName(), logo.getFont().getSize(), logo.getFont().getSize() + 100));
		JPanel logoPanel = new JPanel();
		logoPanel.setOpaque(false);
		logoPanel.add(logo);
		logoDataPanel.add(logoPanel, BorderLayout.NORTH);

		JPanel loginData = new JPanel();
		loginData.setOpaque(false);
		logoDataPanel.add(loginData, BorderLayout.CENTER);
		loginData.setLayout(new BoxLayout(loginData, BoxLayout.Y_AXIS));
		JTextField userID = new JTextField();
		JPanel userIDPanel = new JPanel();
		userIDPanel.setOpaque(false);
		userIDPanel.add(userID);
		loginData.add(userIDPanel);
		userID.setPreferredSize(new Dimension(300, 20));
		userID.setMaximumSize(new Dimension(300, 20));
		userID.setText("Pseudo/Adresse mail");
		userID.addMouseListener(new ResetSimpleText(userID, "Pseudo/Adresse mail"));
		JPasswordField mdp = new JPasswordField();
		JPanel mdpPanel = new JPanel();
		mdp.setOpaque(false);
		mdpPanel.add(mdp);
		loginData.add(mdpPanel);
		mdp.setPreferredSize(new Dimension(300, 20));
		mdp.setMaximumSize(new Dimension(300, 20));
		defaultEchoChar = mdp.getEchoChar();
		mdp.setEchoChar((char) 0);
		mdp.setText("Mot de passe");
		JCheckBox showPassword = new JCheckBox("Afficher le mot de passe");
		JPanel showPasswordPanel = new JPanel();
		showPasswordPanel.setOpaque(false);
		showPasswordPanel.add(showPassword);
		loginData.add(showPasswordPanel);
		mdp.addMouseListener(new ResetPasswordText(mdp, "Mot de passe", showPassword));
		showPassword.addActionListener(new HideText(showPassword, mdp, "Mot de passe"));

		JButton validate = new JButton("Valider");
		JPanel validatePanel = new JPanel();
		validatePanel.setOpaque(false);
		add(validatePanel, BorderLayout.SOUTH);
		validatePanel.add(validate);

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
