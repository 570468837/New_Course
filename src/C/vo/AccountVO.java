package C.vo;

public class AccountVO {
	public AccountVO(String acc, String passwd, long createDate) {
		super();
		this.acc = acc;
		this.passwd = passwd;
		this.CreateDate = createDate;
	}
	String acc;
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public long getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(long createDate) {
		CreateDate = createDate;
	}
	String passwd;
	long CreateDate;
}
