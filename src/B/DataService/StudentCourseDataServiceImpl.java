package B.DataService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import B.Model.Course;
import B.Model.Student;
import B.Model.StudentCourse;


public class StudentCourseDataServiceImpl implements StudentCourseDataService {
	private Session session ;
	@SuppressWarnings("unchecked")
	@Override
	public boolean add(Student student, Course course) {
		// TODO Auto-generated method stub
		boolean result = true ;
		try{
			session = HibernateUtils.getSession() ;
			session.beginTransaction() ;
			String hql = "from StudentCourse where student=? and course=?" ;
			Query query = session.createQuery(hql) ;
			query.setEntity(0, student) ;
			query.setEntity(1, course) ;
			List<StudentCourse> list = query.list() ;
			if(list.size()>0)
				result = false ;
			else{
				StudentCourse sc = new StudentCourse();
				sc.setStudent(student);
				sc.setCourse(course);
				session.save(sc) ;
				session.getTransaction().commit();
			}
		}catch(Exception e){
			e.printStackTrace();
			result = false ;
			session.getTransaction().rollback();
		}finally{
			HibernateUtils.closSession(session);
		}
		return result ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean delete(Student student, Course course) {
		// TODO Auto-generated method stub
		boolean result = true ;
		try{
			session = HibernateUtils.getSession() ;
			session.beginTransaction() ;
			String hql = "from StudentCourse where student=? and course=?" ;
			Query query = session.createQuery(hql) ;
			query.setEntity(0, student) ;
			query.setEntity(1, course) ;
			List<StudentCourse> list = query.list() ;
			if(list.size()<0)
				result = false ;
			else{
				StudentCourse sc = list.get(0) ;
				session.delete(sc);
				session.getTransaction().commit();
			}
		}catch(Exception e){
			result = false ;
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			HibernateUtils.closSession(session);
		}
		return result ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean mark(Student student, Course course, String grade) {
		// TODO Auto-generated method stub
		boolean result = true ;
		try{
			session = HibernateUtils.getSession() ;
			session.beginTransaction() ;
			String hql = "from StudentCourse where student=? and course=?" ;
			Query query = session.createQuery(hql) ;
			query.setEntity(0, student) ;
			query.setEntity(1, course) ;
			List<StudentCourse> list = query.list() ;
			if(list.size()<0)
				result = false ;
			else{
				StudentCourse sc = list.get(0) ;
				sc.setGrade(grade);
				session.update(sc);
				session.getTransaction().commit();
			}
		}catch(Exception e){
			result = false ;
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			HibernateUtils.closSession(session);
		}
		return result ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Course> getSelectedCourses(Student s) {
		// TODO Auto-generated method stub
		ArrayList<Course> result = new ArrayList<Course>() ;
		try{
			session = HibernateUtils.getSession() ;
			session.beginTransaction() ;
			
			//hibernate查询结果含对象时不能多层嵌套new 对象
			String hql = "select new B.Model.Course(course.id,course.name,course.teacher,course.classRoom,course.credit,course.hour,course.ifShared) from StudentCourse where student=?" ;
			Query query = session.createQuery(hql) ;
//			query.setParameter(0, s);
			query.setEntity(0, s) ;
			List<Course> list = query.list() ;
			Iterator<Course> iter = list.iterator() ;
			while(iter.hasNext()){
				Course c = iter.next() ;
				result.add(c) ;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void CreateAllSelectionsXMLFile() {
		// TODO Auto-generated method stub
		IOHelper.createXMLFile("select student_id,course_id,grade from STUDENTS_COURSES_TABLE", "selection");
	}

	public static void main(String[] args){
		StudentCourseDataService scds = new StudentCourseDataServiceImpl() ;
		scds.CreateAllSelectionsXMLFile(); 
	}
	
}
