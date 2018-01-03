package dev.mvc.message;

/*CREATE TABLE msgrecv(
    mrecv_no                          NUMBER(10)     NOT NULL    PRIMARY KEY,
    memberno_recv                     NUMBER(10)     NOT NULL,
    msg_no                            NUMBER(10)     NULL ,
  FOREIGN KEY (memberno_recv) REFERENCES member (memberno),
  FOREIGN KEY (msg_no) REFERENCES message (msg_no)
);
*/

public class MsgrecvVO {
  private int mrecv_no;       // 수신 번호
  private int memberno_recv;  // 수신자 회원 번호
  private int msg_no;         // 
  
  public MsgrecvVO() {
    // TODO Auto-generated constructor stub
  }

  public int getMrecv_no() {
    return mrecv_no; 
  }

  public void setMrecv_no(int mrecv_no) {
    this.mrecv_no = mrecv_no;
  }

  public int getMemberno_recv() {
    return memberno_recv;
  }

  public void setMemberno_recv(int memberno_recv) {
    this.memberno_recv = memberno_recv;
  }

  public int getMsg_no() {
    return msg_no;
  }

  public void setMsg_no(int msg_no) {
    this.msg_no = msg_no;
  }
  
}
