package common;

import java.io.Serializable;

public class FileInformationSev implements FileInformation{
	private String name = null ;
	  private byte[] content = null ;
	  public String getName() {
	     return name ;
	  }
	  public byte[] getContent() {
	     return content;
	  }
	  public void setInformation(String name, byte[] content) {
	     this.name = name ;
	     this.content = content ;
	  }
	}  