package dev.mvc.VO;

public class My_std_catelistVO {
  /**********************************/
  /* Table Name: �����͵�-ī�װ� ����Ʈ ���̺� */
  /**********************************/
  /*CREATE TABLE my_std_catelist(
      mylistno                          NUMBER(10)     NOT NULL,
      stdlist_no                        NUMBER(10)     NULL ,
      cateno                            NUMBER(10)     NULL 
  );

  COMMENT ON TABLE my_std_catelist is '�����͵�-ī�װ� ����Ʈ ���̺�';
  COMMENT ON COLUMN my_std_catelist.mylistno is '�����͵𸮽�Ʈ ���� ��ȣ';
  COMMENT ON COLUMN my_std_catelist.stdlist_no is '���͵��ȣ';
  COMMENT ON COLUMN my_std_catelist.cateno is 'ī�װ���ȣ';*/
  
  private int mylistno;  
  private int stdlist_no;
  private int cateno;
  
  public My_std_catelistVO() {
    // TODO Auto-generated constructor stub
  }
  
  public My_std_catelistVO(int mylistno, int stdlist_no, int cateno) {
    super();
    this.mylistno = mylistno;
    this.stdlist_no = stdlist_no;
    this.cateno = cateno;
  }
  public int getMylistno() {
    return mylistno;
  }
  public void setMylistno(int mylistno) {
    this.mylistno = mylistno;
  }
  public int getStdlist_no() {
    return stdlist_no;
  }
  public void setStdlist_no(int stdlist_no) {
    this.stdlist_no = stdlist_no;
  }
  public int getCateno() {
    return cateno;
  }
  public void setCateno(int cateno) {
    this.cateno = cateno;
  }   
  
  
}
