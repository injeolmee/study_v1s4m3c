package dev.mvc.freereply;

public class FreereplyVO {
  
/*  CREATE TABLE freereply(
      freplyno                          NUMBER(7)    NOT NULL,
      freplycontent                     VARCHAR2(500)    NOT NULL,
      freplyname                        VARCHAR2(100)    NOT NULL,
      freplydate                        DATE     NOT NULL,
      freplypasswd                      VARCHAR2(50)     NULL ,
      freplygrpno                       NUMBER(7)    NOT NULL,
      freplyindent                      NUMBER(7)    DEFAULT 0     NOT NULL,
      freplyansnum                      NUMBER(7)    DEFAULT 0     NOT NULL,
      freeno                            NUMBER(7)    NULL ,
      MEMBERNO                          NUMBER(10)     NULL 
  );*/
  
  /* ��� ��ȣ */
  private int freplyno;
  /* ���� */
  private String freplycontent;
  /* �ۼ��� */
  private String freplyname;
  /* ����� */
  private String freplydate;
  /* �н����� */
  private String freplypasswd;
  /* ��� �׷��ȣ */
  private int freplygrpno;
  /* ��� ���� */
  private int freplyindent;
  /* ��� ���� */
  private int freplyansnum;
  /* �Խñ� ��ȣ */
  private int freeno;
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
  /* �θ�� �ڽ� ����� �����ϱ� ���� �ӽ� ����*/
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
  public int getFreplyno() {
    return freplyno;
  }
  public void setFreplyno(int freplyno) {
    this.freplyno = freplyno;
  }
  public String getFreplycontent() {
    return freplycontent;
  }
  public void setFreplycontent(String freplycontent) {
    this.freplycontent = freplycontent;
  }
  public String getFreplyname() {
    return freplyname;
  }
  public void setFreplyname(String freplyname) {
    this.freplyname = freplyname;
  }
  public String getFreplydate() {
    return freplydate;
  }
  public void setFreplydate(String freplydate) {
    this.freplydate = freplydate;
  }
  public String getFreplypasswd() {
    return freplypasswd;
  }
  public void setFreplypasswd(String freplypasswd) {
    this.freplypasswd = freplypasswd;
  }
  public int getFreplygrpno() {
    return freplygrpno;
  }
  public void setFreplygrpno(int freplygrpno) {
    this.freplygrpno = freplygrpno;
  }
  public int getFreplyindent() {
    return freplyindent;
  }
  public void setFreplyindent(int freplyindent) {
    this.freplyindent = freplyindent;
  }
  public int getFreplyansnum() {
    return freplyansnum;
  }
  public void setFreplyansnum(int freplyansnum) {
    this.freplyansnum = freplyansnum;
  }
  public int getFreeno() {
    return freeno;
  }
  public void setFreeno(int freeno) {
    this.freeno = freeno;
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
