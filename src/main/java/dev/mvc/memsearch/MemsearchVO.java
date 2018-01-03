package dev.mvc.memsearch;

public class MemsearchVO {
  private String memid="0";          // 회원 아이디
  private String memname="";        // 회원 이름
  
  public MemsearchVO() {
    // TODO Auto-generated constructor stub
  }
  
  public String getMemid() {
    return memid;
  }
  public void setMemid(String memid) {
    this.memid = memid;
  }
  public String getMemname() {
    return memname;
  }
  public void setMemname(String memname) {
    this.memname = memname;
  }
  
}
