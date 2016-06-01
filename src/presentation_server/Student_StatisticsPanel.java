package presentation_server;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Student_StatisticsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public Student_StatisticsPanel(JFrame theFrame) {
		this.setBounds(127, 0, 607, 535);
		this.setLayout(null);
		this.setVisible(true);
		theFrame.add(this);
		
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		table.setFont(new Font("宋体", Font.PLAIN, 13));
		table.setRowSorter(new TableRowSorter<TableModel>(model));
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(22, 80, 558, 402);
		add(scrollPane);
		
		JLabel label = new JLabel("学生列表");
		label.setFont(new Font("宋体", Font.BOLD, 19));
		label.setBounds(260, 22, 100, 35);
		add(label);

	}

}