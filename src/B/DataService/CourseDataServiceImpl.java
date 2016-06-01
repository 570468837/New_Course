package B.DataService;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import B.Model.Course;


public class CourseDataServiceImpl implements CourseDataService{
	private Session session ;
	@Override
	public boolean add(Course course) {
		// TODO Auto-generated method stub
		boolean result = true ;
		try{
			session = HibernateUtils.getSession() ;
			session.beginTransaction() ;
			session.save(course) ;
			session.getTransaction().commit(); 
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			result = false;
		}finally{
			HibernateUtils.closSession(session);
		}
		return result ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> show() {
		// TODO Auto-generated method stub
		List<Course> result = null ;
		try{
			session = HibernateUtils.getSession() ;
			session.beginTransaction() ;
			Query query = session.createQuery("from Course") ;
			result = query.list() ;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtils.closSession(session);
		}
		return result;
	}

	@Override
	public Course find(String id) {
		// TODO Auto-generated method stub
		Course result = null ;
		try{
			session = HibernateUtils.getSession() ;
			session.beginTransaction() ;
			String hql = "from Course where id =?" ;
			Query query = session.createQuery(hql) ;
			query.setString(0, id) ;
			result = (Course) query.list().get(0) ;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtils.closSession(session);
		}
		return result ;
	}
}
