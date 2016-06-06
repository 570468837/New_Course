package C.businesslogicservice;



import java.io.IOException;
import java.rmi.Remote;

import C.vo.AccountVO;


public interface UserBLService {

	//登录
	

	public boolean login(String username, String password);
	public void accountXML() throws IOException;

}