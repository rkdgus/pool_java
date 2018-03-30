package kr.or.dgit.pool_java.content;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.dto.Teacher;
import kr.or.dgit.pool_java.frame.AddClassFrame;
import kr.or.dgit.pool_java.frame.ClassInfoFrame;
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
import java.text.SimpleDateFormat;
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
	private JTextField tfSearch;
	private JButton btnSearch;
	private JComboBox<String> cmbSearch;
	
	public static ClassSchedule getInstance() {
		return instance;
	}
	private ClassSchedule() {
		setBounds(0, 0, 900, 570);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 63, 888, 482);
		add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() ==2) {
					int row = table.getSelectedRow();
					int cno = (int)table.getValueAt(row,0);
					Class cls = ClassService.getInstance().selectByNo(cno);
					ClassInfoFrame frame = new ClassInfoFrame(cls);
					frame.setVisible(true);
				}
			}
		});
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		JLabel lblTitle = new JLabel("");
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblTitle.setBounds(788, 16, 108, 47);
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
		btnAddClass.setBounds(8, 29, 97, 24);
		add(btnAddClass);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(312, 29, 308, 24);
		add(tfSearch);
		tfSearch.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(cmbSearch.getSelectedItem().equals("전체보기")) {
					addJTableList();
					tfSearch.setText("");
				}else if(cmbSearch.getSelectedItem().equals("강사명")) {
					String teacher = tfSearch.getText();
					searchTearch(teacher);
					tfSearch.setText("");
				}else if(cmbSearch.getSelectedItem().equals("레벨")) {
					String level = tfSearch.getText();
					searchLevel(level);
					tfSearch.setText("");
				}
			}
		});
		btnSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btnSearch.setBounds(620, 29, 97, 24);
		add(btnSearch);
		
		cmbSearch = new JComboBox<String>();
		cmbSearch.setModel(new DefaultComboBoxModel(new String[] {"전체보기","강사명", "레벨"}));
		cmbSearch.setBounds(222, 29, 89, 24);
		add(cmbSearch);
		addJTableList();
		addPopupMenu();
	}
	private String[] getColumnNames() {
		return new String[] {"반 번호","시간", "강사명", "레벨","인원"};
	}
	private Object[][] getData() {
		List<Class> list = ClassService.getInstance().selectBytoMonth("");
		
		Object[][] data = new Object[list.size()][];
		for (int i = 0; i <list.size(); i++) {
			data[i] = list.get(i).toArray();
		}
		return data;
	}
	public void addJTableList() {
		DefaultTableModel model = new DefaultTableModel(getData(),getColumnNames()) {
	         @Override
	         public boolean isCellEditable(int row, int column) {
	            return false;
	         }
	         
	      };;
		table.setModel(model);
	}
	
	public void searchTearch(String teacher) {
		Class cls = new Class();
		cls.setLevel("%"+teacher+"%");
		Date date = new Date();
		date.setDate(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		cls.setTime(sdf.format(date));
		
		DefaultTableModel model = new DefaultTableModel(getTnoData(cls),getColumnNames()) {
	         @Override
	         public boolean isCellEditable(int row, int column) {
	            return false;
	         }
	         
	      };;
		table.setModel(model);
		
	}
	public void searchLevel(String level) {
		Class cls = new Class();
		cls.setLevel("%"+level+"%");
		Date date = new Date();
		date.setDate(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		cls.setTime(sdf.format(date));
		DefaultTableModel model = new DefaultTableModel(getLevelData(cls),getColumnNames()) {
	         @Override
	         public boolean isCellEditable(int row, int column) {
	            return false;
	         }
	         
	      };;
		table.setModel(model);
	}
	private Object[][] getLevelData(Class cls) {
		List<Class> lists = ClassService.getInstance().selectByLevel(cls);
		Object[][] data = new Object[lists.size()][];
		for (int i = 0; i <lists.size(); i++) {
			data[i] = lists.get(i).toArray();
		}
		return data;
	}
	private Object[][] getTnoData(Class cls) {
		List<Class> list = ClassService.getInstance().selectByTno(cls);
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