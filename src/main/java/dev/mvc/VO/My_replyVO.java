package dev.mvc.VO;

public class My_replyVO {
  /**********************************/
  /* Table Name: �����͵� ��� */
  /**********************************/
/*  CREATE TABLE my_reply(
      mycomno                           NUMBER(10)     NOT NULL,
      mycomcontent                      VARCHAR2(200)    NULL ,
      mycompasswd                       NUMBER(10)     NULL ,
      memberno                          NUMBER(10)     NULL ,
      pdsno                             NUMBER(10)     NULL 
  );

  COMMENT ON TABLE my_reply is '�����͵� ���';
  COMMENT ON COLUMN my_reply.mycomno is '�����͵��۹�ȣ';
  COMMENT ON COLUMN my_reply.mycomcontent is '��� ����';
  COMMENT ON COLUMN my_reply.mycompasswd is '��� ��й�ȣ';
  COMMENT ON COLUMN my_reply.memberno is 'ȸ����ȣ';
  COMMENT ON COLUMN my_reply.pdsno is '�� ��ȣ';*/
  
  private int mycomno;    
  private String mycomcontent;
  private int mycompasswd;
  private int memberno;   
  private int pdsno;
  
  public My_replyVO() {
    // TODO Auto-generated constructor stub
  }
  
  public My_replyVO(int mycomno, String mycomcontent, int mycompasswd, int memberno, int pdsno) {
    super();
    this.mycomno = mycomno;
    this.mycomcontent = mycomcontent;
    this.mycompasswd = mycompasswd;
    this.memberno = memberno;
    this.pdsno = pdsno;
  }

  public int getMycomno() {
    return mycomno;
  }

  public void setMycomno(int mycomno) {
    this.mycomno = mycomno;
  }

  public String getMycomcontent() {
    return mycomcontent;
  }

  public void setMycomcontent(String mycomcontent) {
    this.mycomcontent = mycomcontent;
  }

  public int getMycompasswd() {
    return mycompasswd;
  }

  public void setMycompasswd(int mycompasswd) {
    this.mycompasswd = mycompasswd;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public int getPdsno() {
    return pdsno;
  }

  public void setPdsno(int pdsno) {
    this.pdsno = pdsno;
  }   
  
  
}
