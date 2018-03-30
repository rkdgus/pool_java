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
import kr.or.dgit.pool_java.frame.AddClassFrame;
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
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class ClassSchedule extends JPanel {
	private JTable table;
	private static final ClassSchedule instance = new ClassSchedule();
	private JTextField textField;
	private JButton btnSearch;
	
	public static ClassSchedule getInstance() {
		return instance;
	}
	private ClassSchedule() {
		
		setBounds(0, 0, 900, 570);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 78, 888, 482);
		add(scrollPane);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		scrollPane.setViewportView(table);
		
		JLabel lblTitle = new JLabel("");
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblTitle.setBounds(787, 25, 108, 47);
		add(lblTitle);
		Calendar cal = Calendar.getInstance();
		lblTitle.setText(cal.get(Calendar.YEAR)+"년 " + (cal.get(Calendar.MONTH)+1)+"월");
		
		JButton btnAddClass = new JButton("신규 강의");
		btnAddClass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddClassFrame frame = new AddClassFrame();
				frame.setVisible(true);
			}
		});
		btnAddClass.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btnAddClass.setBounds(7, 44, 97, 24);
		add(btnAddClass);
		
		textField = new JTextField();
		textField.setBounds(311, 44, 308, 24);
		add(textField);
		textField.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btnSearch.setBounds(619, 44, 97, 24);
		add(btnSearch);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"강사명", "반 번호"}));
		comboBox.setBounds(221, 44, 89, 24);
		add(comboBox);
		addJTableList();
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
	public void addJTableList() {
		DefaultTableModel model = new DefaultTableModel(getData(),getColumnNames());
		table.setModel(model);
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
				if(table.getSelectedRow() == -1) {
					return;
				}
				int cno = (int)table.getValueAt(table.getSelectedRow(), 0);
				Class cls = ClassService.getInstance().selectByNo(cno);
				Teacher t = TeacherService.getInstance().selectByNo(cls.getTno());
				System.out.println(cls);
				ClassScheduleUpdate updateFrame = new ClassScheduleUpdate(t.getName(),cls.getClassmate(),cno);
				updateFrame.setVisible(true);
			}
		});
		
		menuItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1) {
					return;
				}
				if(JOptionPane.showConfirmDialog(null,"삭제하시겠습니까?") ==0) {
					int cno = (int)table.getValueAt(table.getSelectedRow(), 0);
					int res = ClassService.getInstance().deleteClass(cno);
					if(res >=0) {
						JOptionPane.showMessageDialog(null,"삭제되었습니다.");
						addJTableList();
					}else {
						JOptionPane.showMessageDialog(null,"삭제가 되지 않았습니다...");
					}
				}
			}
		});
	}
}