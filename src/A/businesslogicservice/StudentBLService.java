package A.businesslogicservice;


import java.util.ArrayList;

import A.po.CoursePO;
import A.po.StudentPO;

public interface StudentBLService{

	//学生统计
	public ArrayList<StudentPO> showAllStudent();
	public StudentPO showStudentById(String studentId);
	public boolean addStudent(StudentPO spo);
}
