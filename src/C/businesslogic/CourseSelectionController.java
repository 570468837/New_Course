package C.businesslogic;
import java.rmi.RemoteException;
import java.util.ArrayList;

import C.businesslogicservice.CourseSelectionBLService;
import C.po.CoursePO;
import C.po.StudentPO;
import C.vo.AccountVO;
import C.vo.CourseSelectionVO;
import C.vo.CourseVO;


public class CourseSelectionController {
	CourseSelectionBLService courseSelectionBLService =new CourseSelectionBL();
	public boolean courseSelect(StudentPO spo,CoursePO cpo){
		return courseSelectionBLService.courseSelect(spo, cpo);
	}
	public CoursePO[] showSelectedCourse(StudentPO spo)  throws RemoteException{
		return courseSelectionBLService.showSelectedCourse(spo);
		
	}
	
	
}
