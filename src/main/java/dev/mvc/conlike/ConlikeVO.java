package dev.mvc.conlike;

/*
 goodNo                              NUMBER(10)               NOT NULL    PRIMARY KEY,
 goodChk                             NUMBER(10)     DEFAULT 0     NULL ,
 conNo                               NUMBER(7)                    NULL ,
 memberno                            NUMBER(10)
 */

public class ConlikeVO {
  /** ���ƿ� ��ȣ */
  private int goodNo;
  /** ���ƿ� üũ ���� */
  private int goodChk;
  /** ������ ��ȣ */
  private int conNo;
  /** ȸ�� ��ȣ */
  private int memberno;
  
  public ConlikeVO() {
    
  }

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

  public int getConNo() {
    return conNo;
  }

  public void setConNo(int conNo) {
    this.conNo = conNo;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  
}
