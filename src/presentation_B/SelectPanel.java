package presentation_B;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
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

import common.WarningDialog;
import B.BusinessLogicService.CourseBLService;
import B.BusinessLogicService.CourseBLServiceImpl;
import B.BusinessLogicService.StudentBLService;
import B.BusinessLogicService.StudentBLServiceImpl;
import B.BusinessLogicService.StudentCourseBLService;
import B.BusinessLogicService.StudentCourseBLServiceImpl;
import B.Model.Course;



public class SelectPanel extends JPanel {
	Vector allCoursesData = new Vector<>();
	StudentBLService studentBL = new StudentBLServiceImpl();
	CourseBLService courseBL = new CourseBLServiceImpl();
	StudentCourseBLService selectBL = new StudentCourseBLServiceImpl();
	
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
		table.setFillsViewportHeight(true);
		
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
		
		JButton refreshButton = new JButton("刷新");
		refreshButton.setFont(new Font("宋体", Font.BOLD, 12));
		refreshButton.setBounds(370, 492, 89, 38);
		add(refreshButton);
		
		refreshButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				getData();
			}
		});
		
		JButton confirmButton = new JButton("选课");
		confirmButton.setFont(new Font("宋体", Font.BOLD, 12));
		confirmButton.setBounds(489, 492, 89, 38);
		add(confirmButton);
		
		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex >= allCoursesData.size())
					return;
				Course coursePO = courseBL.getCourseById(((String)table.getValueAt(rowIndex, 0)));
				//如果是本院系的课
				if(coursePO != null){
					boolean ifSuccess = studentBL.selectCourse(((CourseFrame)theFrame).getStudentPO(), 
							coursePO);
					if(ifSuccess)
						new WarningDialog("选课成功！");
					else
						new WarningDialog("选课失败，可能已选该课！");
				}
				else
					new WarningDialog("没有这门课！");
				//刷新表格
				getData();
			}
		});
		
	}

	private void getData() {
		// TODO Auto-generated method stub
		allCoursesData.clear();
		ArrayList<Course> courses= courseBL.getAllLocalCourse() ;
		for(int i=0;i<courses.size();i++){
			Course oneCourse = courses.get(i);
			Vector oneVector = new Vector<>();
			oneVector.add(oneCourse.getId());
			oneVector.add(oneCourse.getName());
			oneVector.add(oneCourse.getCredit());
			oneVector.add(oneCourse.getTeacher());
			oneVector.add(oneCourse.getClassRoom());
			allCoursesData.add(oneVector);
		}
	}
		
}
