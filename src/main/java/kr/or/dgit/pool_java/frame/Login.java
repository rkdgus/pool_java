package kr.or.dgit.pool_java.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import org.jdesktop.xswingx.PromptSupport;


import kr.or.dgit.pool_java.dao.TeacherDao;
import kr.or.dgit.pool_java.dto.Teacher;
import kr.or.dgit.pool_java.service.TeacherService;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField IdField;
	private JPasswordField PwField;
	private ImageIcon idimg;
	private ImageIcon pwimg;
	private TeacherDao dao;

	public static void main(String[] args) {
				
		// 룩앤필 변경
		try {
			UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {

			e1.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			private Login frame;

			public void run() {
				try {
					frame = new Login();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Login() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage(System.getProperty("user.dir") + "\\images\\Instapaper icon.png");
		setIconImage(img);
		
		setTitle("수영장");
		
		setResizable(false);
		this.dao = TeacherService.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		
		// 아이디 확인 배경
		JLabel IdCheck = new JLabel("");
		IdCheck.setBounds(548, 234, 46, 47);
		contentPane.add(IdCheck);

		// 비밀번호 확인 배경
		JLabel PwCheck = new JLabel("");
		PwCheck.setBounds(548, 291, 46, 45);
		contentPane.add(PwCheck);

		// 아이디 필드
		IdField = new JTextField();
		IdField.setText("아이디를 입력하세요");
				
		// 아이디 필드 포거스 시 글자 선택
		IdField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				IdField.select(0, IdField.getText().length());
			}
		});

		// 아이디 키 리스너
		IdField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				// 아이디 정규표현식
				Pattern p = Pattern.compile("(^[a-zA-Z0-9]{6,15}$)");
				Matcher m = p.matcher(IdField.getText());

				if (m.find()) {
					idimg = new ImageIcon(System.getProperty("user.dir") + "\\Images\\check(true).png");
					IdCheck.setIcon(idimg);
				} else {
					idimg = new ImageIcon(System.getProperty("user.dir") + "\\Images\\check(false).png");
					IdCheck.setIcon(idimg);
				}
			}

		});
		IdField.setBounds(322, 234, 226, 47);
		contentPane.add(IdField);
		IdField.setColumns(10);
		
		
		// 비밀번호 필드
		PwField = new JPasswordField();
		PromptSupport.setPrompt("비밀번호를 입력해주세요.", PwField);
			
		contentPane.add(PwField);

		// 비밀번호 필드 포거스 시 글자 선택
		PwField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				PwField.select(0, PwField.getText().length());

			}
		});

		// 비밀번호 리스너
		PwField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				// 비밀번호 정규표현식
				Pattern p = Pattern.compile("(^[a-zA-Z0-9!@#$%^&*()]{8,15}$)");
				Matcher m = p.matcher(PwField.getText());

				if (m.find()) {
					pwimg = new ImageIcon(System.getProperty("user.dir") + "\\Images\\check(true).png");
					PwCheck.setIcon(pwimg);
				} else if (!m.find()) {
					pwimg = new ImageIcon(System.getProperty("user.dir") + "\\Images\\check(false).png");
					PwCheck.setIcon(pwimg);
				} else {
					PwField.requestFocus();
					PwField.select(0, IdField.getText().length());
				}
			}

		});

		PwField.setBounds(322, 291, 226, 45);
		contentPane.add(PwField);

		// 로그인 버튼
		JButton btnLogin = new JButton("\uB85C\uADF8\uC778");
		btnLogin.setBounds(366, 379, 140, 34);
		
		// 로그인 버튼 리스너
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idCheck;
				int pwCheck;
				String Id = IdField.getText();
				String Pw = PwField.getText();
				int ConfirmId =-1;
				String ConfirmPw = null;
				Teacher ComfirmTeacher = null;
				List<Teacher> list = dao.selectByAll();
				
				try {
					idCheck = idimg.getDescription().indexOf("false");
					pwCheck = pwimg.getDescription().indexOf("false");
				} catch (NullPointerException err) {
					JOptionPane.showMessageDialog(null, "아이디, 비밀번호를 확인해주세요.");
					IdField.requestFocus();
					return;
				}

				/*if (adminLoginBox.isSelected()) {
					if(Id.equals("RENTADMINISTER")) {
						if(Pw.equals("1234567890")) {
							JOptionPane.showMessageDialog(null, "관리자모드를 시작합니다.");
							AdminMain frame = AdminMain.getInstance();
							frame.getContentPane().add(new AdminMainHome(), BorderLayout.CENTER);
							frame.setVisible(true);
							setVisible(false);
							return;
						}else {
							JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요.");
							return;
						}
					}else {
						JOptionPane.showMessageDialog(null, "관리자 아이디 및 비밀번호를 확인해주세요.");
						return;
					}
				} */
								
				if (idCheck > 0 || pwCheck > 0) {
					JOptionPane.showMessageDialog(null, "아이디, 비밀번호를 확인해주세요.");
				} else {
					for(Teacher u : list) {
						if(Id.equals(u.getTno())) {
							ConfirmId = u.getTno();
							if(Pw.equals(u.getPw())) {
								ConfirmPw = u.getPw();
								ComfirmTeacher = u;
								break;
							}else {
								JOptionPane.showMessageDialog(null, "비밀번호를 다시 확인해주세요.");
								return;
							}			
						}
					}
					
					if(ConfirmId == -1) {
						JOptionPane.showMessageDialog(null, "등록되지 않은 아이디입니다.");	
						return;
					}
					
					JOptionPane.showMessageDialog(null, ComfirmTeacher.getName() + "님 환영합니다!");
				/*	UserMain frame = UserMain.getInstance();
					UserMain.getInstance().UserFrameSetTitle(ComfirmUser);
					UserMainHome userMainHome = new UserMainHome();
					userMainHome.setComfirmUser(ComfirmUser);
					frame.getContentPane().add(userMainHome, BorderLayout.CENTER);
					frame.setVisible(true);
					setVisible(false);*/
				}
			}
		});

		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("회원가입");
		btnRegister.setBounds(332, 346, 97, 23);
		contentPane.add(btnRegister);
		
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			/*	MembershipFrame frame = new MembershipFrame();
				frame.setVisible(true);*/
			}
		});
		
		// 내정보찾기 버튼
		JButton btnFind = new JButton("내정보찾기");
		btnFind.setBounds(441, 346, 97, 23);
		contentPane.add(btnFind);
		btnFind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*FindIdPwFrame findId = new FindIdPwFrame();
				findId.setVisible(true);*/
			}
		});
		
		// 배경화면 
		JLabel backImg = new JLabel("");
		backImg.setBounds(5, 5, 620, 442);
		backImg.setIcon(new ImageIcon("C:\\Users\\DGIT3-10\\Desktop\\DSC03920.JPG"));
		contentPane.add(backImg);
	}
}
