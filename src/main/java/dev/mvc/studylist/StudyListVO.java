package dev.mvc.studylist;

public class StudyListVO {

  /**
   * 스터디그룹의 고유 번호
   */
  private int stdlist_no;
  /**
   * 스터디 그룹장 이메일 주소 stdlist_email = email1(아이디)+ email1(주소)
   */
  private String stdlist_email;
  /**
   * 스터디그룹의 분야
   */
  private String stdlist_topic;
  /**
   * select를 이용한 검색 변수 주제
   */
  private String topic;
  /**
   * select를 이용한 검색 변수 주제에 대한 세부카테고리
   */
  private String selected_topic;
  /**
   * 스터디 그룹 시간
   */
  private String stdlist_time;
  /**
   * 스터디 그룹모집 제목
   */
  private String stdlist_title;
  /**
   * 스터디그룹 지역 stdlist_area = area + selected_area;
   */
  private String stdlist_area;
  /**
   * 스터디그룹 요일 체크박스를 통해 넘어온 요일을 조합해서 만듬 stdlist_dow = dow1 + dow2 + dow3 + dow4 +
   * dow5 + dow6 + dow7
   */
  private String stdlist_dow;
  /**
   * 스터디그룹 등록일자
   */
  private String stdlist_date;
  /**
   * 스터디 그룹 모집 총 인원수 stdlist_curr_num = 1; if(stdlist_curr_num=stdlist_tot_num){
   * 모집 마감 stdlist_curr_num ++; }
   */
  private int stdlist_tot_num;
  /**
   * 스터디 그룹 현재 인원수 초기값 : 팀장 포함 1부터 시작
   */
  private int stdlist_curr_num;
  /**
   * 스터디 그룹 모집 내용 커리큘럼, 진행방식 등.
   */
  private String stdlist_content;
  /**
   * 스터디 모집 글 조회 수
   */
  private int stdlist_cnt;
  /**
   * 스터디 추천 수
   */
  private int stdlist_goodcnt;
  /**
   * 검색어
   */
  private String word = "";
  /**
   * 검색 select option
   */
  private String search = "";
  /**
   * 현재 페이지
   */
  private int nowPage = 1;
  /**
   * foreign key member(회원) 테이블의 회원 고유번호
   */
  private int memberno;

  /**
   * email의 아이디
   */
  private String email1;
  /**
   * email 주소
   */
  private String email2;
  /**
   * 지역 선택값
   */
  private String area;
  /**
   * 선택된 지역의 하위 카테고리
   */
  private String selected_area;

  /**
   * 요일값중 '월'
   */
  private String dow1;
  /**
   * 요일값중 '화'
   */
  private String dow2;
  /**
   * 요일값중 '수'
   */
  private String dow3;
  /**
   * 요일값중 '목'
   */
  private String dow4;
  /**
   * 요일값중 '금'
   */
  private String dow5;
  /**
   * 요일값중 '토'
   */
  private String dow6;
  /**
   * 요일값중 '일'
   */
  private String dow7;

  public StudyListVO() {

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

  /**
   * email1 과 email2 를 통해 받아온 값을 조합해 저장
   * 
   * @param stdlist_email
   */
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

  /**
   * 지역과 지역의 하위카테고리를 조합해 저장
   * 
   * @param stdlist_area
   */
  public void setStdlist_area(String stdlist_area) {
    this.stdlist_area = stdlist_area;
  }

  public String getStdlist_dow() {
    return stdlist_dow;
  }

  /**
   * dow1 부터 dow 7 까지 체크박스 체크를 통해 받아온 값을 조합해서 저장
   * 
   * @param stdlist_dow
   */
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

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public String getEmail1() {
    return email1;
  }

  public void setEmail1(String email1) {
    this.email1 = email1;
  }

  public String getEmail2() {
    return email2;
  }

  public void setEmail2(String email2) {
    this.email2 = email2;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getSelected_area() {
    return selected_area;
  }

  public void setSelected_area(String selected_area) {
    this.selected_area = selected_area;
  }

  public String getDow1() {
    return dow1;
  }

  public void setDow1(String dow1) {
    this.dow1 = dow1;
  }

  public String getDow2() {
    return dow2;
  }

  public void setDow2(String dow2) {
    this.dow2 = dow2;
  }

  public String getDow3() {
    return dow3;
  }

  public void setDow3(String dow3) {
    this.dow3 = dow3;
  }

  public String getDow4() {
    return dow4;
  }

  public void setDow4(String dow4) {
    this.dow4 = dow4;
  }

  public String getDow5() {
    return dow5;
  }

  public void setDow5(String dow5) {
    this.dow5 = dow5;
  }

  public String getDow6() {
    return dow6;
  }

  public void setDow6(String dow6) {
    this.dow6 = dow6;
  }

  public String getDow7() {
    return dow7;
  }

  public void setDow7(String dow7) {
    this.dow7 = dow7;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getSelected_topic() {
    return selected_topic;
  }

  public void setSelected_topic(String selected_topic) {
    this.selected_topic = selected_topic;
  }

  public String getSearch() {
    return search;
  }

  public void setSearch(String search) {
    this.search = search;
  }

  public int getNowPage() {
    return nowPage;
  }

  public void setNowPage(int nowPage) {
    this.nowPage = nowPage;
  }

  public int getStdlist_goodcnt() {
    return stdlist_goodcnt;
  }

  public void setStdlist_goodcnt(int stdlist_goodcnt) {
    this.stdlist_goodcnt = stdlist_goodcnt;
  }

}
