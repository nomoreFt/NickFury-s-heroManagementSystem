package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Yowon;
import model.dao.YowonDao;

public class SighUp extends JFrame implements ActionListener {

	private JLabel sighmain, sighid, sighpawd, sighpawd2;
	private JTextField jtfid, jtfpawd, jtfpawd2;
	private JButton join, cancel;
	private BufferedImage img = null;

	public String getId() {
		return this.jtfid.getText();
	}

	public String getPw() {
		return this.jtfpawd.getText();
	}

	public SighUp() {

		class Mypanel extends JPanel {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, null);
			}
		}

		try {
			img = ImageIO.read(new File("final/wall2.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "�ҷ��������");
			System.exit(0);
		}

		setBounds(700, 200, 500, 600);
		setLayout(null);

		sighmain = new JLabel("ȸ������");
		sighmain.setBounds(160, 70, 500, 50);
		sighmain.setFont(new Font("����", Font.BOLD, 40));
		sighmain.setForeground(Color.white);
		add(sighmain);

		sighid = new JLabel("ID");
		sighid.setBounds(20, 170, 100, 50);
		sighid.setForeground(Color.white);
		add(sighid);

		jtfid = new JTextField("");
		jtfid.setBounds(150, 170, 300, 50);
		add(jtfid);

		sighpawd = new JLabel("��й�ȣ");
		sighpawd.setBounds(20, 250, 100, 50);
		sighpawd.setForeground(Color.white);
		add(sighpawd);

		jtfpawd = new JTextField("");
		jtfpawd.setBounds(150, 250, 300, 50);
		add(jtfpawd);

		sighpawd2 = new JLabel("��й�ȣ ��Ȯ��");
		sighpawd2.setBounds(20, 330, 100, 50);
		sighpawd2.setForeground(Color.white);
		add(sighpawd2);

		jtfpawd2 = new JTextField();
		jtfpawd2.setBounds(150, 330, 300, 50);
		add(jtfpawd2);

		join = new JButton("�����ϱ�");
		join.setBounds(100, 430, 100, 50);
		add(join);
		join.addActionListener(this);

		cancel = new JButton("����ϱ�");
		cancel.setBounds(280, 430, 100, 50);
		add(cancel);
		cancel.addActionListener(this);

		Mypanel panel = new Mypanel();
		panel.setSize(500, 600);
		add(panel);
		setVisible(true);

	}

	public static void main(String[] args) {
		SighUp sighup = new SighUp();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == join) {
			if (jtfpawd.getText().equals(jtfpawd2.getText())) {

				Yowon y1 = new Yowon(jtfid.getText(), jtfpawd.getText());
				System.out.println("1.�ٿ� yowonInsert(y1) ����");

				String s1 = "\n";
				s1 += "ID : " + jtfid.getText() + "\n";
				s1 += "Password : " + jtfpawd.getText() + "\n";
				JOptionPane.showMessageDialog(join, "���ԵǾ����ϴ�." + s1);
				dispose();

			} else {
				JOptionPane.showMessageDialog(join, "��й�ȣ�� ��Ȯ�� �Ͻʽÿ�.");
				jtfpawd.setText("");
				jtfpawd2.setText("");
			}
		}
		if (e.getSource() == cancel) {
			JOptionPane.showMessageDialog(cancel, "��ҵǾ����ϴ�.");
			jtfpawd.setText("");
			jtfpawd2.setText("");
			jtfid.setText("");

		}

	}

}
