package C.businesslogicservice;

import java.io.IOException;
import java.rmi.Remote;

import C.po.CoursePO;

public interface CourseBLService{
	public CoursePO[] showAllCourse();
	public CoursePO showCourseById(String courseId);
	public void courseSharedXML() throws IOException ;
	public void courseXML() throws IOException ;
}
