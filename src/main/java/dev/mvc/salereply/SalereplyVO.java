package dev.mvc.salereply;

public class SalereplyVO {
  
  /*CREATE TABLE salereply(
    sreplyno                          NUMBER(7)    NOT NULL,
    sreplycontent                     VARCHAR2(500)    NOT NULL,
    sreplyname                        VARCHAR2(100)    NOT NULL,
    sreplydate                        DATE     NOT NULL,
    sreplypasswd                      VARCHAR2(50)     NULL ,
    sreplygrpno                       NUMBER(7)    NOT NULL,
    sreplyindent                      NUMBER(7)    DEFAULT 0     NOT NULL,
    sreplyansnum                      NUMBER(7)    DEFAULT 0     NOT NULL,
    saleno                            NUMBER(7)    NULL ,
    MEMBERNO                          NUMBER(10)     NULL 
);*/
  
  /* ��� ��ȣ*/
  private int sreplyno;
  /* ���� */
  private String sreplycontent;
  /* �ۼ��� */
  private String sreplyname;
  /* ����� */
  private String sreplydate;
  /* �н����� */
  private String sreplypasswd;
  /* ��� �׷��ȣ */
  private int sreplygrpno;
  /* ���� ����(������ ���������� ��� �Ʒ��� ��ġ�ϱ� ���� ����) */
  private int sreplyindent;
  /* ���� ����(���۳��� �ֱ� ��ϼ����� �����ϱ� ���� ���� */
  private int sreplyansnum;
  /* �ŷ��Խ��� �� ��ȣ */
  private int saleno;
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
  
  public int getSeqno() {
    return seqno;
  }
  public void setSeqno(int seqno) {
    this.seqno = seqno;
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
  public int getSreplyno() {
    return sreplyno;
  }
  public void setSreplyno(int sreplyno) {
    this.sreplyno = sreplyno;
  }
  public String getSreplycontent() {
    return sreplycontent;
  }
  public void setSreplycontent(String sreplycontent) {
    this.sreplycontent = sreplycontent;
  }
  public String getSreplyname() {
    return sreplyname;
  }
  public void setSreplyname(String sreplyname) {
    this.sreplyname = sreplyname;
  }
  public String getSreplydate() {
    return sreplydate;
  }
  public void setSreplydate(String sreplydate) {
    this.sreplydate = sreplydate;
  }
  public String getSreplypasswd() {
    return sreplypasswd;
  }
  public void setSreplypasswd(String sreplypasswd) {
    this.sreplypasswd = sreplypasswd;
  }
  public int getSreplygrpno() {
    return sreplygrpno;
  }
  public void setSreplygrpno(int sreplygrpno) {
    this.sreplygrpno = sreplygrpno;
  }
  public int getSreplyindent() {
    return sreplyindent;
  }
  public void setSreplyindent(int sreplyindent) {
    this.sreplyindent = sreplyindent;
  }
  public int getSreplyansnum() {
    return sreplyansnum;
  }
  public void setSreplyansnum(int sreplyansnum) {
    this.sreplyansnum = sreplyansnum;
  }
  public int getSaleno() {
    return saleno;
  }
  public void setSaleno(int saleno) {
    this.saleno = saleno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }


}
