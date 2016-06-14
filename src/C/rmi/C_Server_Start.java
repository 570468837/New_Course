package C.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class C_Server_Start {
	public static void main(String[] args){
		try {
			C_Interface CCL = new C_Controller() ;
			LocateRegistry.createRegistry(8883) ;//创建注册表
			
			Naming.bind("rmi://localhost:8883/C_Interface",CCL);
			System.out.println("c_server start");
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
