package dev.mvc.admin;

public class AdminVO {
/*  
  CREATE TABLE admin(
      adminno                           NUMBER(10)     PRIMARY KEY,
      admid                             VARCHAR2(50)    UNIQUE NOT NULL,
      admemail                          VARCHAR2(100)    UNIQUE NOT NULL,
      admpasswd                         VARCHAR2(20)     NOT NULL,
      admname                           VARCHAR2(20)     NOT NULL,
      admconfirm                        VARCHAR2(1)    DEFAULT 'N'     NOT NULL,
      admauth                           VARCHAR2(1)    DEFAULT 'N'     NOT NULL,
      admdate                           DATE     NOT NULL
  );
*/
  
/**
 * ������ ��ȣ
 */
  private int adminno;
  /**
   * ������ ���̵�
   */
  private String admid;
  /**
   * ������ �̸���
   */
  private String admemail;
  /**
   * ������ �н�����
   */
  private String admpasswd;
  /**
   * ������ �̸�
   */
  private String admname;
  /**
   * ������ ����Ȯ��
   */
  private String admconfirm;
  /**
   * ������ ����
   */
  private String admauth;
  /**
   * ������ ������
   */
  private String admdate;
  
  /** ��ϵ� �н����� */
  private String old_admpasswd = "";
  /** id ���� ���� */
  private String admid_save = "";
  /** passwd ���� ���� */
  private String admpasswd_save = "";
  /** �̵��� �ּ� ���� */
  private String url_admaddress = "";
  
  /**
   * �˻���
   */
  private String word="";
  /**
   * �˻��з�
   */
  private String search="";
  /**
   * ���� ������
   */
  private int nowPage = 1;
  
  
  public AdminVO() {
  }

  
  public int getAdminno() {
    return adminno;
  }

  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }

  public String getAdmid() {
    return admid;
  }

  public void setAdmid(String admid) {
    this.admid = admid;
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


  public String getOld_admpasswd() {
    return old_admpasswd;
  }


  public void setOld_admpasswd(String old_admpasswd) {
    this.old_admpasswd = old_admpasswd;
  }


  public String getAdmid_save() {
    return admid_save;
  }


  public void setAdmid_save(String admid_save) {
    this.admid_save = admid_save;
  }


  public String getAdmpasswd_save() {
    return admpasswd_save;
  }


  public void setAdmpasswd_save(String admpasswd_save) {
    this.admpasswd_save = admpasswd_save;
  }


  public String getUrl_admaddress() {
    return url_admaddress;
  }


  public void setUrl_admaddress(String url_admaddress) {
    this.url_admaddress = url_admaddress;
  }


  public String getWord() {
    return word;
  }


  public void setWord(String word) {
    this.word = word;
  }


  public String getSearch() {
    return search;
  }


  public void setSearch(String search) {
    this.search = search;
  }


  public int getNowPage() {
    return nowPage;
  }


  public void setNowPage(int nowPage) {
    this.nowPage = nowPage;
  }




  
  
  
}
