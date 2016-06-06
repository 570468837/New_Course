package C.dataservice;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import C.po.AccountPO;


public interface UserDataService extends Remote {


	public AccountPO[] selectAll()throws RemoteException;
	 public int count() throws RemoteException;
	 public void createAccountXML() throws IOException;
}

