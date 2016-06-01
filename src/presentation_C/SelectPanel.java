package presentation_C;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JButton;

import C.businesslogic.CourseBL;
import C.businesslogic.CourseSelectionBL;
import C.businesslogic.CourseSelectionController;
import C.businesslogicservice.CourseBLService;
import C.businesslogicservice.CourseSelectionBLService;
import C.po.CoursePO;
import C.po.StudentPO;
import C.vo.AccountVO;
import C.vo.CourseVO;


public class SelectPanel extends JPanel {
	Vector allCoursesData = new Vector<>();
	
	CourseBLService courseBL = new CourseBL();
	CourseSelectionBLService selectBL = new CourseSelectionBL();
	
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
		//数据
		String[] titles = {"课程编号","课程名称","学分","授课老师","授课地点"};
		Vector<String> v_title = new Vector();
		for(int i=0;i<titles.length;i++)
			v_title.add(titles[i]);
		getData();
		model.setDataVector(allCoursesData, v_title);
		
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
		
		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowIndex = table.getSelectedRow();
				CoursePO coursePO = courseBL.showCourseById((String)table.getValueAt(rowIndex, 0));
				//如果是本院系的课
				if(coursePO != null){
					boolean ifSuccess = selectCourse(((CourseFrame)theFrame).getStudentPO(), coursePO);
					
				}
				
			}
		});
	}
	
	public void getData(){
		CoursePO[] courses = courseBL.showAllCourse();
		for(int i=0;i<courses.length;i++){
			CoursePO oneCourse = courses[i];
			Vector oneVector = new Vector<>();
			oneVector.add(oneCourse.getCno());
			oneVector.add(oneCourse.getCnm());
			oneVector.add(oneCourse.getCpt());
			oneVector.add(oneCourse.getTec());
			oneVector.add(oneCourse.getPla());
			allCoursesData.add(oneVector);
		}
	}
	
	public boolean selectCourse(StudentPO studentPO, CoursePO coursePO){
		return false;
	}
}
