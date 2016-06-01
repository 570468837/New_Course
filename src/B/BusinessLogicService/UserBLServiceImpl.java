package B.BusinessLogicService;

import B.DataService.UserDataService;
import B.DataService.UserDataServiceImpl;
import B.Model.Student;

public class UserBLServiceImpl implements UserBLService{
	private UserDataService uDataController ;
	public UserBLServiceImpl() {
		// TODO Auto-generated constructor stub
		uDataController = new UserDataServiceImpl() ;
	}
	@Override
	public Student loginValidity(String userName, String password) {
		// TODO Auto-generated method stub
		Student student = uDataController.login(userName, password) ;
		if(student==null)
			return null;
		else{
			return student ;
		}
	}

}
