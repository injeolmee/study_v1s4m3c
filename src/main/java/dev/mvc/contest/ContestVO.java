package dev.mvc.contest;

import org.springframework.web.multipart.MultipartFile;

/*
    conNo                             NUMBER(7)                    NOT NULL    PRIMARY KEY,
    conTitle                          VARCHAR2(200)                NOT NULL,
    conStart                          VARCHAR2(200)                NOT NULL,
    conEnd                            VARCHAR2(200)                NOT NULL,
    conCont                           VARCHAR2(4000)               NOT NULL,
    conUrl                            VARCHAR2(200)                NULL,
    conYou                            VARCHAR2(200)                NULL,
    conFile1                          VARCHAR2(50)                 NULL,
    conSize1                          NUMBER(10)                   NULL,
    conThumb                          VARCHAR2(100)                NULL,
    conFile2                          VARCHAR2(50)                 NULL,
    conSize2                          NUMBER(10)     DEFAULT 0     NULL,
    conFile3                          VARCHAR2(50)                 NULL,
    conSize3                          NUMBER(10)     DEFAULT 0     NULL,
    conGood                           NUMBER(7)      DEFAULT 0     NOT NULL,
    conCnt                            NUMBER(7)      DEFAULT 0     NOT NULL,
    cdate                             DATE                         NOT NULL,
    conWord                           VARCHAR2(100)                    NULL,
    adminno                           NUMBER(10)                       NULL,
    cateno                            NUMBER(10)                       NULL,
    memberno                          NUMBER(10)                       NULL,
    FOREIGN KEY (adminno) REFERENCES admin (adminno),
    FOREIGN KEY (cateno) REFERENCES category (cateno),
    FOREIGN KEY (memberno) REFERENCES member (memberno)
*/

public class ContestVO {
  /** ������ ��ȣ */
  private int conNo;
  /** ������ ��ȣ */
  private int adminno;
  /** ī�װ� ��ȣ */
  private int cateno;
  /** ȸ�� ��ȣ */
  private int memberno;
  /** ������ ������ �̸� */
  private String conHost="";
  /** ������ ���� */
  private String conTitle="";
  /** ������ ������ */
  private String conStart = "";
  /** ������ ������ */
  private String conEnd = "";
  /** ������ ���۱��� ���� �ϼ� */
  private long conRemain_s;
  /** ������ ������� ���� �ϼ� */
  private long conRemain_e;
  /** ������ ���� */
  private String conCont;
  /** ������ ���� URL */
  private String conUrl = "";
  /** ������ ���� Youtube */
  private String conYou = "";
  /** ������ ��� �̹��� */
  private String conFile1 = "";
  /** ������ ��� �̹��� ������ */
  private long conSize1 = 0;
  /** ������ ��� ����� */
  private String conThumb = "";
  /** ������ ���� �̹��� */
  private String conFile2 = "";
  /** ������ ���� �̹��� ������ */
  private long conSize2 = 0;
  /** ������ ���� �̹��� �������ϸ� */
  private String conFstor2 = "";
  /** ������ ��� ���� */
  private String conFile3 = "";
  /** ������ ��� ���ϻ����� */
  private long conSize3 = 0;
  /** ������ ��� �������ϸ� */
  private String conFstor3 = "";
  /** ������ ��õ�� */
  private int conGood = 0;
  /** ������ ��ȸ�� */
  private int conCnt = 0;
  /** ������ ����� */
  private String cdate;
  /** ������ �˻��� */
  private String conWord = "";
  /** ���� ������ */
  private int nowPage = 1;
  
  public ContestVO() {
    
  }
  
  /** 
  Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
  */  
  private MultipartFile file1MF;
  private MultipartFile file2MF;
  private MultipartFile file3MF;
  
  /** size1�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String size1Label;
  private String size2Label;
  private String size3Label;

  public int getConNo() {
    return conNo;
  }

  public void setConNo(int conNo) {
    this.conNo = conNo;
  }

  public int getAdminno() {
    return adminno;
  }

  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }

  public int getCateno() {
    return cateno;
  }

  public void setCateno(int cateno) {
    this.cateno = cateno;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  
  public String getConHost() {
    return conHost;
  }

  public void setConHost(String conHost) {
    this.conHost = conHost;
  }

  public String getConTitle() {
    return conTitle;
  }

  public void setConTitle(String conTitle) {
    this.conTitle = conTitle;
  }

  public String getConStart() {
    return conStart;
  }

  public void setConStart(String conStart) {
    this.conStart = conStart;
  }

  public long getConRemain_s() {
    return conRemain_s;
  }

  public void setConRemain_s(long conRemain_s) {
    this.conRemain_s = conRemain_s;
  }
  
  public long getConRemain_e() {
    return conRemain_e;
  }
  
  public void setConRemain_e(long conRemain_e) {
    this.conRemain_e = conRemain_e;
  }
  
  public String getConEnd() {
    return conEnd;
  }

  public void setConEnd(String conEnd) {
    this.conEnd = conEnd;
  }

  public String getConCont() {
    return conCont;
  }

  public void setConCont(String conCont) {
    this.conCont = conCont;
  }

  public String getConUrl() {
    return conUrl;
  }

  public void setConUrl(String conUrl) {
    this.conUrl = conUrl;
  }

  public String getConYou() {
    return conYou;
  }

  public void setConYou(String conYou) {
    this.conYou = conYou;
  }

  public String getConFile1() {
    return conFile1;
  }

  public void setConFile1(String conFile1) {
    this.conFile1 = conFile1;
  }

  public long getConSize1() {
    return conSize1;
  }

  public void setConSize1(long conSize1) {
    this.conSize1 = conSize1;
  }

  public String getConThumb() {
    return conThumb;
  }

  public void setConThumb(String conThumb) {
    this.conThumb = conThumb;
  }

  public String getConFile2() {
    return conFile2;
  }

  public void setConFile2(String conFile2) {
    this.conFile2 = conFile2;
  }

  public long getConSize2() {
    return conSize2;
  }

  public void setConSize2(long conSize2) {
    this.conSize2 = conSize2;
  }
  
  public String getConFstor2() {
    return conFstor2;
  }
  
  public void setConFstor2(String conFstro2) {
    this.conFstor2 = conFstro2;
  }
  
  public String getConFile3() {
    return conFile3;
  }

  public void setConFile3(String conFile3) {
    this.conFile3 = conFile3;
  }

  public long getConSize3() {
    return conSize3;
  }
  
  public String getConFstor3() {
    return conFstor3;
  }
  
  public void setConFstor3(String conFstro3) {
    this.conFstor3 = conFstro3;
  }

  public void setConSize3(long conSize3) {
    this.conSize3 = conSize3;
  }

  public int getConGood() {
    return conGood;
  }

  public void setConGood(int conGood) {
    this.conGood = conGood;
  }

  public int getConCnt() {
    return conCnt;
  }

  public void setConCnt(int conCnt) {
    this.conCnt = conCnt;
  }

  public String getCdate() {
    return cdate;
  }

  public void setCdate(String cdate) {
    this.cdate = cdate;
  }

  public String getConWord() {
    return conWord;
  }

  public void setConWord(String conWord) {
    this.conWord = conWord;
  }

  public int getNowPage() {
    return nowPage;
  }

  public void setNowPage(int nowPage) {
    this.nowPage = nowPage;
  }

  public MultipartFile getFile1MF() {
    return file1MF;
  }

  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }

  public String getSize1Label() {
    return size1Label;
  }

  public void setSize1Label(String size1Label) {
    this.size1Label = size1Label;
  }

  public MultipartFile getFile2MF() {
    return file2MF;
  }

  public void setFile2MF(MultipartFile file2mf) {
    file2MF = file2mf;
  }

  public MultipartFile getFile3MF() {
    return file3MF;
  }

  public void setFile3MF(MultipartFile file3mf) {
    file3MF = file3mf;
  }

  public String getSize2Label() {
    return size2Label;
  }

  public void setSize2Label(String size2Label) {
    this.size2Label = size2Label;
  }

  public String getSize3Label() {
    return size3Label;
  }

  public void setSize3Label(String size3Label) {
    this.size3Label = size3Label;
  }
  
}
