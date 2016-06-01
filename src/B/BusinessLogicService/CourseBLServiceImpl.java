package B.BusinessLogicService;

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
}
