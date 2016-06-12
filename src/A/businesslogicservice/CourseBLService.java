package A.businesslogicservice;

import java.util.ArrayList;

import A.po.CoursePO;

public interface CourseBLService{
	public ArrayList<CoursePO> showAllCourse();
	public CoursePO showCourseById(String courseId);
	
}
