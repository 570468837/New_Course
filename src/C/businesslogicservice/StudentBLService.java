package C.businesslogicservice;

import java.rmi.Remote;

import C.po.CoursePO;
import C.po.StudentPO;

public interface StudentBLService{

	//学生统计
	public void showAllStudent();
	public StudentPO showStudentById(String studentId);
}
