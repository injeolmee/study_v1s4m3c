package dev.mvc.recruit;

public class Recruit_MemberVO {

  /**
   * ������ȣ
   */
  private int recruitno;
  /**
   * ���ο���
   */
  private String confirm;
  /**
   * ����
   */
  private String std_auth;
  /**
   * ���͵� ��ȣ foreign key
   */
  private int stdlist_no;
  /**
   * ȸ����ȣ forgign key
   */
  private int memberno;

  //////////////////////////////////////////////////////////////////////////
  /**
   * ȸ���̸�
   */
  private String memname;
  /**
   * ȸ�� ����
   */
  private String memgender;
  /**
   * ȸ�� �̸���
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
