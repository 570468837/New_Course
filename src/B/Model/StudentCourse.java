package B.Model;

import java.io.Serializable;



/**
 * 
 * @author Rain
 *
 */
@SuppressWarnings("serial")
public class StudentCourse implements Serializable{
	public StudentCourse(){}
	public StudentCourse(Student student, Course course) {
		this.student = student;
		this.course = course;
	}
	private String id;
	private Student student;
	private Course course;
	private String grade;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
}