package dev.mvc.message;

/* ���̺� ����
 * CREATE TABLE message(
    msg_no                            NUMBER(10)     NOT NULL    PRIMARY KEY,
    memberno_send                     NUMBER(10)     NULL ,
    memberno_recv                     NUMBER(10)     NULL ,
    msg_title                         VARCHAR2(100)    NULL ,
    msg_content                       VARCHAR2(500)    NULL ,
    msg_confirm                       VARCHAR2(10)     DEFAULT 'N'     NULL ,
    msg_rev_date                      DATE     NULL ,
    msg_date                          DATE     NULL ,
  FOREIGN KEY (memberno_send) REFERENCES member (memberno),
  FOREIGN KEY (memberno_recv) REFERENCES member (memberno)
);*/

public class MessageVO {
  private int msg_no;              // ���� ��ȣ
  private int memberno_send;       // ���� �߽��� ȸ�� ��ȣ
  private int memberno_recv;       // ���� ������ ȸ�� ��ȣ
  private String msg_title;        // ���� ����
  private String msg_content;      // ���� ����
  private String msg_confirm;      // ���ſ���
  private String msg_rev_date;     // ���ų�¥
  private String msg_date;         // ������ ��¥.
  
  /***********************************************
   * ȸ���� ���� ������ �˻��ϱ� ���� ����
   */
  private int member_count;
  
  /**
   * ȸ���� ���̵� �����ϱ� ���� ���� (DB���� x)
   */
  private String memid;
  
  /**
   * ȸ���� �̸��� �����ϱ� ���� ���� (DB���� x)
   */
  private String memname;
  
  private int mrecv_no;
  
  // ������ ȸ���� ���̵�� �̸�
  private String sender_id;
  private String sender_name;
  
  // �޴� ȸ���� ���̵�� �̸� 
  private String receiver_id;
  private String receiver_name;
  
  private int search_count;
  
  /************************************************/
  
  
  public MessageVO() {
    // TODO Auto-generated constructor stub
  }

  public int getSearch_count() {
    return search_count;
  }

  public void setSearch_count(int search_count) {
    this.search_count = search_count;
  }



  public String getSender_id() {
    return sender_id;
  }

  public void setSender_id(String sender_id) {
    this.sender_id = sender_id;
  }

  public String getSender_name() {
    return sender_name;
  }

  public void setSender_name(String sender_name) {
    this.sender_name = sender_name;
  }

  public String getReceiver_id() {
    return receiver_id;
  }

  public void setReceiver_id(String receiver_id) {
    this.receiver_id = receiver_id;
  }

  public String getReceiver_name() {
    return receiver_name;
  }

  public void setReceiver_name(String receiver_name) {
    this.receiver_name = receiver_name;
  }

  public int getMrecv_no() {
    return mrecv_no;
  }

  public void setMrecv_no(int mrecv_no) {
    this.mrecv_no = mrecv_no;
  }

  public String getMemname() {
    return memname;
  }

  public void setMemname(String memname) {
    this.memname = memname;
  }

  public String getMemid() {
    return memid;
  }

  public void setMemid(String memid) {
    this.memid = memid;
  }

  public int getMember_count() {
    return member_count;
  }

  public void setMember_count(int member_count) {
    this.member_count = member_count;
  }
  
  public int getMemberno_send() {
    return memberno_send;
  }
  public void setMemberno_send(int memberno_send) {
    this.memberno_send = memberno_send;
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

  public String getMsg_title() {
    return msg_title;
  }

  public void setMsg_title(String msg_title) {
    this.msg_title = msg_title;
  }

  public String getMsg_content() {
    return msg_content;
  }

  public void setMsg_content(String msg_content) {
    this.msg_content = msg_content;
  }

  public String getMsg_confirm() {
    return msg_confirm;
  }

  public void setMsg_confirm(String msg_confirm) {
    this.msg_confirm = msg_confirm;
  }

  public String getMsg_rev_date() {
    return msg_rev_date;
  }

  public void setMsg_rev_date(String msg_rev_date) {
    this.msg_rev_date = msg_rev_date;
  }

  public String getMsg_date() {
    return msg_date;
  }

  public void setMsg_date(String msg_date) {
    this.msg_date = msg_date;
  }

  
  
}
