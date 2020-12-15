package graphicResources;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.paintwar.client.view.pages.ConnexionChoice;
import com.paintwar.client.view.pages.Parameters;

public class TestPreviousPageBackground extends JPanel {
	private static final long serialVersionUID = 2179349603495637803L;
	private JPanel background;
	
	public TestPreviousPageBackground(JPanel panel) {
		super();
		background = panel;
		this.setOpaque(false);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image icon = null;
		icon = getImage(background);
		if(icon != null)
			g.drawImage(icon, 0, 0, getWidth(), getHeight(), null);
		}
	
/*
	public Image getImage(JPanel component){ 
		   if(component==null){return null;} 
		   int width = component.getWidth(); 
		   int height = component.getHeight(); 
		   BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
		   Graphics2D g = image.createGraphics(); 
		   component.paintAll(g); 
		   g.dispose(); 
		   Image img = image;
		   return img; 
		}
*/
	
	private static Image getImage(JPanel panel){
		 
		JFrame jwindow = new JFrame();
		jwindow.setContentPane(panel);
		jwindow.pack();
		jwindow.doLayout();
		jwindow.validate();
 
		int width = panel.getWidth();
		int height = panel.getHeight();
 
		BufferedImage image = new BufferedImage(width, height, 
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		jwindow.getContentPane().paintAll(g);
 
		g.dispose();
		jwindow.dispose();
		Image img = image;
		return image;
	}
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(new Dimension(500, 500));
		window.add(new TestPreviousPageBackground(new ConnexionChoice(null)));
		window.setDefaultCloseOperation(3);
		window.setVisible(true);
	}
	
}
