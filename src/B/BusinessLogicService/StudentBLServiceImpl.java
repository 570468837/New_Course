package B.BusinessLogicService;


import B.DataService.StudentCourseDataService;
import B.DataService.StudentCourseDataServiceImpl;
import B.DataService.StudentDataService;
import B.DataService.StudentDataServiceImpl;
import B.Model.Course;
import B.Model.Student;

public class StudentBLServiceImpl implements StudentBLService{
	private StudentDataService sDataController ;
	private StudentCourseDataService scDataControler ;
	public StudentBLServiceImpl(){
		sDataController = new StudentDataServiceImpl() ;
		scDataControler = new StudentCourseDataServiceImpl() ;
	}
	@Override
	public Student getStudentById(String id) {
		// TODO Auto-generated method stub
		return sDataController.find(id);
	}
	@Override
	public boolean selectCourse(Student s, Course c) {
		// TODO Auto-generated method stub
		return scDataControler.add(s, c) ;
	}
	@Override
	public boolean quitCourse(Student s, Course c) {
		// TODO Auto-generated method stub
		return scDataControler.delete(s, c) ;
	}
	
}
