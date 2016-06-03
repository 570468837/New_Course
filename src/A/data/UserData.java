package A.data;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import A.dataservice.UserDataService;
import A.po.AccountPO;
import A.po.CoursePO;

public class UserData implements UserDataService {
	static String sql = null;  
    static DBHelper dbh = null;  
    static ResultSet rs = null; 
    PreparedStatement ps=null;
    ArrayList<AccountPO> poList=new ArrayList<AccountPO>();
	@Override
	public ArrayList<AccountPO> selectAll() throws RemoteException {
		// TODO Auto-generated method stub
		sql = "select * from account";
		dbh=new DBHelper();
		Connection conn = dbh.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {// 判断是否还有下一个数据
				AccountPO apo = new AccountPO(rs.getString(1),rs.getString(2),rs.getString(3));
				poList.add(apo);
			}
			rs.close();  
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return poList;
	}
	@Override
	public AccountPO selectById(String id) {
		// TODO Auto-generated method stub
		sql = "select * from account where 账户名="+id;
		dbh=new DBHelper();
		AccountPO apo=null;
		Connection conn = dbh.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {// 判断是否还有下一个数据
				apo = new AccountPO(rs.getString(1),rs.getString(2),rs.getString(3));
			}
			rs.close();  
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return apo;
	}

}
