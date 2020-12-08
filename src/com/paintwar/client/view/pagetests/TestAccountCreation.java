package com.paintwar.client.view.pagetests;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.paintwar.client.view.pages.AccountCreation;

public class TestAccountCreation {
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(new Dimension(500, 500));
		window.add(new AccountCreation(null));
		window.setVisible(true);
	}

}
