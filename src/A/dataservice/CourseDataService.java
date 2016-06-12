package A.dataservice;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import A.po.CoursePO;

public interface CourseDataService extends Remote {
	
	public ArrayList<CoursePO> selectAll()throws RemoteException, SQLException;
	public CoursePO selectById(String cno)throws RemoteException, SQLException;
	public boolean add(CoursePO cpo);
	
}
