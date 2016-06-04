package presentation_server;

import integrated_server.*;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import common.FileInformation;
import B.B_Server.B_Interface;


public class StatisticsFrame extends JFrame {
	private Student_StatisticsPanel studentPanel ; 
	private Course_StatisticsPanel coursePanel;
	private Select_StatisticsPanel selectPanel;
	
	ArrayList<Student> students = new ArrayList<>();
	ArrayList<Course> courses = new ArrayList<>();
	ArrayList<Selection> selections = new ArrayList<>();
	
	B_Interface BClient = null;
	/**
	 * Create the frame.
	 */
	public StatisticsFrame() {
		startRMI();
		studentPanel= new Student_StatisticsPanel(this);
		coursePanel = new Course_StatisticsPanel(this);
		selectPanel = new Select_StatisticsPanel(this);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(760, 589);
		setLocationRelativeTo(null);
		setLayout(null);
		
		JLabel welcomeLabel = new JLabel("<html><body><p align=\"center\">南京大学教务系统</p><br>"+
				"</body></html>");
				welcomeLabel.setFont(new Font("宋体", Font.BOLD, 14));
				welcomeLabel.setBounds(33, 23, 72, 50);
				add(welcomeLabel);
		
		JButton studentButton = new JButton("学生统计");
		studentButton.setFont(new Font("宋体", Font.BOLD, 12));
		studentButton.setBounds(26, 83, 91, 39);
		add(studentButton);
		
		JButton courseButton = new JButton("课程统计");
		courseButton.setFont(new Font("宋体", Font.BOLD, 12));
		courseButton.setBounds(26, 153, 91, 39);
		add(courseButton);
		
		JButton selectButton = new JButton("选课统计");
		selectButton.setFont(new Font("宋体", Font.BOLD, 12));
		selectButton.setBounds(26, 223, 91, 39);
		add(selectButton);
		
		studentButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				studentPanel.setVisible(true);
				coursePanel.setVisible(false);
				selectPanel.setVisible(false);
				
			}
		});
		
		courseButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				studentPanel.setVisible(false);
				coursePanel.setVisible(true);
				selectPanel.setVisible(false);

			}
		});
		
		selectButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				studentPanel.setVisible(false);
				coursePanel.setVisible(false);
				selectPanel.setVisible(true);
			}
		});
	
		this.repaint();
	}

	private void startRMI() {
		// TODO Auto-generated method stub
		try {
			BClient = (B_Interface)Naming.lookup("rmi://localhost:8882/B_Interface");
			refreshStudents();
			refreshCourses();
			refreshSelections();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void refreshStudents(){
		String student_parentFolder = "IServer/statistics/student/";
		
		FileInformation student_file = BClient.getAllStudents();
		printXML(student_parentFolder, student_file);
		students.clear();
		students.addAll(XML_Helper.decodeStudents
				(student_parentFolder+student_file.getName()));
	}
	
	public void refreshCourses(){
		String course_parentFolder = "IServer/statistics/course/";
		
		FileInformation B_course_file = BClient.getAllCourses();
		printXML(course_parentFolder, B_course_file);
		courses.clear();
		courses.addAll(XML_Helper.decodeCourses
				(course_parentFolder+B_course_file.getName()));
	}
	
	public void refreshSelections(){
		String selection_parentFolder = "IServer/statistics/selection/";

		FileInformation selection_file = BClient.getAllSelections();
		
		printXML(selection_parentFolder, selection_file);
		selections.clear();
		selections.addAll(XML_Helper.decodeSelections
				(selection_parentFolder+selection_file.getName()));
	}
	
	public void printXML(String parentPath, FileInformation fileinfo){
		 BufferedOutputStream output;
		try {
			output = new BufferedOutputStream(new FileOutputStream(new File(parentPath+fileinfo.getName())));
			output.write(fileinfo.getContent());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public B_Interface getBClient() {
		return BClient;
	}
	

	public ArrayList<Student> getStudents() {
		return students;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public ArrayList<Selection> getSelections() {
		return selections;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
			BeautyEyeLNFHelper.frameBorderStyle = 
					BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
			BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
		} catch(Exception e){
			System.out.println("包有问题");
			}
		new StatisticsFrame();
	}
}
