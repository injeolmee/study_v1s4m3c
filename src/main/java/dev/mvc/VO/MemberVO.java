package dev.mvc.VO;

public class MemberVO {
/*  CREATE TABLE member(
      memberno                          NUMBER(10)     NOT NULL,
      mememail                          VARCHAR2(100)    NOT NULL,
      mempasswd                         VARCHAR2(20)     NOT NULL,
      memname                           VARCHAR2(20)     NOT NULL,
      memsignkey                        VARCHAR2(100)    NOT NULL,
      memconfirm                        VARCHAR2(1)    DEFAULT 'N'     NOT NULL,
      memauth                           VARCHAR2(1)    DEFAULT 'B'     NOT NULL,
      membirth                          VARCHAR2(50)     NOT NULL,
      memgender                         VARCHAR2(5)    NOT NULL,
      memaddress                        VARCHAR2(100)    NOT NULL,
      memphone                          VARCHAR2(50)     NOT NULL,
      memsns                            VARCHAR2(100)    NULL ,
      memintro                          VARCHAR2(1000)     NULL ,
      memphoto                          VARCHAR2(500)    NULL ,
      memphoto_t                        VARCHAR2(500)    NULL ,
      memsize                           NUMBER(30)     DEFAULT 0     NOT NULL,
      memvisible                        VARCHAR2(1)    DEFAULT 'N'     NOT NULL,
      memdate                           DATE     NOT NULL
  );

  COMMENT ON TABLE member is '회원정보';
  COMMENT ON COLUMN member.memberno is '회원번호';
  COMMENT ON COLUMN member.mememail is '회원이메일';
  COMMENT ON COLUMN member.mempasswd is '회원비밀번호';
  COMMENT ON COLUMN member.memname is '회원이름';
  COMMENT ON COLUMN member.memsignkey is '회원가입키';
  COMMENT ON COLUMN member.memconfirm is '회원인증확인';
  COMMENT ON COLUMN member.memauth is '회원권한';
  COMMENT ON COLUMN member.membirth is '회원생년월일';
  COMMENT ON COLUMN member.memgender is '회원성별';
  COMMENT ON COLUMN member.memaddress is '회원주소';
  COMMENT ON COLUMN member.memphone is '회원전화번호';
  COMMENT ON COLUMN member.memsns is '회원SNS';
  COMMENT ON COLUMN member.memintro is '회원소개';
  COMMENT ON COLUMN member.memphoto is '회원사진파일';
  COMMENT ON COLUMN member.memphoto_t is '회원사진썸네일';
  COMMENT ON COLUMN member.memsize is '회원사진파일크기';
  COMMENT ON COLUMN member.memvisible is '회원정보출력여부';
  COMMENT ON COLUMN member.memdate is '회원가입일';*/
  
  private int memberno;
  private String mememail; 
  private String mempasswd;
  private String memname;
  private String memsignkey;
  private String memconfirm;
  private String memauth;
  private String membirth;  
  private String memgender; 
  private String memaddress;
  private String memphone;
  private String memsns; 
  private String memintro;  
  private String memphoto; 
  private String memphoto_t;
  private int memsize;
  private String memvisible;
  private String memdate;
  
  public MemberVO() {
    //System.out.println(" --> memberVO created.");
  }
  
  public MemberVO(int memberno, String mememail, String mempasswd, String memname, String memsignkey, String memconfirm,
      String memauth, String membirth, String memgender, String memaddress, String memphone, String memsns,
      String memintro, String memphoto, String memphoto_t, int memsize, String memvisible, String memdate) {
    super();
    this.memberno = memberno;
    this.mememail = mememail;
    this.mempasswd = mempasswd;
    this.memname = memname;
    this.memsignkey = memsignkey;
    this.memconfirm = memconfirm;
    this.memauth = memauth;
    this.membirth = membirth;
    this.memgender = memgender;
    this.memaddress = memaddress;
    this.memphone = memphone;
    this.memsns = memsns;
    this.memintro = memintro;
    this.memphoto = memphoto;
    this.memphoto_t = memphoto_t;
    this.memsize = memsize;
    this.memvisible = memvisible;
    this.memdate = memdate;
  }
  
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public String getMememail() {
    return mememail;
  }
  public void setMememail(String mememail) {
    this.mememail = mememail;
  }
  public String getMempasswd() {
    return mempasswd;
  }
  public void setMempasswd(String mempasswd) {
    this.mempasswd = mempasswd;
  }
  public String getMemname() {
    return memname;
  }
  public void setMemname(String memname) {
    this.memname = memname;
  }
  public String getMemsignkey() {
    return memsignkey;
  }
  public void setMemsignkey(String memsignkey) {
    this.memsignkey = memsignkey;
  }
  public String getMemconfirm() {
    return memconfirm;
  }
  public void setMemconfirm(String memconfirm) {
    this.memconfirm = memconfirm;
  }
  public String getMemauth() {
    return memauth;
  }
  public void setMemauth(String memauth) {
    this.memauth = memauth;
  }
  public String getMembirth() {
    return membirth;
  }
  public void setMembirth(String membirth) {
    this.membirth = membirth;
  }
  public String getMemgender() {
    return memgender;
  }
  public void setMemgender(String memgender) {
    this.memgender = memgender;
  }
  public String getMemaddress() {
    return memaddress;
  }
  public void setMemaddress(String memaddress) {
    this.memaddress = memaddress;
  }
  public String getMemphone() {
    return memphone;
  }
  public void setMemphone(String memphone) {
    this.memphone = memphone;
  }
  public String getMemsns() {
    return memsns;
  }
  public void setMemsns(String memsns) {
    this.memsns = memsns;
  }
  public String getMemintro() {
    return memintro;
  }
  public void setMemintro(String memintro) {
    this.memintro = memintro;
  }
  public String getMemphoto() {
    return memphoto;
  }
  public void setMemphoto(String memphoto) {
    this.memphoto = memphoto;
  }
  public String getMemphoto_t() {
    return memphoto_t;
  }
  public void setMemphoto_t(String memphoto_t) {
    this.memphoto_t = memphoto_t;
  }
  public int getMemsize() {
    return memsize;
  }
  public void setMemsize(int memsize) {
    this.memsize = memsize;
  }
  public String getMemvisible() {
    return memvisible;
  }
  public void setMemvisible(String memvisible) {
    this.memvisible = memvisible;
  }
  public String getMemdate() {
    return memdate;
  }
  public void setMemdate(String memdate) {
    this.memdate = memdate;
  }
  
  
}
