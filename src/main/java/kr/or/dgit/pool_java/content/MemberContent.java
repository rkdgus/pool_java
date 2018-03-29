package kr.or.dgit.pool_java.content;

import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.pool_java.dto.Member;
import kr.or.dgit.pool_java.service.MemberService;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
public class MemberContent extends JPanel {
	private JTable table;
	private JTextField searchField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
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
		
		JLabel label_1 = new JLabel("나이");
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
		label_5.setBounds(610, 33, 57, 15);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("재등록 여부");
		label_6.setBounds(610, 87, 97, 15);
		panel.add(label_6);
		
		JButton addBtn = new JButton("추가");
		addBtn.setBounds(791, 189, 97, 31);
		panel.add(addBtn);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("남자");
		rdbtnNewRadioButton.setBounds(431, 33, 66, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton radioButton = new JRadioButton("여자");
		radioButton.setBounds(499, 33, 66, 23);
		panel.add(radioButton);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(435, 80, 91, 30);
		panel.add(textField);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(689, 25, 81, 30);
		panel.add(comboBox_1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(703, 79, 41, 23);
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
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(396, 189, 130, 30);
		panel.add(comboBox_2);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(270, 190, 124, 30);
		panel.add(textField_7);
		
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
}
