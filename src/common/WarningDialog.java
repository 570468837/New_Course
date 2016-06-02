package common;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WarningDialog extends JFrame{
	public WarningDialog(String warnings){
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		JLabel warningLabel = new JLabel(warnings,JLabel.CENTER);
		warningLabel.setBounds(100, 70, 250, 50);
		warningLabel.setFont(new Font("宋体",Font.BOLD,14));
		
		this.add(warningLabel);
	}
	
	public static void main(String[] args){
		new WarningDialog("用户名或密码不正确！");
	}
}