package A.businesslogicservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import A.po.CoursePO;
import A.po.StudentPO;

public interface CourseSelectionBLService{
	//

	public ArrayList<CoursePO> showSelectedCourse(StudentPO spo) throws RemoteException ;
	public boolean courseQuit(StudentPO s,CoursePO c) throws RemoteException;
	public boolean courseSelect(StudentPO s, CoursePO c);
	
}
