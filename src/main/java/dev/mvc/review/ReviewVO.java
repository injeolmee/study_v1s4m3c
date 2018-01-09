package dev.mvc.review;

import org.springframework.web.multipart.MultipartFile;

public class ReviewVO {
/* 
  rvno                              NUMBER(10)     NOT NULL    PRIMARY KEY,
  rono                              NUMBER(10)     NOT NULL,
  adminno                           NUMBER(10)     NULL ,
  memberno                          NUMBER(10)     NULL ,
  rvdate                            DATE     NOT NULL,
  rvgood                            NUMBER(10)     NOT NULL,
  rvcont                            VARCHAR2(1000)     NOT NULL,
  rvfile1                           VARCHAR2(800)    NULL ,
  rvsize1                           NUMBER(10)    NULL ,
  rvthumb                           VARCHAR2(800)    NULL ,
  rvup                              VARCHAR2(20)     DEFAULT 0     NOT NULL,
  rvdown                            VARCHAR2(20)     DEFAULT 0     NOT NULL,
  rvpw                              VARCHAR2(30)     NOT NULL,
  rvcnt                             NUMBER(6)       DEFAULT 0          NOT NULL
*/
  
  
  private int rvno;
  private int rono;
  private String rvmemname;
  private int memberno;
  private String rvdate;
  private int rvgood;
  private String rvcont;
  private String rvfile1 = "";
  private long rvsize1 = 0;
  private String rvthumb = "";  
  private int rvup;
  private int rvcnt;
  
  private int count  = 0 ;
  
  /** Spring Framework에서 자동 주입되는 업로드 파일 객체,
  DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
  */  
  private MultipartFile file1MF;

  /** size1의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String size1Label;
  
  /** 현재 페이지는 1부터 시작 */
  private int nowPage = 1;
  
  
  

  public int getRvno() {
    return rvno;
  }

  public void setRvno(int rvno) {
    this.rvno = rvno;
  }

  public int getRono() {
    return rono;
  }

  public void setRono(int rono) {
    this.rono = rono;
  }
  
  
  public String getRvmemname() {
    return rvmemname;
  }

  public void setRvmemname(String rvmemname) {
    this.rvmemname = rvmemname;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }


  public String getRvdate() {
    return rvdate;
  }

  public void setRvdate(String rvdate) {
    this.rvdate = rvdate;
  }

  public int getRvgood() {
    return rvgood;
  }

  public void setRvgood(int rvgood) {
    this.rvgood = rvgood;
  }

  public String getRvcont() {
    return rvcont;
  }

  public void setRvcont(String rvcont) {
    this.rvcont = rvcont;
  }

  public String getRvfile1() {
    return rvfile1;
  }

  public void setRvfile1(String rvfile1) {
    this.rvfile1 = rvfile1;
  }

  public long getRvsize1() {
    return rvsize1;
  }

  public void setRvsize1(long rvsize1) {
    this.rvsize1 = rvsize1;
  }

  public String getRvthumb() {
    return rvthumb;
  }

  public void setRvthumb(String rvthumb) {
    this.rvthumb = rvthumb;
  }

  public int getRvup() {
    return rvup;
  }

  public void setRvup(int rvup) {
    this.rvup = rvup;
  }


  public int getRvcnt() {
    return rvcnt;
  }

  public void setRvcnt(int rvcnt) {
    this.rvcnt = rvcnt;
  }

  public MultipartFile getFile1MF() {
    return file1MF;
  }

  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }

  public String getSize1Label() {
    return size1Label;
  }

  public void setSize1Label(String size1Label) {
    this.size1Label = size1Label;
  }

  public int getNowPage() {
    return nowPage;
  }

  public void setNowPage(int nowPage) {
    this.nowPage = nowPage;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
  
 

}
