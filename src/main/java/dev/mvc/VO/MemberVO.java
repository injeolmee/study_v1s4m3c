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

  COMMENT ON TABLE member is 'ȸ������';
  COMMENT ON COLUMN member.memberno is 'ȸ����ȣ';
  COMMENT ON COLUMN member.mememail is 'ȸ���̸���';
  COMMENT ON COLUMN member.mempasswd is 'ȸ����й�ȣ';
  COMMENT ON COLUMN member.memname is 'ȸ���̸�';
  COMMENT ON COLUMN member.memsignkey is 'ȸ������Ű';
  COMMENT ON COLUMN member.memconfirm is 'ȸ������Ȯ��';
  COMMENT ON COLUMN member.memauth is 'ȸ������';
  COMMENT ON COLUMN member.membirth is 'ȸ���������';
  COMMENT ON COLUMN member.memgender is 'ȸ������';
  COMMENT ON COLUMN member.memaddress is 'ȸ���ּ�';
  COMMENT ON COLUMN member.memphone is 'ȸ����ȭ��ȣ';
  COMMENT ON COLUMN member.memsns is 'ȸ��SNS';
  COMMENT ON COLUMN member.memintro is 'ȸ���Ұ�';
  COMMENT ON COLUMN member.memphoto is 'ȸ����������';
  COMMENT ON COLUMN member.memphoto_t is 'ȸ�����������';
  COMMENT ON COLUMN member.memsize is 'ȸ����������ũ��';
  COMMENT ON COLUMN member.memvisible is 'ȸ��������¿���';
  COMMENT ON COLUMN member.memdate is 'ȸ��������';*/
  
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
