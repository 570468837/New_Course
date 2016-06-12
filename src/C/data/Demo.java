package C.data;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import C.po.AccountPO;
import C.po.CoursePO;
import C.po.CourseSelectionPO;
import C.po.StudentPO;  
 /*
  * 这就是一个各种用来测试的代码啦 并不是项目里的什么。。。不要在意*/ 
public class Demo {  
  
    static String sql = null;  
    static DBHelper db1 = null;  
    static ResultSet ret = null; 
    static String insql = null;
  
    public  void getTables() {  
        sql = "show tables";//SQL语句  
        db1 = new DBHelper(sql);//创建DBHelper对象  
  
        try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {  
                 
                System.out.println(ret.getString(1));  
            }//显示数据  
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    } 
    //插入学生记录
    public boolean insertStudent(StudentPO student){
    	insql = "insert into student(Sno,Snm,Sex,Sde,Pwd) values(?,?,?,?,?)";
    	 db1 = new DBHelper(insql);
    	 
    	 String sno=student.getSno();
    	 String snm = student.getSnm();
    	 String sex = student.getSex();
    	 String sde = student.getSde();
    	 String pwd = student.getPwd();
    	 
    	 try {
			db1.pst.setString(1, sno);
			db1.pst.setString(2, snm);
			db1.pst.setString(3, sex);
	    	db1.pst.setString(4, sde);
	    	db1.pst.setString(5, pwd);
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
    //插入账户记录
    public boolean insertAccount(AccountPO account){
    	insql = "insert into account(acc,passwd,CreateDate) values(?,?,?)";
    	 db1 = new DBHelper(insql);
    	 
    	 String acc = account.getAcc();
    	 String passwd = account.getPasswd();
    	 long createDate = account.getCreateDate();
    	 
    	 
    	 try {
			db1.pst.setString(1, acc);
			db1.pst.setString(2, passwd);
			db1.pst.setLong(3, createDate);
	    	
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
    //插入课程记录
    public boolean insertCourse(CoursePO course){
    	insql = "insert into course(Cno,Cnm,Ctm,Cpt,Tec,Pla,Share) values(?,?,?,?,?,?,?)";
    	 db1 = new DBHelper(insql);
    	 
    	 String cno = course.getCno();
    	 String cnm = course.getCnm();
    	 int ctm = course.getCtm();
    	 int cpt = course.getCpt();
    	 String tec = course.getTec();
    	 String pla = course.getPla();
    	 String share = course.getShare();
    	 
    
    	 try {
			db1.pst.setString(1, cno);
			db1.pst.setString(2, cnm);
			db1.pst.setInt(3, ctm);
	    	db1.pst.setInt(4, cpt);
	    	db1.pst.setString(5, tec);
	    	db1.pst.setString(6, pla);
	    	db1.pst.setString(7, share);
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
    
    //插入选课记录
    public boolean insertCourseSelection(CourseSelectionPO courseSelection){
    	insql = "insert into courseSelection(Cno,Sno,Grd) values(?,?,?)";
    	 db1 = new DBHelper(insql);
    	 
    	String cno = courseSelection.getCno();
    	String sno = courseSelection.getSno();
    	int grd = courseSelection.getGrd();
    	 
    
    	 try {
			db1.pst.setString(1, cno);
			db1.pst.setString(2, sno);
			db1.pst.setInt(3, grd);
	  
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
    
    public void selectall(){
    	sql = "select * from account";
    	db1 = new DBHelper(sql);
    	AccountPO[] po = new AccountPO[50];
    
    	int i = 0;
    	try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {  
                  po[i] = new AccountPO(ret.getString(1),ret.getString(2),0);

                System.out.println(ret.getString(2));  
                i++;
                System.out.println(i);
            }//显示数据  
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    	
    	
    }
    public void studentXML() throws IOException{
    	sql = "select * from student";
    	db1 = new DBHelper(sql);
    	
    
    
    	try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集 
            ResultSetMetaData rsmd = ret.getMetaData();
            int count = rsmd.getColumnCount();
            String[] columnName = new String[count];
            for(int j=1;j<=count;j++){
            	columnName[j-1] = rsmd.getColumnName(j);
            }
            Document doc = DocumentHelper.createDocument();
            Element root = doc.addElement("Students");
            
            while(ret.next()){
            	Element emp = root.addElement("student");
            	for(int j=1;j<=count;j++){
            		Element column = emp.addElement(columnName[j-1]);
            		if(ret.getObject(j)!=null){
            			column.setText(ret.getObject(j)+"");
            		}else{
            			column.setText("");
            		}
            	}
            
            	
            }
            
            Writer w = new FileWriter("student.xml");	
            System.out.println("success");
            OutputFormat opf = OutputFormat.createPrettyPrint();
            opf.setEncoding("GB2312");
            XMLWriter xw = new XMLWriter(w,opf);
            xw.write(doc);
            xw.close();
            w.close();
            
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    	
    	
    }
    public StudentPO getStudent(){
    	StudentPO s = new StudentPO("000000001","abcd","F","SE","1234");
    	return s;
    }
    public boolean selectCourse(AccountPO a,CoursePO c){
    	CourseSelectionPO cs = new CourseSelectionPO(null, null, 0);
    	String acc = a.getAcc();
    	String cno = c.getCno();
    	sql = "insert into courseSelection(Cno,Sno,Grd) values (?,?,?)";
    	 db1 = new DBHelper(sql);
    	 
    	 
     	 try {
 			db1.pst.setString(1, cno);
 			db1.pst.setString(2,acc);
 			db1.pst.setInt(3,0);
 			
 	  
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
    
    public static void main(String[] args) throws IOException{
    	Demo demo = new Demo();
//    	demo.getTables();
    	StudentPO s = new StudentPO("1","abcd","F","SE","1234");
    	System.out.println(demo.insertStudent(s));
    	AccountPO a = new AccountPO("2","2333",20160708);
//    	System.out.println(demo.insertAccount(a));
    	CoursePO c = new CoursePO("0111","一二",90,3,"David","110","1");
//    	System.out.println(demo.insertCourse(c));
    	CourseSelectionPO cs = new CourseSelectionPO("0101","12",90);
//    	System.out.println(demo.insertCourseSelection(cs));
//    	demo.selectall();
//    	demo.selectCourse(a, c);
    	demo.insertCourse(c);
//    	demo.studentXML();
    	
    }
}  