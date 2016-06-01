package B.Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;





/**
 * 
 * @author Rain
 *
 */
@SuppressWarnings("serial")
public class Student implements Serializable {
	public Student(){} 
	public Student(String id, String name, String gender, String major,
			String password) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.major = major;
		this.password = password;
	}
	public Student(String id){
		
	}
	private String id ; //学号
	private String name ;//姓名
	private String gender ;//性别
	private String major ;//专业
	private String password ;//密码
	
	private User user ;//管理账户，最多一个
	
	
	private Set<StudentCourse> students_courses =new HashSet<StudentCourse>();
	
	
	
	public Set<StudentCourse> getStudents_courses() {
		return students_courses;
	}
	public void setStudents_courses(Set<StudentCourse> students_courses) {
		this.students_courses = students_courses;
	}
	public String getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}