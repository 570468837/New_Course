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
	/**
	 * 请忽略这个方法
	 * @return
	 * @throws RemoteException
	 */
    public String helloWorld() throws RemoteException; 
    /**
     * 
     * @param self 本院系
     * @return 其他院系的共享课程
     * @throws RemoteException
     */
    public FileInformation getSharedCourses(Faculty self) throws RemoteException ;
    /**
     * 
     * @param fromFile 一条选课记录（其他院系的课）
     * @return	是否选课成功
     * @throws RemoteException
     */
    public boolean selectCourse(FileInformation fromFile, Faculty self) throws RemoteException;
    /**
     * 
     * @param fromFile 一条退课记录（其他院系的课）
     * @return
     * @throws RemoteException
     */
    public boolean quitCourse(FileInformation fromFile, Faculty self) throws RemoteException;
    

}
