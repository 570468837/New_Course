package C.rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.Element;


import C.businesslogic.CourseBL;
import C.businesslogic.CourseSelectionBL;
import C.businesslogic.StudentBL;
import C.businesslogicservice.CourseBLService;
import C.businesslogicservice.CourseSelectionBLService;
import C.businesslogicservice.StudentBLService;
import C.data.DatabaseToXML;
import C.po.CoursePO;
import C.po.StudentPO;
import common.FileInformation;

public class C_Controller extends UnicastRemoteObject implements C_Interface{


	private static final long serialVersionUID = 1L;
	
	private StudentBLService studentController ;
	private CourseBLService courseController ;
	private CourseSelectionBLService selectionController ;
	protected C_Controller() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		studentController = new StudentBL() ;
		courseController = new CourseBL() ;
		selectionController = new CourseSelectionBL() ;
	}

	@Override
	public FileInformation getSharedCourses() {
		// TODO Auto-generated method stub
		try {
			courseController.courseSharedXML();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DatabaseToXML.getFileInformation("courseShare");
	}

	@Override
	public FileInformation getAllCourses() {
		// TODO Auto-generated method stub
		try {
			courseController.courseXML();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DatabaseToXML.getFileInformation("course");
	}

	@Override
	public FileInformation getAllStudents() {
		// TODO Auto-generated method stub
		try {
			studentController.studentXML();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DatabaseToXML.getFileInformation("student");
	}

	@Override
	public FileInformation getAllSelections() {
		// TODO Auto-generated method stub
		try {
			selectionController.courseSelectionXML();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DatabaseToXML.getFileInformation("courseSelection");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean selectFromOtherFaculties(FileInformation file) {
		// TODO Auto-generated method stub
		boolean result = false ;
		Document doc = C_XML_Helper.BytesToDoc(file.getContent()) ;
		Element root = doc.getRootElement() ;
		for(Iterator<Element> i =root.elementIterator();i.hasNext();){
			Element element = i.next() ;
			Vector<String> strs = new Vector<String>() ;
			for(Iterator<Element> j=element.elementIterator();j.hasNext();){
				Element tmp = j.next() ;
				strs.add(tmp.getStringValue()) ;
			}
			StudentPO student = new StudentPO(null, null, null, null, null) ;
			student.setSno(strs.get(0));
			CoursePO course = courseController.showCourseById(strs.get(1)) ;
			result = selectionController.courseSelect(student, course) ;
		}
		return result ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean quitFromOtherFaculties(FileInformation file) {
		// TODO Auto-generated method stub
		boolean result = false ;
		Document doc = C_XML_Helper.BytesToDoc(file.getContent()) ;
		Element root = doc.getRootElement() ;
		for(Iterator<Element> i =root.elementIterator();i.hasNext();){
			Element element = i.next() ;
			Vector<String> strs = new Vector<String>() ;
			for(Iterator<Element> j=element.elementIterator();j.hasNext();){
				Element tmp = j.next() ;
				strs.add(tmp.getStringValue()) ;
			}
			StudentPO student = new StudentPO(null, null, null, null, null) ;
			student.setSno(strs.get(0));
			CoursePO course = courseController.showCourseById(strs.get(1)) ;
			try {
				result = selectionController.courseQuit(student, course) ;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result ;
	}
	public static void main(String[] args) throws RemoteException{
		C_Interface b = new C_Controller() ;
//		FileInformation fileinfo = b.getSharedCourses() ;
		b.getAllCourses();
		b.getAllSelections() ;
		b.getAllStudents() ;
		b.getSharedCourses() ;
		System.out.println("over");
	}
}