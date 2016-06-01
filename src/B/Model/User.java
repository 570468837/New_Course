package B.Model;

import java.io.Serializable;

/**
 * 
 * @author Rain
 *
 */
@SuppressWarnings("serial")
public class User implements Serializable{
	private String userName ;//账户
	private String password ;//密码
	private int rank ;//级别
	public User(){}
	public User(String userName, String password, int rank, Student object) {
		super();
		this.userName = userName;
		this.password = password;
		this.rank = rank;
		this.object = object;
	}
	
	private Student object ;//客体，关联学生
	public String getUserName() {
		return userName;
	}
	public Student getObject() {
		return object;
	}
	public void setObject(Student student) {
		this.object = student;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}

	
}
