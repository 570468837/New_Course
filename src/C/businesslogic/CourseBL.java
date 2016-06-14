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


import C.businesslogic.IInterface;

import C.businesslogicservice.CourseBLService;
import C.data.Demo;
import C.dataservice.CourseDataService;
import C.dataservice.StudentDataService;
import C.po.CoursePO;
import C.rmi.C_Interface;
import C.rmi.C_XML_Helper;
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
		ArrayList<CoursePO> temp = new ArrayList<CoursePO>();
		try {
			FileInformation SharedCourses = iController.IClient.getSharedCourses(Faculty.C) ;
			
			Document Doc = C_XML_Helper.BytesToDoc(SharedCourses.getContent()) ;
			
			//解析doc获取course对象
			updateList(temp, Doc) ;
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0;i<this.showAllCourse().length;i++ ){
			result.add(this.showAllCourse()[i]);
		}
		for (int i = 0;i<result.size();i++){
			System.out.println(result.get(i).getCnm());
		}
		return result ;
	}
	public ArrayList<CoursePO> updateList(ArrayList<CoursePO> list,Document doc){
		CourseDataService cd = null;
		Element root = doc.getRootElement() ;
		for(Iterator<Element> i=root.elementIterator();i.hasNext();){
			Element element = i.next() ;
			Vector<String> strs = new Vector<String>() ;
			for(Iterator<Element> j=element.elementIterator();j.hasNext();){
				Element tmp = j.next() ;
				strs.add(tmp.getStringValue()) ;
			}
			CoursePO course = new CoursePO(strs.get(0),strs.get(1),0,Integer.valueOf(strs.get(2)),strs.get(3),strs.get(4),"0") ;
			list.add(course) ;
			try {
				cd=(CourseDataService) Naming.lookup("rmi://127.0.0.1:2017/Server");
			} catch (MalformedURLException | RemoteException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				cd.insertCourse(course);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//插入数据库
			
			
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
