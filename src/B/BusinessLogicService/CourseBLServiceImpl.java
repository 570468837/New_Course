package B.BusinessLogicService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.Element;

import common.Faculty;
import common.FileInformation;
import B.B_Server.B_XML_Helper;
import B.DataService.CourseDataService;
import B.DataService.CourseDataServiceImpl;
import B.Model.Course;

public class CourseBLServiceImpl implements CourseBLService{
	private CourseDataService dataControler ;
	public CourseBLServiceImpl() {
		// TODO Auto-generated constructor stub
		dataControler = new CourseDataServiceImpl() ;
	}
	@Override
	public Course getCourseById(String id) {
		// TODO Auto-generated method stub
		return dataControler.find(id);
	}
	@Override
	public ArrayList<Course> getLocalCourseShared() {
		// TODO Auto-generated method stub
		ArrayList<Course> result = new ArrayList<Course>() ;
		List<Course> list = dataControler.show() ;
		for(Course c:list){
			if(c.getIfShared()=='1'){
				result.add(c) ;
			}
		}
		return result ;
	}
	@Override
	public ArrayList<Course> getAllLocalCourse() {
		// TODO Auto-generated method stub
		return (ArrayList<Course>)dataControler.show();
	}
	@Override
	public void createAllCoursesXMLFile() {
		// TODO Auto-generated method stub
		dataControler.createAllCoursesXMLFile();
	}
	@Override
	public void createSharedCoursesXMLFile() {
		// TODO Auto-generated method stub
		dataControler.createSharedCoursesXMLFile();
	}
	@Override
	public ArrayList<Course> getAllCourses() {
		// TODO Auto-generated method stub
		IInterface iController = IInterface.getInstance() ;
		
		try {
			FileInformation otherSharedCourses = iController.IClient.getSharedCourses(Faculty.B) ;
//			FileInformation cSharedCourses = iController.IClient.getSharedCourses(Faculty.C) ;
			Document otherDoc = B_XML_Helper.BytesToDoc(otherSharedCourses.getContent()) ;
//			Document cDoc = B_XML_Helper.BytesToDoc(cSharedCourses.getContent()) ;
			//解析doc获取course对象 顺便插入本地数据库
			insetOtherCoursesIntoLocalDB(otherDoc) ;
//			updateList(result, cDoc) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.getAllLocalCourse() ;
	}
	@SuppressWarnings("unchecked")
	private void insetOtherCoursesIntoLocalDB(Document doc){
		Element root = doc.getRootElement() ;
		for(Iterator<Element> i=root.elementIterator();i.hasNext();){
			Element element = i.next() ;
			Vector<String> strs = new Vector<String>() ;
			for(Iterator<Element> j=element.elementIterator();j.hasNext();){
				Element tmp = j.next() ;
				strs.add(tmp.getStringValue()) ;
			}
			Course course = new Course(strs.get(0), strs.get(1), strs.get(3), strs.get(4), strs.get(2)) ;
			dataControler.add(course) ;
		}
	}
	public static void main(String[] args){
		CourseBLServiceImpl c = new CourseBLServiceImpl() ;
		c.getAllCourses();
	}
}
