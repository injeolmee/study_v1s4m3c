package dev.mvc.freelike;

public class FreelikeVO {
  
/*  CREATE TABLE FREELIKE(
      GOODNO                            NUMBER(10)     NOT NULL,
      GOODCHK                           NUMBER(10)     DEFAULT 0     NULL ,
      MEMBERNO                          NUMBER(10)     NULL ,
      freeno                                  NUMBER(7)    NULL 
  );*/
  
  /** ���ƿ� ��ȣ */
  private int goodNo;
  /** ���ƿ� üũ ���� */
  private int goodChk;
  /** �Խñ� ��ȣ */
  private int freeno;
  /** ȸ�� ��ȣ */
  private int memberno;
  
  public int getGoodNo() {
    return goodNo;
  }

  public void setGoodNo(int goodNo) {
    this.goodNo = goodNo;
  }

  public int getGoodChk() {
    return goodChk;
  }

  public void setGoodChk(int goodChk) {
    this.goodChk = goodChk;
  }

  public int getFreeno() {
    return freeno;
  }

  public void setFreeno(int freeno) {
    this.freeno = freeno;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  
}
