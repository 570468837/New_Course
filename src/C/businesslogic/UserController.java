package C.businesslogic;

import java.rmi.RemoteException;
import java.util.ArrayList;

import C.businesslogicservice.UserBLService;
import C.vo.AccountVO;
import C.vo.LoginVO;

public class UserController {
	
	UserBLService userBLService=new UserBL();
	
	//登录
	public boolean login(String username,String password)throws RemoteException{
		return false;
		
	}
	

}

