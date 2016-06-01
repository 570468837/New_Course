package B.DataService;

import B.Model.Student;
import B.Model.User;

/**
 * 
 * @author Rain
 *
 */
public interface UserDataService {
	/**
	 * 
	 * @param user
	 * @return whether register succeed
	 */
	public boolean register(User user) ;
	
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return null if failed
	 */
	public Student login(String userName,String password);
}
