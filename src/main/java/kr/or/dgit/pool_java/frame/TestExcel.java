package kr.or.dgit.pool_java.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestExcel extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	String[] column = {"aa","bb"};
	String FilePath = "c:\\data.xls";
	String SheetName = "test";
	JTable tb;
	String[][] data = new String[4][2];

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestExcel frame = new TestExcel();
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
	public TestExcel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 862, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("추가");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] getColumn = new String[tb.getColumnCount()];
				for(int i = 0 ; i < tb.getColumnCount(); i ++) {
					getColumn[i] = tb.getColumnName(i);
				}
				String[][] getData = new String[tb.getRowCount()][tb.getColumnCount()];
				
				for(int i = 0; i < tb.getRowCount(); i ++) {
					for(int j = 0; j < tb.getColumnCount(); j++) {
						getData[i][j] = (String)tb.getValueAt(i,j);
					}
				}
				
				try {
					//WritableWorkbook
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
	}
	
	public void Excel() {
		for(int i=0; i < 4; i++) {
			for(int j = 0; j <3; j++) {
				data[i][j-1] = i * j+"";
			}
		}
		
		tb = new JTable(data,column);
		scrollPane.setViewportView(tb);
	}
}
