package dev.mvc.VO;

public class StudyroomVO {
  /**********************************/
  /* Table Name: 스터디룸 */
  /**********************************/
//  CREATE TABLE studyroom(
//      rono                              NUMBER(10)     NOT NULL,
//      roname                            VARCHAR2(100)    NOT NULL,
//      rotel                             VARCHAR2(30)     NOT NULL,
//      rosite                            VARCHAR2(200)    NULL ,
//      rodate                            VARCHAR2(100)    NULL ,
//      romap                             VARCHAR2(300)    NOT NULL,
//      rolocation                        VARCHAR2(20)     NOT NULL,
//      rocost                            VARCHAR2(50)     NULL ,
//      roimg                             VARCHAR2(800)    NULL ,
//      rocontent                         VARCHAR2(4000)     NOT NULL,
//      rocount                           VARCHAR2(300)    NULL ,
//      rooption                          VARCHAR2(150)    NOT NULL,
//      reviewno                          NUMBER(10)     NULL ,
//      adminno                           NUMBER(10)     NULL ,
//      cateno                            NUMBER(10)     NULL 
//  );
//
//  COMMENT ON TABLE studyroom is '스터디룸';
//  COMMENT ON COLUMN studyroom.rono is '스터티룸 등록 번호';
//  COMMENT ON COLUMN studyroom.roname is '스터디룸 상호';
//  COMMENT ON COLUMN studyroom.rotel is '스터디룸 전화';
//  COMMENT ON COLUMN studyroom.rosite is '스터디룸 홈페이지';
//  COMMENT ON COLUMN studyroom.rodate is '스터디룸 운영요일/시간';
//  COMMENT ON COLUMN studyroom.romap is '스터디룸 지도';
//  COMMENT ON COLUMN studyroom.rolocation is '스터디룸 위치';
//  COMMENT ON COLUMN studyroom.rocost is '스터디룸 비용';
//  COMMENT ON COLUMN studyroom.roimg is '스터디룸 이미지';
//  COMMENT ON COLUMN studyroom.rocontent is '스터디룸 내용';
//  COMMENT ON COLUMN studyroom.rocount is '스터디룸 룸인원';
//  COMMENT ON COLUMN studyroom.rooption is '스터디룸 옵션';
//  COMMENT ON COLUMN studyroom.reviewno is '스터디룸 리뷰 등록 번호';
//  COMMENT ON COLUMN studyroom.adminno is '관리자번호';
//  COMMENT ON COLUMN studyroom.cateno is '카테고리번호';
  
  private int rono;      
  private String roname;    
  private String rotel;     
  private String rosite;    
  private String rodate;    
  private String romap;     
  private String rolocation;
  private String rocost;    
  private String roimg;     
  private String rocontent; 
  private String rocount;   
  private String rooption;  
  private int reviewno;  
  private int adminno;   
  private int cateno; 
  
  public StudyroomVO() {
    // TODO Auto-generated constructor stub
  }

  public StudyroomVO(int rono, String roname, String rotel, String rosite, String rodate, String romap,
      String rolocation, String rocost, String roimg, String rocontent, String rocount, String rooption, int reviewno,
      int adminno, int cateno) {
    super();
    this.rono = rono;
    this.roname = roname;
    this.rotel = rotel;
    this.rosite = rosite;
    this.rodate = rodate;
    this.romap = romap;
    this.rolocation = rolocation;
    this.rocost = rocost;
    this.roimg = roimg;
    this.rocontent = rocontent;
    this.rocount = rocount;
    this.rooption = rooption;
    this.reviewno = reviewno;
    this.adminno = adminno;
    this.cateno = cateno;
  }

  public int getRono() {
    return rono;
  }

  public void setRono(int rono) {
    this.rono = rono;
  }

  public String getRoname() {
    return roname;
  }

  public void setRoname(String roname) {
    this.roname = roname;
  }

  public String getRotel() {
    return rotel;
  }

  public void setRotel(String rotel) {
    this.rotel = rotel;
  }

  public String getRosite() {
    return rosite;
  }

  public void setRosite(String rosite) {
    this.rosite = rosite;
  }

  public String getRodate() {
    return rodate;
  }

  public void setRodate(String rodate) {
    this.rodate = rodate;
  }

  public String getRomap() {
    return romap;
  }

  public void setRomap(String romap) {
    this.romap = romap;
  }

  public String getRolocation() {
    return rolocation;
  }

  public void setRolocation(String rolocation) {
    this.rolocation = rolocation;
  }

  public String getRocost() {
    return rocost;
  }

  public void setRocost(String rocost) {
    this.rocost = rocost;
  }

  public String getRoimg() {
    return roimg;
  }

  public void setRoimg(String roimg) {
    this.roimg = roimg;
  }

  public String getRocontent() {
    return rocontent;
  }

  public void setRocontent(String rocontent) {
    this.rocontent = rocontent;
  }

  public String getRocount() {
    return rocount;
  }

  public void setRocount(String rocount) {
    this.rocount = rocount;
  }

  public String getRooption() {
    return rooption;
  }

  public void setRooption(String rooption) {
    this.rooption = rooption;
  }

  public int getReviewno() {
    return reviewno;
  }

  public void setReviewno(int reviewno) {
    this.reviewno = reviewno;
  }

  public int getAdminno() {
    return adminno;
  }

  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }

  public int getCateno() {
    return cateno;
  }

  public void setCateno(int cateno) {
    this.cateno = cateno;
  }
  
  
}
