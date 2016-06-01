package C.po;

import java.io.Serializable;

public class CoursePO  implements Serializable{
	public CoursePO(String cno, String cnm, int ctm, int cpt, String tec, String pla, String share) {
		super();
		this.Cno = cno;
		this.Cnm = cnm;
		this.Ctm = ctm;
		this.Cpt = cpt;
		this.Tec = tec;
		this.Pla = pla;
		this.share = share;
	}
	
	String Cno;
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
	public int getCtm() {
		return Ctm;
	}
	public void setCtm(int ctm) {
		Ctm = ctm;
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
	public String getShare() {
		return share;
	}
	public void setShare(String share) {
		this.share = share;
	}

	String Cnm;
	int Ctm;
	int Cpt;
	String Tec;
	String Pla;
	String share;
}
