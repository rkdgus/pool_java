package kr.or.dgit.pool_java.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.dto.Member;
import kr.or.dgit.pool_java.service.ClassService;
import kr.or.dgit.pool_java.service.MemberService;


public class MemberContent extends JPanel {
	private JTable table;
	private JTextField searchField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField emailAddr;
	private JComboBox<String> emailCombo;
	private JComboBox<String> classCombo;
	private JRadioButton menRadio;
	private JRadioButton womenRadio;
	
	/**
	 * Create the panel.
	 */
	public MemberContent() {
		setBounds(0, 0, 900, 570);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
	
		
		scrollPane.setBounds(0, 55, 900, 248);
		add(scrollPane);
		DefaultTableModel model = new DefaultTableModel(getData(),getColumnNames());
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int mno =(int)table.getValueAt(row, 0);
				
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 310, 900, 255);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("회원번호");
		lblNewLabel.setBounds(41, 26, 57, 15);
		panel.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(134, 26, 183, 30);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("이름");
		label.setBounds(41, 87, 57, 15);
		panel.add(label);
		
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(134, 80, 183, 30);
		panel.add(textField_2);
		
		JLabel label_1 = new JLabel("생년월일");
		label_1.setBounds(375, 87, 57, 15);
		panel.add(label_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(134, 134, 66, 30);
		panel.add(textField_3);
		
		JLabel label_2 = new JLabel("전화번호");
		label_2.setBounds(41, 157, 57, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("이메일");
		label_3.setBounds(41, 205, 57, 15);
		panel.add(label_3);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(134, 190, 124, 30);
		panel.add(textField_5);
		
		JLabel label_4 = new JLabel("성별");
		label_4.setBounds(375, 33, 57, 15);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("반 번호");
		label_5.setBounds(719, 37, 57, 15);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("재등록 여부");
		label_6.setBounds(719, 91, 97, 15);
		panel.add(label_6);
		
		JButton addBtn = new JButton("추가");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		addBtn.setBounds(791, 189, 97, 31);
		panel.add(addBtn);
		
		
		
		menRadio = new JRadioButton("남자");
		menRadio.setBounds(442, 34, 66, 23);
		panel.add(menRadio);
		
		womenRadio = new JRadioButton("여자");
		womenRadio.setBounds(510, 34, 66, 23);
		panel.add(womenRadio);
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(menRadio);
		group.add(womenRadio);
		
		classCombo = new JComboBox<>();
		classCombo.setBounds(798, 29, 81, 30);
		panel.add(classCombo);
		getClassCombo();
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(812, 83, 41, 23);
		panel.add(chckbxNewCheckBox);
		
		JLabel lblNewLabel_1 = new JLabel("-");
		lblNewLabel_1.setBounds(203, 140, 13, 15);
		panel.add(lblNewLabel_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(211, 134, 66, 30);
		panel.add(textField_4);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(289, 134, 66, 30);
		panel.add(textField_6);
		
		JLabel label_7 = new JLabel("-");
		label_7.setBounds(280, 141, 13, 15);
		panel.add(label_7);
		
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
		
		JComboBox<String> year = new JComboBox<String>();
		year.setBounds(438, 82, 81, 30);
		panel.add(year);
		
		JComboBox<String> month = new JComboBox<String>();
		month.setBounds(520, 82, 70, 30);
		panel.add(month);
		
		JComboBox<String> day = new JComboBox<String>();
		day.setBounds(591, 82, 70, 30);
		panel.add(day);
		
		year.addItem("선택");
		for(int i=2018;i>1949;i--) {
			year.addItem(i+"");
		}
		month.addItem("선택");
		for(int i=1;i<13;i++) {
			month.addItem(i+"");
		}
		day.addItem("선택");
		for(int i=1;i<32;i++) {
			day.addItem(i+"");
		}
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 10, 138, 35);
		add(comboBox);
		
		searchField = new JTextField();
		searchField.setBounds(149, 10, 630, 35);
		add(searchField);
		searchField.setColumns(10);
		
		JButton searchBtn = new JButton("검색");
		searchBtn.setBounds(791, 10, 97, 35);
		add(searchBtn);
		
		addPopupMenu();
	}
	

	private String[] getColumnNames() {

		return new String[] { "회원번호", "이름", "나이","전화번호","이메일","성별","등록날짜"};
	}
	private Object[][] getData() {
		List<Member> list = MemberService.getInstance().selectAll();
		
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
	      popupMenu.add(menuItem);
	      popupMenu.add(menuItem2);
	      table.setComponentPopupMenu(popupMenu);
	      menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	      
	      menuItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	   
	}
	
	private void getClassCombo() {
		List<Class> list = ClassService.getInstance().selectByAll();
		classCombo.addItem("선택");
		for(int i=0;i<list.size();i++) {
			classCombo.addItem(list.get(i).getCno()+"");
		}
	
	}
}
