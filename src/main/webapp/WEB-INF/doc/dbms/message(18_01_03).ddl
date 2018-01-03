DROP TABLE msg_repo CASCADE CONSTRAINTS;
DROP TABLE msgrecv CASCADE CONSTRAINTS;
DROP TABLE msgsend CASCADE CONSTRAINTS;
DROP TABLE message CASCADE CONSTRAINTS;

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
  FOREIGN KEY (memberno_send) REFERENCES member (memberno),
  FOREIGN KEY (memberno_recv) REFERENCES member (memberno)
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
  FOREIGN KEY (memberno_send) REFERENCES member (memberno),
  FOREIGN KEY (msg_no) REFERENCES message (msg_no)
);

COMMENT ON TABLE msgsend is '���� �۽� ���� ���̺�';
COMMENT ON COLUMN msgsend.msend_no is '�۽Ź�ȣ';
COMMENT ON COLUMN msgsend.memberno_send is '�۽��� ��ȣ';
COMMENT ON COLUMN msgsend.msg_no is '���� ��ȣ';


/**********************************/
/* Table Name: ���� ���� ���� ���̺� */
/**********************************/
CREATE TABLE msgrecv(
		mrecv_no                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		memberno_recv                 		NUMBER(10)		 NOT NULL,
		msg_no                        		NUMBER(10)		 NULL ,
  FOREIGN KEY (memberno_recv) REFERENCES member (memberno),
  FOREIGN KEY (msg_no) REFERENCES message (msg_no)
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
		memberno                      		NUMBER(10)		 NOT NULL,
		msg_no                        		NUMBER(10)		 NULL ,
  FOREIGN KEY (msg_no) REFERENCES message (msg_no),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE msg_repo is '����������';
COMMENT ON COLUMN msg_repo.msg_repono is '����������ȣ';
COMMENT ON COLUMN msg_repo.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN msg_repo.msg_no is '���� ��ȣ';


