package C.businesslogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.Faculty;
import common.FileInformation;
import integrated_server.IServer_Interface;

public class IServer extends UnicastRemoteObject implements IServer_Interface {

	protected IServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String helloWorld() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileInformation getSharedCourses(Faculty self) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean selectCourse(FileInformation fromFile,FileInformation studentFile, Faculty self) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean quitCourse(FileInformation fromFile, Faculty self) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
