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
		//未知属性名情况下
		/*List<Attribute> attributeList = child.attributes();
	   	  for (Attribute attr : attributeList) {
		  System.out.println(attr.getName() + ": " + attr.getValue());
		}*/

		 //已知属性名情况下
			String id = child.elementText("id");
			String name = child.elementText("name");
			String score = child.elementText("score");
			String teacher = child.elementText("teacher");
			String location = child.elementText("location");
			result.add(new Course(id, name, score, teacher, location));

		   //未知子元素名情况下
		   /*List<Element> elementList = child.elements();
		   for (Element ele : elementList) {
		    System.out.println(ele.getName() + ": " + ele.getText());
		   }
		   System.out.println();*/

		   //已知子元素名的情况下
		   //这行是为了格式化美观而存在
			
		  }
		return result;
	}
}
