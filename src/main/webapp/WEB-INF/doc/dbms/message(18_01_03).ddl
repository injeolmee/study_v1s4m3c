DROP TABLE msg_repo CASCADE CONSTRAINTS;
DROP TABLE msgrecv CASCADE CONSTRAINTS;
DROP TABLE msgsend CASCADE CONSTRAINTS;
DROP TABLE message CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 쪽지 테이블 */
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

COMMENT ON TABLE message is '쪽지 테이블';
COMMENT ON COLUMN message.msg_no is '쪽지 번호';
COMMENT ON COLUMN message.memberno_send is '보낸이(회원번호)';
COMMENT ON COLUMN message.memberno_recv is '받는이(회원번호)';
COMMENT ON COLUMN message.msg_title is '쪽지 제목';
COMMENT ON COLUMN message.msg_content is '쪽지 내용';
COMMENT ON COLUMN message.msg_confirm is '쪽지 수신 여부';
COMMENT ON COLUMN message.msg_rev_date is '쪽지 수신일';
COMMENT ON COLUMN message.msg_date is '쪽지 전송일';


/**********************************/
/* Table Name: 쪽지 송신 내역 테이블 */
/**********************************/
CREATE TABLE msgsend(
		msend_no                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		memberno_send                 		NUMBER(10)		 NOT NULL,
		msg_no                        		NUMBER(10)		 NULL ,
  FOREIGN KEY (memberno_send) REFERENCES member (memberno),
  FOREIGN KEY (msg_no) REFERENCES message (msg_no)
);

COMMENT ON TABLE msgsend is '쪽지 송신 내역 테이블';
COMMENT ON COLUMN msgsend.msend_no is '송신번호';
COMMENT ON COLUMN msgsend.memberno_send is '송신자 번호';
COMMENT ON COLUMN msgsend.msg_no is '쪽지 번호';


/**********************************/
/* Table Name: 쪽지 수신 내역 테이블 */
/**********************************/
CREATE TABLE msgrecv(
		mrecv_no                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		memberno_recv                 		NUMBER(10)		 NOT NULL,
		msg_no                        		NUMBER(10)		 NULL ,
  FOREIGN KEY (memberno_recv) REFERENCES member (memberno),
  FOREIGN KEY (msg_no) REFERENCES message (msg_no)
);

COMMENT ON TABLE msgrecv is '쪽지 수신 내역 테이블';
COMMENT ON COLUMN msgrecv.mrecv_no is '수신번호';
COMMENT ON COLUMN msgrecv.memberno_recv is '수신자 번호';
COMMENT ON COLUMN msgrecv.msg_no is '쪽지 번호';


/**********************************/
/* Table Name: 쪽지보관함 */
/**********************************/
CREATE TABLE msg_repo(
		msg_repono                    		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		memberno                      		NUMBER(10)		 NOT NULL,
		msg_no                        		NUMBER(10)		 NULL ,
  FOREIGN KEY (msg_no) REFERENCES message (msg_no),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE msg_repo is '쪽지보관함';
COMMENT ON COLUMN msg_repo.msg_repono is '쪽지보관번호';
COMMENT ON COLUMN msg_repo.memberno is '회원번호';
COMMENT ON COLUMN msg_repo.msg_no is '쪽지 번호';


