package presentation_server;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Select_StatisticsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public Select_StatisticsPanel(JFrame theFrame) {
		this.setBounds(127, 0, 607, 535);
		this.setLayout(null);
		this.setVisible(false);
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
		
		JLabel label = new JLabel("选课列表");
		label.setFont(new Font("宋体", Font.BOLD, 19));
		label.setBounds(260, 22, 100, 35);
		add(label);
		}
	}

