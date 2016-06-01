package B.BusinessLogicService;

import B.Model.Student;

public interface UserBLService {
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return null if failed
	 */
	public Student loginValidity(String userName,String password);
}
