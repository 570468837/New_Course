package A.po;

import java.io.Serializable;

public class StudentPO  implements Serializable{
	public StudentPO(String sno, String snm, String sex, String sde, String acc) {
		super();
		this.Sno = sno;
		this.Snm = snm;
		this.Sex = sex;
		this.Sde = sde;
		this.acc = acc;
	}
	String Sno;
	String Snm;
	String Sex;
	String Sde;
	String acc;
	
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
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		acc = acc;
	}
	
}
