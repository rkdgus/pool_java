package kr.or.dgit.pool_java.content;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.pool_java.dao.AttendanceDao;
import kr.or.dgit.pool_java.dao.ClassDao;
import kr.or.dgit.pool_java.dao.RegisterDao;
import kr.or.dgit.pool_java.dto.Attendance;
import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.dto.Register;
import kr.or.dgit.pool_java.service.AttendanceService;
import kr.or.dgit.pool_java.service.ClassService;
import kr.or.dgit.pool_java.service.MemberService;
import kr.or.dgit.pool_java.service.RegisterService;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AttendPanel extends JPanel {
	private JTable table;
	private AttendanceDao aDao;
	private RegisterDao rDao;
	private ClassDao cDao;
	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public AttendPanel() throws ParseException {
		setLayout(null);
		this.rDao = RegisterService.getInstance();
		this.aDao = AttendanceService.getInstance();
		this.cDao = ClassService.getInstance();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 99, 878, 435);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("프린트");
		btnNewButton.setBounds(774, 10, 97, 30);
		add(btnNewButton);
		
		
		JComboBox yearBox = new JComboBox();
		
		yearBox.setBounds(54, 11, 81, 30);
		add(yearBox);
		Date d = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		SimpleDateFormat sf1 = new SimpleDateFormat("MM");
		int year=Integer.parseInt(sf.format(d));
		int month=Integer.parseInt(sf1.format(d));
		
		for(int i = 2017;i<=year;i++) {
			yearBox.addItem(i);
		}
		yearBox.setSelectedItem(year);
		JComboBox monthBox = new JComboBox();
		
		monthBox.setBounds(173, 11, 54, 30);
		add(monthBox);
		String mm="";
		for(int i = 1;i<=12;i++) {
			if(i<10) {
				mm="0"+i;
			}else {
				mm=i+"";
			}
			monthBox.addItem(mm);
		}
		monthBox.setSelectedItem(sf1.format(d));
		
	
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(274, 10, 160, 30);
		add(comboBox);
		
		JLabel lblNewLabel = new JLabel("년");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(135, 10, 39, 38);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("월");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(224, 10, 39, 38);
		add(lblNewLabel_1);
		
		JLabel title = new JLabel("");
		title.setFont(new Font("굴림", Font.BOLD, 17));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(303, 50, 259, 39);
		add(title);
		title.setText("출석부");
		
		List<Class> items = cDao.selectByTime(yearBox.getSelectedItem()+"-"+monthBox.getSelectedItem()+"-01");
		
		
		for(Class c:items) {
			comboBox.addItem(c.toString());
		}
		
		
		yearBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				List<Class> items = cDao.selectByTime(yearBox.getSelectedItem()+"-"+monthBox.getSelectedItem()+"-01");
				if(items.size()==0) {
					return;
				}
				for(Class c:items) {
					comboBox.addItem(c.toString());
				}
			}
		});
		monthBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				List<Class> items = cDao.selectByTime(yearBox.getSelectedItem()+"-"+monthBox.getSelectedItem()+"-01");
				if(items.size()==0) {
					return;
				}
				for(Class c:items) {
					comboBox.addItem(c.toString());
				}
			}
		});
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem() == null){
					return;
				}
				String str = comboBox.getSelectedItem()+"";
				
				String[] arr =str.split(".");
				str = arr[0];
				String str1 =arr[1];
				
				List<Register> lists = rDao.selectByCno(Integer.parseInt(str));


				try {
					loadDataPrice(lists, Integer.parseInt(monthBox.getSelectedItem()+""), (int)yearBox.getSelectedItem());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				setTable();
				title.setText("["+str1+"] 출석부");
			}
		});

	
	}
	
	public void loadDataPrice(List<Register> lists,int month,int year) throws ParseException {
		DefaultTableModel model = new DefaultTableModel(getDataPrice(lists, month, year), getColumnNames(month));
		table.setModel(model);
		
	}
	
	protected Object[] getColumnNames(int month) {
		int[] day= {32,29,32,31,32,31,32,32,31,32,31,32};
		String[] colum =new String[day[month]];
		for(int i=0;i<colum.length;i++) {
			if(i==0) {
				colum[i] = "이름";
			}else {
				colum[i] = i+"";
			}
			
		}
		
		return colum;
	}
	public void setTable() {
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();


		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		 
		table.getColumnModel().getColumn(0).setMaxWidth(50); 
        table.getColumnModel().getColumn(0).setMinWidth(50); 
        table.getColumnModel().getColumn(0).setWidth(50); 
        for(int i=0;i<table.getColumnCount();i++) {
        	table.getColumnModel().getColumn(i).setCellRenderer(tScheduleCellRenderer);
        }
	}
	
	
	protected Object[][] getDataPrice(List<Register> lists,int month,int year) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		int[] day= {32,29,32,31,32,31,32,32,31,32,31,32};
		String[] colum =new String[day[month]];
		Object[][] data = new Object[lists.size()][];
		Attendance attendance =null;
		List<Attendance> list =null ;
		String m = "";
		String s = "";
		HashMap<String, Object> map= new HashMap<>();
		
		if(month<10) {
			m = "0"+month;
		}else {
			m=month+"";
		}
		for (int i = 0; i < lists.size(); i++) {
			Object[] d = new Object[day[month]];
			for(int j=0;j<colum.length;j++) {
				if(j==0) {
					d[j] = MemberService.getInstance().selectMno(lists.get(i).getMno()).getName();
				}else {
					if(j<10) {
						s = "0"+j;
					}else {
						s=j+"";
					}
				
					map.put("date", year+"-"+m+"-"+s);
					map.put("mno",lists.get(i).getMno());
					list = aDao.selectDate(map);
					if(list.size()!=0) {
						d[j]="O";
					}
				}
				
			}
			
			data[i] = d;

		}
		return data;
	}
	
	
	
	
}
