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

public class DatabaseToXML {
	  static String sql = null;  
	    static DBHelper db1 = null;  
	    static ResultSet ret = null; 
	    static String insql = null;
	    static String  tableName = null;
	  
	    public void accountXML() throws IOException{
	    	sql = "select * from account";
	    	tableName = "account";
	    	toXML(sql,tableName);	
	    }
	    public void studentXML() throws IOException{
	    	sql = "select * from student";
	    	tableName = "student";
	    	toXML(sql,tableName);	
	    }
	    public void courseXML() throws IOException{
	    	sql = "select * from course";
	    	tableName = "course";
	    	toXML(sql,tableName);	
	    }
	    public void courseSelectionXML() throws IOException{
	    	sql = "select * from courseSelection";
	    	tableName = "courseSelection";
	    	toXML(sql,tableName);	
	    }
	    public void courseShareXML() throws IOException {
	    	sql = "select * from course where Share=1";
	    	tableName = "courseShare";
	    	toXML(sql,tableName);	
	    }
	    
	    public void toXML(String sql,String tableName) throws IOException{
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
	            Element root = doc.addElement(tableName+"s");
	            
	            while(ret.next()){
	            	Element emp = root.addElement(tableName);
	            	for(int j=1;j<=count;j++){
	            		Element column = emp.addElement(columnName[j-1]);
	            		if(ret.getObject(j)!=null){
	            			column.setText(ret.getObject(j)+"");
	            		}else{
	            			column.setText("");
	            		}
	            	}
	            
	            	
	            }
	            
	            Writer w = new FileWriter("C."+tableName+".xml");	
	            System.out.println("success");
	            OutputFormat opf = OutputFormat.createPrettyPrint();
	            opf.setEncoding("utf8");
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
	    public static void main(String[] args) throws IOException {
			DatabaseToXML dtx = new DatabaseToXML();
			dtx.studentXML();
			dtx.accountXML();
			dtx.courseXML();
			dtx.courseSelectionXML();
			dtx.courseShareXML();
		}
}

