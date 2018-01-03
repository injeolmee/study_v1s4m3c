package dev.mvc.notice_reply;

public class NrepVO {
/*  
  NREPNO                          NUMBER(10)     PRIMARY KEY,
  NREPCONT                          VARCHAR2(1000)     NOT NULL,
  NREPNAME                          VARCHAR2(100)    NOT NULL,
  NREPDATE                          DATE     NOT NULL,
  MEMBERNO                          NUMBER(10)     NULL ,
  NOTICENO                          NUMBER(10)     NULL,
*/
  /**
   * 공지댓글번호
   */
  private int nrepno;
  /**
   * 공지댓글내용
   */
  private String nrepcont;
  /**
   * 공지댓글작성자
   */
  private String nrepname;
  /**
   * 공지댓글등록일
   */
  private String nrepdate;
  /**
   * 회원번호
   */
  private int memberno;
  /**
   * 공지사항번호
   */
  private int noticeno;
  /**
   * 현재 페이지
   */
  private int nowPage=1;
  
  
  public NrepVO() {
  }


  public int getNrepno() {
    return nrepno;
  }


  public void setNrepno(int nrepno) {
    this.nrepno = nrepno;
  }


  public String getNrepcont() {
    return nrepcont;
  }


  public void setNrepcont(String nrepcont) {
    this.nrepcont = nrepcont;
  }


  public String getNrepname() {
    return nrepname;
  }


  public void setNrepname(String nrepname) {
    this.nrepname = nrepname;
  }


  public String getNrepdate() {
    return nrepdate;
  }


  public void setNrepdate(String nrepdate) {
    this.nrepdate = nrepdate;
  }


  public int getMemberno() {
    return memberno;
  }


  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }


  public int getNoticeno() {
    return noticeno;
  }


  public void setNoticeno(int noticeno) {
    this.noticeno = noticeno;
  }


  public int getNowPage() {
    return nowPage;
  }


  public void setNowPage(int nowPage) {
    this.nowPage = nowPage;
  }




}
