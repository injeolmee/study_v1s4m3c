package dev.mvc.VO;

public class RereplyVO {
  /**********************************/
  /* Table Name: ´ë´ñ±Û */
  /**********************************/
/*  CREATE TABLE Rereply(
      repno_re                          NUMBER(10)     NOT NULL,
      repcont_re                        VARCHAR2(200)    NOT NULL,
      repdate_re                        DATE     NOT NULL,
      repname_re                        VARCHAR2(30)     NOT NULL,
      repno                             NUMBER(10)     NULL 
  );

  COMMENT ON TABLE Rereply is '´ë´ñ±Û';
  COMMENT ON COLUMN Rereply.repno_re is '´ë´ñ±Û ¹øÈ£';
  COMMENT ON COLUMN Rereply.repcont_re is '´ë´ñ±Û ³»¿ë';
  COMMENT ON COLUMN Rereply.repdate_re is '´ë´ñ±Û µî·ÏÀÏ';
  COMMENT ON COLUMN Rereply.repname_re is '´ë´ñ±Û ÀÛ¼ºÀÚ';
  COMMENT ON COLUMN Rereply.repno is '´ñ±Û ¹øÈ£';*/
  
  private String repno_re;  
  private String repcont_re;
  private String repdate_re;
  private String repname_re;
  private String repno;
  
  public RereplyVO() {
    // TODO Auto-generated constructor stub
  }

  public RereplyVO(String repno_re, String repcont_re, String repdate_re, String repname_re, String repno) {
    super();
    this.repno_re = repno_re;
    this.repcont_re = repcont_re;
    this.repdate_re = repdate_re;
    this.repname_re = repname_re;
    this.repno = repno;
  }

  public String getRepno_re() {
    return repno_re;
  }

  public void setRepno_re(String repno_re) {
    this.repno_re = repno_re;
  }

  public String getRepcont_re() {
    return repcont_re;
  }

  public void setRepcont_re(String repcont_re) {
    this.repcont_re = repcont_re;
  }

  public String getRepdate_re() {
    return repdate_re;
  }

  public void setRepdate_re(String repdate_re) {
    this.repdate_re = repdate_re;
  }

  public String getRepname_re() {
    return repname_re;
  }

  public void setRepname_re(String repname_re) {
    this.repname_re = repname_re;
  }

  public String getRepno() {
    return repno;
  }

  public void setRepno(String repno) {
    this.repno = repno;
  }
  
  
}
