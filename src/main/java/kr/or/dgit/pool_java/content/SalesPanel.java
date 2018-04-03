package kr.or.dgit.pool_java.content;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.pool_java.dao.SalesDao;
import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.dto.Sales;
import kr.or.dgit.pool_java.dto.Teacher;
import kr.or.dgit.pool_java.frame.ClassScheduleUpdate;
import kr.or.dgit.pool_java.service.ClassService;
import kr.or.dgit.pool_java.service.SalesService;
import kr.or.dgit.pool_java.service.TeacherService;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class SalesPanel extends JPanel {
	private JTable table;
	private JTable table_1;
	private SalesDao sDao;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	

	/**
	 * Create the panel.
	 */
	public SalesPanel() {
		this.sDao = SalesService.getInstance();
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 85, 286, 406);
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
		SimpleDateFormat sf1 = new SimpleDateFormat("MM");
		SimpleDateFormat sf2 = new SimpleDateFormat("dd");
		int year=Integer.parseInt(sf.format(d));
		int month=Integer.parseInt(sf1.format(d));
		int day=Integer.parseInt(sf2.format(d));
		for(int i = 2017;i<=year;i++) {
			yearBox.addItem(i);
		}
		yearBox.setSelectedItem(year);
		JComboBox monthBox = new JComboBox();
		monthBox.setBounds(105, 43, 54, 21);
		panel.add(monthBox);
		for(int i = 1;i<=12;i++) {
			monthBox.addItem(i);
		}
		monthBox.setSelectedItem(month);
		JComboBox dayBox = new JComboBox();
		dayBox.setBounds(171, 43, 54, 21);
		panel.add(dayBox);
		for(int i = 1;i<=31;i++) {
			dayBox.addItem(i);
		}
		
		dayBox.setSelectedItem(day);
		
		
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex()==0) {
					monthBox.setEnabled(true);
					dayBox.setEnabled(true);
				}else if(comboBox.getSelectedIndex()==1) {
					monthBox.setEnabled(true);
					dayBox.setEnabled(false);
				}else {
					monthBox.setEnabled(false);
					dayBox.setEnabled(false);
				}
			}
		});
		
		
		
		JButton button = new JButton("검색");
		button.setBounds(237, 24, 72, 41);
		panel.add(button);
		
		JLabel lblNewLabel_2 = new JLabel("월 매출 그래프");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(351, 24, 494, 41);
		panel.add(lblNewLabel_2);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 501, 286, 45);
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
		sum.setBounds(81, 0, 204, 45);
		panel_1.add(sum);
		
		JPanel panel_3 = new Chart();
		panel_3.setBounds(296, 76, 609, 424);
		add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("단위-만원");
		lblNewLabel_1.setBounds(836, 501, 57, 15);
		add(lblNewLabel_1);
	
		
		
		
		List<Sales> lists = null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		lists = sDao.selectDate(sd.format(d)); 
		loadDataPrice(lists);
		if(lists.size()==0) {
			sum.setText("0원");
		}else {
			sum.setText(String.valueOf(sDao.selectSum(sd.format(d)))+"원");
		}
		
		
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Sales> lists = null;
				String date ="";
				String month =String.valueOf(monthBox.getSelectedItem());
				String day =String.valueOf(dayBox.getSelectedItem());
				if(Integer.parseInt(month)<10) {
					month = "0"+month;
				}
				if(Integer.parseInt(day)<10) {
					day="0"+day;
				}
				if(comboBox.getSelectedIndex()==0) {
					date =yearBox.getSelectedItem()+"-"+month+"-"+day;
					lists = sDao.selectDate(date);
					
				}else if(comboBox.getSelectedIndex()==1) {
					date =yearBox.getSelectedItem()+"-"+month+"-";
					lists = sDao.selectDate(date);
				}else {
					date =yearBox.getSelectedItem()+"-";
					lists = sDao.selectDate(date);
				}
				if(lists.size()==0) {
					sum.setText("0원");
					
				}else {
					sum.setText(String.valueOf(sDao.selectSum(date))+"원");
					
				}
				
				loadDataPrice(lists);
				
				
			}
		});
		
        
		setTable();
        
        JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("삭제");
		popupMenu.add(menuItem);
		table.setComponentPopupMenu(popupMenu);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1) {
					return;
				}
				
				int no = (int)table.getValueAt(table.getSelectedRow(), 0);
				sDao.deleteSales(no);
				
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				List<Sales> lists = sDao.selectDate(sd.format(d));
				loadDataPrice(lists);
				if(lists.size()==0) {
					sum.setText("0원");
					
				}else {
					sum.setText(String.valueOf(sDao.selectSum(sd.format(d)))+"원");
					
				}
				setTable();
			}
		});
			
      
	}
	
	public void setTable() {
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();


		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		 
		table.getColumnModel().getColumn(0).setMaxWidth(50); 
        table.getColumnModel().getColumn(0).setMinWidth(50); 
        table.getColumnModel().getColumn(0).setWidth(50); 
        table.getColumnModel().getColumn(1).setMaxWidth(150); 
        table.getColumnModel().getColumn(1).setMinWidth(150); 
        table.getColumnModel().getColumn(1).setWidth(150);
        for(int i=0;i<3;i++) {
        	table.getColumnModel().getColumn(i).setCellRenderer(tScheduleCellRenderer);
        }
	}

	
	public void loadDataPrice(List<Sales> lists) {
		DefaultTableModel model = new DefaultTableModel(getDataPrice(lists), getColumnNames());
		table.setModel(model);
		
	}
	
	protected Object[] getColumnNames() {
		return new String[] { "번호","날짜", "금액"};
	}
	
	
	
	protected Object[][] getDataPrice(List<Sales> lists) {
		Object[][] data = new Object[lists.size()][];
		for (int i = 0; i < lists.size(); i++) {
			
			data[i] = lists.get(i).toArray();
		}
		return data;
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
