package com.paintwar.client.view.pagetests;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.paintwar.client.view.pages.LogIn;

public class TestLogIn {
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(new Dimension(500, 500));
		window.add(new LogIn(null));
		window.setVisible(true);
	}

}
