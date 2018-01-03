package dev.mvc.VO;

public class ReplyVO {
  /**********************************/
  /* Table Name: ��� �Խ��� */
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

  COMMENT ON TABLE reply is '��� �Խ���';
  COMMENT ON COLUMN reply.replyno is '��� ��ȣ';
  COMMENT ON COLUMN reply.replycontent is '��� ����';
  COMMENT ON COLUMN reply.replyname is '��� �ۼ���';
  COMMENT ON COLUMN reply.replydate is '��� �����';
  COMMENT ON COLUMN reply.replypasswd is '��� �н�����';
  COMMENT ON COLUMN reply.memberno is 'ȸ����ȣ';
  COMMENT ON COLUMN reply.sharedno is '�ڷ�� ��ȣ';
  COMMENT ON COLUMN reply.freeno is '�����Խ��� ��ȣ';
  COMMENT ON COLUMN reply.saleno is '�ŷ��Խ��� ��ȣ';
  COMMENT ON COLUMN reply.adminno is '�����ڹ�ȣ';*/
  
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
