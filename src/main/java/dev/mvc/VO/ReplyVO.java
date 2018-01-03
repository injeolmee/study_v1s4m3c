package dev.mvc.VO;

public class ReplyVO {
  /**********************************/
  /* Table Name: 댓글 게시판 */
  /**********************************/
/*  CREATE TABLE reply(
      replyno                           NUMBER(7)    NOT NULL,
      replycontent                      VARCHAR2(200)    NOT NULL,
      replyname                         VARCHAR2(50)     NOT NULL,
      replydate                         DATE     NOT NULL,
      replypasswd                       VARCHAR2(50)     NOT NULL,
      memberno                          NUMBER(10)     NULL ,
      sharedno                          NUMBER(7)    NULL ,
      freeno                            NUMBER(7)    NULL ,
      saleno                            NUMBER(7)    NULL ,
      adminno                           NUMBER(10)     NULL 
  );

  COMMENT ON TABLE reply is '댓글 게시판';
  COMMENT ON COLUMN reply.replyno is '댓글 번호';
  COMMENT ON COLUMN reply.replycontent is '댓글 내용';
  COMMENT ON COLUMN reply.replyname is '댓글 작성자';
  COMMENT ON COLUMN reply.replydate is '댓글 등록일';
  COMMENT ON COLUMN reply.replypasswd is '댓글 패스워드';
  COMMENT ON COLUMN reply.memberno is '회원번호';
  COMMENT ON COLUMN reply.sharedno is '자료실 번호';
  COMMENT ON COLUMN reply.freeno is '자유게시판 번호';
  COMMENT ON COLUMN reply.saleno is '거래게시판 번호';
  COMMENT ON COLUMN reply.adminno is '관리자번호';*/
  
  private int replyno;     
  private String replycontent;
  private String replyname;   
  private String replydate;   
  private String replypasswd; 
  private int memberno;    
  private int sharedno;    
  private int freeno;      
  private int saleno;      
  private int adminno;
  
  public ReplyVO() {
    // TODO Auto-generated constructor stub
  }

  public ReplyVO(int replyno, String replycontent, String replyname, String replydate, String replypasswd, int memberno,
      int sharedno, int freeno, int saleno, int adminno) {
    super();
    this.replyno = replyno;
    this.replycontent = replycontent;
    this.replyname = replyname;
    this.replydate = replydate;
    this.replypasswd = replypasswd;
    this.memberno = memberno;
    this.sharedno = sharedno;
    this.freeno = freeno;
    this.saleno = saleno;
    this.adminno = adminno;
  }

  public int getReplyno() {
    return replyno;
  }

  public void setReplyno(int replyno) {
    this.replyno = replyno;
  }

  public String getReplycontent() {
    return replycontent;
  }

  public void setReplycontent(String replycontent) {
    this.replycontent = replycontent;
  }

  public String getReplyname() {
    return replyname;
  }

  public void setReplyname(String replyname) {
    this.replyname = replyname;
  }

  public String getReplydate() {
    return replydate;
  }

  public void setReplydate(String replydate) {
    this.replydate = replydate;
  }

  public String getReplypasswd() {
    return replypasswd;
  }

  public void setReplypasswd(String replypasswd) {
    this.replypasswd = replypasswd;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public int getSharedno() {
    return sharedno;
  }

  public void setSharedno(int sharedno) {
    this.sharedno = sharedno;
  }

  public int getFreeno() {
    return freeno;
  }

  public void setFreeno(int freeno) {
    this.freeno = freeno;
  }

  public int getSaleno() {
    return saleno;
  }

  public void setSaleno(int saleno) {
    this.saleno = saleno;
  }

  public int getAdminno() {
    return adminno;
  }

  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  
  
}
