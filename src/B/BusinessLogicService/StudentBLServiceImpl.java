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
		System.out.println("进入选择所以课程");
		// TODO Auto-generated method stub
		boolean result = false ;
		String courseId = c.getId();
		if(courseId.substring(0, 2).equals("02")) //判断是否为本院系课程
			result = selectLocalCourse(s, c) ;
		else{
			System.out.println("选其他院系课程");
			IInterface iInterface = IInterface.getInstance() ;
			selectionToXml(s, c,"./BFiles/B_XML/B_SELECTIONS.xml");
			FileInformation fileInfo = IOHelper.getFileInformation("selection") ;
			try {
				result = iInterface.IClient.selectCourse(fileInfo, Faculty.B) ;
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
//	private void 
	@Override
	public boolean quitCourse(Student s, Course c) {
		// TODO Auto-generated method stub
		boolean result = false ;
		String cid = c.getId() ;
		if(cid.startsWith("02"))
			result = quitLocalCourse(s, c) ;
		else{
			
			selectionToXml(s, c, "./BFiles/B_XML/B_SELECTIONS.xml");
			FileInformation fileInfo = IOHelper.getFileInformation("selection") ;
			IInterface iInterface = IInterface.getInstance() ;
			try {
				result = iInterface.IClient.quitCourse(fileInfo,Faculty.B) ;
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
		System.out.println("进入选择本地课程");
		return scDataControler.delete(s, c) ;
	}
	
}
