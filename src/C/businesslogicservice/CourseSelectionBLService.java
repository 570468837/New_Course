package C.businesslogicservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import C.po.CoursePO;
import C.po.StudentPO;
import C.vo.AccountVO;
import C.vo.CourseSelectionVO;
import C.vo.CourseVO;

public interface CourseSelectionBLService{
	//

	public CoursePO[] showSelectedCourse(StudentPO spo) throws RemoteException ;
	public boolean courseQuit(StudentPO s,CoursePO c) throws RemoteException;
	public boolean courseSelect(StudentPO s, CoursePO c);
	
}
