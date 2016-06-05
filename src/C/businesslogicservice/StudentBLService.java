package C.businesslogicservice;

import java.io.IOException;
import java.rmi.Remote;

import C.po.CoursePO;
import C.po.StudentPO;

public interface StudentBLService{

	//学生统计
	public void showAllStudent();
	public StudentPO showStudentById(String studentId);
	public void studentXML() throws IOException;
}
