package com.paintwar.client.view.pagetests;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.paintwar.client.view.pages.Codex;

public class TestCodex {
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(new Dimension(1920, 1080));
		window.add(new Codex(null));
		window.setVisible(true);
		window.setDefaultCloseOperation(3);
	}

}
