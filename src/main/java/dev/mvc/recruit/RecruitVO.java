package dev.mvc.recruit;

public class RecruitVO {
  
  /**
   *  모집번호
   */
  private int recruitno;
  /**
   * 승인 여부
   *   승락 'Y' 거절 'N' 미확인 'U'
   */
  private String confirm;
  /**
   *  권한 
   *   팀장이 스터디그룹 모집하게 되면
   *   팀장권한은 L 로 입력
   *   
   *   회원들이 신청누르면 U 권한으로 입력
   *   
   *   팀장이 승락하면 회원 U -> T 으로 변경
   */
  private String std_auth;
  /**
   *  스터디리스트 번호
   *  FOREING KEY
   */
  private int stdlist_no;
  /**
   * 회원번호
   * FOREIGN KEY
   */
  private int memberno;
  
  public RecruitVO() {
 
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
  
  
}
