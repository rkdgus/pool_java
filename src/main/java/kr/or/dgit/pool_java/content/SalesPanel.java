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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

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
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;

public class SalesPanel extends JPanel {
	private JTable table;
	private JTable table_1;
	private SalesDao sDao;
	
	

	/**
	 * Create the panel.
	 */
	public SalesPanel() {
		this.sDao = SalesService.getInstance();
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 85, 300, 406);
		add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 905, 75);
		add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		
		
		
		comboBox.setToolTipText("");
		comboBox.setBounds(12, 0, 81, 31);
		panel.add(comboBox);

		comboBox.addItem("하루매출");
		comboBox.addItem("월매출");
		comboBox.addItem("연매출");
		
		JComboBox yearBox = new JComboBox();
		yearBox.setBounds(12, 41, 81, 24);
		panel.add(yearBox);
		Date d = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		int year=Integer.parseInt(sf.format(d));
		for(int i = 2017;i<=year;i++) {
			yearBox.addItem(i);
		}
		
		JComboBox monthBox = new JComboBox();
		monthBox.setBounds(105, 43, 54, 21);
		panel.add(monthBox);
		for(int i = 1;i<=12;i++) {
			monthBox.addItem(i);
		}
		
		JComboBox dayBox = new JComboBox();
		dayBox.setBounds(171, 43, 54, 21);
		panel.add(dayBox);
		for(int i = 1;i<=31;i++) {
			dayBox.addItem(i);
		}
		
		JButton button = new JButton("검색");
		button.setBounds(237, 24, 72, 41);
		panel.add(button);
		
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 501, 300, 45);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("합계");
		lblNewLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 81, 45);
		panel_1.add(lblNewLabel);
		
		JLabel sum = new JLabel("");
		sum.setHorizontalAlignment(SwingConstants.TRAILING);
		sum.setFont(new Font("굴림", Font.PLAIN, 18));
		sum.setOpaque(true);
		sum.setBackground(Color.WHITE);
		sum.setBounds(81, 0, 219, 45);
		panel_1.add(sum);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(353, 85, 540, 461);
		add(panel_2);
		
		
		
		List<Sales> lists = null;
		Date day=new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		lists = sDao.selectDate(Integer.parseInt(sd.format(day))); 
		loadDataPrice(lists);
		sum.setText(String.valueOf(sDao.selectSum(Integer.parseInt(sd.format(day)))));
		
		
	
		
	}

	
	public void loadDataPrice(List<Sales> lists) {
		DefaultTableModel model = new DefaultTableModel(getDataPrice(lists), getColumnNames());
		table.setModel(model);
		
	}
	
	protected Object[] getColumnNames() {
		return new String[] { "날짜", "금액"};
	}
	
	
	
	protected Object[][] getDataPrice(List<Sales> lists) {
		Object[][] data = new Object[lists.size()][];
		for (int i = 0; i < lists.size(); i++) {
			data[i] = lists.get(i).toArray();
		}
		return data;
	}
}
