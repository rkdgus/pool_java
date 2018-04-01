package kr.or.dgit.pool_java.content;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.pool_java.dto.Member;
import kr.or.dgit.pool_java.dto.Teacher;
import kr.or.dgit.pool_java.service.MemberService;
import kr.or.dgit.pool_java.service.TeacherService;

import javax.swing.JButton;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class TeacherContent extends JPanel {
	private JTextField tno;
	private JTextField name;
	private JTable table;
	private JTextField searchtf;
	private JTextField tell3;
	private JTextField tell2;
	private JTextField tell1;
	private JComboBox<String> searchCombo;
	private JButton updateBtn;
	private JButton searchBtn;
	private JButton addBtn;
	private JComboBox<String> titleCombo;
	private JButton cancel;
	
	public TeacherContent() {
		setLayout(null);
	
		setBounds(0, 0, 900, 570);	
		searchCombo = new JComboBox<>();
		searchCombo.setBounds(23, 22, 117, 30);
		searchCombo.addItem("전체보기");
		searchCombo.addItem("강사번호");
		searchCombo.addItem("이름");
		searchCombo.addItem("직급");
		add(searchCombo);
		
		searchtf = new JTextField();
		searchtf.setBounds(140, 22, 298, 30);
		add(searchtf);
		searchtf.setColumns(10);
		searchtf.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					String keyWord = searchtf.getText();
					String type=searchCombo.getSelectedItem().toString();
					Search(type,keyWord);
				}
			
			}
			
		});
		searchBtn = new JButton("검색");
		searchBtn.setBounds(450, 22, 97, 30);
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String keyWord = searchtf.getText();
				String type=searchCombo.getSelectedItem().toString();
				Search(type,keyWord);
			}
		});
		add(searchBtn);
		
		JLabel tnolbl = new JLabel("강사번호");
		tnolbl.setBounds(579, 109, 57, 15);
		add(tnolbl);
		
		tno = new JTextField();
		tno.setEditable(false);
		tno.setBounds(662, 102, 215, 30);
		add(tno);
		tno.setColumns(10);
		
		JLabel namelbl = new JLabel("이름");
		namelbl.setBounds(579, 167, 57, 15);
		add(namelbl);
		
		name = new JTextField();
		name.setBounds(662, 160, 219, 30);
		add(name);
		name.setColumns(10);
		
		JLabel telllbl = new JLabel("전화번호");
		telllbl.setBounds(579, 225, 57, 15);
		add(telllbl);
		
		tell1 = new JTextField();
		tell1.setColumns(10);
		tell1.setBounds(662, 218, 66, 30);
		add(tell1);
		
		JLabel telllbl1 = new JLabel("-");
		telllbl1.setBounds(731, 224, 13, 15);
		add(telllbl1);
		
		tell2 = new JTextField();
		tell2.setColumns(10);
		tell2.setBounds(739, 218, 66, 30);
		add(tell2);
		
		JLabel tellllbl2 = new JLabel("-");
		tellllbl2.setBounds(808, 225, 13, 15);
		add(tellllbl2);
		
		tell3 = new JTextField();
		tell3.setColumns(10);
		tell3.setBounds(817, 218, 66, 30);
		add(tell3);
		
		addBtn = new JButton("추가");
		addBtn.setBounds(802, 357, 75, 23);
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TeacherService.getInstance().insertTeacher(getTextData("insert"));
				clearField();
				loadData();
			}
		});
		add(addBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 65, 524, 474);
		add(scrollPane);
		
		table = new JTable();
		loadData();
		addPopupMenu();
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					setTextData();
				}
			}
			
		});
		scrollPane.setViewportView(table);
		
		JLabel titlelbl = new JLabel("직급");
		titlelbl.setBounds(579, 281, 57, 15);
		add(titlelbl);
		
		titleCombo = new JComboBox<>();
		titleCombo.setBounds(662, 273, 106, 30);
		titleCombo.addItem("선택");
		titleCombo.addItem("사원");
		titleCombo.addItem("대리");
		titleCombo.addItem("과장");
		titleCombo.addItem("차장");
		titleCombo.addItem("부장");
		titleCombo.addItem("이사");
		titleCombo.addItem("사장");
		add(titleCombo);
		
		updateBtn = new JButton("수정");
		updateBtn.setBounds(725, 357, 75, 23);
		add(updateBtn);
		updateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TeacherService.getInstance().updateTeacher(getTextData("update"));
				cancel.setVisible(false);
				updateBtn.setVisible(false);
				addBtn.setVisible(true);
				loadData();
				clearField();
			}
		});
		
		titleCombo.setBounds(662, 273, 106, 30);
		titleCombo.addItem("선택");
		titleCombo.addItem("사원");
		titleCombo.addItem("대리");
		titleCombo.addItem("과장");
		titleCombo.addItem("차장");
		titleCombo.addItem("부장");
		titleCombo.addItem("이사");
		titleCombo.addItem("사장");
		add(titleCombo);
		
		cancel = new JButton("취소");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel.setVisible(false);
				updateBtn.setVisible(false);
				addBtn.setVisible(true);
				clearField();
			}
		});
		cancel.setBounds(802, 357, 75, 23);
		add(cancel);
		cancel.setVisible(false);
		updateBtn.setVisible(false);
		
	}
	
	private void loadData() {
		DefaultTableModel model = new DefaultTableModel(getData(),getColumnNames()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
	
		table.setModel(model);
	}
	
	private String[] getColumnNames() {

		return new String[] { "강사번호", "이름","전화번호","직급"};
	}
	private Object[][] getData() {
		List<Teacher> list = TeacherService.getInstance().selectByAll();
		
		Object[][] data = new Object[list.size()][];
		
		
			for (int i = 0; i <list.size(); i++) {
				
				data[i] = list.get(i).toArray();
				
			}
	
		return data;
	}
	
	private void clearField() {
		tno.setText("");
		name.setText("");
		tell1.setText("");
		tell2.setText("");
		tell3.setText("");
		titleCombo.setSelectedIndex(0);
	}
	
	private Teacher getTextData(String type) {
		String tell = tell1.getText()+"-"+tell2.getText()+"-"+tell3.getText();
		Teacher teacher = null;
		
		if(type=="insert") {
			teacher = new Teacher(name.getText(), tell, titleCombo.getSelectedItem().toString(),"teacher");
		}
		if(type=="update") {
			teacher = new Teacher(Integer.parseInt(tno.getText()), name.getText(), tell, titleCombo.getSelectedItem().toString());
		}
		
		return teacher;
	}
	
	private void setTextData() {
		
		int row = table.getSelectedRow();
		int no = (int)table.getValueAt(row, 0);
		Teacher t = TeacherService.getInstance().selectByNo(no);
		
		tno.setText(String.valueOf(t.getTno()));
		name.setText(t.getName());
		titleCombo.setSelectedItem(t.getTitle());
		
		String phone = t.getTell();
		tell1.setText(phone.substring(0, phone.indexOf("-")));
		tell2.setText(phone.substring(phone.indexOf("-")+1,phone.lastIndexOf("-")));
		tell3.setText(phone.substring(phone.lastIndexOf("-")+1));
		
		updateBtn.setVisible(true);
		cancel.setVisible(true);
		addBtn.setVisible(false);
		
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
				setTextData();
			}
		});      
	      menuItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "강사정보를 삭제하시겠습니까?","회원정보 삭제",JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
				if(reply==JOptionPane.YES_OPTION) {
					int row = table.getSelectedRow();
					int tno =(int)table.getValueAt(row, 0);
					System.out.println(tno);
					TeacherService.getInstance().deleteTeacher(tno);
					loadData();
				}
			}
		});
	      
	}
	
	private void Search(String type,String keyWord) {
		if(type.equals("전체보기")||keyWord.equals("")) {
			loadData();
		}
		
		if(type.equals("강사번호")||type.equals("이름")||type.equals("직급")) {
			loadSearchData(type,keyWord);
		}
		
	}
	
	private void loadSearchData(String type,String keyWord) {
		
		DefaultTableModel model = new DefaultTableModel(getSearchData(type,keyWord),getColumnNames()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
	
		table.setModel(model);
	}
	
	private Object[][] getSearchData(String type,String keyWord) {
		
		List<Teacher> list = null;
		
		if(type.equals("이름")) {
			list = TeacherService.getInstance().searchName("%"+keyWord+"%");
		}
		
		if(type.equals("직급")) {
			list = TeacherService.getInstance().searchTitle("%"+keyWord+"%");
		}
		
		if(type.equals("강사번호")) {
			list = TeacherService.getInstance().searchTno("%"+keyWord+"%");
		}
		
		Object[][] data = new Object[list.size()][];
		
			for (int i = 0; i <list.size(); i++) {
				
				data[i] = list.get(i).toArray();
				
			}
	
		return data;
	}
	
}
