package A.VO;

import java.io.Serializable;

public class CourseSelectionVO  implements Serializable{
	public CourseSelectionVO(String cno, String sno, int grd) {
		super();
		this.Cno = cno;
		this.Sno = sno;
		this.Grd = grd;
	}
	String Cno;
	public String getCno() {
		return Cno;
	}
	public void setCno(String cno) {
		Cno = cno;
	}
	public String getSno() {
		return Sno;
	}
	public void setSno(String sno) {
		Sno = sno;
	}
	public int getGrd() {
		return Grd;
	}
	public void setGrd(int grd) {
		Grd = grd;
	}
	String Sno;
	int Grd;
	
}
