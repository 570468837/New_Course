package B.DataService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;	
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * 
 * @author Rain
 * hibernate工具类
 */
public class HibernateUtils {
	
	private static SessionFactory factory ;
	static{
		Configuration cfg = new Configuration() ;
		cfg.configure("B\\hibernate.cfg.xml") ;
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
		.applySettings(cfg.getProperties()).build() ;
		factory = cfg.buildSessionFactory(serviceRegistry) ;
		factory = cfg.buildSessionFactory() ;
	}
	/**
	 * 
	 * @return opened session
	 */
	public static Session getSession(){
		return factory.openSession() ;
	}
	/**
	 * 
	 * @param session 
	 *  close the session
	 */
	public static void closSession(Session session){
		if(session!=null && session.isOpen()){
			session.close();
		}
	}
}
