package C.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import C.po.AccountPO;


public interface UserDataService extends Remote {


	public AccountPO[] selectAll()throws RemoteException;
	
}

