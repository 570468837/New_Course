package B.B_Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import B.BusinessLogicService.CourseBLService;
import B.BusinessLogicService.CourseBLServiceImpl;
import B.BusinessLogicService.StudentBLService;
import B.BusinessLogicService.StudentBLServiceImpl;
import B.BusinessLogicService.StudentCourseBLService;
import B.BusinessLogicService.StudentCourseBLServiceImpl;
import B.DataService.IOHelper;
import common.FileInformation;

public class B_Controller extends UnicastRemoteObject implements B_Interface{


	private static final long serialVersionUID = 1L;
	private static String PATH = "./BFiles/B." ;
	private static String STUDENTS_XMLFILE_NAME="STUDENTS.xml" ;
	private static String SELECTIONS_XMLFILE_NAME="SELECTIONS.xml" ;
	private static String COURSES_XMLFILE_NAME="COURSES.xml";
	private static String SHARED_COURSES_XMLFILE_NAME="SHAREDCOURSES.xml" ;
	
	private StudentBLService studentController ;
	private CourseBLService courseController ;
	private StudentCourseBLService selectionController ;
	protected B_Controller() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		studentController = new StudentBLServiceImpl() ;
		courseController = new CourseBLServiceImpl() ;
		selectionController = new StudentCourseBLServiceImpl() ;
	}

	@Override
	public FileInformation getSharedCourses() {
		// TODO Auto-generated method stub
		courseController.createSharedCoursesXMLFile();
		return IOHelper.getFileInformation(PATH+SHARED_COURSES_XMLFILE_NAME, SHARED_COURSES_XMLFILE_NAME);
	}

	@Override
	public FileInformation getAllCourses() {
		// TODO Auto-generated method stub
		courseController.createAllCoursesXMLFile();
		return IOHelper.getFileInformation(PATH+COURSES_XMLFILE_NAME, COURSES_XMLFILE_NAME);
	}

	@Override
	public FileInformation getAllStudents() {
		// TODO Auto-generated method stub
		studentController.createAllStudentsXMLFile();
		return IOHelper.getFileInformation(PATH+STUDENTS_XMLFILE_NAME, STUDENTS_XMLFILE_NAME);
	}

	@Override
	public FileInformation getAllSelections() {
		// TODO Auto-generated method stub
		selectionController.CreateAllSelectionsXMLFile();
		return IOHelper.getFileInformation(PATH+SELECTIONS_XMLFILE_NAME, SELECTIONS_XMLFILE_NAME);
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
	public static void main(String[] args) throws RemoteException{
//		B_Interface b = new B_Controller() ;
//		FileInformation fileinfo = b.getSharedCourses() ;
		
	}
}
