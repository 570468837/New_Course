package C.businesslogic;


import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import C.businesslogicservice.UserBLService;
import C.dataservice.UserDataService;
import C.po.AccountPO;
import C.vo.AccountVO;


public class UserBL  implements UserBLService{
	
	/*
	 * 实现了基本的登录功能，但是不知道为啥，用户名不存在的时候有bug。。。。。过段时间再调吧
	 * 输入用户名 密码 当正确时返回;
	 * 不正确时或者用户不存在时返回false
	 * */
	//登录
	public boolean login(String username,String password) {
		UserDataService ud;
		
		String result = "该用户名不存在";
		boolean res = false;
	
    	boolean nameIsTrue = false;
    	boolean passwordIsTrue = false;
    	try {
			ud=(UserDataService) Naming.lookup("rmi://127.0.0.1:2015/Server");
			AccountPO[] po = ud.selectAll();
			int i = 0;
			while(po[i].getAcc() != null){
				System.out.println(po[i].getAcc());
					System.out.println("lalal");

	    			if(username.equals(po[i].getAcc())){
	    				nameIsTrue = true;
	    				if(password.equals(po[i].getPasswd())){
	    					passwordIsTrue = true;
	    					result = "登录成功";
	    					res = true;
	    					System.out.println(result);
	    					break;
	    				}else{
	    					result = "密码错误";
	    					System.out.println(result);
	    					break;
	    				}
	    			}
	    			i++;
	    		
	    	}
			if(nameIsTrue == false&&passwordIsTrue == false){
	    		result = "该用户名不存在";
	    		System.out.println(result);
	    	}
			
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
		
    	return res;
	}
	public void accountXML() throws IOException {
		UserDataService ud;
		
		
    	try {
			ud=(UserDataService) Naming.lookup("rmi://127.0.0.1:2015/Server");
			ud.createAccountXML();
		
			
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


	public static void main(String[] args) {
		UserBL ubl = new UserBL();
//		AccountVO vo = new AccountVO("2","2333",20190908);
		
		System.out.println(ubl.login("2","2333"));
//		UserDataService ud;
//		try {
//			ud=(UserDataService) Naming.lookup("rmi://127.0.0.1:2015/Server");
//			AccountPO[] po = new AccountPO[50];
//	    	
//	    	for(int i=0;i<10;i++){
//	    		 po[i] = new AccountPO(null,null,0);
//	             System.out.println(po[i].getAcc());  
//	    	}
//			ud.selectAll();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NotBoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
}
