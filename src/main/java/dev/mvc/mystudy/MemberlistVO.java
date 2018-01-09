package dev.mvc.mystudy;

public class MemberlistVO {
  private int memberno;
  private String membername;
  private String passwd;
  
  public MemberlistVO() {
    // TODO Auto-generated constructor stub
  }
  
  public MemberlistVO(int memberno, String membername, String passwd) {
    super();
    this.memberno = memberno;
    this.membername = membername;
    this.passwd = passwd;
  }
  
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public String getMembername() {
    return membername;
  }
  public void setMembername(String membername) {
    this.membername = membername;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  
  
}
