package dev.mvc.qnareply;

/*
  qrno                              NUMBER(7)    NOT NULL    PRIMARY KEY,
  qrcont                            VARCHAR2(500)    NOT NULL,
  qrname                            VARCHAR2(100)    NOT NULL,
  qrdate                            DATE     NOT NULL,
  qrgrpno                           NUMBER(7)    NOT NULL,
  qrindent                          NUMBER(7)    DEFAULT 0     NOT NULL,
  qransnum                          NUMBER(7)    DEFAULT 0     NOT NULL,
  qrseqno                           NUMBER(5)    DEFAULT 1     NULL,
  qnano                             NUMBER(7)    NULL ,
  adminno                           NUMBER(10)     NULL ,
  memberno                          NUMBER(10)     NULL
 */

public class QnareplyVO {
  /* 댓글 번호 */
  private int qrno;
  /* 내용 */
  private String qrcont;
  /* 작성자 */
  private String qrname;
  /* 등록일 */
  private String qrdate;
  /* 게시글 번호 */
  private int qnano;
  /* 회원 번호 */
  private int memberno;
  /* 관리자 번호 */
  private int adminno;
  /* 현재 페이지 */
  private int nowPage;

  
  public int getQrno() {
    return qrno;
  }
  public void setQrno(int qrno) {
    this.qrno = qrno;
  }
  public String getQrcont() {
    return qrcont;
  }
  public void setQrcont(String qrcont) {
    this.qrcont = qrcont;
  }
  public String getQrname() {
    return qrname;
  }
  public void setQrname(String qrname) {
    this.qrname = qrname;
  }
  public String getQrdate() {
    return qrdate;
  }
  public void setQrdate(String qrdate) {
    this.qrdate = qrdate;
  }
 
  public int getQnano() {
    return qnano;
  }
  public void setQnano(int qnano) {
    this.qnano = qnano;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  public int getNowPage() {
    return nowPage;
  }
  public void setNowPage(int nowPage) {
    this.nowPage = nowPage;
  }

  
}
