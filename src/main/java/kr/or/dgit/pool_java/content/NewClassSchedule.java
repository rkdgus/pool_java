package kr.or.dgit.pool_java.content;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.dto.Teacher;
import kr.or.dgit.pool_java.frame.AddClassFrame;
import kr.or.dgit.pool_java.frame.ClassInfoFrame;
import kr.or.dgit.pool_java.frame.ClassScheduleUpdate;
import kr.or.dgit.pool_java.frame.MemberFrame;
import kr.or.dgit.pool_java.service.ClassService;
import kr.or.dgit.pool_java.service.MemberService;
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
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.ComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class NewClassSchedule extends JPanel {
	private JTable table;
	private static final NewClassSchedule instance = new NewClassSchedule();
	private JTextField tfSearch;
	private JButton btnSearch;
	private JComboBox<String> cmbSearch;
	private List<Teacher> lists;
	private JComboBox cmbLevel;
	private JComboBox<String> cmbTno;
	private DefaultComboBoxModel<String> cmbModel;
	private JComboBox cmbTime;
	private JSpinner spinner;
	private JCheckBox cbReclass;
	private JComboBox<String> cbms_day;
	
	public static NewClassSchedule getInstance() {
		return instance;
	}
	private NewClassSchedule() {
		setBounds(0, 0, 900, 570);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 2));
		scrollPane.setBounds(269, 63, 617, 482);
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
		lblTitle.setText(cal.get(Calendar.YEAR)+"년 " + (cal.get(Calendar.MONTH)+2)+"월");
		
		tfSearch = new JTextField();
		tfSearch.setBounds(369, 27, 308, 24);
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
		btnSearch.setBounds(677, 27, 97, 24);
		add(btnSearch);
		
		cmbSearch = new JComboBox<String>();
		cmbSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cmbSearch.setModel(new DefaultComboBoxModel(new String[] {"전체보기","강사명", "레벨"}));
		cmbSearch.setBounds(279, 27, 89, 24);
		add(cmbSearch);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1), "신규 강의 개설", TitledBorder.LEADING, TitledBorder.TOP, new Font(null, Font.BOLD, 18), new Color(0, 0, 0)));
		panel.setBounds(14, 59, 244, 482);
		add(panel);
		
		JLabel label = new JLabel("시간");
		label.setBounds(6, 57, 94, 40);
		panel.add(label);
		
		JLabel label_1 = new JLabel("등급");
		label_1.setBounds(6, 114, 86, 40);
		panel.add(label_1);
		
		cmbLevel = new JComboBox();
		cmbLevel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		cmbLevel.setModel(new DefaultComboBoxModel(new String[] {"초급", "중급", "상급", "고급", "연수", "노인반", "어린이반", "방학특강"}));
		cmbLevel.setBounds(122, 114, 114, 40);
		panel.add(cmbLevel);
		
		JLabel label_2 = new JLabel("강사번호");
		label_2.setBounds(6, 244, 94, 40);
		panel.add(label_2);
		cmbModel = new DefaultComboBoxModel<String>(getDate());
		cmbTno = new JComboBox<String>(cmbModel);
		cmbTno.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		cmbTno.setBounds(122, 244, 114, 40);
		panel.add(cmbTno);
		
		JLabel label_3 = new JLabel("개설일");
		label_3.setBounds(6, 313, 94, 40);
		panel.add(label_3);
		String month = cal.get(Calendar.YEAR)+"년 "+ (cal.get(Calendar.MONTH)+1)+"월";
		String month2 = cal.get(Calendar.YEAR)+"년 "+ (cal.get(Calendar.MONTH)+2)+"월";
		
		cbms_day = new JComboBox<String>();
		cbms_day.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		cbms_day.setBounds(122, 313, 114, 40);
		cbms_day.setModel(new DefaultComboBoxModel(new String[] {month,month2}));
		panel.add(cbms_day);
		
		JButton btnAddClass = new JButton("추가");
		btnAddClass.setBounds(126, 437, 106, 33);
		panel.add(btnAddClass);
		btnAddClass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if((int)spinner.getValue() == 0) {
					JOptionPane.showMessageDialog(null,"총 인원을 입력해주세요");
					return;
				}
				Class cls = new Class();
				
				cls.setTime((String)cmbTime.getSelectedItem());
				cls.setClassmate((int)spinner.getValue());
				cls.setLevel((String)cmbLevel.getSelectedItem());
				cls.setReclass(cbReclass.isSelected());
				if(cbms_day.getSelectedIndex() == 0) {
					Date date = new Date();
					date.setDate(1);
					cls.setS_day(date);
				}else {
					Date date = new Date();
					date.setMonth(date.getMonth()+1);
					date.setDate(1);
					cls.setS_day(date);
				}
				int tno = lists.get(cmbTno.getSelectedIndex()).getTno();
				cls.setTno(tno);
				
				int res = ClassService.getInstance().insertClass(cls);
				if(res >=0) {
					JOptionPane.showMessageDialog(null,"추가하였습니다.");
					if(cbms_day.getSelectedIndex() ==0) {
						ClassSchedule.getInstance().addJTableList();
						resetAll();
						MemberFrame.getInstance().contentCall(ClassSchedule.getInstance());
					}else {
						addJTableList();
					}
					
				}else {
					JOptionPane.showMessageDialog(null,"실패하였습니다.");
				}
			}
		});
		JButton btnCancel = new JButton("취소");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAll();
			}
		});
		btnCancel.setBounds(6, 437, 106, 33);
		panel.add(btnCancel);
		
		cmbTime = new JComboBox();
		cmbTime.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		cmbTime.setModel(new DefaultComboBoxModel(new String[] {"06시 00분", "07시 00분", "08시 00분", "09시 00분", "10시 00분", "11시 00분", "13시 00분", "14시 00분", "15시 00분", "16시 00분", "17시 00분", "18시 00분", "19시 00분", "20시 00분", "21시 00분"}));
		cmbTime.setBounds(122, 57, 114, 40);
		panel.add(cmbTime);
		
		JLabel label_4 = new JLabel("총 인원");
		label_4.setBounds(6, 188, 57, 15);
		panel.add(label_4);
		
		spinner = new JSpinner();
		spinner.setBounds(122, 174, 114, 40);
		panel.add(spinner);
		
		JLabel label_5 = new JLabel("재강습여부");
		label_5.setBounds(6, 378, 94, 18);
		panel.add(label_5);
		
		cbReclass = new JCheckBox("");
		cbReclass.setBounds(169, 378, 34, 27);
		panel.add(cbReclass);
		addJTableList();
		addPopupMenu();
		setAlignWidth();
	}
	private String[] getColumnNames() {
		return new String[] {"반 번호","시간", "강사명", "레벨","인원"};
	}
	private Object[][] getData() {
		List<Class> list = ClassService.getInstance().selectByNextMonth("");
		
		Object[][] data = new Object[list.size()][];
		for (int i = 0; i <list.size(); i++) {
			data[i] = list.get(i).toArray();
		}
		return data;
	}
	private Vector<String> getDate(){
		Vector<String> vt = new Vector<>();
		lists = TeacherService.getInstance().selectByAll();
		for(Teacher t : lists) {
			vt.add(t.getName());
		}
		return vt;
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
		date.setMonth(date.getMonth()+1);
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
		date.setMonth(date.getMonth()+1);
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

	private void resetAll() {
		cmbTime.setSelectedIndex(0);
		cmbLevel.setSelectedIndex(0);
		spinner.setValue(0);
		cmbTno.setSelectedIndex(0);
		cbms_day.setSelectedIndex(0);
		cbReclass.setSelected(false);
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