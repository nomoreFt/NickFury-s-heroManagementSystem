package frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import back.ClientBackground;
import gui.Main.panel.ClockMessage;
import gui.Main.panel.ImgClock;
import gui.Main.panel.MyStarPanel;
import gui.Main.panel.PanelImgLoad;
import screenController.ScreenController;

public class ClientGui4 extends JFrame implements ActionListener , ItemListener{
	// 컴포넌트
	private JButton jbtMyInfo, jbtProjectManage;
	String[] projects = { "The Avengers", "Civilwar", "Infinity war", "End Game" };;
	private JComboBox<String> jcbProjects;
	private JButton jbtSearch;
	private JTextField txfSearch;
	// 백그라운드와 연결, 화면 전환
	ClientBackground clientBackground = new ClientBackground();
	private ScreenController screenController = new ScreenController();
	/***** -.배경넣기 1.이미지 선언 **/
	Image image;
	private BufferedImage img = null;

	// -----------------------------------------//
	// 판넬 선언 및 세팅
	/*** -.판넬 2. 각 판넬에 원하는 위치에 컴포넌트를 삽입하기 위해 사용되는 그리드 백 레이아웃 선언 ***/
	GridBagLayout Gbag = new GridBagLayout();
	GridBagConstraints gbc1;

	/*** -.판넬 3. 판넬 선언 및 스크롤 판넬 선언 ***/
	TheAvengersPanel theAvengersPanel;
	CivilwarPanel civilwarPanel;
	InfinityWarPanel infinityWarPanel;
	EndGamePanel endGamePanel;
	//
	JScrollPane theAvengersPanelScroll;
	JScrollPane civilwarPanelScroll;
	JScrollPane infinityWarPanelScroll;
	JScrollPane endGamePanelScroll;
	// -----------------------------------------//

	
		
	

	public ClientGui4(ClientBackground clientBackground) {

		// 1.기본 frame 잡기
		this.setSize(1600, 900);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("YowonGui3");
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
		
		jcbProjects = new JComboBox<String>(projects);
		txfSearch = new JTextField();
		jbtSearch = new JButton(new ImageIcon("final/buttons/search.jpg"));
		jbtSearch.setBorderPainted(false);
		jbtSearch.setFocusPainted(false);
		jbtSearch.setContentAreaFilled(false);
		jbtSearch.setOpaque(false);
		// *컴포넌트 위치
		jbtMyInfo.setBounds(60, 200, 150, 40);
		jbtProjectManage.setBounds(180, 197, 150, 40);
		jcbProjects.setBounds(490, 250, 300, 50);
		jbtSearch.setBounds(300, 760, 70, 50);
		txfSearch.setBounds(100, 760, 200, 50);
		// *컴포넌트 액션 리스너 달기
		jbtMyInfo.addActionListener(this);
		jbtProjectManage.addActionListener(this);
		jcbProjects.addItemListener(this);
		jbtSearch.addActionListener(this);
		// -----------------------------------------------//
		// 3.기본 frame에 컴포넌트 추가하여 틀 완성.
		// 레이어드 판넬 초기 셋팅
		JLayeredPane jLayeredPane = new JLayeredPane();
		jLayeredPane.setSize(1600, 900);
		jLayeredPane.setLayout(null);
		
		JPanel myPanel = new PanelImgLoad("final/요원관리.png");// jlayyerdpanel 레이아웃이 null이라 안에 넣는 mypanel도
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

		
		// 부모 프레임에 컴포넌트 추가
		jLayeredPane.add(jbtMyInfo);
		jLayeredPane.add(jbtProjectManage);
		jLayeredPane.add(jcbProjects);
		jLayeredPane.add(txfSearch);
		jLayeredPane.add(jbtSearch);
		
		jLayeredPane.add(myPanel, new Integer(0));
		jLayeredPane.add(imgClock, new Integer(4));
		jLayeredPane.add(clockMessage, new Integer(5));
		jLayeredPane.add(myStarPanel,new Integer(3));
		
		// -----------------------------------------//

		/*** -.판넬 4.판넬 컴포넌트 생성 ***/
		theAvengersPanel = new TheAvengersPanel();
		civilwarPanel = new CivilwarPanel();
		infinityWarPanel = new InfinityWarPanel();
		endGamePanel = new EndGamePanel();

		/*** -.판넬 5. 삽입하여 스크롤 판넬 생성. ***/
		theAvengersPanelScroll = new JScrollPane(theAvengersPanel);
		theAvengersPanelScroll.setBounds(90, 400, 600, 300);
		theAvengersPanelScroll.setVisible(false);
		civilwarPanelScroll = new JScrollPane(civilwarPanel);
		civilwarPanelScroll.setBounds(90, 400, 600, 300);
		civilwarPanelScroll.setVisible(false);
		infinityWarPanelScroll = new JScrollPane(infinityWarPanel);
		infinityWarPanelScroll.setBounds(90, 400, 600, 300);
		infinityWarPanelScroll.setVisible(false);
		endGamePanelScroll = new JScrollPane(endGamePanel); // 스크롤패널을 선언
		endGamePanelScroll.setBounds(90, 400, 600, 300); // 프레임에 스크롤패널의 위치를 정한다
		endGamePanelScroll.setVisible(false);

		/*** -.판넬 6.기본 프레임에 기존 판넬이 추가된 스크롤 판넬'만' 추가 ***/
		jLayeredPane.add(theAvengersPanelScroll,new Integer(5));
		jLayeredPane.add(civilwarPanelScroll,new Integer(5));
		jLayeredPane.add(infinityWarPanelScroll,new Integer(5));
		jLayeredPane.add(endGamePanelScroll,new Integer(5));

		// -----------------------------------------------//
		// 4.배경 panel은 마지막에 추가해주기.
		/*** 기본 판을 대신 해줄jLayerdPane 추가 ***/
		this.add(jLayeredPane);
		this.setVisible(true);
		/////////////////////////////////
		this.clientBackground = clientBackground;
		clientBackground.setClientGui4(this);
		
	}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jbtMyInfo) {
				screenController.showClientGui2(this, clientBackground);
			}
			if (e.getSource() == jbtProjectManage) {
				screenController.showClientGui3(this, clientBackground);
			}
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (e.getItem().equals("The Avengers")) {
					/******/
					theAvengersPanelScroll.setVisible(true);
					civilwarPanelScroll.setVisible(false);
					infinityWarPanelScroll.setVisible(false);
					endGamePanelScroll.setVisible(false);

					// 해당 판넬 컴포넌트 배열로 생성
					/*** 요원 아이콘 숫자가 요원의 숫자를 결정함. ***/
					int count = 1;
					String[] iconPathYowonMark = { "final/buttons/avgs.png", "icons/1im.png", "icons/1thor.png", "icons/1ca1.png",
							"icons/1ca1.png", "icons/1ca1.png", "icons/1ca1.png", "icons/1ca1.png" };
			
					String YowonNames[] = { "캡틴아메리카", "아이언맨", "토르", "캡틴 아메리카", "캡틴 아메리카", "캡틴 아메리카", "캡틴 아메리카",
							"캡틴 아메리카" };
					JLabel jlbYowonName[] = new JLabel[iconPathYowonMark.length];

					String[] grades = { "A", "B", "C", "D" };
					JLabel jlbYowonGrades[] = new JLabel[iconPathYowonMark.length];

					ButtonGroup bg[] = new ButtonGroup[iconPathYowonMark.length];
					JRadioButton jrbtnO[] = new JRadioButton[iconPathYowonMark.length];
					JRadioButton jrbtnX[] = new JRadioButton[iconPathYowonMark.length];

					for (int i = 0; i < iconPathYowonMark.length; i++) {
						jlbYowonName[i] = new JLabel(YowonNames[i]);
						jlbYowonName[i].setIcon(new ImageIcon(iconPathYowonMark[i]));

						jlbYowonGrades[i] = new JLabel("                  점수는 : " + grades[0] + "                  ");

						bg[i] = new ButtonGroup();
						jrbtnO[i] = new JRadioButton("출석 O");
						jrbtnX[i] = new JRadioButton("출석 X");
						bg[i].add(jrbtnO[i]);
						bg[i].add(jrbtnX[i]);
					}
					// 생성된 컴포넌트들 요원아이콘 수 만큼 해당 한넬에 삽입
					for (int i = 0; i < iconPathYowonMark.length; i++) {
						create_form(jlbYowonName[i], theAvengersPanel, 0, count * 30, 30, 10);
						create_form(jlbYowonGrades[i], theAvengersPanel, 40, count * 30, 30, 10);
						create_form(jrbtnO[i], theAvengersPanel, 80, count * 30, 30, 10);
						create_form(jrbtnX[i], theAvengersPanel, 120, count * 30, 30, 10);
						count++;
					}
				}
				if (e.getItem().equals("Civilwar")) {
					/*********/
					theAvengersPanelScroll.setVisible(false);
					civilwarPanelScroll.setVisible(true);
					infinityWarPanelScroll.setVisible(false);
					endGamePanelScroll.setVisible(false);

					// 해당 판넬 컴포넌트 배열로 생성
					/*** 요원 아이콘 숫자가 요원의 숫자를 결정함. ***/
					int count = 1;
					String[] iconPathYowonMark = { "icons/1ca1.png", "icons/1im.png", "icons/1thor.png" };
					String YowonNames[] = { "캡틴아메리카", "아이언맨", "토르" };
					JLabel jlbYowonName[] = new JLabel[iconPathYowonMark.length];

					String[] grades = { "A", "B", "C", "D" };
					JLabel jlbYowonGrades[] = new JLabel[iconPathYowonMark.length];

					JToggleButton jtbtnO[] = new JToggleButton[iconPathYowonMark.length];
					JToggleButton jtbtnX[] = new JToggleButton[iconPathYowonMark.length];

					for (int i = 0; i < iconPathYowonMark.length; i++) {
						jlbYowonName[i] = new JLabel(YowonNames[i]);
						jlbYowonName[i].setIcon(new ImageIcon(iconPathYowonMark[i]));

						jlbYowonGrades[i] = new JLabel("                  점수는 : " + grades[0] + "                  ");
						jtbtnO[i] = new JToggleButton("출석 O");
						jtbtnX[i] = new JToggleButton("출석 X");
					}
					// 생성된 컴포넌트들 요원아이콘 수 만큼 해당 한넬에 삽입
					for (int i = 0; i < iconPathYowonMark.length; i++) {
						create_form(jlbYowonName[i], civilwarPanel, 0, count * 30, 30, 10);
						create_form(jlbYowonGrades[i], civilwarPanel, 40, count * 30, 30, 10);
						create_form(jtbtnO[i], civilwarPanel, 80, count * 30, 30, 10);
						create_form(jtbtnX[i], civilwarPanel, 120, count * 30, 30, 10);
						count++;
					}
				}
				if (e.getItem().equals("Infinity war")) {
					/*********/
					theAvengersPanelScroll.setVisible(false);
					civilwarPanelScroll.setVisible(false);
					infinityWarPanelScroll.setVisible(true);
					endGamePanelScroll.setVisible(false);

					// 해당 판넬 컴포넌트 배열로 생성
					/*** 요원 아이콘 숫자가 요원의 숫자를 결정함. ***/
					int count = 1;
					String[] iconPathYowonMark = { "icons/1ca1.png", "icons/1im.png", "icons/1thor.png" };
					String YowonNames[] = { "캡틴아메리카", "아이언맨", "토르" };
					JLabel jlbYowonName[] = new JLabel[iconPathYowonMark.length];

					String[] grades = { "A", "B", "C", "D" };
					JLabel jlbYowonGrades[] = new JLabel[iconPathYowonMark.length];

					JToggleButton jtbtnO[] = new JToggleButton[iconPathYowonMark.length];
					JToggleButton jtbtnX[] = new JToggleButton[iconPathYowonMark.length];

					for (int i = 0; i < iconPathYowonMark.length; i++) {
						jlbYowonName[i] = new JLabel(YowonNames[i]);
						jlbYowonName[i].setIcon(new ImageIcon(iconPathYowonMark[i]));

						jlbYowonGrades[i] = new JLabel("                  점수는 : " + grades[0] + "                  ");
						jtbtnO[i] = new JToggleButton("출석 O");
						jtbtnX[i] = new JToggleButton("출석 X");
					}
					// 생성된 컴포넌트들 요원아이콘 수 만큼 해당 한넬에 삽입
					for (int i = 0; i < iconPathYowonMark.length; i++) {
						create_form(jlbYowonName[i], infinityWarPanel, 0, count * 30, 30, 10);
						create_form(jlbYowonGrades[i], infinityWarPanel, 40, count * 30, 30, 10);
						create_form(jtbtnO[i], infinityWarPanel, 80, count * 30, 30, 10);
						create_form(jtbtnX[i], infinityWarPanel, 120, count * 30, 30, 10);
						count++;
					}
				}

				if (e.getItem().equals("End Game")) {
					/*********/
					theAvengersPanelScroll.setVisible(false);
					civilwarPanelScroll.setVisible(false);
					infinityWarPanelScroll.setVisible(false);
					endGamePanelScroll.setVisible(true);

					// 해당 판넬 컴포넌트 배열로 생성
					/*** 요원 아이콘 숫자가 요원의 숫자를 결정함. ***/
					int count = 1;
					String[] iconPathYowonMark = { "icons/1ca1.png", "icons/1im.png", "icons/1thor.png" };
					String YowonNames[] = { "캡틴아메리카", "아이언맨", "토르" };
					JLabel jlbYowonName[] = new JLabel[iconPathYowonMark.length];

					String[] grades = { "A", "B", "C", "D" };
					JLabel jlbYowonGrades[] = new JLabel[iconPathYowonMark.length];

					JToggleButton jtbtnO[] = new JToggleButton[iconPathYowonMark.length];
					JToggleButton jtbtnX[] = new JToggleButton[iconPathYowonMark.length];

					for (int i = 0; i < iconPathYowonMark.length; i++) {
						jlbYowonName[i] = new JLabel(YowonNames[i]);
						jlbYowonName[i].setIcon(new ImageIcon(iconPathYowonMark[i]));

						jlbYowonGrades[i] = new JLabel("                  점수는 : " + grades[0] + "                  ");
						jtbtnO[i] = new JToggleButton("출석 O");
						jtbtnX[i] = new JToggleButton("출석 X");
					}
					// 생성된 컴포넌트들 요원아이콘 수 만큼 해당 한넬에 삽입
					for (int i = 0; i < iconPathYowonMark.length; i++) {
						create_form(jlbYowonName[i], endGamePanel, 0, count * 30, 30, 10);
						create_form(jlbYowonGrades[i], endGamePanel, 40, count * 30, 30, 10);
						create_form(jtbtnO[i], endGamePanel, 80, count * 30, 30, 10);
						create_form(jtbtnX[i], endGamePanel, 120, count * 30, 30, 10);
						count++;
					} // for end
				} // if end
			} // 아이템 선택되면 end
		}// item 상태 변화 메서드 end

	public void create_form(Component cmpt, JPanel jp, int x, int y, int w, int h) {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		this.Gbag.setConstraints(cmpt, gbc);
		jp.add(cmpt);
		jp.updateUI();

	}

	/*** -.판넬 1.스크롤 판넬을 만들기 위해 그리드 백 레이아웃으로 판넬 생성 ***/
	class TheAvengersPanel extends JPanel {
		public TheAvengersPanel() {
			this.setLayout(Gbag);
			this.setBackground(Color.white);
			System.out.println("2판넬 설정 함.");
		}
	}

	class CivilwarPanel extends JPanel {
		public CivilwarPanel() {
			this.setLayout(Gbag);
			this.setBackground(Color.white);
			System.out.println("2판넬 설정 함.");
		}
	}

	class InfinityWarPanel extends JPanel {
		public InfinityWarPanel() {
			this.setLayout(Gbag);
			this.setBackground(Color.white);
			System.out.println("2판넬 설정 함.");
		}
	}

	class EndGamePanel extends JPanel {
		public EndGamePanel() {
			this.setLayout(Gbag);
			this.setBackground(Color.white);
			System.out.println("2판넬 설정 함.");
		}
	}

	
	public JPanel setPanel(JPanel panel) {
		panel.setLayout(null);
		panel.setOpaque(false);
		return panel;
	}
}
