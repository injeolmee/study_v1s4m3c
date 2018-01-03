package dev.mvc.VO;

public class StudylistVO {
/*  CREATE TABLE studylist(
      stdlist_no                        NUMBER(10)     NOT NULL,
      stdlist_title                     VARCHAR2(100)    NOT NULL,
      stdlist_email                     VARCHAR2(50)     NOT NULL,
      stdlist_topic                     VARCHAR2(50)     NOT NULL,
      stdlist_time                      VARCHAR2(50)     NOT NULL,
      stdlist_area                      VARCHAR2(50)     NOT NULL,
      stdlist_dow                       VARCHAR2(50)     NOT NULL,
      stdlist_tot_num                   NUMBER(7)    DEFAULT 4     NOT NULL,
      stdlist_curr_num                  NUMBER(7)    DEFAULT 0     NOT NULL,
      stdlist_content                   VARCHAR2(4000)     NOT NULL,
      stdlist_cnt                       NUMBER(10)     DEFAULT 0     NOT NULL,
      stdlist_recom_cnt                 NUMBER(10)     DEFAULT 0     NOT NULL,
      stdlist_date                      DATE     NOT NULL,
      memberno                          NUMBER(10)     NOT NULL,
      cateno                            NUMBER(10)     NULL 
  );

  COMMENT ON TABLE studylist is '스터디목록';
  COMMENT ON COLUMN studylist.stdlist_no is '스터디번호';
  COMMENT ON COLUMN studylist.stdlist_title is '스터디 제목';
  COMMENT ON COLUMN studylist.stdlist_email is '이메일';
  COMMENT ON COLUMN studylist.stdlist_topic is '분야';
  COMMENT ON COLUMN studylist.stdlist_time is '시간';
  COMMENT ON COLUMN studylist.stdlist_area is '지역';
  COMMENT ON COLUMN studylist.stdlist_dow is '요일';
  COMMENT ON COLUMN studylist.stdlist_tot_num is '구성원수';
  COMMENT ON COLUMN studylist.stdlist_curr_num is '현재원수';
  COMMENT ON COLUMN studylist.stdlist_content is '내용(주제)요약';
  COMMENT ON COLUMN studylist.stdlist_cnt is '조회수';
  COMMENT ON COLUMN studylist.stdlist_recom_cnt is '추천수';
  COMMENT ON COLUMN studylist.stdlist_date is '등록일';
  COMMENT ON COLUMN studylist.memberno is '회원번호';
  COMMENT ON COLUMN studylist.cateno is '카테고리번호';*/
  
  private int stdlist_no;       
  private String stdlist_title;    
  private String stdlist_email;    
  private String stdlist_topic;    
  private String stdlist_time;     
  private String stdlist_area;     
  private String stdlist_dow;      
  private int stdlist_tot_num;  
  private int stdlist_curr_num; 
  private String stdlist_content;  
  private int stdlist_cnt;      
  private int stdlist_recom_cnt;
  private String stdlist_date;     
  private int memberno;         
  private int cateno;
  
  public StudylistVO() {
    // TODO Auto-generated constructor stub
  }
  
  public StudylistVO(int stdlist_no, String stdlist_title, String stdlist_email, String stdlist_topic,
      String stdlist_time, String stdlist_area, String stdlist_dow, int stdlist_tot_num, int stdlist_curr_num,
      String stdlist_content, int stdlist_cnt, int stdlist_recom_cnt, String stdlist_date, int memberno, int cateno) {
    super();
    this.stdlist_no = stdlist_no;
    this.stdlist_title = stdlist_title;
    this.stdlist_email = stdlist_email;
    this.stdlist_topic = stdlist_topic;
    this.stdlist_time = stdlist_time;
    this.stdlist_area = stdlist_area;
    this.stdlist_dow = stdlist_dow;
    this.stdlist_tot_num = stdlist_tot_num;
    this.stdlist_curr_num = stdlist_curr_num;
    this.stdlist_content = stdlist_content;
    this.stdlist_cnt = stdlist_cnt;
    this.stdlist_recom_cnt = stdlist_recom_cnt;
    this.stdlist_date = stdlist_date;
    this.memberno = memberno;
    this.cateno = cateno;
  }
  
  
  public int getStdlist_no() {
    return stdlist_no;
  }
  public void setStdlist_no(int stdlist_no) {
    this.stdlist_no = stdlist_no;
  }
  public String getStdlist_title() {
    return stdlist_title;
  }
  public void setStdlist_title(String stdlist_title) {
    this.stdlist_title = stdlist_title;
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
  public int getStdlist_recom_cnt() {
    return stdlist_recom_cnt;
  }
  public void setStdlist_recom_cnt(int stdlist_recom_cnt) {
    this.stdlist_recom_cnt = stdlist_recom_cnt;
  }
  public String getStdlist_date() {
    return stdlist_date;
  }
  public void setStdlist_date(String stdlist_date) {
    this.stdlist_date = stdlist_date;
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
  
  
}
