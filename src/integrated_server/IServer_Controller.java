package integrated_server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import B.B_Server.B_Interface;
import C.rmi.C_Interface;
import common.Common;
import common.Faculty;
import common.FileInformation;

public class IServer_Controller extends UnicastRemoteObject implements IServer_Interface{
	ArrayList<Course> allSharedCourses = new ArrayList<>();

	B_Interface BClient = null; 
	C_Interface CClient = null;

	public IServer_Controller() throws RemoteException{
		try {
			BClient = (B_Interface) Naming.lookup("rmi://localhost:8882/B_Interface");
			CClient = (C_Interface) Naming.lookup("rmi://localhost:8883/C_Interface");

		} catch (MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String helloWorld() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileInformation getSharedCourses(Faculty self) {
		// TODO Auto-generated method stub
		ArrayList<Course> result = new ArrayList<>();
		String function_parentFolder = "IServer/functions/";
		String xsl_parentFolder = "IServer/xsl/";
		FileInformation course_file = null;
		try {
			course_file = BClient.getSharedCourses();
			allSharedCourses.addAll(XML_Helper.decodeCourses(
					XML_Helper.TransformXML(course_file, xsl_parentFolder+"B/formatStudent.xsl", 
							function_parentFolder+"shared/B/", "sharedCourses.xml")));
			
			course_file = CClient.getSharedCourses();
			allSharedCourses.addAll(XML_Helper.decodeCourses(
					XML_Helper.TransformXML(course_file, xsl_parentFolder+"C/formatStudent.xsl", 
							function_parentFolder+"shared/C/", "sharedCourses.xml")));
			
			for(Course c: allSharedCourses){
				Faculty faculty = Common.getFacultyById(c.getId());
				if(!faculty.equals(self))
					result.add(c);
			}
			XML_Helper.outputCourses(result, function_parentFolder+"shared/"+"originalcourses.xml");
			//return XML_Helper.getFileInformation(parentPath, fileName, outputFolder, outputFileName);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	@Override
	public boolean selectCourse(FileInformation fromFile, Faculty self) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean quitCourse(FileInformation fromFile, Faculty self) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
}
