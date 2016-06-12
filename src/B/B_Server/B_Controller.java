package B.B_Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.Element;
import B.BusinessLogicService.CourseBLService;
import B.BusinessLogicService.CourseBLServiceImpl;
import B.BusinessLogicService.StudentBLService;
import B.BusinessLogicService.StudentBLServiceImpl;
import B.BusinessLogicService.StudentCourseBLService;
import B.BusinessLogicService.StudentCourseBLServiceImpl;
import B.DataService.IOHelper;
import B.Model.Course;
import B.Model.Student;
import common.FileInformation;

public class B_Controller extends UnicastRemoteObject implements B_Interface{


	private static final long serialVersionUID = 1L;
	
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
		return IOHelper.getFileInformation("sharedcourse");
	}

	@Override
	public FileInformation getAllCourses() {
		// TODO Auto-generated method stub
		courseController.createAllCoursesXMLFile();
		return IOHelper.getFileInformation("course");
	}

	@Override
	public FileInformation getAllStudents() {
		// TODO Auto-generated method stub
		studentController.createAllStudentsXMLFile();
		return IOHelper.getFileInformation("student");
	}

	@Override
	public FileInformation getAllSelections() {
		// TODO Auto-generated method stub
		selectionController.CreateAllSelectionsXMLFile();
		return IOHelper.getFileInformation("selection");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean selectFromOtherFaculties(FileInformation selectionFile,FileInformation studentFile) {
		// TODO Auto-generated method stub
		boolean result = false ;
		
		//解析选课学生信息，加入本地数据库
		Document studentDoc = B_XML_Helper.BytesToDoc(studentFile.getContent()) ;
		Element students = studentDoc.getRootElement() ;
		for(Iterator<Element> i=students.elementIterator();i.hasNext();){
			Element element = i.next() ;
			Vector<String> strs = new Vector<String>() ;
			for(Iterator<Element> j=element.elementIterator();j.hasNext();){
				Element tmp  = j.next() ;
				strs.add(tmp.getStringValue()) ;
			}
			Student student = new Student(strs.get(0), strs.get(1), strs.get(2), strs.get(3), strs.get(4)) ;
			studentController.add(student) ;
		}
		
		Document selectionDoc = B_XML_Helper.BytesToDoc(selectionFile.getContent()) ;
		Element root = selectionDoc.getRootElement() ;
		for(Iterator<Element> i =root.elementIterator();i.hasNext();){
			Element element = i.next() ;
			Vector<String> strs = new Vector<String>() ;
			for(Iterator<Element> j=element.elementIterator();j.hasNext();){
				Element tmp = j.next() ;
				strs.add(tmp.getStringValue()) ;
			}
			Student student = studentController.getStudentById(strs.get(0)) ;
			Course course = courseController.getCourseById(strs.get(1)) ;
			result = studentController.selectCourse(student, course) ;
		}
		return result ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean quitFromOtherFaculties(FileInformation file) {
		// TODO Auto-generated method stub
		boolean result = false ;
		Document doc = B_XML_Helper.BytesToDoc(file.getContent()) ;
		Element root = doc.getRootElement() ;
		for(Iterator<Element> i =root.elementIterator();i.hasNext();){
			Element element = i.next() ;
			Vector<String> strs = new Vector<String>() ;
			for(Iterator<Element> j=element.elementIterator();j.hasNext();){
				Element tmp = j.next() ;
				strs.add(tmp.getStringValue()) ;
			}
			Student student = new Student() ;
			student.setId(strs.get(0));
			Course course = courseController.getCourseById(strs.get(1)) ;
			result = studentController.quitCourse(student, course) ;
		}
		return result ;
	}
	public static void main(String[] args) throws RemoteException{
		B_Interface b = new B_Controller() ;
//		FileInformation fileinfo = b.getSharedCourses() ;
//		b.getAllCourses();
//		b.getAllSelections() ;
//		b.getAllStudents() ;
		FileInformation f = b.getSharedCourses() ;
		System.out.println(f.getContent().toString());
//		System.out.println("over");
	}
}
