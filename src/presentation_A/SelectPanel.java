package presentation_A;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JButton;


public class SelectPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public SelectPanel(JFrame theFrame) {
		theFrame.getContentPane().add(this);
		this.setBounds(127, 0, 607, 535);
		this.setLayout(null);
		
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
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(260, 22, 100, 35);
		add(label);
		
		JButton confirmButton = new JButton("选课");
		confirmButton.setFont(new Font("宋体", Font.BOLD, 12));
		confirmButton.setBounds(489, 492, 89, 38);
		add(confirmButton);
		
	}
}