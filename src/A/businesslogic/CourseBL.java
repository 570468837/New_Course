package A.businesslogic;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import A.businesslogicservice.CourseBLService;
import A.data.CourseData;
import A.dataservice.CourseDataService;
import A.po.CoursePO;


public class CourseBL implements CourseBLService {
	CourseDataService cds;
	@Override
	public ArrayList<CoursePO> showAllCourse() {
		// TODO Auto-generated method stub
		cds = new CourseData();
		try {
			return cds.selectAll();
		} catch (RemoteException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CoursePO showCourseById(String courseId) {
		// TODO Auto-generated method stub
		cds = new CourseData();
		try {
			return cds.selectById(courseId);
		} catch (RemoteException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addCourse(CoursePO cpo) {
		// TODO Auto-generated method stub
		cds = new CourseData();
		return cds.add(cpo);
	}

}
