package dev.mvc.job;

import org.springframework.web.multipart.MultipartFile;

/*
    jobNo                             NUMBER(7)    NOT NULL    PRIMARY KEY,
    comName                           VARCHAR2(200)    NOT NULL,
    bossName                          VARCHAR2(200)    NOT NULL,
    labCnt                            NUMBER(7)    DEFAULT 0     NOT NULL,
    jobStatus                         VARCHAR2(200)    NOT NULL,
    comAddr                           VARCHAR2(200)    NOT NULL,
    homepage                          VARCHAR2(200)    NULL ,
    career                            VARCHAR2(200)    NULL ,
    edu                               VARCHAR2(200)    NULL ,
    jobAddr1                          VARCHAR2(200)    NOT NULL,
    jobAddr2                          VARCHAR2(200)    NOT NULL,
    payLow                            NUMBER(10)     NOT NULL,
    payHigh                           NUMBER(10)     NOT NULL,
    employ                            VARCHAR2(200)    NOT NULL,
    work                              VARCHAR2(200)    NOT NULL,
    jobStart                          VARCHAR2(200)    NOT NULL,
    jobEnd                            VARCHAR2(200)    NOT NULL,
    jobRemain_s                       NUMBER(10)     DEFAULT 0     NULL ,
    jobRemain_e                       NUMBER(10)     DEFAULT 0     NULL ,
    jobCont                           VARCHAR2(2000)     NULL ,
    jobFile1                          VARCHAR2(50)     NULL ,
    jobSize1                          NUMBER(10)     DEFAULT 0     NULL ,
    jobThumb                          VARCHAR2(100)    NULL ,
    jobFile2                          VARCHAR2(50)     NULL ,
    jobSize2                          NUMBER(10)     DEFAULT 0     NULL ,
    jobFstor2                         VARCHAR2(100)    NULL ,
    jdate                             DATE     NOT NULL,
    adminno                           NUMBER(10)     NULL ,
    memberno                          NUMBER(10)     NULL ,
 */

public class JobVO {
  /** ä�� ��ȣ */
  private int jobNo;
  /** ������ ��ȣ */
  private int adminno;
  /** ȸ�� ��ȣ */
  private int memberno;
  /** ȸ��� */
  private String comName="";
  /** ��ǥ�ڸ� */
  private String bossName="";
  /** �ٷ��ڼ� */
  private int labCnt;
  /** �������� */
  private String jobStatus="";
  /** ȸ���ּ� */
  private String comAddr="";
  /** Ȩ������ */
  private String homepage="";
  /** ��� */
  private String career="";
  /** �з� */
  private String edu="";
  /** �ٹ�����(���� �ּ�) */
  private String jobLocal = "";
  /** �ٹ�����(����) */
  private String jcity = "";
  /** �ٹ�����(��, ��) */
  private String jgu = "";
  /** �ٹ�����(������) */
  private String jdong = "";
  /** �ӱ� ���� */
  private String jobPay = "";
  /** �����ӱ� */
  private int payLow;
  /** �ְ��ӱ� */
  private int payHigh;
  /** ������� */
  private String jobEmploy = "";
  /** �ٹ����� */
  private String jobWork = "";
  /** ���������� */
  private String jobStart="";
  /** ���������� */
  private String jobEnd = "";
  /** �����ϼ�(�����ϱ���) */
  private long jobRemain_s;
  /** �����ϼ�(�����ϱ���) */
  private long jobRemain_e;
  /** �߰�����(����) */
  private String jobCont = "";
  /** ����ϸ� */
  private String jobFile1="";
  /** ����ϻ����� */
  private long jobSize1=0;
  /** ����� �������ϸ� */
  private String jobThumb="";
  /** �Ϲ����ϸ� */
  private String jobFile2 = "";
  /** �Ϲ����ϻ����� */
  private long jobSize2 = 0;
  /** �Ϲ����Ͻ����� */
  private String jobFstor2 = "";
  /** ä������ ��ȸ�� */
  private int jobCnt = 0;
  /** �˻� Ű���� */
  private String jobWord = "";
  /** ����� */
  private String jdate;
  /** ���� ������ */
  private int nowPage = 1;
  
  /**
  Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
  */  
  private MultipartFile file1MF;
  private MultipartFile file2MF;
  
  /** size1�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String size1Label;
  private String size2Label;
  
  public JobVO() {
    
  }

  public int getJobNo() {
    return jobNo;
  }

  public void setJobNo(int jobNo) {
    this.jobNo = jobNo;
  }

  public int getAdminno() {
    return adminno;
  }

  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public String getComName() {
    return comName;
  }

  public void setComName(String comName) {
    this.comName = comName;
  }
  
  public String getBossName() {
    return bossName;
  }

  public void setBossName(String bossName) {
    this.bossName = bossName;
  }

  public int getLabCnt() {
    return labCnt;
  }

  public void setLabCnt(int labCnt) {
    this.labCnt = labCnt;
  }

  public String getJobStatus() {
    return jobStatus;
  }

  public void setJobStatus(String jobStatus) {
    this.jobStatus = jobStatus;
  }

  public String getComAddr() {
    return comAddr;
  }

  public void setComAddr(String comAddr) {
    this.comAddr = comAddr;
  }

  public String getHomepage() {
    return homepage;
  }

  public void setHomepage(String homepage) {
    this.homepage = homepage;
  }

  public String getCareer() {
    return career;
  }

  public void setCareer(String career) {
    this.career = career;
  }

  public String getEdu() {
    return edu;
  }

  public void setEdu(String edu) {
    this.edu = edu;
  }
  
  public String getJobLocal() {
    return jobLocal;
  }

  public void setJobLocal(String jobLocal) {
    this.jobLocal = jobLocal;
  }

  public String getJcity() {
    return jcity;
  }

  public void setJcity(String jcity) {
    this.jcity = jcity;
  }

  public String getJgu() {
    return jgu;
  }

  public void setJgu(String jgu) {
    this.jgu = jgu;
  }

  public String getJdong() {
    return jdong;
  }

  public void setJdong(String jdong) {
    this.jdong = jdong;
  }

  public String getJobPay() {
    return jobPay;
  }

  public void setJobPay(String jobPay) {
    this.jobPay = jobPay;
  }

  public int getPayLow() {
    return payLow;
  }

  public void setPayLow(int payLow) {
    this.payLow = payLow;
  }

  public int getPayHigh() {
    return payHigh;
  }

  public void setPayHigh(int payHigh) {
    this.payHigh = payHigh;
  }

  public String getJobEmploy() {
    return jobEmploy;
  }

  public void setJobEmploy(String jobEmploy) {
    this.jobEmploy = jobEmploy;
  }

  public String getJobWork() {
    return jobWork;
  }

  public void setJobWork(String jobWork) {
    this.jobWork = jobWork;
  }

  public String getJobStart() {
    return jobStart;
  }

  public void setJobStart(String jobStart) {
    this.jobStart = jobStart;
  }

  public String getJobEnd() {
    return jobEnd;
  }

  public void setJobEnd(String jobEnd) {
    this.jobEnd = jobEnd;
  }

  public long getJobRemain_s() {
    return jobRemain_s;
  }

  public void setJobRemain_s(long jobRemain_s) {
    this.jobRemain_s = jobRemain_s;
  }

  public long getJobRemain_e() {
    return jobRemain_e;
  }

  public void setJobRemain_e(long jobRemain_e) {
    this.jobRemain_e = jobRemain_e;
  }

  public String getJobCont() {
    return jobCont;
  }

  public void setJobCont(String jobCont) {
    this.jobCont = jobCont;
  }

  public String getJobFile1() {
    return jobFile1;
  }

  public void setJobFile1(String jobFile1) {
    this.jobFile1 = jobFile1;
  }

  public long getJobSize1() {
    return jobSize1;
  }

  public void setJobSize1(long jobSize1) {
    this.jobSize1 = jobSize1;
  }

  public String getJobThumb() {
    return jobThumb;
  }

  public void setJobThumb(String jobThumb) {
    this.jobThumb = jobThumb;
  }

  public String getJobFile2() {
    return jobFile2;
  }

  public void setJobFile2(String jobFile2) {
    this.jobFile2 = jobFile2;
  }

  public long getJobSize2() {
    return jobSize2;
  }

  public void setJobSize2(long jobSize2) {
    this.jobSize2 = jobSize2;
  }

  public String getJobFstor2() {
    return jobFstor2;
  }

  public void setJobFstor2(String jobFstor2) {
    this.jobFstor2 = jobFstor2;
  }
  
  public int getJobCnt() {
    return jobCnt;
  }

  public void setJobCnt(int jobCnt) {
    this.jobCnt = jobCnt;
  }

  public String getJobWord() {
    return jobWord;
  }

  public void setJobWord(String jobWord) {
    this.jobWord = jobWord;
  }

  public String getJdate() {
    return jdate;
  }

  public void setJdate(String jdate) {
    this.jdate = jdate;
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

  public MultipartFile getFile2MF() {
    return file2MF;
  }

  public void setFile2MF(MultipartFile file2mf) {
    file2MF = file2mf;
  }

  public String getSize1Label() {
    return size1Label;
  }

  public void setSize1Label(String size1Label) {
    this.size1Label = size1Label;
  }

  public String getSize2Label() {
    return size2Label;
  }

  public void setSize2Label(String size2Label) {
    this.size2Label = size2Label;
  }
  
}
