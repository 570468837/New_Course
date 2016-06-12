package A.data;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import A.dataservice.CourseDataService;
import A.po.CoursePO;
import A.data.DBHelper;

public class CourseData implements CourseDataService {

	static String sql = null;  
    static DBHelper dbh = null;  
    static ResultSet rs = null; 
    PreparedStatement ps=null;
    ArrayList<CoursePO> poList = new ArrayList<CoursePO>();
	@Override
	public ArrayList<CoursePO> selectAll() {
		// TODO Auto-generated method stub
		sql = "select * from course";
		dbh=new DBHelper();
		Connection conn = dbh.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {// 判断是否还有下一个数据
				CoursePO cpo= new CoursePO(rs.getString(1),rs.getString(2),Integer.parseInt(rs.getString(3))
						,rs.getString(4),rs.getString(5),Integer.parseInt(rs.getString(6)));
				poList.add(cpo);
			}
			rs.close();  
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return poList;
	}

	@Override
	public CoursePO selectById(String cno) throws SQLException {
		// TODO Auto-generated method stub
		sql = "select * from course where 课程编号="+cno;
		dbh=new DBHelper();
		Connection conn = dbh.getConnection();
		CoursePO po = null;
    	try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {// 判断是否还有下一个数据
				po = new CoursePO(rs.getString(1),rs.getString(2),Integer.parseInt(rs.getString(3))
						,rs.getString(4),rs.getString(5),Integer.parseInt(rs.getString(6)));
			}
			rs.close();  
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return po;
	}

	@Override
	public boolean add(CoursePO cpo) {
		// TODO Auto-generated method stub
		boolean result=false;
		DBHelper dbh=new DBHelper();
		Connection conn = dbh.getConnection();
		
		String id = cpo.getCno();
		String name = cpo.getCnm();
		int point = cpo.getCpt();
		String teacher = cpo.getTec();
		String place = cpo.getPla();
		int share = 1;
		
		String sql = "insert into course values('"+id+"','"+name+"','"+point+"','"+teacher+"','"+place+"','"+share+"')";
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
