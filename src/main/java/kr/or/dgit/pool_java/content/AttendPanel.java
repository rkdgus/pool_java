package kr.or.dgit.pool_java.content;

import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AttendPanel extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public AttendPanel() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(95, 50, 785, 423);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Date date = new Date();
		
		
	}
}
