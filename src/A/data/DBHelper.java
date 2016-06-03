package A.data;

import java.sql.*;
import java.util.Properties;

public class DBHelper {
	static Connection dbConn = null;
	
	public static Connection  getConnection() {
	
	  String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  //加载JDBC驱动
	  String dbURL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=电子学院";  //连接服务器和数据库test
	  String userName = "sa";  //默认用户名
	  String userPwd = "0";  //密码
	  
	  try {
	   Class.forName(driverName);
	   dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
	//  System.out.println("Connection Successful!");  //如果连接成功 控制台输出Connection Successful!
	  } catch (Exception e) {
		  
	   e.printStackTrace();
	  }
	  return dbConn;
	}
}
