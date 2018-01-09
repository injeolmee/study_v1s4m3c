package dev.mvc.mystudy;
// 현재 회원이 신청한 스터디 정보와 그 상태 정보
public class My_apply_listVO {
  private int recruitno;
  private String stdlist_title;
  private String stdlist_topic;
  private String stdlist_area;
  private String stdlist_dow;
  private int stdlist_curr_num;
  private int stdlist_tot_num;
  private String confirm;
  
  public My_apply_listVO() {
  }
  
  public int getRecruitno() {
    return recruitno;
  }

  public void setRecruitno(int recruitno) {
    this.recruitno = recruitno;
  }

  public String getStdlist_title() {
    return stdlist_title;
  }

  public void setStdlist_title(String stdlist_title) {
    this.stdlist_title = stdlist_title;
  }

  public String getStdlist_topic() {
    return stdlist_topic;
  }

  public void setStdlist_topic(String stdlist_topic) {
    this.stdlist_topic = stdlist_topic;
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

  public int getStdlist_curr_num() {
    return stdlist_curr_num;
  }

  public void setStdlist_curr_num(int stdlist_curr_num) {
    this.stdlist_curr_num = stdlist_curr_num;
  }

  public int getStdlist_tot_num() {
    return stdlist_tot_num;
  }

  public void setStdlist_tot_num(int stdlist_tot_num) {
    this.stdlist_tot_num = stdlist_tot_num;
  }

  public String getConfirm() {
    return confirm;
  }

  public void setConfirm(String confirm) {
    this.confirm = confirm;
  }
  
  
}
