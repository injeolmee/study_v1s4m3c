package dev.mvc.category;
 
public class Categrp_CategoryVO {
  
  //--------------------------------categrp table
  /**
   * ī�ױ׷� ��ȣ
   */
  private int grpno;
  /**
   * ī�ױ׷� �̸�
   */
  private String grpname;
  /**
   * ī�ױ׷� ��¼���
   */
  private String grpseqno;
  /**
   * ī�ױ׷� ��¿���
   */
  private String grpvisible;
  
  // ---------------------------- category table
  /**
   * ī�װ� ��ȣ
   */
  private int cateno;
  /**
   * ī�װ� �̸�
   */
  private String catename;
  /**
   * ī�װ� ��� ����
   */
  private int cateseqno;
  /**
   * ī�װ� ��� ���
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
 