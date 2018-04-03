package kr.or.dgit.pool_java.content;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.pool_java.dao.AttendanceDao;
import kr.or.dgit.pool_java.dao.RegisterDao;
import kr.or.dgit.pool_java.dto.Attendance;
import kr.or.dgit.pool_java.dto.Register;
import kr.or.dgit.pool_java.service.AttendanceService;
import kr.or.dgit.pool_java.service.MemberService;
import kr.or.dgit.pool_java.service.RegisterService;

public class AttendPanel extends JPanel {
	private JTable table;
	private AttendanceDao aDao;
	private RegisterDao rDao;
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
		
		
		List<Register> lists = rDao.selectByCno(2);
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
		Object[] d = new Object[day[month]];
		Attendance attendance =null;
		List<Attendance> list =null ;
		String m = "";
		String s = "";
		if(month<10) {
			m = "0"+month;
		}else {
			m=month+"";
		}
		for (int i = 0; i < lists.size(); i++) {
			for(int j=0;j<colum.length;j++) {
				if(j==0) {
					d[j] = MemberService.getInstance().selectMno(lists.get(i).getMno()).getName();
				}else {
					if(j<10) {
						s = "0"+j;
					}else {
						s=j+"";
					}
					attendance = new Attendance(sf.parse(year+"-"+m+"-"+s),lists.get(i).getMno());
					list = aDao.selectDate(attendance);
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
