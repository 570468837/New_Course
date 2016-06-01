package C.po;

import java.io.Serializable;

public class StudentPO  implements Serializable{
	public StudentPO(String sno, String snm, String sex, String sde, String pwd) {
		super();
		this.Sno = sno;
		this.Snm = snm;
		this.Sex = sex;
		this.Sde = sde;
		this.Pwd = pwd;
	}
	String Sno;
	String Snm;
	String Sex;
	String Sde;
	String Pwd;
	
	public String getSno() {
		return Sno;
	}
	public void setSno(String sno) {
		Sno = sno;
	}
	public String getSnm() {
		return Snm;
	}
	public void setSnm(String snm) {
		Snm = snm;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getSde() {
		return Sde;
	}
	public void setSde(String sde) {
		Sde = sde;
	}
	public String getPwd() {
		return Pwd;
	}
	public void setPwd(String pwd) {
		Pwd = pwd;
	}
	
}
