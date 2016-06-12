package B.DataService;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;














import org.hibernate.exception.ConstraintViolationException;

import B.Model.Course;


public class CourseDataServiceImpl implements CourseDataService{
	private Session session ;
	@Override
	public boolean add(Course course) {
		// TODO Auto-generated method stub
		boolean result = true ;
		try{
//			System.out.println(course.getCredit());
			session = HibernateUtils.getSession() ;
			session.beginTransaction() ;
			session.save(course) ;
			session.getTransaction().commit(); 
		}catch(ConstraintViolationException e){
			System.out.println("重复插入");
			result = false ;
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

	@Override
	public void createAllCoursesXMLFile() {
		IOHelper.createXMLFile("select * from COURSES_TABLE", "course");
	}

	@Override
	public void createSharedCoursesXMLFile() {
		// TODO Auto-generated method stub
		System.out.println("data层");
		IOHelper.createXMLFile("select * from COURSES_TABLE where ifshared='1' and ID like '02%'", "sharedcourse");
	}
	public static void main(String[] args){
		CourseDataServiceImpl cds = new CourseDataServiceImpl() ;
//		List<Course> cs = cds.show() ;
//		for(Course c:cs){
//			System.out.println(c.getId());
//		}
//		Course c = cds.find("0201") ;
		cds.createSharedCoursesXMLFile();
//		cds.add(c) ;
	}
}
