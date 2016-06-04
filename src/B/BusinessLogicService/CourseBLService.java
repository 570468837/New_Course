package B.BusinessLogicService;

import java.util.ArrayList;

import B.Model.Course;

public interface CourseBLService {
	/**
	 * 
	 * @return all local courses 
	 */
	public ArrayList<Course> getAllLocalCourse();
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
	/**
	 * 生成所有课程的xml文件
	 */
	public void createAllCoursesXMLFile();
	/**
	 * 生成所以共享课程的xml文件
	 */
	public void createSharedCoursesXMLFile() ;
}
