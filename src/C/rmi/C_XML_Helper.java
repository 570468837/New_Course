package C.rmi;





import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.rmi.RemoteException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


import common.FileInformation;
import common.FileInformationSev;

public class C_XML_Helper {
	/**
	 * 
	 * @param sourceFileInfo 将要转化的目标文件对象
	 * @param XSLpath 转化标准xsl文件所在路径
	 * @param savePath xml存储路径文件夹
	 * @param fileName 转化后的文件名
	 * @return 是否转化成功
	 */
	public static boolean TransformXML(FileInformation sourceFileInfo,String XSLpath,String savePath,String fileName){
		Document sourceDoc = BytesToDoc(sourceFileInfo.getContent()) ;
		TransformerFactory  factory = TransformerFactory.newInstance() ;
		try {
			Transformer transformer = factory.newTransformer(new StreamSource(XSLpath)) ;
			DocumentSource source = new DocumentSource(sourceDoc) ;
			DocumentResult result = new DocumentResult(); 
			
			//转化开始
			transformer.transform(source, result);
			Document resultDoc = result.getDocument() ;
			Writer w = new FileWriter(savePath+"/"+fileName) ;
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
			return false ;
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("转化失败");
			return false ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("写入文件失败");
			return false ;
		}
		return true  ;
	}
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
		System.out.println("生成xml文件");
	}
	
	public static void main(String[] args) throws RemoteException{
//		FileInformation f = C_XML_Helper.xmlToFileInfo("./CFiles/C_XML/C_student.xml") ;
//		boolean b = C_XML_Helper.TransformXML(f, "./CFiles/C_XSL/formatStudent.xsl", "./CFiles/United_XML","United_C_STUDENTS.xml") ;
//		System.out.println(b);
//		FileInformation f = C_XML_Helper.xmlToFileInfo("./CFiles/C_XML/C_courseSelection.xml") ;
//		boolean b = C_XML_Helper.TransformXML(f, "./CFiles/C_XSL/formatClassChoice.xsl", "./CFiles/United_XML","United_C_SELECTIONS.xml") ;
//		System.out.println(b);
//		FileInformation f = C_XML_Helper.xmlToFileInfo("./CFiles/C_XML/C_course.xml") ;
//		boolean b = C_XML_Helper.TransformXML(f, "./CFiles/C_XSL/formatClass.xsl", "./CFiles/United_XML","United_C_COURSES.xml") ;
//		System.out.println(b);
//		FileInformation f = C_XML_Helper.xmlToFileInfo("./CFiles/United_XML/United_C_STUDENTS.xml") ;
//		boolean b = C_XML_Helper.TransformXML(f, "./CFiles/C_XSL/studentToC.xsl", "./CFiles/C_XML","C_student.xml") ;
//		System.out.println(b);
//		FileInformation f = C_XML_Helper.xmlToFileInfo("./CFiles/United_XML/United_C_COURSES.xml") ;
//		boolean b = C_XML_Helper.TransformXML(f, "./CFiles/C_XSL/classToC.xsl", "./CFiles/C_XML","C_course.xml") ;
//		System.out.println(b);
		FileInformation f = C_XML_Helper.xmlToFileInfo("./CFiles/United_XML/United_C_SELECTIONS.xml") ;
		boolean b = C_XML_Helper.TransformXML(f, "./CFiles/C_XSL/choiceToC.xsl", "./CFiles/C_XML","C_courseSelection.xml") ;
		System.out.println(b);
		
	}
}
