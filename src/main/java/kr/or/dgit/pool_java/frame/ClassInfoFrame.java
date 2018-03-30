package kr.or.dgit.pool_java.frame;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import kr.or.dgit.pool_java.dto.Teacher;
import kr.or.dgit.pool_java.content.ClassSchedule;
import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.service.ClassService;
import kr.or.dgit.pool_java.service.TeacherService;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class ClassInfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfCno;
	private List<Teacher> lists;
	private DefaultComboBoxModel<String> cmbModel;

	public ClassInfoFrame(Class cls) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 520, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(12, 10, 474, 290);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("반 번호");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 20, 57, 15);
		panel.add(lblNewLabel);

		tfCno = new JTextField();
		tfCno.setEditable(false);
		tfCno.setBounds(81, 17, 116, 21);
		panel.add(tfCno);
		tfCno.setColumns(10);
		tfCno.setText(cls.getCno() + "");

		JLabel lblNewLabel_1 = new JLabel("시간");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(12, 81, 57, 15);
		panel.add(lblNewLabel_1);

		JComboBox cmbTime = new JComboBox();
		cmbTime.setBounds(81, 78, 116, 21);
		cmbTime.setModel(new DefaultComboBoxModel(
				new String[] { "06시 00분", "07시 00분", "08시 00분", "09시 00분", "10시 00분", "11시 00분", "13시 00분", "14시 00분",
						"15시 00분", "16시 00분", "17시 00분", "18시 00분", "19시 00분", "20시 00분", "21시 00분" }));
		panel.add(cmbTime);
		cmbTime.setSelectedItem(cls.getTime());
		JLabel lblNewLabel_2 = new JLabel("레벨");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(12, 142, 57, 15);
		panel.add(lblNewLabel_2);

		JComboBox cmbLevel = new JComboBox();
		cmbLevel.setBounds(81, 139, 116, 21);
		cmbLevel.setModel(
				new DefaultComboBoxModel(new String[] { "초급", "중급", "상급", "고급", "연수", "노인반", "어린이반", "방학특강" }));
		panel.add(cmbLevel);
		cmbLevel.setSelectedItem(cls.getLevel());

		JLabel lblNewLabel_3 = new JLabel("강사명");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(246, 20, 57, 15);
		panel.add(lblNewLabel_3);
		cmbModel = new DefaultComboBoxModel<String>(getDate());
		JComboBox<String> cmbTName = new JComboBox<String> (cmbModel);
		cmbTName.setBounds(308, 17, 116, 21);
		panel.add(cmbTName);
		Teacher t = TeacherService.getInstance().selectByNo(cls.getTno());
		cmbTName.setSelectedItem(t.getName());
		JLabel lblNewLabel_4 = new JLabel("총 인원");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(246, 81, 57, 15);
		panel.add(lblNewLabel_4);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(308, 74, 116, 22);
		spinner.setValue(cls.getClassmate());
		panel.add(spinner);

		JLabel lblNewLabel_5 = new JLabel("재강습여부");
		lblNewLabel_5.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(246, 144, 81, 15);

		panel.add(lblNewLabel_5);

		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(335, 139, 29, 25);
		panel.add(chckbxNewCheckBox);
		if (cls.isReclass()) {
			chckbxNewCheckBox.setSelected(true);
		}
		JButton button = new JButton("확인");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		button.setBounds(95, 226, 110, 34);
		panel.add(button);

		JButton button_1 = new JButton("수정");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Class cls1 = new Class();
				cls1.setClassmate((int)spinner.getValue());
				cls1.setCno(cls.getCno());
				cls1.setLevel((String)cmbLevel.getSelectedItem());
				cls1.setReclass(chckbxNewCheckBox.isSelected());
				String teachName = (String)cmbModel.getSelectedItem();
				int tno = lists.get(cmbModel.getIndexOf(teachName)).getTno();
				cls1.setTno(tno);
				cls1.setTime((String)cmbLevel.getSelectedItem());
				int res = ClassService.getInstance().updateClassInfo(cls);
				if(res >= 0) {
					JOptionPane.showMessageDialog(null,"수정되었습니다.");
					ClassSchedule.getInstance().addJTableList();
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null,"수정에 실패하였습니다.");
				}
			}
		});
		button_1.setBounds(246, 226, 110, 34);
		panel.add(button_1);
	}

	private Vector<String> getDate() {
		Vector<String> vt = new Vector<>();
		lists = TeacherService.getInstance().selectByAll();
		for (Teacher t : lists) {
			vt.add(t.getName());
		}
		return vt;
	}
}
