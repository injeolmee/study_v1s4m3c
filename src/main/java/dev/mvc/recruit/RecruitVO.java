package dev.mvc.recruit;

public class RecruitVO {
  
  /**
   *  ������ȣ
   */
  private int recruitno;
  /**
   * ���� ����
   *   �¶� 'Y' ���� 'N' ��Ȯ�� 'U'
   */
  private String confirm;
  /**
   *  ���� 
   *   ������ ���͵�׷� �����ϰ� �Ǹ�
   *   ��������� L �� �Է�
   *   
   *   ȸ������ ��û������ U �������� �Է�
   *   
   *   ������ �¶��ϸ� ȸ�� U -> T ���� ����
   */
  private String std_auth;
  /**
   *  ���͵𸮽�Ʈ ��ȣ
   *  FOREING KEY
   */
  private int stdlist_no;
  /**
   * ȸ����ȣ
   * FOREIGN KEY
   */
  private int memberno;
  
  public RecruitVO() {
 
  }

  public int getRecruitno() {
    return recruitno;
  }

  public void setRecruitno(int recruitno) {
    this.recruitno = recruitno;
  }

  public String getConfirm() {
    return confirm;
  }

  public void setConfirm(String confirm) {
    this.confirm = confirm;
  }

  public String getStd_auth() {
    return std_auth;
  }

  public void setStd_auth(String std_auth) {
    this.std_auth = std_auth;
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
