package A.data;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import A.dataservice.CourseSelectionDataService;
import A.po.AccountPO;
import A.po.CoursePO;
import A.po.StudentPO;

public class CourseSelectionData implements CourseSelectionDataService {
	static String sql = null;  
    static DBHelper dbh = null;  
    static ResultSet rs = null; 
    PreparedStatement ps=null;
    static String inSql = null;
    CourseData cd = new CourseData();
    @Override
	public boolean selectCourse(StudentPO s, CoursePO c) throws RemoteException {
		// TODO Auto-generated method stub
		dbh=new DBHelper();
		Connection conn = dbh.getConnection();
		
		inSql = "insert into [select] values ('"+c.getCno()+"','"+s.getSno()+"','')";
		try {
			ps=conn.prepareStatement(inSql);
			int i = ps.executeUpdate();
			if(i==1)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public ArrayList<CoursePO> selectAllCourse() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AccountPO> selectAllAccount() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CoursePO> selectMyCourse(StudentPO spo) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<CoursePO> poList = new ArrayList<CoursePO>();
		dbh=new DBHelper();
		Connection conn = dbh.getConnection();
		String[] cpo=new String[50];
		int j=0;
		inSql = "select 课程编号 from [select] where 学生编号 ="+spo.getSno();
		try {
			ps=conn.prepareStatement(inSql);
			rs=ps.executeQuery();
			while(rs.next()){
				cpo[j] = rs.getString(1);
						j++;}
			rs.close();  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CoursePO[] cpos = new CoursePO[50];
		for(int i=0;i<50;i++){
			if(!cpo[i].equals(null))
				try {
					poList.add(cd.selectById(cpo[i]));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		}
		
		return poList;
	}

	@Override
	public boolean quitCourse(StudentPO s, CoursePO c) throws RemoteException {
		// TODO Auto-generated method stub
		dbh=new DBHelper();
		Connection conn = dbh.getConnection();
		
		inSql = "delete from [select] where 课程编号 = "+c.getCno()+"and 学生编号="+s.getSno();
		try {
			ps=conn.prepareStatement(inSql);
			int i = ps.executeUpdate();
			if(i==1)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}


}
