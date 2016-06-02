package C.data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;

import C.dataservice.CourseSelectionDataService;
import C.dataservice.UserDataService;
import C.po.AccountPO;
import C.po.CoursePO;
import C.po.CourseSelectionPO;
import C.po.StudentPO;

public class CourseSelectionData  extends UnicastRemoteObject implements CourseSelectionDataService{
	public CourseSelectionData() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static String sql = null;  
    static DBHelper db1 = null;  
    static ResultSet ret = null; 
    static String insql = null;
    static String sql2 = null;
    public boolean selectOrnot(StudentPO s,CoursePO c){
    	CourseSelectionPO cs = new CourseSelectionPO(null, null, 0);
    	String sno = s.getSno();
    	String cno = c.getCno();
    	sql2 = "select * from courseSelection ";
    	 db1 = new DBHelper(sql2);
    	try {
			ResultSet rs = db1.pst.executeQuery();
			while (rs.next()){
//				System.out.println(acc);
//				System.out.println(rs.getString(1));
//				System.out.println(acc.equals(rs.getString(1)));
				if(sno.equals(rs.getString(2))){
//					System.out.println("ww");
					if(cno.equals(rs.getString(1))){
						
						return true;
					}
				}
//				System.out.println("lalala");
//				System.out.println(rs.getString(1));
//				System.out.println(rs.getString(2));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	return false;
    }
	public boolean selectCourse(StudentPO s,CoursePO c){
		boolean selectOrnot = selectOrnot(s,c);
		if(selectOrnot == true){
			System.out.println("已选过这门课");
		}else{
		CourseSelectionPO cs = new CourseSelectionPO(null, null, 0);
    	String sno = s.getSno();
    	String cno = c.getCno();
    	
    	sql = "insert into courseSelection(Cno,Sno,Grd) values (?,?,?)";
    	 db1 = new DBHelper(sql);
    	 
     	 try {
 			db1.pst.setString(1, cno);
 			db1.pst.setString(2,sno);
 			db1.pst.setInt(3,0);
 			
 	  
 	    	 int result = db1.pst.executeUpdate();
 	    	 if(result>0){
 	    		 return true;
 	    	 }
 	    	 System.out.println("result"+result);
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
     	 }
     	 return false;
    	
	}
	public boolean quitCourse(StudentPO s,CoursePO c){
		boolean selectOrnot = selectOrnot(s,c);
		CourseSelectionPO cs = new CourseSelectionPO(null, null, 0);
    	String sno = s.getSno();
    	String cno = c.getCno();
    	
    	sql = "delete from courseSelection where Cno=? and Sno=?";
    	 db1 = new DBHelper(sql);
    	 try {
			db1.pst.setString(1, cno);
			db1.pst.setString(2, sno);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	 
     	 try {
 			
 			
 	  
 	    	 int result = db1.pst.executeUpdate();
 	    	 if(result>0){
 	    		 return true;
 	    	 }
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
     	 
     	 return false;
    	
	}
	public CoursePO[] selectAllCourse()throws RemoteException{
    	sql = "select * from course";
    	db1 = new DBHelper(sql);
    	CoursePO[] po = new CoursePO[10];
    	int i = 0;
    	try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {  
            	//这边不全哦
            	 po[i] = new CoursePO(ret.getString(1), ret.getString(2), 0, 0, null, null, null);
                System.out.println(po[i].getCno());  
                i++;
            }//显示数据  
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    	
    	return po;
    	
    }
	
	public AccountPO[] selectAllAccount()throws RemoteException{
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


	
	public static void main(String[] args) {
		try {
			CourseSelectionData cs = new CourseSelectionData();
			StudentPO s = new StudentPO("6", "sss", "sss", "ww", "m");
//	    	System.out.println(demo.insertAccount(a));
	    	CoursePO c = new CoursePO("1","adf",20,3,"ella","203","1");
// 	    	cs.selectAllCourse();
//			cs.selectAllAccount();
//			StudentPO spo = new StudentPO("2",null,null,null,null);
//			cs.selectMyCourse(spo);
//	    	System.out.println(cs.quitCourse(a, c));
	    	cs.selectCourse(s, c);
	    	
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public CoursePO[] selectMyCourse(StudentPO spo) {
		// TODO Auto-generated method stub
		
		sql = "select * from courseSelection where Sno=?";
    	db1 = new DBHelper(sql);
    	try {
			db1.pst.setString(1, spo.getSno());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	CoursePO[] po = new CoursePO[10];
    	int i = 0;
    	try {  
    		
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            
            while (ret.next()) {  
            	//这边不全哦
            	 po[i] = new CoursePO(ret.getString(1), ret.getString(2), 0, 0, null, null, null);
                System.out.println(po[i].getCno());  
                i++;
            }//显示数据  
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    	
    	return po;
	}

}
