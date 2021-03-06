package kr.or.dgit.pool_java.content;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import kr.or.dgit.pool_java.frame.MemberFrame;

import javax.swing.SwingConstants;

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
	private JLabel attend;
	public JLabel getAttend() {
		return attend;
	}



	public void setAttend(JLabel attend) {
		this.attend = attend;
	}



	private JPanel pClassBg;
	private JPanel pNewClass;
	private JPanel pSalesBg;
	private JLabel lLockerBg;
	private JLabel lAttendanceBg;
	private JPanel pTeacherBg;
	private JPanel pUserBg;
	private JLabel salesTitle;

	public AdminSidebar() {
		setBounds(10, 10, 236, 550);
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
		schedule.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(schedule);
		
		classqna = new JLabel("신규수강관리");
		classqna.setForeground(new Color(255, 255, 255));
		classqna.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		classqna.setBounds(70, 309, 107, 15);
		classqna.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(classqna);
		
		btnNewButton = new JButton("로그아웃");
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		btnNewButton.setBounds(29, 498, 148, 33);
		add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
			}
		});
		
		member = new JLabel("회원관리");
		member.setForeground(Color.WHITE);
		member.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		member.setBounds(70, 160, 92, 15);
		member.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(member);
		
		teacher = new JLabel("강사관리");
		teacher.setForeground(Color.WHITE);
		teacher.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		teacher.setBounds(70, 196, 92, 15);
		teacher.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(teacher);
		
		salesTitle = new JLabel("매출관리");
		salesTitle.setForeground(Color.WHITE);
		salesTitle.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		salesTitle.setBounds(39, 353, 162, 28);
		add(salesTitle);
		
		sales = new JLabel("매출관리");
		sales.setForeground(Color.WHITE);
		sales.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		sales.setBounds(69, 391, 97, 15);
		sales.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(sales);

		
		attend = new JLabel("출석부");
		attend.setForeground(Color.WHITE);
		attend.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		attend.setBounds(39, 72, 162, 28);
		attend.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(attend);
		
		come = new JLabel("입장");
		come.setForeground(Color.WHITE);
		come.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		come.setBounds(39, 28, 162, 28);
		come.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(come);
		
		pTeacherBg = new JPanel();
		pTeacherBg.setBackground(new Color(102, 153, 255));
		pTeacherBg.setBounds(70, 190, 166, 28);
		add(pTeacherBg);
		
		pUserBg = new JPanel();
		pUserBg.setBackground(new Color(102, 153, 255));
		pUserBg.setBounds(70, 155, 166, 28);
		add(pUserBg);
		
		pClassBg = new JPanel();
		pClassBg.setBackground(new Color(102, 153, 255));
		pClassBg.setBounds(70, 271, 166, 28);
		add(pClassBg);
		
		pNewClass = new JPanel();
		pNewClass.setBackground(new Color(102, 153, 255));
		pNewClass.setBounds(70, 305, 166, 28);
		add(pNewClass);
		
		pSalesBg = new JPanel();
		pSalesBg.setBackground(new Color(102, 153, 255));
		pSalesBg.setBounds(70, 386, 166, 28);
		add(pSalesBg);
		
		lLockerBg = new JLabel("");
		lLockerBg.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		lLockerBg.setHorizontalAlignment(SwingConstants.TRAILING);
		lLockerBg.setBounds(27, 32, 10, 22);
		add(lLockerBg);
		
		lAttendanceBg = new JLabel("");
		lAttendanceBg.setHorizontalAlignment(SwingConstants.TRAILING);
		lAttendanceBg.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		lAttendanceBg.setBounds(27, 76, 10, 22);
		add(lAttendanceBg);
		bgSetVisbleFalse();
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
	
	public void selectItem(String text) {
		bgSetVisbleFalse();
		switch(text) {
		case "입장" :
			lLockerBg.setVisible(true);
			break;
		case "출석부":
			lAttendanceBg.setVisible(true);
			break;
		case "회원관리":
			pUserBg.setVisible(true);
			break;
		case "강사관리":
			pTeacherBg.setVisible(true);
			break;
		case "수강별관리":
			pClassBg.setVisible(true);
			break;
		case "신규수강관리":
			pNewClass.setVisible(true);
			break;
		case "매출관리":
			pSalesBg.setVisible(true);
			break;
		}
	}
	
	
	public JLabel getSalesTitle() {
		return salesTitle;
	}



	private void bgSetVisbleFalse() {
		pTeacherBg.setVisible(false);
		pUserBg.setVisible(false);
		pClassBg.setVisible(false);
		pNewClass.setVisible(false);
		pSalesBg.setVisible(false);
		lLockerBg.setVisible(false);
		lAttendanceBg.setVisible(false);
	}
}
