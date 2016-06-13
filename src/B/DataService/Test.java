package B.DataService;

import java.text.DecimalFormat;
import java.text.Format;

import B.Model.Course;
import B.Model.Student;



public class Test {
	public static void main(String[] args){
		Test t = new Test() ;
		t.insertStudents();
	}
	public void insertCourses(){
		CourseDataService cc = new CourseDataServiceImpl() ;
		Course c = new Course("0201", "组合数学", "Mr.Li", "classRoom1", "2", "2", '1') ;
		cc.add(c);
		Course c1 = new Course("0202","模式识别","Mr.Wang","classRoom2","2","2",'1') ;
		Course c2 = new Course("0203","随机算法","Mr.Wang","classRoom2","2","2",'1') ;
		Course c3 = new Course("0204","软件安全","Mr.Wang","classRoom2","2","2",'1') ;
		Course c4 = new Course("0205","计算思维","Mr.Wang","classRoom2","2","2",'1') ;
		Course c5 = new Course("0206","普通物理","Mr.Li","classRoom1","3","3",'0') ;
		Course c6 = new Course("0207","数理逻辑","Mr.Li","classRoom1","3","3",'0') ;
		Course c7 = new Course("0208","计算方法","Mr.Li","classRoom1","3","3",'0') ;
		Course c8 = new Course("0209","数据通信","Mr.Wang","classRoom2","3","3",'0') ;
		Course c9 = new Course("0210","图论","Mr.Li","classRoom1","2","2",'0') ;
		
		cc.add(c9);
		cc.add(c8);
		cc.add(c7);
		cc.add(c6);
		cc.add(c5);
		cc.add(c4);
		cc.add(c3);
		cc.add(c2);
		cc.add(c1);
		System.out.println("succeed");
	}

	String[] lastName={"贺","张","罗","李","吴","韩","丁","王","姚","盛"};
	String[] nameOneBoy = {"哲","江","壮","建","山","家","超","时","震","盛","雄","琛","钧"};
	String[] nameOneGirl = {"秋","珊","莎","锦","萍","青","荣","婷","姣","婉","娴","瑾","颖"};
	String[] nameTwoBoy = {"固","之","轮","良","海","朗","伯","言","若","鸣","朋","斌","梁"};
	String[] nameTwoGirl = {"聪","欣","兰","凤","洁","梅","琳","丹","云","莲","梦","雪","爱"};
	public void insertStudents(){
		StudentDataService sds = new StudentDataServiceImpl() ;
		DecimalFormat format = new DecimalFormat("00") ;
		for(int i=4;i<51;i++){
			String id = "000" + format.format(i) ;
			int gender = (int)Math.random()*2;
			String g = "" ;
			if(gender==1){
				g="F" ;
			}else{
				g="M"  ;
			}
			Student s = new Student(id, name(gender), g, "B", "123") ;
			System.out.println(s.getId()+" "+s.getName()+" "+s.getGender()+" "+s.getMajor()+" "+s.getPassword());
			sds.add(s) ;
		}
	}

	private String name(int gender){
		String n;
		if(gender==0)
			n = random(lastName)+random(nameOneGirl)+random(nameTwoGirl);
		else
			n = random(lastName)+random(nameOneBoy)+random(nameTwoBoy);
		return n;
	}
	private String random(String[] strs){
		int index = (int)(Math.random()*strs.length);
		if(index == strs.length)
			index--;
		return strs[index];
	}

}

