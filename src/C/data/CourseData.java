package C.data;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.util.ArrayList;

import C.dataservice.CourseDataService;

import C.po.AccountPO;
import C.po.CoursePO;
import C.po.CourseSelectionPO;
import C.po.StudentPO;

import java.sql.ResultSet;  
import java.sql.SQLException; 
public class CourseData   extends UnicastRemoteObject implements CourseDataService{


	public  CourseData() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	static String sql = null;  
    static DBHelper db1 = null;  
    static ResultSet ret = null; 
    static String insql = null;


    public CoursePO[] selectAll()throws RemoteException{
    	sql = "select * from course";
    	db1 = new DBHelper(sql);
    	CoursePO[] po = new CoursePO[50];
    	int i = 0;
    	try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {  
            	 po[i] = new CoursePO(ret.getString(1),ret.getString(2),ret.getInt(3), ret.getInt(4), ret.getString(5), ret.getString(6), ret.getString(7));
//                System.out.println(po[i].getCno());  
                i++;
            }//显示数据  
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    	
    	return po;
    	
    }

    public CoursePO selectById(String cno)throws RemoteException, SQLException{
    	sql = "select * from course where Cno=?";
    	db1 = new DBHelper(sql);
    	db1.pst.setString(1,cno );
    	CoursePO po = null;
    	
    	try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {  
            	 po = new CoursePO(ret.getString(1),ret.getString(2),ret.getInt(3), ret.getInt(4), ret.getString(5), ret.getString(6), ret.getString(7));

                
            }//显示数据  
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    	
    	return po;
    	
    }
    
	public static void main(String[] args) throws RemoteException, SQLException {
		CourseData cd = new CourseData();
		cd.selectAll();
		
		System.out.println(cd.selectById("0102").getCnm());
	}	
	
	
	
}
