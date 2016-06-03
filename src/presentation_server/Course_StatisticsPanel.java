package presentation_server;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Course_StatisticsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public Course_StatisticsPanel(JFrame theFrame) {
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
		
		JLabel label = new JLabel("课程列表");
		label.setFont(new Font("宋体", Font.BOLD, 19));
		label.setBounds(260, 22, 100, 35);
		add(label);
		
		String[] faculties = {"所有院","A","B","C"};
		JComboBox box = new JComboBox(faculties);
		box.setBounds(495,35,80,25);
		add(box);
	}

}
