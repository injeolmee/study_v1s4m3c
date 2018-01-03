package dev.mvc.VO;

public class SaleVO {
  /**********************************/
  /* Table Name: �ŷ� �Խ��� */
  /**********************************/
/*  CREATE TABLE sale(
      saleno                            NUMBER(7)    NOT NULL,
      saletitle                         VARCHAR2(100)    NOT NULL,
      salecontent                       VARCHAR2(4000)     NOT NULL,
      salename                          VARCHAR2(50)     NOT NULL,
      saledate                          DATE     NOT NULL,
      salecnt                           NUMBER(6)    DEFAULT 0     NOT NULL,
      saleprice                         NUMBER(30)     NOT NULL,
      saleaddress                       VARCHAR2(50)     NULL ,
      saletel                           VARCHAR2(50)     NULL ,
      saleemail                         VARCHAR2(50)     NULL ,
      salepasswd                        VARCHAR2(50)     NOT NULL,
      salefile                          VARCHAR2(500)    NULL ,
      saletum                           VARCHAR2(500)    NULL ,
      salesize                          NUMBER(30)     DEFAULT 0     NOT NULL,
      saleseqno                         NUMBER(10)     DEFAULT 1     NOT NULL,
      memberno                          NUMBER(10)     NULL ,
      cateno                            NUMBER(10)     NULL ,
      adminno                           NUMBER(10)     NULL 
  );

  COMMENT ON TABLE sale is '�ŷ� �Խ���';
  COMMENT ON COLUMN sale.saleno is '�Խ��� ��ȣ';
  COMMENT ON COLUMN sale.saletitle is '����';
  COMMENT ON COLUMN sale.salecontent is '����';
  COMMENT ON COLUMN sale.salename is '�̸�';
  COMMENT ON COLUMN sale.saledate is '�����';
  COMMENT ON COLUMN sale.salecnt is '��ȸ��';
  COMMENT ON COLUMN sale.saleprice is '����';
  COMMENT ON COLUMN sale.saleaddress is '�ּ�';
  COMMENT ON COLUMN sale.saletel is '�޴��� ��ȣ';
  COMMENT ON COLUMN sale.saleemail is '�̸���';
  COMMENT ON COLUMN sale.salepasswd is '�н�����';
  COMMENT ON COLUMN sale.salefile is '����';
  COMMENT ON COLUMN sale.saletum is '���� �����';
  COMMENT ON COLUMN sale.salesize is '���� ũ��';
  COMMENT ON COLUMN sale.saleseqno is '��� ����';
  COMMENT ON COLUMN sale.memberno is 'ȸ����ȣ';
  COMMENT ON COLUMN sale.cateno is 'ī�װ���ȣ';
  COMMENT ON COLUMN sale.adminno is '�����ڹ�ȣ';*/
  
  private int saleno;     
  private String saletitle;  
  private String salecontent;
  private String salename;   
  private String saledate;   
  private int salecnt;    
  private int saleprice;  
  private String saleaddress;
  private String saletel;    
  private String saleemail;  
  private String salepasswd; 
  private String salefile;   
  private String saletum;    
  private int salesize;   
  private int saleseqno;  
  private int memberno;   
  private int cateno;     
  private int adminno;
  
  public SaleVO() {
    // TODO Auto-generated constructor stub
  }

  public SaleVO(int saleno, String saletitle, String salecontent, String salename, String saledate, int salecnt,
      int saleprice, String saleaddress, String saletel, String saleemail, String salepasswd, String salefile,
      String saletum, int salesize, int saleseqno, int memberno, int cateno, int adminno) {
    super();
    this.saleno = saleno;
    this.saletitle = saletitle;
    this.salecontent = salecontent;
    this.salename = salename;
    this.saledate = saledate;
    this.salecnt = salecnt;
    this.saleprice = saleprice;
    this.saleaddress = saleaddress;
    this.saletel = saletel;
    this.saleemail = saleemail;
    this.salepasswd = salepasswd;
    this.salefile = salefile;
    this.saletum = saletum;
    this.salesize = salesize;
    this.saleseqno = saleseqno;
    this.memberno = memberno;
    this.cateno = cateno;
    this.adminno = adminno;
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

  public int getSalesize() {
    return salesize;
  }

  public void setSalesize(int salesize) {
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

  public int getCateno() {
    return cateno;
  }

  public void setCateno(int cateno) {
    this.cateno = cateno;
  }

  public int getAdminno() {
    return adminno;
  }

  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  
  
}
