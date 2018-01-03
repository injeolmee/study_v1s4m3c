package dev.mvc.std_recom;

public class Std_RecomVO {

  /**
   * 추천테이블 기본키 번호
   */
  private int std_recom_no;
  /**
   * 좋아요 여부
   */
  private int good_ch;
  /**
   * 스터디그룹번호
   */
  private int stdlist_no;
  /**
   * 회원 번호
   */
  private int memberno;

  public Std_RecomVO() {

  }

  public int getStd_recom_no() {
    return std_recom_no;
  }

  public void setStd_recom_no(int std_recom_no) {
    this.std_recom_no = std_recom_no;
  }

  public int getGood_ch() {
    return good_ch;
  }

  public void setGood_ch(int good_ch) {
    this.good_ch = good_ch;
  }

  public int getStdlist_no() {
    return stdlist_no;
  }

  public void setStdlist_no(int stdlist_no) {
    this.stdlist_no = stdlist_no;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

}
