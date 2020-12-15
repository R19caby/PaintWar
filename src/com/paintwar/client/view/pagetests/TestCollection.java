package com.paintwar.client.view.pagetests;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.paintwar.client.view.pages.Collection;

public class TestCollection {
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(new Dimension(1080, 720));
		window.add(new Collection("Player1", null));
		window.setVisible(true);
		window.setDefaultCloseOperation(3);
	}

}
