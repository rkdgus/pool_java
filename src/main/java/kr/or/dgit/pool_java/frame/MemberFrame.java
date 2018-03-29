package kr.or.dgit.pool_java.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import kr.or.dgit.pool_java.content.AdminSidebar;
import kr.or.dgit.pool_java.content.MemberContent;

public class MemberFrame extends JFrame {

	private JPanel contentPane;
	private JPanel contentPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		contentPanel= new JPanel();
		contentPanel.setBounds(253, 10, 900, 550);
		
		contentPane.add(panel);
		contentPane.add(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		panel.getMember().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				/*super.mouseClicked(e);*/
				contentPanel.removeAll();
				MemberContent memberContent = new MemberContent();
				contentPanel.add(memberContent,BorderLayout.CENTER);
				revalidate();
				repaint();
			}
			
			
		});
		
		
	}
	
	private void contentCall(Object object) {
		
	}
}
