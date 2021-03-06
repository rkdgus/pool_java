package kr.or.dgit.pool_java.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import kr.or.dgit.pool_java.dao.SalesDao;
import kr.or.dgit.pool_java.dto.Attendance;
import kr.or.dgit.pool_java.dto.Locker;
import kr.or.dgit.pool_java.dto.Member;
import kr.or.dgit.pool_java.dto.Sales;
import kr.or.dgit.pool_java.service.AttendanceService;
import kr.or.dgit.pool_java.service.LockerService;
import kr.or.dgit.pool_java.service.MemberService;
import kr.or.dgit.pool_java.service.SalesService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class StancePanel extends JPanel {
	private JTextField noTf;
	private JTextField nameTf;
	private JTextField ageTf;
	private JTextField tellTf;
	private JTextField genderTf;
	private JTextField todayTf;
	private MemberDao mDao;
	private AttendanceDao aDao;
	private LockerDao lDao;
	private SalesDao sDao;

	/**
	 * Create the panel.
	 */
	public StancePanel() {
		setLayout(null);
		this.mDao = MemberService.getInstance();
		this.aDao = AttendanceService.getInstance();
		this.lDao = LockerService.getInstance();
		this.sDao = SalesService.getInstance();

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(28, 70, 232, 299);
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 1));

		JPanel noP = new JPanel();
		panel.add(noP);
		noP.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel noLbl = new JLabel("회원번호");
		noLbl.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		noLbl.setBackground(Color.LIGHT_GRAY);
		noLbl.setHorizontalAlignment(SwingConstants.CENTER);
		noP.add(noLbl);

		noTf = new JTextField();
		noTf.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		noP.add(noTf);
		noTf.setColumns(10);

		JPanel nameP = new JPanel();
		panel.add(nameP);
		nameP.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel nameLbl = new JLabel("이름");
		nameLbl.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		nameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nameP.add(nameLbl);

		nameTf = new JTextField();
		nameTf.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		nameTf.setEnabled(false);
		nameTf.setColumns(10);
		nameP.add(nameTf);

		JPanel ageP = new JPanel();
		panel.add(ageP);
		ageP.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel ageLbl = new JLabel("나이");
		ageLbl.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		ageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		ageP.add(ageLbl);

		ageTf = new JTextField();
		ageTf.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		ageTf.setEnabled(false);
		ageTf.setColumns(10);
		ageP.add(ageTf);

		JPanel tellP = new JPanel();
		panel.add(tellP);
		tellP.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel tellLbl = new JLabel("전화번호");
		tellLbl.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		tellLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tellP.add(tellLbl);

		tellTf = new JTextField();
		tellTf.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tellTf.setEnabled(false);
		tellTf.setColumns(10);
		tellP.add(tellTf);

		JPanel genderP = new JPanel();
		panel.add(genderP);
		genderP.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel genderLbl = new JLabel("성별");
		genderLbl.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		genderLbl.setHorizontalAlignment(SwingConstants.CENTER);
		genderP.add(genderLbl);

		genderTf = new JTextField();
		genderTf.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		genderTf.setEnabled(false);
		genderTf.setColumns(10);
		genderP.add(genderTf);

		JPanel todayP = new JPanel();
		panel.add(todayP);
		todayP.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel toDayLbl = new JLabel("입장시간");
		toDayLbl.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		toDayLbl.setHorizontalAlignment(SwingConstants.CENTER);
		todayP.add(toDayLbl);

		todayTf = new JTextField();
		todayTf.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		todayTf.setEnabled(false);
		todayTf.setColumns(10);
		todayP.add(todayTf);

		noTf.setFocusable(true);

		ClockPanel panel_1 = new ClockPanel();
		panel_1.setBounds(678, 10, 193, 49);
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(278, 46, 610, 494);
		add(tabbedPane);

		JPanel men = new JPanel();
		men.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tabbedPane.addTab("남자 탈의실", null, men, null);
		men.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel women = new JPanel();
		women.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tabbedPane.addTab("여자 탈의실", null, women, null);
		women.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton = new JButton("일일 입장");
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Sales sales = new Sales();
					sales.setPay(2200);
				
				sDao.insertSales(sales);
			}
		});
		
		btnNewButton.setBounds(80, 418, 135, 38);
		add(btnNewButton);

		for (int i = 0; i < 10; i++) {
			JPanel panel_3 = new JPanel();
			men.add(panel_3);
			panel_3.setLayout(new GridLayout(0, 1, 0, 0));
			for (int j = 1; j <= 10; j++) {
				JButton btn = new JButton(String.valueOf(i * 10 + j));
				btn.setBorder(new LineBorder(Color.gray));
				btn.setOpaque(true);
				btn.setBackground(Color.WHITE);
				btn.setHorizontalAlignment(SwingConstants.CENTER);
				Locker locker = new Locker();
				locker.setLno(Integer.parseInt(btn.getText()));
				locker.setGender("남");
				locker = lDao.selectGender(locker);
				if (locker.isAble()) {
					btn.setBackground(Color.LIGHT_GRAY);
				}

				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (btn.getBackground() == Color.WHITE) {
							int i = JOptionPane.showConfirmDialog(null, "사용 하시겠습니까?");
							if (i == 0) {
								Locker locker = new Locker();
								locker.setLno(Integer.parseInt(btn.getText()));
								locker.setAble(true);
								lDao.updateLocker(locker);
								btn.setBackground(Color.LIGHT_GRAY);

							}
						} else {
							int i = JOptionPane.showConfirmDialog(null, "퇴실 하시겠습니까?");
							if (i == 0) {
								Locker locker = new Locker();
								locker.setLno(Integer.parseInt(btn.getText()));
								locker.setAble(false);
								lDao.updateLocker(locker);
								btn.setBackground(Color.WHITE);

							}
						}

					}
				});
				panel_3.add(btn);

			}

		}

		for (int i = 0; i < 10; i++) {
			JPanel panel_3 = new JPanel();
			women.add(panel_3);
			panel_3.setLayout(new GridLayout(0, 1, 0, 0));
			for (int j = 1; j <= 10; j++) {

				JButton btn = new JButton(String.valueOf(i * 10 + j));
				btn.setBorder(new LineBorder(Color.gray));
				btn.setOpaque(true);
				btn.setBackground(Color.WHITE);
				btn.setHorizontalAlignment(SwingConstants.CENTER);
				Locker locker = new Locker();
				String lno = btn.getText();

				if (Integer.parseInt(lno) < 10) {
					lno = "0" + lno;
				}
				if(Integer.parseInt(lno)==100) {
					locker.setLno(200);
				}else {
					locker.setLno(Integer.parseInt("1" + lno));
				}
				locker.setGender("여");
				locker = lDao.selectGender(locker);
				if (locker.isAble()) {
					btn.setBackground(Color.LIGHT_GRAY);
				}

				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (btn.getBackground() == Color.WHITE) {
							int i = JOptionPane.showConfirmDialog(null, "사용 하시겠습니까?");
							if (i == 0) {
								Locker locker = new Locker();
								String lno = btn.getText();

								if (Integer.parseInt(lno) < 10) {
									lno = "0" + lno;
								}
								locker.setLno(Integer.parseInt("1" + lno));
								locker.setAble(true);
								lDao.updateLocker(locker);
								btn.setBackground(Color.LIGHT_GRAY);

							}
						} else {
							int i = JOptionPane.showConfirmDialog(null, "퇴실 하시겠습니까?");
							if (i == 0) {
								Locker locker = new Locker();
								String lno = btn.getText();

								if (Integer.parseInt(lno) < 10) {
									lno = "0" + lno;
								}
								locker.setLno(Integer.parseInt("1" + lno));
								locker.setAble(false);
								lDao.updateLocker(locker);
								btn.setBackground(Color.WHITE);

							}
						}

					}
				});
				panel_3.add(btn);
			}

		}

		noTf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Member member = mDao.selectMno(Integer.parseInt(noTf.getText()));
				if (member == null) {
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

				if (member.getGender().equals("남")) {
					tabbedPane.setSelectedIndex(0);
				} else if (member.getGender().equals("여")) {
					tabbedPane.setSelectedIndex(1);
				}

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
