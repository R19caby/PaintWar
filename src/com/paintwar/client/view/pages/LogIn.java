package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.paintwar.client.view.components.BackButton;
import com.paintwar.client.view.components.ParametersButton;

public class LogIn extends JPanel {

	private static final long serialVersionUID = 7402238299632129019L;
	
	public LogIn() {
		super();
		this.setLayout(new BorderLayout());
		
		JPanel shortcuts = new JPanel();
		shortcuts.setLayout(new FlowLayout(FlowLayout.RIGHT));
		shortcuts.add(new ParametersButton());
		shortcuts.add(new BackButton(PageName.CONNEXION_CHOICE));
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
		userID.setPreferredSize(new Dimension(200, 20));
		userID.setMaximumSize(new Dimension(200, 20));
		userID.setText("Pseudo/Adresse mail");
		userID.addMouseListener(new ResetText(userID, "Pseudo/Adresse mail"));
		JPasswordField mdp = new JPasswordField();
		loginData.add(mdp);
		mdp.setPreferredSize(new Dimension(200, 20));
		mdp.setMaximumSize(new Dimension(200, 20));
		mdp.setText("Mot de passe");
		mdp.addMouseListener(new ResetText(mdp, "Mot de passe"));
		
		
		JButton validate = new JButton("Valider");
		add(validate, BorderLayout.SOUTH);
		validate.setPreferredSize(new Dimension(100, 50));
		validate.setMaximumSize(new Dimension(100, 50));
		
		
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
