package frame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import back.ClientBackground;
import gui.Main.panel.ClockMessage;
import gui.Main.panel.ImgClock;
import gui.Main.panel.MyStarPanel;
import gui.Main.panel.PanelImgLoad;
import screenController.ScreenController;

//textarea ������ serverserver���� ���Ѽ� ClientBackground���� ���� �޼����� textarea
//���ٰ� �߰��� �ϸ� ��. ������ x;

// ---------------------------------- //

public class YowonGui1 extends JFrame implements ActionListener {
	// ������Ʈ
	private JButton jbtMyInfo,jbtProjectManage;
	private JTextArea jtaProjectMessage;
	// ��׶���� ����, ȭ�� ��ȯ
	private ClientBackground clientBackground = new ClientBackground();
	private ScreenController screenController = new ScreenController();
	/***** ���ֱ� 1.�̹��� ���� **/
	Image image;
	private BufferedImage img = null;

	public YowonGui1(ClientBackground clientBackground) {
		// 1.�⺻ frame ���
		this.setSize(1600, 900);
		this.setLayout(null);
		//-----------------------------------------------//
		// 2.�⺻ frame�� �� �� ������Ʈ ��ġ ��� ����
		//*������Ʈ ����
		jbtMyInfo = new JButton(new ImageIcon("final/buttons/mypage.png"));
		jbtMyInfo.setBorderPainted(false);
		jbtMyInfo.setFocusPainted(false);
		jbtMyInfo.setContentAreaFilled(false);
		jbtMyInfo.setOpaque(false);
		
		jbtProjectManage = new JButton(new ImageIcon("final/buttons/avgs.png"));
		jbtProjectManage.setBorderPainted(false);
		jbtProjectManage.setFocusPainted(false);
		jbtProjectManage.setContentAreaFilled(false);
		jbtProjectManage.setOpaque(false);
		jtaProjectMessage = new JTextArea(25, 30);
		//*������Ʈ ��ġ
		jbtMyInfo.setBounds(60, 200, 150, 40);
		jbtProjectManage.setBounds(180, 197, 150, 40);
		jtaProjectMessage.setBounds(100, 450, 430, 300);
		//*������Ʈ �׼� ������ �ޱ�
		jbtMyInfo.addActionListener(this);
		jbtProjectManage.addActionListener(this);
		//-----------------------------------------------//
		// 3.�⺻ frame�� ������Ʈ �߰��Ͽ� Ʋ �ϼ�.
		
		JLayeredPane jLayeredPane = new JLayeredPane();
		jLayeredPane.setBounds(0, 0, 1600, 900);
		jLayeredPane.setLayout(null);
		//******���� �����ؼ� ������� ��� �ٸ��� �� �����Դϴ�. ����
		this.clientBackground = clientBackground;
		clientBackground.setYowonGui1(this);
		String nickName = clientBackground.getNickname();
		appendMsg(nickName  + "�� ȯ���մϴ�. ������ ������ ���͵帱���?\n"); 
		//*****
		JPanel myPanel;
	
			myPanel = new PanelImgLoad("final/��������.png");// jlayyerdpanel ���̾ƿ��� null�̶� �ȿ� �ִ� mypanel��
		// layout ���� ��������� �Ѵ�.
		myPanel.setLayout(null);
		myPanel.setBounds(0, -30, 1600, 900);//2
		
		// �ð� �̹���
		ImgClock imgClock = new ImgClock();
		setPanel(imgClock).setBounds(83, 53, 179, 149);
		new Thread(imgClock).start();
		// �ð� �۾�
		ClockMessage clockMessage = new ClockMessage();
		setPanel(clockMessage).setBounds(80, 53, 100, 100);
		new Thread(clockMessage).start();

		// �����̴� ���� ó��
		MyStarPanel myStarPanel = new MyStarPanel();
		setPanel(myStarPanel).setBounds(0, -30, 1600, 900);
		new Thread(myStarPanel).start();

		
		jLayeredPane.add(jbtMyInfo);
		jLayeredPane.add(jbtProjectManage);
		jLayeredPane.add(jtaProjectMessage);
		jLayeredPane.add(myPanel, new Integer(0));
		jLayeredPane.add(imgClock, new Integer(4));
		jLayeredPane.add(clockMessage, new Integer(5));
		jLayeredPane.add(myStarPanel, new Integer(3));
		this.add(jLayeredPane);
		//-----------------------------------------------//
		// 4.��� panel�� �������� �߰����ֱ�.

		this.setVisible(true);
		//-----------------------------------------------//
		//////////////////////////////////////////////////////////////////////
		

	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtMyInfo) {
			// ������ ��ư ������ �� �� ��
			System.out.println("�� ���� ��ư ����");
			screenController.showYowonGui2(this, clientBackground);
		}
		if (e.getSource() == jbtProjectManage) {
			// ���� ���� ��ư�� ������ �� �� ��
			System.out.println("���� ���� ��ư ����");
			screenController.showYowonGui3(this, clientBackground);
		}
	}

	public void appendMsg(String msg) {
		jtaProjectMessage.append(msg);
	}

	public JPanel setPanel(JPanel panel) {
		panel.setLayout(null);
		panel.setOpaque(false);
		return panel;
	}
}
