
CREATE USER study IDENTIFIED BY 1234;
 
 
3. ���� �ο�
    GRANT connect, resource to study;

DROP TABLE std_recom CASCADE CONSTRAINTS;
DROP TABLE std_reply CASCADE CONSTRAINTS;
DROP TABLE recruit CASCADE CONSTRAINTS;
DROP TABLE studylist CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: ȸ������ */
/**********************************/
CREATE TABLE member(
    memberno                          NUMBER(10)     PRIMARY KEY,
    memid                               VARCHAR2(50)     NOT NULL,
    mememail                          VARCHAR2(100)     NOT NULL,
    mempasswd                         VARCHAR2(50)     NOT NULL,
    memname                           VARCHAR2(20)     NOT NULL,
    memconfirm                        VARCHAR2(5)    DEFAULT 'N'     NOT NULL,
    memauth                           VARCHAR2(5)    DEFAULT 'B'     NOT NULL,
    membirth                          VARCHAR2(50)     NOT NULL,
    memgender                         VARCHAR2(5)    NOT NULL,
    memaddress                        VARCHAR2(100)    NOT NULL,
    memphone                          VARCHAR2(50)     NULL,
    memsns                            VARCHAR2(100)    NULL ,
    memintro                          VARCHAR2(1000)     NULL ,
    memphoto                          VARCHAR2(500)    NULL ,
    memphoto_t                        VARCHAR2(500)    NULL ,
    memsize                           NUMBER(30)     DEFAULT 0     NULL,
    mbirthvb                          VARCHAR2(5)    DEFAULT 'Y'    NULL,
    mgendervb                         VARCHAR2(5)    DEFAULT 'Y'    NULL,
    maddressvb                        VARCHAR2(5)    DEFAULT 'Y'     NULL,
    mphonevb                          VARCHAR2(5)    DEFAULT 'Y'     NULL,
    msnsvb                            VARCHAR2(5)    DEFAULT 'Y'     NULL,
    mintrovb                          VARCHAR2(5)    DEFAULT 'Y'     NULL,
    mphotovb                          VARCHAR2(5)    DEFAULT 'Y'    NULL,
    memdate                           DATE     NOT NULL
);

COMMENT ON TABLE member is 'ȸ������';
COMMENT ON COLUMN member.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN member.memid is 'ȸ�����̵�';
COMMENT ON COLUMN member.mememail is 'ȸ���̸���';
COMMENT ON COLUMN member.mempasswd is 'ȸ����й�ȣ';
COMMENT ON COLUMN member.memname is 'ȸ���̸�';
COMMENT ON COLUMN member.memconfirm is 'ȸ������Ȯ��';
COMMENT ON COLUMN member.memauth is 'ȸ������';
COMMENT ON COLUMN member.membirth is 'ȸ���������';
COMMENT ON COLUMN member.memgender is 'ȸ������';
COMMENT ON COLUMN member.memaddress is 'ȸ���ּ�';
COMMENT ON COLUMN member.memphone is 'ȸ����ȭ��ȣ';
COMMENT ON COLUMN member.memsns is 'ȸ��SNS';
COMMENT ON COLUMN member.memintro is 'ȸ���Ұ�';
COMMENT ON COLUMN member.memphoto is 'ȸ����������';
COMMENT ON COLUMN member.memphoto_t is 'ȸ�����������';
COMMENT ON COLUMN member.memsize is 'ȸ����������ũ��';
COMMENT ON COLUMN member.mbirthvb is 'ȸ�����������¿���';
COMMENT ON COLUMN member.mgendervb is 'ȸ��������¿���';
COMMENT ON COLUMN member.maddressvb is 'ȸ���ּ���¿���';
COMMENT ON COLUMN member.mphonevb is 'ȸ����ȭ��ȣ��¿���';
COMMENT ON COLUMN member.msnsvb is 'ȸ��SNS��¿���';
COMMENT ON COLUMN member.mintrovb is 'ȸ���ڱ�Ұ���¿���';
COMMENT ON COLUMN member.mphotovb is 'ȸ��������¿���';
COMMENT ON COLUMN member.memdate is 'ȸ��������';

select *
from member

1. INSERT    
1) ���
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test1', 'test1@gmail.com', '123456', '����',
'Y', 'U', '19891025', '��', '���� ���ϱ�', '01012345678', '�ν�Ÿ�׷�', '�ȳ��ϼ���', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test2', 'test2@gmail.com', '123456', '����2',
'Y', 'B', '19891025', '��', '���� ���ϱ�', '01012345678', '�ν�Ÿ�׷�', '�ȳ��ϼ���', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test3', 'test3@gmail.com', '123456', '������',
'Y', 'U', '19891025', '��', '���� ���ϱ�', '01012345678', '�ν�Ÿ�׷�', '�ȳ��ϼ���', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test4', 'test4@gmail.com', '123456', '������2',
'Y', 'U', '19891025', '��', '���� ���ϱ�', '01012345678', '�ν�Ÿ�׷�', '�ȳ��ϼ���', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test5', 'test5@gmail.com', '123456', '����',
'Y', 'U', '19891025', '��', '���� ���ϱ�', '01012345678', '�ν�Ÿ�׷�', '�ȳ��ϼ���', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test6', 'test6@gmail.com', '123456', '���Ҷ�',
'Y', 'U', '19891025', '��', '���� ���ϱ�', '01012345678', '�ν�Ÿ�׷�', '�ȳ��ϼ���', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);

/**********************************/
/* Table Name: ���͵��� */
/**********************************/
CREATE TABLE studylist(
		stdlist_no                    		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		stdlist_title                 		VARCHAR2(100)		 NOT NULL,
		stdlist_email                 		VARCHAR2(50)		 NOT NULL,
		stdlist_topic                 		VARCHAR2(50)		 NOT NULL,
		stdlist_time                  		VARCHAR2(50)		 NOT NULL,
		stdlist_area                  		VARCHAR2(50)		 NOT NULL,
		stdlist_dow                   		VARCHAR2(50)		 NOT NULL,
		stdlist_tot_num               		NUMBER(7)		 DEFAULT 4		 NOT NULL,
		stdlist_curr_num              		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		stdlist_goodcnt               		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		stdlist_content               		VARCHAR2(4000)		 NOT NULL,
		stdlist_cnt                   		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		stdlist_date                  		DATE		 NOT NULL,
		memberno                      		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE studylist is '���͵���';
COMMENT ON COLUMN studylist.stdlist_no is '���͵��ȣ';
COMMENT ON COLUMN studylist.stdlist_title is '���͵� ����';
COMMENT ON COLUMN studylist.stdlist_email is '�̸���';
COMMENT ON COLUMN studylist.stdlist_topic is '�о�';
COMMENT ON COLUMN studylist.stdlist_time is '�ð�';
COMMENT ON COLUMN studylist.stdlist_area is '����';
COMMENT ON COLUMN studylist.stdlist_dow is '����';
COMMENT ON COLUMN studylist.stdlist_tot_num is '��������';
COMMENT ON COLUMN studylist.stdlist_curr_num is '�������';
COMMENT ON COLUMN studylist.stdlist_goodcnt is '���ƿ��';
COMMENT ON COLUMN studylist.stdlist_content is '����(����)���';
COMMENT ON COLUMN studylist.stdlist_cnt is '��ȸ��';
COMMENT ON COLUMN studylist.stdlist_date is '�����';
COMMENT ON COLUMN studylist.memberno is 'ȸ����ȣ';


select *
from studylist

/**********************************/
/* Table Name: ��û ��Ȳ ���̺� */
/**********************************/
CREATE TABLE recruit(
		recruitno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		confirm                       		VARCHAR2(10)		 DEFAULT 'U'		 NOT NULL,
		std_auth                      		VARCHAR2(10)		 DEFAULT 'U'		 NOT NULL,
		stdlist_no                    		NUMBER(10)		 NULL ,
		memberno                      		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (stdlist_no) REFERENCES studylist (stdlist_no)
);

COMMENT ON TABLE recruit is '��û ��Ȳ ���̺�';
COMMENT ON COLUMN recruit.recruitno is '��û��ȣ';
COMMENT ON COLUMN recruit.confirm is '���ο���';
COMMENT ON COLUMN recruit.std_auth is '����(���͵� ��)';
COMMENT ON COLUMN recruit.stdlist_no is '���͵��ȣ';
COMMENT ON COLUMN recruit.memberno is 'ȸ����ȣ';


/**********************************/
/* Table Name: ���͵� ����Ʈ ��� */
/**********************************/
CREATE TABLE std_reply(
		std_repno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		std_repcont                   		VARCHAR2(200)		 NOT NULL,
		std_repdate                   		DATE		 NOT NULL,
		std_repname                   		VARCHAR2(30)		 NOT NULL,
		memberno                      		NUMBER(10)		 NULL ,
		stdlist_no                    		NUMBER(10)		 NULL ,
  FOREIGN KEY (stdlist_no) REFERENCES studylist (stdlist_no)
);

COMMENT ON TABLE std_reply is '���͵� ����Ʈ ���';
COMMENT ON COLUMN std_reply.std_repno is '��� ��ȣ';
COMMENT ON COLUMN std_reply.std_repcont is '��� ����';
COMMENT ON COLUMN std_reply.std_repdate is '��� �����';
COMMENT ON COLUMN std_reply.std_repname is '��� �ۼ���';
COMMENT ON COLUMN std_reply.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN std_reply.stdlist_no is '���͵��ȣ';


/**********************************/
/* Table Name: ���ƿ�, �Ⱦ�� */
/**********************************/
CREATE TABLE std_recom(
		std_recom_no                  		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		good_ch                       		NUMBER(2)		 DEFAULT 0		 NOT NULL,
		stdlist_no                    		NUMBER(10)		 NULL ,
		memberno                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (stdlist_no) REFERENCES studylist (stdlist_no),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE std_recom is '���ƿ�, �Ⱦ��';
COMMENT ON COLUMN std_recom.std_recom_no is '��ȣ';
COMMENT ON COLUMN std_recom.good_ch is '���ƿ俩��';
COMMENT ON COLUMN std_recom.stdlist_no is '���͵��ȣ';
COMMENT ON COLUMN std_recom.memberno is 'ȸ����ȣ';


