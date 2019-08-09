package gui.Main.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClockMessage extends JPanel implements Runnable {
	Calendar today = Calendar.getInstance();
	int i = today.get(Calendar.AM_PM);
	String[] ampm = { "AM", "PM" };

	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	String time = sdf.format(new Date());
	JLabel timeLabel, ampmLabel;

	public ClockMessage() {
		this.setLayout(null);

		timeLabel = new JLabel(time);
		timeLabel.setBounds(0, 0, 100, 20);
		timeLabel.setForeground(Color.CYAN);
		timeLabel.setFont(new Font("±¼¸²", Font.BOLD, 12));

		ampmLabel = new JLabel(ampm[i]);
		ampmLabel.setBounds(15, 20, 100, 30);
		ampmLabel.setForeground(Color.CYAN);
		ampmLabel.setFont(new Font("±¼¸²", Font.BOLD, 12));

		add(timeLabel, BorderLayout.NORTH);
		add(ampmLabel, BorderLayout.CENTER);
	}

	@Override
	public void run() {
		do {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			timeLabel.setText(sdf.format(new Date()));
			i = today.get(Calendar.AM_PM);
			ampmLabel.setText(ampm[i]);
		} while (true);

	}
}