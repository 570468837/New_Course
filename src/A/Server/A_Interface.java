package A.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.FileInformation;

public interface A_Interface extends Remote{
	/**
	 * 
	 * @return 获取共享课程的xml文件
	 */
	public FileInformation getSharedCourses()throws RemoteException ;
	/**
	 * 获取所有课程的xml文件：
	 */
	public FileInformation getAllCourses()throws RemoteException;

	/**
	 * 
	 * @return获取所有学生的xml文件：
	 */
	public FileInformation getAllStudents()throws RemoteException;

	/**
	 * 
	 * @return 获取所有选课信息的xml文件：
	 */
	public FileInformation getAllSelections()throws RemoteException;

	/**
	 * 
	 * @param file
	 * @return 提供其他院系的学生选了本院系一门课的xml文件，执行选课任务(需要把选课信息添加到数据库中)，返回选课是否成功：
	 */
	public boolean selectFromOtherFaculties(FileInformation selectionFile,FileInformation studentFile)throws RemoteException;

	/**
	 * 
	 * @param file
	 * @return 提供其他院系的学生退了本院系一门课的xml文件，执行退课任务(在数据库中要删除)，返回退课是否成功：
	 */
	public boolean quitFromOtherFaculties(FileInformation file)throws RemoteException;
}
