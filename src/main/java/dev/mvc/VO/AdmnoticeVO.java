package dev.mvc.VO;

public class AdmnoticeVO {
/*  CREATE TABLE admnotice(
      anoticeno                         NUMBER(10)     NOT NULL,
      anoticetitle                      VARCHAR2(200)    NOT NULL,
      anoticecontent                    VARCHAR2(2000)     NOT NULL,
      anoticecnt                        NUMBER(10)     NOT NULL,
      anoticedate                       DATE     NOT NULL,
      adminno                           NUMBER(10)     NOT NULL,
      cateno                            NUMBER(10)     NULL 
  );

  COMMENT ON TABLE admnotice is '관리자 공지사항 게시판';
  COMMENT ON COLUMN admnotice.anoticeno is '공지사항번호';
  COMMENT ON COLUMN admnotice.anoticetitle is '공지사항제목';
  COMMENT ON COLUMN admnotice.anoticecontent is '공지사항내용';
  COMMENT ON COLUMN admnotice.anoticecnt is '공지사항조회수';
  COMMENT ON COLUMN admnotice.anoticedate is '공지사항등록일';
  COMMENT ON COLUMN admnotice.adminno is '관리자번호';
  COMMENT ON COLUMN admnotice.cateno is '카테고리번호';*/
  
  private int anoticeno;     
  private String anoticetitle;  
  private String anoticecontent;
  private int anoticecnt;    
  private String anoticedate;   
  private String adminno;       
  private String cateno;
  
  public AdmnoticeVO() {
    // TODO Auto-generated constructor stub
  }
  
  public AdmnoticeVO(int anoticeno, String anoticetitle, String anoticecontent, int anoticecnt, String anoticedate,
      String adminno, String cateno) {
    super();
    this.anoticeno = anoticeno;
    this.anoticetitle = anoticetitle;
    this.anoticecontent = anoticecontent;
    this.anoticecnt = anoticecnt;
    this.anoticedate = anoticedate;
    this.adminno = adminno;
    this.cateno = cateno;
  }
  public int getAnoticeno() {
    return anoticeno;
  }
  public void setAnoticeno(int anoticeno) {
    this.anoticeno = anoticeno;
  }
  public String getAnoticetitle() {
    return anoticetitle;
  }
  public void setAnoticetitle(String anoticetitle) {
    this.anoticetitle = anoticetitle;
  }
  public String getAnoticecontent() {
    return anoticecontent;
  }
  public void setAnoticecontent(String anoticecontent) {
    this.anoticecontent = anoticecontent;
  }
  public int getAnoticecnt() {
    return anoticecnt;
  }
  public void setAnoticecnt(int anoticecnt) {
    this.anoticecnt = anoticecnt;
  }
  public String getAnoticedate() {
    return anoticedate;
  }
  public void setAnoticedate(String anoticedate) {
    this.anoticedate = anoticedate;
  }
  public String getAdminno() {
    return adminno;
  }
  public void setAdminno(String adminno) {
    this.adminno = adminno;
  }
  public String getCateno() {
    return cateno;
  }
  public void setCateno(String cateno) {
    this.cateno = cateno;
  }        
  
  
}
