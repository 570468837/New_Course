package B.BusinessLogicService;

import B.Model.Course;
import B.Model.Student;

public interface StudentBLService {
	/**
	 * 
	 * @param id
	 * @return null if failed
	 */
	public Student getStudentById(String id);
	/**
	 * 
	 * @param s student
	 * @param c course
	 * @return whether succeed
	 */
	public boolean selectCourse(Student s,Course c) ;
	/**
	 * 
	 * @param s student 
	 * @param c course
	 * @return whether succeed
	 */
	public boolean quitCourse(Student s,Course c) ;
	/**
	 * 返回所有学生的xml文件
	 */
	public void createAllStudentsXMLFile() ;
}
