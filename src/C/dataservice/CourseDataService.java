package C.dataservice;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import C.po.CoursePO;

public interface CourseDataService extends Remote {


	public CoursePO[] selectAll()throws RemoteException;
	public CoursePO selectById(String cno)throws RemoteException, SQLException;
	
}
