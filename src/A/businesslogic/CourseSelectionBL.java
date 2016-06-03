package A.businesslogic;

import java.rmi.RemoteException;
import java.util.ArrayList;

import A.data.CourseSelectionData;
import A.businesslogicservice.CourseSelectionBLService;
import A.po.CoursePO;
import A.po.StudentPO;

public class CourseSelectionBL implements CourseSelectionBLService {
	CourseSelectionData csd;
	@Override
	public ArrayList<CoursePO> showSelectedCourse(StudentPO spo) throws RemoteException {
		// TODO Auto-generated method stub
		csd = new CourseSelectionData();
		return csd.selectMyCourse(spo);
	}

	@Override
	public boolean courseQuit(StudentPO s, CoursePO c) throws RemoteException {
		// TODO Auto-generated method stub
		csd = new CourseSelectionData();
		return csd.quitCourse(s, c);
	}

	@Override
	public boolean courseSelect(StudentPO s, CoursePO c) {
		// TODO Auto-generated method stub
		csd = new CourseSelectionData();
		try {
			return csd.selectCourse(s, c);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


}
