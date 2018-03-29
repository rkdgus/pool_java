package kr.or.dgit.pool_java.content;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class TeacherSidebar extends JPanel {

	/**
	 * Create the panel.
	 */
	public TeacherSidebar() {
		setBounds(10, 10, 200, 550);
		setBackground(new Color(0, 102, 255));
		setLayout(null);
		
		JLabel schedule = new JLabel("강의 시간표");
		schedule.setForeground(new Color(255, 255, 255));
		schedule.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		schedule.setBounds(39, 44, 162, 28);
		add(schedule);
		
		JLabel classinfo = new JLabel("수강정보");
		classinfo.setForeground(new Color(255, 255, 255));
		classinfo.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		classinfo.setBounds(39, 108, 162, 28);
		add(classinfo);
		
		JLabel userinfo = new JLabel("회원정보 ");
		userinfo.setForeground(new Color(255, 255, 255));
		userinfo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		userinfo.setBounds(70, 159, 92, 15);
		add(userinfo);
		
		JLabel classqna = new JLabel("수강문의관리");
		classqna.setForeground(new Color(255, 255, 255));
		classqna.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		classqna.setBounds(70, 200, 107, 15);
		add(classqna);
		
		JButton btnNewButton = new JButton("로그아웃");
		btnNewButton.setBounds(29, 498, 148, 33);
		add(btnNewButton);

	}
}
