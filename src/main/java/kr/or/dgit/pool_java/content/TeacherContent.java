package kr.or.dgit.pool_java.content;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Button;

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
	private JTextField id;
	private JPasswordField pw;
	private JButton idCheckBtn;
	private int idCheck=-1;
	
	public TeacherContent() {
		setLayout(null);

		setBounds(0, 0, 900, 570);
		searchCombo = new JComboBox<>();
		searchCombo.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		searchCombo.setBounds(23, 22, 117, 30);
		searchCombo.addItem("전체보기");
		searchCombo.addItem("이름");
		searchCombo.addItem("직급");
		add(searchCombo);

		searchtf = new JTextField();
		searchtf.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		searchtf.setBounds(140, 22, 298, 30);
		add(searchtf);
		searchtf.setColumns(10);
		searchtf.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String keyWord = searchtf.getText();
					String type = searchCombo.getSelectedItem().toString();
					Search(type, keyWord);
				}

			}

		});
		searchBtn = new JButton("검색");
		searchBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		searchBtn.setBounds(450, 22, 97, 30);
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String keyWord = searchtf.getText();
				String type = searchCombo.getSelectedItem().toString();
				Search(type, keyWord);
			}
		});
		add(searchBtn);

		JLabel tnolbl = new JLabel("강사번호");
		tnolbl.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tnolbl.setBounds(559, 77, 57, 15);
		add(tnolbl);

		tno = new JTextField();
		tno.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tno.setBounds(643, 70, 233, 30);
		tno.setEnabled(false);
		add(tno);
		tno.setColumns(10);

		JLabel namelbl = new JLabel("이름");
		namelbl.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		namelbl.setBounds(580, 242, 36, 15);
		add(namelbl);

		name = new JTextField();
		name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		name.setBounds(643, 235, 233, 30);
		add(name);
		name.setColumns(10);

		JLabel telllbl = new JLabel("전화번호");
		telllbl.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		telllbl.setBounds(559, 297, 57, 15);
		add(telllbl);

		tell1 = new JTextField();
		tell1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tell1.setColumns(10);
		tell1.setBounds(643, 296, 66, 30);
		tell1.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					getToolkit().beep();
					e.consume();
				} else {
					if (tell1.getText().length() >= 2) {
						tell2.requestFocus();
						if ((c == KeyEvent.VK_BACK_SPACE)||(c == KeyEvent.VK_DELETE)) {
							tell1.requestFocus();
						}
					}
				}

			}

		});
		add(tell1);

		JLabel telllbl1 = new JLabel("-");
		telllbl1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		telllbl1.setBounds(715, 303, 13, 15);
		add(telllbl1);

		tell2 = new JTextField();
		tell2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tell2.setColumns(10);
		tell2.setBounds(725, 297, 66, 30);
		tell2.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					getToolkit().beep();
					e.consume();
				} else {
					if (tell2.getText().length() >= 3) {
						tell3.requestFocus();
						if ((c == KeyEvent.VK_BACK_SPACE)||(c == KeyEvent.VK_DELETE)) {
							tell2.requestFocus();
						}
					}
				}
			}

		});
		add(tell2);

		JLabel tellllbl2 = new JLabel("-");
		tellllbl2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tellllbl2.setBounds(797, 304, 13, 15);
		add(tellllbl2);

		tell3 = new JTextField();
		tell3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tell3.setColumns(10);
		tell3.setBounds(808, 297, 66, 30);
		add(tell3);
		tell3.addKeyListener(new KeyAdapter() {
			@Override

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					getToolkit().beep();
					e.consume();
				} else {
					if (tell3.getText().length() >= 3) {
						titleCombo.requestFocus();
						if ((c == KeyEvent.VK_BACK_SPACE)||(c == KeyEvent.VK_DELETE)) {
							tell3.requestFocus();
						}
					}
				}

			}

		});

		addBtn = new JButton("추가");
		addBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		addBtn.setBounds(724, 417, 75, 23);
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (emptyCheck() > 0 && selectCheck() > 0 
						&& idCheck>0) {
					TeacherService.getInstance().insertTeacher(getTextData("insert"));
				} else if (emptyCheck() < 0) {
					JOptionPane.showMessageDialog(null, "공백이 존재합니다. 모두 입력해주세요.");
					return;
				} else if (selectCheck() < 0) {
					JOptionPane.showMessageDialog(null, "직급을 선택해주세요");
					return;
				}else if(idCheck<0) {
					JOptionPane.showMessageDialog(null, "아이디 중복을 확인해 주세요");
					return;
				}

				
				clearField();
				loadData();
				idCheckBtn.setText("중복확인");
				idCheck=-1;
			}
		});
		add(addBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 65, 524, 474);
		add(scrollPane);

		table = new JTable();
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		loadData();
		addPopupMenu();
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					tno.setEnabled(false);
					pw.setEnabled(false);
					id.setEnabled(false);
					idCheckBtn.setEnabled(false);
					setTextData();
				}
			}

		});
		scrollPane.setViewportView(table);

		JLabel titlelbl = new JLabel("직급");
		titlelbl.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		titlelbl.setBounds(579, 348, 57, 15);
		add(titlelbl);

		titleCombo = new JComboBox<>();
		titleCombo.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		titleCombo.setBounds(643, 351, 106, 30);
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
		updateBtn.setBounds(725, 417, 75, 23);
		add(updateBtn);
		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(name.getText().equals("")||tell1.getText().equals("")||tell2.getText().equals("")||tell3.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "공백이 존재합니다. 모두 입력해주세요.");
					return;
				}
				if (selectCheck() < 0) {
					JOptionPane.showMessageDialog(null, "직급을 선택해주세요");
					return;
				}
				
				TeacherService.getInstance().updateTeacher(getTextData("update"));
				
				updateBtn.setVisible(false);
				addBtn.setVisible(true);
				pw.setEnabled(true);
				id.setEnabled(true);
				idCheckBtn.setEnabled(true);
				idCheckBtn.setText("중복확인");
				loadData();
				clearField();
			}
		});

		cancel = new JButton("취소");
		cancel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateBtn.setVisible(false);
				addBtn.setVisible(true);
				pw.setEnabled(true);
				id.setEnabled(true);
				idCheckBtn.setEnabled(true);
				idCheckBtn.setText("중복확인");
				idCheck=-1;
				clearField();
			}
		});
		cancel.setBounds(801, 417, 75, 23);
		add(cancel);
		
		JLabel idlbl = new JLabel("아이디");
		idlbl.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		idlbl.setBounds(569, 133, 36, 15);
		add(idlbl);
		
		id = new JTextField();
		id.setBounds(643, 126, 148, 30);
		id.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				idCheckBtn.setText("중복확인");
				idCheck=-1;
			}
			
			
		});
		add(id);
		id.setColumns(10);
		
		pw = new JPasswordField();
		pw.setColumns(10);
		pw.setBounds(643, 183, 233, 30);
		add(pw);
		
		JLabel pwlbl = new JLabel("비밀번호");
		pwlbl.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		pwlbl.setBounds(559, 190, 57, 15);
		add(pwlbl);
		
		idCheckBtn = new JButton("중복확인");
		idCheckBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		idCheckBtn.setBounds(793, 126, 83, 30);
		idCheckBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Teacher> t = TeacherService.getInstance().findId(id.getText());
				
				if(t.size()==0) {
					JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다!");
					idCheckBtn.setText("사용가능");
					idCheck=1;
				}else {
					JOptionPane.showMessageDialog(null, "이미 등록된 아이디 입니다!(사용불가)");
					idCheckBtn.setText("중복확인");
					idCheck=-1;
				}
				
			}
		});
		add(idCheckBtn);
		updateBtn.setVisible(false);

	}

	private void loadData() {
		DefaultTableModel model = new DefaultTableModel(getData(), getColumnNames()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
			

		};

		table.setModel(model);
		setAlignWidth();
	}

	private String[] getColumnNames() {

		return new String[] { "강사번호", "이름", "전화번호", "직급", "재등록률" };
	}

	private Object[][] getData() {
		List<Teacher> list = TeacherService.getInstance().selectByAll();

		Object[][] data = new Object[list.size()][];

		for (int i = 0; i < list.size(); i++) {

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
		pw.setText("");
		id.setText("");
		titleCombo.setSelectedIndex(0);
	}

	private Teacher getTextData(String type) {
		String tell = tell1.getText() + "-" + tell2.getText() + "-" + tell3.getText();
		Teacher teacher = null;

		if (type == "insert") {
			teacher = new Teacher(name.getText(), tell, titleCombo.getSelectedItem().toString(), pw.getText(), id.getText());
		}
		if (type == "update") {
			teacher = new Teacher(Integer.parseInt(tno.getText()), name.getText(), tell,
					titleCombo.getSelectedItem().toString());
		}

		return teacher;
	}

	private void setTextData() {

		int row = table.getSelectedRow();
		int no = (int) table.getValueAt(row, 0);
		Teacher t = TeacherService.getInstance().selectByNo(no);

		tno.setText(String.valueOf(t.getTno()));
		name.setText(t.getName());
		titleCombo.setSelectedItem(t.getTitle());

		String phone = t.getTell();
		tell1.setText(phone.substring(0, phone.indexOf("-")));
		tell2.setText(phone.substring(phone.indexOf("-") + 1, phone.lastIndexOf("-")));
		tell3.setText(phone.substring(phone.lastIndexOf("-") + 1));
		id.setText(t.getId());
		
		updateBtn.setVisible(true);
		cancel.setVisible(true);
		addBtn.setVisible(false);

	}

	private void addPopupMenu() {
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("수정");
		JMenuItem menuItem2 = new JMenuItem("퇴사");
		JMenuItem menuItem3 = new JMenuItem("삭제");
		
		popupMenu.add(menuItem);
		popupMenu.add(menuItem2);
		popupMenu.add(menuItem3);
		
		table.setComponentPopupMenu(popupMenu);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tno.setEnabled(false);
				pw.setEnabled(false);
				id.setEnabled(false);
				idCheckBtn.setEnabled(false);
				idCheckBtn.setText("중복확인");
				idCheck=-1;
				setTextData();
			}
		});
		menuItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "퇴사 강사로 등록하시겠습니까?", "퇴사 강사 등록",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				if (reply == JOptionPane.YES_OPTION) {
					int row = table.getSelectedRow();
					int tno = (int) table.getValueAt(row, 0);
					Teacher teacher = new Teacher();
					teacher.setTno(tno);
					teacher.setTitle("퇴사");
					TeacherService.getInstance().quitTeacher(teacher);
					loadData();
					updateBtn.setVisible(false);
					addBtn.setVisible(true);
					pw.setEnabled(true);
					id.setEnabled(true);
					idCheckBtn.setEnabled(true);
					idCheckBtn.setText("중복확인");
					idCheck=-1;
					clearField();
				}
			}
		});
		
		
		menuItem3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "강사정보를 삭제하시겠습니까?", "강사정보 삭제",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				if (reply == JOptionPane.YES_OPTION) {
					int row = table.getSelectedRow();
					int tno = (int) table.getValueAt(row, 0);
					TeacherService.getInstance().deleteTeacher(tno);
					loadData();
					updateBtn.setVisible(false);
					addBtn.setVisible(true);
					pw.setEnabled(true);
					id.setEnabled(true);
					idCheckBtn.setText("중복확인");
					idCheckBtn.setEnabled(true);
					idCheck=-1;
					clearField();
				}
			}
		});
	}

	private void Search(String type, String keyWord) {
		if (type.equals("전체보기") || keyWord.equals("")) {
			loadData();
		}

		if (type.equals("강사번호") || type.equals("이름") || type.equals("직급")) {
			loadSearchData(type, keyWord);
		}

	}

	private void loadSearchData(String type, String keyWord) {

		DefaultTableModel model = new DefaultTableModel(getSearchData(type, keyWord), getColumnNames()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		table.setModel(model);
		setAlignWidth();
	}

	private Object[][] getSearchData(String type, String keyWord) {

		List<Teacher> list = null;

		if (type.equals("이름")) {
			list = TeacherService.getInstance().searchName("%" + keyWord + "%");
		}

		if (type.equals("직급")) {
			list = TeacherService.getInstance().searchTitle("%" + keyWord + "%");
		}

		Object[][] data = new Object[list.size()][];

		for (int i = 0; i < list.size(); i++) {

			data[i] = list.get(i).toArray();

		}

		return data;
	}

	public JTextField getTno() {
		return tno;
	}

	public void setBarCode(Teacher t) {
		tno.setText(String.valueOf(t.getTno()));
		name.setText(t.getName());
		titleCombo.setSelectedItem(t.getTitle());

		String phone = t.getTell();
		tell1.setText(phone.substring(0, phone.indexOf("-")));
		tell2.setText(phone.substring(phone.indexOf("-") + 1, phone.lastIndexOf("-")));
		tell3.setText(phone.substring(phone.lastIndexOf("-") + 1));

		updateBtn.setVisible(true);
		cancel.setVisible(true);
		addBtn.setVisible(false);
	}

	private int emptyCheck() {
		int check = -1;

		if (!name.getText().equals("") && !tell1.getText().equals("")
				&& !tell2.getText().equals("") && !tell3.getText().equals("") && !id.getText().equals("")&& !pw.getText().equals("")) {
			check = 1;
		}
		return check;
	}

	private int selectCheck() {
		int select = -1;
		if (titleCombo.getSelectedIndex() > 0) {
			select = 1;
		}
		return select;
	}
	
	
	public void setAlignWidth() {
		setAlign(SwingConstants.CENTER,0,1,2,3);
		setAlign(SwingConstants.RIGHT, 4);
		setCellWidth(70, 70, 100, 40,50);
	}
	
	public void setCellWidth(int...width) {
		TableColumnModel cModel = table.getColumnModel();
		for(int i=0; i<width.length; i++){
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}
	}
	
	
	public void setAlign(int align, int...idx) {
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		
		TableColumnModel cModel = table.getColumnModel();
	
		for(int i=0; i<idx.length;i++){
			cModel.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}
}
