package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.components.Header;

public class Shop extends JPanel {

	private static final long serialVersionUID = 8825220079126392422L;
	private MainWindow manager;
	private String playerName;
	private JLabel effectiveTitle;
	private JPanel container;
	private JButton borders;
	private JButton avatars;
	private JButton cursors;
	private JButton appearances;
	private static final Dimension DIMENSION_ARTICLES = new Dimension(200, 200);
	private static final Dimension DIMENSION_CONTAINER = new Dimension(1000, 1000);

	public Shop(String s, MainWindow parent) {
		super();
		manager = parent;
		playerName = s;
		setLayout(new BorderLayout());

		add(new Header(manager), BorderLayout.NORTH);

		JPanel center = new JPanel();
		container = new JPanel();
		JPanel west = new JPanel();
		JPanel categories = new JPanel();
		JPanel balance = new JPanel();
		JPanel titlePanel = new JPanel();
		
		center.setOpaque(false);
		container.setOpaque(false);
		west.setOpaque(false);
		categories.setOpaque(false);
		balance.setOpaque(false);
		titlePanel.setOpaque(false);
		
		
		add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(10, 10));
		center.add(titlePanel, BorderLayout.NORTH);
		effectiveTitle = new JLabel("Bordures");
		titlePanel.add(effectiveTitle);

		JScrollPane scroll = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		container.setAutoscrolls(true);
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		container.setPreferredSize(DIMENSION_CONTAINER);
		center.add(scroll, BorderLayout.CENTER);
		container.setLayout(new CardLayout());
		JPanel borderGoods = buildGoodsPanel("Bordure");
		JPanel avatarGoods = buildGoodsPanel("Avatar");
		JPanel cursorGoods = buildGoodsPanel("Curseur");
		JPanel AppearanceGoods = buildGoodsPanel("Texture");
		container.add("Bordures", borderGoods);
		container.add("Avatars", avatarGoods);
		container.add("Curseurs", cursorGoods);
		container.add("Textures", AppearanceGoods);

		add(west, BorderLayout.WEST);
		west.setLayout(new BorderLayout(100, 100));
		west.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

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

		west.add(balance, BorderLayout.SOUTH);
		JLabel balanceTitle = new JLabel("Solde");
		balance.add(balanceTitle);

	}

	private JPanel buildGoodsPanel(String good) {
		JPanel cardsPanel = new JPanel();
		cardsPanel.setOpaque(false);
		cardsPanel.setLayout(new GridLayout(0, 5, 10, 10));
		cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		cardsPanel.setPreferredSize(DIMENSION_CONTAINER);
		JPanel card1Container = new JPanel();
		JPanel card2Container = new JPanel();
		JPanel card3Container = new JPanel();
		JPanel card4Container = new JPanel();
		JPanel card5Container = new JPanel();
		JPanel card6Container = new JPanel();
		JPanel card7Container = new JPanel();
		JPanel card8Container = new JPanel();
		JPanel card9Container = new JPanel();
		JPanel card10Container = new JPanel();
		JPanel card11Container = new JPanel();
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
		card1Container.add(card1);
		card2Container.add(card2);
		card3Container.add(card3);
		card4Container.add(card4);
		card5Container.add(card5);
		card6Container.add(card6);
		card7Container.add(card7);
		card8Container.add(card8);
		card9Container.add(card9);
		card10Container.add(card10);
		card11Container.add(card11);
		
		card1Container.setOpaque(false);
		card2Container.setOpaque(false);
		card3Container.setOpaque(false);
		card4Container.setOpaque(false);
		card5Container.setOpaque(false);
		card6Container.setOpaque(false);
		card7Container.setOpaque(false);
		card8Container.setOpaque(false);
		card9Container.setOpaque(false);
		card10Container.setOpaque(false);
		card11Container.setOpaque(false);

		JLabel title1 = new JLabel(good + "1");
		JLabel title2 = new JLabel(good + "2");
		JLabel title3 = new JLabel(good + "3");
		JLabel title4 = new JLabel(good + "4");
		JLabel title5 = new JLabel(good + "5");
		JLabel title6 = new JLabel(good + "6");
		JLabel title7 = new JLabel(good + "7");
		JLabel title8 = new JLabel(good + "8");
		JLabel title9 = new JLabel(good + "9");
		JLabel title10 = new JLabel(good + "10");
		JLabel title11 = new JLabel(good + "11");
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

		card1.setPreferredSize(DIMENSION_ARTICLES);
		card2.setPreferredSize(DIMENSION_ARTICLES);
		card3.setPreferredSize(DIMENSION_ARTICLES);
		card4.setPreferredSize(DIMENSION_ARTICLES);
		card5.setPreferredSize(DIMENSION_ARTICLES);
		card6.setPreferredSize(DIMENSION_ARTICLES);
		card7.setPreferredSize(DIMENSION_ARTICLES);
		card8.setPreferredSize(DIMENSION_ARTICLES);
		card9.setPreferredSize(DIMENSION_ARTICLES);
		card10.setPreferredSize(DIMENSION_ARTICLES);
		card11.setPreferredSize(DIMENSION_ARTICLES);
//		card1.setMinimumSize(new Dimension(100, 25));
//		card2.setMinimumSize(new Dimension(100, 25));
//		card3.setMinimumSize(new Dimension(100, 25));
//		card4.setMinimumSize(new Dimension(100, 25));
//		card5.setMinimumSize(new Dimension(100, 25));
//		card6.setMinimumSize(new Dimension(100, 25));
//		card7.setMinimumSize(new Dimension(100, 25));
//		card8.setMinimumSize(new Dimension(100, 25));
//		card9.setMinimumSize(new Dimension(100, 25));
//		card10.setMinimumSize(new Dimension(100, 25));
//		card11.setMinimumSize(new Dimension(100, 25));

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

		cardsPanel.add(card1Container);
		cardsPanel.add(card2Container);
		cardsPanel.add(card3Container);
		cardsPanel.add(card4Container);
		cardsPanel.add(card5Container);
		cardsPanel.add(card6Container);
		cardsPanel.add(card7Container);
		cardsPanel.add(card8Container);
		cardsPanel.add(card9Container);
		cardsPanel.add(card10Container);
		cardsPanel.add(card11Container);

		return cardsPanel;
	}

	private JPanel buildFiltersPanel() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(0, 1, 10, 10));
		JLabel filtersTitle = new JLabel("Filtres");
		panel.add(filtersTitle);

		JCheckBox filter1 = new JCheckBox("Filtre1");
		JCheckBox filter2 = new JCheckBox("Filtre2");
		JCheckBox filter3 = new JCheckBox("Filtre3");
		JCheckBox filter4 = new JCheckBox("Filtre4");
		JCheckBox filter5 = new JCheckBox("Filtre5");
		
		filter1.setOpaque(false);
		filter2.setOpaque(false);
		filter3.setOpaque(false);
		filter4.setOpaque(false);
		filter5.setOpaque(false);

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
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image icon = null;
		try {
			icon = ImageIO.read(new File(System.getProperty("user.dir") + "/src/graphicResources/paint_HQ.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(icon != null)
			g.drawImage(icon, 0, 0, getWidth(), getHeight(), null);
		}

}
