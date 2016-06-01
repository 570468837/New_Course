package B.BusinessLogicService;

import java.util.ArrayList;

import B.Model.Course;
import B.Model.Student;

public interface StudentCourseBLService {
	/**
	 * 
	 * @param s
	 * @return empty list if failed
	 */
	public ArrayList<Course> getSelectedCourses(Student s);
}
