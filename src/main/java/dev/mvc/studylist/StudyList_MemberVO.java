package dev.mvc.studylist;

public class StudyList_MemberVO {

  /**
   *  스터디그룹의 고유 번호
   */
  private int stdlist_no;
  /**
   *  스터디 그룹장 이메일 주소
   *  stdlist_email = email1(아이디)+ email1(주소)
   */
  private String stdlist_email;
  /**
   *  스터디그룹의 분야
   */
  private String stdlist_topic;
  /**
   *  스터디 그룹 시간
   */
  private String stdlist_time;
  /**
   * 스터디 그룹모집 제목
   */
  private String stdlist_title;
  /**
   *  스터디그룹 지역
   *  stdlist_area = area + selected_area;
   */
  private String stdlist_area;
  /**
   * 스터디그룹 요일
   * 체크박스를 통해 넘어온 요일을 조합해서 만듬
   *  stdlist_dow = dow1 + dow2 + dow3 + dow4 + dow5 + dow6 + dow7
   */
  private String stdlist_dow;
  /**
   *  스터디그룹 등록일자
   */
  private String stdlist_date;
  /**
   *  스터디 그룹 모집 총 인원수
   *  stdlist_curr_num = 1;
   *  if(stdlist_curr_num=stdlist_tot_num){
   *    모집 마감
   *    stdlist_curr_num ++;
   *  } 
   */
  private int stdlist_tot_num;
  /**
   *  스터디 그룹 현재 인원수
   *   초기값 : 팀장 포함 1부터 시작
   */
  private int stdlist_curr_num;
  /**
   *  스터디 그룹 모집 내용
   *  커리큘럼, 진행방식 등.
   */
  private String stdlist_content;
  /**
   *  스터디 모집 글 조회 수
   */
  private int stdlist_cnt;
  /**
   *  스터디 추천 수
   */
  private int stdlist_goodcnt;
  
  ///////////////////////////////////////////////////////////////////////////////////
  
  /**
   *  회원번호
   */
  private int memberno;
  /**
   *  회원 이메일
   */
  private String mememail;
  /**
   * 회원 이름
   */
  private String memname;
  /**
   *  회원 성별
   */
  private String memgender;
  /**
   * 회원 핸드폰번호
   */
  private String memphone;
  /**
   *  회원 sns
   */
  private String memsns;
  /**
   *  회원 사진
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
