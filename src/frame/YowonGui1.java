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

//textarea 국장이 serverserver에게 시켜서 ClientBackground에서 받은 메세지를 textarea
//에다가 추가만 하면 됨. 보내기 x;

// ---------------------------------- //

public class YowonGui1 extends JFrame implements ActionListener {
	// 컴포넌트
	private JButton jbtMyInfo,jbtProjectManage;
	private JTextArea jtaProjectMessage;
	// 백그라운드와 연결, 화면 전환
	private ClientBackground clientBackground = new ClientBackground();
	private ScreenController screenController = new ScreenController();
	/***** 배경넣기 1.이미지 선언 **/
	Image image;
	private BufferedImage img = null;

	public YowonGui1(ClientBackground clientBackground) {
		// 1.기본 frame 잡기
		this.setSize(1600, 900);
		this.setLayout(null);
		//-----------------------------------------------//
		// 2.기본 frame에 들어갈 각 컴포넌트 위치 잡아 생성
		//*컴포넌트 생성
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
		//*컴포넌트 위치
		jbtMyInfo.setBounds(60, 200, 150, 40);
		jbtProjectManage.setBounds(180, 197, 150, 40);
		jtaProjectMessage.setBounds(100, 450, 430, 300);
		//*컴포넌트 액션 리스너 달기
		jbtMyInfo.addActionListener(this);
		jbtProjectManage.addActionListener(this);
		//-----------------------------------------------//
		// 3.기본 frame에 컴포넌트 추가하여 틀 완성.
		
		JLayeredPane jLayeredPane = new JLayeredPane();
		jLayeredPane.setBounds(0, 0, 1600, 900);
		jLayeredPane.setLayout(null);
		//******서버 설정해서 요원마다 배경 다르게 할 공간입니다. ㅎㅎ
		this.clientBackground = clientBackground;
		clientBackground.setYowonGui1(this);
		String nickName = clientBackground.getNickname();
		appendMsg(nickName  + "님 환영합니다. 오늘은 무엇을 도와드릴까요?\n"); 
		//*****
		JPanel myPanel;
	
			myPanel = new PanelImgLoad("final/공지사항.png");// jlayyerdpanel 레이아웃이 null이라 안에 넣는 mypanel도
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

		
		jLayeredPane.add(jbtMyInfo);
		jLayeredPane.add(jbtProjectManage);
		jLayeredPane.add(jtaProjectMessage);
		jLayeredPane.add(myPanel, new Integer(0));
		jLayeredPane.add(imgClock, new Integer(4));
		jLayeredPane.add(clockMessage, new Integer(5));
		jLayeredPane.add(myStarPanel, new Integer(3));
		this.add(jLayeredPane);
		//-----------------------------------------------//
		// 4.배경 panel은 마지막에 추가해주기.

		this.setVisible(true);
		//-----------------------------------------------//
		//////////////////////////////////////////////////////////////////////
		

	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtMyInfo) {
			// 내정보 버튼 눌렸을 시 할 일
			System.out.println("내 정보 버튼 눌림");
			screenController.showYowonGui2(this, clientBackground);
		}
		if (e.getSource() == jbtProjectManage) {
			// 작전 관리 버튼이 눌렸을 떄 할 일
			System.out.println("작전 관리 버튼 눌림");
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
