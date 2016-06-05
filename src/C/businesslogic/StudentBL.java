package C.businesslogic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import C.businesslogicservice.StudentBLService;
import C.dataservice.CourseDataService;
import C.dataservice.StudentDataService;
import C.dataservice.UserDataService;
import C.po.CoursePO;
import C.po.StudentPO;

public class StudentBL implements StudentBLService{

	@Override
	/*
	 * 显示所有学生
	 * */
	public void showAllStudent() {
		// TODO Auto-generated method stub
		StudentDataService sd;
		try {
			sd=(StudentDataService) Naming.lookup("rmi://127.0.0.1:2018/Server");
			sd.selectAll();
			
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
	
	/*
	 * 根据课程编号获取课程对象
	 * */
		public StudentPO showStudentById(String studentId) {
			// TODO Auto-generated method stub
			StudentDataService sd;
			try {
				sd=(StudentDataService) Naming.lookup("rmi://127.0.0.1:2018/Server");
				return sd.selectById(studentId);
				
				
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
		public void studentXML() throws IOException {
			// TODO Auto-generated method stub
			StudentDataService sd;
			try {
				sd=(StudentDataService) Naming.lookup("rmi://127.0.0.1:2018/Server");
				sd.createStudentXML();
				
				
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
		public static void main(String[] args) {
			StudentBL sbl = new StudentBL();
			
//			for(int i = 0;i<cbl.showAllCourse().length-1;i++){
//				
//				
//				System.out.println(cbl.showAllCourse()[i].getCno());
//			}
			System.out.println(sbl.showStudentById("001").getSnm());
		}

}
