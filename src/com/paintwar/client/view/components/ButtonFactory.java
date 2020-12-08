package com.paintwar.client.view.components;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

import com.paintwar.client.logger.Logger;
import com.paintwar.client.view.MainWindow;
import com.paintwar.client.view.pages.PageName;

/**
 * This is a button factory.
 * <p>
 * {@code JButton newButton = ButtonFactory.getInstance().getButton();}
 * <p>
 * Ouais je sais c'est nul désolé je ne comprends pas pourquoi la classe interne
 * privée CutsomButton et RoundedBorder ne marchent pas dans un contexte
 * statique.
 * 
 * @author jkerl
 *
 */
public class ButtonFactory
{
	private ButtonFactory()
	{
	}

	private static ButtonFactory instance;

	public static ButtonFactory getInstance()
	{
		if (instance == null)
		{
			instance = new ButtonFactory();
		}
		return instance;
	}

	public JButton getButton(String name, PageName pageName, MainWindow manager)
	{
		JButton b = new CustomButton(name, pageName, manager);
		b.setBorder(new RoundedBorder(10));
		return b;
	}

	public JButton getParameterButton(MainWindow manager)
	{
		JButton b = new JButton("Failed");
		try
		{
			b = new JButton(new ImageIcon(new URL("https://img.icons8.com/ios/452/settings.png")));
		} catch (MalformedURLException e)
		{
			Logger.print(e);
		}

		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		b.setPreferredSize(new Dimension(30, 30));

		return b;
	}

	private class CustomButton extends JButton implements ActionListener
	{
		private PageName pageName;
		private MainWindow manager;

		private CustomButton(String name, PageName pageName, MainWindow manager)
		{
			super(name);
			this.pageName = pageName;
			this.manager = manager;

			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			CardLayout cl = (CardLayout) (manager.getContentContainer().getLayout());
			cl.show(manager.getContentContainer(), pageName+"");
		}

	}

	private class RoundedBorder implements Border
	{
		private int radius;

		public RoundedBorder(int radius)
		{
			this.radius = radius;
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
		{
			g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
		}

		@Override
		public Insets getBorderInsets(Component c)
		{
			return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
		}

		@Override
		public boolean isBorderOpaque()
		{
			return true;
		}
	}
}
