package kr.or.dgit.pool_java.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import kr.or.dgit.pool_java.content.AdminSidebar;
import kr.or.dgit.pool_java.content.ClassSchedule;
import kr.or.dgit.pool_java.content.MemberContent;
import kr.or.dgit.pool_java.content.SalesPanel;
import kr.or.dgit.pool_java.content.StancePanel;
import kr.or.dgit.pool_java.content.TeacherContent;

public class MemberFrame extends JFrame {

	private JPanel contentPane;
	private JPanel content;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		/*try {
			UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberFrame frame = new MemberFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MemberFrame() {
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
		
		panel.getMember().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				MemberContent memberContent = new MemberContent();
				contentCall(memberContent);
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
		
		panel.getTeacher().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				TeacherContent teacherContent = new TeacherContent();
				contentCall(teacherContent);
			}
			
		});
		
	}
	
	private void contentCall(JPanel object) {
		content.removeAll();
		content.add(object,BorderLayout.CENTER);
		revalidate();
		repaint();
	}
}
