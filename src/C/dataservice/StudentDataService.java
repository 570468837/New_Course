package C.dataservice;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import C.po.CoursePO;
import C.po.StudentPO;


public interface StudentDataService extends Remote{
	public StudentPO[] selectAll()throws RemoteException;
	public StudentPO selectById(String sno)throws RemoteException, SQLException;
	 public int count() throws RemoteException;
}
