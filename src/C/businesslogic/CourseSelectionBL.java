package C.businesslogic;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import C.businesslogicservice.CourseSelectionBLService;
import C.dataservice.CourseDataService;
import C.dataservice.CourseSelectionDataService;
import C.dataservice.UserDataService;
import C.po.AccountPO;
import C.po.CoursePO;
import C.po.CourseSelectionPO;
import C.po.StudentPO;
import C.vo.AccountVO;
import C.vo.CourseSelectionVO;
import C.vo.CourseVO;
import C.vo.LoginVO;
public class CourseSelectionBL implements CourseSelectionBLService{

	@Override
	/*
	 * 学生选课操作
	 * */
	public CourseSelectionVO courseSelect(AccountVO a, CourseVO c) {
		// TODO Auto-generated method stub
		CourseSelectionDataService cs;
		CourseSelectionVO courseSelectionVO = null;
		
		
		try {
			cs=(CourseSelectionDataService) Naming.lookup("rmi://127.0.0.1:2016/Server");
			CoursePO cpo = new CoursePO(null, null, 0, 0, null, null, null);
			AccountPO apo = new AccountPO(null, null, 0);
			cpo.setCno( c.getCno());
			apo.setAcc(a.getAcc());
			
			cs.selectCourse(apo, cpo);
			
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
		return courseSelectionVO;
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
	 * 退课   啊啊啊啊啊啊啊啊啊不知道为什么在这里就是false 不是true   ！！！！！明天再写
	 * */
	public boolean courseQuit(AccountVO a, CourseVO c) throws RemoteException {
		// TODO Auto-generated method stub
		CourseSelectionDataService cs = null;
		CoursePO cpo = null;
		AccountPO apo =null;
		try {
			cs=(CourseSelectionDataService) Naming.lookup("rmi://127.0.0.1:2016/Server");
			cpo = new CoursePO(null, null, 0, 0, null, null, null);
			apo = new AccountPO(null, null, 0);
			cpo.setCno( c.getCno());
			apo.setAcc(a.getAcc());
			
			cs.quitCourse(apo, cpo);
			
			
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
		return cs.quitCourse(apo, cpo);
		
	}
	public static void main(String[] args) throws RemoteException {
		CourseSelectionBL csbl = new CourseSelectionBL();
		AccountVO avo = new AccountVO("3","2333",20190908);
		CourseVO cvo = new CourseVO("1", null, 0, 0, null, null, null);
		StudentPO spo = new StudentPO("3",null,null,null,null);

//		csbl.courseSelect(avo, cvo);
//		for(int i = 0;i<csbl.showSelectedCourse(spo).length;i++){
//			
//			
//			System.out.println(csbl.showSelectedCourse(spo)[i].getCno());
//		}
//		try {
//			csbl.showSelectedCourse(spo);
//			
//			
//			System.out.println(csbl.showSelectedCourse(spo)[0].getCno());
//		
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(csbl.courseQuit(avo, cvo)+"ssss");
	}

	
}
