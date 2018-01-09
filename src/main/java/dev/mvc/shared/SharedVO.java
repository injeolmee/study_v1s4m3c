package dev.mvc.shared;

import org.springframework.web.multipart.MultipartFile;

public class SharedVO {
/*  
  CREATE TABLE shared(
    sharedno                          NUMBER(7)    NOT NULL,
    sharedtitle                       VARCHAR2(100)    NOT NULL,
    sharedcontent                     VARCHAR2(1000)     NOT NULL,
    sharedname                        VARCHAR2(50)     NOT NULL,
    sharedyoutube                     VARCHAR2(500)    NULL ,
    sharedseqno                       NUMBER(10)     NOT NULL,
    sharedfile                        VARCHAR2(500)    NULL ,
    sharedfstor                       VARCHAR2(500)    NULL ,
    sharedtum                         VARCHAR2(500)    NULL ,
    sharedsize                        NUMBER(30)     DEFAULT 0     NOT NULL,
    shareddate                        DATE     NOT NULL,
    sharedcnt                         NUMBER(6)    DEFAULT 0     NOT NULL,
    sharedlike                        NUMBER(6)    DEFAULT 0     NOT NULL,
    sharedpasswd                      VARCHAR2(50)     NULL ,
    MEMBERNO                          NUMBER(10)     NULL 
);*/
  
  /* �Խ��� ��ȣ */
  private int sharedno;
  /* ���� */
  private String sharedtitle;
  /* ���� */
  private String sharedcontent;
  /* �̸� */
  private String sharedname;
  /* YOUTUBE */
  private String sharedyoutube;
  /* ��� ���� (��õ�� ���� ������ ����) */
  private int sharedseqno;
  /* ���ϸ� */
  private String sharedfile;
  /* ������ ����� ���ϸ� */
  private String sharedfstor;
  /* ���� ����� */
  private String sharedtum;
  /* ���� ������ */
  private long sharedsize;
  /* �����*/
  private String shareddate;
  /* ��ȸ�� */
  private int sharedcnt;
  /* ��õ�� */
  private int sharedlike;
  /* �н����� */
  private String sharedpasswd;
  /* ȸ����ȣ */
  private int memberno;
  /* ������ ��ȣ */
  private int adminno;
  /* ���� ���� ������ ���� �ӽ� ����*/
  private MultipartFile file1MF;
  /** size1�� �ĸ� ���� ��¿� �ӽ� ���� */
  private String sharedsizeLabel;
  /* �˻��� ���� ���� �ӽ� ���� */
  private String word;
  /* �˻� �з��� ���� �ӽ� ����*/
  private String search;
  /* ���� + ���� �˻� ���� �ӽ� ���� */
  private String sharedtn;
  /* ����¡ ���� �ӽ� ���� */
  private int nowPage;
  /* ������ ���� �ӽ� ���� */
  private int pre_num;
  /* ������ ���� �ӽ� ���� */
  private int post_num;
  
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  public int getPre_num() {
    return pre_num;
  }
  public void setPre_num(int pre_num) {
    this.pre_num = pre_num;
  }
  public int getPost_num() {
    return post_num;
  }
  public void setPost_num(int post_num) {
    this.post_num = post_num;
  }
  
  public String getSharedfstor() {
    return sharedfstor;
  }
  public void setSharedfstor(String sharedfstor) {
    this.sharedfstor = sharedfstor;
  }
  public String getSharedsizeLabel() {
    return sharedsizeLabel;
  }
  public void setSharedsizeLabel(String sharedsizeLabel) {
    this.sharedsizeLabel = sharedsizeLabel;
  }
  public String getWord() {
    return word;
  }
  public void setWord(String word) {
    this.word = word;
  }
  public String getSearch() {
    return search;
  }
  public void setSearch(String search) {
    this.search = search;
  }
  public String getSharedtn() {
    return sharedtn;
  }
  public void setSharedtn(String sharedtn) {
    this.sharedtn = sharedtn;
  }
  public int getNowPage() {
    return nowPage;
  }
  public void setNowPage(int nowPage) {
    this.nowPage = nowPage;
  }
  public String getSharedtum() {
    return sharedtum;
  }
  public void setSharedtum(String sharedtum) {
    this.sharedtum = sharedtum;
  }
  public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }
  public int getSharedno() {
    return sharedno;
  }
  public void setSharedno(int sharedno) {
    this.sharedno = sharedno;
  }
  public String getSharedtitle() {
    return sharedtitle;
  }
  public void setSharedtitle(String sharedtitle) {
    this.sharedtitle = sharedtitle;
  }
  public String getSharedcontent() {
    return sharedcontent;
  }
  public void setSharedcontent(String sharedcontent) {
    this.sharedcontent = sharedcontent;
  }
  public String getSharedname() {
    return sharedname;
  }
  public void setSharedname(String sharedname) {
    this.sharedname = sharedname;
  }
  public String getSharedyoutube() {
    return sharedyoutube;
  }
  public void setSharedyoutube(String sharedyoutube) {
    this.sharedyoutube = sharedyoutube;
  }
  public int getSharedseqno() {
    return sharedseqno;
  }
  public void setSharedseqno(int sharedseqno) {
    this.sharedseqno = sharedseqno;
  }
  public String getSharedfile() {
    return sharedfile;
  }
  public void setSharedfile(String sharedfile) {
    this.sharedfile = sharedfile;
  }

  public long getSharedsize() {
    return sharedsize;
  }
  public void setSharedsize(long sharedsize) {
    this.sharedsize = sharedsize;
  }
  public String getShareddate() {
    return shareddate;
  }
  public void setShareddate(String shareddate) {
    this.shareddate = shareddate;
  }
  public int getSharedcnt() {
    return sharedcnt;
  }
  public void setSharedcnt(int sharedcnt) {
    this.sharedcnt = sharedcnt;
  }
  public int getSharedlike() {
    return sharedlike;
  }
  public void setSharedlike(int sharedlike) {
    this.sharedlike = sharedlike;
  }
  public String getSharedpasswd() {
    return sharedpasswd;
  }
  public void setSharedpasswd(String sharedpasswd) {
    this.sharedpasswd = sharedpasswd;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }


  
}
