package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

import com.paintwar.client.view.components.ButtonFactory;
import com.paintwar.client.view.components.Header;
import com.paintwar.client.view.pages.Home.SelectType;

public class Collection extends JPanel {

	private static final long serialVersionUID = -916442714757214449L;
	private final static String AVATAR = "Avatar";
	private final static String BORDER = "Bordure";
	private final static String CURSOR = "Cursor";
	private final static String TEXTURE = "Texture";
	
	public Collection() {
		super();
		this.setLayout(new BorderLayout());
		
		Header header = new Header();
		this.add(header, BorderLayout.NORTH);

		JPanel west = new JPanel();
		west.setLayout(new BorderLayout(200, 1000));
		ButtonFactory codex = new ButtonFactory("Codex", PageName.CODEX);
		codex.setPreferredSize(new Dimension(200, 70));
		west.add(codex, BorderLayout.NORTH);
		
		JPanel center = new JPanel();
		center.setLayout(new CardLayout());
		center.setAlignmentX(RIGHT_ALIGNMENT);
		
		JPanel catPanel = new JPanel();
		catPanel.setLayout(new BoxLayout(catPanel, BoxLayout.PAGE_AXIS));
		catPanel.setAlignmentX(CENTER_ALIGNMENT);
		catPanel.setPreferredSize(new Dimension(200, 600));
		ButtonGroup categories =new ButtonGroup();
		JRadioButton avatar = new JRadioButton("Avatars");
		avatar.setSelected(true);
		avatar.addActionListener(new CategoryListener(center, AVATAR));
		categories.add(avatar);
		catPanel.add(avatar);
		JRadioButton border = new JRadioButton("Bordures");
		border.addActionListener(new CategoryListener(center, BORDER));
		categories.add(border);
		catPanel.add(border);
		JRadioButton cursor = new JRadioButton("Curseurs");
		cursor.addActionListener(new CategoryListener(center, CURSOR));
		categories.add(cursor);
		catPanel.add(cursor);
		JRadioButton texture = new JRadioButton("Textures");
		texture.addActionListener(new CategoryListener(center, TEXTURE));
		categories.add(texture);
		catPanel.add(texture);
		catPanel.setVisible(true);
		west.add(catPanel, BorderLayout.CENTER);
		
		
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
		
		west.add(filterPanel, BorderLayout.SOUTH);
		
		this.add(west, BorderLayout.WEST);
		
		
		JPanel avatarCard = new JPanel();
		JScrollPane avatarPane = new JScrollPane(avatarCard, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel avatarGrid = new JPanel();
		avatarGrid.setLayout(new GridLayout(3, 3, 10, 10));
		avatarCard.add(avatarGrid);		
		center.add(avatarPane, AVATAR);
		
		//Samples added to the avatarCard:
		JLabel item1 = new JLabel("Item");
		item1.setPreferredSize(new Dimension(100,100));
		avatarGrid.add(item1);
		JLabel item2 = new JLabel("Item");
		item2.setPreferredSize(new Dimension(100,100));
		avatarGrid.add(item2);
		JLabel item3 = new JLabel("Item");
		item3.setPreferredSize(new Dimension(100,100));
		avatarGrid.add(item3);
		JLabel item4 = new JLabel("Item");
		item4.setPreferredSize(new Dimension(100,100));
		avatarGrid.add(item4);
		JLabel item5 = new JLabel("Item");
		item5.setPreferredSize(new Dimension(100,100));
		avatarGrid.add(item5);
		JLabel item6 = new JLabel("Item");
		item6.setPreferredSize(new Dimension(100,100));
		avatarGrid.add(item6);
		JLabel item7 = new JLabel("Item");
		item7.setPreferredSize(new Dimension(100,100));
		avatarGrid.add(item7);
		JLabel item8 = new JLabel("Item");
		item8.setPreferredSize(new Dimension(100,100));
		avatarGrid.add(item8);
		JLabel item9 = new JLabel("Item");
		item9.setPreferredSize(new Dimension(100,100));
		avatarGrid.add(item9);
		
		JPanel borderCard = new JPanel();
		borderCard.setLayout(new GridLayout(3, 3, 10, 10));
		center.add(borderCard, BORDER);
		
		JPanel cursorCard = new JPanel();
		cursorCard.setLayout(new GridLayout(3, 3, 10, 10));
		center.add(cursorCard, CURSOR);
		
		JPanel textureCard = new JPanel();
		textureCard.setLayout(new GridLayout(3, 3, 10, 10));
		center.add(textureCard, TEXTURE);
		
		this.add(center, BorderLayout.CENTER);
	}
	
	
	public class CategoryListener implements ActionListener{
		private String category;
		private JPanel center;
		public CategoryListener(JPanel center, String category) {
			this.category = category;
			this.center = center;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cl = (CardLayout)(center.getLayout());
			cl.show(center, category);
			
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
