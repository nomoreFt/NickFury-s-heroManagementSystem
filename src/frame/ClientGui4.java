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
	// ������Ʈ
	private JButton jbtMyInfo, jbtProjectManage;
	String[] projects = { "The Avengers", "Civilwar", "Infinity war", "End Game" };;
	private JComboBox<String> jcbProjects;
	private JButton jbtSearch;
	private JTextField txfSearch;
	// ��׶���� ����, ȭ�� ��ȯ
	ClientBackground clientBackground = new ClientBackground();
	private ScreenController screenController = new ScreenController();
	/***** -.���ֱ� 1.�̹��� ���� **/
	Image image;
	private BufferedImage img = null;

	// -----------------------------------------//
	// �ǳ� ���� �� ����
	/*** -.�ǳ� 2. �� �ǳڿ� ���ϴ� ��ġ�� ������Ʈ�� �����ϱ� ���� ���Ǵ� �׸��� �� ���̾ƿ� ���� ***/
	GridBagLayout Gbag = new GridBagLayout();
	GridBagConstraints gbc1;

	/*** -.�ǳ� 3. �ǳ� ���� �� ��ũ�� �ǳ� ���� ***/
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

		// 1.�⺻ frame ���
		this.setSize(1600, 900);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("YowonGui3");
		// -----------------------------------------------//
		// 2.�⺻ frame�� �� �� ������Ʈ ��ġ ��� ����
		// *������Ʈ ����
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
		// *������Ʈ ��ġ
		jbtMyInfo.setBounds(60, 200, 150, 40);
		jbtProjectManage.setBounds(180, 197, 150, 40);
		jcbProjects.setBounds(490, 250, 300, 50);
		jbtSearch.setBounds(300, 760, 70, 50);
		txfSearch.setBounds(100, 760, 200, 50);
		// *������Ʈ �׼� ������ �ޱ�
		jbtMyInfo.addActionListener(this);
		jbtProjectManage.addActionListener(this);
		jcbProjects.addItemListener(this);
		jbtSearch.addActionListener(this);
		// -----------------------------------------------//
		// 3.�⺻ frame�� ������Ʈ �߰��Ͽ� Ʋ �ϼ�.
		// ���̾�� �ǳ� �ʱ� ����
		JLayeredPane jLayeredPane = new JLayeredPane();
		jLayeredPane.setSize(1600, 900);
		jLayeredPane.setLayout(null);
		
		JPanel myPanel = new PanelImgLoad("final/�������.png");// jlayyerdpanel ���̾ƿ��� null�̶� �ȿ� �ִ� mypanel��
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

		
		// �θ� �����ӿ� ������Ʈ �߰�
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

		/*** -.�ǳ� 4.�ǳ� ������Ʈ ���� ***/
		theAvengersPanel = new TheAvengersPanel();
		civilwarPanel = new CivilwarPanel();
		infinityWarPanel = new InfinityWarPanel();
		endGamePanel = new EndGamePanel();

		/*** -.�ǳ� 5. �����Ͽ� ��ũ�� �ǳ� ����. ***/
		theAvengersPanelScroll = new JScrollPane(theAvengersPanel);
		theAvengersPanelScroll.setBounds(90, 400, 600, 300);
		theAvengersPanelScroll.setVisible(false);
		civilwarPanelScroll = new JScrollPane(civilwarPanel);
		civilwarPanelScroll.setBounds(90, 400, 600, 300);
		civilwarPanelScroll.setVisible(false);
		infinityWarPanelScroll = new JScrollPane(infinityWarPanel);
		infinityWarPanelScroll.setBounds(90, 400, 600, 300);
		infinityWarPanelScroll.setVisible(false);
		endGamePanelScroll = new JScrollPane(endGamePanel); // ��ũ���г��� ����
		endGamePanelScroll.setBounds(90, 400, 600, 300); // �����ӿ� ��ũ���г��� ��ġ�� ���Ѵ�
		endGamePanelScroll.setVisible(false);

		/*** -.�ǳ� 6.�⺻ �����ӿ� ���� �ǳ��� �߰��� ��ũ�� �ǳ�'��' �߰� ***/
		jLayeredPane.add(theAvengersPanelScroll,new Integer(5));
		jLayeredPane.add(civilwarPanelScroll,new Integer(5));
		jLayeredPane.add(infinityWarPanelScroll,new Integer(5));
		jLayeredPane.add(endGamePanelScroll,new Integer(5));

		// -----------------------------------------------//
		// 4.��� panel�� �������� �߰����ֱ�.
		/*** �⺻ ���� ��� ����jLayerdPane �߰� ***/
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

					// �ش� �ǳ� ������Ʈ �迭�� ����
					/*** ��� ������ ���ڰ� ����� ���ڸ� ������. ***/
					int count = 1;
					String[] iconPathYowonMark = { "final/buttons/avgs.png", "icons/1im.png", "icons/1thor.png", "icons/1ca1.png",
							"icons/1ca1.png", "icons/1ca1.png", "icons/1ca1.png", "icons/1ca1.png" };
			
					String YowonNames[] = { "ĸƾ�Ƹ޸�ī", "���̾��", "�丣", "ĸƾ �Ƹ޸�ī", "ĸƾ �Ƹ޸�ī", "ĸƾ �Ƹ޸�ī", "ĸƾ �Ƹ޸�ī",
							"ĸƾ �Ƹ޸�ī" };
					JLabel jlbYowonName[] = new JLabel[iconPathYowonMark.length];

					String[] grades = { "A", "B", "C", "D" };
					JLabel jlbYowonGrades[] = new JLabel[iconPathYowonMark.length];

					ButtonGroup bg[] = new ButtonGroup[iconPathYowonMark.length];
					JRadioButton jrbtnO[] = new JRadioButton[iconPathYowonMark.length];
					JRadioButton jrbtnX[] = new JRadioButton[iconPathYowonMark.length];

					for (int i = 0; i < iconPathYowonMark.length; i++) {
						jlbYowonName[i] = new JLabel(YowonNames[i]);
						jlbYowonName[i].setIcon(new ImageIcon(iconPathYowonMark[i]));

						jlbYowonGrades[i] = new JLabel("                  ������ : " + grades[0] + "                  ");

						bg[i] = new ButtonGroup();
						jrbtnO[i] = new JRadioButton("�⼮ O");
						jrbtnX[i] = new JRadioButton("�⼮ X");
						bg[i].add(jrbtnO[i]);
						bg[i].add(jrbtnX[i]);
					}
					// ������ ������Ʈ�� ��������� �� ��ŭ �ش� �ѳڿ� ����
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

					// �ش� �ǳ� ������Ʈ �迭�� ����
					/*** ��� ������ ���ڰ� ����� ���ڸ� ������. ***/
					int count = 1;
					String[] iconPathYowonMark = { "icons/1ca1.png", "icons/1im.png", "icons/1thor.png" };
					String YowonNames[] = { "ĸƾ�Ƹ޸�ī", "���̾��", "�丣" };
					JLabel jlbYowonName[] = new JLabel[iconPathYowonMark.length];

					String[] grades = { "A", "B", "C", "D" };
					JLabel jlbYowonGrades[] = new JLabel[iconPathYowonMark.length];

					JToggleButton jtbtnO[] = new JToggleButton[iconPathYowonMark.length];
					JToggleButton jtbtnX[] = new JToggleButton[iconPathYowonMark.length];

					for (int i = 0; i < iconPathYowonMark.length; i++) {
						jlbYowonName[i] = new JLabel(YowonNames[i]);
						jlbYowonName[i].setIcon(new ImageIcon(iconPathYowonMark[i]));

						jlbYowonGrades[i] = new JLabel("                  ������ : " + grades[0] + "                  ");
						jtbtnO[i] = new JToggleButton("�⼮ O");
						jtbtnX[i] = new JToggleButton("�⼮ X");
					}
					// ������ ������Ʈ�� ��������� �� ��ŭ �ش� �ѳڿ� ����
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

					// �ش� �ǳ� ������Ʈ �迭�� ����
					/*** ��� ������ ���ڰ� ����� ���ڸ� ������. ***/
					int count = 1;
					String[] iconPathYowonMark = { "icons/1ca1.png", "icons/1im.png", "icons/1thor.png" };
					String YowonNames[] = { "ĸƾ�Ƹ޸�ī", "���̾��", "�丣" };
					JLabel jlbYowonName[] = new JLabel[iconPathYowonMark.length];

					String[] grades = { "A", "B", "C", "D" };
					JLabel jlbYowonGrades[] = new JLabel[iconPathYowonMark.length];

					JToggleButton jtbtnO[] = new JToggleButton[iconPathYowonMark.length];
					JToggleButton jtbtnX[] = new JToggleButton[iconPathYowonMark.length];

					for (int i = 0; i < iconPathYowonMark.length; i++) {
						jlbYowonName[i] = new JLabel(YowonNames[i]);
						jlbYowonName[i].setIcon(new ImageIcon(iconPathYowonMark[i]));

						jlbYowonGrades[i] = new JLabel("                  ������ : " + grades[0] + "                  ");
						jtbtnO[i] = new JToggleButton("�⼮ O");
						jtbtnX[i] = new JToggleButton("�⼮ X");
					}
					// ������ ������Ʈ�� ��������� �� ��ŭ �ش� �ѳڿ� ����
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

					// �ش� �ǳ� ������Ʈ �迭�� ����
					/*** ��� ������ ���ڰ� ����� ���ڸ� ������. ***/
					int count = 1;
					String[] iconPathYowonMark = { "icons/1ca1.png", "icons/1im.png", "icons/1thor.png" };
					String YowonNames[] = { "ĸƾ�Ƹ޸�ī", "���̾��", "�丣" };
					JLabel jlbYowonName[] = new JLabel[iconPathYowonMark.length];

					String[] grades = { "A", "B", "C", "D" };
					JLabel jlbYowonGrades[] = new JLabel[iconPathYowonMark.length];

					JToggleButton jtbtnO[] = new JToggleButton[iconPathYowonMark.length];
					JToggleButton jtbtnX[] = new JToggleButton[iconPathYowonMark.length];

					for (int i = 0; i < iconPathYowonMark.length; i++) {
						jlbYowonName[i] = new JLabel(YowonNames[i]);
						jlbYowonName[i].setIcon(new ImageIcon(iconPathYowonMark[i]));

						jlbYowonGrades[i] = new JLabel("                  ������ : " + grades[0] + "                  ");
						jtbtnO[i] = new JToggleButton("�⼮ O");
						jtbtnX[i] = new JToggleButton("�⼮ X");
					}
					// ������ ������Ʈ�� ��������� �� ��ŭ �ش� �ѳڿ� ����
					for (int i = 0; i < iconPathYowonMark.length; i++) {
						create_form(jlbYowonName[i], endGamePanel, 0, count * 30, 30, 10);
						create_form(jlbYowonGrades[i], endGamePanel, 40, count * 30, 30, 10);
						create_form(jtbtnO[i], endGamePanel, 80, count * 30, 30, 10);
						create_form(jtbtnX[i], endGamePanel, 120, count * 30, 30, 10);
						count++;
					} // for end
				} // if end
			} // ������ ���õǸ� end
		}// item ���� ��ȭ �޼��� end

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

	/*** -.�ǳ� 1.��ũ�� �ǳ��� ����� ���� �׸��� �� ���̾ƿ����� �ǳ� ���� ***/
	class TheAvengersPanel extends JPanel {
		public TheAvengersPanel() {
			this.setLayout(Gbag);
			this.setBackground(Color.white);
			System.out.println("2�ǳ� ���� ��.");
		}
	}

	class CivilwarPanel extends JPanel {
		public CivilwarPanel() {
			this.setLayout(Gbag);
			this.setBackground(Color.white);
			System.out.println("2�ǳ� ���� ��.");
		}
	}

	class InfinityWarPanel extends JPanel {
		public InfinityWarPanel() {
			this.setLayout(Gbag);
			this.setBackground(Color.white);
			System.out.println("2�ǳ� ���� ��.");
		}
	}

	class EndGamePanel extends JPanel {
		public EndGamePanel() {
			this.setLayout(Gbag);
			this.setBackground(Color.white);
			System.out.println("2�ǳ� ���� ��.");
		}
	}

	
	public JPanel setPanel(JPanel panel) {
		panel.setLayout(null);
		panel.setOpaque(false);
		return panel;
	}
}
