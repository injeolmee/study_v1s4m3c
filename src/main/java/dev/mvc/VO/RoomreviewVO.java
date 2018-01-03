package dev.mvc.VO;

public class RoomreviewVO {
  /**********************************/
  /* Table Name: ���͵�� ���� */
  /**********************************/
/*  CREATE TABLE roomreview(
      rvno                              NUMBER(10)     NOT NULL,
      rono                              NUMBER(10)     NOT NULL,
      memberno                          NUMBER(10)     NULL ,
      rvdate                            DATE     NOT NULL,
      rvgood                            NUMBER(10)     NOT NULL,
      rvcont                            VARCHAR2(1000)     NOT NULL,
      rvup                              VARCHAR2(20)     DEFAULT 0     NOT NULL,
      rvdown                            VARCHAR2(20)     DEFAULT 0     NOT NULL,
      rvpw                              INTEGER(10)    NULL 
  );

  COMMENT ON TABLE roomreview is '���͵�� ����';
  COMMENT ON COLUMN roomreview.rvno is '���͵�� ���� ��� ��ȣ';
  COMMENT ON COLUMN roomreview.rono is '���͵�� ��� ��ȣ';
  COMMENT ON COLUMN roomreview.memberno is 'ȸ����ȣ';
  COMMENT ON COLUMN roomreview.rvdate is '���� �����';
  COMMENT ON COLUMN roomreview.rvgood is '���� ����';
  COMMENT ON COLUMN roomreview.rvcont is '���� ����';
  COMMENT ON COLUMN roomreview.rvup is '���� ���ƿ�';
  COMMENT ON COLUMN roomreview.rvdown is '���� �Ⱦ��';
  COMMENT ON COLUMN roomreview.rvpw is '��й�ȣ';*/
  
  private int rvno;   
  private int rono;   
  private int memberno;
  private String rvdate;  
  private String rvgood;  
  private int rvcont;  
  private String rvup;    
  private String rvdown;  
  private int rvpw;
  
  public RoomreviewVO() {
    // TODO Auto-generated constructor stub
  }

  public RoomreviewVO(int rvno, int rono, int memberno, String rvdate, String rvgood, int rvcont, String rvup,
      String rvdown, int rvpw) {
    super();
    this.rvno = rvno;
    this.rono = rono;
    this.memberno = memberno;
    this.rvdate = rvdate;
    this.rvgood = rvgood;
    this.rvcont = rvcont;
    this.rvup = rvup;
    this.rvdown = rvdown;
    this.rvpw = rvpw;
  }

  public int getRvno() {
    return rvno;
  }

  public void setRvno(int rvno) {
    this.rvno = rvno;
  }

  public int getRono() {
    return rono;
  }

  public void setRono(int rono) {
    this.rono = rono;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public String getRvdate() {
    return rvdate;
  }

  public void setRvdate(String rvdate) {
    this.rvdate = rvdate;
  }

  public String getRvgood() {
    return rvgood;
  }

  public void setRvgood(String rvgood) {
    this.rvgood = rvgood;
  }

  public int getRvcont() {
    return rvcont;
  }

  public void setRvcont(int rvcont) {
    this.rvcont = rvcont;
  }

  public String getRvup() {
    return rvup;
  }

  public void setRvup(String rvup) {
    this.rvup = rvup;
  }

  public String getRvdown() {
    return rvdown;
  }

  public void setRvdown(String rvdown) {
    this.rvdown = rvdown;
  }

  public int getRvpw() {
    return rvpw;
  }

  public void setRvpw(int rvpw) {
    this.rvpw = rvpw;
  }
  
  
}
