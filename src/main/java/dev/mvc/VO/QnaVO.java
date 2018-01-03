package dev.mvc.VO;

public class QnaVO {
  /**********************************/
  /* Table Name: QnA �Խ��� */
  /**********************************/
/*  CREATE TABLE qna(
      qnano                             NUMBER(10)     NOT NULL,
      qnatitle                          VARCHAR2(100)    NOT NULL,
      qnacont                           VARCHAR2(4000)     NOT NULL,
      qname                             VARCHAR2(20)     NOT NULL,
      qdate                             DATE     NOT NULL,
      qnacnt                            NUMBER(10)     NOT NULL,
      qnapw                             NUMBER(10)     NOT NULL,
      memberno                          NUMBER(10)     NULL ,
      adminno                           NUMBER(10)     NULL ,
      cateno                            NUMBER(10)     NULL 
  );

  COMMENT ON TABLE qna is 'QnA �Խ���';
  COMMENT ON COLUMN qna.qnano is '�Խ��� ��ȣ';
  COMMENT ON COLUMN qna.qnatitle is '�Խ��� ����';
  COMMENT ON COLUMN qna.qnacont is '�Խ��� ����';
  COMMENT ON COLUMN qna.qname is '����� �̸�';
  COMMENT ON COLUMN qna.qdate is '�Խ��� �����';
  COMMENT ON COLUMN qna.qnacnt is '�Խ��� ��ȸ��';
  COMMENT ON COLUMN qna.qnapw is '��й�ȣ';
  COMMENT ON COLUMN qna.memberno is 'ȸ����ȣ';
  COMMENT ON COLUMN qna.adminno is '�����ڹ�ȣ';
  COMMENT ON COLUMN qna.cateno is 'ī�װ���ȣ';*/
  
  private int qnano;  
  private String qnatitle;
  private String qnacont; 
  private String qname;   
  private String qdate;   
  private int qnacnt;  
  private int qnapw;   
  private int memberno;
  private int adminno; 
  private int cateno;
  
  public QnaVO() {
    // TODO Auto-generated constructor stub
  }

  public QnaVO(int qnano, String qnatitle, String qnacont, String qname, String qdate, int qnacnt, int qnapw,
      int memberno, int adminno, int cateno) {
    super();
    this.qnano = qnano;
    this.qnatitle = qnatitle;
    this.qnacont = qnacont;
    this.qname = qname;
    this.qdate = qdate;
    this.qnacnt = qnacnt;
    this.qnapw = qnapw;
    this.memberno = memberno;
    this.adminno = adminno;
    this.cateno = cateno;
  }

  public int getQnano() {
    return qnano;
  }

  public void setQnano(int qnano) {
    this.qnano = qnano;
  }

  public String getQnatitle() {
    return qnatitle;
  }

  public void setQnatitle(String qnatitle) {
    this.qnatitle = qnatitle;
  }

  public String getQnacont() {
    return qnacont;
  }

  public void setQnacont(String qnacont) {
    this.qnacont = qnacont;
  }

  public String getQname() {
    return qname;
  }

  public void setQname(String qname) {
    this.qname = qname;
  }

  public String getQdate() {
    return qdate;
  }

  public void setQdate(String qdate) {
    this.qdate = qdate;
  }

  public int getQnacnt() {
    return qnacnt;
  }

  public void setQnacnt(int qnacnt) {
    this.qnacnt = qnacnt;
  }

  public int getQnapw() {
    return qnapw;
  }

  public void setQnapw(int qnapw) {
    this.qnapw = qnapw;
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

  public int getCateno() {
    return cateno;
  }

  public void setCateno(int cateno) {
    this.cateno = cateno;
  }
  
}
