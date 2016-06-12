package A.data;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import common.Faculty;
import common.FileInformation;
import A.Server.A_XML_Helper;
import A.dataservice.CourseSelectionDataService;
import A.po.AccountPO;
import A.po.CoursePO;
import A.po.StudentPO;
import B.BusinessLogicService.IInterface;
import B.DataService.IOHelper;
import C.rmi.C_XML_Helper;

public class CourseSelectionData implements CourseSelectionDataService {
	static String sql = null;  
    static DBHelper dbh = null;  
    static ResultSet rs = null; 
    PreparedStatement ps=null;
    static String inSql = null;
    CourseData cd = new CourseData();
    public boolean selectCourseLocal(StudentPO s, CoursePO c) {
		// TODO Auto-generated method stub
		dbh=new DBHelper();
		Connection conn = dbh.getConnection();
		
		inSql = "insert into selection values ('"+c.getCno()+"','"+s.getSno()+"','')";
		try {
			ps=conn.prepareStatement(inSql);
			int i = ps.executeUpdate();
			if(i==1)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

    public boolean selectCourse(StudentPO s, CoursePO c){
    	String courseId = c.getCno();
    	if (courseId.startsWith("03"))
    		return selectCourseLocal(s,c);
    	else
    		return selectCourseOther(s,c);    
    }
    
    public boolean selectCourseOther(StudentPO s,CoursePO c){
    	boolean result = false;
    	Faculty f = null ;
    	String courseId = c.getCno();
		if(courseId.startsWith("02"))
			f = Faculty.B ;
		else
			f = Faculty.C ;
		IInterface iInterface = IInterface.getInstance() ;
		selectionToXml(s, c,"./AFiles/A_XML/A_courseSelection.xml");
		studentToXml(s, "./AFiles/A_XML/A_student.xml");
		FileInformation selectionfileInfo = A_XML_Helper.xmlToFileInfo("./CFiles/C_XML/C_courseSelection.xml");
		FileInformation studentfileInfo = A_XML_Helper.xmlToFileInfo("./CFiles/C_XML/C_student.xml");

		try {
			result = iInterface.IClient.selectCourse(selectionfileInfo, studentfileInfo,f) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
    }
    
	@Override
	public ArrayList<CoursePO> selectAllCourse() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AccountPO> selectAllAccount() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CoursePO> selectMyCourse(StudentPO spo)  {
		// TODO Auto-generated method stub
		ArrayList<CoursePO> poList = new ArrayList<CoursePO>();
		dbh=new DBHelper();
		Connection conn = dbh.getConnection();
		ArrayList<String> cpo=new ArrayList<String>();
		inSql = "select 课程编号 from selection where 学生编号 ="+spo.getSno();
		try {
			ps=conn.prepareStatement(inSql);
			rs=ps.executeQuery();
			while(rs.next()){
				cpo.add(rs.getString(1));
						}
			rs.close();  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String s : cpo){
			if(!s.equals(null))
				try {
					poList.add(cd.selectById(s));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		}
		
		return poList;
	}

	public boolean quitCourseLocal(StudentPO s, CoursePO c) {
		// TODO Auto-generated method stub
		dbh=new DBHelper();
		Connection conn = dbh.getConnection();
		inSql = "select * from selection where 课程编号 = "+c.getCno()+"and 学生编号="+s.getSno();
		try {
			ps=conn.prepareStatement(inSql);
			rs = ps.executeQuery();
			if(rs==null)
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		inSql = "delete from selection where 课程编号 = "+c.getCno()+"and 学生编号="+s.getSno();
		try {
			ps=conn.prepareStatement(inSql);
			int i = ps.executeUpdate();
			if(i==1)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	public  boolean quitCourse(StudentPO s,CoursePO c){
		String courseId = c.getCno();
		if(courseId.startsWith("03"))
			return quitCourseLocal(s,c);
		else
			return quitCourseOther(s,c);
	}
	
	public boolean quitCourseOther(StudentPO s, CoursePO c){
		boolean result=false;
		String courseId = c.getCno();
		Faculty f = null ;
		if(courseId.startsWith("02"))
			f=Faculty.B;
		else
			f=Faculty.C;
		IInterface iInterface = IInterface.getInstance() ;
		selectionToXml(s, c,"./CFiles/C_XML/C_courseSelection.xml");
		FileInformation fileInfo = C_XML_Helper.xmlToFileInfo("./CFiles/C_XML/C_courseSelection.xml");
		try {
			result = iInterface.IClient.quitCourse(fileInfo, Faculty.C) ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
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
		A_XML_Helper.docToXml(doc,savePath);
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
		A_XML_Helper.docToXml(doc,savePath);
	}

}
