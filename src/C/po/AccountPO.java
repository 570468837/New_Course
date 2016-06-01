package C.po;

import java.io.Serializable;

public class AccountPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AccountPO(String acc, String passwd, long createDate) {
		super();
		this.acc = acc;
		this.passwd = passwd;
		this.CreateDate = createDate;
	}
	String acc =null;
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
	String passwd = null;
	long CreateDate = 0;
	
	public static void main(String[] args) {
		AccountPO[] po = new AccountPO[20];
    	po[0] = new AccountPO("w","w",000000000);
	}
}
