package presentation_B;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import B.Model.Student;
import presentation_server.StatisticsFrame;


public class Main {
	public Main(){
		initBeautyEye();
	}
	
	public void initBeautyEye(){
		try{
			BeautyEyeLNFHelper.frameBorderStyle = 
					BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
			BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
		} catch(Exception e){
			System.out.println("包有问题");
			}
		}
	
	public static void main(String[] args){
		Main m = new Main();
		LoginFrame lf = new LoginFrame();
		CourseFrame cf = new CourseFrame(new Student("0201","盛宇", "女", "专业", "2333"));
		StatisticsFrame sf = new StatisticsFrame();
		
	}
}
