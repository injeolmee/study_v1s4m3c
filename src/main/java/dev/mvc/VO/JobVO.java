package dev.mvc.VO;

public class JobVO {
  /**********************************/
  /* Table Name: ��� ���� ��� */
  /**********************************/
/*  CREATE TABLE job(
      infono_j                          NUMBER(10)     NOT NULL,
      infotitle_j                       VARCHAR2(100)    NOT NULL,
      infolocal_j                       VARCHAR2(50)     NOT NULL,
      infocont_j                        VARCHAR2(4000)     NOT NULL,
      infotel_j                         VARCHAR2(30)     NOT NULL,
      iname_j                           VARCHAR2(20)     NOT NULL,
      idate_j                           DATE     NOT NULL,
      infofile_j                        VARCHAR2(100)    NOT NULL,
      infofile_tj                       VARCHAR2(100)    NOT NULL,
      infosize_j                        NUMBER(10)     NOT NULL,
      infocnt_j                         NUMBER(10)     NOT NULL,
      adminno                           NUMBER(10)     NULL ,
      memberno                          NUMBER(10)     NULL ,
      cateno                            NUMBER(10)     NULL 
  );

  COMMENT ON TABLE job is '��� ���� ���';
  COMMENT ON COLUMN job.infono_j is '��� ���� ��� ��ȣ';
  COMMENT ON COLUMN job.infotitle_j is '��� ����';
  COMMENT ON COLUMN job.infolocal_j is '��� ����';
  COMMENT ON COLUMN job.infocont_j is '���� ����';
  COMMENT ON COLUMN job.infotel_j is '��ȭ��ȣ';
  COMMENT ON COLUMN job.iname_j is '����� �̸�';
  COMMENT ON COLUMN job.idate_j is '���� �����';
  COMMENT ON COLUMN job.infofile_j is '÷�� ����';
  COMMENT ON COLUMN job.infofile_tj is '÷������ �����';
  COMMENT ON COLUMN job.infosize_j is '÷������ ������';
  COMMENT ON COLUMN job.infocnt_j is '���� ��ȸ��';
  COMMENT ON COLUMN job.adminno is '�����ڹ�ȣ';
  COMMENT ON COLUMN job.memberno is 'ȸ����ȣ';
  COMMENT ON COLUMN job.cateno is 'ī�װ���ȣ';*/
  
  private int infono_j;   
  private String infotitle_j;
  private String infolocal_j;
  private String infocont_j; 
  private String infotel_j;  
  private String iname_j;    
  private String idate_j;    
  private String infofile_j; 
  private String infofile_tj;
  private int infosize_j; 
  private int infocnt_j;  
  private int adminno;    
  private int memberno;   
  private int cateno;
  
  public JobVO() {
    // TODO Auto-generated constructor stub
  }

  public JobVO(int infono_j, String infotitle_j, String infolocal_j, String infocont_j, String infotel_j,
      String iname_j, String idate_j, String infofile_j, String infofile_tj, int infosize_j, int infocnt_j, int adminno,
      int memberno, int cateno) {
    super();
    this.infono_j = infono_j;
    this.infotitle_j = infotitle_j;
    this.infolocal_j = infolocal_j;
    this.infocont_j = infocont_j;
    this.infotel_j = infotel_j;
    this.iname_j = iname_j;
    this.idate_j = idate_j;
    this.infofile_j = infofile_j;
    this.infofile_tj = infofile_tj;
    this.infosize_j = infosize_j;
    this.infocnt_j = infocnt_j;
    this.adminno = adminno;
    this.memberno = memberno;
    this.cateno = cateno;
  }

  public int getInfono_j() {
    return infono_j;
  }

  public void setInfono_j(int infono_j) {
    this.infono_j = infono_j;
  }

  public String getInfotitle_j() {
    return infotitle_j;
  }

  public void setInfotitle_j(String infotitle_j) {
    this.infotitle_j = infotitle_j;
  }

  public String getInfolocal_j() {
    return infolocal_j;
  }

  public void setInfolocal_j(String infolocal_j) {
    this.infolocal_j = infolocal_j;
  }

  public String getInfocont_j() {
    return infocont_j;
  }

  public void setInfocont_j(String infocont_j) {
    this.infocont_j = infocont_j;
  }

  public String getInfotel_j() {
    return infotel_j;
  }

  public void setInfotel_j(String infotel_j) {
    this.infotel_j = infotel_j;
  }

  public String getIname_j() {
    return iname_j;
  }

  public void setIname_j(String iname_j) {
    this.iname_j = iname_j;
  }

  public String getIdate_j() {
    return idate_j;
  }

  public void setIdate_j(String idate_j) {
    this.idate_j = idate_j;
  }

  public String getInfofile_j() {
    return infofile_j;
  }

  public void setInfofile_j(String infofile_j) {
    this.infofile_j = infofile_j;
  }

  public String getInfofile_tj() {
    return infofile_tj;
  }

  public void setInfofile_tj(String infofile_tj) {
    this.infofile_tj = infofile_tj;
  }

  public int getInfosize_j() {
    return infosize_j;
  }

  public void setInfosize_j(int infosize_j) {
    this.infosize_j = infosize_j;
  }

  public int getInfocnt_j() {
    return infocnt_j;
  }

  public void setInfocnt_j(int infocnt_j) {
    this.infocnt_j = infocnt_j;
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
