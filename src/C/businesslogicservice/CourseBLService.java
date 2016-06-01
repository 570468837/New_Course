package C.businesslogicservice;

import java.rmi.Remote;

import C.po.CoursePO;

public interface CourseBLService{
	public CoursePO[] showAllCourse();
	public CoursePO showCourseById(String courseId);
}
