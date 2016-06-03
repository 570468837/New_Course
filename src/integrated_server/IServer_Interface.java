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
    public String helloWorld() throws RemoteException; 
    public FileInformation getCoursesOfOtherFaculties(Faculty self);

}
