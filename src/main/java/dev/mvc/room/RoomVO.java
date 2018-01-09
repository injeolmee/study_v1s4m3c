package dev.mvc.room;

import org.springframework.web.multipart.MultipartFile;

public class RoomVO {
  /*
  rono                              NUMBER(10)     NOT NULL    PRIMARY KEY,
  grpno                            NUMBER(10)  NULL,
  adminno                           NUMBER(10)     NULL ,
  cateno                            NUMBER(10)     NULL ,
  roname                            VARCHAR2(100)    NOT NULL,
  rotel                             VARCHAR2(30)     NOT NULL,
  rosite                            VARCHAR2(200)    NULL ,
  rorunday                          VARCHAR2(100)    NULL ,
  romap                             VARCHAR2(300)    NOT NULL,
  rolocation                        VARCHAR2(20)     NOT NULL,
  rocity                        VARCHAR2(20)     NOT NULL,
  rogu                            VARCHAR2(20)     NOT NULL,
  rodong                        VARCHAR2(100)      NULL,
  ronalo                         VARCHAR2(200)      NULL,
  rocost                            VARCHAR2(50)     NULL ,
  rofile1                           VARCHAR2(800)    NULL ,
  rosize1                           NUMBER(10)    NULL ,
  rothumb                           VARCHAR2(800)    NULL ,
  rocontent                         VARCHAR2(1000)     NOT NULL,
  rocount                           VARCHAR2(300)    NULL ,
  rooption                          VARCHAR2(150)    NOT NULL,
  */
  
  /** �� ��ȣ */
  private int rono;
  
  /** ī�װ� �׷� ��ȣ */
  private int grpno;
 
  /** ������ ��ȣ */
  private int adminno;
  
  /** �Խ��� ��ȣ */
  private int cateno;
  
  /** ���͵�� ��ȣ */
  private String roname="";
  
  /** ���͵�� ��ȭ��ȣ */
  private String rotel;
  
  /** ���͵�� Ȩ������ */
  private String rosite;
  
  /** ���͵�� ��ð� */
  private String rorunday;

  /** ���͵�� ���� */
  private String romap;
  
  /** ���͵�� �ּ�
   *  rolocation = rocity + rogu + rodong;
   */
  private String rolocation;

  /** ���͵�� ��� */
  private String rocost;
  
  /** ���ε� ���� */
  private String rofile1 = "";
  
  /** ���ε�� ���� ũ�� */
  private long rosize1 = 0;
  
  /** Preview ���� �̹��� �ڵ� ������ */
  private String rothumb = "";
  
  /** �� ���� */
  private String rocontent;
  
  /** ���͵�� �ο� */
  private String rocount;
  
  /** ���͵�� �ɼ� 
   * rooption = option1 + option2 + option3 + option4 + option5
   * */
  private String rooption;
  
///////////////////////////////////////////////////////////////////////////////////
  
  /** Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
       DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
  */  
  private MultipartFile file1MF;
  
  /** size1�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String size1Label;
 
  /** ���� �������� 1���� ���� */
  private int nowPage = 1;
  
///////////////////////////////////////////////////////////////////////////////////
  
  /** ���͵�� �ּ�
   *  rolocation = rocity + rogu + rodong; 
   */
  private String rocity;
  private String rogu;
  private String rodong;
  
  /** ���͵�� �˻��� ���� �÷�
   *  ronalo = roname + rolocation; 
   */
  private String ronalo;
  
  
  /** ���͵�� �ɼ� 
   * rooption = option1 + option2 + option3 + option4 + option5
   * */
  private String option1;
  private String option2;
  private String option3;
  private String option4;
  private String option5;  
  
  
  
  public int getRono() {
    return rono;
  }
  public void setRono(int rono) {
    this.rono = rono;
  }
  
  public int getGrpno() {
    return grpno;
  }
  public void setGrpno(int grpno) {
    this.grpno = grpno;
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
  
  
  public String getRorunday() {
    return rorunday;
  }
  public void setRorunday(String rorunday) {
    this.rorunday = rorunday;
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
  
  
  public String getRofile1() {
    return rofile1;
  }
  public void setRofile1(String rofile1) {
    this.rofile1 = rofile1;
  }
  
  
  public long getRosize1() {
    return rosize1;
  }
  public void setRosize1(long rosize1) {
    this.rosize1 = rosize1;
  }
  
  
  public String getRothumb() {
    return rothumb;
  }
  public void setRothumb(String rothumb) {
    this.rothumb = rothumb;
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
  
  
  public String getRocity() {
    return rocity;
  }
  public void setRocity(String rocity) {
    this.rocity = rocity;
  }
  
  
  public String getRogu() {
    return rogu;
  }
  public void setRogu(String rogu) {
    this.rogu = rogu;
  }
  
  
  public String getRodong() {
    return rodong;
  }
  public void setRodong(String rodong) {
    this.rodong = rodong;
  }
  
  
  public String getRonalo() {
    return ronalo;
  }
  public void setRonalo(String ronalo) {
    this.ronalo = ronalo;
  }
  
  
  public String getOption1() {
    return option1;
  }
  public void setOption1(String option1) {
    this.option1 = option1;
  }
  
  public String getOption2() {
    return option2;
  }
  public void setOption2(String option2) {
    this.option2 = option2;
  }
  
  public String getOption3() {
    return option3;
  }
  public void setOption3(String option3) {
    this.option3 = option3;
  }
  
  public String getOption4() {
    return option4;
  }
  public void setOption4(String option4) {
    this.option4 = option4;
  }
  
  public String getOption5() {
    return option5;
  }
  public void setOption5(String option5) {
    this.option5 = option5;
  }

  
}
