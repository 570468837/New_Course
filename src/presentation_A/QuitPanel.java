package presentation_A;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import common.WarningDialog;

import A.businesslogic.CourseBL;
import A.businesslogic.CourseSelectionBL;
import A.businesslogicservice.CourseBLService;
import A.po.CoursePO;

public class QuitPanel extends JPanel {
	JFrame theFrame;
	JTable table ;
	Vector allSelectedCoursesData = new Vector<>();
	
	CourseBLService courseBL = new CourseBL();
	CourseSelectionBL selectBL = new CourseSelectionBL();
	/**
	 * Create the panel.
	 */
	public QuitPanel(JFrame theFrame) {
		this.theFrame = theFrame;
		theFrame.add(this);
		this.setBounds(127, 0, 607, 535);
		this.setLayout(null);
		setVisible(false);
		
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		table.setFont(new Font("宋体", Font.PLAIN, 13));
		table.setRowSorter(new TableRowSorter<TableModel>(model));
		table.setFillsViewportHeight(true);
		
		String[] titles = {"课程编号","课程名称","学分","授课老师","授课地点"};
		Vector<String> v_title = new Vector();
		for(int i=0;i<titles.length;i++)
			v_title.add(titles[i]);
		getData();
		model.setDataVector(allSelectedCoursesData, v_title);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(22, 80, 558, 402);
		add(scrollPane);
		
		JLabel label = new JLabel("已选课程列表");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(235, 22, 150, 35);
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
		
		JButton confirmButton = new JButton("退课");
		confirmButton.setFont(new Font("宋体", Font.BOLD, 12));
		confirmButton.setBounds(489, 492, 89, 38);
		add(confirmButton);
		
		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex >= allSelectedCoursesData.size())
					return;
				CoursePO coursePO = courseBL.showCourseById((String)table.getValueAt(rowIndex, 0));
				try{
				if(coursePO != null){
					boolean ifSuccess=false;
					ifSuccess = selectBL.courseQuit(((CourseFrame)theFrame).getStudentPO(), coursePO);
					if(ifSuccess)
						new WarningDialog("退课成功！");
					else
						new WarningDialog("退课失败，可能已经退选过！");
					}
				else
					new WarningDialog("没有这门课！");
					//刷新表格
					getData();
					
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				}
			});
	}
	private void getData() {
		// TODO Auto-generated method stub
		allSelectedCoursesData.clear();
		ArrayList<CoursePO> courses = new ArrayList<>();
		try {
			courses = selectBL.showSelectedCourse(((CourseFrame)theFrame).getStudentPO());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(courses.size()== 0)
			return;
		for(int i=0;i<courses.size();i++){
			CoursePO oneCourse = courses.get(i);
			Vector oneVector = new Vector<>();
			oneVector.add(oneCourse.getCno());
			oneVector.add(oneCourse.getCnm());
			oneVector.add(oneCourse.getCpt());
			oneVector.add(oneCourse.getTec());
			oneVector.add(oneCourse.getPla());
			allSelectedCoursesData.add(oneVector);
		}
		table.repaint();
	}

}
