package kr.or.dgit.pool_java.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.dto.Teacher;
import kr.or.dgit.pool_java.service.ClassService;
import kr.or.dgit.pool_java.service.TeacherService;
import javax.swing.JSpinner;

public class AddClassFrame extends JFrame {

	private JPanel contentPane;
	private DefaultComboBoxModel<String> cmbModel;
	private List<Teacher> lists;
	private JComboBox cbmTime;
	private JComboBox<String> cbms_day;
	private JComboBox<String> cbmTno;
	private JComboBox cbmLevel;
	private JSpinner spinner;

	public AddClassFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 357, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "신규 강의 개설", TitledBorder.LEADING, TitledBorder.TOP, new Font(null, Font.BOLD, 18), new Color(0, 0, 0)));
		
		panel.setBounds(28, 10, 285, 532);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("시간");
		lblNewLabel.setBounds(6, 57, 136, 40);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("등급");
		label.setBounds(6, 152, 136, 40);
		panel.add(label);
		
		cbmLevel = new JComboBox();
		cbmLevel.setModel(new DefaultComboBoxModel(new String[] {"초급", "중급", "상급", "고급", "연수", "노인반", "어린이반", "방학특강"}));
		cbmLevel.setBounds(142, 152, 136, 40);
		panel.add(cbmLevel);
		
		JLabel label_1 = new JLabel("강사번호");
		label_1.setBounds(6, 282, 136, 40);
		panel.add(label_1);
		cmbModel = new DefaultComboBoxModel<String>(getDate());
		cbmTno = new JComboBox<String>(cmbModel);
		cbmTno.setBounds(142, 282, 136, 40);
		panel.add(cbmTno);
		
		JLabel label_2 = new JLabel("개설일");
		label_2.setBounds(6, 351, 136, 40);
		panel.add(label_2);
		
		cbms_day = new JComboBox<String>();
		Calendar cal = Calendar.getInstance();
		String month = cal.get(Calendar.YEAR)+"년 "+ (cal.get(Calendar.MONTH)+1)+"월";
		String month2 = cal.get(Calendar.YEAR)+"년 "+ (cal.get(Calendar.MONTH)+2)+"월";
		cbms_day.setBounds(142, 351, 136, 40);
		cbms_day.setModel(new DefaultComboBoxModel(new String[] {month,month2}));
		panel.add(cbms_day);
		
		JButton btnAddClass = new JButton("추가");
		btnAddClass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Class cls = new Class();
				
				cls.setTime((String)cbmTime.getSelectedItem());
				cls.setClassmate((int)spinner.getValue());
				cls.setLevel((String)cbmLevel.getSelectedItem());
				
				if(cbms_day.getSelectedIndex() == 0) {
					Date date = new Date();
					cls.setS_day(date);
				}else {
					Date date = new Date();
					date.setMonth(date.getMonth()+1);
					date.setDate(1);
					cls.setS_day(date);
				}
				int tno = lists.get(cbmTno.getSelectedIndex()).getTno();
				cls.setTno(tno);
				
				int res = ClassService.getInstance().insertClass(cls);
				if(res >=0) {
					JOptionPane.showMessageDialog(null,"추가하였습니다.");
				}else {
					JOptionPane.showMessageDialog(null,"실패하였습니다.");
				}
				
			}
		});
		btnAddClass.setBounds(142, 443, 136, 33);
		panel.add(btnAddClass);
		
		JButton btnCancel = new JButton("취소");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(6, 443, 136, 33);
		panel.add(btnCancel);
		
		cbmTime = new JComboBox();
		cbmTime.setModel(new DefaultComboBoxModel(new String[] {"06시 00분", "07시 00분", "08시 00분", "09시 00분", "10시 00분", "11시 00분", "13시 00분", "14시 00분", "15시 00분", "16시 00분", "17시 00분", "18시 00분", "19시 00분", "20시 00분", "21시 00분"}));
		cbmTime.setBounds(142, 57, 136, 40);
		panel.add(cbmTime);
		
		JLabel lblNewLabel_1 = new JLabel("총 인원");
		lblNewLabel_1.setBounds(6, 226, 57, 15);
		panel.add(lblNewLabel_1);
		
		spinner = new JSpinner();
		spinner.setBounds(142, 212, 136, 40);
		panel.add(spinner);
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
