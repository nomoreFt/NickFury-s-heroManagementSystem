package frame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import back.ClientBackground;
import gui.Main.panel.ClockMessage;
import gui.Main.panel.ImgClock;
import gui.Main.panel.MyStarPanel;
import gui.Main.panel.PanelImgLoad;
import model.Yowon;
import model.dao.YowonDao;
import screenController.ScreenController;

public class ClientGui extends JFrame implements ActionListener {
	// 컴포넌트
	private JButton btnDirectorSecurity, btnDirectorFingerPrint;
	private JTextField jtfId;// id// password
	
	//&&&
	private JPasswordField jtfPw;
	
	private JButton jbtLogin;
	private JButton sighup = new JButton(new ImageIcon("final/signup.png"));
	// 백그라운드와 연결, 화면 전환
	private static String nickName;
	private ScreenController screenController = new ScreenController();
	private ClientBackground clientBackground = new ClientBackground();

	/***** 배경넣기 1.이미지 선언 **/
	Image imageDirectorSecBtn, imageDirectorFingerPrint;
	Icon iconDirectorSecBtn, iconDirectorFingerPrint;
	private BufferedImage img = null;

	private YowonDao dao = new YowonDao();
	private HashMap<String, Yowon> db_map = dao.getDbMap();
	SighUp sign;
	String dbId = "";
	String dbPw = "";
	int count = 0;
	

	
	public ClientGui() {
		// 1.기본 frame 잡기
		this.setSize(1600, 900);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("LoginGui");
		// -----------------------------------------------//
		// 2.기본 frame에 들어갈 각 컴포넌트 위치 잡아 생성
		// *컴포넌트 생성
		imageDirectorSecBtn = Toolkit.getDefaultToolkit().createImage("final/국장로그인.gif");
		iconDirectorSecBtn = new ImageIcon(imageDirectorSecBtn);
		btnDirectorSecurity = new JButton(iconDirectorSecBtn);
		imageDirectorFingerPrint = Toolkit.getDefaultToolkit().createImage("final/fingerPrint .gif");
		iconDirectorFingerPrint = new ImageIcon(imageDirectorFingerPrint);
		btnDirectorFingerPrint = new JButton(iconDirectorFingerPrint);
		
		sighup.setBorderPainted(false);
		sighup.setFocusPainted(false);
		sighup.setContentAreaFilled(false);
		sighup.setOpaque(false);
		
		btnDirectorSecurity.setBorderPainted(false);
		btnDirectorSecurity.setFocusPainted(false);
		btnDirectorSecurity.setContentAreaFilled(false);
		btnDirectorSecurity.setOpaque(false);
		
		btnDirectorFingerPrint.setBorderPainted(false);
		btnDirectorFingerPrint.setFocusPainted(false);
		btnDirectorFingerPrint.setContentAreaFilled(false);
		btnDirectorFingerPrint.setOpaque(false);
		
		jtfId = new JTextField(25);
		jtfPw = new JPasswordField(25);
		
		jbtLogin = new JButton(new ImageIcon("final/buttons/lg1.png"));
		
		jbtLogin.setBorderPainted(false);
		jbtLogin.setFocusPainted(false);
		jbtLogin.setContentAreaFilled(false);
		jbtLogin.setOpaque(false);
		// *컴포넌트 위치 & 초기 셋팅
		btnDirectorSecurity.setBounds(1450, 30, 100, 100);
		btnDirectorFingerPrint.setBounds(300, 30, 100, 120);
		btnDirectorFingerPrint.setVisible(false);
		jtfId.setBounds(720, 360, 250, 50);
		jtfId.setOpaque(false); // textfield 투명
		jtfId.setBorder(javax.swing.BorderFactory.createEmptyBorder());// 테두리 지우기
		jtfId.setForeground(Color.CYAN);
		jtfId.setFont(new Font("굴림", Font.BOLD, 30));
		jtfPw.setBounds(720, 450, 250, 50);
		jtfPw.setOpaque(false); // textfield 투명
		
		jtfPw.setBorder(javax.swing.BorderFactory.createEmptyBorder());// 테두리 지우기
		jtfPw.setForeground(Color.CYAN);
		jtfPw.setFont(new Font("굴림", Font.BOLD, 30));
		jtfPw.setEchoChar('*');
		
		jbtLogin.setBounds(780, 600, 70, 50);
		sighup.setBounds(900, 600, 100, 50);
		// *컴포넌트 액션 리스너 달기
		btnDirectorSecurity.addActionListener(this);
		btnDirectorFingerPrint.addActionListener(this);
		jbtLogin.addActionListener(this);
		sighup.addActionListener(this);
		// -----------------------------------------------//
		// 시계 이미지
		ImgClock imgClock = new ImgClock();
		setPanel(imgClock).setBounds(83, 53, 179, 149);
		new Thread(imgClock).start();
		// 시계 글씨
		ClockMessage clockMessage = new ClockMessage();
		setPanel(clockMessage).setBounds(80, 53, 100, 100);
		new Thread(clockMessage).start();

		// 삽입
		JLayeredPane jLayeredPane = new JLayeredPane();
		jLayeredPane.setBounds(0, 0, 1600, 900);
		jLayeredPane.setLayout(null);

		// 3.기본 frame에 컴포넌트 추가하여 틀 완성.
		
		JPanel myPanel = new PanelImgLoad("final/main.png");// jlayyerdpanel 레이아웃이 null이라 안에 넣는 mypanel도
		// layout 따로 설정해줘야 한다.
		myPanel.setLayout(null);
		myPanel.setBounds(0, -30, 1600, 900);//2
		jLayeredPane.add(btnDirectorSecurity);
		jLayeredPane.add(btnDirectorFingerPrint);
		jLayeredPane.add(jtfId);
		jLayeredPane.add(jtfPw);
		jLayeredPane.add(jbtLogin);
		jLayeredPane.add(sighup);
		jLayeredPane.add(myPanel, new Integer(0));
		jLayeredPane.add(imgClock, new Integer(4));
		jLayeredPane.add(clockMessage, new Integer(5));
		this.add(jLayeredPane);
		// -----------------------------------------------//
		// 4.배경 panel은 마지막에 추가해주기.
		
		this.setVisible(true);
		// -----------------------------------------------//

		////////////////////////////////////////////////////////////
		clientBackground.setClientGui1(this);
		clientBackground.setNickname(nickName);
		clientBackground.connet();
	}

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		System.out.println("닉네임을 입력해주세요. 현재 등록된 요원 : 캡틴아메리카, 캡틴마블, 블랙위도우, 아이언맨, 토르");
		nickName = sc.nextLine();
		new ClientGui();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDirectorSecurity) {
			JOptionPane.showMessageDialog(this, " 환영합니다. ");
			btnDirectorFingerPrint.setVisible(true);
		}
		if (e.getSource() == btnDirectorFingerPrint) {
			/*** 요원 dao로 정보 받아와서 넘겨줄 곳 ***/
			JOptionPane.showMessageDialog(this, "지문이 일치합니다");
			screenController.showClientGui2(this, this.clientBackground);
		}
		if (e.getSource() == sighup) {
			sign = new SighUp();
			count++;
		}
		if (e.getSource() == jbtLogin) {

			String id = jtfId.getText();
			String password = jtfPw.getText();
			/* System.out.println(id + "이건 사용자가 친 아이디와 비번이야" + password); */
			if (count != 0) {
				System.out.println("***1.회원가입 이루어짐");
				dbId = sign.getId();
				dbPw = sign.getPw();
				Yowon y1 = new Yowon(dbId, dbPw);
				db_map.put(dbId, y1);
				// System.out.println(dbId + "이건 그냥 text 받아 넘어온거야," + dbPw);
				Iterator it = db_map.keySet().iterator();

				while (it.hasNext()) {
					String keys = (String) it.next();
					Yowon yo1 = db_map.get(keys);
					/* System.out.println(yo1.getYowon_id() + " , " + yo1.getYowon_password()); */
					if (id.equals(yo1.getYowon_id()) && password.equals(yo1.getYowon_password())) {
						JOptionPane.showMessageDialog(this, "아이디와 비밀번호가 일치합니다.");

						screenController.showYowonGui1(this, this.clientBackground);

						System.out.println(
								"1-2 클라이언트 gui에서 버튼 누를 시 screenController의 메서드 작동하여 자신의 객체와 현재 사용하는 clientserver 넘겨줌.");
						/*clientBackground.sendToServer(id);
						clientBackground.sendToServer(password);*/
						System.out.println("1.클라이언트gui에서 textfield 작성하여 client server로 넘겨줌.");
					}
				}
				jtfId.setText("");
				jtfPw.setText("");

			} else {
				System.out.println("***1.회원가입 없이 가는중");
				Iterator it = db_map.keySet().iterator();

				while (it.hasNext()) {
					String keys = (String) it.next();
					Yowon yo1 = db_map.get(keys);
					/* System.out.println(yo1.getYowon_id() + " , " + yo1.getYowon_password()); */
					if (id.equals(yo1.getYowon_id()) && password.equals(yo1.getYowon_password())) {
						JOptionPane.showMessageDialog(this, "아이디와 비밀번호가 일치합니다.");

						screenController.showYowonGui1(this, this.clientBackground);

						System.out.println(
								"1-2 클라이언트 gui에서 버튼 누를 시 screenController의 메서드 작동하여 자신의 객체와 현재 사용하는 clientserver 넘겨줌.");
						/*clientBackground.sendToServer(id);
						clientBackground.sendToServer(password);*/
						System.out.println("1.클라이언트gui에서 textfield 작성하여 client server로 넘겨줌.");
					}
				}
				jtfId.setText("");
				jtfPw.setText("");
			}

		}

	}
	public JPanel setPanel(JPanel panel) {
		panel.setLayout(null);
		panel.setOpaque(false);
		return panel;
	}
	public String getNickname() {
		return this.nickName;
	}
}