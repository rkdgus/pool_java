package kr.or.dgit.pool_java.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ClassScheduleUpdate extends JFrame {
	private JPanel contentPane;
	public ClassScheduleUpdate() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 375, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(17, 11, 323, 208);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("강사명 : ");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setBounds(64, 35, 75, 26);
		panel.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(130, 40, 116, 21);
		panel.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("총 인원 : ");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1.setBounds(64, 77, 75, 15);
		panel.add(lblNewLabel_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(130, 71, 116, 22);
		panel.add(spinner);
		
		JButton btnUpdate = new JButton("수정");
		btnUpdate.setBounds(183, 136, 97, 23);
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		panel.add(btnUpdate);
		
		JButton btnCancel = new JButton("취소");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCancel.setBounds(59, 136, 97, 23);
		panel.add(btnCancel);
	}
}
