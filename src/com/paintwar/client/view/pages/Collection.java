package com.paintwar.client.view.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
		JPanel avatarCard = new JPanel();
		JScrollPane avatarPane = new JScrollPane(avatarCard, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel avatarGrid = new JPanel();
		avatarGrid.setLayout(new GridLayout(3, 3, 10, 10));
		avatarCard.add(avatarGrid);
		container.add(avatarPane, AVATAR);
		
		JPanel borderCard = new JPanel();
		JScrollPane borderPane = new JScrollPane(borderCard, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel borderGrid = new JPanel();
		borderGrid.setLayout(new GridLayout(3, 3, 10, 10));
		borderCard.add(borderGrid);
		container.add(borderPane, BORDER);
		
		JPanel cursorCard = new JPanel();
		JScrollPane cursorPane = new JScrollPane(cursorCard, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel cursorGrid = new JPanel();
		cursorGrid.setLayout(new GridLayout(3, 3, 10, 10));
		cursorCard.add(cursorGrid);
		container.add(cursorPane, CURSOR);
		
		JPanel textureCard = new JPanel();
		JScrollPane texturePane = new JScrollPane(textureCard, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel textureGrid = new JPanel();
		textureGrid.setLayout(new GridLayout(3, 3, 10, 10));
		textureCard.add(textureGrid);
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
			
		
		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.PAGE_AXIS));
		west.add(filterPanel, BorderLayout.SOUTH);
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
