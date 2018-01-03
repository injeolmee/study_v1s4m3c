package dev.mvc.VO;

public class RecruitVO {
/*
   Table Name: 신청 현황 테이블 

  CREATE TABLE recruit(
      recruitno                         NUMBER(10)     NOT NULL,
      confirm                           VARCHAR2(10)     DEFAULT 'U'     NOT NULL,
      std_auth                          VARCHAR2(10)     DEFAULT 'U'     NOT NULL,
      stdlist_no                        NUMBER(10)     NULL ,
      memberno                          NUMBER(10)     NOT NULL
  );

  COMMENT ON TABLE recruit is '신청 현황 테이블';
  COMMENT ON COLUMN recruit.recruitno is '신청번호';
  COMMENT ON COLUMN recruit.confirm is '승인여부';
  COMMENT ON COLUMN recruit.std_auth is '권한(스터디 내)';
  COMMENT ON COLUMN recruit.stdlist_no is '스터디번호';
  COMMENT ON COLUMN recruit.memberno is '회원번호';*/
  
  private int recruitno; 
  private String confirm;   
  private String std_auth;  
  private int stdlist_no;
  private int memberno;
  
  public RecruitVO() {
    // TODO Auto-generated constructor stub
  }

  public RecruitVO(int recruitno, String confirm, String std_auth, int stdlist_no, int memberno) {
    super();
    this.recruitno = recruitno;
    this.confirm = confirm;
    this.std_auth = std_auth;
    this.stdlist_no = stdlist_no;
    this.memberno = memberno;
  }

  public int getRecruitno() {
    return recruitno;
  }

  public void setRecruitno(int recruitno) {
    this.recruitno = recruitno;
  }

  public String getConfirm() {
    return confirm;
  }

  public void setConfirm(String confirm) {
    this.confirm = confirm;
  }

  public String getStd_auth() {
    return std_auth;
  }

  public void setStd_auth(String std_auth) {
    this.std_auth = std_auth;
  }

  public int getStdlist_no() {
    return stdlist_no;
  }

  public void setStdlist_no(int stdlist_no) {
    this.stdlist_no = stdlist_no;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  
  
}
