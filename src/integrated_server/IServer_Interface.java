package integrated_server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.Faculty;
import common.FileInformation;
/**
 * 
 * @author FrankYao
 * 集成服务器的RMI接口
 *
 */
public interface IServer_Interface extends Remote{
	//每一个方法都要抛出RemoteException
    public String helloWorld() throws RemoteException; 
    public FileInformation getCoursesOfOtherFaculties(Faculty self) throws RemoteException ;
    public boolean selectCourse(FileInformation fromFile) throws RemoteException;
    public boolean quitCourse(FileInformation fromFile) throws RemoteException;
    

}
