package B.DataService;

import java.util.ArrayList;

import B.Model.Course;
import B.Model.Student;



public interface StudentCourseDataService {
	/**
	 * 
	 * @param student
	 * @param course
	 * @return whether select course successfully
	 */
	public boolean add(Student student,Course course) ;
	/**
	 * 
	 * @param student
	 * @param course
	 * @return whether drop the class successfully
	 */
	public boolean delete(Student student,Course course) ;
	/**
	 * 
	 * @param student
	 * @param course
	 * @param grade
	 * @return 
	 */
	public boolean mark(Student student,Course course,String grade) ;
	/**
	 * 
	 * @param s
	 * @return empty list if failed
	 */
	public ArrayList<Course> getSelectedCourses(Student s) ;
}
