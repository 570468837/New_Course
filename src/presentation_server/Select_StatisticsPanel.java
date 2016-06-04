package presentation_server;

import integrated_server.*;

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

import common.Common;

public class Select_StatisticsPanel extends JPanel {
	JFrame theFrame ;
	JTable table;
	Vector allSelectionsData = new Vector();
	ArrayList<Course> allCourses = new ArrayList<>();
	ArrayList<Student> allStudents = new ArrayList<>();
	/**
	 * Create the panel.
	 */
	public Select_StatisticsPanel(JFrame theFrame) {
		this.theFrame = theFrame;
		this.setBounds(127, 0, 607, 535);
		this.setLayout(null);
		this.setVisible(false);
		theFrame.add(this);
		
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		table.setFont(new Font("宋体", Font.PLAIN, 13));
		table.setRowSorter(new TableRowSorter<TableModel>(model));
		table.setFillsViewportHeight(true);
		
		String[] titles = {"学生学号","学生姓名","学生院系","成绩","课程编号","课程名称","开设院系"};
		Vector v_titles = new Vector<>();
		for(int i=0;i<titles.length;i++)
			v_titles.add(titles[i]);
		getData();
		model.setDataVector(allSelectionsData, v_titles);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(22, 80, 558, 402);
		add(scrollPane);
		
		JLabel label = new JLabel("选课列表");
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
		allSelectionsData.clear();
		// TODO Auto-generated method stub
		ArrayList<Selection> selections = ((StatisticsFrame)theFrame).getSelections();
		if(selections.size() == 0)
			return;
		for(Selection s: selections){
			Student student = getStudentById(s.getSid());
			Course course = getCourseById(s.getCid());
			Vector oneVector = new Vector<>();
			oneVector.add(s.getSid());
			oneVector.add(student.getName());
			oneVector.add(student.getMajor());
			oneVector.add(s.getScore());
			oneVector.add(course.getId());
			oneVector.add(course.getName());
			oneVector.add(Common.getFacultyById(course.getId()).toString());
			allSelectionsData.add(oneVector);
		}
		table.repaint();
	}
	
	private Student getStudentById(String id){
		for(Student s: allStudents){
			if(s.getId().equals(id))
				return s;
		}
		return null;
	}
	
	private Course getCourseById(String id){
		for(Course c: allCourses){
			if(c.getId().equals(id))
				return c;
		}
		return null;
	}
	
}

