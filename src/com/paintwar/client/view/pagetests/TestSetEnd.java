package com.paintwar.client.view.pagetests;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.paintwar.client.view.pages.AccountCreation;
import com.paintwar.client.view.pages.SetEnd;

public class TestSetEnd {
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(new Dimension(500, 500));
		window.add(new SetEnd());
		window.setVisible(true);
	}

}