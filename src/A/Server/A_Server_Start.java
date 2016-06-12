package A.Server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class A_Server_Start {
	public static void main(String[] args){
		try {
			A_Interface BBL = new A_Controller() ;
			LocateRegistry.createRegistry(8881) ;//创建注册表
			
			Naming.bind("rmi://localhost:8881/A_Interface",BBL);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("创建远程对象异常");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("绑定重复异常");
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			System.out.println("URL畸形异常");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("B服务器开启");
	}
}
