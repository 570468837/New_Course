package A.data;

import java.sql.*;

public class testQuery {
		public static void main(String[] args) {
				DBHelper dbh=new DBHelper();
				Connection conn = dbh.getConnection();
				PreparedStatement ps = null;
				ResultSet rs = null;
				String sql="select * from student";
				try {
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					while (rs.next()) {// 判断是否还有下一个数据
		                 System.out.println("ID：" + rs.getString("学号") + "\tNAME:"
		                         + rs.getString("姓名"));
						             }
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		}
}
