package C.dataservice;


import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import C.po.AccountPO;
import C.po.CoursePO;
import C.po.StudentPO;


public interface CourseSelectionDataService extends Remote {

	
	public boolean selectCourse(StudentPO s,CoursePO c)throws RemoteException;
	public CoursePO[] selectAllCourse() throws RemoteException;
	public AccountPO[] selectAllAccount() throws RemoteException;
	
	public CoursePO[] selectMyCourse(StudentPO spo)throws RemoteException;	
	public boolean quitCourse(StudentPO s,CoursePO c)throws RemoteException;
	 public int countC() throws RemoteException;
	 public int countCS() throws RemoteException;
	 public int countA() throws RemoteException;
	 public void createCourseSelectionXML() throws IOException;
}
