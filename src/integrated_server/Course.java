package integrated_server;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Rain
 *	
 */
@SuppressWarnings("serial")
public class Course implements Serializable{
	
	public Course(String id, String name, String score, String teacher,
			String location) {
		super();
		this.id = id;
		this.name = name;
		this.score = score;
		this.teacher = teacher;
		this.location = location;
	}
	
	private String id ;
	private String name ;//课程名称
	private String score ;//学分
	private String teacher ;//教师
	private String location;
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
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}