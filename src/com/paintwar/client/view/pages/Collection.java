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

public class Collection extends JPanel {

	private static final long serialVersionUID = -916442714757214449L;
	private final static String AVATAR = "Avatars";
	private final static String BORDER = "Bordures";
	private final static String CURSOR = "Curseurs";
	private final static String TEXTURE = "Textures";
	
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
		add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(10, 10));
		JPanel titlePanel = new JPanel();
		center.add(titlePanel, BorderLayout.NORTH);
		effectiveTitle = new JLabel(AVATAR);
		titlePanel.add(effectiveTitle);

		container = new JPanel();
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
		
		
		JPanel west = new JPanel();
		add(west, BorderLayout.WEST);
		west.setLayout(new BorderLayout(100, 100));
		west.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		
		ButtonFactory codex = new ButtonFactory("Codex", PageName.CODEX, manager);
		codex.setPreferredSize(new Dimension(200, 60));
		west.add(codex, BorderLayout.NORTH);
		
		JPanel categories = new JPanel();
		west.add(categories, BorderLayout.CENTER);
		categories.setLayout(new BoxLayout(categories, BoxLayout.PAGE_AXIS));
		JLabel categoriesTitle = new JLabel("Cat�gories");
		categoriesTitle.setAlignmentX(CENTER_ALIGNMENT);
		categories.add(categoriesTitle);
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
		west.add(filterPanel, BorderLayout.SOUTH);
		
		
		
		
		
	}
	
	public JScrollPane buildItemPanel(String cat) {
		JPanel itemCard = new JPanel();
		itemCard.setMinimumSize(new Dimension(800, 800));
		JScrollPane itemPane = new JScrollPane(itemCard, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel itemGrid = new JPanel();
		itemGrid.setLayout(new GridLayout(0, 3, 10, 10));
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
		JLabel title1 = new JLabel(cat+"1");
		JLabel title2 = new JLabel(cat+"2");
		JLabel title3 = new JLabel(cat+"3");
		JLabel title4 = new JLabel(cat+"4");
		JLabel title5 = new JLabel(cat+"5");
		JLabel title6 = new JLabel(cat+"6");
		JLabel title7 = new JLabel(cat+"7");
		JLabel title8 = new JLabel(cat+"8");
		JLabel title9 = new JLabel(cat+"9");
		JLabel title10 = new JLabel(cat+"10");
		JLabel title11 = new JLabel(cat+"11");
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
		filterType.addItem("Ordre alphab�tique");
		filterType.addItem("Ordre alphab�tique inverse");
		filterType.addItem("Date d'acquisition croissante");
		filterType.addItem("Date d'acquisition d�croissante");
				
		JPanel voidPanel = new JPanel();
		voidPanel.setPreferredSize((new Dimension(200, 100)));
		filterPanel.add(voidPanel);
		
		return filterPanel;
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
			//sort the current category according to the selected filter
			
		}
		
	}
	
	public void addItem(String category, String name, String description, boolean unlocked, String imgSourcePath) {
		//get all the info to create the item and than add it in the good category
	}
	
	

}
