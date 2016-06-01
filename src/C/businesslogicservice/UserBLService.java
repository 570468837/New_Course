package C.businesslogicservice;



import java.rmi.Remote;

import C.vo.AccountVO;
import C.vo.LoginVO;

public interface UserBLService {

	//登录
	

	public boolean login(String username, String password);

}