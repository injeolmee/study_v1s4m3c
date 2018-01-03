package dev.mvc.notice;

public class NoticeVO {
  
/*  CREATE TABLE notice(
    noticeno                         NUMBER(10)     PRIMARY KEY,
    ntitle                              VARCHAR2(500)    NOT NULL,
    nname                             VARCHAR2(100)    NOT NULL,
    ncontent                    VARCHAR2(4000)     NOT NULL,
    nseqno                      NUMBER(10)     DEFAULT 1     NOT NULL,
    ncnt                        NUMBER(10)     DEFAULT 0     NOT NULL,
    ndate                       DATE     NOT NULL,
    adminno                           NUMBER(10)     NOT NULL,
    FOREIGN KEY (adminno) REFERENCES admin (adminno)
);*/
  
  /**
   * �������� ��ȣ
   */
  private int noticeno;
  /**
   * �������� ����
   */
  private String ntitle;
  /**
   * �������� �ۼ���
   */
  private String nname;
  /**
   * �������� ����
   */
  private String ncontent;
  /**
   * �������� ��� ����
   */
  private int nseqno;
  /**
   * �������� ��ȸ��
   */
  private int ncnt;
  /**
   * �������� �����
   */
  private String ndate;
  /**
   * ������ ��ȣ
   */
  private int adminno;
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
  private int nowPage=1;

  public NoticeVO() {
  }

  public int getNoticeno() {
    return noticeno;
  }

  public void setNoticeno(int noticeno) {
    this.noticeno = noticeno;
  }

  public String getNtitle() {
    return ntitle;
  }

  public void setNtitle(String ntitle) {
    this.ntitle = ntitle;
  }

  public String getNname() {
    return nname;
  }

  public void setNname(String nname) {
    this.nname = nname;
  }

  public String getNcontent() {
    return ncontent;
  }

  public void setNcontent(String ncontent) {
    this.ncontent = ncontent;
  }

  public int getNseqno() {
    return nseqno;
  }

  public void setNseqno(int nseqno) {
    this.nseqno = nseqno;
  }

  public int getNcnt() {
    return ncnt;
  }

  public void setNcnt(int ncnt) {
    this.ncnt = ncnt;
  }

  public String getNdate() {
    return ndate;
  }

  public void setNdate(String ndate) {
    this.ndate = ndate;
  }

  public int getAdminno() {
    return adminno;
  }

  public void setAdminno(int adminno) {
    this.adminno = adminno;
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
