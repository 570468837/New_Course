package B.B_Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.FileInformation;

public class B_Controller extends UnicastRemoteObject implements B_Interface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected B_Controller() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public FileInformation getSharedCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileInformation getAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileInformation getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileInformation getAllSelections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean selectFromOtherFaculties(FileInformation file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean quitFromOtherFaculties(FileInformation file) {
		// TODO Auto-generated method stub
		return false;
	}

}
