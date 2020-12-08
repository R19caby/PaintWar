package com.paintwar.client.view.pages;

import java.awt.Dimension;

import javax.swing.JFrame;

public class TestCodex {
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(new Dimension(1920, 1080));
		window.add(new Codex());
		window.setVisible(true);
	}

}
