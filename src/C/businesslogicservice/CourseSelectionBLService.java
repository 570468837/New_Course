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
	public CourseSelectionVO courseSelect(AccountVO a,CourseVO c);
	public CoursePO[] showSelectedCourse(StudentPO spo) throws RemoteException ;
	public boolean courseQuit(AccountVO a,CourseVO c) throws RemoteException;
}
