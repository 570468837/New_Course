package presentation_server;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;


public class StatisticsFrame extends JFrame {
	private Student_StatisticsPanel studentPanel = new Student_StatisticsPanel(this);
	private Course_StatisticsPanel coursePanel = new Course_StatisticsPanel(this);
	private Select_StatisticsPanel selectPanel = new Select_StatisticsPanel(this);
	/**
	 * Create the frame.
	 */
	public StatisticsFrame() {
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
