package gui.Main.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;


public class PanelImgLoad extends JPanel {

	Image image;


	public PanelImgLoad(String path) {
		image = Toolkit.getDefaultToolkit().createImage(path);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		super.update(g);
	}

}