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
	// ������Ʈ
	private JButton btnDirectorSecurity, btnDirectorFingerPrint;
	private JTextField jtfId;// id// password
	
	//&&&
	private JPasswordField jtfPw;
	
	private JButton jbtLogin;
	private JButton sighup = new JButton(new ImageIcon("final/signup.png"));
	// ��׶���� ����, ȭ�� ��ȯ
	private static String nickName;
	private ScreenController screenController = new ScreenController();
	private ClientBackground clientBackground = new ClientBackground();

	/***** ���ֱ� 1.�̹��� ���� **/
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
		// 1.�⺻ frame ���
		this.setSize(1600, 900);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("LoginGui");
		// -----------------------------------------------//
		// 2.�⺻ frame�� �� �� ������Ʈ ��ġ ��� ����
		// *������Ʈ ����
		imageDirectorSecBtn = Toolkit.getDefaultToolkit().createImage("final/����α���.gif");
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
		// *������Ʈ ��ġ & �ʱ� ����
		btnDirectorSecurity.setBounds(1450, 30, 100, 100);
		btnDirectorFingerPrint.setBounds(300, 30, 100, 120);
		btnDirectorFingerPrint.setVisible(false);
		jtfId.setBounds(720, 360, 250, 50);
		jtfId.setOpaque(false); // textfield ����
		jtfId.setBorder(javax.swing.BorderFactory.createEmptyBorder());// �׵θ� �����
		jtfId.setForeground(Color.CYAN);
		jtfId.setFont(new Font("����", Font.BOLD, 30));
		jtfPw.setBounds(720, 450, 250, 50);
		jtfPw.setOpaque(false); // textfield ����
		
		jtfPw.setBorder(javax.swing.BorderFactory.createEmptyBorder());// �׵θ� �����
		jtfPw.setForeground(Color.CYAN);
		jtfPw.setFont(new Font("����", Font.BOLD, 30));
		jtfPw.setEchoChar('*');
		
		jbtLogin.setBounds(780, 600, 70, 50);
		sighup.setBounds(900, 600, 100, 50);
		// *������Ʈ �׼� ������ �ޱ�
		btnDirectorSecurity.addActionListener(this);
		btnDirectorFingerPrint.addActionListener(this);
		jbtLogin.addActionListener(this);
		sighup.addActionListener(this);
		// -----------------------------------------------//
		// �ð� �̹���
		ImgClock imgClock = new ImgClock();
		setPanel(imgClock).setBounds(83, 53, 179, 149);
		new Thread(imgClock).start();
		// �ð� �۾�
		ClockMessage clockMessage = new ClockMessage();
		setPanel(clockMessage).setBounds(80, 53, 100, 100);
		new Thread(clockMessage).start();

		// ����
		JLayeredPane jLayeredPane = new JLayeredPane();
		jLayeredPane.setBounds(0, 0, 1600, 900);
		jLayeredPane.setLayout(null);

		// 3.�⺻ frame�� ������Ʈ �߰��Ͽ� Ʋ �ϼ�.
		
		JPanel myPanel = new PanelImgLoad("final/main.png");// jlayyerdpanel ���̾ƿ��� null�̶� �ȿ� �ִ� mypanel��
		// layout ���� ��������� �Ѵ�.
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
		// 4.��� panel�� �������� �߰����ֱ�.
		
		this.setVisible(true);
		// -----------------------------------------------//

		////////////////////////////////////////////////////////////
		clientBackground.setClientGui1(this);
		clientBackground.setNickname(nickName);
		clientBackground.connet();
	}

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		System.out.println("�г����� �Է����ּ���. ���� ��ϵ� ��� : ĸƾ�Ƹ޸�ī, ĸƾ����, ��������, ���̾��, �丣");
		nickName = sc.nextLine();
		new ClientGui();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDirectorSecurity) {
			JOptionPane.showMessageDialog(this, " ȯ���մϴ�. ");
			btnDirectorFingerPrint.setVisible(true);
		}
		if (e.getSource() == btnDirectorFingerPrint) {
			/*** ��� dao�� ���� �޾ƿͼ� �Ѱ��� �� ***/
			JOptionPane.showMessageDialog(this, "������ ��ġ�մϴ�");
			screenController.showClientGui2(this, this.clientBackground);
		}
		if (e.getSource() == sighup) {
			sign = new SighUp();
			count++;
		}
		if (e.getSource() == jbtLogin) {

			String id = jtfId.getText();
			String password = jtfPw.getText();
			/* System.out.println(id + "�̰� ����ڰ� ģ ���̵�� ����̾�" + password); */
			if (count != 0) {
				System.out.println("***1.ȸ������ �̷����");
				dbId = sign.getId();
				dbPw = sign.getPw();
				Yowon y1 = new Yowon(dbId, dbPw);
				db_map.put(dbId, y1);
				// System.out.println(dbId + "�̰� �׳� text �޾� �Ѿ�°ž�," + dbPw);
				Iterator it = db_map.keySet().iterator();

				while (it.hasNext()) {
					String keys = (String) it.next();
					Yowon yo1 = db_map.get(keys);
					/* System.out.println(yo1.getYowon_id() + " , " + yo1.getYowon_password()); */
					if (id.equals(yo1.getYowon_id()) && password.equals(yo1.getYowon_password())) {
						JOptionPane.showMessageDialog(this, "���̵�� ��й�ȣ�� ��ġ�մϴ�.");

						screenController.showYowonGui1(this, this.clientBackground);

						System.out.println(
								"1-2 Ŭ���̾�Ʈ gui���� ��ư ���� �� screenController�� �޼��� �۵��Ͽ� �ڽ��� ��ü�� ���� ����ϴ� clientserver �Ѱ���.");
						/*clientBackground.sendToServer(id);
						clientBackground.sendToServer(password);*/
						System.out.println("1.Ŭ���̾�Ʈgui���� textfield �ۼ��Ͽ� client server�� �Ѱ���.");
					}
				}
				jtfId.setText("");
				jtfPw.setText("");

			} else {
				System.out.println("***1.ȸ������ ���� ������");
				Iterator it = db_map.keySet().iterator();

				while (it.hasNext()) {
					String keys = (String) it.next();
					Yowon yo1 = db_map.get(keys);
					/* System.out.println(yo1.getYowon_id() + " , " + yo1.getYowon_password()); */
					if (id.equals(yo1.getYowon_id()) && password.equals(yo1.getYowon_password())) {
						JOptionPane.showMessageDialog(this, "���̵�� ��й�ȣ�� ��ġ�մϴ�.");

						screenController.showYowonGui1(this, this.clientBackground);

						System.out.println(
								"1-2 Ŭ���̾�Ʈ gui���� ��ư ���� �� screenController�� �޼��� �۵��Ͽ� �ڽ��� ��ü�� ���� ����ϴ� clientserver �Ѱ���.");
						/*clientBackground.sendToServer(id);
						clientBackground.sendToServer(password);*/
						System.out.println("1.Ŭ���̾�Ʈgui���� textfield �ۼ��Ͽ� client server�� �Ѱ���.");
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