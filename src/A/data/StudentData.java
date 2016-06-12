package A.data;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import A.dataservice.StudentDataService;
import A.po.StudentPO;
import A.po.StudentPO;

public class StudentData implements StudentDataService {
	static String sql = null;  
    static DBHelper dbh = null;  
    static ResultSet rs = null; 
    PreparedStatement ps=null;
    ArrayList<StudentPO> poList = new ArrayList<StudentPO>();
	@Override
	public ArrayList<StudentPO> selectAll() throws RemoteException {
		// TODO Auto-generated method stub
		sql = "select * from student";
		dbh=new DBHelper();
		Connection conn = dbh.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {// 判断是否还有下一个数据
				StudentPO spo = new StudentPO(rs.getString(1),rs.getString(2),rs.getString(3)
						,rs.getString(4),rs.getString(5));
				poList.add(spo);
			}
			rs.close();  
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return poList;
	}

	@Override
	public StudentPO selectById(String sno) throws RemoteException,
			SQLException {
		// TODO Auto-generated method stub
		sql = "select * from student where 学号='"+sno+"'";
		dbh=new DBHelper();
		Connection conn = dbh.getConnection();
		StudentPO po = null;
    	try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {// 判断是否还有下一个数据
				po = new StudentPO(rs.getString(1),rs.getString(2),rs.getString(3)
						,rs.getString(4),rs.getString(5));
			}
			rs.close();  
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return po;
	}
	
	public StudentPO selectByAcc(String acc) throws RemoteException,SQLException {
	// TODO Auto-generated method stub
		sql = "select * from student where 关联账户='"+acc+"'";
		dbh=new DBHelper();
		Connection conn = dbh.getConnection();
		StudentPO po = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {// 判断是否还有下一个数据
				po = new StudentPO(rs.getString(1),rs.getString(2),rs.getString(3)
						,rs.getString(4),rs.getString(5));
			}
			rs.close();  
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
			return po;
	}

	@Override
	public boolean add(StudentPO spo) {
		// TODO Auto-generated method stub
		boolean result=false;
		DBHelper dbh=new DBHelper();
		Connection conn = dbh.getConnection();
		
		String id = spo.getSno();
		String name = spo.getSnm();
		String gender = spo.getSex();
		String depart = spo.getSde();
		
		String sql = "insert into student values('"+id+"','"+name+"','"+gender+"','"+depart+"',NULL)";
		try {
			Statement select = conn.createStatement();
			int count = select.executeUpdate(sql);
			if(count==1)
				result= true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
