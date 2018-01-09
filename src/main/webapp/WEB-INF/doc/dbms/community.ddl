DROP TABLE MEMBER;
DROP TABLE ADMIN;
DROP TABLE free;
DROP TABLE freereply;
DROP TABLE freelike;
DROP TABLE sale;
DROP TABLE salereply;
DROP TABLE shared;
DROP TABLE sharedreply;

select * from MEMBER;
select * from ADMIN;
select * from free;
select * from freereply;
select * from freelike;
select * from sale;
select * from salereply;
select * from shared;
select * from sharedreply;

/**********************************/
/* Table Name: ȸ������ */
/**********************************/
CREATE TABLE MEMBER(
		MEMBERNO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		MEMID                         		VARCHAR2(50)		 NOT NULL,
		MEMEMAIL                      		VARCHAR2(100)		 NOT NULL,
		MEMPASSWD                     		VARCHAR2(20)		 NOT NULL,
		MEMNAME                       		VARCHAR2(20)		 NOT NULL,
		MEMCONFIRM                    		VARCHAR2(1)		 NOT NULL,
		MEMAUTH                       		VARCHAR2(1)		 NOT NULL,
		MEMBIRTH                      		VARCHAR2(50)		 NOT NULL,
		MEMGENDER                     		VARCHAR2(5)		 NOT NULL,
		MEMADDRESS                    		VARCHAR2(100)		 NOT NULL,
		MEMPHONE                      		VARCHAR2(50)		 NULL ,
		MEMSNS                        		VARCHAR2(100)		 NULL ,
		MEMINTRO                      		VARCHAR2(1000)		 NULL ,
		MEMPHOTO                      		VARCHAR2(500)		 NULL ,
		MEMPHOTO_T                    		VARCHAR2(500)		 NULL ,
		MEMSIZE                       		NUMBER(30)		 NULL ,
		MBIRTHVB                      		VARCHAR2(1)		 NULL ,
		MGENDERVB                     		VARCHAR2(1)		 NULL ,
		MADDRESSVB                    		VARCHAR2(1)		 NULL ,
		MPHONEVB                      		VARCHAR2(1)		 NULL ,
		MSNSVB                        		VARCHAR2(1)		 NULL ,
		MINTROVB                      		VARCHAR2(1)		 NULL ,
		MPHOTOVB                      		VARCHAR2(1)		 NULL ,
		MEMDATE                       		DATE		 NOT NULL,
  CONSTRAINT SYS_C007836 UNIQUE (MEMID),
  CONSTRAINT SYS_C007837 UNIQUE (MEMEMAIL)
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
/* Table Name: �ŷ� �Խ��� */
/**********************************/
CREATE TABLE sale(
		saleno                        		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		saletitle                     		VARCHAR2(100)		 NOT NULL,
		salecontent                   		VARCHAR2(1000)		 NOT NULL,
		salename                      		VARCHAR2(50)		 NOT NULL,
		saletname                    		VARCHAR2(100)		 NOT NULL,
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
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (saleno) REFERENCES sale (saleno),
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
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (sharedno) REFERENCES shared (sharedno),
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
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (freeno) REFERENCES free (freeno),
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
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (freeno) REFERENCES free (freeno)
);

COMMENT ON TABLE FREELIKE is '�����Խ��� ���ƿ�';
COMMENT ON COLUMN FREELIKE.GOODNO is '���ƿ� ��ȣ';
COMMENT ON COLUMN FREELIKE.GOODCHK is '���ƿ� üũ';
COMMENT ON COLUMN FREELIKE.MEMBERNO is 'ȸ�� ��ȣ';
COMMENT ON COLUMN FREELIKE.freeno is '�Խ��� ��ȣ';


