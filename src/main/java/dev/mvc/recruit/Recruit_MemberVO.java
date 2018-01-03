package dev.mvc.recruit;

public class Recruit_MemberVO {

  /**
   * 모집번호
   */
  private int recruitno;
  /**
   * 승인여부
   */
  private String confirm;
  /**
   * 권한
   */
  private String std_auth;
  /**
   * 스터디 번호 foreign key
   */
  private int stdlist_no;
  /**
   * 회원번호 forgign key
   */
  private int memberno;

  //////////////////////////////////////////////////////////////////////////
  /**
   * 회원이름
   */
  private String memname;
  /**
   * 회원 성별
   */
  private String memgender;
  /**
   * 회원 이메일
   */
  private String mememail;

  public Recruit_MemberVO() {

  }

  public int getRecruitno() {
    return recruitno;
  }

  public void setRecruitno(int recruitno) {
    this.recruitno = recruitno;
  }

  public String getConfirm() {
    return confirm;
  }

  public void setConfirm(String confirm) {
    this.confirm = confirm;
  }

  public String getStd_auth() {
    return std_auth;
  }

  public void setStd_auth(String std_auth) {
    this.std_auth = std_auth;
  }

  public int getStdlist_no() {
    return stdlist_no;
  }

  public void setStdlist_no(int stdlist_no) {
    this.stdlist_no = stdlist_no;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public String getMemname() {
    return memname;
  }

  public void setMemname(String memname) {
    this.memname = memname;
  }

  public String getMemgender() {
    return memgender;
  }

  public void setMemgender(String memgender) {
    this.memgender = memgender;
  }

  public String getMememail() {
    return mememail;
  }

  public void setMememail(String mememail) {
    this.mememail = mememail;
  }

}
