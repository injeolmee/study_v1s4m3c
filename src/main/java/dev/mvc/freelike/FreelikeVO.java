package dev.mvc.freelike;

public class FreelikeVO {
  
/*  CREATE TABLE FREELIKE(
      GOODNO                            NUMBER(10)     NOT NULL,
      GOODCHK                           NUMBER(10)     DEFAULT 0     NULL ,
      MEMBERNO                          NUMBER(10)     NULL ,
      freeno                                  NUMBER(7)    NULL 
  );*/
  
  /** 좋아요 번호 */
  private int goodNo;
  /** 좋아요 체크 여부 */
  private int goodChk;
  /** 게시글 번호 */
  private int freeno;
  /** 회원 번호 */
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
