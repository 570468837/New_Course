package C.dataservice;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import C.po.CoursePO;

public interface CourseDataService extends Remote {


	public CoursePO[] selectAll()throws RemoteException;
	public CoursePO selectById(String cno)throws RemoteException, SQLException;
	public int count() throws RemoteException;
	public void createCourseXML() throws IOException;
	public void createSharesCourseXML() throws IOException;
	public  boolean insertCourse(CoursePO course)throws RemoteException;
}
