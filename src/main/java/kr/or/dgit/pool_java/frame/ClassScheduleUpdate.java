package kr.or.dgit.pool_java.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import kr.or.dgit.pool_java.content.ClassSchedule;
import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.dto.Teacher;
import kr.or.dgit.pool_java.service.ClassService;
import kr.or.dgit.pool_java.service.TeacherService;

@SuppressWarnings("serial")
public class ClassScheduleUpdate extends JFrame {
	private JPanel contentPane;
	private DefaultComboBoxModel<String> cmbModel;
	private List<Teacher> lists;
	public ClassScheduleUpdate(String name, int classmate,int cno) {
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
		
		cmbModel = new DefaultComboBoxModel<String>(getDate());
		JComboBox<String> comboBox = new JComboBox<String>(cmbModel);
		cmbModel.setSelectedItem(name);
		comboBox.setBounds(130, 40, 116, 21);
		panel.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("총 인원 : ");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1.setBounds(64, 77, 75, 15);
		panel.add(lblNewLabel_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(130, 71, 116, 22);
		spinner.setValue(classmate);
		panel.add(spinner);
		
		JButton btnUpdate = new JButton("수정");
		btnUpdate.setBounds(183, 136, 97, 23);
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//1. 수정
				String teachName = (String)cmbModel.getSelectedItem();
				int tno = lists.get(cmbModel.getIndexOf(teachName)).getTno();
				System.out.println();
				int classmate = (int)spinner.getValue();
				Class cls = new Class();
				cls.setClassmate(classmate);
				cls.setTno(tno);
				cls.setCno(cno);
				int res = ClassService.getInstance().updateClass(cls);
				//2. 닫으면서 리스트 갱신
				if(res >= 0) {
					JOptionPane.showMessageDialog(null, "수정되었습니다.");
					ClassSchedule.getInstance().addJTableList();
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "수정이 실패하였습니다.");
				}
				
				
			}
		});
		panel.add(btnUpdate);
		
		JButton btnCancel = new JButton("취소");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(59, 136, 97, 23);
		panel.add(btnCancel);
	}
	
	private Vector<String> getDate(){
		Vector<String> vt = new Vector<>();
		lists = TeacherService.getInstance().selectByAll();
		for(Teacher t : lists) {
			vt.add(t.getName());
		}
		return vt;
	}
}
