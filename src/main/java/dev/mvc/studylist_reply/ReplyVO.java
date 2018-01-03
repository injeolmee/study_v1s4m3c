package dev.mvc.studylist_reply;

public class ReplyVO {

  /**
   * 댓글 번호
   */
  private int std_repno;
  /**
   * 댓글 내용
   */
  private String std_repcont;
  /**
   *  댓글 등록일
   */
  private String std_repdate;
  /**
   *  댓글 작성자
   */
  private String std_repname;
  /**
   * 회원번호
   * foreign key
   */
  private int memberno;
  /**
   * 스터디 그룹 번호
   * foreign key
   */
  private int stdlist_no;
  /**
   * 현재 페이지 
   */
  private int nowPage ;
  
  
  public ReplyVO(){
    
  }

  public int getStd_repno() {
    return std_repno;
  }

  public void setStd_repno(int std_repno) {
    this.std_repno = std_repno;
  }

  public String getStd_repcont() {
    return std_repcont;
  }

  public void setStd_repcont(String std_repcont) {
    this.std_repcont = std_repcont;
  }

  public String getStd_repdate() {
    return std_repdate;
  }

  public void setStd_repdate(String std_repdate) {
    this.std_repdate = std_repdate;
  }

  public String getStd_repname() {
    return std_repname;
  }

  public void setStd_repname(String std_repname) {
    this.std_repname = std_repname;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public int getStdlist_no() {
    return stdlist_no;
  }

  public void setStdlist_no(int stdlist_no) {
    this.stdlist_no = stdlist_no;
  }

  public int getNowPage() {
    return nowPage;
  }

  public void setNowPage(int nowPage) {
    this.nowPage = nowPage;
  }
  
  
}
