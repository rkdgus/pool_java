package kr.or.dgit.pool_java.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import kr.or.dgit.pool_java.dao.AttendanceDao;
import kr.or.dgit.pool_java.dao.LockerDao;
import kr.or.dgit.pool_java.dao.MemberDao;
import kr.or.dgit.pool_java.dto.Attendance;
import kr.or.dgit.pool_java.dto.Member;
import kr.or.dgit.pool_java.service.AttendanceService;
import kr.or.dgit.pool_java.service.LockerService;
import kr.or.dgit.pool_java.service.MemberService;

public class StancePanel extends JPanel {
	private JTextField noTf;
	private JTextField nameTf;
	private JTextField ageTf;
	private JTextField tellTf;
	private JTextField genderTf;
	private JTextField todayTf;
	private JTextField textField;
	private MemberDao mDao;
	private AttendanceDao aDao;
	private LockerDao lDao;
	

	/**
	 * Create the panel.
	 */
	public StancePanel() {
		setLayout(null);
		this.mDao = MemberService.getInstance();
		this.aDao = AttendanceService.getInstance();
		this.lDao = LockerService.getInstance();
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(28, 70, 232, 299);
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 1));
		
		JPanel noP = new JPanel();
		panel.add(noP);
		noP.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel noLbl = new JLabel("회원번호");
		noLbl.setBackground(Color.LIGHT_GRAY);
		noLbl.setHorizontalAlignment(SwingConstants.CENTER);
		noP.add(noLbl);
		
		noTf = new JTextField();
		
		noP.add(noTf);
		noTf.setColumns(10);
		
		JPanel nameP = new JPanel();
		panel.add(nameP);
		nameP.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel nameLbl = new JLabel("이름");
		nameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nameP.add(nameLbl);
		
		nameTf = new JTextField();
		nameTf.setEnabled(false);
		nameTf.setColumns(10);
		nameP.add(nameTf);
		
		JPanel ageP = new JPanel();
		panel.add(ageP);
		ageP.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel ageLbl = new JLabel("나이");
		ageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		ageP.add(ageLbl);
		
		ageTf = new JTextField();
		ageTf.setEnabled(false);
		ageTf.setColumns(10);
		ageP.add(ageTf);
		
		JPanel tellP = new JPanel();
		panel.add(tellP);
		tellP.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel tellLbl = new JLabel("전화번호");
		tellLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tellP.add(tellLbl);
		
		tellTf = new JTextField();
		tellTf.setEnabled(false);
		tellTf.setColumns(10);
		tellP.add(tellTf);
		
		JPanel genderP = new JPanel();
		panel.add(genderP);
		genderP.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel genderLbl = new JLabel("성별");
		genderLbl.setHorizontalAlignment(SwingConstants.CENTER);
		genderP.add(genderLbl);
		
		genderTf = new JTextField();
		genderTf.setEnabled(false);
		genderTf.setColumns(10);
		genderP.add(genderTf);
		
		JPanel todayP = new JPanel();
		panel.add(todayP);
		todayP.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel toDayLbl = new JLabel("입장시간");
		toDayLbl.setHorizontalAlignment(SwingConstants.CENTER);
		todayP.add(toDayLbl);
		
		todayTf = new JTextField();
	
	
	
		
		todayTf.setEnabled(false);
		todayTf.setColumns(10);
		todayP.add(todayTf);
		
		noTf.setFocusable(true);

		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(678, 10, 193, 49);
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(278, 46, 610, 494);
		add(tabbedPane);
		
		JPanel men = new JPanel();
		tabbedPane.addTab("남자 탈의실", null, men, null);
		men.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel women = new JPanel();
		tabbedPane.addTab("여자 탈의실", null, women, null);
		women.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		for(int i=0;i<10;i++) {
			JPanel panel_3 = new JPanel();
			men.add(panel_3);
			panel_3.setLayout(new GridLayout(0, 1, 0, 0));
			for(int j=1;j<=10;j++) {
				JButton btn = new JButton(String.valueOf(i*10+j));
				btn.setBorder(new LineBorder(Color.gray));
				btn.setOpaque(true);
				btn.setBackground(Color.WHITE);
				btn.setHorizontalAlignment(SwingConstants.CENTER);
				panel_3.add(btn);
			
			}
			
		}
		
		
		
		for(int i=0;i<10;i++) {
			JPanel panel_3 = new JPanel();
			women.add(panel_3);
			panel_3.setLayout(new GridLayout(0, 1, 0, 0));
			for(int j=1;j<=10;j++) {
				
				JButton btn = new JButton(String.valueOf(i*10+j));
				btn.setBorder(new LineBorder(Color.gray));
				btn.setOpaque(true);
				btn.setBackground(Color.WHITE);
				btn.setHorizontalAlignment(SwingConstants.CENTER);
				panel_3.add(btn);
			}
			
		}
		
		
		noTf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Member member = mDao.selectMno(Integer.parseInt(noTf.getText()));
				if(member == null) {
					JOptionPane.showMessageDialog(null, "존재하지 않는 회원입니다.");
					clear(); 
					return;
				}
				nameTf.setText(member.getName());
				ageTf.setText(String.valueOf(member.getAge()));
				tellTf.setText(member.getTell());
				genderTf.setText(member.getGender());
				SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				todayTf.setText(sf.format(new Date()));
				Attendance attendance = new Attendance();
				attendance.setDate(new Date());
				attendance.setMno(member.getMno());
				aDao.insertAttendance(attendance);
			}
		});

	}

	public void clear() {
		noTf.setText("");
		nameTf.setText("");
		ageTf.setText("");
		tellTf.setText("");
		genderTf.setText("");
		todayTf.setText("");
		noTf.setFocusable(true);
	}
}
