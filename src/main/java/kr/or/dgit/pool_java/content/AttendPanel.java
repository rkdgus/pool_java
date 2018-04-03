package kr.or.dgit.pool_java.content;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import kr.or.dgit.pool_java.dao.AttendanceDao;
import kr.or.dgit.pool_java.dao.RegisterDao;
import kr.or.dgit.pool_java.dto.Attendance;
import kr.or.dgit.pool_java.dto.Register;
import kr.or.dgit.pool_java.service.AttendanceService;
import kr.or.dgit.pool_java.service.MemberService;
import kr.or.dgit.pool_java.service.RegisterService;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class AttendPanel extends JPanel {
	private JTable table;
	private AttendanceDao aDao;
	private RegisterDao rDao;
	String FilePath2 = "D:/excelData";
	String FilePath = "D:/excelData/data2.xls";
	String SheetName = "test";
	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public AttendPanel() throws ParseException {
		setLayout(null);
		this.rDao = RegisterService.getInstance();
		this.aDao = AttendanceService.getInstance();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 50, 878, 423);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("프린트");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excelFile();
			}
		});
		btnNewButton.setBounds(774, 10, 97, 30);
		add(btnNewButton);
		

		List<Register> lists = RegisterService.getInstance().selectByCno(6);
		loadDataPrice(lists, 4, 2018);
		
		
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
	
	
	
	protected Object[][] getDataPrice(List<Register> lists,int month,int year) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		int[] day= {32,29,32,31,32,31,32,32,31,32,31,32};
		String[] colum =new String[day[month]];
		Object[][] data = new Object[lists.size()][];
		Attendance attendance =null;
		List<Attendance> list =null ;
		String m = "";
		String s = "";
		HashMap<String, Object> map = null;
		
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
					
					d[j]="O";
				}
			}
			
			data[i] = d;

		}
		return data;
	}
	
	private void excelFile() {
		String[] getColumn = new String[table.getColumnCount()];
		for(int i = 0 ; i < table.getColumnCount(); i ++) {
			getColumn[i] = table.getColumnName(i);
		}
		
		
		String[][] getData = new String[table.getRowCount()][table.getColumnCount()];
		
		for(int i = 0; i < table.getRowCount(); i ++) {
			for(int j = 0; j < table.getColumnCount(); j++) {
				System.out.println((String)table.getValueAt(i,j));
				getData[i][j] = (String)table.getValueAt(i,j);
			}
		}
		
		try {
			File file = new File(FilePath2);
			if(!file.exists()) {
				System.out.println();
				file.mkdir();
			}
			File file1 = new File(FilePath);
			
			if(!file1.exists()) {
				file1.createNewFile();
			}
			WritableWorkbook workbook = Workbook.createWorkbook(file1);
			WritableSheet sheet = workbook.createSheet(SheetName,0);
			
			WritableCellFormat format_column = new WritableCellFormat();
			WritableCellFormat format_data = new WritableCellFormat();
			WritableCellFormat format_integer1 = new WritableCellFormat(NumberFormats.INTEGER);
			format_column.setBackground(jxl.format.Colour.GRAY_25);
			format_column.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
			for(int i =0; i < getColumn.length; i++) {
				Label label = new Label(i,0,getColumn[i],format_column);
				sheet.addCell(label);
			}
			
			for(int i = 0; i<getData.length; i++) {
				for(int j = 0; j < getData[i].length; j++) {
					Label label = new Label(j,i+1,getData[i][j],format_data);
					sheet.addCell(label);
				}
			}
			workbook.write();
			workbook.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
}
