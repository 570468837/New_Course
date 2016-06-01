package C.rmi;



import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;

import C.data.CourseData;
import C.data.CourseSelectionData;
import C.data.StudentData;
import C.data.UserData;
import C.dataservice.CourseDataService;
import C.dataservice.CourseSelectionDataService;
import C.dataservice.StudentDataService;
import C.dataservice.UserDataService;



public class Server {
	public Server() { 
	try {  
		LocateRegistry.createRegistry(2005);    
		LocateRegistry.createRegistry(2006);    
		LocateRegistry.createRegistry(2007); 
		LocateRegistry.createRegistry(2015);//userData
		LocateRegistry.createRegistry(2016);//courseSelectionData
		LocateRegistry.createRegistry(2017);//courseData
		LocateRegistry.createRegistry(2018);//studentData

		UserDataService ud = new UserData();    //2015
		CourseSelectionDataService cs = new CourseSelectionData();//2016
		CourseDataService c = new CourseData();//2017
		StudentDataService s= new StudentData();//2018
	 
		Naming.rebind("rmi://127.0.0.1:2015/Server", ud); 
		Naming.rebind("rmi://127.0.0.1:2016/Server", cs); 
		Naming.rebind("rmi://127.0.0.1:2017/Server", c); 
		Naming.rebind("rmi://127.0.0.1:2018/Server", s); 
		System.out.println("server success");
		} catch (Exception e) {  
			e.printStackTrace();  
			}  
	}  
	public static void main(String args[]) {  
		new Server(); 
		
		} 
}