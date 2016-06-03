package A.VO;

import java.io.Serializable;

public class CourseVO  implements Serializable{

	String Cnm;
	int Cpt;
	String Cno;
	String Tec;
	String Pla;
	int share;
	public CourseVO(String cno, String cnm, int ctm, int cpt, String tec, String pla, int share) {
		super();
		this.Cno = cno;
		this.Cnm = cnm;
		this.Cpt = cpt;
		this.Tec = tec;
		this.Pla = pla;
		this.share = share;
	}
	

	public String getCno() {
		return Cno;
	}
	public void setCno(String cno) {
		Cno = cno;
	}
	public String getCnm() {
		return Cnm;
	}
	public void setCnm(String cnm) {
		Cnm = cnm;
	}
	
	public int getCpt() {
		return Cpt;
	}
	public void setCpt(int cpt) {
		Cpt = cpt;
	}
	public String getTec() {
		return Tec;
	}
	public void setTec(String tec) {
		Tec = tec;
	}
	public String getPla() {
		return Pla;
	}
	public void setPla(String pla) {
		Pla = pla;
	}
	public int getShare() {
		return share;
	}
	public void setShare(int share) {
		this.share = share;
	}

}
