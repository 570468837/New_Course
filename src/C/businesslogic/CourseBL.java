package C.businesslogic;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import C.businesslogicservice.CourseBLService;
import C.dataservice.CourseDataService;
import C.dataservice.StudentDataService;
import C.po.CoursePO;
/*
 * 
 * 获取所有课程，返回的是CoursePO[]
 * 
 * */

public class CourseBL implements CourseBLService{
	public CoursePO[] showAllCourse() {
		// TODO Auto-generated method stub
		CourseDataService cd;
		try {
			cd=(CourseDataService) Naming.lookup("rmi://127.0.0.1:2017/Server");
			return cd.selectAll();
			
			
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
		return null;
	}
/*
 * 根据课程编号获取课程对象
 * */
	public CoursePO showCourseById(String courseId) {
		// TODO Auto-generated method stub
		CourseDataService cd;
		try {
			cd=(CourseDataService) Naming.lookup("rmi://127.0.0.1:2017/Server");
			return cd.selectById(courseId);
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		CourseBL cbl = new CourseBL();
	
//		for(int i = 0;i<cbl.showAllCourse().length-1;i++){
//			
//			
//			System.out.println(cbl.showAllCourse()[i].getCno());
//		}
		System.out.println(cbl.showCourseById("0103").getCnm());
	}


	
	
}
