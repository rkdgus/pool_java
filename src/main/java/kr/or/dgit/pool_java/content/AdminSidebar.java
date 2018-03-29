package kr.or.dgit.pool_java.content;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

public class AdminSidebar extends JPanel {
	private JLabel schedule;
	private JLabel classinfo;
	private JLabel userinfo;
	private JLabel classqna;
	private JButton btnNewButton;
	private JLabel member;
	private JLabel teacher;
	/**
	 * Create the panel.
	 */
	public AdminSidebar() {
		setBounds(10, 10, 200, 550);
		setBackground(new Color(0, 102, 255));
		setLayout(null);
		
		schedule = new JLabel("회원정보");
		schedule.setForeground(new Color(255, 255, 255));
		schedule.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		schedule.setBounds(39, 44, 162, 28);
		add(schedule);
		
		classinfo = new JLabel("수강관리");
		classinfo.setForeground(new Color(255, 255, 255));
		classinfo.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		classinfo.setBounds(39, 150, 162, 28);
		add(classinfo);
		
		userinfo = new JLabel("수강별관리");
		userinfo.setForeground(new Color(255, 255, 255));
		userinfo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		userinfo.setBounds(69, 188, 92, 15);
		add(userinfo);
		
		classqna = new JLabel("신규수강관리");
		classqna.setForeground(new Color(255, 255, 255));
		classqna.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		classqna.setBounds(69, 223, 107, 15);
		add(classqna);
		
		btnNewButton = new JButton("로그아웃");
		btnNewButton.setBounds(29, 498, 148, 33);
		add(btnNewButton);
		
		member = new JLabel("회원관리");
		member.setForeground(Color.WHITE);
		member.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		member.setBounds(69, 82, 92, 15);
		add(member);
		
		teacher = new JLabel("강사관리");
		teacher.setForeground(Color.WHITE);
		teacher.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		teacher.setBounds(69, 110, 92, 15);
		add(teacher);
		
		JLabel salesTitle = new JLabel("매출관리");
		salesTitle.setForeground(Color.WHITE);
		salesTitle.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		salesTitle.setBounds(39, 264, 162, 28);
		add(salesTitle);
		
		JLabel sales = new JLabel("매출관리");
		sales.setForeground(Color.WHITE);
		sales.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		sales.setBounds(64, 305, 97, 15);
		add(sales);
		
		JLabel reclass = new JLabel("재등록 강의률");
		reclass.setForeground(Color.WHITE);
		reclass.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		reclass.setBounds(69, 342, 107, 15);
		add(reclass);
		
		JLabel locker = new JLabel("락커룸");
		locker.setForeground(Color.WHITE);
		locker.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		locker.setBounds(39, 381, 162, 28);
		add(locker);
	}
	
	public JLabel getMember() {
		return member;
	}
	public void setMember(JLabel member) {
		this.member = member;
	}
	public JLabel getTeacher() {
		return teacher;
	}
	public void setTeacher(JLabel teacher) {
		this.teacher = teacher;
	}
	
	
}
