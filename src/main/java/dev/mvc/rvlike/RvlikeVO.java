package dev.mvc.rvlike;

/*
 rvlikeno                              NUMBER(10)               NOT NULL    PRIMARY KEY,
 rvlikechk                             NUMBER(10)     DEFAULT 0     NULL ,
 rvno                               NUMBER(7)                    NULL ,
 memberno                            NUMBER(10)
 */

public class RvlikeVO {
 

  /** ���ƿ� ��ȣ */
  private int rvlikeno;
  /** ���ƿ� üũ ���� */
  private int rvlikechk;
  /** ������ ��ȣ */
  private int rvno;
  /** ȸ�� ��ȣ */
  private int memberno;

  
  
  public int getRvlikeno() {
    return rvlikeno;
  }

  public void setRvlikeno(int rvlikeno) {
    this.rvlikeno = rvlikeno;
  }

  public int getRvlikechk() {
    return rvlikechk;
  }

  public void setRvlikechk(int rvlikechk) {
    this.rvlikechk = rvlikechk;
  }

  public int getRvno() {
    return rvno;
  }

  public void setRvno(int rvno) {
    this.rvno = rvno;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  
}
