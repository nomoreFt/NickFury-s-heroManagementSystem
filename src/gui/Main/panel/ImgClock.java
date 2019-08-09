package gui.Main.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class ImgClock extends JPanel implements Runnable {
	Image img[] = new Image[15];
	int i = 2;

	public ImgClock() {
		img[1] = Toolkit.getDefaultToolkit().createImage("final/clock/tt1.png");// �ð� ���� 1��
		img[2] = Toolkit.getDefaultToolkit().createImage("final/clock/tt2.png");// �ð� ���۱׸� 2��
		img[3] = Toolkit.getDefaultToolkit().createImage("final/clock/tt3.png");// �ð� ���� �׸� 3��
		img[4] = Toolkit.getDefaultToolkit().createImage("final/clock/tt4.png");// �ð� ���� �׸� 3��
		img[5] = Toolkit.getDefaultToolkit().createImage("final/clock/tt5.png");// �ð� ���� �׸� 3��
		img[6] = Toolkit.getDefaultToolkit().createImage("final/clock/tt6.png");// �ð� ���� �׸� 3��
		img[7] = Toolkit.getDefaultToolkit().createImage("final/clock/tt7.png");// �ð� ���� �׸� 3��
		img[8] = Toolkit.getDefaultToolkit().createImage("final/clock/tt8.png");// �ð� ���� �׸� 3��
		img[9] = Toolkit.getDefaultToolkit().createImage("final/clock/tt9.png");// �ð� ���� �׸� 3��
		img[10] = Toolkit.getDefaultToolkit().createImage("final/clock/tt10.png");// �ð� ���� �׸� 3��
		img[11] = Toolkit.getDefaultToolkit().createImage("final/clock/tt11.png");// �ð� ���� �׸� 3��
		img[12] = Toolkit.getDefaultToolkit().createImage("final/clock/tt12.png");// �ð� ���� �׸� 3��
		img[13] = Toolkit.getDefaultToolkit().createImage("final/clock/tt13.png");// �ð� ���� �׸� 3��
		img[14] = Toolkit.getDefaultToolkit().createImage("final/clock/tt14.png");// �ð� ���� �׸� 3��
		img[0] = img[1];// �ð� �ʱ� ����� �̹��� 1�����.
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img[0], 0,0, this);
	}

	@Override
	public void run() {
		while (true) {

			try {
				Thread.sleep(100);
			
				i = (i==14) ? 1 : ++i;
				img[0] = img[i];
				
				repaint();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}