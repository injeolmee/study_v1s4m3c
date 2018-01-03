package dev.mvc.category;
 
public class Categrp_CategoryVO {
  
  //--------------------------------categrp table
  /**
   * 카테그룹 번호
   */
  private int grpno;
  /**
   * 카테그룹 이름
   */
  private String grpname;
  /**
   * 카테그룹 출력순서
   */
  private String grpseqno;
  /**
   * 카테그룹 출력여부
   */
  private String grpvisible;
  
  // ---------------------------- category table
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
  
  
  public int getGrpno() {
    return grpno;
  }
  public void setGrpno(int grpno) {
    this.grpno = grpno;
  }
  public String getGrpname() {
    return grpname;
  }
  public void setGrpname(String grpname) {
    this.grpname = grpname;
  }
  public String getGrpseqno() {
    return grpseqno;
  }
  public void setGrpseqno(String grpseqno) {
    this.grpseqno = grpseqno;
  }
  public String getGrpvisible() {
    return grpvisible;
  }
  public void setGrpvisible(String grpvisible) {
    this.grpvisible = grpvisible;
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

  
  
 
 
  
}
 