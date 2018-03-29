package kr.or.dgit.pool_java.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.or.dgit.pool_java.dao.MemberDao;
import kr.or.dgit.pool_java.dto.Member;
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
	

	/**
	 * Create the panel.
	 */
	public StancePanel() {
		setLayout(null);
		this.mDao = MemberService.getInstance();
		JPanel panel = new JPanel();
		panel.setBounds(44, 82, 232, 299);
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel noP = new JPanel();
		panel.add(noP);
		noP.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel noLbl = new JLabel("회원번호");
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
		nameTf.setColumns(10);
		nameP.add(nameTf);
		
		JPanel ageP = new JPanel();
		panel.add(ageP);
		ageP.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel ageLbl = new JLabel("나이");
		ageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		ageP.add(ageLbl);
		
		ageTf = new JTextField();
		ageTf.setColumns(10);
		ageP.add(ageTf);
		
		JPanel tellP = new JPanel();
		panel.add(tellP);
		tellP.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel tellLbl = new JLabel("전화번호");
		tellLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tellP.add(tellLbl);
		
		tellTf = new JTextField();
		tellTf.setColumns(10);
		tellP.add(tellTf);
		
		JPanel genderP = new JPanel();
		panel.add(genderP);
		genderP.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel genderLbl = new JLabel("성별");
		genderLbl.setHorizontalAlignment(SwingConstants.CENTER);
		genderP.add(genderLbl);
		
		genderTf = new JTextField();
		genderTf.setColumns(10);
		genderP.add(genderTf);
		
		JPanel todayP = new JPanel();
		panel.add(todayP);
		todayP.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel toDayLbl = new JLabel("입장시간");
		toDayLbl.setHorizontalAlignment(SwingConstants.CENTER);
		todayP.add(toDayLbl);
		
		todayTf = new JTextField();
		todayTf.setColumns(10);
		todayP.add(todayTf);

		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(664, 10, 193, 62);
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(374, 82, 494, 458);
		add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		
		for(int i=0;i<100;i++) {
			JPanel panel_3 = new JPanel();
			panel_2.add(panel_3);
			panel_3.setLayout(new GridLayout(0, 1, 0, 0));
			JTextField textField = new JTextField();
			panel_3.add(textField);
			textField.setColumns(10);
			textField.setText(String.valueOf(i));
		}
		
		
		noTf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Member member = mDao.selectMno(Integer.parseInt(noTf.getText()));
				nameTf.setText(member.getName());
				ageTf.setText(member.getName());
				tellTf.setText(member.getTell());
				genderTf.setText(member.getGender());
				SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				todayTf.setText(sf.format(new Date()));
				
			}
		});
		
		
		
		
	}
	
	
	
}
