package kr.or.dgit.pool_java.content;

import java.util.Calendar;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.dto.Teacher;
import kr.or.dgit.pool_java.frame.ClassScheduleUpdate;
import kr.or.dgit.pool_java.service.ClassService;
import kr.or.dgit.pool_java.service.TeacherService;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class ClassSchedule extends JPanel {
	private JTable table;
	private static final ClassSchedule instance = new ClassSchedule();
	
	public static ClassSchedule getInstance() {
		return instance;
	}
	private ClassSchedule() {
		
		setBounds(0, 0, 900, 570);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 78, 900, 492);
		add(scrollPane);
		DefaultTableModel model = new DefaultTableModel(getData(),getColumnNames());
		table = new JTable(model);
		table.setFont(new Font("굴림", Font.BOLD, 14));
		
		scrollPane.setViewportView(table);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 70, 30);
		add(menuBar);
		
		JMenu mMenu = new JMenu("강의설정");
		menuBar.add(mMenu);
		
		JMenuItem mMenuInsert = new JMenuItem("신규개설");
		mMenuInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("신규개설");
			}
		});
		mMenu.add(mMenuInsert);
		
		JMenuItem mMenuUpdate = new JMenuItem("강의수정");
		mMenuUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("강의수정");
			}
		});
		mMenu.add(mMenuUpdate);
		
		JMenuItem mMenuDelete = new JMenuItem("강의삭제");
		mMenuDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("강의삭제");
			}
		});
		mMenu.add(mMenuDelete);
		
		JLabel lblTitle = new JLabel("");
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblTitle.setBounds(860, 33, 40, 47);
		add(lblTitle);
		Calendar cal = Calendar.getInstance();
		lblTitle.setText((cal.get(Calendar.MONTH)+1)+"월");
		addPopupMenu();
	}
	private String[] getColumnNames() {

		return new String[] {"반 번호","시간", "강사명", "레벨","인원"};
	}
	private Object[][] getData() {
		List<Class> list = ClassService.getInstance().selectByAll();
		
		Object[][] data = new Object[list.size()][];
		for (int i = 0; i <list.size(); i++) {
			data[i] = list.get(i).toArray();
		}
		return data;
	}
	private void addPopupMenu() {
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("수정");
		popupMenu.add(menuItem);
		table.setComponentPopupMenu(popupMenu);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1) {
					return;
				}
				int cno = (int)table.getValueAt(table.getSelectedRow(), 0);
				Class cls = ClassService.getInstance().selectByNo(cno);
				Teacher t = TeacherService.getInstance().selectByNo(cls.getTno());
				System.out.println(cls);
				ClassScheduleUpdate updateFrame = new ClassScheduleUpdate(t.getName(),cls.getClassmate());
				updateFrame.setVisible(true);
			}
		});
	}
}