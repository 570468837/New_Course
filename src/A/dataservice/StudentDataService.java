package A.dataservice;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import A.po.CoursePO;
import A.po.StudentPO;


public interface StudentDataService extends Remote{
	public ArrayList<StudentPO> selectAll()throws RemoteException;
	public StudentPO selectById(String sno)throws RemoteException, SQLException;
	public StudentPO selectByAcc(String acc) throws RemoteException,SQLException;
}
