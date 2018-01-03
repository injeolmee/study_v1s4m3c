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

  COMMENT ON TABLE admin is '������';
  COMMENT ON COLUMN admin.adminno is '�����ڹ�ȣ';
  COMMENT ON COLUMN admin.admemail is '�������̸���';
  COMMENT ON COLUMN admin.admpasswd is '�����ں�й�ȣ';
  COMMENT ON COLUMN admin.admname is '�������̸�';
  COMMENT ON COLUMN admin.admsignkey is '�����ڰ���Ű';
  COMMENT ON COLUMN admin.admconfirm is '����������Ȯ��';
  COMMENT ON COLUMN admin.admauth is '�����ڱ���';
  COMMENT ON COLUMN admin.admdate is '�����ڰ�����';*/
  
  
  private int adminno;         //'�����ڹ�ȣ';
  private String admemail;     // '�������̸���'
  private String admpasswd;    // '�����ں�й�ȣ'
  private String admname;      // '�������̸�'
  private String admsignkey;   // '�����ڰ���Ű'
  private String admconfirm;   // '����������Ȯ��'
  private String admauth;      // '�����ڱ���'
  private String admdate;      // '�����ڰ�����'
  
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
