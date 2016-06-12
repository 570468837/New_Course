package A.businesslogic;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import A.businesslogicservice.StudentBLService;
import A.data.StudentData;
import A.dataservice.StudentDataService;
import A.po.StudentPO;

public class StudentBL implements StudentBLService {
	StudentDataService sds;
	@Override
	public ArrayList<StudentPO> showAllStudent() {
		// TODO Auto-generated method stub
		sds = new StudentData();
		try {
			return sds.selectAll();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public StudentPO showStudentById(String studentId) {
		// TODO Auto-generated method stub
		sds = new StudentData();
		try {
			return sds.selectById(studentId);
		} catch (RemoteException|SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addStudent(StudentPO spo) {
		// TODO Auto-generated method stub
		sds = new StudentData();
		return sds.add(spo);
	}

}
