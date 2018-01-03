package dev.mvc.VO;

/**
 * @author lee33
 *
 */
public class My_pdsVO {
  /**********************************/
  /* Table Name: �� ���͵� �� ��� */
  /**********************************/
/*  CREATE TABLE my_pds(
      pdsno                             NUMBER(10)     NOT NULL,
      pdstitle                          VARCHAR2(100)    NOT NULL,
      pdspasswd                         VARCHAR2(20)     NULL ,
      pdscontent                        VARCHAR2(4000)     NULL ,
      pdsfile1                          VARCHAR2(10)     NULL ,
      pdsfstor1                         VARCHAR2(100)    NULL ,
      pdslike                           NUMBER(10)     DEFAULT 0     NULL ,
      pdscnt                            NUMBER(10)     DEFAULT 0     NULL ,
      pdsdate                           DATE     NOT NULL,
      memberno                          NUMBER(10)     NULL ,
      mylistno                          NUMBER(10)     NULL 
  );

  COMMENT ON TABLE my_pds is '�� ���͵� �� ���';
  COMMENT ON COLUMN my_pds.pdsno is '�� ��ȣ';
  COMMENT ON COLUMN my_pds.pdstitle is '�� ����';
  COMMENT ON COLUMN my_pds.pdspasswd is '�� ��й�ȣ';
  COMMENT ON COLUMN my_pds.pdscontent is '�� ����';
  COMMENT ON COLUMN my_pds.pdsfile1 is '�� ÷�����ϸ�';
  COMMENT ON COLUMN my_pds.pdsfstor1 is '���� ���� ���ϸ�';
  COMMENT ON COLUMN my_pds.pdslike is '�� ��õ��';
  COMMENT ON COLUMN my_pds.pdscnt is '�� ��ȸ��';
  COMMENT ON COLUMN my_pds.pdsdate is '�� �ۼ���';
  COMMENT ON COLUMN my_pds.memberno is 'ȸ����ȣ';
  COMMENT ON COLUMN my_pds.mylistno is '�����͵𸮽�Ʈ ���� ��ȣ';*/
  
  private int pdsno;     
  private String pdstitle;  
  private String pdspasswd; 
  private String pdscontent;
  private String pdsfile1;  
  private String pdsfstor1; 
  private int pdslike;   
  private int pdscnt;    
  private String pdsdate;   
  private int memberno;  
  private int mylistno;
  
  public My_pdsVO(int pdsno, String pdstitle, String pdspasswd, String pdscontent, String pdsfile1, String pdsfstor1,
      int pdslike, int pdscnt, String pdsdate, int memberno, int mylistno) {
    super();
    this.pdsno = pdsno;
    this.pdstitle = pdstitle;
    this.pdspasswd = pdspasswd;
    this.pdscontent = pdscontent;
    this.pdsfile1 = pdsfile1;
    this.pdsfstor1 = pdsfstor1;
    this.pdslike = pdslike;
    this.pdscnt = pdscnt;
    this.pdsdate = pdsdate;
    this.memberno = memberno;
    this.mylistno = mylistno;
  }
  
  public My_pdsVO() {
    // TODO Auto-generated constructor stub
  }

  public int getPdsno() {
    return pdsno;
  }

  public void setPdsno(int pdsno) {
    this.pdsno = pdsno;
  }

  public String getPdstitle() {
    return pdstitle;
  }

  public void setPdstitle(String pdstitle) {
    this.pdstitle = pdstitle;
  }

  public String getPdspasswd() {
    return pdspasswd;
  }

  public void setPdspasswd(String pdspasswd) {
    this.pdspasswd = pdspasswd;
  }

  public String getPdscontent() {
    return pdscontent;
  }

  public void setPdscontent(String pdscontent) {
    this.pdscontent = pdscontent;
  }

  public String getPdsfile1() {
    return pdsfile1;
  }

  public void setPdsfile1(String pdsfile1) {
    this.pdsfile1 = pdsfile1;
  }

  public String getPdsfstor1() {
    return pdsfstor1;
  }

  public void setPdsfstor1(String pdsfstor1) {
    this.pdsfstor1 = pdsfstor1;
  }

  public int getPdslike() {
    return pdslike;
  }

  public void setPdslike(int pdslike) {
    this.pdslike = pdslike;
  }

  public int getPdscnt() {
    return pdscnt;
  }

  public void setPdscnt(int pdscnt) {
    this.pdscnt = pdscnt;
  }

  public String getPdsdate() {
    return pdsdate;
  }

  public void setPdsdate(String pdsdate) {
    this.pdsdate = pdsdate;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public int getMylistno() {
    return mylistno;
  }

  public void setMylistno(int mylistno) {
    this.mylistno = mylistno;
  }
  
  
}
