package kr.or.dgit.pool_java.content;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.dto.Member;
import kr.or.dgit.pool_java.dto.Register;
import kr.or.dgit.pool_java.dto.Sales;
import kr.or.dgit.pool_java.service.ClassService;
import kr.or.dgit.pool_java.service.MemberService;
import kr.or.dgit.pool_java.service.RegisterService;
import kr.or.dgit.pool_java.service.SalesService;
import java.awt.Font;
import java.awt.Component;

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
	private JButton reenterBtn;
	private int oldCno=-1;
	private JButton classreupdateBtn;
	private int oldClass=-1;
	/**
	 * Create the panel.
	 */
	public MemberContent() {
		setBounds(0, 0, 900, 570);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		scrollPane.setBounds(0, 55, 900, 248);
		add(scrollPane);

		table = new JTable();
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		loadData();

		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBounds(0, 310, 900, 255);
		add(panel);
		panel.setLayout(null);

		JLabel lblmno = new JLabel("회원번호");
		lblmno.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lblmno.setBounds(41, 26, 57, 15);
		panel.add(lblmno);

		mno = new JTextField();
		mno.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		mno.setBounds(134, 26, 183, 30);
		mno.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					getToolkit().beep();
					e.consume();
				} 
			}
			
		});
		panel.add(mno);
		mno.setColumns(10);
	
		
		JLabel lblname = new JLabel("이름");
		lblname.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lblname.setBounds(41, 87, 57, 15);
		panel.add(lblname);

		name = new JTextField();
		name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		name.setColumns(10);
		name.setBounds(134, 80, 183, 30);
		panel.add(name);

		JLabel label_1 = new JLabel("생년월일");
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_1.setBounds(369, 33, 57, 15);
		panel.add(label_1);

		tell1 = new JTextField();
		tell1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tell1.setColumns(10);
		tell1.setBounds(134, 134, 66, 30);
		tell1.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					getToolkit().beep();
					e.consume();
				} else {
					if (tell1.getText().length() >= 2) {
						tell2.requestFocus();
						if ((c == KeyEvent.VK_BACK_SPACE)||(c == KeyEvent.VK_DELETE)) {
							tell1.requestFocus();
						}
					}
				}

			}
		});
		panel.add(tell1);

		JLabel lbltell = new JLabel("전화번호");
		lbltell.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbltell.setBounds(41, 141, 57, 15);
		panel.add(lbltell);

		JLabel label_3 = new JLabel("이메일");
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_3.setBounds(41, 197, 57, 15);
		panel.add(label_3);

		email1 = new JTextField();
		email1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		email1.setColumns(10);
		email1.setBounds(134, 190, 124, 30);
		panel.add(email1);

		JLabel label_4 = new JLabel("성별");
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_4.setBounds(375, 83, 57, 15);
		panel.add(label_4);

		JLabel label_5 = new JLabel("반 번호");
		label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_5.setBounds(375, 141, 57, 15);
		panel.add(label_5);

		year = new JComboBox<String>();
		year.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		year.setBounds(438, 33, 81, 30);
		panel.add(year);

		month = new JComboBox<String>();
		month.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		month.setBounds(522, 33, 70, 30);
		panel.add(month);

		day = new JComboBox<String>();
		day.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		day.setBounds(593, 33, 70, 30);
		panel.add(day);

		tell2 = new JTextField();
		tell2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tell2.setColumns(10);
		tell2.setBounds(211, 134, 66, 30);
		tell2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					getToolkit().beep();
					e.consume();
				} else {
					if (tell2.getText().length() >= 3) {
						tell3.requestFocus();
						if ((c == KeyEvent.VK_BACK_SPACE)||(c == KeyEvent.VK_DELETE)) {
							tell2.requestFocus();
						}
					}
				}
			}
		});
		panel.add(tell2);

		tell3 = new JTextField();
		tell3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tell3.setColumns(10);
		tell3.setBounds(289, 134, 66, 30);
		tell3.addKeyListener(new KeyAdapter() {
			@Override

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					getToolkit().beep();
					e.consume();
				} else {
					if (tell3.getText().length() >= 3) {
						email1.requestFocus();
						if ((c == KeyEvent.VK_BACK_SPACE)||(c == KeyEvent.VK_DELETE)) {
							tell3.requestFocus();
						}
						
					}
				}

			}
		});
		panel.add(tell3);
		

		menRadio = new JRadioButton("남");
		menRadio.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		menRadio.setSelected(true);
		menRadio.setBounds(440, 79, 66, 23);
		panel.add(menRadio);

		womenRadio = new JRadioButton("여");
		womenRadio.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		womenRadio.setBounds(508, 79, 66, 23);
		panel.add(womenRadio);

		ButtonGroup group = new ButtonGroup();
		group.add(menRadio);
		group.add(womenRadio);

		classCombo = new JComboBox<>();
		classCombo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		classCombo.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		classCombo.setBounds(434, 132, 229, 30);
		panel.add(classCombo);
		getClassCombo();

		JLabel lbl1 = new JLabel("-");
		lbl1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl1.setBounds(203, 140, 13, 15);
		panel.add(lbl1);

		JLabel lbl2 = new JLabel("-");
		lbl2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl2.setBounds(280, 141, 13, 15);
		panel.add(lbl2);

		JLabel label_8 = new JLabel("@");
		label_8.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_8.setBounds(259, 197, 13, 15);
		panel.add(label_8);

		emailAddr = new JTextField();
		emailAddr.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		emailAddr.setColumns(10);
		emailAddr.setBounds(270, 190, 124, 30);
		panel.add(emailAddr);

		emailCombo = new JComboBox<>();
		emailCombo.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		emailCombo.setBounds(396, 190, 130, 30);
		emailCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (emailCombo.getSelectedIndex() == 0) {
					emailAddr.setText("");
				} else {
					emailAddr.setText((String) emailCombo.getSelectedItem());
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
		regdate.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		regdate.setBounds(694, 41, 57, 15);
		panel.add(regdate);

		regdatetf = new JTextField();
		regdatetf.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		regdatetf.setEnabled(false);
		regdatetf.setBounds(763, 34, 91, 30);
		panel.add(regdatetf);
		regdatetf.setColumns(10);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		regdatetf.setText(sdf.format(date));

		year.addItem("선택");
		for (int i = 2018; i > 1949; i--) {
			year.addItem(i + "");
		}
		month.addItem("선택");
		for (int i = 1; i < 13; i++) {
			if (i < 10) {
				month.addItem("0" + i + "");
			} else {
				month.addItem(i + "");
			}

		}
		day.addItem("선택");
		for (int i = 1; i < 32; i++) {
			if (i < 10) {
				day.addItem("0" + i + "");
			} else {
				day.addItem(i + "");
			}

		}

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		comboBox.setBounds(12, 10, 138, 35);
		add(comboBox);
		comboBox.addItem("전체보기");
		comboBox.addItem("이름");
		comboBox.addItem("수강반");

		searchField = new JTextField();
		searchField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		searchField.setBounds(149, 10, 630, 35);
		add(searchField);
		searchField.setColumns(10);
		searchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String word = searchField.getText();
					String type = comboBox.getSelectedItem().toString();
					Search(type, word);
				}
			}

		});

		JButton searchBtn = new JButton("검색");
		searchBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		searchBtn.setBounds(791, 10, 97, 35);
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String word = searchField.getText();
				
				String type = comboBox.getSelectedItem().toString();
				Search(type, word);
			}
		});
		add(searchBtn);

		addPopupMenu();

		addBtn = new JButton("신규등록");
		addBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(emptyCheck()>0&&selectDateCheck()>0&&classCheck()>0) {
					MemberService.getInstance().insertMember(sendMemberData("insert"));
					Sales sales = new Sales();
					sales.setPay(50000);
					SalesService.getInstance().insertSales(sales);
				}else if(emptyCheck()<0){
					JOptionPane.showMessageDialog(null, "공백이 존재합니다.");
					return;
				}else if(selectDateCheck()<0) {
					JOptionPane.showMessageDialog(null, "생년월일을 확인해주세요");
					return;
				}else if(classCheck()<0) {
					JOptionPane.showMessageDialog(null, "수강반을 선택해주세요");
					return;
				}
				
				
				
				loadData();
				mno.setEnabled(true);
				mno.requestFocus();
			}
		});
		addBtn.setBounds(723, 190, 97, 30);
		panel.add(addBtn);

		memupdate = new JButton("회원정보수정");
		memupdate.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		memupdate.setBounds(591, 190, 117, 30);
		panel.add(memupdate);

		classupdate = new JButton("수강반 수정");
		classupdate.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		classupdate.setBounds(710, 190, 110, 30);
		panel.add(classupdate);
		classupdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<String, Object> map = new HashMap<>();
				String newCno = classCombo.getSelectedItem().toString();
				if(newCno.equals("선택")) {
					JOptionPane.showMessageDialog(null, "수강반을 선택하세요");
					return;
				}
				
				if(newCno.substring(0,newCno.indexOf("/")-1).equals(oldCno+"")) {
					JOptionPane.showMessageDialog(null, "이미 수강중인 반입니다.");
					return;
				}
				
				map.put("newCno", newCno.substring(0,newCno.indexOf("/")-1));
				map.put("mno",mno.getText());
				map.put("oldCno",oldCno);
				RegisterService.getInstance().changeClass(map);
				
				clearText();
				getClassCombo();
				addBtn.setVisible(true);
				memupdate.setVisible(false);
				classupdate.setVisible(false);
				reenterBtn.setVisible(false);
			}
		});

		backBtn = new JButton("취소");
		backBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		backBtn.setBounds(822, 190, 66, 30);
		panel.add(backBtn);
		
		reenterBtn = new JButton("재등록");
		reenterBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		reenterBtn.setBounds(721, 190, 97, 30);
		panel.add(reenterBtn);
		
		classreupdateBtn = new JButton("재등록 수정");
		classreupdateBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		classreupdateBtn.setBounds(708, 190, 110, 30);
		panel.add(classreupdateBtn);
		classreupdateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				HashMap<String, Object> map = new HashMap<>();
				
				String newCno = classCombo.getSelectedItem().toString();
				
				if(newCno.equals("선택")) {
					JOptionPane.showMessageDialog(null, "재수강 반을 선택해주세요");
					return;
				}
				
				if(newCno.substring(0,newCno.indexOf("/")-1).equals(oldClass+"")) {
					JOptionPane.showMessageDialog(null, "이미 재수강 신청된 반입니다.");
					return;
				}
				
				
				map.put("newCno", newCno.substring(0,newCno.indexOf("/")-1));
				map.put("mno",mno.getText());
				map.put("oldCno",oldClass);
				RegisterService.getInstance().changeClass(map);
				
				clearText();
				getClassCombo();
				addBtn.setVisible(true);
				memupdate.setVisible(false);
				classupdate.setVisible(false);
				reenterBtn.setVisible(false);
				classreupdateBtn.setVisible(false);
				
			}
		});
		classreupdateBtn.setVisible(false);
		
		reenterBtn.setVisible(false);
		reenterBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mno.setEnabled(true);
				int no = Integer.parseInt(mno.getText());
				String comboVal = classCombo.getSelectedItem().toString();
				int cno = Integer.parseInt(comboVal.substring(0,comboVal.indexOf("/")-1));
				
				Register register1 = new Register();
				register1.setCno(cno);
				register1.setMno(no);
				register1.setReentrance(false);
				RegisterService.getInstance().insertRegister(register1);
				
				
				
				Class c = ClassService.getInstance().selectByNo(cno);
				
				HashMap<String,Object> map = new HashMap<>();
				map.put("mno", no);
				Date d = new Date();
				
				d.setDate(1);
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				map.put("s_day",sdf2.format(d));
				
				Class c2 = RegisterService.getInstance().selectByMno(map);
				
			
				if(c.getTno()==c2.getTno()) {
					Register register = new Register();
					register.setMno(no);
					register.setCno(c2.getCno());
					register.setReentrance(true);
					System.out.println(register);
					RegisterService.getInstance().updateReenter(register);
				}
				
				Sales sales = new Sales();
				sales.setPay(50000);
				SalesService.getInstance().insertSales(sales);
				
				clearText();
				getClassCombo();
				addBtn.setVisible(true);
				reenterBtn.setVisible(false);
			}
		});
		
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearText();
				addBtn.setVisible(true);
				memupdate.setVisible(false);
				classupdate.setVisible(false);
				reenterBtn.setVisible(false);
				classreupdateBtn.setVisible(false);
				mno.setEnabled(true);
				getClassCombo();
			}
		});

		memupdate.setVisible(false);
		memupdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				if(emptyCheck()>0&&selectDateCheck()>0&&classCheck()>0) {
					MemberService.getInstance().updateMember(sendMemberData("update"));
				}else if(emptyCheck()<0){
					JOptionPane.showMessageDialog(null, "공백이 존재합니다.");
					return;
				}else if(selectDateCheck()<0) {
					JOptionPane.showMessageDialog(null, "생년월일을 확인해주세요");
					return;
				}else if(classCheck()<0) {
					JOptionPane.showMessageDialog(null, "수강반을 선택해주세요");
					return;
				}
				
				
				memupdate.setVisible(false);
				classupdate.setVisible(false);
				addBtn.setVisible(true);
				reenterBtn.setVisible(false);
				loadData();
				mno.setEnabled(true);

			}
		});
		classupdate.setVisible(false);

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					getClassComboUpdate();
					getMemberData();
					addBtn.setVisible(false);
					memupdate.setVisible(true);
					classupdate.setVisible(true);
					backBtn.setVisible(true);
					reenterBtn.setVisible(false);
					
				}
			}

		});
	}

	private String[] getColumnNames() {

		return new String[] { "회원번호", "이름", "생년월일", "전화번호", "이메일", "성별", "등록날짜" };
	}

	private Object[][] getData() {
		List<Member> list = MemberService.getInstance().selectAll();

		Object[][] data = new Object[list.size()][];

		for (int i = 0; i < list.size(); i++) {

			data[i] = list.get(i).toArray();

		}
		return data;
	}

	private void Search(String type, String keyWord) {
		if (type.equals("전체보기") || keyWord.equals("")) {
			loadData();
		}
		if(type.equals("회원번호")|| type.equals("이름")||type.equals("수강반")) {
			loadSearchData(type,keyWord);
		}
	}

	private Object[][] getSearchData(String type, String keyword) {
		List<Member> list = null;

		if (type.equals("이름")) {
			list = MemberService.getInstance().selectSearchName("%" + keyword + "%");
		}
		if(type.equals("수강반")) {
			list = MemberService.getInstance().selectSearchCno(Integer.parseInt(keyword));
		}
	
		Object[][] data = new Object[list.size()][];

		for (int i = 0; i < list.size(); i++) {

			data[i] = list.get(i).toArray();

		}
		return data;
	}


	private void loadSearchData(String type, String keyword) {

		DefaultTableModel model = new DefaultTableModel(getSearchData(type, keyword), getColumnNames()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		table.setModel(model);
		setAlignWidth();
	}

	private void addPopupMenu() {
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("수정");
		JMenuItem menuItem2 = new JMenuItem("삭제");
		JMenuItem menuItem3 = new JMenuItem("재등록");
		JMenuItem menuItem4 = new JMenuItem("수강 취소");
		
		popupMenu.add(menuItem);
		popupMenu.add(menuItem2);
		popupMenu.add(menuItem3);
		popupMenu.add(menuItem4);
		
		table.setComponentPopupMenu(popupMenu);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getClassComboUpdate();
				getMemberData();
				addBtn.setVisible(false);
				memupdate.setVisible(true);
				classupdate.setVisible(true);
				backBtn.setVisible(true);
				reenterBtn.setVisible(false);
				mno.setEnabled(false);
			}
		});
		menuItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "회원을 삭제하시겠습니까?", "회원정보 삭제",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				if (reply == JOptionPane.YES_OPTION) {
					int row = table.getSelectedRow();
					if(row<0) {
						return;
					}
					int mno = (int) table.getValueAt(row, 0);
					MemberService.getInstance().deleteMember(mno);
					loadData();
				}
			}
		});

		menuItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getMemberData();
				reenterBtn.setVisible(true);
				addBtn.setVisible(false);
				memupdate.setVisible(false);
				classupdate.setVisible(false);
				backBtn.setVisible(true);
				mno.setEnabled(false);
				List<Class> list = ClassService.getInstance().selectByNextMonth("");
				
				
				
				classCombo.removeAllItems();
				classCombo.addItem("선택");
				
				for (int i = 0; i < list.size(); i++) {
					classCombo.addItem(list.get(i).getCno() + " / "+list.get(i).getTime()+" / "+list.get(i).getLevel());
				}
				
				int row = table.getSelectedRow();
				if(row<0) {
					return;
				}
				int no = (int) table.getValueAt(row, 0);
				
				for(int i=0;i<list.size();i++) {
					
					int reCno  = list.get(i).getCno();
					Register r1 = new Register();
					r1.setCno(reCno);
					r1.setMno(no);
					
					Register r = RegisterService.getInstance().checkReent(r1);
					if(r!=null) {
						JOptionPane.showMessageDialog(null, "이미 재등록된 회원입니다.");
						classCombo.setSelectedItem(list.get(i).getCno() + " / "+list.get(i).getTime()+" / "+list.get(i).getLevel());
						oldClass = list.get(i).getCno();
						classreupdateBtn.setVisible(true);
						reenterBtn.setVisible(false);
						break;
					}
					
				}
				
				
				
			}
		});
		
		menuItem4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row<0) {
					return;
				}
				int mno = (int)table.getValueAt(row, 0);
				
				List<Register> list = RegisterService.getInstance().findClass(mno);
				
			
				if(list.size()==0) {
					JOptionPane.showMessageDialog(null, "수강 신청한 반이 없습니다");
					return;
				}
				
				String[] classNo = new String[list.size()];
				for(int i=0;i<list.size();i++) {
					int cno = list.get(i).getCno();
					Class c = ClassService.getInstance().selectByNo(cno);
					classNo[i] = c.getCno()+" / "+c.getTime()+" / "+c.getLevel();
				}
				
				Object selected = JOptionPane.showInputDialog(null, "수강 취소 할 반을 선택하세요", "수강취소", JOptionPane.QUESTION_MESSAGE, null, classNo, classNo[0]);
				
				if(selected !=null) {
					String cancel = selected.toString();
					RegisterService.getInstance().cancelClass(new Register(mno, Integer.parseInt(cancel.substring(0,cancel.indexOf("/")-1))));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					Date day = new Date();
					Sales s = SalesService.getInstance().lastSales(sdf.format(day));
					SalesService.getInstance().deleteSales(s.getSno());
				}
			}
		});
	}

	private void getClassCombo() {

		List<Class> list = new ArrayList<>();

		Calendar c = Calendar.getInstance();
		if (c.get(Calendar.DATE) <= 10) {
			list = ClassService.getInstance().selectBytoMonth("");
		} else if (c.get(Calendar.DATE) >= 20) {
			list = ClassService.getInstance().selectByNextMonth("");
		}
		classCombo.removeAllItems();
		classCombo.addItem("선택");
		for (int i = 0; i < list.size(); i++) {
			classCombo.addItem(list.get(i).getCno() + " / "+list.get(i).getTime()+" / "+list.get(i).getLevel());
		}

	}
	
	private void loadData() {
		DefaultTableModel model = new DefaultTableModel(getData(), getColumnNames()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		table.setModel(model);
		setAlignWidth();
	}

	private Member sendMemberData(String type) {

		String y = year.getSelectedItem().toString();
		String m = month.getSelectedItem().toString();
		String d = day.getSelectedItem().toString();
		String a = y + m + d;

		int age = Integer.parseInt(a);

		String tell = tell1.getText() + "-" + tell2.getText() + "-" + tell3.getText();
		String gender = "";

		if (menRadio.isSelected()) {
			gender = "남";
		} else {
			gender = "여";
		}

		String email = email1.getText() + "@" + emailAddr.getText();
		Member mem = null;

		if (type.equals("insert")) {
			mem = new Member(Integer.parseInt(mno.getText()),name.getText(), age, tell, email, gender, a);
			Register register = new Register();
			
			String classNo = classCombo.getSelectedItem().toString();
			
			int cno = Integer.parseInt(classNo.substring(0,classNo.indexOf("/")-1));
			
			register.setCno(cno);
			register.setMno(Integer.parseInt(mno.getText()));
			register.setReentrance(false);
			RegisterService.getInstance().insertRegister(register);

		} else if (type.equals("update")) {
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
		if(row<0) {
			return;
		}
		int no = (int) table.getValueAt(row, 0);

		Member m = MemberService.getInstance().selectMno(no);

		mno.setText(m.getMno() + "");
		name.setText(m.getName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		regdatetf.setText(sdf.format(m.getDate()));
		
		if (m.getGender().equals("남")) {
			womenRadio.setSelected(false);
			menRadio.setSelected(true);
		} else if (m.getGender().equals("여")) {
			menRadio.setSelected(false);
			womenRadio.setSelected(true);
		}
		HashMap<String,Object> map = new HashMap<>();
		map.put("mno", no);
		Date d = new Date();
		
		d.setDate(1);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		map.put("s_day",sdf2.format(d));
		
		Class c = RegisterService.getInstance().selectByMno(map);
		
		if(c!=null) {
			classCombo.setSelectedItem(c.getCno()+" / "+c.getTime()+" / "+c.getLevel());
			oldCno = c.getCno();
		}else {
			JOptionPane.showMessageDialog(null, "수강신청 기록이 없습니다.");
			classCombo.setSelectedIndex(0);
			oldCno=-1;
		}
		
		
		String total = String.valueOf(m.getAge());
		String y = total.substring(0, 4);
		year.setSelectedItem(y);

		String m2 = total.substring(4, 6);
		month.setSelectedItem(m2);

		String d2 = total.substring(6, total.length());
		day.setSelectedItem(d2);

		String totale = m.getEmail();
		String em = totale.substring(0, totale.indexOf("@"));
		email1.setText(em);

		String emaddr = totale.substring(totale.indexOf("@") + 1);
		emailAddr.setText(emaddr);

		String phone = m.getTell();
		String t1 = phone.substring(0, phone.indexOf("-"));
		String t2 = phone.substring(phone.indexOf("-") + 1, phone.lastIndexOf("-"));
		String t3 = phone.substring(phone.lastIndexOf("-") + 1);
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

	public JTextField getMno() {
		return mno;
	}
	
	public void barCodeData(Member m,int no) {
		
		mno.setText(m.getMno() + "");
		name.setText(m.getName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		regdatetf.setText(sdf.format(m.getDate()));

		if (m.getGender().equals("남")) {
			womenRadio.setSelected(false);
			menRadio.setSelected(true);
		} else if (m.getGender().equals("여")) {
			menRadio.setSelected(false);
			womenRadio.setSelected(true);
		}
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("mno", no);
		Date d = new Date();
		
		d.setDate(1);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		map.put("s_day",sdf2.format(d));
		
		Class c = RegisterService.getInstance().selectByMno(map);
		
		classCombo.setSelectedItem(c.getCno()+"");
		String total = String.valueOf(m.getAge());
		String y = total.substring(0, 4);
		year.setSelectedItem(y);

		String m2 = total.substring(4, 6);
		month.setSelectedItem(m2);

		String d2 = total.substring(6, total.length());
		day.setSelectedItem(d2);

		String totale = m.getEmail();
		String em = totale.substring(0, totale.indexOf("@"));
		email1.setText(em);

		String emaddr = totale.substring(totale.indexOf("@") + 1);
		emailAddr.setText(emaddr);

		String phone = m.getTell();
		String t1 = phone.substring(0, phone.indexOf("-"));
		String t2 = phone.substring(phone.indexOf("-") + 1, phone.lastIndexOf("-"));
		String t3 = phone.substring(phone.lastIndexOf("-") + 1);
		tell1.setText(t1);
		tell2.setText(t2);
		tell3.setText(t3);
		
		reenterBtn.setVisible(true);
		addBtn.setVisible(false);
		memupdate.setVisible(false);
		classupdate.setVisible(false);
		backBtn.setVisible(true);
		mno.setEnabled(false);
		
		List<Class> list =ClassService.getInstance().selectByNextMonth("");
		classCombo.removeAllItems();
		classCombo.addItem("선택");
		for (int i = 0; i < list.size(); i++) {
			classCombo.addItem(list.get(i).getCno() + "");
		}
		
	}
	
	private int emptyCheck() {
		int empty=-1;
		
		if(!mno.getText().equals("")&&!name.getText().equals("")
				&&!tell1.getText().equals("")&&!tell2.getText().equals("")
				&&!tell3.getText().equals("")&& !email1.getText().equals("")&&!emailAddr.getText().equals("")) {
			
			empty=1;
			
		}
		return empty;
		
	}
	
	private int selectDateCheck() {
		int select=-1;
		
		if(year.getSelectedIndex()>0&&month.getSelectedIndex()>0&&day.getSelectedIndex()>0) {
			select=1;
		}
		
		return select;
	}
	
	private int classCheck() {
		int select=-1;
		if(classCombo.getSelectedIndex()>0) {
			select=1;
		}
		return select;
	}
	
	
	public void setAlignWidth() {
		setAlign(SwingConstants.CENTER,0,1,2,3,4,5,6);
		setCellWidth(30, 70, 80, 100,200,30,50);
	}
	
	public void setCellWidth(int...width) {
		TableColumnModel cModel = table.getColumnModel();
		for(int i=0; i<width.length; i++){
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}
	}
	
	
	public void setAlign(int align, int...idx) {
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		
		TableColumnModel cModel = table.getColumnModel();
	
		for(int i=0; i<idx.length;i++){
			cModel.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}
	
	private void getClassComboUpdate() {

		List<Class> list = ClassService.getInstance().selectBytoMonth("");
		for(Class c : list) {
			System.out.println("class : " + c);
		}
		classCombo.removeAllItems();
		classCombo.addItem("선택");
		for (int i = 0; i < list.size(); i++) {
			classCombo.addItem(list.get(i).getCno() + " / "+list.get(i).getTime()+" / "+list.get(i).getLevel());
		}

	}
	
}
