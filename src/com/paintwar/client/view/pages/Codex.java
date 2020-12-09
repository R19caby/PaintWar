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

public class Codex extends JPanel {

	private static final long serialVersionUID = -7550865054233894692L;
	private final static String ITEM = "Éléments de cosmétique";
	private final static String AVATAR = "Avatars";
	private final static String BORDER = "Bordures";
	private final static String CURSOR = "Curseurs";
	private final static String TEXTURE = "Textures";
	private final static String BIGITEM = "Grands éléments";
	private final static String SPELL = "Sorts";
	private static final Dimension DIMENSION_INVENTORY = new Dimension(200, 60);
	private final static Dimension DIMENSION_FILTERS = new Dimension(200, 40);
	private final static Dimension DIMENSION_ARTICLE = new Dimension(900, 300);
	private final static Dimension DIMENSION_ITEM_CARD = new Dimension(1400, 1800);
	private final static Dimension DIMENSION_ITEM_GRID = new Dimension(1400, 1800);
	private final static Dimension DIMENSION_BUTTON = new Dimension(400, 50);
	private final static Dimension DIMENSION_CATEGORIES = new Dimension(200, 40);

	private MainWindow manager;
	private JLabel effectiveTitle;
	private JPanel container;
	private JButton bigItemBtn;
	private JButton spellBtn;
	private JButton itemBtn;
	private JButton avatarBtn;
	private JButton borderBtn;
	private JButton cursorBtn;
	private JButton textureBtn;

	public Codex(MainWindow parent) {
		super();
		this.manager = parent;
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		 
		Header header = new Header(manager);
		this.add(header, BorderLayout.NORTH);

		JPanel center = new JPanel();
		JPanel titlePanel = new JPanel();
		container = new JPanel();
		JPanel west = new JPanel();
		JPanel categoriesContainer = new JPanel();
		JPanel categories = new JPanel();
		JPanel bigItemBtnPanel = new JPanel();
		JPanel spellBtnPanel = new JPanel();
		JPanel itemBtnPanel = new JPanel();
		JPanel itemCategories = new JPanel();
		JPanel avatarBtnPanel = new JPanel();
		JPanel borderBtnPanel = new JPanel();
		JPanel cursorBtnPanel = new JPanel();
		JPanel textureBtnPanel = new JPanel();
		
		center.setOpaque(false);
		titlePanel.setOpaque(false);
		container.setOpaque(false);
		west.setOpaque(false);
		categoriesContainer.setOpaque(false);
		categories.setOpaque(false);
		bigItemBtnPanel.setOpaque(false);
		spellBtnPanel.setOpaque(false);
		itemBtnPanel.setOpaque(false);
		itemCategories.setOpaque(false);
		avatarBtnPanel.setOpaque(false);
		borderBtnPanel.setOpaque(false);
		cursorBtnPanel.setOpaque(false);
		textureBtnPanel.setOpaque(false);
		
		
		add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(10, 10));
		center.add(titlePanel, BorderLayout.NORTH);
		effectiveTitle = new JLabel(BIGITEM);
		effectiveTitle.setFont(new Font(effectiveTitle.getFont().getName(), effectiveTitle.getFont().getSize(),
				effectiveTitle.getFont().getSize() + 10));
		titlePanel.add(effectiveTitle);
		center.add(container);
		container.setLayout(new CardLayout());
		JScrollPane bigItemPane = buildItemPanel(BIGITEM);
		container.add(bigItemPane, BIGITEM);
		JScrollPane spellPane = buildItemPanel(SPELL);
		container.add(spellPane, SPELL);
		JScrollPane itemPane = buildItemPanel(ITEM);
		container.add(itemPane, ITEM);
		JScrollPane avatarPane = buildItemPanel(AVATAR);
		container.add(avatarPane, AVATAR);
		JScrollPane borderPane = buildItemPanel(BORDER);
		container.add(borderPane, BORDER);
		JScrollPane cursorPane = buildItemPanel(CURSOR);
		container.add(cursorPane, CURSOR);
		JScrollPane texturePane = buildItemPanel(TEXTURE);
		container.add(texturePane, TEXTURE);
		add(west, BorderLayout.WEST);
		west.setLayout(new BorderLayout(100, 100));
		west.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

		JButton inventory = ButtonFactory.getInstance().getButton("Inventaire", PageName.COLLECTION, manager);
		inventory.setPreferredSize(DIMENSION_INVENTORY);
		west.add(inventory, BorderLayout.NORTH);
		categoriesContainer.setLayout(new GridLayout(0,1));
		categoriesContainer.setLayout(new GridLayout(0, 1));
		categoriesContainer.add(categories);
		categories.setLayout(new GridLayout(0, 1, 25, 25));
		west.add(categoriesContainer, BorderLayout.CENTER);
		
		
		bigItemBtn = new JButton(BIGITEM);
		bigItemBtn.setPreferredSize(DIMENSION_BUTTON);
		bigItemBtnPanel.add(bigItemBtn);
		bigItemBtn.setAlignmentX(CENTER_ALIGNMENT);
		categories.add(bigItemBtnPanel);
		bigItemBtn.setEnabled(false);
		bigItemBtn.addActionListener(new CategoryListener(BIGITEM, bigItemBtn));
		
		spellBtn = new JButton(SPELL);
		spellBtn.setPreferredSize(DIMENSION_BUTTON);
		spellBtnPanel.add(spellBtn);
		spellBtn.setAlignmentX(CENTER_ALIGNMENT);
		categories.add(spellBtnPanel);
		spellBtn.addActionListener(new CategoryListener(SPELL, spellBtn));
		
		itemBtn = new JButton(ITEM);
		itemBtn.setPreferredSize(DIMENSION_BUTTON);
		itemBtnPanel.add(itemBtn);
		itemBtn.setAlignmentX(CENTER_ALIGNMENT);
		categories.add(itemBtnPanel);
		itemBtn.addActionListener(new CategoryListener(ITEM, itemBtn));
		itemCategories.setLayout(new BoxLayout(itemCategories, BoxLayout.Y_AXIS));
		categoriesContainer.add(itemCategories);
		
		avatarBtn = new JButton("► Avatars");
		avatarBtn.setPreferredSize(DIMENSION_CATEGORIES);
		avatarBtn.setAlignmentX(CENTER_ALIGNMENT);
		avatarBtnPanel.add(avatarBtn);
		itemCategories.add(avatarBtnPanel);
		avatarBtn.addActionListener(new CategoryListener(ITEM + " : " + AVATAR, avatarBtn));
		
		borderBtn = new JButton("► Bordures");
		borderBtn.setPreferredSize(DIMENSION_CATEGORIES);
		borderBtn.setAlignmentX(CENTER_ALIGNMENT);
		borderBtnPanel.add(borderBtn);
		itemCategories.add(borderBtnPanel);
		borderBtn.addActionListener(new CategoryListener(ITEM + " : " + BORDER, borderBtn));
		cursorBtn = new JButton("► Curseurs");
		cursorBtn.setPreferredSize(DIMENSION_CATEGORIES);
		cursorBtn.setAlignmentX(CENTER_ALIGNMENT);
		cursorBtnPanel.add(cursorBtn);
		itemCategories.add(cursorBtnPanel);
		cursorBtn.addActionListener(new CategoryListener(ITEM + " : " + CURSOR, cursorBtn));
		
		textureBtn = new JButton("► Textures");
		textureBtn.setPreferredSize(DIMENSION_CATEGORIES);
		textureBtn.setAlignmentX(CENTER_ALIGNMENT);
		textureBtnPanel.add(textureBtn);
		itemCategories.add(textureBtnPanel);
		textureBtn.addActionListener(new CategoryListener(ITEM + " : " + TEXTURE, textureBtn));
		JPanel filterPanel = buildFilterPanel();
		west.add(filterPanel, BorderLayout.SOUTH);
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
		itemCard.setMinimumSize(DIMENSION_ITEM_CARD);
		JScrollPane itemPane = new JScrollPane(itemCard, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		itemPane.getViewport().setOpaque(false);
		itemPane.setBorder(null);
		JPanel itemGrid = new JPanel();
		
		itemCard.setOpaque(false);
		itemPane.setOpaque(false);
		itemGrid.setOpaque(false);
		itemGrid.setLayout(new GridLayout(0, 1, 10, 10));
		//itemGrid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		itemGrid.setMinimumSize(DIMENSION_ITEM_GRID);
		itemCard.add(itemGrid);
		for (int index = 0; index < 22; index++) {
			CardArticle card = new CardArticle(
					cat + (index + 1) + " \n " + "Explications, détail et description de l'item.");
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
		filterPanel.add(filterType);
		filterType.setAlignmentX(CENTER_ALIGNMENT);
		filterType.setPreferredSize(DIMENSION_FILTERS);
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
			bigItemBtn.setEnabled(true);
			spellBtn.setEnabled(true);
			itemBtn.setEnabled(true);
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
