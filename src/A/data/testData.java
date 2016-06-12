package A.data;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import A.po.AccountPO;
import A.po.CoursePO;
import A.po.StudentPO;

public class testData {
public static void main(String[] args) throws RemoteException, SQLException {
	StudentData cd = new StudentData();
	ArrayList<StudentPO> s=cd.selectAll();
	for(StudentPO c:s)
		System.out.println(c.getSno());
	StudentPO C =cd.selectById("0301");
	System.out.println(C.getSnm());
	UserData ac = new UserData();
	CourseData cs = new CourseData();
	CoursePO p = cs.selectById("03001");
	ArrayList<AccountPO> a = ac.selectAll();
	for(AccountPO as:a)
		System.out.println(as.getAcc());
	CourseSelectionData csd = new CourseSelectionData();
	
//	System.out.print(csd.selectCourse(C, p));
	System.out.print(csd.quitCourse(C, p));
}
}
