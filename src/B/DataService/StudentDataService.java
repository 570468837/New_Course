package B.DataService;

import java.util.List;

import B.Model.Student;


/**
 * 
 * @author Rain
 *	对Student的增删改查
 */
public interface StudentDataService {
	/**
	 * 
	 * @return students list
	 */
	public List<Student> show();
	/**
	 * 
	 * @return whether add a new student succeed
	 */
	public boolean add(Student student);
	/**
	 * 
	 * @param id
	 * @return null if no
	 */
	public Student find(String id);
}
