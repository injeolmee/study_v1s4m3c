package dev.mvc.categrp;

public class CategrpVO {
/*  CREATE TABLE categrp(
      grpno                            NUMBER(10)      PRIMARY KEY,
      grpname                          VARCHAR2(50)     NOT NULL,
      grpseqno                         NUMBER(5)    NOT NULL,
      grpvisible                       VARCHAR2(1)    DEFAULT 'Y'     NOT NULL
  );*/
  
  /**
   * ī�ױ׷� ��ȣ
   */
  private int grpno;
  /**
   * ī�ױ׷� �̸�
   */
  private String grpname;
  /**
   * ī�ױ׷� ��¼���
   */
  private String grpseqno;
  /**
   * ī�ױ׷� ��¿���
   */
  private String grpvisible;
  

  public CategrpVO() {
  }


  public int getGrpno() {
    return grpno;
  }


  public void setGrpno(int grpno) {
    this.grpno = grpno;
  }


  public String getGrpname() {
    return grpname;
  }


  public void setGrpname(String grpname) {
    this.grpname = grpname;
  }


  public String getGrpseqno() {
    return grpseqno;
  }


  public void setGrpseqno(String grpseqno) {
    this.grpseqno = grpseqno;
  }


  public String getGrpvisible() {
    return grpvisible;
  }


  public void setGrpvisible(String grpvisible) {
    this.grpvisible = grpvisible;
  }


  
  
  

}
