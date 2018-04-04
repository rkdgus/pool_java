package kr.or.dgit.pool_java.content;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.dto.Teacher;
import kr.or.dgit.pool_java.frame.ClassInfoFrame;
import kr.or.dgit.pool_java.frame.ClassScheduleUpdate;
import kr.or.dgit.pool_java.service.ClassService;
import kr.or.dgit.pool_java.service.TeacherService;

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
		
		tfSearch = new JTextField();
		tfSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
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
		cmbSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cmbSearch.setModel(new DefaultComboBoxModel(new String[] {"전체보기","강사명", "레벨"}));
		cmbSearch.setBounds(222, 29, 89, 24);
		add(cmbSearch);
		
		addJTableList();
		setAlignWidth();
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
		setAlignWidth();
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
		setAlignWidth();
		
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
		setAlignWidth();
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
	public void setAlignWidth() {
		setAlign(SwingConstants.CENTER,0,1,2,3,4);
		setCellWidth(30, 260, 80, 80,80);
	}
	
	public void setCellWidth(int...width) {
		TableColumnModel cModel = table.getColumnModel();
		for(int i=0; i<width.length; i++){
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}
	}
	
	
	public void setAlign(int align, int...idx) {
		//0번 컬럼을 정렬(Left, Right, Center)
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		
		TableColumnModel cModel = table.getColumnModel();
		// idx = [0,2]
		for(int i=0; i<idx.length;i++){
			cModel.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}
}