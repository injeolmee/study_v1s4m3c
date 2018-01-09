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
  /* 댓글 번호 */
  private int shreplyno;
  /* 내용 */
  private String shreplycontent;
  /* 작성자 */
  private String shreplyname;
  /* 등록일 */
  private String shreplydate;
  /* 댓글 그룹번호 */
  private int shreplygrpno;
  /* 댓글 차수 */
  private int shreplyindent;
  /* 댓글 순서 */
  private int shreplyansnum;
  /* 게시글 번호 */
  private int sharedno;
  /* 회원 번호 */
  private int memberno;
  /* 현재 페이지 */
  private int nowPage;
  /* 이전글 위한 임시 변수 */
  private int pre_num;
  /* 다음글 위한 임시 변수 */
  private int post_num;
  /* 검색어 저장 위한 임시 변수 */
  private String word="";
  /* 검색 분류를 위한 임시 변수*/
  private String search="";
  /* 부모/자식 구별을 위한 임시 변수*/
  private int seqno;
  /* 관리자 번호 */
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
