package B.BusinessLogicService;

import B.Model.Course;
import B.Model.Student;

public interface StudentBLService {
	/**
	 * 选本院系课程
	 * @param s 
	 * @param c
	 * @return
	 */
	public boolean selectLocalCourse(Student s,Course c) ;
	/**
	 *  退本院系课程
	 * @param s
	 * @param c
	 * @return
	 */
	public boolean quitLocalCourse(Student s,Course c) ;
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
	/**
	 * 添加一个选则本院系课程的其他院系学生信息进入数据库
	 * @param s
	 * @return
	 */
	public boolean add(Student s);
}
