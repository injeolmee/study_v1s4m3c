DROP TABLE RVLIKE CASCADE CONSTRAINTS;
DROP TABLE REVIEW CASCADE CONSTRAINTS;
DROP TABLE ROOM CASCADE CONSTRAINTS;
DROP TABLE msg_repo CASCADE CONSTRAINTS;
DROP TABLE msgrecv CASCADE CONSTRAINTS;
DROP TABLE my_pds CASCADE CONSTRAINTS;
DROP TABLE my_std_catelist CASCADE CONSTRAINTS;
DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE msgsend CASCADE CONSTRAINTS;
DROP TABLE message CASCADE CONSTRAINTS;
DROP TABLE QNAREPLY CASCADE CONSTRAINTS;
DROP TABLE QNABOARD CASCADE CONSTRAINTS;
DROP TABLE JOBINFO CASCADE CONSTRAINTS;
DROP TABLE CONLIKE CASCADE CONSTRAINTS;
DROP TABLE CONTEST CASCADE CONSTRAINTS;
DROP TABLE sharedreply CASCADE CONSTRAINTS;
DROP TABLE salereply CASCADE CONSTRAINTS;
DROP TABLE FREELIKE CASCADE CONSTRAINTS;
DROP TABLE freereply CASCADE CONSTRAINTS;
DROP TABLE shared CASCADE CONSTRAINTS;
DROP TABLE free CASCADE CONSTRAINTS;
DROP TABLE sale CASCADE CONSTRAINTS;
DROP TABLE STD_RECOM CASCADE CONSTRAINTS;
DROP TABLE RECRUIT CASCADE CONSTRAINTS;
DROP TABLE STD_REPLY CASCADE CONSTRAINTS;
DROP TABLE STUDYLIST CASCADE CONSTRAINTS;
DROP TABLE NOTICE_REPLY CASCADE CONSTRAINTS;
DROP TABLE NOTICE CASCADE CONSTRAINTS;
DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE VISIT CASCADE CONSTRAINTS;
DROP TABLE ADMIN CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: ������ */
/**********************************/
CREATE TABLE ADMIN(
		ADMINNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		ADMID                         		VARCHAR2(50)		 NOT NULL,
		ADMEMAIL                      		VARCHAR2(100)		 NOT NULL,
		ADMPASSWD                     		VARCHAR2(50)		 NOT NULL,
		ADMNAME                       		VARCHAR2(20)		 NOT NULL,
		ADMCONFIRM                    		VARCHAR2(5)		 NOT NULL,
		ADMAUTH                       		VARCHAR2(5)		 NOT NULL,
		ADMDATE                       		DATE		 NOT NULL
);

COMMENT ON TABLE ADMIN is '������';
COMMENT ON COLUMN ADMIN.ADMINNO is '�����ڹ�ȣ';
COMMENT ON COLUMN ADMIN.ADMID is '�����ھ��̵�';
COMMENT ON COLUMN ADMIN.ADMEMAIL is '�������̸���';
COMMENT ON COLUMN ADMIN.ADMPASSWD is '�����ں�й�ȣ';
COMMENT ON COLUMN ADMIN.ADMNAME is '�������̸�';
COMMENT ON COLUMN ADMIN.ADMCONFIRM is '����������Ȯ��';
COMMENT ON COLUMN ADMIN.ADMAUTH is '�����ڱ���';
COMMENT ON COLUMN ADMIN.ADMDATE is '�����ڰ�����';


/**********************************/
/* Table Name: VISIT */
/**********************************/
CREATE TABLE VISIT(
		VDATE                         		DATE		 NOT NULL
);

COMMENT ON TABLE VISIT is 'VISIT';
COMMENT ON COLUMN VISIT.VDATE is 'VDATE';


/**********************************/
/* Table Name: ȸ������ */
/**********************************/
CREATE TABLE MEMBER(
		MEMBERNO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		MEMID                         		VARCHAR2(50)		 NOT NULL,
		MEMEMAIL                      		VARCHAR2(100)		 NOT NULL,
		MEMPASSWD                     		VARCHAR2(50)		 NOT NULL,
		MEMNAME                       		VARCHAR2(20)		 NOT NULL,
		MEMCONFIRM                    		VARCHAR2(5)		 NOT NULL,
		MEMAUTH                       		VARCHAR2(5)		 NOT NULL,
		MEMBIRTH                      		VARCHAR2(50)		 NOT NULL,
		MEMGENDER                     		VARCHAR2(5)		 NOT NULL,
		MEMADDRESS                    		VARCHAR2(100)		 NOT NULL,
		MEMPHONE                      		VARCHAR2(50)		 NULL ,
		MEMSNS                        		VARCHAR2(100)		 NULL ,
		MEMINTRO                      		VARCHAR2(1000)		 NULL ,
		MEMPHOTO                      		VARCHAR2(500)		 NULL ,
		MEMPHOTO_T                    		VARCHAR2(500)		 NULL ,
		MEMSIZE                       		NUMBER(30)		 NULL ,
		MBIRTHVB                      		VARCHAR2(5)		 NULL ,
		MGENDERVB                     		VARCHAR2(5)		 NULL ,
		MADDRESSVB                    		VARCHAR2(5)		 NULL ,
		MPHONEVB                      		VARCHAR2(5)		 NULL ,
		MSNSVB                        		VARCHAR2(5)		 NULL ,
		MINTROVB                      		VARCHAR2(5)		 NULL ,
		MPHOTOVB                      		VARCHAR2(5)		 NULL ,
		MEMDATE                       		DATE		 NOT NULL
);

COMMENT ON TABLE MEMBER is 'ȸ������';
COMMENT ON COLUMN MEMBER.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN MEMBER.MEMID is 'ȸ�����̵�';
COMMENT ON COLUMN MEMBER.MEMEMAIL is 'ȸ���̸���';
COMMENT ON COLUMN MEMBER.MEMPASSWD is 'ȸ����й�ȣ';
COMMENT ON COLUMN MEMBER.MEMNAME is 'ȸ���̸�';
COMMENT ON COLUMN MEMBER.MEMCONFIRM is 'ȸ������Ȯ��';
COMMENT ON COLUMN MEMBER.MEMAUTH is 'ȸ������';
COMMENT ON COLUMN MEMBER.MEMBIRTH is 'ȸ���������';
COMMENT ON COLUMN MEMBER.MEMGENDER is 'ȸ������';
COMMENT ON COLUMN MEMBER.MEMADDRESS is 'ȸ���ּ�';
COMMENT ON COLUMN MEMBER.MEMPHONE is 'ȸ����ȭ��ȣ';
COMMENT ON COLUMN MEMBER.MEMSNS is 'ȸ��SNS';
COMMENT ON COLUMN MEMBER.MEMINTRO is 'ȸ���Ұ�';
COMMENT ON COLUMN MEMBER.MEMPHOTO is 'ȸ����������';
COMMENT ON COLUMN MEMBER.MEMPHOTO_T is 'ȸ�����������';
COMMENT ON COLUMN MEMBER.MEMSIZE is 'ȸ����������ũ��';
COMMENT ON COLUMN MEMBER.MBIRTHVB is 'ȸ�����������¿���';
COMMENT ON COLUMN MEMBER.MGENDERVB is 'ȸ��������¿���';
COMMENT ON COLUMN MEMBER.MADDRESSVB is 'ȸ���ּ���¿���';
COMMENT ON COLUMN MEMBER.MPHONEVB is 'ȸ����ȭ��ȣ��¿���';
COMMENT ON COLUMN MEMBER.MSNSVB is 'ȸ��SNS��¿���';
COMMENT ON COLUMN MEMBER.MINTROVB is 'ȸ���ڱ�Ұ���¿���';
COMMENT ON COLUMN MEMBER.MPHOTOVB is 'ȸ��������¿���';
COMMENT ON COLUMN MEMBER.MEMDATE is 'ȸ��������';


/**********************************/
/* Table Name: ������ �������� �Խ��� */
/**********************************/
CREATE TABLE NOTICE(
		NOTICENO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NTITLE                        		VARCHAR2(500)		 NOT NULL,
		NNAME                         		VARCHAR2(100)		 NOT NULL,
		NCONTENT                      		VARCHAR2(4000)		 NOT NULL,
		NSEQNO                        		NUMBER(10)		 NOT NULL,
		NCNT                          		NUMBER(10)		 NOT NULL,
		NDATE                         		DATE		 NOT NULL,
		ADMINNO                       		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE NOTICE is '������ �������� �Խ���';
COMMENT ON COLUMN NOTICE.NOTICENO is '�������׹�ȣ';
COMMENT ON COLUMN NOTICE.NTITLE is '������������';
COMMENT ON COLUMN NOTICE.NNAME is '���������ۼ���';
COMMENT ON COLUMN NOTICE.NCONTENT is '�������׳���';
COMMENT ON COLUMN NOTICE.NSEQNO is '����������¼���';
COMMENT ON COLUMN NOTICE.NCNT is '����������ȸ��';
COMMENT ON COLUMN NOTICE.NDATE is '�������׵����';
COMMENT ON COLUMN NOTICE.ADMINNO is '�����ڹ�ȣ';


/**********************************/
/* Table Name: �������� ��� */
/**********************************/
CREATE TABLE NOTICE_REPLY(
		NREPNO                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NREPCONT                      		VARCHAR2(1000)		 NOT NULL,
		NREPNAME                      		VARCHAR2(100)		 NOT NULL,
		NREPDATE                      		DATE		 NOT NULL,
		MEMBERNO                      		NUMBER(10)		 NULL ,
		NOTICENO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (NOTICENO) REFERENCES NOTICE (NOTICENO)
);

COMMENT ON TABLE NOTICE_REPLY is '�������� ���';
COMMENT ON COLUMN NOTICE_REPLY.NREPNO is '������۹�ȣ';
COMMENT ON COLUMN NOTICE_REPLY.NREPCONT is '������۳���';
COMMENT ON COLUMN NOTICE_REPLY.NREPNAME is '��������ۼ���';
COMMENT ON COLUMN NOTICE_REPLY.NREPDATE is '������۵����';
COMMENT ON COLUMN NOTICE_REPLY.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN NOTICE_REPLY.NOTICENO is '�������׹�ȣ';


/**********************************/
/* Table Name: ���͵��� */
/**********************************/
CREATE TABLE STUDYLIST(
		STDLIST_NO                    		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		STDLIST_TITLE                 		VARCHAR2(100)		 NOT NULL,
		STDLIST_EMAIL                 		VARCHAR2(50)		 NOT NULL,
		STDLIST_TOPIC                 		VARCHAR2(50)		 NOT NULL,
		STDLIST_TIME                  		VARCHAR2(50)		 NOT NULL,
		STDLIST_AREA                  		VARCHAR2(50)		 NOT NULL,
		STDLIST_DOW                   		VARCHAR2(50)		 NOT NULL,
		STDLIST_TOT_NUM               		NUMBER(7)		 NOT NULL,
		STDLIST_CURR_NUM              		NUMBER(7)		 NOT NULL,
		STDLIST_GOODCNT               		NUMBER(10)		 NOT NULL,
		STDLIST_CONTENT               		VARCHAR2(4000)		 NOT NULL,
		STDLIST_CNT                   		NUMBER(10)		 NOT NULL,
		STDLIST_DATE                  		DATE		 NOT NULL,
		MEMBERNO                      		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE STUDYLIST is '���͵���';
COMMENT ON COLUMN STUDYLIST.STDLIST_NO is '���͵��ȣ';
COMMENT ON COLUMN STUDYLIST.STDLIST_TITLE is '���͵� ����';
COMMENT ON COLUMN STUDYLIST.STDLIST_EMAIL is '�̸���';
COMMENT ON COLUMN STUDYLIST.STDLIST_TOPIC is '�о�';
COMMENT ON COLUMN STUDYLIST.STDLIST_TIME is '�ð�';
COMMENT ON COLUMN STUDYLIST.STDLIST_AREA is '����';
COMMENT ON COLUMN STUDYLIST.STDLIST_DOW is '����';
COMMENT ON COLUMN STUDYLIST.STDLIST_TOT_NUM is '��������';
COMMENT ON COLUMN STUDYLIST.STDLIST_CURR_NUM is '�������';
COMMENT ON COLUMN STUDYLIST.STDLIST_GOODCNT is '���ƿ��';
COMMENT ON COLUMN STUDYLIST.STDLIST_CONTENT is '����(����)���';
COMMENT ON COLUMN STUDYLIST.STDLIST_CNT is '��ȸ��';
COMMENT ON COLUMN STUDYLIST.STDLIST_DATE is '�����';
COMMENT ON COLUMN STUDYLIST.MEMBERNO is 'ȸ����ȣ';


/**********************************/
/* Table Name: ���͵� ����Ʈ ��� */
/**********************************/
CREATE TABLE STD_REPLY(
		STD_REPNO                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		STD_REPCONT                   		VARCHAR2(200)		 NOT NULL,
		STD_REPDATE                   		DATE		 NOT NULL,
		STD_REPNAME                   		VARCHAR2(30)		 NOT NULL,
		MEMBERNO                      		NUMBER(10)		 NULL ,
		STDLIST_NO                    		NUMBER(10)		 NULL ,
  FOREIGN KEY (STDLIST_NO) REFERENCES STUDYLIST (STDLIST_NO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE STD_REPLY is '���͵� ����Ʈ ���';
COMMENT ON COLUMN STD_REPLY.STD_REPNO is '��� ��ȣ';
COMMENT ON COLUMN STD_REPLY.STD_REPCONT is '��� ����';
COMMENT ON COLUMN STD_REPLY.STD_REPDATE is '��� �����';
COMMENT ON COLUMN STD_REPLY.STD_REPNAME is '��� �ۼ���';
COMMENT ON COLUMN STD_REPLY.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN STD_REPLY.STDLIST_NO is '���͵��ȣ';


/**********************************/
/* Table Name: ��û ��Ȳ ���̺� */
/**********************************/
CREATE TABLE RECRUIT(
		RECRUITNO                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CONFIRM                       		VARCHAR2(10)		 NOT NULL,
		STD_AUTH                      		VARCHAR2(10)		 NOT NULL,
		STDLIST_NO                    		NUMBER(10)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (STDLIST_NO) REFERENCES STUDYLIST (STDLIST_NO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE RECRUIT is '��û ��Ȳ ���̺�';
COMMENT ON COLUMN RECRUIT.RECRUITNO is '��û��ȣ';
COMMENT ON COLUMN RECRUIT.CONFIRM is '���ο���';
COMMENT ON COLUMN RECRUIT.STD_AUTH is '����(���͵� ��)';
COMMENT ON COLUMN RECRUIT.STDLIST_NO is '���͵��ȣ';
COMMENT ON COLUMN RECRUIT.MEMBERNO is 'ȸ����ȣ';


/**********************************/
/* Table Name: ���ƿ�, �Ⱦ�� */
/**********************************/
CREATE TABLE STD_RECOM(
		STD_RECOM_NO                  		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		GOOD_CH                       		NUMBER(2)		 NOT NULL,
		STDLIST_NO                    		NUMBER(10)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (STDLIST_NO) REFERENCES STUDYLIST (STDLIST_NO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE STD_RECOM is '���ƿ�, �Ⱦ��';
COMMENT ON COLUMN STD_RECOM.STD_RECOM_NO is '��ȣ';
COMMENT ON COLUMN STD_RECOM.GOOD_CH is '���ƿ俩��';
COMMENT ON COLUMN STD_RECOM.STDLIST_NO is '���͵��ȣ';
COMMENT ON COLUMN STD_RECOM.MEMBERNO is 'ȸ����ȣ';


/**********************************/
/* Table Name: �ŷ� �Խ��� */
/**********************************/
CREATE TABLE sale(
		saleno                        		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		saletitle                     		VARCHAR2(100)		 NOT NULL,
		salecontent                   		VARCHAR2(1000)		 NOT NULL,
		salename                      		VARCHAR2(50)		 NOT NULL,
		saletname                     		VARCHAR2(100)		 NOT NULL,
		saledate                      		DATE		 NOT NULL,
		salecnt                       		NUMBER(6)		 DEFAULT 0		 NOT NULL,
		saleprice                     		NUMBER(30)		 NOT NULL,
		saleaddress                   		VARCHAR2(50)		 NULL ,
		saletel                       		VARCHAR2(50)		 NULL ,
		saleemail                     		VARCHAR2(50)		 NULL ,
		salefile                      		VARCHAR2(500)		 NULL ,
		salefstor                     		VARCHAR2(500)		 NULL ,
		saletum                       		VARCHAR2(500)		 NULL ,
		salesize                      		NUMBER(30)		 DEFAULT 0		 NOT NULL,
		saleseqno                     		NUMBER(10)		 DEFAULT 1		 NOT NULL,
		MEMBERNO                      		NUMBER(10)		 NULL ,
		ADMINNO                       		NUMBER(10)		 NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE sale is '�ŷ� �Խ���';
COMMENT ON COLUMN sale.saleno is '�Խ��� ��ȣ';
COMMENT ON COLUMN sale.saletitle is '����';
COMMENT ON COLUMN sale.salecontent is '����';
COMMENT ON COLUMN sale.salename is '�̸�';
COMMENT ON COLUMN sale.saletname is '��ǰ��';
COMMENT ON COLUMN sale.saledate is '�����';
COMMENT ON COLUMN sale.salecnt is '��ȸ��';
COMMENT ON COLUMN sale.saleprice is '����';
COMMENT ON COLUMN sale.saleaddress is '�ּ�';
COMMENT ON COLUMN sale.saletel is '�޴��� ��ȣ';
COMMENT ON COLUMN sale.saleemail is '�̸���';
COMMENT ON COLUMN sale.salefile is '����';
COMMENT ON COLUMN sale.salefstor is '���� ���ϸ�';
COMMENT ON COLUMN sale.saletum is '���� �����';
COMMENT ON COLUMN sale.salesize is '���� ũ��';
COMMENT ON COLUMN sale.saleseqno is '��� ����';
COMMENT ON COLUMN sale.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN sale.ADMINNO is '�����ڹ�ȣ';


/**********************************/
/* Table Name: ���� �Խ��� */
/**********************************/
CREATE TABLE free(
		freeno                        		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		freetitle                     		VARCHAR2(100)		 NOT NULL,
		freecontent                   		VARCHAR2(1000)		 NOT NULL,
		freename                      		VARCHAR2(50)		 NOT NULL,
		freedate                      		DATE		 NOT NULL,
		freecnt                       		NUMBER(6)		 DEFAULT 0		 NOT NULL,
		freelike                      		NUMBER(6)		 DEFAULT 0		 NOT NULL,
		MEMBERNO                      		NUMBER(10)		 NULL ,
		ADMINNO                       		NUMBER(10)		 NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE free is '���� �Խ���';
COMMENT ON COLUMN free.freeno is '�Խ��� ��ȣ';
COMMENT ON COLUMN free.freetitle is '����';
COMMENT ON COLUMN free.freecontent is '����';
COMMENT ON COLUMN free.freename is '�̸�';
COMMENT ON COLUMN free.freedate is '�����';
COMMENT ON COLUMN free.freecnt is '��ȸ��';
COMMENT ON COLUMN free.freelike is '��õ��';
COMMENT ON COLUMN free.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN free.ADMINNO is '�����ڹ�ȣ';


/**********************************/
/* Table Name: �ڷ�� */
/**********************************/
CREATE TABLE shared(
		sharedno                      		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		sharedtitle                   		VARCHAR2(100)		 NOT NULL,
		sharedcontent                 		VARCHAR2(1000)		 NOT NULL,
		sharedname                    		VARCHAR2(50)		 NOT NULL,
		sharedyoutube                 		VARCHAR2(500)		 NULL ,
		sharedseqno                   		NUMBER(10)		 NOT NULL,
		sharedfile                    		VARCHAR2(500)		 NULL ,
		sharedfstor                   		VARCHAR2(500)		 NULL ,
		sharedtum                     		VARCHAR2(500)		 NULL ,
		sharedsize                    		NUMBER(30)		 DEFAULT 0		 NOT NULL,
		shareddate                    		DATE		 NOT NULL,
		sharedcnt                     		NUMBER(6)		 DEFAULT 0		 NOT NULL,
		sharedlike                    		NUMBER(6)		 DEFAULT 0		 NOT NULL,
		MEMBERNO                      		NUMBER(10)		 NULL ,
		ADMINNO                       		NUMBER(10)		 NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE shared is '�ڷ��';
COMMENT ON COLUMN shared.sharedno is '�Խ��� ��ȣ';
COMMENT ON COLUMN shared.sharedtitle is '����';
COMMENT ON COLUMN shared.sharedcontent is '����';
COMMENT ON COLUMN shared.sharedname is '�̸�';
COMMENT ON COLUMN shared.sharedyoutube is '��Ʃ��';
COMMENT ON COLUMN shared.sharedseqno is '��� ����';
COMMENT ON COLUMN shared.sharedfile is '����';
COMMENT ON COLUMN shared.sharedfstor is '���� ���ϸ�';
COMMENT ON COLUMN shared.sharedtum is '���� �����';
COMMENT ON COLUMN shared.sharedsize is '���� ũ��';
COMMENT ON COLUMN shared.shareddate is '�����';
COMMENT ON COLUMN shared.sharedcnt is '��ȸ��';
COMMENT ON COLUMN shared.sharedlike is '��õ��';
COMMENT ON COLUMN shared.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN shared.ADMINNO is '�����ڹ�ȣ';


/**********************************/
/* Table Name: ���� �Խ��� ��� */
/**********************************/
CREATE TABLE freereply(
		freplyno                      		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		freplycontent                 		VARCHAR2(500)		 NOT NULL,
		freplyname                    		VARCHAR2(100)		 NOT NULL,
		freplydate                    		DATE		 NOT NULL,
		freplygrpno                   		NUMBER(7)		 NOT NULL,
		freplyindent                  		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		freplyansnum                  		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		seqno                         		NUMBER(5)		 DEFAULT 1		 NULL ,
		freeno                        		NUMBER(7)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
		ADMINNO                       		NUMBER(10)		 NULL ,
  FOREIGN KEY (freeno) REFERENCES free (freeno),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE freereply is '���� �Խ��� ���';
COMMENT ON COLUMN freereply.freplyno is '��� ��ȣ';
COMMENT ON COLUMN freereply.freplycontent is '��� ����';
COMMENT ON COLUMN freereply.freplyname is '��� �ۼ���';
COMMENT ON COLUMN freereply.freplydate is '��� �����';
COMMENT ON COLUMN freereply.freplygrpno is '��� �׷��ȣ';
COMMENT ON COLUMN freereply.freplyindent is '���� ����';
COMMENT ON COLUMN freereply.freplyansnum is '���� ����';
COMMENT ON COLUMN freereply.seqno is '��� ����';
COMMENT ON COLUMN freereply.freeno is '�Խ��� ��ȣ';
COMMENT ON COLUMN freereply.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN freereply.ADMINNO is '�����ڹ�ȣ';


/**********************************/
/* Table Name: �����Խ��� ���ƿ� */
/**********************************/
CREATE TABLE FREELIKE(
		GOODNO                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		GOODCHK                       		NUMBER(10)		 DEFAULT 0		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
		freeno                        		NUMBER(7)		 NULL ,
  FOREIGN KEY (freeno) REFERENCES free (freeno),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE FREELIKE is '�����Խ��� ���ƿ�';
COMMENT ON COLUMN FREELIKE.GOODNO is '���ƿ� ��ȣ';
COMMENT ON COLUMN FREELIKE.GOODCHK is '���ƿ� üũ';
COMMENT ON COLUMN FREELIKE.MEMBERNO is 'ȸ�� ��ȣ';
COMMENT ON COLUMN FREELIKE.freeno is '�Խ��� ��ȣ';


/**********************************/
/* Table Name: �ŷ� �Խ��� ��� */
/**********************************/
CREATE TABLE salereply(
		sreplyno                      		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		sreplycontent                 		VARCHAR2(500)		 NOT NULL,
		sreplyname                    		VARCHAR2(100)		 NOT NULL,
		sreplydate                    		DATE		 NOT NULL,
		sreplygrpno                   		NUMBER(7)		 NOT NULL,
		sreplyindent                  		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		sreplyansnum                  		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		seqno                         		NUMBER(5)		 DEFAULT 1		 NULL ,
		saleno                        		NUMBER(7)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
		ADMINNO                       		NUMBER(10)		 NULL ,
  FOREIGN KEY (saleno) REFERENCES sale (saleno),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE salereply is '�ŷ� �Խ��� ���';
COMMENT ON COLUMN salereply.sreplyno is '��� ��ȣ';
COMMENT ON COLUMN salereply.sreplycontent is '��� ����';
COMMENT ON COLUMN salereply.sreplyname is '��� �ۼ���';
COMMENT ON COLUMN salereply.sreplydate is '��� �����';
COMMENT ON COLUMN salereply.sreplygrpno is '��� �׷��ȣ';
COMMENT ON COLUMN salereply.sreplyindent is '���� ����';
COMMENT ON COLUMN salereply.sreplyansnum is '���� ����';
COMMENT ON COLUMN salereply.seqno is '��� ����';
COMMENT ON COLUMN salereply.saleno is '�ŷ��Խ��� ��ȣ';
COMMENT ON COLUMN salereply.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN salereply.ADMINNO is '�����ڹ�ȣ';


/**********************************/
/* Table Name: �ڷ� �Խ��� ��� */
/**********************************/
CREATE TABLE sharedreply(
		shreplyno                     		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		shreplycontent                		VARCHAR2(500)		 NOT NULL,
		shreplyname                   		VARCHAR2(100)		 NOT NULL,
		shreplydate                   		DATE		 NOT NULL,
		shreplygrpno                  		NUMBER(7)		 NOT NULL,
		shreplyindent                 		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		shreplyansnum                 		NUMBER(7)		 DEFAULT 0		 NOT NULL,
		seqno                         		NUMBER(5)		 DEFAULT 1		 NULL ,
		sharedno                      		NUMBER(7)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
		ADMINNO                       		NUMBER(10)		 NULL ,
  FOREIGN KEY (sharedno) REFERENCES shared (sharedno),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE sharedreply is '�ڷ� �Խ��� ���';
COMMENT ON COLUMN sharedreply.shreplyno is '��� ��ȣ';
COMMENT ON COLUMN sharedreply.shreplycontent is '��� ����';
COMMENT ON COLUMN sharedreply.shreplyname is '��� �ۼ���';
COMMENT ON COLUMN sharedreply.shreplydate is '��� �����';
COMMENT ON COLUMN sharedreply.shreplygrpno is '��� �׷��ȣ';
COMMENT ON COLUMN sharedreply.shreplyindent is '���� ����';
COMMENT ON COLUMN sharedreply.shreplyansnum is '���� ����';
COMMENT ON COLUMN sharedreply.seqno is '��� ����';
COMMENT ON COLUMN sharedreply.sharedno is '�Խ��� ��ȣ';
COMMENT ON COLUMN sharedreply.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN sharedreply.ADMINNO is '�����ڹ�ȣ';


/**********************************/
/* Table Name: ������ ���� �Խ��� */
/**********************************/
CREATE TABLE CONTEST(
		CONNO                         		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		CONHOST                       		VARCHAR2(200)		 NOT NULL,
		CONTITLE                      		VARCHAR2(200)		 NOT NULL,
		CONSTART                      		VARCHAR2(200)		 NOT NULL,
		CONEND                        		VARCHAR2(200)		 NOT NULL,
		CONREMAIN_S                   		NUMBER(10)		 NULL ,
		CONREMAIN_E                   		NUMBER(10)		 NULL ,
		CONCONT                       		VARCHAR2(4000)		 NOT NULL,
		CONURL                        		VARCHAR2(200)		 NULL ,
		CONYOU                        		VARCHAR2(200)		 NULL ,
		CONFILE1                      		VARCHAR2(50)		 NULL ,
		CONSIZE1                      		NUMBER(10)		 NULL ,
		CONTHUMB                      		VARCHAR2(100)		 NULL ,
		CONFILE2                      		VARCHAR2(50)		 NULL ,
		CONSIZE2                      		NUMBER(10)		 NULL ,
		CONFSTOR2                     		VARCHAR2(100)		 NULL ,
		CONFILE3                      		VARCHAR2(50)		 NULL ,
		CONSIZE3                      		NUMBER(10)		 NULL ,
		CONFSTOR3                     		VARCHAR2(100)		 NULL ,
		CONGOOD                       		NUMBER(7)		 NOT NULL,
		CONCNT                        		NUMBER(7)		 NOT NULL,
		CDATE                         		DATE		 NOT NULL,
		CONWORD                       		VARCHAR2(100)		 NULL ,
		ADMINNO                       		NUMBER(10)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE CONTEST is '������ ���� �Խ���';
COMMENT ON COLUMN CONTEST.CONNO is '������ ��ȣ';
COMMENT ON COLUMN CONTEST.CONHOST is 'CONHOST';
COMMENT ON COLUMN CONTEST.CONTITLE is '������ ����';
COMMENT ON COLUMN CONTEST.CONSTART is '������ ������';
COMMENT ON COLUMN CONTEST.CONEND is '������ ������';
COMMENT ON COLUMN CONTEST.CONREMAIN_S is '������ ���� ��¥ ���';
COMMENT ON COLUMN CONTEST.CONREMAIN_E is '������ ���� ��¥ ���';
COMMENT ON COLUMN CONTEST.CONCONT is '������ ����';
COMMENT ON COLUMN CONTEST.CONURL is '������ ���� URL';
COMMENT ON COLUMN CONTEST.CONYOU is '������ ���ÿ���(YouTube)';
COMMENT ON COLUMN CONTEST.CONFILE1 is '������ ��� ����';
COMMENT ON COLUMN CONTEST.CONSIZE1 is '������ ��� ������';
COMMENT ON COLUMN CONTEST.CONTHUMB is '������ ��� �����';
COMMENT ON COLUMN CONTEST.CONFILE2 is '������ ���� ����';
COMMENT ON COLUMN CONTEST.CONSIZE2 is '������ ���� ���� ������';
COMMENT ON COLUMN CONTEST.CONFSTOR2 is '������ ���� �������ϸ�';
COMMENT ON COLUMN CONTEST.CONFILE3 is '������ ��� ����';
COMMENT ON COLUMN CONTEST.CONSIZE3 is '������ ��� ���� ������';
COMMENT ON COLUMN CONTEST.CONFSTOR3 is '������ ��� �������ϸ�';
COMMENT ON COLUMN CONTEST.CONGOOD is '������ ��õ��';
COMMENT ON COLUMN CONTEST.CONCNT is '������ ��ȸ��';
COMMENT ON COLUMN CONTEST.CDATE is '������ �����';
COMMENT ON COLUMN CONTEST.CONWORD is '������ �˻���';
COMMENT ON COLUMN CONTEST.ADMINNO is '�����ڹ�ȣ';
COMMENT ON COLUMN CONTEST.MEMBERNO is 'ȸ����ȣ';


/**********************************/
/* Table Name: ���ƿ� */
/**********************************/
CREATE TABLE CONLIKE(
		GOODNO                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		GOODCHK                       		NUMBER(10)		 NULL ,
		CONNO                         		NUMBER(7)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (CONNO) REFERENCES CONTEST (CONNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE CONLIKE is '���ƿ�';
COMMENT ON COLUMN CONLIKE.GOODNO is '���ƿ� ��ȣ';
COMMENT ON COLUMN CONLIKE.GOODCHK is '���ƿ� üũ';
COMMENT ON COLUMN CONLIKE.CONNO is '������ ��ȣ';
COMMENT ON COLUMN CONLIKE.MEMBERNO is 'ȸ����ȣ';


/**********************************/
/* Table Name: ä������ */
/**********************************/
CREATE TABLE JOBINFO(
		JOBNO                         		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		COMNAME                       		VARCHAR2(200)		 NOT NULL,
		BOSSNAME                      		VARCHAR2(200)		 NOT NULL,
		LABCNT                        		NUMBER(7)		 NOT NULL,
		JOBSTATUS                     		VARCHAR2(200)		 NOT NULL,
		COMADDR                       		VARCHAR2(1000)		 NOT NULL,
		HOMEPAGE                      		VARCHAR2(200)		 NULL ,
		CAREER                        		VARCHAR2(200)		 NULL ,
		EDU                           		VARCHAR2(200)		 NULL ,
		JOBLOCAL                      		VARCHAR2(200)		 NOT NULL,
		JCITY                         		VARCHAR2(20)		 NOT NULL,
		JGU                           		VARCHAR2(20)		 NOT NULL,
		JDONG                         		VARCHAR2(200)		 NULL ,
		JOBPAY                        		VARCHAR2(20)		 NOT NULL,
		PAYLOW                        		NUMBER(10)		 NOT NULL,
		PAYHIGH                       		NUMBER(10)		 NOT NULL,
		JOBEMPLOY                     		VARCHAR2(200)		 NOT NULL,
		JOBWORK                       		VARCHAR2(200)		 NOT NULL,
		JOBSTART                      		VARCHAR2(200)		 NOT NULL,
		JOBEND                        		VARCHAR2(200)		 NOT NULL,
		JOBREMAIN_S                   		NUMBER(10)		 NULL ,
		JOBREMAIN_E                   		NUMBER(10)		 NULL ,
		JOBCONT                       		VARCHAR2(2000)		 NULL ,
		JOBFILE1                      		VARCHAR2(50)		 NULL ,
		JOBSIZE1                      		NUMBER(10)		 NULL ,
		JOBTHUMB                      		VARCHAR2(100)		 NULL ,
		JOBFILE2                      		VARCHAR2(50)		 NULL ,
		JOBSIZE2                      		NUMBER(10)		 NULL ,
		JOBFSTOR2                     		VARCHAR2(100)		 NULL ,
		JOBCNT                        		NUMBER(7)		 NOT NULL,
		JOBWORD                       		VARCHAR2(100)		 NULL ,
		JDATE                         		DATE		 NOT NULL,
		ADMINNO                       		NUMBER(10)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE JOBINFO is 'ä������';
COMMENT ON COLUMN JOBINFO.JOBNO is 'ä���ȣ';
COMMENT ON COLUMN JOBINFO.COMNAME is 'ȸ���';
COMMENT ON COLUMN JOBINFO.BOSSNAME is '��ǥ�ڸ�';
COMMENT ON COLUMN JOBINFO.LABCNT is '�ٷ��ڼ�';
COMMENT ON COLUMN JOBINFO.JOBSTATUS is '��������';
COMMENT ON COLUMN JOBINFO.COMADDR is 'ȸ���ּ�';
COMMENT ON COLUMN JOBINFO.HOMEPAGE is 'Ȩ������';
COMMENT ON COLUMN JOBINFO.CAREER is '���';
COMMENT ON COLUMN JOBINFO.EDU is '�з�';
COMMENT ON COLUMN JOBINFO.JOBLOCAL is '�ٹ�����';
COMMENT ON COLUMN JOBINFO.JCITY is '�ٹ����� (��)';
COMMENT ON COLUMN JOBINFO.JGU is '�ٹ����� (��, ��)';
COMMENT ON COLUMN JOBINFO.JDONG is '�ٹ����� (������ �ּ�)';
COMMENT ON COLUMN JOBINFO.JOBPAY is '�ӱ�';
COMMENT ON COLUMN JOBINFO.PAYLOW is '�����ӱ�';
COMMENT ON COLUMN JOBINFO.PAYHIGH is '�ְ��ӱ�';
COMMENT ON COLUMN JOBINFO.JOBEMPLOY is '�������';
COMMENT ON COLUMN JOBINFO.JOBWORK is '�ٹ�����';
COMMENT ON COLUMN JOBINFO.JOBSTART is '����������';
COMMENT ON COLUMN JOBINFO.JOBEND is '����������';
COMMENT ON COLUMN JOBINFO.JOBREMAIN_S is '�����ϼ�(�����ϱ���)';
COMMENT ON COLUMN JOBINFO.JOBREMAIN_E is '�����ϼ�(�����ϱ���)';
COMMENT ON COLUMN JOBINFO.JOBCONT is '�߰�����(����)';
COMMENT ON COLUMN JOBINFO.JOBFILE1 is '����ϸ�';
COMMENT ON COLUMN JOBINFO.JOBSIZE1 is '����ϻ�����';
COMMENT ON COLUMN JOBINFO.JOBTHUMB is '����Ͻ������ϸ�';
COMMENT ON COLUMN JOBINFO.JOBFILE2 is '�Ϲ����ϸ�';
COMMENT ON COLUMN JOBINFO.JOBSIZE2 is '�Ϲ����ϻ�����';
COMMENT ON COLUMN JOBINFO.JOBFSTOR2 is '�Ϲ����Ͻ�����';
COMMENT ON COLUMN JOBINFO.JOBCNT is '��ȸ��';
COMMENT ON COLUMN JOBINFO.JOBWORD is '�˻�Ű����';
COMMENT ON COLUMN JOBINFO.JDATE is '�����';
COMMENT ON COLUMN JOBINFO.ADMINNO is '�����ڹ�ȣ';
COMMENT ON COLUMN JOBINFO.MEMBERNO is 'ȸ����ȣ';


/**********************************/
/* Table Name: QnA�Խ��� */
/**********************************/
CREATE TABLE QNABOARD(
		QNANO                         		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		QNATITLE                      		VARCHAR2(200)		 NOT NULL,
		WNAME                         		VARCHAR2(100)		 NOT NULL,
		QNACONT                       		VARCHAR2(4000)		 NOT NULL,
		QNAFILE1                      		VARCHAR2(400)		 NULL ,
		QNASIZE1                      		NUMBER(10)		 NULL ,
		QNAFSTOR1                     		VARCHAR2(400)		 NULL ,
		QNAGRP                        		VARCHAR2(100)		 NOT NULL,
		QDATE                         		DATE		 NOT NULL,
		QNAPWD                        		VARCHAR2(200)		 NOT NULL,
		QNACNT                        		NUMBER(7)		 NOT NULL,
		QNAWORD                       		VARCHAR2(100)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
		ADMINNO                       		NUMBER(10)		 NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE QNABOARD is 'QnA�Խ���';
COMMENT ON COLUMN QNABOARD.QNANO is '�Խ��� ��ȣ';
COMMENT ON COLUMN QNABOARD.QNATITLE is '�Խ��� ����';
COMMENT ON COLUMN QNABOARD.WNAME is '�Խ��� �ۼ���';
COMMENT ON COLUMN QNABOARD.QNACONT is 'qna ����';
COMMENT ON COLUMN QNABOARD.QNAFILE1 is '÷�����ϸ�';
COMMENT ON COLUMN QNABOARD.QNASIZE1 is '÷������ ������';
COMMENT ON COLUMN QNABOARD.QNAFSTOR1 is '÷������ ������';
COMMENT ON COLUMN QNABOARD.QNAGRP is '�Խ��� ����';
COMMENT ON COLUMN QNABOARD.QDATE is '�Խ��� �����';
COMMENT ON COLUMN QNABOARD.QNAPWD is '�Խ��� ��й�ȣ';
COMMENT ON COLUMN QNABOARD.QNACNT is '�Խ��� ��ȸ��';
COMMENT ON COLUMN QNABOARD.QNAWORD is '�Խ��� �˻���';
COMMENT ON COLUMN QNABOARD.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN QNABOARD.ADMINNO is '�����ڹ�ȣ';


/**********************************/
/* Table Name: QnA ��� */
/**********************************/
CREATE TABLE QNAREPLY(
		QRNO                          		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		QRCONT                        		VARCHAR2(500)		 NOT NULL,
		QRNAME                        		VARCHAR2(100)		 NOT NULL,
		QRDATE                        		DATE		 NOT NULL,
		QNANO                         		NUMBER(7)		 NULL ,
		ADMINNO                       		NUMBER(10)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (QNANO) REFERENCES QNABOARD (QNANO),
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE QNAREPLY is 'QnA ���';
COMMENT ON COLUMN QNAREPLY.QRNO is 'QnA ��� ��ȣ';
COMMENT ON COLUMN QNAREPLY.QRCONT is 'QnA ��� ����';
COMMENT ON COLUMN QNAREPLY.QRNAME is 'QnA ��� �ۼ���';
COMMENT ON COLUMN QNAREPLY.QRDATE is 'QnA ��� �����';
COMMENT ON COLUMN QNAREPLY.QNANO is '�Խ��� ��ȣ';
COMMENT ON COLUMN QNAREPLY.ADMINNO is '�����ڹ�ȣ';
COMMENT ON COLUMN QNAREPLY.MEMBERNO is 'ȸ����ȣ';


/**********************************/
/* Table Name: ���� ���̺� */
/**********************************/
CREATE TABLE message(
		msg_no                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		memberno_send                 		NUMBER(10)		 NULL ,
		memberno_recv                 		NUMBER(10)		 NULL ,
		msg_title                     		VARCHAR2(300)		 NULL ,
		msg_content                   		VARCHAR2(4000)		 NULL ,
		msg_confirm                   		VARCHAR2(10)		 DEFAULT 'N'		 NULL ,
		msg_rev_date                  		DATE		 NULL ,
		msg_date                      		DATE		 NULL ,
  FOREIGN KEY (memberno_send) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (memberno_recv) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE message is '���� ���̺�';
COMMENT ON COLUMN message.msg_no is '���� ��ȣ';
COMMENT ON COLUMN message.memberno_send is '������(ȸ����ȣ)';
COMMENT ON COLUMN message.memberno_recv is '�޴���(ȸ����ȣ)';
COMMENT ON COLUMN message.msg_title is '���� ����';
COMMENT ON COLUMN message.msg_content is '���� ����';
COMMENT ON COLUMN message.msg_confirm is '���� ���� ����';
COMMENT ON COLUMN message.msg_rev_date is '���� ������';
COMMENT ON COLUMN message.msg_date is '���� ������';


/**********************************/
/* Table Name: ���� �۽� ���� ���̺� */
/**********************************/
CREATE TABLE msgsend(
		msend_no                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		memberno_send                 		NUMBER(10)		 NOT NULL,
		msg_no                        		NUMBER(10)		 NULL ,
  FOREIGN KEY (msg_no) REFERENCES message (msg_no),
  FOREIGN KEY (memberno_send) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE msgsend is '���� �۽� ���� ���̺�';
COMMENT ON COLUMN msgsend.msend_no is '�۽Ź�ȣ';
COMMENT ON COLUMN msgsend.memberno_send is '�۽��� ��ȣ';
COMMENT ON COLUMN msgsend.msg_no is '���� ��ȣ';


/**********************************/
/* Table Name: ī�װ� */
/**********************************/
CREATE TABLE category(
		cateno                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		catename                      		VARCHAR2(50)		 NOT NULL,
		cateseqno                     		NUMBER(5)		 NOT NULL,
		catevisible                   		VARCHAR2(1)		 DEFAULT 'Y'		 NOT NULL
);

COMMENT ON TABLE category is 'ī�װ�';
COMMENT ON COLUMN category.cateno is 'ī�װ���ȣ';
COMMENT ON COLUMN category.catename is 'ī�װ��̸�';
COMMENT ON COLUMN category.cateseqno is 'ī�װ���¼���';
COMMENT ON COLUMN category.catevisible is 'ī�װ���¿���';


/**********************************/
/* Table Name: �����͵�-ī�װ� ����Ʈ ���̺� */
/**********************************/
CREATE TABLE my_std_catelist(
		mylistno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		cateno                        		NUMBER(10)		 NULL ,
		STDLIST_NO                    		NUMBER(10)		 NULL ,
  FOREIGN KEY (STDLIST_NO) REFERENCES STUDYLIST (STDLIST_NO),
  FOREIGN KEY (cateno) REFERENCES category (cateno)
);

COMMENT ON TABLE my_std_catelist is '�����͵�-ī�װ� ����Ʈ ���̺�';
COMMENT ON COLUMN my_std_catelist.mylistno is '�����͵𸮽�Ʈ ���� ��ȣ';
COMMENT ON COLUMN my_std_catelist.cateno is 'ī�װ���ȣ';
COMMENT ON COLUMN my_std_catelist.STDLIST_NO is '���͵��ȣ';


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
COMMENT ON COLUMN my_pds.mylistno is '�����͵𸮽�Ʈ ���� ��ȣ';
COMMENT ON COLUMN my_pds.MEMBERNO is 'ȸ����ȣ';


/**********************************/
/* Table Name: ���� ���� ���� ���̺� */
/**********************************/
CREATE TABLE msgrecv(
		mrecv_no                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		memberno_recv                 		NUMBER(10)		 NOT NULL,
		msg_no                        		NUMBER(10)		 NULL ,
  FOREIGN KEY (msg_no) REFERENCES message (msg_no),
  FOREIGN KEY (memberno_recv) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE msgrecv is '���� ���� ���� ���̺�';
COMMENT ON COLUMN msgrecv.mrecv_no is '���Ź�ȣ';
COMMENT ON COLUMN msgrecv.memberno_recv is '������ ��ȣ';
COMMENT ON COLUMN msgrecv.msg_no is '���� ��ȣ';


/**********************************/
/* Table Name: ���������� */
/**********************************/
CREATE TABLE msg_repo(
		msg_repono                    		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		msg_no                        		NUMBER(10)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (msg_no) REFERENCES message (msg_no),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE msg_repo is '����������';
COMMENT ON COLUMN msg_repo.msg_repono is '����������ȣ';
COMMENT ON COLUMN msg_repo.msg_no is '���� ��ȣ';
COMMENT ON COLUMN msg_repo.MEMBERNO is 'ȸ����ȣ';


/**********************************/
/* Table Name: ���͵�� */
/**********************************/
CREATE TABLE ROOM(
		RONO                          		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		ADMINNO                       		NUMBER(10)		 NULL ,
		RONAME                        		VARCHAR2(100)		 NOT NULL,
		ROTEL                         		VARCHAR2(30)		 NOT NULL,
		ROSITE                        		VARCHAR2(200)		 NULL ,
		RORUNDAY                      		VARCHAR2(100)		 NULL ,
		ROMAP                         		VARCHAR2(1000)		 NOT NULL,
		ROLOCATION                    		VARCHAR2(100)		 NOT NULL,
		ROCITY                        		VARCHAR2(20)		 NOT NULL,
		ROGU                          		VARCHAR2(20)		 NOT NULL,
		RODONG                        		VARCHAR2(100)		 NULL ,
		RONALO                        		VARCHAR2(200)		 NULL ,
		ROCOST                        		VARCHAR2(50)		 NULL ,
		ROFILE1                       		VARCHAR2(800)		 NULL ,
		ROSIZE1                       		NUMBER(10)		 NULL ,
		ROTHUMB                       		VARCHAR2(800)		 NULL ,
		ROCONTENT                     		VARCHAR2(1000)		 NOT NULL,
		ROCOUNT                       		VARCHAR2(300)		 NULL ,
		ROOPTION                      		VARCHAR2(150)		 NULL ,
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE ROOM is '���͵��';
COMMENT ON COLUMN ROOM.RONO is '����Ƽ�� ��� ��ȣ';
COMMENT ON COLUMN ROOM.ADMINNO is '�����ڹ�ȣ';
COMMENT ON COLUMN ROOM.RONAME is '���͵�� ��ȣ';
COMMENT ON COLUMN ROOM.ROTEL is '���͵�� ��ȭ';
COMMENT ON COLUMN ROOM.ROSITE is '���͵�� Ȩ������';
COMMENT ON COLUMN ROOM.RORUNDAY is '���͵�� �����/�ð�';
COMMENT ON COLUMN ROOM.ROMAP is '���͵�� ����';
COMMENT ON COLUMN ROOM.ROLOCATION is '���͵�� ��ġ';
COMMENT ON COLUMN ROOM.ROCITY is 'ROCITY';
COMMENT ON COLUMN ROOM.ROGU is 'ROGU';
COMMENT ON COLUMN ROOM.RODONG is 'RODONG';
COMMENT ON COLUMN ROOM.RONALO is 'RONALO';
COMMENT ON COLUMN ROOM.ROCOST is '���͵�� ���';
COMMENT ON COLUMN ROOM.ROFILE1 is '���͵�� �̹���';
COMMENT ON COLUMN ROOM.ROSIZE1 is '���͵�� �̹���ũ��';
COMMENT ON COLUMN ROOM.ROTHUMB is '���͵�� �����';
COMMENT ON COLUMN ROOM.ROCONTENT is '���͵�� ����';
COMMENT ON COLUMN ROOM.ROCOUNT is '���͵�� ���ο�';
COMMENT ON COLUMN ROOM.ROOPTION is '���͵�� �ɼ�';


/**********************************/
/* Table Name: ���͵�� ���� */
/**********************************/
CREATE TABLE REVIEW(
		RVNO                          		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		RONO                          		NUMBER(10)		 NOT NULL,
		MEMBERNO                      		NUMBER(10)		 NULL ,
		RVMEMNAME                     		VARCHAR2(40)		 NULL ,
		RVDATE                        		DATE		 NOT NULL,
		RVGOOD                        		NUMBER(10)		 NOT NULL,
		RVCONT                        		VARCHAR2(1000)		 NOT NULL,
		RVFILE1                       		VARCHAR2(800)		 NULL ,
		RVSIZE1                       		NUMBER(10)		 NULL ,
		RVTHUMB                       		VARCHAR2(800)		 NULL ,
		RVUP                          		NUMBER(6)		 NOT NULL,
		RVCNT                         		NUMBER(6)		 NOT NULL,
  FOREIGN KEY (RONO) REFERENCES ROOM (RONO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE REVIEW is '���͵�� ����';
COMMENT ON COLUMN REVIEW.RVNO is '���͵�� ���� ��� ��ȣ';
COMMENT ON COLUMN REVIEW.RONO is '���͵�� ��� ��ȣ';
COMMENT ON COLUMN REVIEW.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN REVIEW.RVMEMNAME is 'RVMEMNAME';
COMMENT ON COLUMN REVIEW.RVDATE is '���� �����';
COMMENT ON COLUMN REVIEW.RVGOOD is '���� ����';
COMMENT ON COLUMN REVIEW.RVCONT is '���� ����';
COMMENT ON COLUMN REVIEW.RVFILE1 is '���� �̹���';
COMMENT ON COLUMN REVIEW.RVSIZE1 is '�̹��� ������';
COMMENT ON COLUMN REVIEW.RVTHUMB is '�̹��� �����';
COMMENT ON COLUMN REVIEW.RVUP is '���� ���ƿ�';
COMMENT ON COLUMN REVIEW.RVCNT is '���� ����';


/**********************************/
/* Table Name: ���ƿ� */
/**********************************/
CREATE TABLE RVLIKE(
		RVLIKENO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		RVLIKECHK                     		NUMBER(10)		 NULL ,
		RVNO                          		NUMBER(7)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (RVNO) REFERENCES REVIEW (RVNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE RVLIKE is '���ƿ�';
COMMENT ON COLUMN RVLIKE.RVLIKENO is '���ƿ� ��ȣ';
COMMENT ON COLUMN RVLIKE.RVLIKECHK is '���ƿ� üũ';
COMMENT ON COLUMN RVLIKE.RVNO is '������ ��ȣ';
COMMENT ON COLUMN RVLIKE.MEMBERNO is 'ȸ����ȣ';




