package kr.or.dgit.pool_java.content;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.imageio.spi.RegisterableService;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.dto.Member;
import kr.or.dgit.pool_java.dto.Register;
import kr.or.dgit.pool_java.service.ClassService;
import kr.or.dgit.pool_java.service.MemberService;
import kr.or.dgit.pool_java.service.RegisterService;


public class MemberContent extends JPanel {
	private JTable table;
	private JTextField searchField;
	private JTextField mno;
	private JTextField name;
	private JTextField tell1;
	private JTextField email1;
	private JTextField tell2;
	private JTextField tell3;
	private JTextField emailAddr;
	private JComboBox<String> emailCombo;
	private JComboBox<String> classCombo;
	private JRadioButton menRadio;
	private JRadioButton womenRadio;
	private JTextField regdatetf;
	private JComboBox<String> year;
	private JComboBox<String> month;
	private JComboBox<String> day;
	private JButton memupdate;
	private JButton addBtn;
	private JButton classupdate;
	private JButton backBtn;
	
	/**
	 * Create the panel.
	 */
	public MemberContent() {
		setBounds(0, 0, 900, 570);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
	
		scrollPane.setBounds(0, 55, 900, 248);
		add(scrollPane);
		
		table = new JTable();
	
		loadData();

		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 310, 900, 255);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblmno = new JLabel("회원번호");
		lblmno.setBounds(41, 26, 57, 15);
		panel.add(lblmno);
		
		mno = new JTextField();
		mno.setEnabled(false);
		mno.setBounds(134, 26, 183, 30);
		panel.add(mno);
	
		mno.setColumns(10);
		
		JLabel lblname = new JLabel("이름");
		lblname.setBounds(41, 87, 57, 15);
		panel.add(lblname);
		
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(134, 80, 183, 30);
		panel.add(name);
		
		JLabel label_1 = new JLabel("생년월일");
		label_1.setBounds(375, 87, 57, 15);
		panel.add(label_1);
		
		tell1 = new JTextField();
		tell1.setColumns(10);
		tell1.setBounds(134, 134, 66, 30);
		panel.add(tell1);
		
		JLabel lbltell = new JLabel("전화번호");
		lbltell.setBounds(41, 141, 57, 15);
		panel.add(lbltell);
		
		JLabel label_3 = new JLabel("이메일");
		label_3.setBounds(41, 197, 57, 15);
		panel.add(label_3);
		
		email1 = new JTextField();
		email1.setColumns(10);
		email1.setBounds(134, 190, 124, 30);
		panel.add(email1);
		
		JLabel label_4 = new JLabel("성별");
		label_4.setBounds(375, 33, 57, 15);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("반 번호");
		label_5.setBounds(719, 37, 57, 15);
		panel.add(label_5);
		
		
		year = new JComboBox<String>();
		year.setBounds(438, 82, 81, 30);
		panel.add(year);
		
		month = new JComboBox<String>();
		month.setBounds(520, 82, 70, 30);
		panel.add(month);
		
		day = new JComboBox<String>();
		day.setBounds(591, 82, 70, 30);
		panel.add(day);
		
		
		tell2 = new JTextField();
		tell2.setColumns(10);
		tell2.setBounds(211, 134, 66, 30);
		panel.add(tell2);
		
		tell3 = new JTextField();
		tell3.setColumns(10);
		tell3.setBounds(289, 134, 66, 30);
		panel.add(tell3);
		
		
		menRadio = new JRadioButton("남");
		menRadio.setSelected(true);
		menRadio.setBounds(442, 34, 66, 23);
		panel.add(menRadio);
		
		womenRadio = new JRadioButton("여");
		womenRadio.setBounds(510, 34, 66, 23);
		panel.add(womenRadio);
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(menRadio);
		group.add(womenRadio);
		
		classCombo = new JComboBox<>();
		classCombo.setBounds(798, 29, 81, 30);
		panel.add(classCombo);
		getClassCombo();
		
		JLabel lbl1 = new JLabel("-");
		lbl1.setBounds(203, 140, 13, 15);
		panel.add(lbl1);
		
		
		JLabel lbl2 = new JLabel("-");
		lbl2.setBounds(280, 141, 13, 15);
		panel.add(lbl2);
		
		JLabel label_8 = new JLabel("@");
		label_8.setBounds(259, 197, 13, 15);
		panel.add(label_8);
		
		emailAddr = new JTextField();
		emailAddr.setColumns(10);
		emailAddr.setBounds(270, 190, 124, 30);
		panel.add(emailAddr);
		
		emailCombo = new JComboBox<>();
		emailCombo.setBounds(396, 190, 130, 30);
		emailCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(emailCombo.getSelectedIndex()==0) {
					emailAddr.setText("");
				}else {
					emailAddr.setText((String)emailCombo.getSelectedItem());
				}
			}
		});
		emailCombo.addItem("직접입력");
		emailCombo.addItem("naver.com");
		emailCombo.addItem("gmail.com");
		emailCombo.addItem("nate.com");
		emailCombo.addItem("daum.net");
		
		
		panel.add(emailCombo);
		
		
		JLabel regdate = new JLabel("등록날짜");
		regdate.setBounds(375, 141, 57, 15);
		panel.add(regdate);
		
		regdatetf = new JTextField();
		regdatetf.setEnabled(false);
		regdatetf.setBounds(438, 134, 91, 30);
		panel.add(regdatetf);
		regdatetf.setColumns(10);
		Date date = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		regdatetf.setText(sdf.format(date));
		
		year.addItem("선택");
		for(int i=2018;i>1949;i--) {
			year.addItem(i+"");
		}
		month.addItem("선택");
		for(int i=1;i<13;i++) {
			if(i<10) {
				month.addItem("0"+i+"");
			}else {
				month.addItem(i+"");
			}
			
		}
		day.addItem("선택");
		for(int i=1;i<32;i++) {
			if(i<10) {
				day.addItem("0"+i+"");
			}else {
				day.addItem(i+"");
			}
		
		}
		
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(12, 10, 138, 35);
		add(comboBox);
		comboBox.addItem("전체보기");
		comboBox.addItem("회원번호");
		comboBox.addItem("이름");
		comboBox.addItem("수강반");
		
		searchField = new JTextField();
		searchField.setBounds(149, 10, 630, 35);
		add(searchField);
		searchField.setColumns(10);
		searchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					String word = searchField.getText();
					
					if(word.equals("")||comboBox.getSelectedItem().equals("전체보기")) {
						loadData();
					}
					
					if(comboBox.getSelectedItem().equals("회원번호")) {
						loadSearchData("searchMno",word);
					}
					
					if(comboBox.getSelectedItem().equals("이름")) {
						loadSearchData("searchName",word);
					}
					
					if(comboBox.getSelectedItem().equals("수강반")) {
						
					}
					
				}
			}
			
		});
		
		JButton searchBtn = new JButton("검색");
		searchBtn.setBounds(791, 10, 97, 35);
		add(searchBtn);
		
		addPopupMenu();
		
		addBtn = new JButton("신규등록");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberService.getInstance().insertMember(sendMemberData("insert"));
				loadData();
			}
		});
		addBtn.setBounds(791, 190, 97, 30);
		panel.add(addBtn);
		
		memupdate = new JButton("회원정보수정");
		memupdate.setBounds(591, 190, 117, 30);
		panel.add(memupdate);
		
		classupdate = new JButton("수강반 수정");
		classupdate.setBounds(710, 190, 110, 30);
		panel.add(classupdate);
		
		backBtn = new JButton("취소");
		backBtn.setBounds(822, 190, 66, 30);
		panel.add(backBtn);
		
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearText();
				addBtn.setVisible(true);
				memupdate.setVisible(false);
				classupdate.setVisible(false);
				backBtn.setVisible(false);
			}
		});
		
		memupdate.setVisible(false);
		memupdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MemberService.getInstance().updateMember(sendMemberData("update"));
				memupdate.setVisible(false);
				classupdate.setVisible(false);
				backBtn.setVisible(false);
				addBtn.setVisible(true);
				loadData();
				
			}
		});
		classupdate.setVisible(false);
		
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					getMemberData();
					addBtn.setVisible(false);
					memupdate.setVisible(true);
					classupdate.setVisible(true);
					backBtn.setVisible(true);
				}
			}
			
		});
	}
	

	private String[] getColumnNames() {

		return new String[] { "회원번호", "이름", "생년월일","전화번호","이메일","성별","등록날짜"};
	}
	private Object[][] getData() {
		List<Member> list = MemberService.getInstance().selectAll();
		
		Object[][] data = new Object[list.size()][];
		
		for (int i = 0; i <list.size(); i++) {
			
			data[i] = list.get(i).toArray();
			
		}
		return data;
	}
	
	
	private Object[][] getSearchData(String type,String keyword) {
		List<Member> list =null;
		if(type.equals("searchName")) {
			list = MemberService.getInstance().selectSearchName("%"+keyword+"%");
		}
		if(type.equals("searchMno")) {
			list = MemberService.getInstance().selectSearchMno("%"+keyword+"%");
		}
	
		Object[][] data = new Object[list.size()][];
		
		for (int i = 0; i <list.size(); i++) {
			
			data[i] = list.get(i).toArray();
			
		}
		return data;
	}
	
	
	
	private void addPopupMenu() {
	      JPopupMenu popupMenu = new JPopupMenu();
	      JMenuItem menuItem = new JMenuItem("수정");
	      JMenuItem menuItem2 = new JMenuItem("삭제");
	      JMenuItem menuItem3 = new JMenuItem("재등록");
	      
	      popupMenu.add(menuItem);
	      popupMenu.add(menuItem2);
	      popupMenu.add(menuItem3);
	      
	      table.setComponentPopupMenu(popupMenu);
	      menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getMemberData();
				addBtn.setVisible(false);
				memupdate.setVisible(true);
				classupdate.setVisible(true);
				backBtn.setVisible(true);
			}
		});      
	      menuItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "회원을 삭제하시겠습니까?","회원정보 삭제",JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
				if(reply==JOptionPane.YES_OPTION) {
					int row = table.getSelectedRow();
					int mno =(int)table.getValueAt(row, 0);
					MemberService.getInstance().deleteMember(mno);
					loadData();
				}
			}
		});
	      
	      menuItem3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

	   
	}
	
	private void getClassCombo() {
		
		List<Class> list = null;
		
		Calendar c = Calendar.getInstance();
		if(c.get(Calendar.DATE)<=10) {
			list = ClassService.getInstance().selectBytoMonth("");
		}else if(c.get(Calendar.DATE)>=20) {
			list = ClassService.getInstance().selectByNextMonth("");
		}
		
		classCombo.addItem("선택");
		for(int i=0;i<list.size();i++) {
			classCombo.addItem(list.get(i).getCno()+"");
		}

	}
	
	private void loadData() {
		DefaultTableModel model = new DefaultTableModel(getData(),getColumnNames()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
	
		table.setModel(model);
	}
	
	private void loadSearchData(String type,String keyword) {
		
		DefaultTableModel model = new DefaultTableModel(getSearchData(type,keyword),getColumnNames()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
	
		table.setModel(model);
	}
	private Member sendMemberData(String type) {
		
		String y = year.getSelectedItem().toString();
		String m = month.getSelectedItem().toString();
		String d = day.getSelectedItem().toString();
		String a = y+m+d;
	
			int age = Integer.parseInt(a);
		
		
		String tell = tell1.getText()+"-"+tell2.getText()+"-"+tell3.getText();
		String gender="";
		
		if(menRadio.isSelected()) {
			gender = "남";
		}else {
			gender = "여";
		}
		
		String email = email1.getText()+"@"+emailAddr.getText();
		Member mem = null;
		
		if(type.equals("insert")) {
			mem = new Member(name.getText(), age, tell, email, gender, a);
			Register  register = new Register();
			register.setCno(mem.getMno());
			register.setReentrance(false);
			RegisterService.getInstance().insertRegister(register);
			
		}else if(type.equals("update")) {
			mem = new Member(Integer.parseInt(mno.getText()), name.getText(), age, tell, email, gender);
		}
			
		name.setText("");
		tell1.setText("");
		tell2.setText("");
		tell3.setText("");
		email1.setText("");
		emailAddr.setText("");
		emailCombo.setSelectedIndex(0);
		year.setSelectedIndex(0);
		month.setSelectedIndex(0);
		day.setSelectedIndex(0);
		classCombo.setSelectedIndex(0);
		menRadio.setSelected(true);
		mno.setText("");
		
		return mem;
	}
	
	private void getMemberData() {
		int row = table.getSelectedRow();
		int no =(int)table.getValueAt(row, 0); 
		

		Member m = MemberService.getInstance().selectMno(no);
		
		mno.setText(m.getMno()+"");
		name.setText(m.getName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		regdatetf.setText(sdf.format(m.getDate()));
		
		if(m.getGender().equals("남")) {
			womenRadio.setSelected(false);
			menRadio.setSelected(true);
		}else if(m.getGender().equals("여")){
			menRadio.setSelected(false);
			womenRadio.setSelected(true);
		}
		
		String total = String.valueOf(m.getAge());
		String y = total.substring(0, 4);
		year.setSelectedItem(y);
		
		String m2 = total.substring(4,6);
		month.setSelectedItem(m2);
		
		String d = total.substring(6,total.length());
		day.setSelectedItem(d);
		
		String totale = m.getEmail();
		String em = totale.substring(0, totale.indexOf("@"));
		email1.setText(em);
		
		String emaddr = totale.substring(totale.indexOf("@")+1);
		emailAddr.setText(emaddr);
		
		String phone = m.getTell();
		String t1 = phone.substring(0, phone.indexOf("-"));
		String t2 = phone.substring(phone.indexOf("-")+1,phone.lastIndexOf("-"));
		String t3 = phone.substring(phone.lastIndexOf("-")+1);
		tell1.setText(t1);
		tell2.setText(t2);
		tell3.setText(t3);
	}
	
	private void clearText() {
		name.setText("");
		tell1.setText("");
		tell2.setText("");
		tell3.setText("");
		email1.setText("");
		emailAddr.setText("");
		emailCombo.setSelectedIndex(0);
		year.setSelectedIndex(0);
		month.setSelectedIndex(0);
		day.setSelectedIndex(0);
		classCombo.setSelectedIndex(0);
		menRadio.setSelected(true);
		mno.setText("");
	}
	

	
}

