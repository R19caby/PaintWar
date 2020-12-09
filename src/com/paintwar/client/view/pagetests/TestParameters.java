package com.paintwar.client.view.pagetests;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.paintwar.client.view.pages.ConnexionChoice;
import com.paintwar.client.view.pages.Parameters;

public class TestParameters {
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(new Dimension(500, 500));
		window.add(new Parameters(new ConnexionChoice(null), null));
		window.setDefaultCloseOperation(3);
		window.setVisible(true);
	}
	
}
