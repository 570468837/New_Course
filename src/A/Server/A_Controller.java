package A.Server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.Element;

import A.businesslogicservice.*;
import A.businesslogic.*;
import A.data.*;
import A.po.CoursePO;
import A.po.StudentPO;
import B.B_Server.B_XML_Helper;
import B.Model.Course;
import B.Model.Student;
import common.FileInformation;


public class A_Controller extends UnicastRemoteObject implements A_Interface{


	private static final long serialVersionUID = 1L;
	
	private StudentBLService studentController ;
	private CourseBLService courseController ;
	private CourseSelectionBLService selectionController ;
	DatabaseToXML dtxml;
	
	protected A_Controller() throws RemoteException {
		super();
		studentController = new StudentBL();
		courseController = new CourseBL();
		selectionController = new CourseSelectionBL();
		dtxml = new DatabaseToXML();
		// TODO Auto-generated constructor stub
	}

	@Override
	public FileInformation getSharedCourses() throws RemoteException {
		// TODO Auto-generated method stub
		try {
			dtxml.courseShareXML();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtxml.getFileInformation("courseShare");
	}

	@Override
	public FileInformation getAllCourses() throws RemoteException {
		try {
			dtxml.courseXML();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtxml.getFileInformation("course");
	}

	@Override
	public FileInformation getAllStudents() throws RemoteException {
		try {
			dtxml.studentXML();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtxml.getFileInformation("student");
	}

	@Override
	public FileInformation getAllSelections() throws RemoteException {
		try {
			dtxml.courseSelectionXML();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtxml.getFileInformation("selection");
	}

	@Override
	public boolean selectFromOtherFaculties(FileInformation selectionFile,FileInformation studentFile)
			throws RemoteException{
		boolean result = false ;
		Document studentDoc = A_XML_Helper.BytesToDoc(studentFile.getContent()) ;
		Element students = studentDoc.getRootElement() ;
		StudentPO student = null;
		for(Iterator<Element> i=students.elementIterator();i.hasNext();){
			Element element = i.next() ;
			Vector<String> strs = new Vector<String>() ;
			for(Iterator<Element> j=element.elementIterator();j.hasNext();){
				Element tmp  = j.next() ;
				strs.add(tmp.getStringValue()) ;
			}
			student = new StudentPO(strs.get(0),strs.get(1),strs.get(2),strs.get(3),null) ;
			studentController.addStudent(student);
		}
		
		Document selectionDoc = A_XML_Helper.BytesToDoc(selectionFile.getContent()) ;
		Element root = selectionDoc.getRootElement() ;
		for(Iterator<Element> i =root.elementIterator();i.hasNext();)
		{
			Element element = i.next() ;
			Vector<String> strs = new Vector<String>() ;
			for(Iterator<Element> j=element.elementIterator();j.hasNext();){
				Element tmp = j.next() ;
				strs.add(tmp.getStringValue()) ;
			}
			StudentPO spo = studentController.showStudentById(strs.get(0)) ;
			CoursePO course = courseController.showCourseById(strs.get(1));
			result = selectionController.courseSelect(spo, course) ;
		}
		return result ;
	}

	@Override
	public boolean quitFromOtherFaculties(FileInformation file)
			throws RemoteException {
		boolean result = false ;
		Document doc = A_XML_Helper.BytesToDoc(file.getContent()) ;
		Element root = doc.getRootElement() ;
		for(Iterator<Element> i =root.elementIterator();i.hasNext();){
			Element element = i.next() ;
			Vector<String> strs = new Vector<String>() ;
			for(Iterator<Element> j=element.elementIterator();j.hasNext();){
				Element tmp = j.next() ;
				strs.add(tmp.getStringValue()) ;
			}
			StudentPO student = studentController.showStudentById(strs.get(0));
			CoursePO course = courseController.showCourseById(strs.get(1));
			result = selectionController.courseQuit(student, course) ;
		}
		return result ;
	}
	

}
