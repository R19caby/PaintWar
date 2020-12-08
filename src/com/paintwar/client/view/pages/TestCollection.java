package com.paintwar.client.view.pages;

import java.awt.Dimension;

import javax.swing.JFrame;

public class TestCollection {
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(new Dimension(1080, 720));
		window.add(new Collection("Player1"));
		window.setVisible(true);
	}

}
