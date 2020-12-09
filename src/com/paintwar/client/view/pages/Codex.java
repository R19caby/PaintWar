package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
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
		inventory.setPreferredSize(new Dimension(200, 60));
		west.add(inventory, BorderLayout.NORTH);
		
		JPanel categories = new JPanel();
		categories.setLayout(new BoxLayout(categories, BoxLayout.PAGE_AXIS));
		west.add(categories, BorderLayout.CENTER);
		bigItemBtn = new JButton(BIGITEM);
		bigItemBtn.setPreferredSize(new Dimension(200, 60));
		bigItemBtn.setAlignmentX(CENTER_ALIGNMENT);
		categories.add(bigItemBtn);
		bigItemBtn.setEnabled(false);
		bigItemBtn.addActionListener(new CategoryListener(BIGITEM, bigItemBtn));
		spellBtn = new JButton(SPELL);
		spellBtn.setPreferredSize(new Dimension(200, 60));
		spellBtn.setAlignmentX(CENTER_ALIGNMENT);
		categories.add(spellBtn);
		spellBtn.addActionListener(new CategoryListener(SPELL, spellBtn));
		itemBtn = new JButton(ITEM);
		itemBtn.setPreferredSize(new Dimension(200, 60));
		itemBtn.setAlignmentX(CENTER_ALIGNMENT);
		categories.add(itemBtn);
		itemBtn.addActionListener(new CategoryListener(ITEM, itemBtn));
				
		JPanel itemCategories = new JPanel();
		itemCategories.setLayout(new BoxLayout(itemCategories, BoxLayout.PAGE_AXIS));
		categories.add(itemCategories);
		avatarBtn = new JButton("► Avatars");
		avatarBtn.setAlignmentX(LEFT_ALIGNMENT);
		itemCategories.add(avatarBtn);
		avatarBtn.addActionListener(new CategoryListener(ITEM + " : " + AVATAR, avatarBtn));
		borderBtn = new JButton("► Bordures");
		borderBtn.setAlignmentX(LEFT_ALIGNMENT);
		itemCategories.add(borderBtn);
		borderBtn.addActionListener(new CategoryListener(ITEM + " : " + BORDER, borderBtn));	
		cursorBtn = new JButton("► Curseurs");
		cursorBtn.setAlignmentX(LEFT_ALIGNMENT);
		itemCategories.add(cursorBtn);
		cursorBtn.addActionListener(new CategoryListener(ITEM + " : " + CURSOR, cursorBtn));
		textureBtn = new JButton("► Textures");
		textureBtn.setAlignmentX(LEFT_ALIGNMENT);
		itemCategories.add(textureBtn);
		textureBtn.addActionListener(new CategoryListener(ITEM + " : " + TEXTURE, textureBtn));
		
		JPanel filterPanel = buildFilterPanel();
		west.add(filterPanel, BorderLayout.SOUTH);
	}
	
	public JScrollPane buildItemPanel(String cat) {
		JPanel itemCard = new JPanel();
		itemCard.setMinimumSize(new Dimension(800, 800));
		JScrollPane itemPane = new JScrollPane(itemCard, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel itemGrid = new JPanel();
		itemGrid.setLayout(new GridLayout(0, 1, 10, 10));
		itemGrid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		itemGrid.setMinimumSize(new Dimension(800, 800));
		itemCard.add(itemGrid);
		
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
		JLabel title1 = new JLabel(cat+"1"+" \n "+"Explications, détail et description de l'item.");
		JLabel title2 = new JLabel(cat+"2"+" \n "+"Explications, détail et description de l'item.");
		JLabel title3 = new JLabel(cat+"3"+" \n "+"Explications, détail et description de l'item.");
		JLabel title4 = new JLabel(cat+"4"+" \n "+"Explications, détail et description de l'item.");
		JLabel title5 = new JLabel(cat+"5"+" \n "+"Explications, détail et description de l'item.");
		JLabel title6 = new JLabel(cat+"6"+" \n "+"Explications, détail et description de l'item.");
		JLabel title7 = new JLabel(cat+"7"+" \n "+"Explications, détail et description de l'item.");
		JLabel title8 = new JLabel(cat+"8"+" \n "+"Explications, détail et description de l'item.");
		JLabel title9 = new JLabel(cat+"9"+" \n "+"Explications, détail et description de l'item.");
		JLabel title10 = new JLabel(cat+"10"+" \n "+"Explications, détail et description de l'item.");
		JLabel title11 = new JLabel(cat+"11"+" \n "+"Explications, détail et description de l'item.");
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
		
		card1.setMinimumSize(new Dimension(800, 60));
		card2.setMinimumSize(new Dimension(800, 60));
		card3.setMinimumSize(new Dimension(800, 60));
		card4.setMinimumSize(new Dimension(800, 60));
		card5.setMinimumSize(new Dimension(800, 60));
		card6.setMinimumSize(new Dimension(800, 60));
		card7.setMinimumSize(new Dimension(800, 60));
		card8.setMinimumSize(new Dimension(800, 60));
		card9.setMinimumSize(new Dimension(800, 60));
		card10.setMinimumSize(new Dimension(800, 60));
		card11.setMinimumSize(new Dimension(800, 60));
		
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

		itemGrid.add(card1);
		itemGrid.add(card2);
		itemGrid.add(card3);
		itemGrid.add(card4);
		itemGrid.add(card5);
		itemGrid.add(card6);
		itemGrid.add(card7);
		itemGrid.add(card8);
		itemGrid.add(card9);
		itemGrid.add(card10);
		itemGrid.add(card11);
		
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
		filterType.setPreferredSize(new Dimension(200, 20));
		filterType.setMaximumSize(new Dimension(200, 20));
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
