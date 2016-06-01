package B.BusinessLogicService;

import B.Model.Course;

public interface CourseBLService {
	/**
	 * 
	 * @param id
	 * @return null if failed
	 */
	public Course getCourseById(String id);
}
