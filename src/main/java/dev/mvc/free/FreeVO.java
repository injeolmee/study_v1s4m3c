package dev.mvc.free;

public class FreeVO {
   

/*  CREATE TABLE free(
      freeno                            NUMBER(7)    NOT NULL,
      freetitle                         VARCHAR2(100)    NOT NULL,
      freecontent                       VARCHAR2(1000)     NOT NULL,
      freename                          VARCHAR2(50)     NOT NULL,
      freedate                          DATE     NOT NULL,
      freecnt                           NUMBER(6)    DEFAULT 0     NOT NULL,
      freelike                          NUMBER(6)    DEFAULT 0     NOT NULL,
      freepasswd                        VARCHAR2(50)     NULL ,
      MEMBERNO                          NUMBER(10)     NULL 
  );*/
    
  /* �����Խ��� ��ȣ */    
  private int freeno; 
  /* ���� */
  private String freetitle; 
  /* ���� */
  private String freecontent;  
  /* �̸� */
  private String freename; 
  /* ����� */
  private String freedate;
  /* ��ȸ�� */
  private int freecnt;
  /* ��õ�� */
  private int freelike;
  /* �н����� */
  private String freepasswd; 
  /* ȸ����ȣ */
  private int memberno;
  /* �����ڹ�ȣ */
  private int adminno;
  /* ��� ���� */
  private int freeseqno;
  /* �˻��� ���� �ӽ� ����*/
  private String word; 
  /* �˻� �ɼǿ� ���� �ӽ� ����*/
  private String search;
  /* ���� + �ۼ��ڸ� ���ÿ� �˻��ϱ� ���� �ӽ� ����*/
  private String freetn;
  /* ����¡�� ���������� ����� �ӽ� ����*/
  private int nowPage;
  /* ������ ��ȸ ���� �ӽ� ����*/
  private int pre_num;
  /* ������ ��ȸ ���� �ӽ� ����*/
  private int post_num;
  
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    this.adminno = adminno;
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
  public int getNowPage() {
    return nowPage;
  }
  public void setNowPage(int nowPage) {
    this.nowPage = nowPage;
  }
  public String getSearch() {
    return search;
  }
  public void setSearch(String search) {
    this.search = search;
  }
  public String getFreetn() {
    return freetn;
  }
  public void setFreetn(String freetn) {
    this.freetn = freetn;
  }
  public int getFreeno() {
    return freeno;
  }
  public String getWord() {
    return word;
  }
  public void setWord(String word) {
    this.word = word;
  }
  public void setFreeno(int freeno) {
    this.freeno = freeno;
  }
  public String getFreetitle() {
    return freetitle;
  }
  public void setFreetitle(String freetitle) {
    this.freetitle = freetitle;
  }
  public String getFreecontent() {
    return freecontent;
  }
  public void setFreecontent(String freecontent) {
    this.freecontent = freecontent;
  }
  public String getFreename() {
    return freename;
  }
  public void setFreename(String freename) {
    this.freename = freename;
  }
  public String getFreedate() {
    return freedate;
  }
  public void setFreedate(String freedate) {
    this.freedate = freedate;
  }
  public int getFreecnt() {
    return freecnt;
  }
  public void setFreecnt(int freecnt) {
    this.freecnt = freecnt;
  }
  public int getFreelike() {
    return freelike;
  }
  public void setFreelike(int freelike) {
    this.freelike = freelike;
  }
  public String getFreepasswd() {
    return freepasswd;
  }
  public void setFreepasswd(String freepasswd) {
    this.freepasswd = freepasswd;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getFreeseqno() {
    return freeseqno;
  }
  public void setFreeseqno(int freeseqno) {
    this.freeseqno = freeseqno;
  }


}
