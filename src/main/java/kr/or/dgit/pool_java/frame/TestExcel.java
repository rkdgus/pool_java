package kr.or.dgit.pool_java.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class TestExcel extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	String[] column = {"aa","bb"};
	String FilePath2 = "D:/excelData";
	String FilePath = "D:/excelData/data2.xls";
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
		Excel();
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
						System.out.println((String)tb.getValueAt(i,j));
						getData[i][j] = (String)tb.getValueAt(i,j);
					}
				}
				
				try {
					File file = new File(FilePath2);
					if(!file.exists()) {
						System.out.println();
						file.mkdir();
					}
					File file1 = new File(FilePath);
					
					if(!file1.exists()) {
						file1.createNewFile();
					}
					WritableWorkbook workbook = Workbook.createWorkbook(file1);
					WritableSheet sheet = workbook.createSheet(SheetName,0);
					
					WritableCellFormat format_column = new WritableCellFormat();
					WritableCellFormat format_data = new WritableCellFormat();
					WritableCellFormat format_integer1 = new WritableCellFormat(NumberFormats.INTEGER);
					format_column.setBackground(jxl.format.Colour.GRAY_25);
					format_column.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
					for(int i =0; i < getColumn.length; i++) {
						Label label = new Label(i,0,column[i],format_column);
						sheet.addCell(label);
					}
					
					for(int i = 0; i<getData.length; i++) {
						for(int j = 0; j < getData[i].length; j++) {
							Label label = new Label(j,i+1,getData[i][j],format_data);
							sheet.addCell(label);
						}
					}
					workbook.write();
					workbook.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
	}
	public void Excel() {
		for(int i=0; i < 4; i++) {
			for(int j = 1; j <3; j++) {
				data[i][j-1] = i * j+"";
			}
		}
		tb = new JTable(data,column);
		scrollPane.setViewportView(tb);
	}
}
