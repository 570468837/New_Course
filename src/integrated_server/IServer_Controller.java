package integrated_server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.Faculty;
import common.FileInformation;

public class IServer_Controller extends UnicastRemoteObject implements IServer_Interface{

	public IServer_Controller() throws RemoteException{
		
	}

	@Override
	public String helloWorld() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileInformation getCoursesOfOtherFaculties(Faculty self) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean selectCourse(FileInformation fromFile)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean quitCourse(FileInformation fromFile) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
}
