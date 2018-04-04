package kr.or.dgit.pool_java.content;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
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

public class AttendPanel extends JPanel {
	private JTable table;
	private AttendanceDao aDao;
	private RegisterDao rDao;

	String FilePath2 = "D:/excelData";
	String FilePath = "";
	String SheetName = "test";

	private ClassDao cDao;
	private JComboBox yearBox;
	private JComboBox monthBox;
	private JComboBox comboBox;


	/**
	 * Create the panel.
	 * 
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
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excelFile();
			}
		});
		btnNewButton.setBounds(774, 10, 97, 30);
		add(btnNewButton);

		List<Register> lists = RegisterService.getInstance().selectByCno(6);
		loadDataPrice(lists, 4, 2018);


		 yearBox = new JComboBox();
		yearBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		yearBox.setBounds(54, 11, 81, 30);
		add(yearBox);
		Date d = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		SimpleDateFormat sf1 = new SimpleDateFormat("MM");
		int year = Integer.parseInt(sf.format(d));
		int month = Integer.parseInt(sf1.format(d));

		for (int i = 2017; i <= year; i++) {
			yearBox.addItem(i);
		}
		yearBox.setSelectedItem(year);

		monthBox = new JComboBox();
		monthBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		monthBox.setBounds(173, 11, 54, 30);
		add(monthBox);
		String mm = "";
		for (int i = 1; i <= 12; i++) {
			if (i < 10) {
				mm = "0" + i;
			} else {
				mm = i + "";
			}
			monthBox.addItem(mm);
		}
		monthBox.setSelectedItem(sf1.format(d));


		comboBox = new JComboBox();
		comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

	
		comboBox.setBounds(274, 10, 160, 30);
		add(comboBox);

		JLabel lblNewLabel = new JLabel("년");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(135, 10, 39, 38);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("월");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(224, 10, 39, 38);
		add(lblNewLabel_1);

		JLabel title = new JLabel("");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(303, 50, 259, 39);
		add(title);
		title.setText("출석부");

		List<Class> items = cDao.selectByTime(yearBox.getSelectedItem() + "-" + monthBox.getSelectedItem() + "-01");

		for (Class c : items) {
			comboBox.addItem(c.toString());
		}

		yearBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				List<Class> items = cDao
						.selectByTime(yearBox.getSelectedItem() + "-" + monthBox.getSelectedItem() + "-01");
				if (items.size() == 0) {
					return;
				}
				for (Class c : items) {
					comboBox.addItem(c.toString());
				}
			}
		});
		monthBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				List<Class> items = cDao
						.selectByTime(yearBox.getSelectedItem() + "-" + monthBox.getSelectedItem() + "-01");
				if (items.size() == 0) {
					return;
				}
				for (Class c : items) {
					comboBox.addItem(c.toString());
				}
			}
		});

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() == null) {
					return;
				}

				String str = comboBox.getSelectedItem()+"";
				
				String[] arr =str.split("/");


				str = arr[0];
				String str1 = arr[1];

				List<Register> lists = rDao.selectByCno(Integer.parseInt(str));
				try {
					loadDataPrice(lists, Integer.parseInt(monthBox.getSelectedItem() + ""),
							(int) yearBox.getSelectedItem());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				setTable();
				title.setText("[" +monthBox.getSelectedItem()+"월 "+ str1 + "] 출석부");
			}
		});

	}

	public void loadDataPrice(List<Register> lists, int month, int year) throws ParseException {
		DefaultTableModel model = new DefaultTableModel(getDataPrice(lists, month, year), getColumnNames(month));
		table.setModel(model);

	}

	protected Object[] getColumnNames(int month) {
		List<Integer> lists = monthOfLasyDay(month);
		
		String[] colum = new String[lists.size()+1];
		for (int i = 0; i < colum.length; i++) {
			if (i == 0) {
				colum[i] = "이름";
			} else {
				colum[i] = lists.get(i-1) + "";
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
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
	}

	protected Object[][] getDataPrice(List<Register> lists, int month, int year) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		List<Integer> intlists = monthOfLasyDay(month);
		
		String[] colum = new String[intlists.size()+1];
		Object[][] data = new Object[lists.size()][];
		Attendance attendance = null;
		List<Attendance> list = null;
		String m = "";
		String s = "";
		HashMap<String, Object> map = new HashMap<>();

		if (month < 10) {
			m = "0" + month;
		} else {
			m = month + "";
		}
		for (int i = 0; i < lists.size(); i++) {
			Object[] d = new Object[intlists.size()+1];
			for (int j = 0; j < colum.length; j++) {
				if (j == 0) {
					d[j] = MemberService.getInstance().selectMno(lists.get(i).getMno()).getName();
				} else {
					if (intlists.get(j-1) < 10) {
						s = "0" + intlists.get(j-1);
					} else {
						s = intlists.get(j-1) + "";
					}
					map.put("date", year + "-" + m + "-" + s);
					map.put("mno", lists.get(i).getMno());
					list = aDao.selectDate(map); //출석 체크 
					if (list.size() != 0) {
						d[j] = "O";
					}

				}
			}

			data[i] = d;

		}
		return data;
	}

	private void excelFile() {
		String[] getColumn = new String[table.getColumnCount()];
		for (int i = 0; i < table.getColumnCount(); i++) {
			getColumn[i] = table.getColumnName(i);
		}

		String[][] getData = new String[table.getRowCount()][table.getColumnCount()];

		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 0; j < table.getColumnCount(); j++) {

				getData[i][j] = (String) table.getValueAt(i, j);
			}
		}

		try {
			File file = new File(FilePath2);
			if (!file.exists()) {
				
				file.mkdir();
			}
			String year = yearBox.getSelectedItem().toString()+"년 ";
			String month = monthBox.getSelectedItem().toString()+ "월";
			String cno = comboBox.getSelectedItem().toString();
			String level = " "+cno.substring(cno.indexOf(",")+2,cno.length())+"반";
			FilePath = FilePath2 +"/"+year+month+cno.substring(0,cno.indexOf("/"))+"반"+".xls";
			File file1 = new File(FilePath);

			if (!file1.exists()) {
				file1.createNewFile();
			}
			WritableWorkbook workbook = Workbook.createWorkbook(file1);
			WritableSheet sheet = workbook.createSheet(SheetName, 0);
			WritableFont centerFontBold = new WritableFont(WritableFont.ARIAL,11,WritableFont.BOLD); 
			WritableCellFormat format_column = new WritableCellFormat();
			WritableCellFormat format_name = new WritableCellFormat(centerFontBold);
			WritableCellFormat format_data = new WritableCellFormat();
			WritableCellFormat format_date = new WritableCellFormat(centerFontBold);
			format_column.setBackground(jxl.format.Colour.BRIGHT_GREEN);
			format_name.setBackground(jxl.format.Colour.VERY_LIGHT_YELLOW);
			format_name.setAlignment(Alignment.LEFT);
			format_date.setAlignment(Alignment.CENTRE);
			format_column.setAlignment(Alignment.CENTRE);
			format_data.setAlignment(Alignment.CENTRE);
			format_name.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			format_column.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			Label titleLabel = new Label(getColumn.length-3,0,year+month+level,format_date);
			sheet.addCell(titleLabel);
			sheet.mergeCells(getColumn.length-3, 0, getColumn.length-1, 0 );
			for (int i = 0; i < getColumn.length; i++) {
				Label label = null;
				if(i == 0) {
					label = new Label(i, 1, getColumn[i], format_name);
					
				}else {
					label = new Label(i, 1, getColumn[i], format_column);
				}
				
				sheet.addCell(label);
			}

			for (int i = 0; i < getData.length; i++) {
				for (int j = 0; j < getData[i].length; j++) {
					Label label = null;
					if(j == 0) {
						label = new Label(j, i + 2, getData[i][j], format_name);
						
					}else {
						label = new Label(j, i + 2, getData[i][j], format_data);
					}
					sheet.addCell(label);
				}
			}
			workbook.write();
			workbook.close();
			JOptionPane.showMessageDialog(null,"엑셀파일이 생성되었습니다.");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null,"엑셀창을 닫아주세요");
			e1.printStackTrace();
		}
	}
	private List<Integer> monthOfLasyDay(int month) {
		
		Calendar cal = Calendar.getInstance();
		Date d = new Date();
		d.setMonth(month-1);
		cal.setTime(d);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		List<Integer> lists = new ArrayList<>();
		for(int i=1; i <= lastDay; i++) {
			d.setDate(i);
			if(d.getDay() != 0 && d.getDay() !=6) {
				lists.add(i);
			}
		}
		return lists;
	}
}
