package integrated_server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
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
