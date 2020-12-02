package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		center.add(container);
		container.setLayout(new CardLayout());
		JPanel borderGoods = new JPanel();
		container.add(borderGoods);
		JPanel avatarGoods = new JPanel();
		container.add(avatarGoods);
		JPanel cursorGoods = new JPanel();
		container.add(cursorGoods);
		JPanel AppearanceGoods = new JPanel();
		container.add(AppearanceGoods);
		
		
		JPanel west = new JPanel();
		add(west, BorderLayout.WEST);
		west.setLayout(new BorderLayout(100, 100));
		west.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		
		JPanel categories = new JPanel();
		west.add(categories, BorderLayout.NORTH);
		categories.setLayout(new BoxLayout(categories, BoxLayout.PAGE_AXIS));
		JLabel categoriesTitle = new JLabel("Catégories");
		categories.add(categoriesTitle);
		borders = new JButton("Bordures");
		categories.add(borders);
		borders.setEnabled(false);
		borders.addActionListener(new CategoryChooserListener("Bordures", borders));
		avatars = new JButton("Avatars");
		categories.add(avatars);
		avatars.addActionListener(new CategoryChooserListener("Avatars", avatars));
		cursors = new JButton("Curseurs");
		categories.add(cursors);
		cursors.addActionListener(new CategoryChooserListener("Curseurs", cursors));
		appearances = new JButton("Textures");
		categories.add(appearances);
		appearances.addActionListener(new CategoryChooserListener("Textures", appearances));
		
		JPanel filters = new JPanel();
		west.add(filters, BorderLayout.CENTER);
		JLabel filtersTitle = new JLabel("Filtres");
		filters.add(filtersTitle);
		
		JPanel balance = new JPanel();
		west.add(balance, BorderLayout.SOUTH);
		JLabel balanceTitle = new JLabel("Solde");
		balance.add(balanceTitle);
		
		
		
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
