package presentation_C;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;


public class LoginFrame extends JFrame {
	
	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;
	private JLabel titlelabel;
	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(510, 357);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titlelabel = new JLabel("南京大学教务系统");
		titlelabel.setFont(new Font("宋体", Font.BOLD, 20));
		titlelabel.setBounds(157, 29, 227, 46);
		contentPane.add(titlelabel);
		
		JLabel userLabel = new JLabel("用户名：");
		userLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		userLabel.setBounds(132, 93, 64, 35);
		contentPane.add(userLabel);
		
		JLabel passwordLabel = new JLabel("密 码：");
		passwordLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		passwordLabel.setBounds(132, 170, 64, 22);
		contentPane.add(passwordLabel);
		
		userField = new JTextField();
		userField.setBounds(214, 97, 144, 29);
		contentPane.add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(214, 168, 144, 29);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JButton loginButton = new JButton("登陆");
		loginButton.setFont(new Font("宋体", Font.PLAIN, 15));
		loginButton.setBounds(200, 234, 98, 35);
		contentPane.add(loginButton);
		
		contentPane.repaint();
		repaint();
	}
	public static void main(String[] args){
		new LoginFrame();
	}
}
