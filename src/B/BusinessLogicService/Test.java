package B.BusinessLogicService;



import B.Model.Student;

public class Test {
	public static void main(String[] args){
//		CourseBLService cbl = new CourseBLServiceImpl() ;
//		StudentBLService sbl = new StudentBLServiceImpl() ;
//		Student s = sbl.getStudentById("131250011") ;
//		System.out.println(s.getName());
//		String str = "020" ;
//		for(int i=1;i<11;i++){
//			Course c = cbl.getCourseById("0210") ;
//			System.out.println(c.getName());
//			sbl.selectCourse(s, c) ;
//		}
//		StudentCourseBLService scbl = new StudentCourseBLServiceImpl() ;
//		ArrayList<Course> list = scbl.getSelectedCourses(s) ;
//		Iterator<Course> it = list.iterator() ;
//		while(it.hasNext()){
//			System.out.println(it.next().getName());
//		}
		UserBLService ubl = new UserBLServiceImpl() ;
		Student s = ubl.loginValidity("SY","123");
		System.out.println(s.getName());
		System.exit(0);
	}
}