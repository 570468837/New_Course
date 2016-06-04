package presentation_server;

import integrated_server.Student;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Student_StatisticsPanel extends JPanel {
	JFrame theFrame ;
	JTable table;
	Vector allStudentsData = new Vector();
	
	/**
	 * Create the panel.
	 */
	public Student_StatisticsPanel(JFrame theFrame) {
		this.theFrame = theFrame;
		this.setBounds(127, 0, 607, 535);
		this.setLayout(null);
		this.setVisible(true);
		theFrame.add(this);
		
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		table.setFont(new Font("宋体", Font.PLAIN, 13));
		table.setRowSorter(new TableRowSorter<TableModel>(model));
		table.setFillsViewportHeight(true);
		
		String[] titles = {"学号","姓名","性别","院系"};
		Vector v_titles = new Vector<>();
		for(int i=0;i<titles.length;i++)
			v_titles.add(titles[i]);
		getData();
		model.setDataVector(allStudentsData, v_titles);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(22, 80, 558, 402);
		add(scrollPane);
		
		JLabel label = new JLabel("学生列表");
		label.setFont(new Font("宋体", Font.BOLD, 19));
		label.setBounds(260, 22, 100, 35);
		add(label);
		
		String[] faculties = {"所有院","A","B","C"};
		JComboBox box = new JComboBox(faculties);
		box.setBounds(495,35,80,25);
		add(box);

	}

	private void getData() {
		// TODO Auto-generated method stub
		allStudentsData.clear();
		ArrayList<Student> students = ((StatisticsFrame)theFrame).getStudents();
		if(students.size() == 0)
			return;
		for(Student s:students){
			Vector oneVector = new Vector<>();
			oneVector.add(s.getId());
			oneVector.add(s.getName());
			oneVector.add(s.getSex());
			oneVector.add(s.getMajor());
			allStudentsData.add(oneVector);
		}
		table.repaint();
	}

}
