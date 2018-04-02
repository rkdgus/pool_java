package kr.or.dgit.pool_java.content;

import java.awt.Font;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;

public class ClockPanel extends JPanel implements Runnable{
	private Thread thread;
	private JLabel label;

	/**
	 * Create the panel.
	 */
	public ClockPanel() {
		label = new JLabel();
		label.setForeground(Color.BLACK);
		/*label.setFont(new Font("Serif",Font.SERIF , 20));*/
		 label.setFont(new Font("Serif", Font.PLAIN, 24));
	
		if (thread == null) {
			// this의 의미는 Runnable이 구현된 객체를 뜻함(DigitalClock)
			thread = new Thread(this);
			thread.start();
		}
		setLayout(new BorderLayout(0, 0));
		add(label);
		setBounds(100, 100, 400, 100);
		setVisible(true);

	}

	public void run() {
		while (true) {
			Calendar cal = Calendar.getInstance();
			String now = cal.get(Calendar.YEAR) + "년" + (cal.get(Calendar.MONTH) + 1) + "월" + cal.get(Calendar.DATE)
					+ "일" + cal.get(Calendar.HOUR) + "시" + cal.get(Calendar.MINUTE) + "분" + cal.get(Calendar.SECOND)
					+ "초";
			label.setText(now);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
