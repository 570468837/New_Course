package C.businesslogicservice;

import java.io.IOException;
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
	public boolean courseQuitLocal(StudentPO s,CoursePO c) throws RemoteException;
	public boolean courseSelectLocal(StudentPO s, CoursePO c);
	public void courseSelectionXML() throws IOException;
	public  void selectionToXml(StudentPO s,CoursePO c,String savePath);
	public boolean courseSelect(StudentPO s,CoursePO c);
	public boolean courseQuit(StudentPO s, CoursePO c) throws RemoteException;
	
}
