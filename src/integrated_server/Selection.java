package integrated_server;

import java.io.Serializable;
/**
 * 
 * @author Rain
 *
 */
@SuppressWarnings("serial")
public class Selection implements Serializable{
	
	public Selection(String sid, Student cid, String score) {
		this.sid = sid;
		this.cid = cid;
		this.score = score;
	}
	
	private String sid;
	private Student cid;
	private String score;
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public Student getCid() {
		return cid;
	}
	public void setCid(Student cid) {
		this.cid = cid;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	
}