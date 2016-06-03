package A.VO;

import java.io.Serializable;

public class AccountVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AccountVO(String acc, String passwd, char authority) {
		super();
		this.acc = acc;
		this.passwd = passwd;
		this.authority = authority;
	}
	char authority;
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
	public char getCreateDate() {
		return authority;
	}
	public void setCreateDate(char authority) {
		this.authority = authority;
	}
	String passwd = null;
	
	public static void main(String[] args) {
		AccountVO[] po = new AccountVO[20];
    	po[0] = new AccountVO("w","w",'0');
	}
}
