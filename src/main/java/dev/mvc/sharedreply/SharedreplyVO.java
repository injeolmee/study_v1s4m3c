package dev.mvc.sharedreply;

public class SharedreplyVO {

/*  CREATE TABLE sharedreply(
    shreplyno                          NUMBER(7)    NOT NULL,
    shreplycontent                    VARCHAR2(500)    NOT NULL,
    shreplyname                       VARCHAR2(100)    NOT NULL,
    shreplydate                        DATE     NOT NULL,
    shreplypasswd                     VARCHAR2(50)     NULL ,
    shreplygrpno                      NUMBER(7)    NOT NULL,
    shreplyindent                      NUMBER(7)    DEFAULT 0     NOT NULL,
    shreplyansnum                     NUMBER(7)    DEFAULT 0     NOT NULL,
    sharedno                            NUMBER(7)    NULL ,
    MEMBERNO                          NUMBER(10)     NULL 
);*/
  /* ��� ��ȣ */
  private int shreplyno;
  /* ���� */
  private String shreplycontent;
  /* �ۼ��� */
  private String shreplyname;
  /* ����� */
  private String shreplydate;
  /* ��� �׷��ȣ */
  private int shreplygrpno;
  /* ��� ���� */
  private int shreplyindent;
  /* ��� ���� */
  private int shreplyansnum;
  /* �Խñ� ��ȣ */
  private int sharedno;
  /* ȸ�� ��ȣ */
  private int memberno;
  /* ���� ������ */
  private int nowPage;
  /* ������ ���� �ӽ� ���� */
  private int pre_num;
  /* ������ ���� �ӽ� ���� */
  private int post_num;
  /* �˻��� ���� ���� �ӽ� ���� */
  private String word="";
  /* �˻� �з��� ���� �ӽ� ����*/
  private String search="";
  /* �θ�/�ڽ� ������ ���� �ӽ� ����*/
  private int seqno;
  /* ������ ��ȣ */
  private int adminno;
 
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  public int getSeqno() {
    return seqno;
  }
  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }
  public int getShreplyno() {
    return shreplyno;
  }
  public void setShreplyno(int shreplyno) {
    this.shreplyno = shreplyno;
  }
  public String getShreplycontent() {
    return shreplycontent;
  }
  public void setShreplycontent(String shreplycontent) {
    this.shreplycontent = shreplycontent;
  }
  public String getShreplyname() {
    return shreplyname;
  }
  public void setShreplyname(String shreplyname) {
    this.shreplyname = shreplyname;
  }
  public String getShreplydate() {
    return shreplydate;
  }
  public void setShreplydate(String shreplydate) {
    this.shreplydate = shreplydate;
  }
  public int getShreplygrpno() {
    return shreplygrpno;
  }
  public void setShreplygrpno(int shreplygrpno) {
    this.shreplygrpno = shreplygrpno;
  }
  public int getShreplyindent() {
    return shreplyindent;
  }
  public void setShreplyindent(int shreplyindent) {
    this.shreplyindent = shreplyindent;
  }
  public int getShreplyansnum() {
    return shreplyansnum;
  }
  public void setShreplyansnum(int shreplyansnum) {
    this.shreplyansnum = shreplyansnum;
  }
  public int getSharedno() {
    return sharedno;
  }
  public void setSharedno(int sharedno) {
    this.sharedno = sharedno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getNowPage() {
    return nowPage;
  }
  public void setNowPage(int nowPage) {
    this.nowPage = nowPage;
  }
  public int getPre_num() {
    return pre_num;
  }
  public void setPre_num(int pre_num) {
    this.pre_num = pre_num;
  }
  public int getPost_num() {
    return post_num;
  }
  public void setPost_num(int post_num) {
    this.post_num = post_num;
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
  
}
