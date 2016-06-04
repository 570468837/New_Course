package B.DataService;

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
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

import common.FileInformation;
import common.FileInformationSev;

public class IOHelper {
	private static String path ="./BFiles/B." ;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void createXMLFile(String sql, String elementName){
		// TODO Auto-generated method stub
		
		//获取ResultSet
		Session session = HibernateUtils.getSession() ;;
		ResultSet result = (ResultSet) session.doReturningWork(new ReturningWork(){
			@Override
			public Object execute(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps = con.prepareStatement(sql) ;
				return ps.executeQuery();
			}
		}) ;
		System.out.println("获得resultSet");
		//生成Document
		Document doc = null ;
		try {
			ResultSetMetaData rsmd = result.getMetaData();
			int count = rsmd.getColumnCount() ;
			String[] columnNames = new String[count] ;
			for(int i=0;i<count;i++){
				columnNames[i] = rsmd.getColumnName(1+i) ;
			}
			doc = DocumentHelper.createDocument() ;
			Element root = doc.addElement(elementName.toUpperCase()+"S") ;
			while(result.next()){
				Element emp = root.addElement(elementName) ;
				for(int i=0;i<count;i++){
					Element column = emp.addElement(columnNames[i]) ;
					if(result.getObject(i+1)!=null)
						column.setText(result.getObject(i+1)+"");
					else
						column.setText("");;
				}
			}
			System.out.println("获得doc");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//生成xml文件
		try {
			Writer w = new FileWriter(path+elementName.toUpperCase()+"S.xml") ;
			OutputFormat opf = OutputFormat.createPrettyPrint() ;
			opf.setEncoding("UTF-8");
			XMLWriter xw = new XMLWriter(w,opf) ;
			xw.write(doc);
			xw.close();
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("生成xml文件");
	}
	
	public static FileInformation getFileInformation(String url,String fileName) {
		FileInformation fileInfo = new FileInformationSev() ;
		
		File file = new File(url) ;
		if(!file.exists()){
			System.out.println("xml文件不存在");
		}
		byte[] content = new byte[(int)file.length()] ;
		
		BufferedInputStream input = null ;
		try {
			input =  new BufferedInputStream(new FileInputStream(file)) ;
			input.read(content) ;
			fileInfo.setInformation(fileName, content);
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
}
