package integrated_server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XML_Decode {
	public ArrayList<Course> decodeCourses(String fileAddress){
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


	public ArrayList<Student> decodeStudents(String fileAddress){
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
	
}
