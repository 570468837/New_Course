package B.BusinessLogicService;

import java.util.ArrayList;

import B.Model.Course;

public interface CourseBLService {
	/**
	 * 
	 * @param id
	 * @return null if failed
	 */
	public Course getCourseById(String id);
	/**
	 * 
	 * @return list of shared courses
	 */
	public ArrayList<Course> getLocalCourseShared() ;
}
