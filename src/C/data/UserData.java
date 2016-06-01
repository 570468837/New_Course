package C.data;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.util.ArrayList;

import C.dataservice.UserDataService;
import C.po.AccountPO;
import C.po.CoursePO;
import C.po.CourseSelectionPO;
import C.po.StudentPO;

import java.sql.ResultSet;  
import java.sql.SQLException;  
  


public class UserData  extends UnicastRemoteObject implements UserDataService{


	public  UserData() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	static String sql = null;  
    static DBHelper db1 = null;  
    static ResultSet ret = null; 
    static String insql = null;


    public AccountPO[] selectAll()throws RemoteException{
    	sql = "select * from account";
    	db1 = new DBHelper(sql);
    	AccountPO[] po = new AccountPO[50];
    	int i = 0;
    	try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {  
            	 po[i] = new AccountPO(ret.getString(1),ret.getString(2),0);
                System.out.println(po[i].getAcc());  
                i++;
            }//显示数据  
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    	
    	return po;
    	
    }

    
	public static void main(String[] args) throws RemoteException {
		UserData ud = new UserData();
		ud.selectAll();
	}	
	
	
	
}
