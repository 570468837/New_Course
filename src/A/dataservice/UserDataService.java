package A.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import A.po.AccountPO;
import A.po.CoursePO;


public interface UserDataService extends Remote {

	public ArrayList<AccountPO> selectAll()throws RemoteException;
	public AccountPO selectById(String id);
}

