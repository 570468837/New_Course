package A.data;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import common.FileInformation;
import common.FileInformationSev;

public class DatabaseToXML {
	    static String sql = null;  
	    static DBHelper dbh = null;  
	    static ResultSet ret = null; 
	    static String insql = null;
	    static String  tableName = null;
		private static String path ="./AFiles/A_XML/A_" ;
		
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
	    	sql = "select * from selection";
	    	tableName = "selection";
	    	toXML(sql,tableName);	
	    }
	    public void courseShareXML() throws IOException {
	    	sql = "select * from course where 共享=1 and 课程编号 like '03%'";
	    	tableName = "courseShare";
	    	toXML(sql,tableName);	
	    }
	    
	    public void toXML(String sql,String tableName) throws IOException{
	    	dbh = new DBHelper();
			Connection conn = dbh.getConnection();
	    	try {  
	    		PreparedStatement ps = conn.prepareStatement(sql);
				ret = ps.executeQuery();
	            ResultSetMetaData rsmd = ret.getMetaData();
	            int count = rsmd.getColumnCount();
	            String[] columnName = new String[count];
	            for(int j=1;j<=count;j++){
	            	columnName[j-1] = rsmd.getColumnName(j);
	            }
	            Document doc = DocumentHelper.createDocument();
	            if(tableName.equals("selection")){
	            	Element root = doc.addElement("course"+"s");
		            
		            while(ret.next()){
		            	Element emp = root.addElement("course");
		            	for(int j=1;j<=count;j++){
		            		Element column = emp.addElement(columnName[j-1]);
		            		if(ret.getObject(j)!=null){
		            			column.setText(ret.getObject(j)+"");
		            		}else{
		            			column.setText("");
		            		}
		            	}
		            
		            	
		            }
	            }else{
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
	            }
	            Writer w = new FileWriter("./AFiles/A_XML/A_"+tableName+".xml");	
	            System.out.println("success");
	            OutputFormat opf = OutputFormat.createPrettyPrint();
	            opf.setEncoding("utf8");
	            XMLWriter xw = new XMLWriter(w,opf);
	            xw.write(doc);
	            xw.close();
	            w.close();
	            
	            ret.close();  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	    	
	    	
	    }
	    public FileInformation getFileInformation(String fileName) {
			FileInformation fileInfo = new FileInformationSev() ;
			
			File file = new File(path+fileName+".xml") ;
			if(!file.exists()){
				System.out.println("xml文件不存在");
			}
			
			byte[] content = new byte[(int)file.length()] ;
			
			BufferedInputStream input = null ;
			try {
				input =  new BufferedInputStream(new FileInputStream(file)) ;
				input.read(content) ;
				fileInfo.setInformation(fileName+".xml", content);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(input!=null){
					try {
						input.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
			return fileInfo ;
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

