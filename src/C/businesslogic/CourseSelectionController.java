package C.businesslogic;
import java.rmi.RemoteException;
import java.util.ArrayList;

import C.businesslogicservice.CourseSelectionBLService;
import C.po.CoursePO;
import C.po.StudentPO;
import C.vo.AccountVO;
import C.vo.CourseSelectionVO;
import C.vo.CourseVO;
import C.vo.LoginVO;

public class CourseSelectionController {
	CourseSelectionBLService courseSelectionBLService =new CourseSelectionBL();
	public CourseSelectionVO courseSelect(AccountVO avo,CourseVO cvo){
		return courseSelectionBLService.courseSelect(avo, cvo);
	}
	public CoursePO[] showSelectedCourse(StudentPO spo)  throws RemoteException{
		return courseSelectionBLService.showSelectedCourse(spo);
		
	}
	
	
}
