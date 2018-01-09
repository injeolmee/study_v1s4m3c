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
    
  /* 자유게시판 번호 */    
  private int freeno; 
  /* 제목 */
  private String freetitle; 
  /* 내용 */
  private String freecontent;  
  /* 이름 */
  private String freename; 
  /* 등록일 */
  private String freedate;
  /* 조회수 */
  private int freecnt;
  /* 추천수 */
  private int freelike;
  /* 패스워드 */
  private String freepasswd; 
  /* 회원번호 */
  private int memberno;
  /* 관리자번호 */
  private int adminno;
  /* 출력 순서 */
  private int freeseqno;
  /* 검색을 위한 임시 변수*/
  private String word; 
  /* 검색 옵션에 따른 임시 변수*/
  private String search;
  /* 제목 + 작성자를 동시에 검색하기 위한 임시 변수*/
  private String freetn;
  /* 페이징의 현재페이지 사용할 임시 변수*/
  private int nowPage;
  /* 이전글 조회 위한 임시 변수*/
  private int pre_num;
  /* 다음글 조회 위한 임시 변수*/
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
