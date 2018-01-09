/**********************************/
/* Table Name: ī�װ� */
/**********************************/
CREATE TABLE category(
      cateno                              NUMBER(10)       NOT NULL       PRIMARY KEY,
      catename                            VARCHAR2(50)       NOT NULL,
      cateseqno                           NUMBER(5)       NOT NULL,
      catevisible                         VARCHAR2(1)       DEFAULT 'Y'       NOT NULL
);

COMMENT ON TABLE category is 'ī�װ�';
COMMENT ON COLUMN category.cateno is 'ī�װ���ȣ';
COMMENT ON COLUMN category.catename is 'ī�װ��̸�';
COMMENT ON COLUMN category.cateseqno is 'ī�װ���¼���';
COMMENT ON COLUMN category.catevisible is 'ī�װ���¿���';

INSERT INTO "STUDY"."CATEGORY" (CATENO, CATENAME, CATESEQNO, CATEVISIBLE) VALUES ('1', '�����͵�', '1', 'Y');
INSERT INTO "STUDY"."CATEGORY" (CATENO, CATENAME, CATESEQNO, CATEVISIBLE) VALUES ('2', '�����͵�-��������', '2', 'Y');
INSERT INTO "STUDY"."CATEGORY" (CATENO, CATENAME, CATESEQNO, CATEVISIBLE) VALUES ('3', '�����͵�-�����Խ���', '3', 'Y');
INSERT INTO "STUDY"."CATEGORY" (CATENO, CATENAME, CATESEQNO, CATEVISIBLE) VALUES ('4', '�����͵�-�ڷ��', '4', 'Y');
INSERT INTO "STUDY"."CATEGORY" (CATENO, CATENAME, CATESEQNO, CATEVISIBLE) VALUES ('5', '�����͵�-���͵� ����', '5', 'Y');



/**********************************/
/* Table Name: �����͵�-ī�װ� ����Ʈ ���̺� */
/**********************************/
CREATE TABLE my_std_catelist(
      mylistno                            NUMBER(10)       NOT NULL       PRIMARY KEY,
      stdlist_no                          NUMBER(10)       NULL ,
      cateno                              NUMBER(10)       NULL ,
  FOREIGN KEY (stdlist_no) REFERENCES studylist (stdlist_no),
  FOREIGN KEY (cateno) REFERENCES category (cateno)
);

COMMENT ON TABLE my_std_catelist is '�����͵�-ī�װ� ����Ʈ ���̺�';
COMMENT ON COLUMN my_std_catelist.mylistno is '�����͵𸮽�Ʈ ���� ��ȣ';
COMMENT ON COLUMN my_std_catelist.stdlist_no is '���͵��ȣ';
COMMENT ON COLUMN my_std_catelist.cateno is 'ī�װ���ȣ';


/**********************************/
/* Table Name: �� ���͵� �� ��� */
/**********************************/
CREATE TABLE my_pds(
      pdsno                               NUMBER(10)       NOT NULL       PRIMARY KEY,
      pdstitle                            VARCHAR2(500)       NOT NULL,
      pdspasswd                           VARCHAR2(100)       NULL ,
      pdscontent                          VARCHAR2(4000)       NULL ,
      pdsfile1                            VARCHAR2(300)       NULL ,
      pdsfilesize                         NUMBER(30)       DEFAULT 0       NULL ,
      pdsthumb                            VARCHAR2(100)       NULL ,
      pdsword                             VARCHAR2(100)       NULL ,
      pdslike                             NUMBER(10)       DEFAULT 0       NULL ,
      pdscnt                              NUMBER(10)       DEFAULT 0       NULL ,
      pdsdate                             DATE       NOT NULL,
      memberno                            NUMBER(10)       NULL ,
      mylistno                            NUMBER(10)       NULL ,
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (mylistno) REFERENCES my_std_catelist (mylistno)
);

COMMENT ON TABLE my_pds is '�� ���͵� �� ���';
COMMENT ON COLUMN my_pds.pdsno is '�� ��ȣ';
COMMENT ON COLUMN my_pds.pdstitle is '�� ����';
COMMENT ON COLUMN my_pds.pdspasswd is '�� ��й�ȣ';
COMMENT ON COLUMN my_pds.pdscontent is '�� ����';
COMMENT ON COLUMN my_pds.pdsfile1 is '�� ÷�����ϸ�';
COMMENT ON COLUMN my_pds.pdsfilesize is '�� ���� ������';
COMMENT ON COLUMN my_pds.pdsthumb is '�� ���� thumb';
COMMENT ON COLUMN my_pds.pdsword is '�� �˻���';
COMMENT ON COLUMN my_pds.pdslike is '�� ��õ��';
COMMENT ON COLUMN my_pds.pdscnt is '�� ��ȸ��';
COMMENT ON COLUMN my_pds.pdsdate is '�� �ۼ���';
COMMENT ON COLUMN my_pds.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN my_pds.mylistno is '�����͵𸮽�Ʈ ���� ��ȣ';