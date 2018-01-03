package dev.mvc.VO;

public class SharedVO {
  /**********************************/
  /* Table Name: 자료실 */
  /**********************************/
/*  CREATE TABLE shared(
      sharedno                          NUMBER(7)    NOT NULL,
      sharedtitle                       VARCHAR2(100)    NOT NULL,
      sharedcontent                     VARCHAR2(4000)     NOT NULL,
      sharedname                        VARCHAR2(50)     NOT NULL,
      sharedyoutube                     VARCHAR2(500)    NULL ,
      sharedseqno                       NUMBER(10)     NOT NULL,
      sharedmp3                         VARCHAR2(500)    NULL ,
      sharedmp4                         VARCHAR2(500)    NULL ,
      sharedfile                        VARCHAR2(500)    NULL ,
      sharedfstor                       VARCHAR2(500)    NULL ,
      sharedsize                        NUMBER(30)     DEFAULT 0     NOT NULL,
      shareddate                        DATE     NOT NULL,
      sharedcnt                         NUMBER(6)    DEFAULT 0     NOT NULL,
      sharedlike                        NUMBER(6)    DEFAULT 0     NOT NULL,
      sharedpasswd                      VARCHAR2(50)     NOT NULL,
      memberno                          NUMBER(10)     NULL ,
      cateno                            NUMBER(10)     NULL ,
      adminno                           NUMBER(10)     NULL 
  );

  COMMENT ON TABLE shared is '자료실';
  COMMENT ON COLUMN shared.sharedno is '게시판 번호';
  COMMENT ON COLUMN shared.sharedtitle is '제목';
  COMMENT ON COLUMN shared.sharedcontent is '내용';
  COMMENT ON COLUMN shared.sharedname is '이름';
  COMMENT ON COLUMN shared.sharedyoutube is '유튜브';
  COMMENT ON COLUMN shared.sharedseqno is '출력 순서';
  COMMENT ON COLUMN shared.sharedmp3 is 'MP3';
  COMMENT ON COLUMN shared.sharedmp4 is 'MP4';
  COMMENT ON COLUMN shared.sharedfile is '파일';
  COMMENT ON COLUMN shared.sharedfstor is '실제 파일명';
  COMMENT ON COLUMN shared.sharedsize is '파일 크기';
  COMMENT ON COLUMN shared.shareddate is '등록일';
  COMMENT ON COLUMN shared.sharedcnt is '조회수';
  COMMENT ON COLUMN shared.sharedlike is '추천수';
  COMMENT ON COLUMN shared.sharedpasswd is '패스워드';
  COMMENT ON COLUMN shared.memberno is '회원번호';
  COMMENT ON COLUMN shared.cateno is '카테고리번호';
  COMMENT ON COLUMN shared.adminno is '관리자번호';*/
  
  private int sharedno;     
  private String sharedtitle;  
  private String sharedcontent;
  private String sharedname;   
  private String sharedyoutube;
  private int sharedseqno;  
  private String sharedmp3;    
  private String sharedmp4;    
  private String sharedfile;   
  private String sharedfstor;  
  private int sharedsize;   
  private String shareddate;   
  private int sharedcnt;    
  private int sharedlike;   
  private String sharedpasswd; 
  private int memberno;     
  private int cateno;       
  private int adminno;
  
  public SharedVO() {
    // TODO Auto-generated constructor stub
  }

  public SharedVO(int sharedno, String sharedtitle, String sharedcontent, String sharedname, String sharedyoutube,
      int sharedseqno, String sharedmp3, String sharedmp4, String sharedfile, String sharedfstor, int sharedsize,
      String shareddate, int sharedcnt, int sharedlike, String sharedpasswd, int memberno, int cateno, int adminno) {
    super();
    this.sharedno = sharedno;
    this.sharedtitle = sharedtitle;
    this.sharedcontent = sharedcontent;
    this.sharedname = sharedname;
    this.sharedyoutube = sharedyoutube;
    this.sharedseqno = sharedseqno;
    this.sharedmp3 = sharedmp3;
    this.sharedmp4 = sharedmp4;
    this.sharedfile = sharedfile;
    this.sharedfstor = sharedfstor;
    this.sharedsize = sharedsize;
    this.shareddate = shareddate;
    this.sharedcnt = sharedcnt;
    this.sharedlike = sharedlike;
    this.sharedpasswd = sharedpasswd;
    this.memberno = memberno;
    this.cateno = cateno;
    this.adminno = adminno;
  }

  public int getSharedno() {
    return sharedno;
  }

  public void setSharedno(int sharedno) {
    this.sharedno = sharedno;
  }

  public String getSharedtitle() {
    return sharedtitle;
  }

  public void setSharedtitle(String sharedtitle) {
    this.sharedtitle = sharedtitle;
  }

  public String getSharedcontent() {
    return sharedcontent;
  }

  public void setSharedcontent(String sharedcontent) {
    this.sharedcontent = sharedcontent;
  }

  public String getSharedname() {
    return sharedname;
  }

  public void setSharedname(String sharedname) {
    this.sharedname = sharedname;
  }

  public String getSharedyoutube() {
    return sharedyoutube;
  }

  public void setSharedyoutube(String sharedyoutube) {
    this.sharedyoutube = sharedyoutube;
  }

  public int getSharedseqno() {
    return sharedseqno;
  }

  public void setSharedseqno(int sharedseqno) {
    this.sharedseqno = sharedseqno;
  }

  public String getSharedmp3() {
    return sharedmp3;
  }

  public void setSharedmp3(String sharedmp3) {
    this.sharedmp3 = sharedmp3;
  }

  public String getSharedmp4() {
    return sharedmp4;
  }

  public void setSharedmp4(String sharedmp4) {
    this.sharedmp4 = sharedmp4;
  }

  public String getSharedfile() {
    return sharedfile;
  }

  public void setSharedfile(String sharedfile) {
    this.sharedfile = sharedfile;
  }

  public String getSharedfstor() {
    return sharedfstor;
  }

  public void setSharedfstor(String sharedfstor) {
    this.sharedfstor = sharedfstor;
  }

  public int getSharedsize() {
    return sharedsize;
  }

  public void setSharedsize(int sharedsize) {
    this.sharedsize = sharedsize;
  }

  public String getShareddate() {
    return shareddate;
  }

  public void setShareddate(String shareddate) {
    this.shareddate = shareddate;
  }

  public int getSharedcnt() {
    return sharedcnt;
  }

  public void setSharedcnt(int sharedcnt) {
    this.sharedcnt = sharedcnt;
  }

  public int getSharedlike() {
    return sharedlike;
  }

  public void setSharedlike(int sharedlike) {
    this.sharedlike = sharedlike;
  }

  public String getSharedpasswd() {
    return sharedpasswd;
  }

  public void setSharedpasswd(String sharedpasswd) {
    this.sharedpasswd = sharedpasswd;
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

  public int getAdminno() {
    return adminno;
  }

  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  
  
}
