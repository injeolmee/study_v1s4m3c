package dev.mvc.VO;

public class CategoryVO {
  /*CREATE TABLE category(
      cateno                            NUMBER(10)     NOT NULL,
      catename                          VARCHAR2(50)     NOT NULL,
      cateseqno                         NUMBER(5)    NOT NULL,
      catevisible                       VARCHAR2(1)    DEFAULT 'Y'     NOT NULL
  );

  COMMENT ON TABLE category is 'ī�װ�';
  COMMENT ON COLUMN category.cateno is 'ī�װ���ȣ';
  COMMENT ON COLUMN category.catename is 'ī�װ��̸�';
  COMMENT ON COLUMN category.cateseqno is 'ī�װ���¼���';
  COMMENT ON COLUMN category.catevisible is 'ī�װ���¿���';*/
  
  private int cateno;     
  private String catename;   
  private int cateseqno;  
  private String catevisible;
  
  public CategoryVO() {
    //System.out.println(" --> categoryVO created.");
  }
  
  public CategoryVO(int cateno, String catename, int cateseqno, String catevisible) {
    super();
    this.cateno = cateno;
    this.catename = catename;
    this.cateseqno = cateseqno;
    this.catevisible = catevisible;
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
