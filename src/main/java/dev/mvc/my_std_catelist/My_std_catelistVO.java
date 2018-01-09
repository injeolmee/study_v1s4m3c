package dev.mvc.my_std_catelist;

public class My_std_catelistVO {
  /*CREATE TABLE my_std_catelist(
      mylistno                          NUMBER(10)     NOT NULL    PRIMARY KEY,
      stdlist_no                        NUMBER(10)     NULL ,
      cateno                            NUMBER(10)     NULL ,
    FOREIGN KEY (stdlist_no) REFERENCES studylist (stdlist_no),
    FOREIGN KEY (cateno) REFERENCES category (cateno)
  );*/
  private int mylistno;
  private int stdlist_no;
  private int cateno;
  
  public My_std_catelistVO() {
    // TODO Auto-generated constructor stub
  }
  
  public My_std_catelistVO(int mylistno, int stdlist_no, int cateno) {
    super();
    this.mylistno = mylistno;
    this.stdlist_no = stdlist_no;
    this.cateno = cateno;
  }

  public int getMylistno() {
    return mylistno;
  }
  public void setMylistno(int mylistno) {
    this.mylistno = mylistno;
  }
  public int getStdlist_no() {
    return stdlist_no;
  }
  public void setStdlist_no(int stdlist_no) {
    this.stdlist_no = stdlist_no;
  }
  public int getCateno() {
    return cateno;
  }
  public void setCateno(int cateno) {
    this.cateno = cateno;
  }
  
}
