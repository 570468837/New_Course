package C.data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;

import C.dataservice.StudentDataService;
import C.po.CoursePO;
import C.po.StudentPO;

public class StudentData   extends UnicastRemoteObject implements StudentDataService{


	public  StudentData() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	static String sql = null;  
    static DBHelper db1 = null;  
    static ResultSet ret = null; 
    static String insql = null;
    public int count() throws RemoteException{
    	sql = "select count(Sno) from student";
    	
    	
    	db1 = new DBHelper(sql);
    	int count = 0;
    	try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {  
            	count = ret.getInt(1);
               
            }//显示数据  
//            System.out.println(count);
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    	return count;
    }

    public StudentPO[] selectAll()throws RemoteException{
    	int count = 0;
    	count = count();
    	sql = "select * from student";
    	db1 = new DBHelper(sql);
    	StudentPO[] po = new StudentPO[count];
    	int i = 0;
    	try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {  
            	 po[i] = new StudentPO(ret.getString(1),ret.getString(2),ret.getString(3),ret.getString(4),ret.getString(5));
                System.out.println(po[i].getSno());  
                i++;
            }//显示数据  
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    	
    	return po;
    	
    }
    public StudentPO selectById(String sno)throws RemoteException, SQLException{
    	sql = "select * from student where Sno=?";
    	db1 = new DBHelper(sql);
    	db1.pst.setString(1,sno );
    	StudentPO po = null;
    	
    	try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {  
            	 po = new StudentPO(ret.getString(1),ret.getString(2), ret.getString(3), ret.getString(4), ret.getString(5));

                
            }//显示数据  
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    	
    	return po;
    	
    }
    
	public static void main(String[] args) throws RemoteException, SQLException {
		StudentData sd = new StudentData();
		sd.selectAll();
//		System.out.println(sd.selectById("001").getSnm());
	}	
	
	
	
}

