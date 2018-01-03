package dev.mvc.qna;

import org.springframework.web.multipart.MultipartFile;

/*
  qnano                             NUMBER(7)    NOT NULL    PRIMARY KEY,
  qnatitle                          VARCHAR2(200)    NOT NULL,
  wname                             VARCHAR2(100)    NOT NULL,
  qnacont                           VARCHAR2(4000)     NOT NULL,
  qnafile1                          VARCHAR2(400)    NULL ,
  qnasize1                          NUMBER(10)     DEFAULT 0     NULL ,
  qnafstor1                         VARCHAR2(400)    NULL ,
  qnakind                           VARCHAR2(100)    NOT NULL,
  qnavisible                        VARCHAR2(10)     DEFAULT 'Y'     NOT NULL,
  qdate                             DATE     NOT NULL,
  qnapwd                            VARCHAR2(200)     NOT NULL,
  qnacnt                            NUMBER(7)      DEFAULT 0      NOT NULL,
  qnaword                           VARCHAR2(100)    NULL ,
  memberno                          NUMBER(10)     NULL ,
  adminno                           NUMBER(10)     NULL ,
 */

public class QnaVO {
  /** �Խ��� ��ȣ */
  private int qnano;
  /** ȸ�� ��ȣ */
  private int memberno;
  /** ������ ��ȣ */
  private int adminno;
  /** �Խ��� ���� */
  private String qnatitle="";
  /** �ۼ��� */
  private String wname="";
  /** �Խ��� ���� */
  private String qnacont="";
  /** ÷������ �� */
  private String qnafile1="";
  /** ÷������ ������ */
  private long qnasize1=0;
  /** ÷������ �������ϸ� */
  private String qnafstor1="";
  /** �Խ��� �з� */
  private String qnakind="";
  /** ��� ���� */
  private String qnavisible="Y";
  /** ����� */
  private String qdate="";
  /** �Խ��� ��й�ȣ */
  private String qnapwd="";
  /** �Խ��� ��ȸ�� */
  private int qnacnt=0;
  /** �Խ��� �˻��� */
  private String qnaword="";
  /** ���� ������ */
  private int nowPage = 1;
  /** 
  Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
  */
  private MultipartFile file1MF;
  /** size1�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String size1Label;
  
  public QnaVO() {
    
  }

  public int getQnano() {
    return qnano;
  }

  public void setQnano(int qnano) {
    this.qnano = qnano;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public int getAdminno() {
    return adminno;
  }

  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }

  public String getQnatitle() {
    return qnatitle;
  }

  public void setQnatitle(String qnatitle) {
    this.qnatitle = qnatitle;
  }

  public String getWname() {
    return wname;
  }

  public void setWname(String wname) {
    this.wname = wname;
  }

  public String getQnacont() {
    return qnacont;
  }

  public void setQnacont(String qnacont) {
    this.qnacont = qnacont;
  }

  public String getQnafile1() {
    return qnafile1;
  }

  public void setQnafile1(String qnafile1) {
    this.qnafile1 = qnafile1;
  }

  public long getQnasize1() {
    return qnasize1;
  }

  public void setQnasize1(long qnasize1) {
    this.qnasize1 = qnasize1;
  }

  public String getQnafstor1() {
    return qnafstor1;
  }

  public void setQnafstor1(String qnafstor1) {
    this.qnafstor1 = qnafstor1;
  }

  public String getQnagrp() {
    return qnakind;
  }

  public void setQnagrp(String qnakind) {
    this.qnakind = qnakind;
  }

  public String getQnavisible() {
    return qnavisible;
  }

  public void setQnavisible(String qnavisible) {
    this.qnavisible = qnavisible;
  }

  public String getQdate() {
    return qdate;
  }

  public void setQdate(String qdate) {
    this.qdate = qdate;
  }

  public String getQnapwd() {
    return qnapwd;
  }

  public void setQnapwd(String qnapwd) {
    this.qnapwd = qnapwd;
  }

  public int getQnacnt() {
    return qnacnt;
  }

  public void setQnacnt(int qnacnt) {
    this.qnacnt = qnacnt;
  }

  public String getQnaword() {
    return qnaword;
  }

  public void setQnaword(String qnaword) {
    this.qnaword = qnaword;
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
  
}
