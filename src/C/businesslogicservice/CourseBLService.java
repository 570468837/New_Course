package C.businesslogicservice;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;

import org.dom4j.Document;

import C.po.CoursePO;

public interface CourseBLService{
	public CoursePO[] showAllCourse();
	public CoursePO showCourseById(String courseId);
	public void courseSharedXML() throws IOException ;
	public void courseXML() throws IOException ;
	public ArrayList<CoursePO> getAllCourses() ;
	public ArrayList<CoursePO> updateList(ArrayList<CoursePO> list,Document doc);
}
