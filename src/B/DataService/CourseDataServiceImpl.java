package B.DataService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;







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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void createAllCoursesXMLFile() {
		// TODO Auto-generated method stub
		session = HibernateUtils.getSession() ;;
		ResultSet result = (ResultSet) session.doReturningWork(new ReturningWork(){
			@Override
			public Object execute(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				String sql = "select * from COURSES_TABLE" ;
				PreparedStatement ps = con.prepareStatement(sql) ;
				return ps.executeQuery();
			}
		}) ;
		ResultSetMetaData rsmd;
		try {
			rsmd = result.getMetaData();
			int count = rsmd.getColumnCount() ;
			String[] columnNames = new String[count] ;
			for(int i=0;i<count;i++){
				columnNames[i] = rsmd.getColumnName(1+i) ;
			}
			Document doc = DocumentHelper.createDocument() ;
			Element root = doc.addElement("Courses") ;
			while(result.next()){
				Element emp = root.addElement("course") ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void createSharedCoursesXMLFile() {
		// TODO Auto-generated method stub
		
	}
}
