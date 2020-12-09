package com.paintwar.client.view.pagetests;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.paintwar.client.view.pages.Battlepass;

public class TestBattlepass {
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(new Dimension(2000, 1000));
		window.add(new Battlepass("player", null));
		window.setVisible(true);
		window.setDefaultCloseOperation(3);
	}
	

}
