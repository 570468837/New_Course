package B.DataService;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import B.Model.Student;


public class StudentDataServiceImpl implements StudentDataService{
	private Session session ;

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> show() {
		// TODO Auto-generated method stub
		List<Student> students = null ;
		try{
			session = HibernateUtils.getSession() ;
			session.beginTransaction() ;
			Query query = session.createQuery("from Student") ;
			students = query.list() ;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtils.closSession(session);
		}
		return students;
	}

	@Override
	public boolean add(Student student) {
		// TODO Auto-generated method stub
		session = HibernateUtils.getSession() ;
		session.beginTransaction() ;
		try{
			session.save(student) ;
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
	public Student find(String id) {
		// TODO Auto-generated method stub
		Student s = null ;
		try{
			session = HibernateUtils.getSession() ;
			session.beginTransaction() ;
			String hql = "from Student where id=?" ;
			Query query = session.createQuery(hql) ;
			query.setString(0, id) ;
			List<Student> list = query.list() ;
			s = list.get(0) ;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtils.closSession(session);
		}
		return s;
	}
}
