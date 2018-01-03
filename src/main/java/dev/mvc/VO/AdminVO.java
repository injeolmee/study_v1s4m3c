package dev.mvc.VO;

public class AdminVO {
/*  CREATE TABLE admin(
      adminno                           NUMBER(10)     NOT NULL,
      admemail                          VARCHAR2(100)    NOT NULL,
      admpasswd                         VARCHAR2(20)     NOT NULL,
      admname                           VARCHAR2(20)     NOT NULL,
      admsignkey                        VARCHAR2(100)    NOT NULL,
      admconfirm                        VARCHAR2(1)    DEFAULT 'N'     NOT NULL,
      admauth                           VARCHAR2(1)    DEFAULT 'N'     NOT NULL,
      admdate                           DATE     NOT NULL
  );

  COMMENT ON TABLE admin is '관리자';
  COMMENT ON COLUMN admin.adminno is '관리자번호';
  COMMENT ON COLUMN admin.admemail is '관리자이메일';
  COMMENT ON COLUMN admin.admpasswd is '관리자비밀번호';
  COMMENT ON COLUMN admin.admname is '관리자이름';
  COMMENT ON COLUMN admin.admsignkey is '관리자가입키';
  COMMENT ON COLUMN admin.admconfirm is '관리자인증확인';
  COMMENT ON COLUMN admin.admauth is '관리자권한';
  COMMENT ON COLUMN admin.admdate is '관리자가입일';*/
  
  
  private int adminno;         //'관리자번호';
  private String admemail;     // '관리자이메일'
  private String admpasswd;    // '관리자비밀번호'
  private String admname;      // '관리자이름'
  private String admsignkey;   // '관리자가입키'
  private String admconfirm;   // '관리자인증확인'
  private String admauth;      // '관리자권한'
  private String admdate;      // '관리자가입일'
  
  public AdminVO() {
    //System.out.println(" --> adminVO created.");
  }
  
  public AdminVO(int adminno, String admemail, String admpasswd, String admname, String admsignkey, String admconfirm,
      String admauth, String admdate) {
    super();
    this.adminno = adminno;
    this.admemail = admemail;
    this.admpasswd = admpasswd;
    this.admname = admname;
    this.admsignkey = admsignkey;
    this.admconfirm = admconfirm;
    this.admauth = admauth;
    this.admdate = admdate;
  }
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  public String getAdmemail() {
    return admemail;
  }
  public void setAdmemail(String admemail) {
    this.admemail = admemail;
  }
  public String getAdmpasswd() {
    return admpasswd;
  }
  public void setAdmpasswd(String admpasswd) {
    this.admpasswd = admpasswd;
  }
  public String getAdmname() {
    return admname;
  }
  public void setAdmname(String admname) {
    this.admname = admname;
  }
  public String getAdmsignkey() {
    return admsignkey;
  }
  public void setAdmsignkey(String admsignkey) {
    this.admsignkey = admsignkey;
  }
  public String getAdmconfirm() {
    return admconfirm;
  }
  public void setAdmconfirm(String admconfirm) {
    this.admconfirm = admconfirm;
  }
  public String getAdmauth() {
    return admauth;
  }
  public void setAdmauth(String admauth) {
    this.admauth = admauth;
  }
  public String getAdmdate() {
    return admdate;
  }
  public void setAdmdate(String admdate) {
    this.admdate = admdate;
  }
  
  
}
