package A.businesslogic;

import java.rmi.RemoteException;
import java.sql.SQLException;

import A.businesslogicservice.UserBLService;
import A.data.StudentData;
import A.data.UserData;
import A.dataservice.StudentDataService;
import A.dataservice.UserDataService;
import A.po.AccountPO;
import A.po.StudentPO;

public class UserBL implements UserBLService {

	@Override
	public StudentPO loginValidity(String account, String password) {
		// TODO Auto-generated method stub
		UserDataService uds= new UserData();
		StudentDataService sds = new StudentData();
		AccountPO apo = uds.selectById(account);
		
		if(apo==null|!(apo.getPasswd()).equals(password))
			return null;
		
		String acc = apo.getAcc();
		try {
			return sds.selectByAcc(acc);
		} catch (RemoteException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
