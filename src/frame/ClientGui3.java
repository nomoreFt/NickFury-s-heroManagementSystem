package frame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.*;

import back.ClientBackground;
import gui.Main.panel.ClockMessage;
import gui.Main.panel.ImgClock;
import gui.Main.panel.MyStarPanel;
import gui.Main.panel.PanelImgLoad;
import screenController.ScreenController;

public class ClientGui3 extends JFrame implements ActionListener {
	private JButton jbtMyInfo, jbtProjectManage;
	private JTextField jtfName, jtfAge, jtfAddress, jtfMajor;
	private JButton jbtSave, jbtCancel;
	// 백그라운드와 연결, 화면 전환
	private ClientBackground clientBackground = new ClientBackground();
	private ScreenController screenController = new ScreenController();
	/***** 배경넣기 1.이미지 선언 **/
	Image image;
	private BufferedImage img = null;


	public ClientGui3(ClientBackground clientBackground) {
		// 1.기본 frame 잡기
		this.setSize(1600, 900);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("YowonGui2");
		// -----------------------------------------------//
		// 2.기본 frame에 들어갈 각 컴포넌트 위치 잡아 생성
		// *컴포넌트 생성
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
		jtfName = new JTextField();
		jtfAge = new JTextField();
		jtfAddress = new JTextField();
		jtfMajor = new JTextField();
		jbtSave = new JButton(new ImageIcon("final/buttons/save.png"));
		jbtSave.setBorderPainted(false);
		jbtSave.setFocusPainted(false);
		jbtSave.setContentAreaFilled(false);
		jbtSave.setOpaque(false);
		jbtCancel = new JButton(new ImageIcon("final/buttons/cc1.png"));
		jbtCancel.setBorderPainted(false);
		jbtCancel.setFocusPainted(false);
		jbtCancel.setContentAreaFilled(false);
		jbtCancel.setOpaque(false);
		// *컴포넌트 위치
		jbtMyInfo.setBounds(60, 200, 150, 40);
		jbtProjectManage.setBounds(180, 197, 150, 40);
		jtfName.setBounds(230, 365, 700, 40);
		jtfAge.setBounds(230, 415, 700, 40);
		jtfAddress.setBounds(230, 465, 700, 40);
		jtfMajor.setBounds(230, 515, 700, 40);
		jbtSave.setBounds(120, 650, 90, 50);
		jbtCancel.setBounds(270, 650, 90, 50);
		
		JLayeredPane jLayeredPane = new JLayeredPane();
		jLayeredPane.setBounds(0, 0, 1600, 900);
		jLayeredPane.setLayout(null);
		
		JPanel myPanel = new PanelImgLoad("final/국장2.png");// jlayyerdpanel 레이아웃이 null이라 안에 넣는 mypanel도
		// layout 따로 설정해줘야 한다.
		myPanel.setLayout(null);
		myPanel.setBounds(0, -30, 1600, 900);//2
		
		// 시계 이미지
		ImgClock imgClock = new ImgClock();
		setPanel(imgClock).setBounds(83, 53, 179, 149);
		new Thread(imgClock).start();
		// 시계 글씨
		ClockMessage clockMessage = new ClockMessage();
		setPanel(clockMessage).setBounds(80, 53, 100, 100);
		new Thread(clockMessage).start();

		// 움직이는 광원 처리
		MyStarPanel myStarPanel = new MyStarPanel();
		setPanel(myStarPanel).setBounds(0, -30, 1600, 900);
		new Thread(myStarPanel).start();
		
		// *컴포넌트 액션 리스너 달기
		jbtMyInfo.addActionListener(this);
		jbtProjectManage.addActionListener(this);
		jbtSave.addActionListener(this);
		jbtCancel.addActionListener(this);
		// -----------------------------------------------//
		// 3.기본 frame에 컴포넌트 추가하여 틀 완성.
		jLayeredPane.add(jbtMyInfo);
		jLayeredPane.add(jbtProjectManage);
		jLayeredPane.add(jtfName);
		jLayeredPane.add(jtfAge);
		jLayeredPane.add(jtfAddress);
		jLayeredPane.add(jtfMajor);
		jLayeredPane.add(jbtSave);
		jLayeredPane.add(jbtCancel);
		
		jLayeredPane.add(myPanel, new Integer(0));
		jLayeredPane.add(imgClock, new Integer(4));
		jLayeredPane.add(clockMessage, new Integer(5));
		jLayeredPane.add(myStarPanel, new Integer(3));
		this.add(jLayeredPane);
		
		// -----------------------------------------------//
		// 4.배경 panel은 마지막에 추가해주기.
	
		this.setVisible(true);
		////////////////////////////////////////////////////////////////
		this.clientBackground = clientBackground;
		clientBackground.setClientGui3(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jbtMyInfo) {
			jbtMyInfo.setEnabled(false);
		}
		if (e.getSource() == jbtProjectManage) {
			screenController.showClientGui4(this, clientBackground);
		}

		if (e.getSource() == jbtSave) {
			JOptionPane.showMessageDialog(null, "저장");
			String[] MasterInfo = new String[4];
			MasterInfo[0] = jtfName.getText();
			MasterInfo[1] = jtfAge.getText();
			MasterInfo[2] = jtfAddress.getText();
			MasterInfo[3] = jtfMajor.getText();

			for (int i = 0; i < MasterInfo.length; i++) {
				System.out.println(MasterInfo[i]);

			}
			// ---------------------------------- // 저장 여부 확인

		}
		if (e.getSource() == jbtCancel) {
			JOptionPane.showMessageDialog(null, "초기화");
			jtfName.setText("");
			jtfAge.setText("");
			jtfAddress.setText("");
			jtfMajor.setText("");
			// ---------------------------------- // 내용 초기화
		}

	}
	public JPanel setPanel(JPanel panel) {
		panel.setLayout(null);
		panel.setOpaque(false);
		return panel;
	}

}
