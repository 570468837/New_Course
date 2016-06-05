package C.data;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

public class XSLTrans {
	public void trans() throws DocumentException, TransformerException, IOException{
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new FileReader(new File("C.account.xml")));
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(new StreamSource("classToA.xsl"));
		DocumentSource source = new DocumentSource(document);
		DocumentResult result = new DocumentResult();
		transformer.transform(source, result);
		Document transformedDoc = result.getDocument();
		Writer w = new FileWriter("**.xml");
		OutputFormat opf = OutputFormat.createPrettyPrint();
		opf.setEncoding("utf8");
		XMLWriter xw = new XMLWriter(w,opf);
		xw.write(transformedDoc);
		xw.close();
		w.close();
		
		
	}
	
	public static void main(String[] args) throws DocumentException, TransformerException, IOException {
		XSLTrans x = new XSLTrans();
		x.trans();
	}
	
}
