package C.dataservice;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import C.po.AccountPO;
import C.po.CoursePO;
import C.po.StudentPO;


public interface CourseSelectionDataService extends Remote {

	
	public boolean selectCourse(AccountPO a,CoursePO c)throws RemoteException;
	public CoursePO[] selectAllCourse() throws RemoteException;
	public AccountPO[] selectAllAccount() throws RemoteException;
	
	public CoursePO[] selectMyCourse(StudentPO spo)throws RemoteException;	
	public boolean quitCourse(AccountPO a,CoursePO c)throws RemoteException;
}
