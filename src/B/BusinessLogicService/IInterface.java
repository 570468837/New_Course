package B.BusinessLogicService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import integrated_server.IServer_Interface;

public class IInterface {
	private static IInterface theInstance ;
	public IServer_Interface IClient ;
	private static String INTEGRAED_SERVER_IP = "localhost" ;
	private IInterface() {
		// TODO Auto-generated constructor stub
		initIClient();
	}
	private void initIClient(){
		if(IClient==null){
			try {
				IClient=(IServer_Interface) Naming.lookup("rmi://"+INTEGRAED_SERVER_IP+":8880/I_Interface") ;
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
//		return IClient ;
	}
	public static IInterface getInstance(){
		if(theInstance==null){
			theInstance=new IInterface() ;
		}
		return theInstance ;
	}
}
