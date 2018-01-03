package dev.mvc.message;

/*CREATE TABLE msgsend(
    msend_no                          NUMBER(10)     NOT NULL    PRIMARY KEY,
    memberno_send                     NUMBER(10)     NOT NULL,
    msg_no                            NUMBER(10)     NULL ,
  FOREIGN KEY (memberno_send) REFERENCES member (memberno),
  FOREIGN KEY (msg_no) REFERENCES message (msg_no)
);*/

public class MsgsendVO {
  private int msend_no;
  private int memberno_send;
  private int msg_no;
  
  public MsgsendVO() {
    // TODO Auto-generated constructor stub
  }

  public int getMsend_no() {
    return msend_no;
  }

  public void setMsend_no(int msend_no) {
    this.msend_no = msend_no;
  }

  public int getMemberno_send() {
    return memberno_send;
  }

  public void setMemberno_send(int memberno_send) {
    this.memberno_send = memberno_send;
  }

  public int getMsg_no() {
    return msg_no;
  }

  public void setMsg_no(int msg_no) {
    this.msg_no = msg_no;
  }
  
  
}
