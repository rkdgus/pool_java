package kr.or.dgit.pool_java.content;

import java.util.Calendar;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.service.ClassService;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class IectureSchedule extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public IectureSchedule() {
		
		setBounds(0, 0, 900, 570);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 78, 900, 492);
		add(scrollPane);
		DefaultTableModel model = new DefaultTableModel(getData(),getColumnNames());
		table = new JTable(model);
		
		scrollPane.setViewportView(table);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 70, 30);
		add(menuBar);
		
		JMenu mnNewMenu = new JMenu("강의설정");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("신규개설");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("강의수정");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem menuItem = new JMenuItem("강의삭제");
		mnNewMenu.add(menuItem);
		
		JLabel lblTitle = new JLabel("");
		lblTitle.setBounds(223, 10, 453, 47);
		add(lblTitle);
		Calendar cal = Calendar.getInstance();
		lblTitle.setText(cal.get(Calendar.MONTH)+"월");
	}
	private String[] getColumnNames() {

		return new String[] { "시간", "강사명", "레벨","인원"};
	}
	private Object[][] getData() {
		List<Class> list = ClassService.getInstance().selectByAll();
		
		Object[][] data = new Object[list.size()][];
		
		for (int i = 0; i <list.size(); i++) {
			
			data[i] = list.get(i).toArray();
			
		}
		return data;
	}
}
