package kr.or.dgit.pool_java.content;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

public class AdminSidebar extends JPanel {

	/**
	 * Create the panel.
	 */
	public AdminSidebar() {
		setBounds(10, 10, 200, 550);
		setBackground(new Color(0, 102, 255));
		setLayout(null);
		
		JLabel schedule = new JLabel("회원정보");
		schedule.setForeground(new Color(255, 255, 255));
		schedule.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		schedule.setBounds(39, 44, 162, 28);
		add(schedule);
		
		JLabel classinfo = new JLabel("수강관리");
		classinfo.setForeground(new Color(255, 255, 255));
		classinfo.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		classinfo.setBounds(39, 150, 162, 28);
		add(classinfo);
		
		JLabel userinfo = new JLabel("수강별관리");
		userinfo.setForeground(new Color(255, 255, 255));
		userinfo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		userinfo.setBounds(69, 188, 92, 15);
		add(userinfo);
		
		JLabel classqna = new JLabel("신규수강관리");
		classqna.setForeground(new Color(255, 255, 255));
		classqna.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		classqna.setBounds(69, 223, 107, 15);
		add(classqna);
		
		JButton btnNewButton = new JButton("로그아웃");
		btnNewButton.setBounds(29, 498, 148, 33);
		add(btnNewButton);
		
		JLabel label = new JLabel("회원관리");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label.setBounds(69, 82, 92, 15);
		add(label);
		
		JLabel label_1 = new JLabel("강사관리");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_1.setBounds(69, 110, 92, 15);
		add(label_1);
		
		JLabel label_2 = new JLabel("매출관리");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_2.setBounds(39, 264, 162, 28);
		add(label_2);
		
		JLabel label_3 = new JLabel("매출관리");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_3.setBounds(64, 305, 97, 15);
		add(label_3);
		
		JLabel label_4 = new JLabel("재등록 강의률");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_4.setBounds(64, 342, 107, 15);
		add(label_4);
		
		JLabel label_5 = new JLabel("락커룸");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_5.setBounds(39, 381, 162, 28);
		add(label_5);
	}
}
