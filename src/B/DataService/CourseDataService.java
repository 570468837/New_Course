package B.DataService;

import java.util.List;

import B.Model.Course;

public interface CourseDataService {
	/**
	 * 
	 * @param course
	 * @return whether add succeed
	 */
	public boolean add(Course course);
	/**
	 * 
	 * @return courses list
	 */
	public List<Course> show();
	/**
	 * 
	 * @param id
	 * @return null if failed
	 */
	public Course find(String id) ;
	/**
	 * 生成所有课程的xml文件
	 */
	public void createAllCoursesXMLFile();
	/**
	 * 生成所以共享课程的xml文件
	 */
	public void createSharedCoursesXMLFile() ;
}
