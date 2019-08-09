
package frame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import back.ClientBackground;
import gui.Main.panel.ClockMessage;
import gui.Main.panel.ImgClock;
import gui.Main.panel.MyStarPanel;
import gui.Main.panel.PanelImgLoad;
import screenController.ScreenController;

public class ClientGui2 extends JFrame implements ActionListener {

	private JButton jbtMyInfo, jbtProjectManage;
	private JTextArea jtaNotice;
	private JTextField jtfNotice;
	// 백그라운드와 연결, 화면 전환
	private static String nickName;
	private ClientBackground clientBackground = new ClientBackground();
	private ScreenController screenController = new ScreenController();
	/***** 배경넣기 1.이미지 선언 **/
	Image image;
	private BufferedImage img = null;

	public ClientGui2(ClientBackground clientBackground) {
		// 1.기본 frame 잡기
				this.setSize(1600, 900);
				this.setLayout(null);
				this.setDefaultCloseOperation(EXIT_ON_CLOSE);
				this.setTitle("ClientGui2");
				this.setVisible(true);
				
				JLayeredPane jLayeredPane = new JLayeredPane();
				jLayeredPane.setBounds(0, 0, 1600, 900);
				jLayeredPane.setLayout(null);
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
				jtfNotice = new JTextField(25);
				jtaNotice = new JTextArea(40, 25);

				// *컴포넌트 위치
				jbtMyInfo.setBounds(60, 200, 150, 40);
				jbtProjectManage.setBounds(180, 197, 150, 40);
				jtfNotice.setBounds(100, 390, 430, 35);
				jtaNotice.setBounds(100, 450, 430, 300);
				// *컴포넌트 액션 리스너 달기
				jbtMyInfo.addActionListener(this);
				jbtProjectManage.addActionListener(this);
				jtfNotice.addActionListener(this);
				// 3.기본 frame에 컴포넌트 추가하여 틀 완성.
				jLayeredPane.add(jbtMyInfo);
				jLayeredPane.add(jbtProjectManage);
				jLayeredPane.add(jtfNotice);
				jLayeredPane.add(jtaNotice);

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

				// 삽입


				// 3.기본 frame에 컴포넌트 추가하여 틀 완성.
				
				JPanel myPanel = new PanelImgLoad("final/공지사항.png");// jlayyerdpanel 레이아웃이 null이라 안에 넣는 mypanel도
				myPanel.setLayout(null);
				myPanel.setBounds(0, -30, 1600, 900);//2
				
				jLayeredPane.add(myPanel, new Integer(0));
				jLayeredPane.add(imgClock, new Integer(4));
				jLayeredPane.add(clockMessage, new Integer(5));
				jLayeredPane.add(myStarPanel, new Integer(3));
				this.add(jLayeredPane);
				// -----------------------------------------------//
				
				//-----------------------------------------------//
				
				///////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////
		this.clientBackground = clientBackground;
		this.nickName = clientBackground.getNickname();
		appendMsg(nickName + "님 환영합니다. 오늘은 무엇을 도와드릴까요?\n"); 
		clientBackground.setClientGui2(this);
	}
	
	public ClientGui2() {
		// 1.기본 frame 잡기
				this.setSize(1600, 900);
				this.setLayout(null);
				this.setDefaultCloseOperation(EXIT_ON_CLOSE);
				this.setTitle("ClientGui2");
				this.setVisible(true);
				
				JLayeredPane jLayeredPane = new JLayeredPane();
				jLayeredPane.setBounds(0, 0, 1600, 900);
				jLayeredPane.setLayout(null);
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
				jtfNotice = new JTextField(25);
				jtaNotice = new JTextArea(40, 25);

				// *컴포넌트 위치
				jbtMyInfo.setBounds(60, 200, 150, 40);
				jbtProjectManage.setBounds(180, 197, 150, 40);
				jtfNotice.setBounds(100, 390, 430, 35);
				jtaNotice.setBounds(100, 450, 430, 300);
				// *컴포넌트 액션 리스너 달기
				jbtMyInfo.addActionListener(this);
				jbtProjectManage.addActionListener(this);
				jtfNotice.addActionListener(this);
				// 3.기본 frame에 컴포넌트 추가하여 틀 완성.
				jLayeredPane.add(jbtMyInfo);
				jLayeredPane.add(jbtProjectManage);
				jLayeredPane.add(jtfNotice);
				jLayeredPane.add(jtaNotice);

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

				// 삽입


				// 3.기본 frame에 컴포넌트 추가하여 틀 완성.
				
				JPanel myPanel = new PanelImgLoad("final/공지사항.png");// jlayyerdpanel 레이아웃이 null이라 안에 넣는 mypanel도
				myPanel.setLayout(null);
				myPanel.setBounds(0, -30, 1600, 900);//2
				
				jLayeredPane.add(myPanel, new Integer(0));
				jLayeredPane.add(imgClock, new Integer(4));
				jLayeredPane.add(clockMessage, new Integer(5));
				jLayeredPane.add(myStarPanel, new Integer(3));
				this.add(jLayeredPane);
				// -----------------------------------------------//
				
				//-----------------------------------------------//
				
				///////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////

	}
	public static void main(String[] args) {
		new ClientGui2(); 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtMyInfo) {
			screenController.showClientGui3(this, this.clientBackground);
		}
		if (e.getSource() == jbtProjectManage) {
			screenController.showClientGui4(this, this.clientBackground);
		}

		String msg = jtfNotice.getText() + "\n";
		clientBackground.sendToServer(msg);
		// appendMsg(msg);
		jtfNotice.setText("");

	}

	public void appendMsg(String msg) {
		jtaNotice.append(msg);
	}
	public JPanel setPanel(JPanel panel) {
		panel.setLayout(null);
		panel.setOpaque(false);
		return panel;
	}

}
