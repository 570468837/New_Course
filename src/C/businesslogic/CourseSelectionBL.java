package C.businesslogic;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import C.businesslogic.IInterface;

import C.businesslogicservice.CourseSelectionBLService;
import C.dataservice.CourseDataService;
import C.dataservice.CourseSelectionDataService;
import C.dataservice.UserDataService;
import C.po.AccountPO;
import C.po.CoursePO;
import C.po.CourseSelectionPO;
import C.po.StudentPO;
import C.rmi.C_XML_Helper;
import C.vo.AccountVO;
import C.vo.CourseSelectionVO;
import C.vo.CourseVO;
import common.Faculty;
import common.FileInformation;

public class CourseSelectionBL implements CourseSelectionBLService{

	 //插入学生记录
    public  void insertStudent(StudentPO student){
    	CourseSelectionDataService cs;
		try {
			cs=(CourseSelectionDataService) Naming.lookup("rmi://127.0.0.1:2016/Server");
			cs.insertStudent(student);
		
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	@Override
	/*
	 * 学生选课操作   好吧我不知道为什么反正确实能选课，但是返回的布尔值就是不正确
	 * */
	public boolean courseSelectLocal(StudentPO s, CoursePO c) {
		// TODO Auto-generated method stub
		CourseSelectionDataService cs;
		CourseSelectionVO courseSelectionVO = null;
		boolean result = false;
		
		try {
			cs=(CourseSelectionDataService) Naming.lookup("rmi://127.0.0.1:2016/Server");
			
			
			cs.selectCourse(s, c);
			result = cs.selectCourse(s, c);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public boolean courseSelect(StudentPO s,CoursePO c){
		boolean result1 = false;
		boolean result2 = false;
		String courseId = c.getCno();
		result1 = courseSelectLocal(s, c) ;
		if(courseId.startsWith("01")){ //判断是否为本院系课程
			System.out.println("是本院系");
			return result1;
		}
		else{
			Faculty f = null ;
			if(courseId.startsWith("02"))
				f = Faculty.B ;
			else
				f = Faculty.A ;
			IInterface iInterface = IInterface.getInstance() ;
			selectionToXml(s, c,"./CFiles/C_XML/C_courseSelection.xml");
			studentToXml(s, "./CFiles/C_XML/C_student.xml");
			FileInformation selectionfileInfo = C_XML_Helper.xmlToFileInfo("./CFiles/C_XML/C_courseSelection.xml");
			FileInformation studentfileInfo = C_XML_Helper.xmlToFileInfo("./CFiles/C_XML/C_student.xml");

			
			try {
				result2 = iInterface.IClient.selectCourse(selectionfileInfo, studentfileInfo,Faculty.C) ;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result2 ;
	}
	public  void studentToXml(StudentPO s,String savePath){
		Document doc = DocumentHelper.createDocument() ;
		Element root = doc.addElement("students") ;
		Element student = root.addElement("student") ;
		Element sid = student.addElement("Sno") ;
		sid.setText(s.getSno());
		Element snm = student.addElement("Snm") ;
		snm.setText(s.getSnm());
		Element sex = student.addElement("Sex");
		sex.setText(s.getSex());
		Element sde = student.addElement("Sde");
		sde.setText(s.getSde());
		Element pwd = student.addElement("Pwd");
		pwd.setText(s.getPwd());
		C_XML_Helper.docToXml(doc,savePath);
	}
	public  void selectionToXml(StudentPO s,CoursePO c,String savePath){
		Document doc = DocumentHelper.createDocument() ;
		Element root = doc.addElement("courseSelections") ;
		Element selection = root.addElement("courseSelection") ;
		Element sid = selection.addElement("Sno") ;
		sid.setText(s.getSno());
		Element cid = selection.addElement("Cno");
		cid.setText(c.getCno());
		Element grade = selection.addElement("Grd") ;
		grade.setText("0");
		C_XML_Helper.docToXml(doc,savePath);
	}
	/*
	 * 显示已选课程  
	 * 输入studentpo 输出coursepo
	 * 好吧。。。好像最后有个空指针异常 先不管了。。。。selectallcourse好像也有这个问题
	 * 
	 * */
	public CoursePO[] showSelectedCourse(StudentPO spo) throws RemoteException {
		// TODO Auto-generated method stub
		CourseSelectionDataService cd = null;
		
		
		try {
			
			cd=(CourseSelectionDataService) Naming.lookup("rmi://127.0.0.1:2016/Server");
			
			cd.selectMyCourse(spo);
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			
	
		
		return cd.selectMyCourse(spo);
		
	}
	
	/*
	 * 退课   啊啊啊啊啊啊啊啊啊不知道为什么在这里就是false 不是true   ！！！！！但是功能是能实现的
	 *       可以在表中删除的！！！！！
	 * */
	public boolean courseQuitLocal(StudentPO s, CoursePO c) throws RemoteException {
		// TODO Auto-generated method stub
		CourseSelectionDataService cs = null;
		boolean result  = false;
		
		try {
			cs=(CourseSelectionDataService) Naming.lookup("rmi://127.0.0.1:2016/Server");
			
			
			cs.quitCourse(s, c);
			result = cs.quitCourse(s, c);
//			return result;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
	}
	public void courseSelectionXML() throws IOException {
		// TODO Auto-generated method stub
		CourseSelectionDataService cs;
	
		
		try {
			cs=(CourseSelectionDataService) Naming.lookup("rmi://127.0.0.1:2016/Server");
			
			
			cs.createCourseSelectionXML();
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean courseQuit(StudentPO s, CoursePO c) throws RemoteException{
		boolean result = false;
		String courseId = c.getCno();
		result = courseQuitLocal(s, c) ;
		if(courseId.startsWith("01")) //判断是否为本院系课程
			System.out.println("本院系");
		else{
			Faculty f = null ;
			if(courseId.startsWith("02"))
				f = Faculty.B ;
			else
				f = Faculty.A ;
			IInterface iInterface = IInterface.getInstance() ;
			selectionToXml(s, c,"./CFiles/C_XML/C_courseSelection.xml");
			FileInformation fileInfo = C_XML_Helper.xmlToFileInfo("./CFiles/C_XML/C_courseSelection.xml");
			try {
				result = iInterface.IClient.quitCourse(fileInfo, Faculty.C) ;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result ;
	}
	
	public static void main(String[] args) throws RemoteException {
		CourseSelectionBL csbl = new CourseSelectionBL();
		AccountVO avo = new AccountVO("3","2333",20190908);
		CoursePO cpo = new CoursePO("1", null, 0, 0, null, null, null);
		StudentPO spo = new StudentPO("01001",null,null,null,null);
//		csbl.courseSelect(spo, cpo);
//		System.out.println(csbl.courseSelect(spo, cpo));
//		csbl.showSelectedCourse(spo);
		for(int i = 0;i<csbl.showSelectedCourse(spo).length;i++){

			System.out.println(csbl.showSelectedCourse(spo)[i].getCno()+" "+csbl.showSelectedCourse(spo)[i].getTec()+" "+csbl.showSelectedCourse(spo)[i].getPla());
		}

//		System.out.println(csbl.courseQuit(avo, cvo)+"ssss");
//		csbl.courseQuit(spo, cpo);
	}

	
}
