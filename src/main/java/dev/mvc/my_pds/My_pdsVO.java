package dev.mvc.my_pds;

import org.springframework.web.multipart.MultipartFile;

public class My_pdsVO {
/*  CREATE TABLE my_pds(
      pdsno                             NUMBER(10)     NOT NULL    PRIMARY KEY,
      pdstitle                          VARCHAR2(100)    NOT NULL,
      pdspasswd                         VARCHAR2(20)     NULL ,
      pdscontent                        VARCHAR2(4000)     NULL ,
      pdsfile1                          VARCHAR2(10)     NULL ,
      pdsfilesize                       NUMBER(10)     DEFAULT 0     NULL ,
      pdsthumb                          VARCHAR2(100)    NULL ,
      pdsword                           VARCHAR2(100)    NULL ,
      pdslike                           NUMBER(10)     DEFAULT 0     NULL ,
      pdscnt                            NUMBER(10)     DEFAULT 0     NULL ,
      pdsdate                           DATE     NOT NULL,
      memberno                          NUMBER(10)     NULL ,
      mylistno                          NUMBER(10)     NULL ,
    FOREIGN KEY (mylistno) REFERENCES my_std_catelist (mylistno),
    FOREIGN KEY (memberno) REFERENCES member (memberno)
  );*/
  
  private int pdsno;
  private String pdstitle;
  private String pdspasswd;
  private String pdscontent;
  private String pdsfile1;
  private int pdsfilesize;
  private String pdsthumb; 
  private String pdsword;  
  private int pdslike;
  private int pdscnt;
  private String pdsdate; 
  private int memberno;
  private int mylistno;
  
  /**=DB�� ���� property ===========================================*/ 
  /** Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  ���� �÷��� �������� ����.
  */  
  private MultipartFile file1MF;
  
  /** size1�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String size1Label;
  
  /**
   * memberno�� �������� sql�� ���������� ���� ������ �̸�.
   */
  private String memname;
  
  private String memid;
  
  /**
   * �ӽ������Ͽ� ����ϱ����� ����.
   */
  private int stdlist_no;
  private int cateno;
  
  /**
   * �˻� ��� ���� ����
   */
  private int search_count;
  
  /**
   * ����¡ ����
   */
  private int RECORD_PER_PAGE;
  private int PAGE_PER_BLOCK;
  
  /**================================================================*/  
  
  public String getMemid() {
    return memid;
  }

  public void setMemid(String memid) {
    this.memid = memid;
  }

  public int getRECORD_PER_PAGE() {
    return RECORD_PER_PAGE;
  }

  public void setRECORD_PER_PAGE(int rECORD_PER_PAGE) {
    RECORD_PER_PAGE = rECORD_PER_PAGE;
  }

  public int getPAGE_PER_BLOCK() {
    return PAGE_PER_BLOCK;
  }

  public void setPAGE_PER_BLOCK(int pAGE_PER_BLOCK) {
    PAGE_PER_BLOCK = pAGE_PER_BLOCK;
  }
 
  public int getStdlist_no() {
    return stdlist_no;
  }
  
  public int getSearch_count() {
    return search_count;
  }

  public void setSearch_count(int search_count) {
    this.search_count = search_count;
  }

  public void setStdlist_no(int stdlist_no) {
    this.stdlist_no = stdlist_no;
  }

  public int getCateno() {
    return cateno;
  }

  public void setCateno(int cateno) {
    this.cateno = cateno;
  }
  

  public My_pdsVO() {
    // TODO Auto-generated constructor stub
  }
  
  public String getMemname() {
    return memname;
  }

  public void setMemname(String memname) {
    this.memname = memname;
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

  public int getPdsno() {
    return pdsno;
  }
  public void setPdsno(int pdsno) {
    this.pdsno = pdsno;
  }
  public String getPdstitle() {
    return pdstitle;
  }
  public void setPdstitle(String pdstitle) {
    this.pdstitle = pdstitle;
  }
  public String getPdspasswd() {
    return pdspasswd;
  }
  public void setPdspasswd(String pdspasswd) {
    this.pdspasswd = pdspasswd;
  }
  public String getPdscontent() {
    return pdscontent;
  }
  public void setPdscontent(String pdscontent) {
    this.pdscontent = pdscontent;
  }
  public String getPdsfile1() {
    return pdsfile1;
  }
  public void setPdsfile1(String pdsfile1) {
    this.pdsfile1 = pdsfile1;
  }
  public int getPdsfilesize() {
    return pdsfilesize;
  }
  public void setPdsfilesize(int pdsfilesize) {
    this.pdsfilesize = pdsfilesize;
  }
  public String getPdsthumb() {
    return pdsthumb;
  }
  public void setPdsthumb(String pdsthumb) {
    this.pdsthumb = pdsthumb;
  }
  public String getPdsword() {
    return pdsword;
  }
  public void setPdsword(String pdsword) {
    this.pdsword = pdsword;
  }
  public int getPdslike() {
    return pdslike;
  }
  public void setPdslike(int pdslike) {
    this.pdslike = pdslike;
  }
  public int getPdscnt() {
    return pdscnt;
  }
  public void setPdscnt(int pdscnt) {
    this.pdscnt = pdscnt;
  }
  public String getPdsdate() {
    return pdsdate;
  }
  public void setPdsdate(String pdsdate) {
    this.pdsdate = pdsdate;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getMylistno() {
    return mylistno;
  }
  public void setMylistno(int mylistno) {
    this.mylistno = mylistno;
  }
  
  
}
