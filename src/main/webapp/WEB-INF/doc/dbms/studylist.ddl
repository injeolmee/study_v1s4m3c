
CREATE USER study IDENTIFIED BY 1234;
 
 
3. 권한 부여
    GRANT connect, resource to study;

DROP TABLE std_recom CASCADE CONSTRAINTS;
DROP TABLE std_reply CASCADE CONSTRAINTS;
DROP TABLE recruit CASCADE CONSTRAINTS;
DROP TABLE studylist CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 회원정보 */
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

COMMENT ON TABLE member is '회원정보';
COMMENT ON COLUMN member.memberno is '회원번호';
COMMENT ON COLUMN member.memid is '회원아이디';
COMMENT ON COLUMN member.mememail is '회원이메일';
COMMENT ON COLUMN member.mempasswd is '회원비밀번호';
COMMENT ON COLUMN member.memname is '회원이름';
COMMENT ON COLUMN member.memconfirm is '회원인증확인';
COMMENT ON COLUMN member.memauth is '회원권한';
COMMENT ON COLUMN member.membirth is '회원생년월일';
COMMENT ON COLUMN member.memgender is '회원성별';
COMMENT ON COLUMN member.memaddress is '회원주소';
COMMENT ON COLUMN member.memphone is '회원전화번호';
COMMENT ON COLUMN member.memsns is '회원SNS';
COMMENT ON COLUMN member.memintro is '회원소개';
COMMENT ON COLUMN member.memphoto is '회원사진파일';
COMMENT ON COLUMN member.memphoto_t is '회원사진썸네일';
COMMENT ON COLUMN member.memsize is '회원사진파일크기';
COMMENT ON COLUMN member.mbirthvb is '회원생년월일출력여부';
COMMENT ON COLUMN member.mgendervb is '회원성별출력여부';
COMMENT ON COLUMN member.maddressvb is '회원주소출력여부';
COMMENT ON COLUMN member.mphonevb is '회원전화번호출력여부';
COMMENT ON COLUMN member.msnsvb is '회원SNS출력여부';
COMMENT ON COLUMN member.mintrovb is '회원자기소개출력여부';
COMMENT ON COLUMN member.mphotovb is '회원사진출력여부';
COMMENT ON COLUMN member.memdate is '회원가입일';

select *
from member

1. INSERT    
1) 등록
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test1', 'test1@gmail.com', '123456', '지나',
'Y', 'U', '19891025', '여', '서울 강북구', '01012345678', '인스타그램', '안녕하세요', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test2', 'test2@gmail.com', '123456', '지나2',
'Y', 'B', '19891025', '남', '서울 강북구', '01012345678', '인스타그램', '안녕하세요', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test3', 'test3@gmail.com', '123456', '인절미',
'Y', 'U', '19891025', '남', '서울 강북구', '01012345678', '인스타그램', '안녕하세요', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test4', 'test4@gmail.com', '123456', '인절미2',
'Y', 'U', '19891025', '여', '서울 강북구', '01012345678', '인스타그램', '안녕하세요', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test5', 'test5@gmail.com', '123456', '찹쌀',
'Y', 'U', '19891025', '여', '서울 강북구', '01012345678', '인스타그램', '안녕하세요', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test6', 'test6@gmail.com', '123456', '찹쌀떡',
'Y', 'U', '19891025', '여', '서울 강북구', '01012345678', '인스타그램', '안녕하세요', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);

/**********************************/
/* Table Name: 스터디목록 */
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

COMMENT ON TABLE studylist is '스터디목록';
COMMENT ON COLUMN studylist.stdlist_no is '스터디번호';
COMMENT ON COLUMN studylist.stdlist_title is '스터디 제목';
COMMENT ON COLUMN studylist.stdlist_email is '이메일';
COMMENT ON COLUMN studylist.stdlist_topic is '분야';
COMMENT ON COLUMN studylist.stdlist_time is '시간';
COMMENT ON COLUMN studylist.stdlist_area is '지역';
COMMENT ON COLUMN studylist.stdlist_dow is '요일';
COMMENT ON COLUMN studylist.stdlist_tot_num is '구성원수';
COMMENT ON COLUMN studylist.stdlist_curr_num is '현재원수';
COMMENT ON COLUMN studylist.stdlist_goodcnt is '좋아요수';
COMMENT ON COLUMN studylist.stdlist_content is '내용(주제)요약';
COMMENT ON COLUMN studylist.stdlist_cnt is '조회수';
COMMENT ON COLUMN studylist.stdlist_date is '등록일';
COMMENT ON COLUMN studylist.memberno is '회원번호';


select *
from studylist

/**********************************/
/* Table Name: 신청 현황 테이블 */
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

COMMENT ON TABLE recruit is '신청 현황 테이블';
COMMENT ON COLUMN recruit.recruitno is '신청번호';
COMMENT ON COLUMN recruit.confirm is '승인여부';
COMMENT ON COLUMN recruit.std_auth is '권한(스터디 내)';
COMMENT ON COLUMN recruit.stdlist_no is '스터디번호';
COMMENT ON COLUMN recruit.memberno is '회원번호';


/**********************************/
/* Table Name: 스터디 리스트 댓글 */
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

COMMENT ON TABLE std_reply is '스터디 리스트 댓글';
COMMENT ON COLUMN std_reply.std_repno is '댓글 번호';
COMMENT ON COLUMN std_reply.std_repcont is '댓글 내용';
COMMENT ON COLUMN std_reply.std_repdate is '댓글 등록일';
COMMENT ON COLUMN std_reply.std_repname is '댓글 작성자';
COMMENT ON COLUMN std_reply.memberno is '회원번호';
COMMENT ON COLUMN std_reply.stdlist_no is '스터디번호';


/**********************************/
/* Table Name: 좋아요, 싫어요 */
/**********************************/
CREATE TABLE std_recom(
		std_recom_no                  		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		good_ch                       		NUMBER(2)		 DEFAULT 0		 NOT NULL,
		stdlist_no                    		NUMBER(10)		 NULL ,
		memberno                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (stdlist_no) REFERENCES studylist (stdlist_no),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE std_recom is '좋아요, 싫어요';
COMMENT ON COLUMN std_recom.std_recom_no is '번호';
COMMENT ON COLUMN std_recom.good_ch is '좋아요여부';
COMMENT ON COLUMN std_recom.stdlist_no is '스터디번호';
COMMENT ON COLUMN std_recom.memberno is '회원번호';


