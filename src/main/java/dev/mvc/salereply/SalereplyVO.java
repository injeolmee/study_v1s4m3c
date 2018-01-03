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
  
  /* 댓글 번호*/
  private int sreplyno;
  /* 내용 */
  private String sreplycontent;
  /* 작성자 */
  private String sreplyname;
  /* 등록일 */
  private String sreplydate;
  /* 패스워드 */
  private String sreplypasswd;
  /* 댓글 그룹번호 */
  private int sreplygrpno;
  /* 대댓글 차수(대댓글을 순차적으로 댓글 아래에 배치하기 위한 변수) */
  private int sreplyindent;
  /* 대댓글 순서(대댓글끼리 최근 등록순으로 정렬하기 위한 변수 */
  private int sreplyansnum;
  /* 거래게시판 글 번호 */
  private int saleno;
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
  /* 부모와 자식 댓글을 구별하기 위한 임시 변수*/
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
