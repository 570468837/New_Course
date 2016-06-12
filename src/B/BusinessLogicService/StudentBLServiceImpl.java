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
		boolean result1 = true ;//选课信息存入本地数据库结果
		boolean result2 = true ;//选课信息存入其他课程所在院系数据库结果
		String courseId = c.getId();
		result1 = selectLocalCourse(s, c) ;
		System.out.println("本地插入结果："+result1);
		if(!courseId.substring(0, 2).equals("02")&&result1){ //判断是否为本院系课程且本院系已经选课成功
			System.out.println("选其他院系课程");
			IInterface iInterface = IInterface.getInstance() ;
			selectionToXml(s, c,"./BFiles/B_XML/B_SELECTIONS.xml");
			studentToXml(s,"./BFiles/B_XML/B_STUDENTS.xml") ;
			FileInformation selectionFileInfo = IOHelper.getFileInformation("selection") ;
			FileInformation studentFileInfo = IOHelper.getFileInformation("student") ;
			try {
				result2 = iInterface.IClient.selectCourse(selectionFileInfo, studentFileInfo, Faculty.B) ;
				System.out.println("其他院系插入结果："+result1);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result1&&result2 ;
	}
	private void studentToXml(Student s,String savePath){
		Document doc = DocumentHelper.createDocument() ;
		Element root = doc.addElement("STUDENTS");
		Element student = root.addElement("STUDENT") ;
		Element id = student.addElement("ID") ;
		id.setText(s.getId());
		Element name = student.addElement("NAME") ;
		name.setText(s.getName());
		Element gender = student.addElement("GENDER");
		gender.setText(s.getGender());
		Element major = student.addElement("MAJOR") ;
		major.setText(s.getMajor());
		Element password = student.addElement("PASSWORE") ;
		password.setText(s.getPassword());
		IOHelper.docToXml(doc, savePath);
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
		boolean result1 = true ;
		boolean result2 = true ;
		String cid = c.getId() ;
		result1 = quitLocalCourse(s, c) ;
		if(!cid.startsWith("02")){
			selectionToXml(s, c, "./BFiles/B_XML/B_SELECTIONS.xml");
			FileInformation fileInfo = IOHelper.getFileInformation("selection") ;
			IInterface iInterface = IInterface.getInstance() ;
			try {
				result2 = iInterface.IClient.quitCourse(fileInfo,Faculty.B) ;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result1&&result2 ;
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
	@Override
	public boolean add(Student s) {
		// TODO Auto-generated method stub
		return sDataController.add(s);
	}
	
}
