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
	private static String path ="./BFiles/B_XML/B_" ;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void createXMLFile(String sql, String elementName){
		// TODO Auto-generated method stub
		String fileName = elementName ;
		if(elementName.equals("sharedcourse"))
			elementName = "course" ;
		//获取ResultSet
		Session session = HibernateUtils.getSession() ;
		ResultSet result = (ResultSet) session.doReturningWork(new ReturningWork(){
			@Override
			public Object execute(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps = con.prepareStatement(sql) ;
				System.out.println(sql);
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
				Element emp = root.addElement(elementName.toUpperCase()) ;
				for(int i=0;i<count;i++){
					Element column = emp.addElement(columnNames[i]) ;
					if(result.getObject(i+1)!=null)
						column.setText(result.getObject(i+1)+"");
					else{
						column.setText("");
					}
				}
			}
			System.out.println("获得doc");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//生成xml文件
		docToXml(doc, path+fileName.toUpperCase()+"S.xml");
		
	}
	/**
	 * 
	 * @param doc
	 * @param savePath 生成xml文件的保存路径，包括文件名
	 */
	public static void docToXml(Document doc,String savePath){
		try {
			Writer w = new FileWriter(savePath) ;
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
		System.out.println("生成xml文件："+savePath);
	}
	
	public static FileInformation getFileInformation(String fileName) {
		FileInformation fileInfo = new FileInformationSev() ;
		
		File file = new File(path+fileName.toUpperCase()+"S.xml") ;
		if(!file.exists()){
			System.out.println("xml文件不存在");
		}
		
		byte[] content = new byte[(int)file.length()] ;
		
		BufferedInputStream input = null ;
		try {
			input =  new BufferedInputStream(new FileInputStream(file)) ;
			input.read(content) ;
			fileInfo.setInformation("B_"+fileName.toUpperCase()+"S.xml", content);
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
