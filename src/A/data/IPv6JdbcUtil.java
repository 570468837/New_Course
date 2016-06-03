package A.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author qyf
 *
 */
public class IPv6JdbcUtil {
	public static void main(String[] args) {
		try {
			boolean ipv6 = true;
//			testDb2Connection(ipv6);
//			testMySQLConnection(ipv6);
//			testOracleConnection(ipv6);
			testSqlServerConnection(ipv6);
//			testSybaseConnection(ipv6);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *	ipv4 Driver URL: 
	 *		jdbc:mysql://127.0.0.1:3306/database
	 *	ipv6 Driver URL:
	 *		jdbc:mysql://address=(protocol=tcp)(host=2001:470:23:13::6)(port=3306)/database 
	 *
	 *  Deiver package version 5.1.31 以上
	 *  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */
	public static void testMySQLConnection(boolean ipv6) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String jdbcIpv4Url = "jdbc:mysql://127.0.0.1:3306/databaseName";
		String jdbcIpv6Url = "jdbc:mysql://address=(protocol=tcp)(host=2001:470:23:13::6)(port=3306)/database";
		String jdbcUrl = ipv6 ? jdbcIpv6Url : jdbcIpv4Url;
		Properties jdbcProperties = new Properties();
		jdbcProperties.put("user", "dbuser");
		jdbcProperties.put("password", "db_password");
		Connection connection = DriverManager.getConnection(jdbcUrl);
		System.out.println(connection);
	}
	
	/**
	 * jdbc:db2://[fec0:ffff:ffff:8000:20e:cff:fe50:39c8]:50000/sample
	 * jdbc:db2://192.168.10.10:50000/sample
	 * 
	 *  Deiver package version 
	 * <dependency>
     *  <groupId>com.ibm.db2</groupId>
     *  <artifactId>db2jcc4</artifactId>
     *  <version>10.1</version>
     * </dependency>
	 */
	public static void testDb2Connection(boolean ipv6) throws ClassNotFoundException, SQLException {
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		String jdbcIpv4Url = "jdbc:db2://192.168.10.10:50000/sample";
		String jdbcIpv6Url = "jdbc:db2://[2001:470:23:13::6]:50000/sample";
		String jdbcUrl = ipv6 ? jdbcIpv6Url : jdbcIpv4Url;
		Properties jdbcProperties = new Properties();
		jdbcProperties.put("user", "dbuser");
		jdbcProperties.put("password", "db_password");
		Connection connection = DriverManager.getConnection(jdbcUrl);
		System.out.println(connection);
	}
	
	/**
	 * 
	 * ipv4 Driver URL: 
	 * 		jdbc:oracle:thin:@//127.0.0.1:3306/orcl 
	 * 
	 * ipv6 Driver URL:
	 * 		jdbc:oracle:thin:@(DESCRIPTION=
	  			(ADDRESS=(PROTOCOL=tcp)(HOST=[fe80::5cf:72])(PORT=1521))
	  			(CONNECT_DATA=(SERVICE_NAME=fnstdb1)))
	 * 
	 * @see http://stackoverflow.com/questions/10647845/does-oracle-11gr2-actually-support-ipv6
	 * 
	 *  Deiver package version 
	 *  <dependency>
     *   <groupId>com.oracle</groupId>
     *   <artifactId>ojdbc14</artifactId>
     *   <version>10.2.0.3.0</version>
     *  </dependency>
	 */
	public static void testOracleConnection(boolean ipv6) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		String jdbcIpv4Url = "jdbc:oracle:thin:@//127.0.0.1:3306/orcl";
		String jdbcIpv6Url = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=[fe80::5cf:72])(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=fnstdb1)))";
		String jdbcUrl = ipv6 ? jdbcIpv6Url : jdbcIpv4Url;
		Properties jdbcProperties = new Properties();
		jdbcProperties.put("user", "dbuser");
		jdbcProperties.put("password", "db_password");
		Connection connection = DriverManager.getConnection(jdbcUrl);
		System.out.println(connection);
	}
	
	/**
	 *	ipv4 Driver URL: 
	 *		jdbc:jtds:sqlserver://127.0.0.1:1433/master
	 *	ipv6 Driver URL:
	 *		jdbc:jtds:sqlserver://
	 *
	 *	Deiver package version 
	 * <dependency>
	 * 	<groupId>net.sourceforge.jtds</groupId>
	 * 	<artifactId>jtds</artifactId>	
	 * 	<version>1.2.4</version>
	 * </dependency>
	 *
	 */
	public static void testSqlServerConnection(boolean ipv6) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String jdbcIpv4Url = "jdbc:jtds:sqlserver://127.0.0.1:1433/master";
		String jdbcIpv6Url = "jdbc:jtds:sqlserver://";
		String jdbcUrl = ipv6 ? jdbcIpv6Url : jdbcIpv4Url;
		Properties jdbcProperties = new Properties();
		jdbcProperties.put("user", "dbuser");
		jdbcProperties.put("password", "db_password");
		if (ipv6) {
			jdbcProperties.put("portNumber", 1433);
			jdbcProperties.put("instanceName ", "master");
			jdbcProperties.put("serverName", "2001:470:23:13::6");
		}
		Connection connection = DriverManager.getConnection(jdbcUrl);
		System.out.println(connection);
	}
	
	/**
	 *	ipv4 Driver URL: 
	 *		jdbc:jtds:sybase://127.0.0.1:1433/master
	 *	ipv6 Driver URL:
	 *		jdbc:jtds:sybase://
	 *
	 *	Deiver package version 
	 * <dependency>
	 * 	<groupId>net.sourceforge.jtds</groupId>
	 * 	<artifactId>jtds</artifactId>	
	 * 	<version>1.2.4</version>
	 * </dependency>
	 *
	 */
	public static void testSybaseConnection(boolean ipv6) throws ClassNotFoundException, SQLException {
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		String jdbcIpv4Url = "jdbc:jtds:sybase://127.0.0.1:1433/master";
		String jdbcIpv6Url = "jdbc:jtds:sybase://";
		String jdbcUrl = ipv6 ? jdbcIpv6Url : jdbcIpv4Url;
		Properties jdbcProperties = new Properties();
		jdbcProperties.put("user", "dbuser");
		jdbcProperties.put("password", "db_password");
		if (ipv6) {
			jdbcProperties.put("portNumber", 1433);
			jdbcProperties.put("instanceName ", "master");
			jdbcProperties.put("serverName", "2001:470:23:13::6");
		}
		Connection connection = DriverManager.getConnection(jdbcUrl);
		System.out.println(connection);
	}
}
