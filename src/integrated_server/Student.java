package integrated_server;

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
	
	public Student(String id, String name, String gender, String major,
			String password) {
		super();
		this.id = id;
		this.name = name;
		this.major = major;
	}
	public Student(String id){
		
	}
	private String id ; //学号
	private String name ;//姓名
	private String sex ;//性别
	private String major ;//专业
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
	
}