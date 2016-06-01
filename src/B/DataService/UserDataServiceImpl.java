package B.DataService;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import B.Model.Student;
import B.Model.User;

public class UserDataServiceImpl implements UserDataService{
	private Session session ;
	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		try{
			session = HibernateUtils.getSession() ;
			session.beginTransaction() ;
			session.save(user) ;
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			return false ;
		}finally{
			HibernateUtils.closSession(session);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student login(String userName, String password) {
		// TODO Auto-generated method stub
		Student user = null ;
		try{
			session = HibernateUtils.getSession() ;
			session.beginTransaction() ;
			String hql = "select new Student(u.object.id,u.object.name,u.object.gender,u.object.major,u.object.password) from User u where u.userName=? and u.password=?" ;
			Query query = session.createQuery(hql) ;
			query.setString(0, userName) ;
			query.setString(1,password) ;
			List<Student> list = query.list() ;
			user = list.get(0) ;
		}catch(Exception e){
			e.printStackTrace();
			return null ;
		}finally{
			HibernateUtils.closSession(session); 
		}
		return user ;
	}
}
