package kr.or.dgit.pool_java.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.pool_java.content.AdminSidebar;
import kr.or.dgit.pool_java.content.AttendPanel;
import kr.or.dgit.pool_java.content.ClassSchedule;
import kr.or.dgit.pool_java.content.MemberContent;
import kr.or.dgit.pool_java.content.NewClassSchedule;
import kr.or.dgit.pool_java.content.SalesPanel;
import kr.or.dgit.pool_java.content.StancePanel;
import kr.or.dgit.pool_java.content.TeacherContent;
import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.service.ClassService;


public class MemberFrame extends JFrame {
	private static final MemberFrame instance = new MemberFrame();
	
	public static MemberFrame getInstance() {
		return instance;
	}

	private JPanel contentPane;
	private JPanel content;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberFrame frame = MemberFrame.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private MemberFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1170, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		AdminSidebar panel = new AdminSidebar();
		panel.setBounds(12, 10, 236, 551);
		content= new JPanel();
		content.setBounds(253, 10, 900, 550);
		
		contentPane.add(panel);
		contentPane.add(content);
		content.setLayout(new BorderLayout(0, 0));
		Calendar cal = Calendar.getInstance();
		if(cal.get(Calendar.DATE) >= 20) {
			updateReclass();
		}
		panel.getMember().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MemberContent memberContent = new MemberContent();
				contentCall(memberContent);
				memberContent.getMno().requestFocus();
				memberContent.getMno().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						memberContent.getMno().setEnabled(false);
					}
				});
			}
		});
		
		panel.getSchedule().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClassSchedule classSchedule = ClassSchedule.getInstance();
				contentCall(classSchedule);
			}
		});
		
		panel.getCome().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StancePanel stancePanel = new StancePanel();
				contentCall(stancePanel);
			}
		});
		
		panel.getSales().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SalesPanel salesPanel = new SalesPanel();
				contentCall(salesPanel);
			}
		});
		panel.getClassqna().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NewClassSchedule newClassShchedule = NewClassSchedule.getInstance();
				contentCall(newClassShchedule);
			}
		});
		
		panel.getTeacher().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TeacherContent teacherContent = new TeacherContent();
				contentCall(teacherContent);
			}
		});
		
		panel.getAttend().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AttendPanel attendPanel;
				try {
					attendPanel = new AttendPanel();
					contentCall(attendPanel);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});
		
	}
	public JPanel getContent() {
		return content;
	}

	public void contentCall(JPanel object) {
		content.removeAll();
		content.add(object,BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	private void updateReclass() {
		List<Class> lists = ClassService.getInstance().selectByreclass(true);
		for(Class cls : lists) {
			Date date = new Date();
			date.setMonth(date.getMonth()+1);
			date.setDate(1);
			cls.setS_day(date);
			ClassService.getInstance().updateReclass(cls);
		}
	}
}
