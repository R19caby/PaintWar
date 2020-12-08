package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.paintwar.client.view.components.Header;

public class Shop extends JPanel {
	
	private static final long serialVersionUID = 8825220079126392422L;
	private String playerName;
	private JLabel effectiveTitle;
	private JPanel container;
	private JButton borders;
	private JButton avatars;
	private JButton cursors;
	private JButton appearances;
	
	public Shop(String s) {
		super();
		playerName = s;
		setLayout(new BorderLayout());
		
		add(new Header(), BorderLayout.NORTH);
		
		JPanel center = new JPanel();
		add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(10, 10));
		JPanel titlePanel = new JPanel();
		center.add(titlePanel, BorderLayout.NORTH);
		effectiveTitle = new JLabel("Bordures");
		titlePanel.add(effectiveTitle);
		
		container = new JPanel();
		center.add(container, BorderLayout.CENTER);
		container.setLayout(new CardLayout());
		JPanel borderGoods = buildGoodsPanel("Bordure");
		JPanel avatarGoods = buildGoodsPanel("Avatar");
		JPanel cursorGoods = buildGoodsPanel("Curseur");
		JPanel AppearanceGoods = buildGoodsPanel("Texture");
		container.add("Bordures", borderGoods);
		container.add("Avatars", avatarGoods);
		container.add("Curseurs", cursorGoods);
		container.add("Textures", AppearanceGoods);
		
		
		JPanel west = new JPanel();
		add(west, BorderLayout.WEST);
		west.setLayout(new BorderLayout(100, 100));
		west.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		
		JPanel categories = new JPanel();
		west.add(categories, BorderLayout.NORTH);
		categories.setLayout(new GridLayout(0, 1, 10, 10));
		JLabel categoriesTitle = new JLabel("Catégories");
		categories.add(categoriesTitle);
		borders = new JButton("Bordures");
		avatars = new JButton("Avatars");
		cursors = new JButton("Curseurs");
		appearances = new JButton("Textures");
		borders.setEnabled(false);
		categories.add(borders);
		categories.add(avatars);
		categories.add(cursors);
		categories.add(appearances);
		borders.addActionListener(new CategoryChooserListener("Bordures", borders));
		avatars.addActionListener(new CategoryChooserListener("Avatars", avatars));
		cursors.addActionListener(new CategoryChooserListener("Curseurs", cursors));
		appearances.addActionListener(new CategoryChooserListener("Textures", appearances));
		
		JPanel filters = buildFiltersPanel();
		west.add(filters, BorderLayout.CENTER);
		
		JPanel balance = new JPanel();
		west.add(balance, BorderLayout.SOUTH);
		JLabel balanceTitle = new JLabel("Solde");
		balance.add(balanceTitle);
		
		
		
	}
	
	private JPanel buildGoodsPanel(String good) {
		JPanel cardsPanel = new JPanel();
		cardsPanel.setLayout(new GridLayout(0, 5, 10, 10));
		cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel card1 = new JPanel();
		JPanel card2 = new JPanel();
		JPanel card3 = new JPanel();
		JPanel card4 = new JPanel();
		JPanel card5 = new JPanel();
		JPanel card6 = new JPanel();
		JPanel card7 = new JPanel();
		JPanel card8 = new JPanel();
		JPanel card9 = new JPanel();
		JPanel card10 = new JPanel();
		JPanel card11 = new JPanel();
		JLabel title1 = new JLabel(good+"1");
		JLabel title2 = new JLabel(good+"2");
		JLabel title3 = new JLabel(good+"3");
		JLabel title4 = new JLabel(good+"4");
		JLabel title5 = new JLabel(good+"5");
		JLabel title6 = new JLabel(good+"6");
		JLabel title7 = new JLabel(good+"7");
		JLabel title8 = new JLabel(good+"8");
		JLabel title9 = new JLabel(good+"9");
		JLabel title10 = new JLabel(good+"10");
		JLabel title11 = new JLabel(good+"11");
		card1.add(title1);
		card2.add(title2);
		card3.add(title3);
		card4.add(title4);
		card5.add(title5);
		card6.add(title6);
		card7.add(title7);
		card8.add(title8);
		card9.add(title9);
		card10.add(title10);
		card11.add(title11);
		
		card1.setMaximumSize(new Dimension(100, 75));
		card2.setMaximumSize(new Dimension(100, 75));
		card3.setMaximumSize(new Dimension(100, 75));
		card4.setMaximumSize(new Dimension(100, 75));
		card5.setMaximumSize(new Dimension(100, 75));
		card6.setMaximumSize(new Dimension(100, 75));
		card7.setMaximumSize(new Dimension(100, 75));
		card8.setMaximumSize(new Dimension(100, 75));
		card9.setMaximumSize(new Dimension(100, 75));
		card10.setMaximumSize(new Dimension(100, 75));
		card11.setMaximumSize(new Dimension(100, 75));
		card1.setMinimumSize(new Dimension(100, 25));
		card2.setMinimumSize(new Dimension(100, 25));
		card3.setMinimumSize(new Dimension(100, 25));
		card4.setMinimumSize(new Dimension(100, 25));
		card5.setMinimumSize(new Dimension(100, 25));
		card6.setMinimumSize(new Dimension(100, 25));
		card7.setMinimumSize(new Dimension(100, 25));
		card8.setMinimumSize(new Dimension(100, 25));
		card9.setMinimumSize(new Dimension(100, 25));
		card10.setMinimumSize(new Dimension(100, 25));
		card11.setMinimumSize(new Dimension(100, 25));
		
		card1.setBackground(Color.white);
		card2.setBackground(Color.white);
		card3.setBackground(Color.white);
		card4.setBackground(Color.white);
		card5.setBackground(Color.white);
		card6.setBackground(Color.white);
		card7.setBackground(Color.white);
		card8.setBackground(Color.white);
		card9.setBackground(Color.white);
		card10.setBackground(Color.white);
		card11.setBackground(Color.white);
		card1.setBorder(BorderFactory.createLineBorder(Color.black));
		card2.setBorder(BorderFactory.createLineBorder(Color.black));
		card3.setBorder(BorderFactory.createLineBorder(Color.black));
		card4.setBorder(BorderFactory.createLineBorder(Color.black));
		card5.setBorder(BorderFactory.createLineBorder(Color.black));
		card6.setBorder(BorderFactory.createLineBorder(Color.black));
		card7.setBorder(BorderFactory.createLineBorder(Color.black));
		card8.setBorder(BorderFactory.createLineBorder(Color.black));
		card9.setBorder(BorderFactory.createLineBorder(Color.black));
		card10.setBorder(BorderFactory.createLineBorder(Color.black));
		card11.setBorder(BorderFactory.createLineBorder(Color.black));

		cardsPanel.add(card1);
		cardsPanel.add(card2);
		cardsPanel.add(card3);
		cardsPanel.add(card4);
		cardsPanel.add(card5);
		cardsPanel.add(card6);
		cardsPanel.add(card7);
		cardsPanel.add(card8);
		cardsPanel.add(card9);
		cardsPanel.add(card10);
		cardsPanel.add(card11);
		
		return cardsPanel;
	}
	
	private JPanel buildFiltersPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1, 10, 10));
		JLabel filtersTitle = new JLabel("Filtres");
		panel.add(filtersTitle);
		
		JCheckBox filter1 = new JCheckBox("Filtre1");
		JCheckBox filter2 = new JCheckBox("Filtre2");
		JCheckBox filter3 = new JCheckBox("Filtre3");
		JCheckBox filter4 = new JCheckBox("Filtre4");
		JCheckBox filter5 = new JCheckBox("Filtre5");
		
		panel.add(filter1);
		panel.add(filter2);
		panel.add(filter3);
		panel.add(filter4);
		panel.add(filter5);
		
		return panel;
	}
	
	
	
	public class CategoryChooserListener implements ActionListener {
		private String category;
		private JButton button;

		public CategoryChooserListener(String category, JButton button) {
			this.category = category;
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cl = (CardLayout) (container.getLayout());
            cl.show(container, category);
			effectiveTitle.setText(category);
			borders.setEnabled(true);
			avatars.setEnabled(true);
			cursors.setEnabled(true);
			appearances.setEnabled(true);
			button.setEnabled(false);
		}

	}

}
