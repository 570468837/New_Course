package C.businesslogic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.Element;

import B.B_Server.B_XML_Helper;
import B.BusinessLogicService.IInterface;
import B.Model.Course;
import C.businesslogicservice.CourseBLService;
import C.dataservice.CourseDataService;
import C.dataservice.StudentDataService;
import C.po.CoursePO;
/*
 * 
 * 获取所有课程，返回的是CoursePO[]
 * 
 * */
import common.Faculty;
import common.FileInformation;

public class CourseBL implements CourseBLService{
	public CoursePO[] showAllCourse() {
		// TODO Auto-generated method stub
		CourseDataService cd;
		
		try {
			cd=(CourseDataService) Naming.lookup("rmi://127.0.0.1:2017/Server");
			return cd.selectAll();
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 这里是用小宇的方法显示出所有的课程，包括本地的和共享的
	 * 
	 * */
	public ArrayList<CoursePO> getAllCourses() {
		// TODO Auto-generated method stub
		IInterface iController = IInterface.getInstance() ;
		ArrayList<CoursePO> result = new ArrayList<CoursePO>();
		for(int i = 0;i<this.showAllCourse().length;i++ ){
			result.add(this.showAllCourse()[i]);
		}
		try {
			FileInformation aSharedCourses = iController.IClient.getSharedCourses(Faculty.A) ;
			FileInformation bSharedCourses = iController.IClient.getSharedCourses(Faculty.B) ;
			Document aDoc = B_XML_Helper.BytesToDoc(aSharedCourses.getContent()) ;
			Document cDoc = B_XML_Helper.BytesToDoc(bSharedCourses.getContent()) ;
			
			//解析doc获取course对象
			updateList(result, aDoc) ;
			updateList(result, cDoc) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
	}
	public ArrayList<CoursePO> updateList(ArrayList<CoursePO> list,Document doc){
		Element root = doc.getRootElement() ;
		for(Iterator<Element> i=root.elementIterator();i.hasNext();){
			Element element = i.next() ;
			Vector<String> strs = new Vector<String>() ;
			for(Iterator<Element> j=element.elementIterator();j.hasNext();){
				Element tmp = j.next() ;
				strs.add(tmp.getStringValue()) ;
			}
			CoursePO course = new CoursePO(strs.get(0),strs.get(1),Integer.valueOf(strs.get(2)),Integer.valueOf(strs.get(3)),strs.get(4), strs.get(5),strs.get(6)) ;
			list.add(course) ;
		}
		return list ;
		
	}
	
/*
 * 根据课程编号获取课程对象
 * */
	public CoursePO showCourseById(String courseId) {
		// TODO Auto-generated method stub
		CourseDataService cd;
		try {
			cd=(CourseDataService) Naming.lookup("rmi://127.0.0.1:2017/Server");
			return cd.selectById(courseId);
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void courseXML() throws IOException {
		// TODO Auto-generated method stub
		CourseDataService cd;
		try {
			cd=(CourseDataService) Naming.lookup("rmi://127.0.0.1:2017/Server");
			cd.createCourseXML();
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void courseSharedXML() throws IOException {
		// TODO Auto-generated method stub
		CourseDataService cd;
		try {
			cd=(CourseDataService) Naming.lookup("rmi://127.0.0.1:2017/Server");
			cd.createSharesCourseXML();
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		CourseBL cbl = new CourseBL();
	
		for(int i = 0;i<cbl.showAllCourse().length;i++){
			
			
			System.out.println(cbl.showAllCourse()[i].getCno());
		}
//		System.out.println(cbl.showCourseById("0103").getCnm());
	}


	
	
}
