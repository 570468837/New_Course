package integrated_server;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import common.FileInformation;
import common.FileInformationSev;
/**
 * 
 * @author FrankYao
 *	用于帮助在集成服务器上统计数据的类
 */
public class XML_Helper {
	public static ArrayList<Course> decodeCourses(String fileAddress){
		ArrayList<Course> result = new ArrayList<>();
		SAXReader reader = new SAXReader();
		File file = new File(fileAddress);
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		for (Element child : childElements) {
			String id = child.elementText("id");
			String name = child.elementText("name");
			String score = child.elementText("score");
			String teacher = child.elementText("teacher");
			String location = child.elementText("location");
			result.add(new Course(id, name, score, teacher, location));
		  }
		return result;
	}	
	
	public static void outputCourses(ArrayList<Course> courses, String outputAddress) {
		Document document = DocumentHelper.createDocument();	
		//创建root 
		Element root = document.addElement("courses");  
		        //生成root的一个接点  
		for(Course c: courses){
			Element courseElement = root.addElement("course");
			Element id = courseElement.addElement("id");
			id.setText(c.getId());
			Element name = courseElement.addElement("name");
			name.setText(c.getName());
			Element score = courseElement.addElement("score");
			score.setText(c.getScore());
			Element teacher = courseElement.addElement("teacher");
			teacher.setText(c.getTeacher());
			Element location = courseElement.addElement("location");
			location.setText(c.getLocation());
		}
		//输出
		StringWriter stringWriter = new StringWriter();  
	    //设置文件编码  
		OutputFormat xmlFormat = new OutputFormat();  
	    xmlFormat.setEncoding("UTF-8"); 
	    // 设置换行 
	    xmlFormat.setNewlines(true); 
	    // 生成缩进 
	    xmlFormat.setIndent(true); 
	    // 使用4个空格进行缩进, 可以兼容文本编辑器 
	    xmlFormat.setIndent("    "); 
	        
	    //创建写文件方法  
	    XMLWriter xmlWriter;
		try {
			xmlWriter = new XMLWriter(new FileWriter(outputAddress),xmlFormat);
			xmlWriter.write(document);  
			xmlWriter.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

	public static ArrayList<Student> decodeStudents(String fileAddress){
		ArrayList<Student> result = new ArrayList<>();
		SAXReader reader = new SAXReader();
		File file = new File(fileAddress);
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		for (Element child : childElements) {
			String id = child.elementText("id");
			String name = child.elementText("name");
			String sex = child.elementText("sex");
			String major = child.elementText("major");
			result.add(new Student(id, name, sex, major));
		  }
		return result;
	}
	
	public static ArrayList<Selection> decodeSelections(String fileAddress){
		ArrayList<Selection> result = new ArrayList<>();
		SAXReader reader = new SAXReader();
		File file = new File(fileAddress);
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		for (Element child : childElements) {
			String sid = child.elementText("sid");
			String cid = child.elementText("cid");
			String score = child.elementText("score");
			result.add(new Selection(sid, cid, score));
		  }
		return result;
	}
	
	//下面是小宇的代码
	/**
	 * 
	 * @param sourceFileInfo 将要转化的目标文件对象
	 * @param XSLpath 转化标准xsl文件所在路径
	 * @param savePath xml存储路径文件夹，最后要加"/"
	 * @param fileName 转化后的文件名
	 * @return 是否转化成功
	 */
	public static String TransformXML(FileInformation sourceFileInfo,String XSLpath,String savePath,String fileName){
		Document sourceDoc = BytesToDoc(sourceFileInfo.getContent()) ;
		TransformerFactory  factory = TransformerFactory.newInstance() ;
		try {
			Transformer transformer = factory.newTransformer(new StreamSource(XSLpath)) ;
			DocumentSource source = new DocumentSource(sourceDoc) ;
			DocumentResult result = new DocumentResult(); 
			
			//转化开始
			transformer.transform(source, result);
			Document resultDoc = result.getDocument() ;
			Writer w = new FileWriter(savePath+fileName) ;
			OutputFormat opf = OutputFormat.createPrettyPrint() ;
			opf.setEncoding("UTF-8");
			XMLWriter xw = new XMLWriter(w,opf) ;
			xw.write(resultDoc);
			xw.close();
			w.close();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("实例化transformer失败");
			return null ;
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("转化失败");
			return null ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("写入文件失败");
			return null ;
		}
		return savePath + fileName  ;
	}
	//辅助方法，可以忽略
	public static FileInformation xmlToFileInfo(String xmlPath){
		FileInformation fileInfo = new FileInformationSev() ;
		int index = xmlPath.lastIndexOf("/") ;
		String name = xmlPath.substring(index+1) ;
		File file = new File(xmlPath) ;
		if(!file.exists()){
			System.out.println("xml文件不存在");
		}
		
		byte[] content = new byte[(int)file.length()] ;
		
		BufferedInputStream input = null ;
		try {
			input =  new BufferedInputStream(new FileInputStream(file)) ;
			input.read(content) ;
			fileInfo.setInformation(name, content);
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
	//辅助方法，可以忽略
	public static Document BytesToDoc(byte[] content){
		Document result = null ;
		SAXReader reader = new SAXReader() ;
		ByteArrayInputStream bais = new ByteArrayInputStream(content);  
        try {
			result = reader.read(bais);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("字节转换为Document失败");
		}  
        return result ;
	}
	
	public static void main(String[] args){
		XML_Helper x = new XML_Helper();
		ArrayList<Course> result = new ArrayList<>();
		result.add(new Course("1", "离散数学", "3", "陈道蓄", "102"));
		result.add(new Course("2", "软工", "3", "刘钦", "103"));
		result.add(new Course("3", "网络", "刘峰", "3", "104"));
		x.outputCourses(result, "test.xml");
//		result = x.decodeCourses("test.xml");
//		for(Course c:result){
//			System.out.println(c.getId());
//			System.out.println(c.getName());
//			System.out.println(c.getScore());
//			System.out.println(c.getTeacher());
//			System.out.println(c.getLocation());
//			System.out.println();
		}
	}
