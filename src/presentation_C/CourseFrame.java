package presentation_C;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import C.po.StudentPO;

import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CourseFrame extends JFrame {
	private SelectPanel selectPanel ;
	private QuitPanel quitPanel ;
	StudentPO studentPO;
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
		CourseFrame frame = new CourseFrame(new StudentPO("01001", "盛宇", "女", "未知", "hhh"));
	}

	/**
	 * Create the frame.
	 */
	public CourseFrame(StudentPO studentPO) {
		this.studentPO = studentPO;
		selectPanel = new SelectPanel(this);
		quitPanel = new QuitPanel(this);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(760, 589);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel welcomeLabel = new JLabel("<html><body><p align=\"center\">Welcome</p><br>"+
		"<p align=\"center\">"+studentPO.getSnm()+"</p></body></html>");
		welcomeLabel.setFont(new Font("宋体", Font.BOLD, 14));
		welcomeLabel.setBounds(39, 10, 81, 50);
		getContentPane().add(welcomeLabel);
		
		JButton selectButton = new JButton("学期选课");
		selectButton.setFont(new Font("宋体", Font.BOLD, 12));
		selectButton.setBounds(26, 83, 91, 39);
		getContentPane().add(selectButton);
		
		JButton quitButton = new JButton("课程退选");
		quitButton.setFont(new Font("宋体", Font.BOLD, 12));
		quitButton.setBounds(26, 153, 91, 39);
		getContentPane().add(quitButton);
		
		selectButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectPanel.setVisible(true);
	            quitPanel.setVisible(false);
			}
		});
		
		quitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectPanel.setVisible(false);
				quitPanel.setVisible(true);
			}
		});
		
		this.repaint();
	}
	
	public StudentPO getStudentPO(){
		return this.studentPO;
	}
}
