package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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

public class Codex extends JPanel{

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
		 
		Header header = new Header(manager);
		this.add(header, BorderLayout.NORTH);
		
		JPanel center = new JPanel();
		add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(10, 10));
		JPanel titlePanel = new JPanel();
		center.add(titlePanel, BorderLayout.NORTH);
		effectiveTitle = new JLabel(BIGITEM);
		effectiveTitle.setFont(new Font(effectiveTitle.getFont().getName(), effectiveTitle.getFont().getSize(), effectiveTitle.getFont().getSize()+10));
		titlePanel.add(effectiveTitle);
		
		container = new JPanel();
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
		
		
		
		JPanel west = new JPanel();
		add(west, BorderLayout.WEST);
		west.setLayout(new BorderLayout(100, 100));
		west.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		
		JButton inventory = ButtonFactory.getInstance().getButton("Inventaire", PageName.COLLECTION, manager);
		inventory.setPreferredSize(DIMENSION_INVENTORY);
		west.add(inventory, BorderLayout.NORTH);
		
		JPanel categoriesContainer = new JPanel();
		categoriesContainer.setLayout(new GridLayout(0,1));
		JPanel categories = new JPanel();
		categoriesContainer.add(categories);
		categories.setLayout(new GridLayout(0,1, 25, 25));
		west.add(categoriesContainer, BorderLayout.CENTER);
		bigItemBtn = new JButton(BIGITEM);
		bigItemBtn.setPreferredSize(DIMENSION_BUTTON);
		JPanel bigItemBtnPanel = new JPanel();
		bigItemBtnPanel.add(bigItemBtn);
		bigItemBtn.setAlignmentX(CENTER_ALIGNMENT);
		categories.add(bigItemBtnPanel);
		bigItemBtn.setEnabled(false);
		bigItemBtn.addActionListener(new CategoryListener(BIGITEM, bigItemBtn));
		spellBtn = new JButton(SPELL);
		JPanel spellBtnPanel = new JPanel();
		spellBtn.setPreferredSize(DIMENSION_BUTTON);
		spellBtnPanel.add(spellBtn);
		spellBtn.setAlignmentX(CENTER_ALIGNMENT);
		categories.add(spellBtnPanel);
		spellBtn.addActionListener(new CategoryListener(SPELL, spellBtn));
		itemBtn = new JButton(ITEM);
		JPanel itemBtnPanel = new JPanel();
		itemBtn.setPreferredSize(DIMENSION_BUTTON);
		itemBtnPanel.add(itemBtn);
		itemBtn.setAlignmentX(CENTER_ALIGNMENT);
		categories.add(itemBtnPanel);
		itemBtn.addActionListener(new CategoryListener(ITEM, itemBtn));
		JPanel itemCategories = new JPanel();
		itemCategories.setLayout(new BoxLayout(itemCategories, BoxLayout.Y_AXIS));
		categoriesContainer.add(itemCategories);
		avatarBtn = new JButton("► Avatars");
		avatarBtn.setPreferredSize(DIMENSION_CATEGORIES);
		avatarBtn.setAlignmentX(CENTER_ALIGNMENT);
		JPanel avatarBtnPanel = new JPanel();
		avatarBtnPanel.add(avatarBtn);
		itemCategories.add(avatarBtnPanel);
		avatarBtn.addActionListener(new CategoryListener(ITEM + " : " + AVATAR, avatarBtn));
		borderBtn = new JButton("► Bordures");
		borderBtn.setPreferredSize(DIMENSION_CATEGORIES);
		borderBtn.setAlignmentX(CENTER_ALIGNMENT);
		JPanel borderBtnPanel = new JPanel();
		borderBtnPanel.add(borderBtn);
		itemCategories.add(borderBtnPanel);
		borderBtn.addActionListener(new CategoryListener(ITEM + " : " + BORDER, borderBtn));	
		cursorBtn = new JButton("► Curseurs");
		cursorBtn.setPreferredSize(DIMENSION_CATEGORIES);
		cursorBtn.setAlignmentX(CENTER_ALIGNMENT);
		JPanel cursorBtnPanel = new JPanel();
		cursorBtnPanel.add(cursorBtn);
		itemCategories.add(cursorBtnPanel);
		cursorBtn.addActionListener(new CategoryListener(ITEM + " : " + CURSOR, cursorBtn));
		textureBtn = new JButton("► Textures");
		textureBtn.setPreferredSize(DIMENSION_CATEGORIES);
		textureBtn.setAlignmentX(CENTER_ALIGNMENT);
		JPanel textureBtnPanel = new JPanel();
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
		JScrollPane itemPane = new JScrollPane(itemCard, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel itemGrid = new JPanel();
		itemGrid.setLayout(new GridLayout(0, 1, 10, 10));
		itemGrid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		itemGrid.setMinimumSize(DIMENSION_ITEM_GRID);
		itemCard.add(itemGrid);
		for (int index = 0; index < 22; index++) {
			CardArticle card = new CardArticle(cat+(index+1)+" \n "+"Explications, détail et description de l'item.");
			itemGrid.add(card);
		}
		return itemPane;
	}
	
	
	public JPanel buildFilterPanel() {
		JPanel filterPanel = new JPanel();
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
			//sort the current category according to the selected filter
			
		}
		
	}
	
	public class CategoryListener implements ActionListener{
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

}
