package gui.Main.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class MyStarPanel extends JPanel implements Runnable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image img;
    int i = 1;
    int sleep = 7;
    int sx = -10, sy=0;

    public MyStarPanel() {
        img = Toolkit.getDefaultToolkit().createImage("final/star6.png");
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(img!=null) g.drawImage(img, sx, sy, this);
    }

    @Override
    public void run() {
        try {
            do {
                Thread.sleep(sleep);
                switch (i) {
                case 1: sy+=2; if(sy>800) i=2; break;
                case 2: sx+=2; if(sx>1510) i=3; break;
                case 3: sy-=2; if(sy<10) i=4; break;
                case 4: sx-=2; if(sx<-10) i=1; break;
                }
                repaint();
            } while (true);
        } catch (Exception e) {
            System.out.println("¹«½¼ ¿¡·¯¿©");
        }
    }

}

