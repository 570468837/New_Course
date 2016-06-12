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
	
	private static String A_Server_IP = "localhost";
	private static String B_Server_IP = "172.19.110.162";
	private static String C_Server_IP = "localhost";

	public IServer_Controller() throws RemoteException{
		try {
			BClient = (B_Interface) Naming.lookup("rmi://" + B_Server_IP + ":8882/B_Interface");
			CClient = (C_Interface) Naming.lookup("rmi://" + C_Server_IP + ":8883/C_Interface");

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
		FileInformation course_file = null, resultFileInfo = null;
		try {
			course_file = BClient.getSharedCourses();
			allSharedCourses.addAll(XML_Helper.decodeCourses(
					XML_Helper.TransformXML(course_file, xsl_parentFolder+"B/formatClass.xsl", 
							function_parentFolder+"shared/B/", "sharedCourses.xml")));
			
			course_file = CClient.getSharedCourses();
			allSharedCourses.addAll(XML_Helper.decodeCourses(
					XML_Helper.TransformXML(course_file, xsl_parentFolder+"C/formatClass.xsl", 
							function_parentFolder+"shared/C/", "sharedCourses.xml")));
			
			for(Course c: allSharedCourses){
				Faculty faculty = Common.getFacultyById(c.getId());
				if(!faculty.equals(self))
					result.add(c);
			}
			//course -> XML
			XML_Helper.outputCourses(result, function_parentFolder+"shared/"+self.toString()+"_receiving_unitedCourses.xml");
			//XML -> FileInfo -> XML of certain versions
			FileInformation temp = XML_Helper.getFileInformation(function_parentFolder+"shared/", 
					self.toString()+"_receiving_unitedCourses.xml", function_parentFolder+"shared/", 
					self.toString()+"_receiving_unitedCourses.txt");
			XML_Helper.TransformXML(temp, 
					xsl_parentFolder+self.toString()+"/classTo"+self.toString()+".xsl", 
					function_parentFolder+"shared/", self.toString()+"_receiving_courses.xml");
			//XML -> FileInfo
			resultFileInfo = XML_Helper.getFileInformation(function_parentFolder+"shared/", 
					self.toString()+"_receiving_courses.xml", function_parentFolder+"shared/", 
					self.toString()+"_receiving_courses.txt");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultFileInfo;
	}

	@Override
	public boolean selectCourse(FileInformation fromFile, Faculty self) throws RemoteException {
		// TODO Auto-generated method stub
		String function_parentFolder = "IServer/functions/";
		String xsl_parentFolder = "IServer/xsl/";
		// fromFile -> standardXML
		String standard_xml_address = XML_Helper.TransformXML(fromFile, 
				xsl_parentFolder+self.toString()+"/formatClassChoice.xsl", 
				function_parentFolder+"select/", self.toString()+"_select_standard.xml");
		//standardXM -> selections 一次选课只能选一门
		ArrayList<Selection> selections = XML_Helper.decodeSelections(function_parentFolder+
				"select/"+self.toString()+"_select_standard.xml");
		Faculty destination = Common.getFacultyById(selections.get(0).getCid());
		//selections -> destination XML
		XML_Helper.outputSelections(selections, 
				function_parentFolder+"select/"+self.toString()+"_select_destination.xml");
		//XML -> FileInfo
		FileInformation temp = XML_Helper.getFileInformation(function_parentFolder+"select/", 
				self.toString()+"_select_destination.xml", 
				function_parentFolder+"select/", self.toString()+"_select_temp.txt");
		
		boolean ifSuccess = false;
		switch(destination){
		
		case B: 
			ifSuccess = BClient.selectFromOtherFaculties(temp);
			break;
		case C:
			ifSuccess = CClient.selectFromOtherFaculties(temp);
			break;
		default:
			System.out.println("课程不属于任何院系");
			break;
		}
		return ifSuccess;
	}

	@Override
	public boolean quitCourse(FileInformation fromFile, Faculty self) throws RemoteException {
		// TODO Auto-generated method stub
		String function_parentFolder = "IServer/functions/";
		String xsl_parentFolder = "IServer/xsl/";
		// fromFile -> standardXML
		String standard_xml_address = XML_Helper.TransformXML(fromFile, 
				xsl_parentFolder+self.toString()+"/formatClassChoice.xsl", 
				function_parentFolder+"quit/", self.toString()+"_quit_standard.xml");
		//standardXM -> courses 一次选课只能选一门
		ArrayList<Selection> selections = XML_Helper.decodeSelections(function_parentFolder+
				"quit/"+self.toString()+"_quit_standard.xml");
		Faculty destination = Common.getFacultyById(selections.get(0).getCid());
		//courses -> destination XML
		XML_Helper.outputSelections(selections, 
				function_parentFolder+"quit/"+self.toString()+"_quit_destination.xml");
		//XML -> FileInfo
		FileInformation temp = XML_Helper.getFileInformation(function_parentFolder+"quit/", 
				self.toString()+"_quit_destination.xml", 
				function_parentFolder+"quit/", self.toString()+"_quit_temp.txt");
		
		boolean ifSuccess = false;
		switch(destination){
		
		case B: 
			ifSuccess = BClient.selectFromOtherFaculties(temp);
			break;
		case C:
			ifSuccess = CClient.selectFromOtherFaculties(temp);
			break;
		default:
			System.out.println("课程不属于任何院系");
			break;
		}
		return ifSuccess;
	}
}
