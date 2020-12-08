package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.components.ButtonFactory;

public class Parameters extends JPanel {
	private static final long serialVersionUID = -2023081651434102668L;
	private MainWindow manager;
	private JLabel pageLabel;
	private JPanel parametersContainer;
	private List<JButton> buttonsList = new ArrayList<>();
	private static final Dimension DIMENSION_PARAM_FIELD = new Dimension(600, 75);
	private static final Dimension DIMENSION_BUTTON = new Dimension(100, 40);

	public Parameters(MainWindow parent) {
		super();
		manager = parent;
		setLayout(new BorderLayout(10, 50));
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		add(north, BorderLayout.NORTH);
		add(south, BorderLayout.SOUTH);
		add(center, BorderLayout.CENTER);

		JLabel parametersTitle = new JLabel("Paramètres");
		incrFontSize(20, parametersTitle);
		JPanel titlePanel = new JPanel();
		titlePanel.add(parametersTitle);
		north.add(titlePanel);

		ButtonFactory validateButton = new ButtonFactory("Valider", PageName.PARAMETERS, manager);
		south.add(validateButton);

		JPanel categoryPanel = new JPanel();
		categoryPanel.setLayout(new GridLayout(0, 1));
		pageLabel = new JLabel("Gameplay");
		incrFontSize(7, pageLabel);
		JPanel pageLabelPanel = new JPanel();
		parametersContainer = new JPanel();
		parametersContainer.setLayout(new CardLayout());

		for (Category cat : Category.values()) {
			String name = cat + "";
			parametersContainer.add(buildParam(name), name);
			JButton button = new JButton(name);
			if (cat == Category.GAMEPLAY) {
				button.setEnabled(false);
			}
			button.setPreferredSize(DIMENSION_BUTTON);
			button.addActionListener(new CategoryChooserListener());
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(button);
			categoryPanel.add(buttonPanel);
			buttonsList.add(button);
		}

		JScrollPane scrollPane = new JScrollPane(parametersContainer, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		parametersContainer.setAutoscrolls(true);
		scrollPane.setPreferredSize(new Dimension(800, 600));
		JPanel parametersPanel = new JPanel();
		parametersPanel.setLayout(new BoxLayout(parametersPanel, BoxLayout.Y_AXIS));

		pageLabelPanel.add(pageLabel);
		parametersPanel.add(pageLabelPanel);
		parametersPanel.add(scrollPane);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setPreferredSize(new Dimension(800, 600));
		splitPane.setLeftComponent(categoryPanel);
		splitPane.setRightComponent(parametersPanel);
		center.add(splitPane);
	}
	
	private enum Category {
		GAMEPLAY("Gameplay"), ACCOUNT("Account"),AUDIO("Audio"), VIDEO("Vidéo") ;
		final String name;
		Category(String name) {
			this.name = name;
		}
		
		public String toString() {
			return name;
		}
	}

	private JPanel buildParam(String category) {
		JPanel parameters = new JPanel();
		parameters.setLayout(new GridLayout(0, 1));
		for (int index = 0; index < 9; index ++) {
			JPanel paramPanel = new JPanel();
			ParamField param = new ParamField("Param"+index+category);
			paramPanel.add(param);
			parameters.add(paramPanel);
		}
		return parameters;
	}

	private void incrFontSize(int deltaSize, Component component) {
		component.setFont(new Font(component.getFont().getName(),component.getFont().getSize(),component.getFont().getSize() + deltaSize));
	}
	
	private class ParamField extends JLabel {
		public ParamField(String text) {
			super(text);
			setBorder(BorderFactory.createLineBorder(Color.black));
			setPreferredSize(DIMENSION_PARAM_FIELD);
			incrFontSize(4, this);
		}
	}

	private class CategoryChooserListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton targetButton = (JButton) e.getSource();
			String targetText = targetButton.getText(); 
			CardLayout cl = (CardLayout) (parametersContainer.getLayout());
			cl.show(parametersContainer, targetText);
			pageLabel.setText(targetText);
			for (JButton button : buttonsList) {
				button.setEnabled(true);
			}
			targetButton.setEnabled(false);
		}

	}
}
