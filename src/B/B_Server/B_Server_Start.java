package B.B_Server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class B_Server_Start {
	public static void main(String[] args){
		try {
			B_Interface BBL = new B_Controller() ;
			LocateRegistry.createRegistry(8882) ;//创建注册表
			
			Naming.bind("rmi://localhost:8882/B_Interface",BBL);
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
	}
}
