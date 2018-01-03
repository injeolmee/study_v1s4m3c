package dev.mvc.studylist;

public class StudyList_MemberVO {

  /**
   *  ���͵�׷��� ���� ��ȣ
   */
  private int stdlist_no;
  /**
   *  ���͵� �׷��� �̸��� �ּ�
   *  stdlist_email = email1(���̵�)+ email1(�ּ�)
   */
  private String stdlist_email;
  /**
   *  ���͵�׷��� �о�
   */
  private String stdlist_topic;
  /**
   *  ���͵� �׷� �ð�
   */
  private String stdlist_time;
  /**
   * ���͵� �׷���� ����
   */
  private String stdlist_title;
  /**
   *  ���͵�׷� ����
   *  stdlist_area = area + selected_area;
   */
  private String stdlist_area;
  /**
   * ���͵�׷� ����
   * üũ�ڽ��� ���� �Ѿ�� ������ �����ؼ� ����
   *  stdlist_dow = dow1 + dow2 + dow3 + dow4 + dow5 + dow6 + dow7
   */
  private String stdlist_dow;
  /**
   *  ���͵�׷� �������
   */
  private String stdlist_date;
  /**
   *  ���͵� �׷� ���� �� �ο���
   *  stdlist_curr_num = 1;
   *  if(stdlist_curr_num=stdlist_tot_num){
   *    ���� ����
   *    stdlist_curr_num ++;
   *  } 
   */
  private int stdlist_tot_num;
  /**
   *  ���͵� �׷� ���� �ο���
   *   �ʱⰪ : ���� ���� 1���� ����
   */
  private int stdlist_curr_num;
  /**
   *  ���͵� �׷� ���� ����
   *  Ŀ��ŧ��, ������ ��.
   */
  private String stdlist_content;
  /**
   *  ���͵� ���� �� ��ȸ ��
   */
  private int stdlist_cnt;
  /**
   *  ���͵� ��õ ��
   */
  private int stdlist_goodcnt;
  
  ///////////////////////////////////////////////////////////////////////////////////
  
  /**
   *  ȸ����ȣ
   */
  private int memberno;
  /**
   *  ȸ�� �̸���
   */
  private String mememail;
  /**
   * ȸ�� �̸�
   */
  private String memname;
  /**
   *  ȸ�� ����
   */
  private String memgender;
  /**
   * ȸ�� �ڵ�����ȣ
   */
  private String memphone;
  /**
   *  ȸ�� sns
   */
  private String memsns;
  /**
   *  ȸ�� ����
   */
  private String memphoto_t;
  
  
  public StudyList_MemberVO(){
    
  }
  

  public int getStdlist_no() {
    return stdlist_no;
  }

  public void setStdlist_no(int stdlist_no) {
    this.stdlist_no = stdlist_no;
  }

  public String getStdlist_email() {
    return stdlist_email;
  }

  public void setStdlist_email(String stdlist_email) {
    this.stdlist_email = stdlist_email;
  }

  public String getStdlist_topic() {
    return stdlist_topic;
  }

  public void setStdlist_topic(String stdlist_topic) {
    this.stdlist_topic = stdlist_topic;
  }

  public String getStdlist_time() {
    return stdlist_time;
  }

  public void setStdlist_time(String stdlist_time) {
    this.stdlist_time = stdlist_time;
  }

  public String getStdlist_title() {
    return stdlist_title;
  }

  public void setStdlist_title(String stdlist_title) {
    this.stdlist_title = stdlist_title;
  }

  public String getStdlist_area() {
    return stdlist_area;
  }

  public void setStdlist_area(String stdlist_area) {
    this.stdlist_area = stdlist_area;
  }

  public String getStdlist_dow() {
    return stdlist_dow;
  }

  public void setStdlist_dow(String stdlist_dow) {
    this.stdlist_dow = stdlist_dow;
  }

  public String getStdlist_date() {
    return stdlist_date;
  }

  public void setStdlist_date(String stdlist_date) {
    this.stdlist_date = stdlist_date;
  }

  public int getStdlist_tot_num() {
    return stdlist_tot_num;
  }

  public void setStdlist_tot_num(int stdlist_tot_num) {
    this.stdlist_tot_num = stdlist_tot_num;
  }

  public int getStdlist_curr_num() {
    return stdlist_curr_num;
  }

  public void setStdlist_curr_num(int stdlist_curr_num) {
    this.stdlist_curr_num = stdlist_curr_num;
  }

  public String getStdlist_content() {
    return stdlist_content;
  }

  public void setStdlist_content(String stdlist_content) {
    this.stdlist_content = stdlist_content;
  }

  public int getStdlist_cnt() {
    return stdlist_cnt;
  }

  public void setStdlist_cnt(int stdlist_cnt) {
    this.stdlist_cnt = stdlist_cnt;
  }

  public int getStdlist_goodcnt() {
    return stdlist_goodcnt;
  }

  public void setStdlist_goodcnt(int stdlist_goodcnt) {
    this.stdlist_goodcnt = stdlist_goodcnt;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public String getMememail() {
    return mememail;
  }

  public void setMememail(String mememail) {
    this.mememail = mememail;
  }

  public String getMemname() {
    return memname;
  }

  public void setMemname(String memname) {
    this.memname = memname;
  }

  public String getMemgender() {
    return memgender;
  }

  public void setMemgender(String memgender) {
    this.memgender = memgender;
  }

  public String getMemphone() {
    return memphone;
  }

  public void setMemphone(String memphone) {
    this.memphone = memphone;
  }

  public String getMemsns() {
    return memsns;
  }

  public void setMemsns(String memsns) {
    this.memsns = memsns;
  }

  public String getMemphoto_t() {
    return memphoto_t;
  }

  public void setMemphoto_t(String memphoto_t) {
    this.memphoto_t = memphoto_t;
  }
  
  
}
