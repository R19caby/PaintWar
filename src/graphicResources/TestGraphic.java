package graphicResources;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.paintwar.client.view.pages.AccountCreation;

public class TestGraphic extends JFrame {
	
	public TestGraphic() {
		// initialisation de la fenêtre
		super();

		// préparation du label avec l'image
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir") + "/src/graphicResources/paint.png")));
		} catch (IOException e) {
			// pb de chargement de l'image
			e.printStackTrace();
		}
		// version sans le stretch de l'image
		//JLabel contentPane = new JLabel(icon);
		// version avec stretch de l'image
		JLabel contentPane = new JLabel(icon) {
		  @Override
		  public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    if(getIcon() != null)
		      g.drawImage(
		          ((ImageIcon)getIcon()).getImage(), 0, 0, 
		          getWidth(), getHeight(), null);
		  }
		};
		// ajoute le conteneur
		setContentPane(contentPane);
		// modification du layout
		getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		// ajout de composants
		getContentPane().add(new JButton("Plop"));
		// affichage de la fenêtre
		setVisible(true);
	}
	
	/*
	public void paintComponent(Graphics g) {
        try {
        	super.paintComponents(g);
        	
        	File imageFile = new File("graphicResources.paint.png");
        	Image img = ImageIO.read(imageFile);
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            
            this.repaint();
        } catch (Exception e ) {
        }
    }
    */
	
	public static void main(String[] args) {
		TestGraphic window = new TestGraphic();
		window.setSize(new Dimension(500, 500));
		window.setVisible(true);
	}

}
