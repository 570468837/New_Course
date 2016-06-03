package A.dataservice;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import A.po.AccountPO;
import A.po.CoursePO;
import A.po.StudentPO;


public interface CourseSelectionDataService extends Remote {

	
	public boolean selectCourse(StudentPO s,CoursePO c)throws RemoteException;
	public ArrayList<CoursePO> selectAllCourse() throws RemoteException;
	public ArrayList<AccountPO> selectAllAccount() throws RemoteException;
	
	public ArrayList<CoursePO> selectMyCourse(StudentPO spo)throws RemoteException;	
	public boolean quitCourse(StudentPO s,CoursePO c)throws RemoteException;
}
