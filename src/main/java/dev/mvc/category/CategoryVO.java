package dev.mvc.category;

public class CategoryVO {
/*  CREATE TABLE category(
      cateno                            NUMBER(10)     PRIMARY KEY,
      catename                          VARCHAR2(50)     NOT NULL,
      cateseqno                         NUMBER(5)    NOT NULL,
      catevisible                       VARCHAR2(1)    DEFAULT 'Y'     NOT NULL,
      grpno                            NUMBER(10)  NULL,
      FOREIGN KEY (grpno) REFERENCES categrp (grpno)
  );*/
  
  /**
   * 카테고리 번호
   */
  private int cateno;
  /**
   * 카테고리 이름
   */
  private String catename;
  /**
   * 카테고리 출력 순서
   */
  private int cateseqno;
  /**
   * 카테고리 출력 모드
   */
  private String catevisible;
  /**
   * 카테그룹 번호
   */
  private int grpno;
  
  
  public CategoryVO() {
    
  }


  public int getCateno() {
    return cateno;
  }


  public void setCateno(int cateno) {
    this.cateno = cateno;
  }


  public String getCatename() {
    return catename;
  }


  public void setCatename(String catename) {
    this.catename = catename;
  }


  public int getCateseqno() {
    return cateseqno;
  }


  public void setCateseqno(int cateseqno) {
    this.cateseqno = cateseqno;
  }


  public String getCatevisible() {
    return catevisible;
  }


  public void setCatevisible(String catevisible) {
    this.catevisible = catevisible;
  }


  public int getGrpno() {
    return grpno;
  }


  public void setGrpno(int grpno) {
    this.grpno = grpno;
  }
  
  
  
  
}
