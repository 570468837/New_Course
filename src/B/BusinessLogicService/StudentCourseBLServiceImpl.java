package B.BusinessLogicService;

import java.util.ArrayList;

import B.DataService.StudentCourseDataService;
import B.DataService.StudentCourseDataServiceImpl;
import B.Model.Course;
import B.Model.Student;

public class StudentCourseBLServiceImpl implements StudentCourseBLService{
	private StudentCourseDataService scController ;
	public StudentCourseBLServiceImpl() {
		// TODO Auto-generated constructor stub
		scController = new StudentCourseDataServiceImpl() ;
	}
	@Override
	public ArrayList<Course> getSelectedCourses(Student s) {
		// TODO Auto-generated method stub
		return scController.getSelectedCourses(s);
	}
	@Override
	public void CreateAllSelectionsXMLFile() {
		// TODO Auto-generated method stub
		scController.CreateAllSelectionsXMLFile(); 
	}

}
