package B.BusinessLogicService;

import java.util.ArrayList;
import java.util.List;

import B.DataService.CourseDataService;
import B.DataService.CourseDataServiceImpl;
import B.Model.Course;

public class CourseBLServiceImpl implements CourseBLService{
	private CourseDataService dataControler ;
	public CourseBLServiceImpl() {
		// TODO Auto-generated constructor stub
		dataControler = new CourseDataServiceImpl() ;
	}
	@Override
	public Course getCourseById(String id) {
		// TODO Auto-generated method stub
		return dataControler.find(id);
	}
	@Override
	public ArrayList<Course> getLocalCourseShared() {
		// TODO Auto-generated method stub
		ArrayList<Course> result = new ArrayList<Course>() ;
		List<Course> list = dataControler.show() ;
		for(Course c:list){
			if(c.getIfShared()=='1'){
				result.add(c) ;
			}
		}
		return result ;
	}
}
