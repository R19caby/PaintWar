package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.components.ButtonFactory;
import com.paintwar.client.view.components.Header;

public class Collection extends JPanel {

	private static final long serialVersionUID = -916442714757214449L;
	private final static String AVATAR = "Avatars";
	private final static String BORDER = "Bordures";
	private final static String CURSOR = "Curseurs";
	private final static String TEXTURE = "Textures";
	private static final Dimension DIMENSION_CODEX = new Dimension(200, 60);
	private final static Dimension DIMENSION_FILTERS = new Dimension(200, 40);
	private final static Dimension DIMENSION_ARTICLE = new Dimension(300, 300);
	private final static Dimension DIMENSION_ITEM_CARD = new Dimension(1400, 1800);
	private final static Dimension DIMENSION_ITEM_GRID = new Dimension(1400, 1800);

	private String playerName;
	private JLabel effectiveTitle;
	private JPanel container;
	private JButton avatarBtn;
	private JButton borderBtn;
	private JButton cursorBtn;
	private JButton textureBtn;

	private MainWindow manager;

	public Collection(String s, MainWindow parent) {
		super();
		manager = parent;
		this.playerName = s;
		this.setLayout(new BorderLayout());

		Header header = new Header(manager);
		this.add(header, BorderLayout.NORTH);

		JPanel center = new JPanel();
		JPanel titlePanel = new JPanel();
		container = new JPanel();
		JPanel west = new JPanel();
		JPanel categories = new JPanel();
		
		center.setOpaque(false);
		titlePanel.setOpaque(false);
		container.setOpaque(false);
		west.setOpaque(false);
		categories.setOpaque(false);
		
		
		
		add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(10, 10));
		center.add(titlePanel, BorderLayout.NORTH);
		effectiveTitle = new JLabel(AVATAR);
		effectiveTitle.setFont(new Font(effectiveTitle.getFont().getName(), effectiveTitle.getFont().getSize(),
				effectiveTitle.getFont().getSize() + 10));
		titlePanel.add(effectiveTitle);
		
		
		center.add(container);
		container.setLayout(new CardLayout());
		JScrollPane avatarPane = buildItemPanel(AVATAR);
		container.add(avatarPane, AVATAR);

		JScrollPane borderPane = buildItemPanel(BORDER);
		container.add(borderPane, BORDER);

		JScrollPane cursorPane = buildItemPanel(CURSOR);
		container.add(cursorPane, CURSOR);

		JScrollPane texturePane = buildItemPanel(TEXTURE);
		container.add(texturePane, TEXTURE);
		
		
		add(west, BorderLayout.WEST);
		west.setLayout(new BorderLayout(0, 200));
		west.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		
		
		JButton codex = ButtonFactory.getInstance().getButton("Codex", PageName.CODEX, manager);
		codex.setPreferredSize(DIMENSION_CODEX);
		west.add(codex, BorderLayout.NORTH);
		
		
		west.add(categories, BorderLayout.CENTER);
		categories.setLayout(new GridLayout(0, 1, 25, 25));
		// JLabel categoriesTitle = new JLabel("Catégories");
		// categoriesTitle.setAlignmentX(CENTER_ALIGNMENT);
		// categories.add(categoriesTitle);
		avatarBtn = new JButton(AVATAR);
		avatarBtn.setAlignmentX(CENTER_ALIGNMENT);
		categories.add(avatarBtn);
		avatarBtn.setEnabled(false);
		avatarBtn.addActionListener(new CategoryListener(AVATAR, avatarBtn));
		borderBtn = new JButton(BORDER);
		borderBtn.setAlignmentX(CENTER_ALIGNMENT);
		categories.add(borderBtn);
		borderBtn.addActionListener(new CategoryListener(BORDER, borderBtn));
		cursorBtn = new JButton(CURSOR);
		cursorBtn.setAlignmentX(CENTER_ALIGNMENT);
		categories.add(cursorBtn);
		cursorBtn.addActionListener(new CategoryListener(CURSOR, cursorBtn));
		textureBtn = new JButton(TEXTURE);
		textureBtn.setAlignmentX(CENTER_ALIGNMENT);
		categories.add(textureBtn);
		textureBtn.addActionListener(new CategoryListener(TEXTURE, textureBtn));
		
		
		JPanel filterPanel = buildFilterPanel();
		filterPanel.setOpaque(false);
		west.add(filterPanel, BorderLayout.SOUTH);

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

	private class CardArticle extends JPanel {
		CardArticle(String title) {
			super();
			JLabel titleLabel = new JLabel(title);
			setPreferredSize(DIMENSION_ARTICLE);
			add(titleLabel);
			setBackground(Color.white);
			setBorder(BorderFactory.createLineBorder(Color.black));

		}
	}

	public JScrollPane buildItemPanel(String cat) {
		JPanel itemCard = new JPanel();
		
		itemCard.setPreferredSize(DIMENSION_ITEM_CARD);
		JScrollPane itemPane = new JScrollPane(itemCard, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		itemPane.getViewport().setOpaque(false);
		itemPane.getVerticalScrollBar().setUnitIncrement(50);
		itemPane.setBorder(null);
		itemPane.setAutoscrolls(true);
		itemPane.setOpaque(false);
		itemCard.setOpaque(false);
		JPanel itemGrid = new JPanel();
		itemGrid.setOpaque(false);
		itemGrid.setLayout(new GridLayout(0, 5, 10, 10));
		itemGrid.setPreferredSize(DIMENSION_ITEM_GRID);
		
		itemCard.add(itemGrid);

		for (int index = 0; index < 22; index++) {
			CardArticle card = new CardArticle(cat + (index+1));
			itemGrid.add(card);
		}
		return itemPane;
	}

	public JPanel buildFilterPanel() {
		JPanel filterPanel = new JPanel();
		filterPanel.setOpaque(false);
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.PAGE_AXIS));
		JLabel filterLabel = new JLabel("Filtrer par :");
		filterLabel.setAlignmentX(CENTER_ALIGNMENT);
		filterPanel.add(filterLabel);
		JComboBox<String> filterType = new JComboBox<>();
		filterType.setPreferredSize(DIMENSION_FILTERS);
		filterPanel.add(filterType);
		filterType.setAlignmentX(CENTER_ALIGNMENT);
		filterType.addItemListener(new SelectType(filterType));
		filterType.addItem("Ordre alphabétique");
		filterType.addItem("Ordre alphabétique inverse");
		filterType.addItem("Date d'acquisition croissante");
		filterType.addItem("Date d'acquisition décroissante");

		JPanel voidPanel = new JPanel();
		voidPanel.setOpaque(false);
		voidPanel.setPreferredSize((new Dimension(200, 100)));
		filterPanel.add(voidPanel);

		return filterPanel;
	}

	public class CategoryListener implements ActionListener {
		private String category;
		private JButton button;

		public CategoryListener(String category, JButton button) {
			this.category = category;
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cl = (CardLayout) (container.getLayout());
			cl.show(container, category);
			effectiveTitle.setText(category);
			avatarBtn.setEnabled(true);
			borderBtn.setEnabled(true);
			cursorBtn.setEnabled(true);
			textureBtn.setEnabled(true);
			button.setEnabled(false);

		}
	}

	public class SelectType implements ItemListener {

		private JComboBox<String> choice;

		public SelectType(JComboBox<String> possibilities) {
			choice = possibilities;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			String newSelected = (String) e.getItem();
			choice.setSelectedItem(newSelected);
			// sort the current category according to the selected filter

		}

	}

	public void addItem(String category, String name, String description, boolean unlocked, String imgSourcePath) {
		// get all the info to create the item and than add it in the good category
	}

}
