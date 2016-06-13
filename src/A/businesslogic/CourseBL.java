package A.businesslogic;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.Element;

import common.Faculty;
import common.FileInformation;
import A.Server.A_XML_Helper;
import A.businesslogicservice.CourseBLService;
import A.data.CourseData;
import A.dataservice.CourseDataService;
import A.po.CoursePO;
import B.BusinessLogicService.IInterface;

public class CourseBL implements CourseBLService {
	CourseDataService cds;
	@Override
	public ArrayList<CoursePO> showAllCourse() {
		// TODO Auto-generated method stub
		cds = new CourseData();
		IInterface iController = IInterface.getInstance() ;
		
		try {
			FileInformation otherSharedCourses = iController.IClient.getSharedCourses(Faculty.A) ;
			Document otherDoc = A_XML_Helper.BytesToDoc(otherSharedCourses.getContent()) ;
			//解析doc获取course对象 顺便插入本地数据库
			insetOtherCoursesIntoLocalDB(otherDoc) ;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		try {
			return cds.selectAll();
		} catch (RemoteException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CoursePO showCourseById(String courseId) {
		// TODO Auto-generated method stub
		cds = new CourseData();
		try {
			return cds.selectById(courseId);
		} catch (RemoteException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addCourse(CoursePO cpo) {
		// TODO Auto-generated method stub
		cds = new CourseData();
		return cds.add(cpo);
	}
	
	private void insetOtherCoursesIntoLocalDB(Document doc){
		Element root = doc.getRootElement() ;
		cds = new CourseData();
		for(Iterator<Element> i=root.elementIterator();i.hasNext();){
			Element element = i.next() ;
			Vector<String> strs = new Vector<String>() ;
			for(Iterator<Element> j=element.elementIterator();j.hasNext();){
				Element tmp = j.next() ;
				strs.add(tmp.getStringValue()) ;
			}
			CoursePO course = new CoursePO(strs.get(0), strs.get(1), Integer.parseInt(strs.get(2)), strs.get(3), strs.get(4),1) ;
			addCourse(course) ;
		}
	}

}
