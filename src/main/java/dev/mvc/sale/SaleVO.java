package dev.mvc.sale;

import org.springframework.web.multipart.MultipartFile;

public class SaleVO {
  
/*  CREATE TABLE sale(
      saleno                            NUMBER(7)    NOT NULL,
      saletitle                         VARCHAR2(100)    NOT NULL,
      salecontent                       VARCHAR2(1000)     NOT NULL,
      salename                          VARCHAR2(50)     NOT NULL,
      salenaming                        VARCHAR2(100)    NOT NULL,
      saledate                          DATE     NOT NULL,
      salecnt                           NUMBER(6)    DEFAULT 0     NOT NULL,
      saleprice                         NUMBER(30)     NOT NULL,
      saleaddress                       VARCHAR2(50)     NULL ,
      saletel                           VARCHAR2(50)     NULL ,
      saleemail                         VARCHAR2(50)     NULL ,
      salepasswd                        VARCHAR2(50)     NULL ,
      salefile                          VARCHAR2(500)    NULL ,
      salefstor                         VARCHAR2(500)    NULL ,
      saletum                           VARCHAR2(500)    NULL ,
      salesize                          NUMBER(30)     DEFAULT 0     NOT NULL,
      saleseqno                         NUMBER(10)     DEFAULT 1     NOT NULL,
      MEMBERNO                          NUMBER(10)     NULL 
  );*/
  
  /* 게시판 번호 */
  private int saleno;
  /* 제목 */
  private String saletitle;
  /* 내용 */
  private String salecontent;
  /* 상품명 */
  private String saletname;
  /* 이름 */
  private String salename;
  /* 등록일 */
  private String saledate;
  /* 조회수 */
  private int salecnt;
  /* 가격 */
  private int saleprice;
  /* 주소 */
  private String saleaddress;
  /* 전화번호 */
  private String saletel;
  /* 이메일 */
  private String saleemail;
  /* 패스워드 */
  private String salepasswd;
  /* 파일명 */
  private String salefile;
  /* 실제 파일명*/
  private String salefstor;
  /* 파일 썸네일 */
  private String saletum;
  /* 파일 사이즈 */
  private long salesize;
  /* 출력 순서 (추천수에 따라) */
  private int saleseqno;
  /* 회원 번호 */
  private int memberno;
  /* 파일 실제 사이즈 위한 임시 변수*/
  private MultipartFile file1MF;
  /** size1의 컴마 저장 출력용 임시 변수 */
  private String salesizeLabel;
  /* 검색어 저장 위한 임시 변수 */
  private String word="";
  /* 검색 분류를 위한 임시 변수*/
  private String search="";
  /* 제목 + 내용 검색 위한 임시 변수 */
  private String saletn;
  /* 페이징 위한 임시 변수 */
  private int nowPage;
  /* 이전글 위한 임시 변수 */
  private int pre_num;
  /* 다음글 위한 임시 변수 */
  private int post_num;
  /* 출력 변경을 위한 임시 변수 */
  private int check_seqno;

  public int getCheck_seqno() {
    return check_seqno;
  }
  public void setCheck_seqno(int check_seqno) {
    this.check_seqno = check_seqno;
  }
  public String getSaletname() {
    return saletname;
  }
  public void setSaletname(String saletname) {
    this.saletname = saletname;
  }
  public String getSalefstor() {
    return salefstor;
  }
  public void setSalefstor(String salefstor) {
    this.salefstor = salefstor;
  }
  public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }
  public String getSalesizeLabel() {
    return salesizeLabel;
  }
  public void setSalesizeLabel(String salesizeLabel) {
    this.salesizeLabel = salesizeLabel;
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
  public String getSaletn() {
    return saletn;
  }
  public void setSaletn(String saletn) {
    this.saletn = saletn;
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
  public int getSaleno() {
    return saleno;
  }
  public void setSaleno(int saleno) {
    this.saleno = saleno;
  }
  public String getSaletitle() {
    return saletitle;
  }
  public void setSaletitle(String saletitle) {
    this.saletitle = saletitle;
  }
  public String getSalecontent() {
    return salecontent;
  }
  public void setSalecontent(String salecontent) {
    this.salecontent = salecontent;
  }
  public String getSalename() {
    return salename;
  }
  public void setSalename(String salename) {
    this.salename = salename;
  }
  public String getSaledate() {
    return saledate;
  }
  public void setSaledate(String saledate) {
    this.saledate = saledate;
  }
  public int getSalecnt() {
    return salecnt;
  }
  public void setSalecnt(int salecnt) {
    this.salecnt = salecnt;
  }
  public int getSaleprice() {
    return saleprice;
  }
  public void setSaleprice(int saleprice) {
    this.saleprice = saleprice;
  }
  public String getSaleaddress() {
    return saleaddress;
  }
  public void setSaleaddress(String saleaddress) {
    this.saleaddress = saleaddress;
  }
  public String getSaletel() {
    return saletel;
  }
  public void setSaletel(String saletel) {
    this.saletel = saletel;
  }
  public String getSaleemail() {
    return saleemail;
  }
  public void setSaleemail(String saleemail) {
    this.saleemail = saleemail;
  }
  public String getSalepasswd() {
    return salepasswd;
  }
  public void setSalepasswd(String salepasswd) {
    this.salepasswd = salepasswd;
  }
  public String getSalefile() {
    return salefile;
  }
  public void setSalefile(String salefile) {
    this.salefile = salefile;
  }
  public String getSaletum() {
    return saletum;
  }
  public void setSaletum(String saletum) {
    this.saletum = saletum;
  }
  public long getSalesize() {
    return salesize;
  }
  public void setSalesize(long salesize) {
    this.salesize = salesize;
  }
  public int getSaleseqno() {
    return saleseqno;
  }
  public void setSaleseqno(int saleseqno) {
    this.saleseqno = saleseqno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }


}
