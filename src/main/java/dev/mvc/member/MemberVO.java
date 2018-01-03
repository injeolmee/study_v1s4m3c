package dev.mvc.member;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
  /*
CREATE TABLE member(
    memberno                          NUMBER(10)     NOT NULL,
    memid                          VARCHAR2(50)    UNIQUE NOT NULL,
    mememail                          VARCHAR2(100)   UNIQUE  NOT NULL,
    mempasswd                         VARCHAR2(20)     NOT NULL,
    memname                           VARCHAR2(20)     NOT NULL,
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
    mbirthvb                          VARCHAR2(1)    DEFAULT 'Y'     NOT NULL,
    mgendervb                         VARCHAR2(1)    DEFAULT 'Y'     NOT NULL,
    maddressvb                        VARCHAR2(1)    DEFAULT 'Y'     NOT NULL,
    mphonevb                          VARCHAR2(1)    DEFAULT 'Y'     NOT NULL,
    msnsvb                            VARCHAR2(1)    DEFAULT 'Y'     NOT NULL,
    mintrovb                          VARCHAR2(1)    DEFAULT 'Y'     NOT NULL,
    mphotovb                          VARCHAR2(1)    DEFAULT 'Y'     NOT NULL,
    memdate                           DATE     NOT NULL
*/
  /**
   * ȸ����ȣ
   */
  private int memberno;
  /**
   * ȸ�� ���̵�
   */
  private String memid;
  /**
   * ȸ�� �̸���
   */
  private String mememail;
  /**
   * ȸ�� �н�����
   */
  private String mempasswd;
  /**
   * ȸ�� �̸�
   */
  private String memname;
  /**
   * ȸ������ ���� Ȯ��= 'Y' ��Ȯ��= 'N'
   */
  private String memconfirm;
  /**
   * ȸ�� ���� ��ȸ��= 'N' ���ԿϷ�� ����= 'U'
   */
  private String memauth;
  /**
   * ȸ�� ������� '19891025'
   */
  private String membirth;
  /**
   * ȸ�� ���� '��', '��'
   */
  private String memgender;
  /**
   * ȸ�� �ּ� '����� ���ϱ� ������'
   */
  private String memaddress;
  /**
   * ȸ�� ��ȭ��ȣ '01012345678'
   */
  private String memphone;
  /**
   * ȸ�� SNS ���� 'Ʈ���� abcd'
   */
  private String memsns;
  /**
   * ȸ�� �ڱ�Ұ�
   */
  private String memintro;
  /**
   * ȸ�� ���� ����
   */
  private String memphoto;
  /**
   * ȸ�� ���� ���� �����
   */
  private String memphoto_t;
  /**
   * ȸ�� ���� ���� ������
   */
  private long memsize;
  /**
   * ȸ�� ������� ��� ���� ���= 'Y' �����='N'
   */
  private String mbirthvb;
  /**
   * ȸ�� ������� ��� ���� ���= 'Y' �����='N'
   */
  private String mgendervb;
  /**
   * ȸ�� ������� ��� ���� ���= 'Y' �����='N'
   */
  private String maddressvb;
  /**
   * ȸ�� ������� ��� ���� ���= 'Y' �����='N'
   */
  private String mphonevb;
  /**
   * ȸ�� ������� ��� ���� ���= 'Y' �����='N'
   */
  private String msnsvb;
  /**
   * ȸ�� ������� ��� ���� ���= 'Y' �����='N'
   */
  private String mintrovb;
  /**
   * ȸ�� ������� ��� ���� ���= 'Y' �����='N'
   */
  private String mphotovb;
  /**
   * ȸ�� ������
   */
  private String memdate;
  /**
   * ȸ�� �ּ� �� ����
   */
  private String area;
  /**
   * ȸ�� �ּ� �� ����
   */
  private String selected_area;
  
  /** 
  Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
*/  
  private MultipartFile file1MF;

/** size1�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String size1Label;
  
  /** ��ϵ� �н����� */
  private String old_passwd = "";
  /** id ���� ���� */
  private String id_save = "";
  /** passwd ���� ���� */
  private String passwd_save = "";
  /** �̵��� �ּ� ���� */
  private String url_address = "";
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

  
  public MemberVO() {
    
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }  

  public String getMemid() {
    return memid;
  }

  public void setMemid(String memid) {
    this.memid = memid;
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

  public long getMemsize() {
    return memsize;
  }

  public void setMemsize(long memsize) {
    this.memsize = memsize;
  }
  
  public String getMemdate() {
    return memdate;
  }

  public void setMemdate(String memdate) {
    this.memdate = memdate;
  }

  public String getMbirthvb() {
    return mbirthvb;
  }

  public void setMbirthvb(String mbirthvb) {
    this.mbirthvb = mbirthvb;
  }

  public String getMgendervb() {
    return mgendervb;
  }

  public void setMgendervb(String mgendervb) {
    this.mgendervb = mgendervb;
  }

  public String getMaddressvb() {
    return maddressvb;
  }

  public void setMaddressvb(String maddressvb) {
    this.maddressvb = maddressvb;
  }

  public String getMphonevb() {
    return mphonevb;
  }

  public void setMphonevb(String mphonevb) {
    this.mphonevb = mphonevb;
  }

  public String getMsnsvb() {
    return msnsvb;
  }

  public void setMsnsvb(String msnsvb) {
    this.msnsvb = msnsvb;
  }

  public String getMintrovb() {
    return mintrovb;
  }

  public void setMintrovb(String mintrovb) {
    this.mintrovb = mintrovb;
  }

  public String getMphotovb() {
    return mphotovb;
  }

  public void setMphotovb(String mphotovb) {
    this.mphotovb = mphotovb;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getSelected_area() {
    return selected_area;
  }

  public void setSelected_area(String selected_area) {
    this.selected_area = selected_area;
  }

  public MultipartFile getFile1MF() {
    return file1MF;
  }

  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }

  public String getSize1Label() {
    return size1Label;
  }

  public void setSize1Label(String size1Label) {
    this.size1Label = size1Label;
  }

  public String getOld_passwd() {
    return old_passwd;
  }

  public void setOld_passwd(String old_passwd) {
    this.old_passwd = old_passwd;
  }

  public String getId_save() {
    return id_save;
  }

  public void setId_save(String id_save) {
    this.id_save = id_save;
  }

  public String getPasswd_save() {
    return passwd_save;
  }

  public void setPasswd_save(String passwd_save) {
    this.passwd_save = passwd_save;
  }

  public String getUrl_address() {
    return url_address;
  }

  public void setUrl_address(String url_address) {
    this.url_address = url_address;
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
