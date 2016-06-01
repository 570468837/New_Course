package B.Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Rain
 *	
 */
@SuppressWarnings("serial")
public class Course implements Serializable{
	public Course(){} ;
	public Course(String id, String name, String teacher, String classRoom,
			String credit, String hour, char ifShared) {
		this.id = id;
		this.name = name;
		this.teacher = teacher;
		this.classRoom = classRoom;
		this.credit = credit;
		this.hour = hour;
		this.ifShared = ifShared;
	}
	private String id ;//
	private String name ;//课程名称
	private String teacher ;//教师
	private String classRoom ;//上课地点
	private String credit ;//学分
	private String hour ;//课时
	private char ifShared ;//是否共享
	
	private Set<StudentCourse> students_courses=new HashSet<StudentCourse>() ;
	
	
	public Set<StudentCourse> getStudents_courses() {
		return students_courses;
	}
	public void setStudents_courses(Set<StudentCourse> students_courses) {
		this.students_courses = students_courses;
	}
	public String getId() {
		return id;
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
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}
	
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public char getIfShared() {
		return ifShared;
	}
	public void setIfShared(char ifShared) {
		this.ifShared = ifShared;
	}
	
}