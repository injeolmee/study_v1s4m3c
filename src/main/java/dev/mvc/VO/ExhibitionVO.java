package dev.mvc.VO;

public class ExhibitionVO {
  /**********************************/
  /* Table Name: 공모전 정보 목록 */
  /**********************************/
/*  CREATE TABLE exhibition(
      infono_e                          NUMBER(10)     NOT NULL,
      infotitle_e                       VARCHAR2(100)    NOT NULL,
      infolocal_e                       VARCHAR2(50)     NOT NULL,
      infocont_e                        VARCHAR2(3000)     NOT NULL,
      infotel_e                         VARCHAR2(30)     NOT NULL,
      iname_e                           VARCHAR2(20)     NOT NULL,
      idate_e                           DATE     NOT NULL,
      infofile_e                        VARCHAR2(100)    NOT NULL,
      infofile_te                       VARCHAR2(100)    NOT NULL,
      infosize_e                        NUMBER(10)     NOT NULL,
      infocnt_e                         NUMBER(10)     NOT NULL,
      adminno                           NUMBER(10)     NULL ,
      memberno                          NUMBER(10)     NULL ,
      cateno                            NUMBER(10)     NULL 
  );

  COMMENT ON TABLE exhibition is '공모전 정보 목록';
  COMMENT ON COLUMN exhibition.infono_e is '공모전 정보 등록 번호';
  COMMENT ON COLUMN exhibition.infotitle_e is '등록 제목';
  COMMENT ON COLUMN exhibition.infolocal_e is '등록 지역';
  COMMENT ON COLUMN exhibition.infocont_e is '정보 내용';
  COMMENT ON COLUMN exhibition.infotel_e is '전화번호';
  COMMENT ON COLUMN exhibition.iname_e is '등록자 이름';
  COMMENT ON COLUMN exhibition.idate_e is '정보 등록일';
  COMMENT ON COLUMN exhibition.infofile_e is '첨부 파일';
  COMMENT ON COLUMN exhibition.infofile_te is '첨부파일 썸네일';
  COMMENT ON COLUMN exhibition.infosize_e is '첨부파일 사이즈';
  COMMENT ON COLUMN exhibition.infocnt_e is '정보 조회수';
  COMMENT ON COLUMN exhibition.adminno is '관리자번호';
  COMMENT ON COLUMN exhibition.memberno is '회원번호';
  COMMENT ON COLUMN exhibition.cateno is '카테고리번호';*/
  
  private int infono_e;   
  private String infotitle_e;
  private String infolocal_e;
  private String infocont_e; 
  private String infotel_e;  
  private String iname_e;    
  private String idate_e;    
  private String infofile_e; 
  private String infofile_te;
  private int infosize_e; 
  private int infocnt_e;  
  private int adminno;    
  private int memberno;   
  private int cateno;
  
  public ExhibitionVO() {
    // TODO Auto-generated constructor stub
  }

  public ExhibitionVO(int infono_e, String infotitle_e, String infolocal_e, String infocont_e, String infotel_e,
      String iname_e, String idate_e, String infofile_e, String infofile_te, int infosize_e, int infocnt_e, int adminno,
      int memberno, int cateno) {
    super();
    this.infono_e = infono_e;
    this.infotitle_e = infotitle_e;
    this.infolocal_e = infolocal_e;
    this.infocont_e = infocont_e;
    this.infotel_e = infotel_e;
    this.iname_e = iname_e;
    this.idate_e = idate_e;
    this.infofile_e = infofile_e;
    this.infofile_te = infofile_te;
    this.infosize_e = infosize_e;
    this.infocnt_e = infocnt_e;
    this.adminno = adminno;
    this.memberno = memberno;
    this.cateno = cateno;
  }

  public int getInfono_e() {
    return infono_e;
  }

  public void setInfono_e(int infono_e) {
    this.infono_e = infono_e;
  }

  public String getInfotitle_e() {
    return infotitle_e;
  }

  public void setInfotitle_e(String infotitle_e) {
    this.infotitle_e = infotitle_e;
  }

  public String getInfolocal_e() {
    return infolocal_e;
  }

  public void setInfolocal_e(String infolocal_e) {
    this.infolocal_e = infolocal_e;
  }

  public String getInfocont_e() {
    return infocont_e;
  }

  public void setInfocont_e(String infocont_e) {
    this.infocont_e = infocont_e;
  }

  public String getInfotel_e() {
    return infotel_e;
  }

  public void setInfotel_e(String infotel_e) {
    this.infotel_e = infotel_e;
  }

  public String getIname_e() {
    return iname_e;
  }

  public void setIname_e(String iname_e) {
    this.iname_e = iname_e;
  }

  public String getIdate_e() {
    return idate_e;
  }

  public void setIdate_e(String idate_e) {
    this.idate_e = idate_e;
  }

  public String getInfofile_e() {
    return infofile_e;
  }

  public void setInfofile_e(String infofile_e) {
    this.infofile_e = infofile_e;
  }

  public String getInfofile_te() {
    return infofile_te;
  }

  public void setInfofile_te(String infofile_te) {
    this.infofile_te = infofile_te;
  }

  public int getInfosize_e() {
    return infosize_e;
  }

  public void setInfosize_e(int infosize_e) {
    this.infosize_e = infosize_e;
  }

  public int getInfocnt_e() {
    return infocnt_e;
  }

  public void setInfocnt_e(int infocnt_e) {
    this.infocnt_e = infocnt_e;
  }

  public int getAdminno() {
    return adminno;
  }

  public void setAdminno(int adminno) {
    this.adminno = adminno;
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
  
  
}
