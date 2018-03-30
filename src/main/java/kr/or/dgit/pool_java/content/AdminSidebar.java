package kr.or.dgit.pool_java.content;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

public class AdminSidebar extends JPanel {
	private JLabel userinfo;
	private JLabel classinfo;
	private JLabel schedule;
	private JLabel classqna;
	private JButton btnNewButton;
	private JLabel member;
	private JLabel teacher;
	private JLabel come;
	private JLabel sales;
	/**
	 * Create the panel.
	 */
	public AdminSidebar() {
		setBounds(10, 10, 200, 550);
		setBackground(new Color(0, 102, 255));
		setLayout(null);
		
		userinfo = new JLabel("회원정보");
		userinfo.setForeground(new Color(255, 255, 255));
		userinfo.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		userinfo.setBounds(39, 122, 162, 28);
		add(userinfo);
		
		classinfo = new JLabel("수강관리");
		classinfo.setForeground(new Color(255, 255, 255));
		classinfo.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		classinfo.setBounds(39, 237, 162, 28);
		add(classinfo);
		
		schedule = new JLabel("수강별관리");
		schedule.setForeground(new Color(255, 255, 255));
		schedule.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		schedule.setBounds(70, 275, 92, 15);
		add(schedule);
		
		classqna = new JLabel("신규수강관리");
		classqna.setForeground(new Color(255, 255, 255));
		classqna.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		classqna.setBounds(70, 309, 107, 15);
		add(classqna);
		
		btnNewButton = new JButton("로그아웃");
		btnNewButton.setBounds(29, 498, 148, 33);
		add(btnNewButton);
		
		member = new JLabel("회원관리");
		member.setForeground(Color.WHITE);
		member.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		member.setBounds(70, 160, 92, 15);
		add(member);
		
		teacher = new JLabel("강사관리");
		teacher.setForeground(Color.WHITE);
		teacher.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		teacher.setBounds(70, 196, 92, 15);
		add(teacher);
		
		JLabel salesTitle = new JLabel("매출관리");
		salesTitle.setForeground(Color.WHITE);
		salesTitle.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		salesTitle.setBounds(39, 353, 162, 28);
		add(salesTitle);
		
		sales = new JLabel("매출관리");
		sales.setForeground(Color.WHITE);
		sales.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		sales.setBounds(69, 391, 97, 15);
		add(sales);
		

		JLabel reclass = new JLabel("재등록 강의률");
		reclass.setForeground(Color.WHITE);
		reclass.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		reclass.setBounds(69, 427, 107, 15);
		add(reclass);

		
		JLabel locker = new JLabel("출석부");
		locker.setForeground(Color.WHITE);
		locker.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		locker.setBounds(39, 72, 162, 28);
		add(locker);
		
		come = new JLabel("입장");
		come.setForeground(Color.WHITE);
		come.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		come.setBounds(39, 28, 162, 28);
		add(come);
	}
	
	
	
	public JLabel getSales() {
		return sales;
	}



	public void setSales(JLabel sales) {
		this.sales = sales;
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

	public JLabel getSchedule() {
		return schedule;
	}

	public void setSchedule(JLabel schedule) {
		this.schedule = schedule;
	}

	public JLabel getClassqna() {
		return classqna;
	}

	public void setClassqna(JLabel classqna) {
		this.classqna = classqna;
	}

	public JLabel getCome() {
		return come;
	}

	public void setCome(JLabel come) {
		this.come = come;
	}
	
	
	
	
}
