package dev.mvc.VO;

public class InforeplyVO {
  /**********************************/
  /* Table Name: 정보 목록 댓글 */
  /**********************************/
/*  CREATE TABLE Inforeply(
      repno                             NUMBER(10)     NOT NULL,
      repcont                           VARCHAR2(200)    NOT NULL,
      repdate                           DATE     NOT NULL,
      repname                           VARCHAR2(30)     NOT NULL,
      memberno                          NUMBER(10)     NULL ,
      qnano                             NUMBER(10)     NULL ,
      infono_e                          NUMBER(10)     NULL ,
      infono_j                          NUMBER(10)     NULL 
  );

  COMMENT ON TABLE Inforeply is '정보 목록 댓글';
  COMMENT ON COLUMN Inforeply.repno is '댓글 번호';
  COMMENT ON COLUMN Inforeply.repcont is '댓글 내용';
  COMMENT ON COLUMN Inforeply.repdate is '댓글 등록일';
  COMMENT ON COLUMN Inforeply.repname is '댓글 작성자';
  COMMENT ON COLUMN Inforeply.memberno is '회원번호';
  COMMENT ON COLUMN Inforeply.qnano is '게시판 번호';
  COMMENT ON COLUMN Inforeply.infono_e is '공모전 정보 등록 번호';
  COMMENT ON COLUMN Inforeply.infono_j is '취업 정보 등록 번호';*/
  
  private int repno;  
  private String repcont; 
  private String repdate; 
  private String repname; 
  private int memberno;
  private int qnano;   
  private int infono_e;
  private int infono_j;
  
  public InforeplyVO() {
    // TODO Auto-generated constructor stub
  }

  public InforeplyVO(int repno, String repcont, String repdate, String repname, int memberno, int qnano, int infono_e,
      int infono_j) {
    super();
    this.repno = repno;
    this.repcont = repcont;
    this.repdate = repdate;
    this.repname = repname;
    this.memberno = memberno;
    this.qnano = qnano;
    this.infono_e = infono_e;
    this.infono_j = infono_j;
  }

  public int getRepno() {
    return repno;
  }

  public void setRepno(int repno) {
    this.repno = repno;
  }

  public String getRepcont() {
    return repcont;
  }

  public void setRepcont(String repcont) {
    this.repcont = repcont;
  }

  public String getRepdate() {
    return repdate;
  }

  public void setRepdate(String repdate) {
    this.repdate = repdate;
  }

  public String getRepname() {
    return repname;
  }

  public void setRepname(String repname) {
    this.repname = repname;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public int getQnano() {
    return qnano;
  }

  public void setQnano(int qnano) {
    this.qnano = qnano;
  }

  public int getInfono_e() {
    return infono_e;
  }

  public void setInfono_e(int infono_e) {
    this.infono_e = infono_e;
  }

  public int getInfono_j() {
    return infono_j;
  }

  public void setInfono_j(int infono_j) {
    this.infono_j = infono_j;
  }
  
  
}
