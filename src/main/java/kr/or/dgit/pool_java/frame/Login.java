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
import java.awt.Font;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField IdField;
	private JPasswordField PwField;
	private ImageIcon idimg;
	private ImageIcon pwimg;
	private TeacherDao dao;

	public static void main(String[] args) {
				
	/*	// 룩앤필 변경
		try {
			UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {

			e1.printStackTrace();
		}*/

		EventQueue.invokeLater(new Runnable() {
			private Login frame;

			public void run() {
				try {
					frame = new Login();
					frame.setVisible(true);
					frame.setResizable(false);

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
		this.dao = TeacherService.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 479);
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
		IdField.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		IdField.setText("아이디를 입력하세요");
				
		// 아이디 필드 포거스 시 글자 선택
		IdField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				IdField.select(0, IdField.getText().length());
			}
		});

		IdField.setBounds(322, 234, 226, 47);
		contentPane.add(IdField);
		IdField.setColumns(10);
		
		
		// 비밀번호 필드
		PwField = new JPasswordField();
		PromptSupport.setPrompt("비밀번호를 입력해주세요.", PwField);
		PromptSupport.setPrompt("아이디를 입력하세요.", IdField);
			
		contentPane.add(PwField);

		// 비밀번호 필드 포거스 시 글자 선택
		PwField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				PwField.select(0, PwField.getText().length());

			}
		});



		PwField.setBounds(322, 291, 226, 45);
		contentPane.add(PwField);

		// 로그인 버튼
		JButton btnLogin = new JButton("\uB85C\uADF8\uC778");
		btnLogin.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		btnLogin.setBounds(363, 367, 140, 34);
		
		// 로그인 버튼 리스너
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				String id = IdField.getText();
				String pw = PwField.getText();
				int ConfirmId =-1;
				
				List<Teacher> list = TeacherService.getInstance().findId(id);
				
				if(list.size()==0) {
					JOptionPane.showMessageDialog(null, "등록되지 않은 강사입니다.");
					IdField.setText("");
					PwField.setText("");
					IdField.requestFocus();
				}else {
					for(Teacher t : list) {
						if(id.equals(t.getId())) {
							Teacher t2 = new Teacher();
							t2.setId(id);
							t2.setPw(pw);
							Teacher login = TeacherService.getInstance().login(t2);
							if(login == null) {
								JOptionPane.showMessageDialog(null, "비밀번호를 확인해 주세요.");
								PwField.setText("");
								PwField.requestFocus();
							}else if(!t.getTitle().equals("사장")){
								JOptionPane.showMessageDialog(null, t.getName() + " 님 환영합니다!");
								MemberFrame frame = MemberFrame.getInstance();
								frame.setResizable(false);
								frame.getPanel().getSales().setVisible(false);
								frame.getPanel().getSalesTitle().setVisible(false);
								frame.setVisible(true);
								setVisible(false);
							}else {
								JOptionPane.showMessageDialog(null, t.getName() + " 님 환영합니다!");
								MemberFrame frame = MemberFrame.getInstance();
								frame.setVisible(true);
								setVisible(false);
							}
							break;
						}
					}
				}
				
				
				/*	UserMain frame = UserMain.getInstance();
					UserMain.getInstance().UserFrameSetTitle(ComfirmUser);
					UserMainHome userMainHome = new UserMainHome();
					userMainHome.setComfirmUser(ComfirmUser);
					frame.getContentPane().add(userMainHome, BorderLayout.CENTER);
					frame.setVisible(true);
					setVisible(false);*/
				}
			
		});

		contentPane.add(btnLogin);
		
		// 배경화면 
		JLabel backImg = new JLabel("");
		backImg.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\images\\ss.jpg"));
		backImg.setBounds(0, 0, 626, 450);
		contentPane.add(backImg);
	}
}
