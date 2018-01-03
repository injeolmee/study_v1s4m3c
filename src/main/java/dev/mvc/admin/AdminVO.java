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
 * 관리자 번호
 */
  private int adminno;
  /**
   * 관리자 아이디
   */
  private String admid;
  /**
   * 관리자 이메일
   */
  private String admemail;
  /**
   * 관리자 패스워드
   */
  private String admpasswd;
  /**
   * 관리자 이름
   */
  private String admname;
  /**
   * 관리자 인증확인
   */
  private String admconfirm;
  /**
   * 관리자 권한
   */
  private String admauth;
  /**
   * 관리자 가입일
   */
  private String admdate;
  
  /** 등록된 패스워드 */
  private String old_admpasswd = "";
  /** id 저장 여부 */
  private String admid_save = "";
  /** passwd 저장 여부 */
  private String admpasswd_save = "";
  /** 이동할 주소 저장 */
  private String url_admaddress = "";
  
  /**
   * 검색어
   */
  private String word="";
  /**
   * 검색분류
   */
  private String search="";
  /**
   * 현재 페이지
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
