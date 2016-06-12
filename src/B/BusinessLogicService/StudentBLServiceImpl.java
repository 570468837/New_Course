package B.BusinessLogicService;


import java.rmi.RemoteException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import common.Faculty;
import common.FileInformation;
import B.DataService.IOHelper;
import B.DataService.StudentCourseDataService;
import B.DataService.StudentCourseDataServiceImpl;
import B.DataService.StudentDataService;
import B.DataService.StudentDataServiceImpl;
import B.Model.Course;
import B.Model.Student;

public class StudentBLServiceImpl implements StudentBLService{
	private StudentDataService sDataController ;
	private StudentCourseDataService scDataControler ;
	public StudentBLServiceImpl(){
		sDataController = new StudentDataServiceImpl() ;
		scDataControler = new StudentCourseDataServiceImpl() ;
	}
	@Override
	public Student getStudentById(String id) {
		// TODO Auto-generated method stub
		return sDataController.find(id);
	}
	@Override
	public boolean selectCourse(Student s, Course c) {
		// TODO Auto-generated method stub
		boolean result = false ;
		String courseId = c.getId();
		if(courseId.startsWith("02")) //判断是否为本院系课程
			result = selectLocalCourse(s, c) ;
		else{
			Faculty f = null ;
			if(courseId.startsWith("03"))
				f = Faculty.A ;
			else
				f = Faculty.C ;
			IInterface iInterface = IInterface.getInstance() ;
			selectionToXml(s, c,"./BFiles/B_XML/B_SELECTIONS.xml");
			FileInformation fileInfo = IOHelper.getFileInformation("./BFiles/B_XML/B_SELECTIONS.xml") ;
			try {
				result = iInterface.IClient.selectCourse(fileInfo, f) ;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result ;
	}
	private void selectionToXml(Student s,Course c,String savePath){
		Document doc = DocumentHelper.createDocument() ;
		Element root = doc.addElement("SELECTIONS") ;
		Element selection = root.addElement("SELECTION") ;
		Element sid = selection.addElement("STUDENT_ID") ;
		sid.setText(s.getId());
		Element cid = selection.addElement("COURSE_ID");
		cid.setText(c.getId());
		Element grade = selection.addElement("GRADE") ;
		grade.setText("0");
		IOHelper.docToXml(doc,savePath);
	}
	@Override
	public boolean quitCourse(Student s, Course c) {
		// TODO Auto-generated method stub
		boolean result = false ;
		String cid = c.getId() ;
		if(cid.startsWith("02"))
			result = quitLocalCourse(s, c) ;
		else{
			Faculty f = null ;
			if(cid.startsWith("01"))
				f = Faculty.A ;
			else
				f = Faculty.C ;
			selectionToXml(s, c, "./BFiles/B_XML/B_SELECTIONS.xml");
			FileInformation fileInfo = IOHelper.getFileInformation("./BFiles/B_XML/B_SELECTIONS.xml") ;
			IInterface iInterface = IInterface.getInstance() ;
			try {
				result = iInterface.IClient.quitCourse(fileInfo,f) ;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result ;
	}
	@Override
	public void createAllStudentsXMLFile() {
		// TODO Auto-generated method stub
		sDataController.createAllStudentsXMLFile();
	}
	@Override
	public boolean selectLocalCourse(Student s, Course c) {
		// TODO Auto-generated method stub
		return scDataControler.add(s, c) ;
	}
	@Override
	public boolean quitLocalCourse(Student s, Course c) {
		// TODO Auto-generated method stub
		return scDataControler.delete(s, c) ;
	}
	
}
