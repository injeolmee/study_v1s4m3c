package dev.mvc.VO;

public class FreeVO {
  /**********************************/
  /* Table Name: 자유 게시판 */
  /**********************************/
/*  CREATE TABLE free(
      freeno                            NUMBER(7)    NOT NULL,
      freetitle                         VARCHAR2(100)    NOT NULL,
      freecontent                       VARCHAR2(4000)     NOT NULL,
      freename                          VARCHAR2(50)     NOT NULL,
      freedate                          DATE     NOT NULL,
      freecnt                           NUMBER(6)    DEFAULT 0     NOT NULL,
      freelike                          NUMBER(6)    DEFAULT 0     NOT NULL,
      freepasswd                        VARCHAR2(50)     NOT NULL,
      memberno                          NUMBER(10)     NULL ,
      cateno                            NUMBER(10)     NULL ,
      freeseqno                         NUMBER(10)     DEFAULT 1     NOT NULL,
      adminno                           NUMBER(10)     NULL 
  );

  COMMENT ON TABLE free is '자유 게시판';
  COMMENT ON COLUMN free.freeno is '게시판 번호';
  COMMENT ON COLUMN free.freetitle is '제목';
  COMMENT ON COLUMN free.freecontent is '내용';
  COMMENT ON COLUMN free.freename is '이름';
  COMMENT ON COLUMN free.freedate is '등록일';
  COMMENT ON COLUMN free.freecnt is '조회수';
  COMMENT ON COLUMN free.freelike is '추천수';
  COMMENT ON COLUMN free.freepasswd is '패스워드';
  COMMENT ON COLUMN free.memberno is '회원번호';
  COMMENT ON COLUMN free.cateno is '카테고리번호';
  COMMENT ON COLUMN free.freeseqno is '출력 권한';
  COMMENT ON COLUMN free.adminno is '관리자번호';*/
  
  private int freeno;     
  private String freetitle;  
  private String freecontent;
  private String freename;   
  private String freedate;   
  private int freecnt;    
  private int freelike;   
  private String freepasswd; 
  private int memberno;   
  private int cateno;     
  private int freeseqno;  
  private int adminno;
  
  public FreeVO() {
    // TODO Auto-generated constructor stub
  }

  public FreeVO(int freeno, String freetitle, String freecontent, String freename, String freedate, int freecnt,
      int freelike, String freepasswd, int memberno, int cateno, int freeseqno, int adminno) {
    super();
    this.freeno = freeno;
    this.freetitle = freetitle;
    this.freecontent = freecontent;
    this.freename = freename;
    this.freedate = freedate;
    this.freecnt = freecnt;
    this.freelike = freelike;
    this.freepasswd = freepasswd;
    this.memberno = memberno;
    this.cateno = cateno;
    this.freeseqno = freeseqno;
    this.adminno = adminno;
  }

  public int getFreeno() {
    return freeno;
  }

  public void setFreeno(int freeno) {
    this.freeno = freeno;
  }

  public String getFreetitle() {
    return freetitle;
  }

  public void setFreetitle(String freetitle) {
    this.freetitle = freetitle;
  }

  public String getFreecontent() {
    return freecontent;
  }

  public void setFreecontent(String freecontent) {
    this.freecontent = freecontent;
  }

  public String getFreename() {
    return freename;
  }

  public void setFreename(String freename) {
    this.freename = freename;
  }

  public String getFreedate() {
    return freedate;
  }

  public void setFreedate(String freedate) {
    this.freedate = freedate;
  }

  public int getFreecnt() {
    return freecnt;
  }

  public void setFreecnt(int freecnt) {
    this.freecnt = freecnt;
  }

  public int getFreelike() {
    return freelike;
  }

  public void setFreelike(int freelike) {
    this.freelike = freelike;
  }

  public String getFreepasswd() {
    return freepasswd;
  }

  public void setFreepasswd(String freepasswd) {
    this.freepasswd = freepasswd;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public int getCateno() {
    return cateno;
  }

  public void setCateno(int cateno) {
    this.cateno = cateno;
  }

  public int getFreeseqno() {
    return freeseqno;
  }

  public void setFreeseqno(int freeseqno) {
    this.freeseqno = freeseqno;
  }

  public int getAdminno() {
    return adminno;
  }

  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  
  
}
