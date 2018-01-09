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
/* Table Name: 관리자 */
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

COMMENT ON TABLE ADMIN is '관리자';
COMMENT ON COLUMN ADMIN.ADMINNO is '관리자번호';
COMMENT ON COLUMN ADMIN.ADMID is '관리자아이디';
COMMENT ON COLUMN ADMIN.ADMEMAIL is '관리자이메일';
COMMENT ON COLUMN ADMIN.ADMPASSWD is '관리자비밀번호';
COMMENT ON COLUMN ADMIN.ADMNAME is '관리자이름';
COMMENT ON COLUMN ADMIN.ADMCONFIRM is '관리자인증확인';
COMMENT ON COLUMN ADMIN.ADMAUTH is '관리자권한';
COMMENT ON COLUMN ADMIN.ADMDATE is '관리자가입일';


/**********************************/
/* Table Name: VISIT */
/**********************************/
CREATE TABLE VISIT(
		VDATE                         		DATE		 NOT NULL
);

COMMENT ON TABLE VISIT is 'VISIT';
COMMENT ON COLUMN VISIT.VDATE is 'VDATE';


/**********************************/
/* Table Name: 회원정보 */
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

COMMENT ON TABLE MEMBER is '회원정보';
COMMENT ON COLUMN MEMBER.MEMBERNO is '회원번호';
COMMENT ON COLUMN MEMBER.MEMID is '회원아이디';
COMMENT ON COLUMN MEMBER.MEMEMAIL is '회원이메일';
COMMENT ON COLUMN MEMBER.MEMPASSWD is '회원비밀번호';
COMMENT ON COLUMN MEMBER.MEMNAME is '회원이름';
COMMENT ON COLUMN MEMBER.MEMCONFIRM is '회원인증확인';
COMMENT ON COLUMN MEMBER.MEMAUTH is '회원권한';
COMMENT ON COLUMN MEMBER.MEMBIRTH is '회원생년월일';
COMMENT ON COLUMN MEMBER.MEMGENDER is '회원성별';
COMMENT ON COLUMN MEMBER.MEMADDRESS is '회원주소';
COMMENT ON COLUMN MEMBER.MEMPHONE is '회원전화번호';
COMMENT ON COLUMN MEMBER.MEMSNS is '회원SNS';
COMMENT ON COLUMN MEMBER.MEMINTRO is '회원소개';
COMMENT ON COLUMN MEMBER.MEMPHOTO is '회원사진파일';
COMMENT ON COLUMN MEMBER.MEMPHOTO_T is '회원사진썸네일';
COMMENT ON COLUMN MEMBER.MEMSIZE is '회원사진파일크기';
COMMENT ON COLUMN MEMBER.MBIRTHVB is '회원생년월일출력여부';
COMMENT ON COLUMN MEMBER.MGENDERVB is '회원성별출력여부';
COMMENT ON COLUMN MEMBER.MADDRESSVB is '회원주소출력여부';
COMMENT ON COLUMN MEMBER.MPHONEVB is '회원전화번호출력여부';
COMMENT ON COLUMN MEMBER.MSNSVB is '회원SNS출력여부';
COMMENT ON COLUMN MEMBER.MINTROVB is '회원자기소개출력여부';
COMMENT ON COLUMN MEMBER.MPHOTOVB is '회원사진출력여부';
COMMENT ON COLUMN MEMBER.MEMDATE is '회원가입일';


/**********************************/
/* Table Name: 관리자 공지사항 게시판 */
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

COMMENT ON TABLE NOTICE is '관리자 공지사항 게시판';
COMMENT ON COLUMN NOTICE.NOTICENO is '공지사항번호';
COMMENT ON COLUMN NOTICE.NTITLE is '공지사항제목';
COMMENT ON COLUMN NOTICE.NNAME is '공지사항작성자';
COMMENT ON COLUMN NOTICE.NCONTENT is '공지사항내용';
COMMENT ON COLUMN NOTICE.NSEQNO is '공지사항출력순서';
COMMENT ON COLUMN NOTICE.NCNT is '공지사항조회수';
COMMENT ON COLUMN NOTICE.NDATE is '공지사항등록일';
COMMENT ON COLUMN NOTICE.ADMINNO is '관리자번호';


/**********************************/
/* Table Name: 공지사항 댓글 */
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

COMMENT ON TABLE NOTICE_REPLY is '공지사항 댓글';
COMMENT ON COLUMN NOTICE_REPLY.NREPNO is '공지댓글번호';
COMMENT ON COLUMN NOTICE_REPLY.NREPCONT is '공지댓글내용';
COMMENT ON COLUMN NOTICE_REPLY.NREPNAME is '공지댓글작성자';
COMMENT ON COLUMN NOTICE_REPLY.NREPDATE is '공지댓글등록일';
COMMENT ON COLUMN NOTICE_REPLY.MEMBERNO is '회원번호';
COMMENT ON COLUMN NOTICE_REPLY.NOTICENO is '공지사항번호';


/**********************************/
/* Table Name: 스터디목록 */
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

COMMENT ON TABLE STUDYLIST is '스터디목록';
COMMENT ON COLUMN STUDYLIST.STDLIST_NO is '스터디번호';
COMMENT ON COLUMN STUDYLIST.STDLIST_TITLE is '스터디 제목';
COMMENT ON COLUMN STUDYLIST.STDLIST_EMAIL is '이메일';
COMMENT ON COLUMN STUDYLIST.STDLIST_TOPIC is '분야';
COMMENT ON COLUMN STUDYLIST.STDLIST_TIME is '시간';
COMMENT ON COLUMN STUDYLIST.STDLIST_AREA is '지역';
COMMENT ON COLUMN STUDYLIST.STDLIST_DOW is '요일';
COMMENT ON COLUMN STUDYLIST.STDLIST_TOT_NUM is '구성원수';
COMMENT ON COLUMN STUDYLIST.STDLIST_CURR_NUM is '현재원수';
COMMENT ON COLUMN STUDYLIST.STDLIST_GOODCNT is '좋아요수';
COMMENT ON COLUMN STUDYLIST.STDLIST_CONTENT is '내용(주제)요약';
COMMENT ON COLUMN STUDYLIST.STDLIST_CNT is '조회수';
COMMENT ON COLUMN STUDYLIST.STDLIST_DATE is '등록일';
COMMENT ON COLUMN STUDYLIST.MEMBERNO is '회원번호';


/**********************************/
/* Table Name: 스터디 리스트 댓글 */
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

COMMENT ON TABLE STD_REPLY is '스터디 리스트 댓글';
COMMENT ON COLUMN STD_REPLY.STD_REPNO is '댓글 번호';
COMMENT ON COLUMN STD_REPLY.STD_REPCONT is '댓글 내용';
COMMENT ON COLUMN STD_REPLY.STD_REPDATE is '댓글 등록일';
COMMENT ON COLUMN STD_REPLY.STD_REPNAME is '댓글 작성자';
COMMENT ON COLUMN STD_REPLY.MEMBERNO is '회원번호';
COMMENT ON COLUMN STD_REPLY.STDLIST_NO is '스터디번호';


/**********************************/
/* Table Name: 신청 현황 테이블 */
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

COMMENT ON TABLE RECRUIT is '신청 현황 테이블';
COMMENT ON COLUMN RECRUIT.RECRUITNO is '신청번호';
COMMENT ON COLUMN RECRUIT.CONFIRM is '승인여부';
COMMENT ON COLUMN RECRUIT.STD_AUTH is '권한(스터디 내)';
COMMENT ON COLUMN RECRUIT.STDLIST_NO is '스터디번호';
COMMENT ON COLUMN RECRUIT.MEMBERNO is '회원번호';


/**********************************/
/* Table Name: 좋아요, 싫어요 */
/**********************************/
CREATE TABLE STD_RECOM(
		STD_RECOM_NO                  		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		GOOD_CH                       		NUMBER(2)		 NOT NULL,
		STDLIST_NO                    		NUMBER(10)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (STDLIST_NO) REFERENCES STUDYLIST (STDLIST_NO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE STD_RECOM is '좋아요, 싫어요';
COMMENT ON COLUMN STD_RECOM.STD_RECOM_NO is '번호';
COMMENT ON COLUMN STD_RECOM.GOOD_CH is '좋아요여부';
COMMENT ON COLUMN STD_RECOM.STDLIST_NO is '스터디번호';
COMMENT ON COLUMN STD_RECOM.MEMBERNO is '회원번호';


/**********************************/
/* Table Name: 거래 게시판 */
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

COMMENT ON TABLE sale is '거래 게시판';
COMMENT ON COLUMN sale.saleno is '게시판 번호';
COMMENT ON COLUMN sale.saletitle is '제목';
COMMENT ON COLUMN sale.salecontent is '내용';
COMMENT ON COLUMN sale.salename is '이름';
COMMENT ON COLUMN sale.saletname is '상품명';
COMMENT ON COLUMN sale.saledate is '등록일';
COMMENT ON COLUMN sale.salecnt is '조회수';
COMMENT ON COLUMN sale.saleprice is '가격';
COMMENT ON COLUMN sale.saleaddress is '주소';
COMMENT ON COLUMN sale.saletel is '휴대폰 번호';
COMMENT ON COLUMN sale.saleemail is '이메일';
COMMENT ON COLUMN sale.salefile is '파일';
COMMENT ON COLUMN sale.salefstor is '실제 파일명';
COMMENT ON COLUMN sale.saletum is '파일 썸네일';
COMMENT ON COLUMN sale.salesize is '파일 크기';
COMMENT ON COLUMN sale.saleseqno is '출력 여부';
COMMENT ON COLUMN sale.MEMBERNO is '회원번호';
COMMENT ON COLUMN sale.ADMINNO is '관리자번호';


/**********************************/
/* Table Name: 자유 게시판 */
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

COMMENT ON TABLE free is '자유 게시판';
COMMENT ON COLUMN free.freeno is '게시판 번호';
COMMENT ON COLUMN free.freetitle is '제목';
COMMENT ON COLUMN free.freecontent is '내용';
COMMENT ON COLUMN free.freename is '이름';
COMMENT ON COLUMN free.freedate is '등록일';
COMMENT ON COLUMN free.freecnt is '조회수';
COMMENT ON COLUMN free.freelike is '추천수';
COMMENT ON COLUMN free.MEMBERNO is '회원번호';
COMMENT ON COLUMN free.ADMINNO is '관리자번호';


/**********************************/
/* Table Name: 자료실 */
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

COMMENT ON TABLE shared is '자료실';
COMMENT ON COLUMN shared.sharedno is '게시판 번호';
COMMENT ON COLUMN shared.sharedtitle is '제목';
COMMENT ON COLUMN shared.sharedcontent is '내용';
COMMENT ON COLUMN shared.sharedname is '이름';
COMMENT ON COLUMN shared.sharedyoutube is '유튜브';
COMMENT ON COLUMN shared.sharedseqno is '출력 순서';
COMMENT ON COLUMN shared.sharedfile is '파일';
COMMENT ON COLUMN shared.sharedfstor is '실제 파일명';
COMMENT ON COLUMN shared.sharedtum is '파일 썸네일';
COMMENT ON COLUMN shared.sharedsize is '파일 크기';
COMMENT ON COLUMN shared.shareddate is '등록일';
COMMENT ON COLUMN shared.sharedcnt is '조회수';
COMMENT ON COLUMN shared.sharedlike is '추천수';
COMMENT ON COLUMN shared.MEMBERNO is '회원번호';
COMMENT ON COLUMN shared.ADMINNO is '관리자번호';


/**********************************/
/* Table Name: 자유 게시판 댓글 */
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

COMMENT ON TABLE freereply is '자유 게시판 댓글';
COMMENT ON COLUMN freereply.freplyno is '댓글 번호';
COMMENT ON COLUMN freereply.freplycontent is '댓글 내용';
COMMENT ON COLUMN freereply.freplyname is '댓글 작성자';
COMMENT ON COLUMN freereply.freplydate is '댓글 등록일';
COMMENT ON COLUMN freereply.freplygrpno is '댓글 그룹번호';
COMMENT ON COLUMN freereply.freplyindent is '대댓글 차수';
COMMENT ON COLUMN freereply.freplyansnum is '대댓글 순서';
COMMENT ON COLUMN freereply.seqno is '출력 권한';
COMMENT ON COLUMN freereply.freeno is '게시판 번호';
COMMENT ON COLUMN freereply.MEMBERNO is '회원번호';
COMMENT ON COLUMN freereply.ADMINNO is '관리자번호';


/**********************************/
/* Table Name: 자유게시판 좋아요 */
/**********************************/
CREATE TABLE FREELIKE(
		GOODNO                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		GOODCHK                       		NUMBER(10)		 DEFAULT 0		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
		freeno                        		NUMBER(7)		 NULL ,
  FOREIGN KEY (freeno) REFERENCES free (freeno),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE FREELIKE is '자유게시판 좋아요';
COMMENT ON COLUMN FREELIKE.GOODNO is '좋아요 번호';
COMMENT ON COLUMN FREELIKE.GOODCHK is '좋아요 체크';
COMMENT ON COLUMN FREELIKE.MEMBERNO is '회원 번호';
COMMENT ON COLUMN FREELIKE.freeno is '게시판 번호';


/**********************************/
/* Table Name: 거래 게시판 댓글 */
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

COMMENT ON TABLE salereply is '거래 게시판 댓글';
COMMENT ON COLUMN salereply.sreplyno is '댓글 번호';
COMMENT ON COLUMN salereply.sreplycontent is '댓글 내용';
COMMENT ON COLUMN salereply.sreplyname is '댓글 작성자';
COMMENT ON COLUMN salereply.sreplydate is '댓글 등록일';
COMMENT ON COLUMN salereply.sreplygrpno is '댓글 그룹번호';
COMMENT ON COLUMN salereply.sreplyindent is '대댓글 차수';
COMMENT ON COLUMN salereply.sreplyansnum is '대댓글 순서';
COMMENT ON COLUMN salereply.seqno is '출력 권한';
COMMENT ON COLUMN salereply.saleno is '거래게시판 번호';
COMMENT ON COLUMN salereply.MEMBERNO is '회원번호';
COMMENT ON COLUMN salereply.ADMINNO is '관리자번호';


/**********************************/
/* Table Name: 자료 게시판 댓글 */
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

COMMENT ON TABLE sharedreply is '자료 게시판 댓글';
COMMENT ON COLUMN sharedreply.shreplyno is '댓글 번호';
COMMENT ON COLUMN sharedreply.shreplycontent is '댓글 내용';
COMMENT ON COLUMN sharedreply.shreplyname is '댓글 작성자';
COMMENT ON COLUMN sharedreply.shreplydate is '댓글 등록일';
COMMENT ON COLUMN sharedreply.shreplygrpno is '댓글 그룹번호';
COMMENT ON COLUMN sharedreply.shreplyindent is '대댓글 차수';
COMMENT ON COLUMN sharedreply.shreplyansnum is '대댓글 순서';
COMMENT ON COLUMN sharedreply.seqno is '출력 권한';
COMMENT ON COLUMN sharedreply.sharedno is '게시판 번호';
COMMENT ON COLUMN sharedreply.MEMBERNO is '회원번호';
COMMENT ON COLUMN sharedreply.ADMINNO is '관리자번호';


/**********************************/
/* Table Name: 공모전 정보 게시판 */
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

COMMENT ON TABLE CONTEST is '공모전 정보 게시판';
COMMENT ON COLUMN CONTEST.CONNO is '공모전 번호';
COMMENT ON COLUMN CONTEST.CONHOST is 'CONHOST';
COMMENT ON COLUMN CONTEST.CONTITLE is '공모전 제목';
COMMENT ON COLUMN CONTEST.CONSTART is '공모전 시작일';
COMMENT ON COLUMN CONTEST.CONEND is '공모전 종료일';
COMMENT ON COLUMN CONTEST.CONREMAIN_S is '시작일 기준 날짜 계산';
COMMENT ON COLUMN CONTEST.CONREMAIN_E is '종료일 기준 날짜 계산';
COMMENT ON COLUMN CONTEST.CONCONT is '공모전 내용';
COMMENT ON COLUMN CONTEST.CONURL is '공모전 관련 URL';
COMMENT ON COLUMN CONTEST.CONYOU is '공모전 관련영상(YouTube)';
COMMENT ON COLUMN CONTEST.CONFILE1 is '공모전 배너 파일';
COMMENT ON COLUMN CONTEST.CONSIZE1 is '공모전 배너 사이즈';
COMMENT ON COLUMN CONTEST.CONTHUMB is '공모전 배너 썸네일';
COMMENT ON COLUMN CONTEST.CONFILE2 is '공모전 내용 파일';
COMMENT ON COLUMN CONTEST.CONSIZE2 is '공모전 내용 파일 사이즈';
COMMENT ON COLUMN CONTEST.CONFSTOR2 is '공모전 내용 실제파일명';
COMMENT ON COLUMN CONTEST.CONFILE3 is '공모전 양식 파일';
COMMENT ON COLUMN CONTEST.CONSIZE3 is '공모전 양식 파일 사이즈';
COMMENT ON COLUMN CONTEST.CONFSTOR3 is '공모전 양식 실제파일명';
COMMENT ON COLUMN CONTEST.CONGOOD is '공모전 추천수';
COMMENT ON COLUMN CONTEST.CONCNT is '공모전 조회수';
COMMENT ON COLUMN CONTEST.CDATE is '공모전 등록일';
COMMENT ON COLUMN CONTEST.CONWORD is '공모전 검색어';
COMMENT ON COLUMN CONTEST.ADMINNO is '관리자번호';
COMMENT ON COLUMN CONTEST.MEMBERNO is '회원번호';


/**********************************/
/* Table Name: 좋아요 */
/**********************************/
CREATE TABLE CONLIKE(
		GOODNO                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		GOODCHK                       		NUMBER(10)		 NULL ,
		CONNO                         		NUMBER(7)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (CONNO) REFERENCES CONTEST (CONNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE CONLIKE is '좋아요';
COMMENT ON COLUMN CONLIKE.GOODNO is '좋아요 번호';
COMMENT ON COLUMN CONLIKE.GOODCHK is '좋아요 체크';
COMMENT ON COLUMN CONLIKE.CONNO is '공모전 번호';
COMMENT ON COLUMN CONLIKE.MEMBERNO is '회원번호';


/**********************************/
/* Table Name: 채용정보 */
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

COMMENT ON TABLE JOBINFO is '채용정보';
COMMENT ON COLUMN JOBINFO.JOBNO is '채용번호';
COMMENT ON COLUMN JOBINFO.COMNAME is '회사명';
COMMENT ON COLUMN JOBINFO.BOSSNAME is '대표자명';
COMMENT ON COLUMN JOBINFO.LABCNT is '근로자수';
COMMENT ON COLUMN JOBINFO.JOBSTATUS is '직무내용';
COMMENT ON COLUMN JOBINFO.COMADDR is '회사주소';
COMMENT ON COLUMN JOBINFO.HOMEPAGE is '홈페이지';
COMMENT ON COLUMN JOBINFO.CAREER is '경력';
COMMENT ON COLUMN JOBINFO.EDU is '학력';
COMMENT ON COLUMN JOBINFO.JOBLOCAL is '근무지역';
COMMENT ON COLUMN JOBINFO.JCITY is '근무지역 (시)';
COMMENT ON COLUMN JOBINFO.JGU is '근무지역 (구, 동)';
COMMENT ON COLUMN JOBINFO.JDONG is '근무지역 (나머지 주소)';
COMMENT ON COLUMN JOBINFO.JOBPAY is '임금';
COMMENT ON COLUMN JOBINFO.PAYLOW is '최저임금';
COMMENT ON COLUMN JOBINFO.PAYHIGH is '최고임금';
COMMENT ON COLUMN JOBINFO.JOBEMPLOY is '고용형태';
COMMENT ON COLUMN JOBINFO.JOBWORK is '근무형태';
COMMENT ON COLUMN JOBINFO.JOBSTART is '접수시작일';
COMMENT ON COLUMN JOBINFO.JOBEND is '접수마감일';
COMMENT ON COLUMN JOBINFO.JOBREMAIN_S is '남은일수(시작일기준)';
COMMENT ON COLUMN JOBINFO.JOBREMAIN_E is '남은일수(종료일기준)';
COMMENT ON COLUMN JOBINFO.JOBCONT is '추가사항(내용)';
COMMENT ON COLUMN JOBINFO.JOBFILE1 is '썸네일명';
COMMENT ON COLUMN JOBINFO.JOBSIZE1 is '썸네일사이즈';
COMMENT ON COLUMN JOBINFO.JOBTHUMB is '썸네일실제파일명';
COMMENT ON COLUMN JOBINFO.JOBFILE2 is '일반파일명';
COMMENT ON COLUMN JOBINFO.JOBSIZE2 is '일반파일사이즈';
COMMENT ON COLUMN JOBINFO.JOBFSTOR2 is '일반파일실제명';
COMMENT ON COLUMN JOBINFO.JOBCNT is '조회수';
COMMENT ON COLUMN JOBINFO.JOBWORD is '검색키워드';
COMMENT ON COLUMN JOBINFO.JDATE is '등록일';
COMMENT ON COLUMN JOBINFO.ADMINNO is '관리자번호';
COMMENT ON COLUMN JOBINFO.MEMBERNO is '회원번호';


/**********************************/
/* Table Name: QnA게시판 */
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

COMMENT ON TABLE QNABOARD is 'QnA게시판';
COMMENT ON COLUMN QNABOARD.QNANO is '게시판 번호';
COMMENT ON COLUMN QNABOARD.QNATITLE is '게시판 제목';
COMMENT ON COLUMN QNABOARD.WNAME is '게시판 작성자';
COMMENT ON COLUMN QNABOARD.QNACONT is 'qna 내용';
COMMENT ON COLUMN QNABOARD.QNAFILE1 is '첨부파일명';
COMMENT ON COLUMN QNABOARD.QNASIZE1 is '첨부파일 사이즈';
COMMENT ON COLUMN QNABOARD.QNAFSTOR1 is '첨부파일 실제명';
COMMENT ON COLUMN QNABOARD.QNAGRP is '게시판 종류';
COMMENT ON COLUMN QNABOARD.QDATE is '게시판 등록일';
COMMENT ON COLUMN QNABOARD.QNAPWD is '게시판 비밀번호';
COMMENT ON COLUMN QNABOARD.QNACNT is '게시판 조회수';
COMMENT ON COLUMN QNABOARD.QNAWORD is '게시판 검색어';
COMMENT ON COLUMN QNABOARD.MEMBERNO is '회원번호';
COMMENT ON COLUMN QNABOARD.ADMINNO is '관리자번호';


/**********************************/
/* Table Name: QnA 댓글 */
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

COMMENT ON TABLE QNAREPLY is 'QnA 댓글';
COMMENT ON COLUMN QNAREPLY.QRNO is 'QnA 댓글 번호';
COMMENT ON COLUMN QNAREPLY.QRCONT is 'QnA 댓글 내용';
COMMENT ON COLUMN QNAREPLY.QRNAME is 'QnA 댓글 작성자';
COMMENT ON COLUMN QNAREPLY.QRDATE is 'QnA 댓글 등록일';
COMMENT ON COLUMN QNAREPLY.QNANO is '게시판 번호';
COMMENT ON COLUMN QNAREPLY.ADMINNO is '관리자번호';
COMMENT ON COLUMN QNAREPLY.MEMBERNO is '회원번호';


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
  FOREIGN KEY (memberno_send) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (memberno_recv) REFERENCES MEMBER (MEMBERNO)
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
  FOREIGN KEY (msg_no) REFERENCES message (msg_no),
  FOREIGN KEY (memberno_send) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE msgsend is '쪽지 송신 내역 테이블';
COMMENT ON COLUMN msgsend.msend_no is '송신번호';
COMMENT ON COLUMN msgsend.memberno_send is '송신자 번호';
COMMENT ON COLUMN msgsend.msg_no is '쪽지 번호';


/**********************************/
/* Table Name: 카테고리 */
/**********************************/
CREATE TABLE category(
		cateno                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		catename                      		VARCHAR2(50)		 NOT NULL,
		cateseqno                     		NUMBER(5)		 NOT NULL,
		catevisible                   		VARCHAR2(1)		 DEFAULT 'Y'		 NOT NULL
);

COMMENT ON TABLE category is '카테고리';
COMMENT ON COLUMN category.cateno is '카테고리번호';
COMMENT ON COLUMN category.catename is '카테고리이름';
COMMENT ON COLUMN category.cateseqno is '카테고리출력순서';
COMMENT ON COLUMN category.catevisible is '카테고리출력여부';


/**********************************/
/* Table Name: 내스터디-카테고리 리스트 테이블 */
/**********************************/
CREATE TABLE my_std_catelist(
		mylistno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		cateno                        		NUMBER(10)		 NULL ,
		STDLIST_NO                    		NUMBER(10)		 NULL ,
  FOREIGN KEY (STDLIST_NO) REFERENCES STUDYLIST (STDLIST_NO),
  FOREIGN KEY (cateno) REFERENCES category (cateno)
);

COMMENT ON TABLE my_std_catelist is '내스터디-카테고리 리스트 테이블';
COMMENT ON COLUMN my_std_catelist.mylistno is '내스터디리스트 구분 번호';
COMMENT ON COLUMN my_std_catelist.cateno is '카테고리번호';
COMMENT ON COLUMN my_std_catelist.STDLIST_NO is '스터디번호';


/**********************************/
/* Table Name: 내 스터디 글 목록 */
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

COMMENT ON TABLE my_pds is '내 스터디 글 목록';
COMMENT ON COLUMN my_pds.pdsno is '글 번호';
COMMENT ON COLUMN my_pds.pdstitle is '글 제목';
COMMENT ON COLUMN my_pds.pdspasswd is '글 비밀번호';
COMMENT ON COLUMN my_pds.pdscontent is '글 내용';
COMMENT ON COLUMN my_pds.pdsfile1 is '글 첨부파일명';
COMMENT ON COLUMN my_pds.pdsfilesize is '글 사진 사이즈';
COMMENT ON COLUMN my_pds.pdsthumb is '글 사진 thumb';
COMMENT ON COLUMN my_pds.pdsword is '글 검색어';
COMMENT ON COLUMN my_pds.pdslike is '글 추천수';
COMMENT ON COLUMN my_pds.pdscnt is '글 조회수';
COMMENT ON COLUMN my_pds.pdsdate is '글 작성일';
COMMENT ON COLUMN my_pds.mylistno is '내스터디리스트 구분 번호';
COMMENT ON COLUMN my_pds.MEMBERNO is '회원번호';


/**********************************/
/* Table Name: 쪽지 수신 내역 테이블 */
/**********************************/
CREATE TABLE msgrecv(
		mrecv_no                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		memberno_recv                 		NUMBER(10)		 NOT NULL,
		msg_no                        		NUMBER(10)		 NULL ,
  FOREIGN KEY (msg_no) REFERENCES message (msg_no),
  FOREIGN KEY (memberno_recv) REFERENCES MEMBER (MEMBERNO)
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
		msg_no                        		NUMBER(10)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (msg_no) REFERENCES message (msg_no),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE msg_repo is '쪽지보관함';
COMMENT ON COLUMN msg_repo.msg_repono is '쪽지보관번호';
COMMENT ON COLUMN msg_repo.msg_no is '쪽지 번호';
COMMENT ON COLUMN msg_repo.MEMBERNO is '회원번호';


/**********************************/
/* Table Name: 스터디룸 */
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

COMMENT ON TABLE ROOM is '스터디룸';
COMMENT ON COLUMN ROOM.RONO is '스터티룸 등록 번호';
COMMENT ON COLUMN ROOM.ADMINNO is '관리자번호';
COMMENT ON COLUMN ROOM.RONAME is '스터디룸 상호';
COMMENT ON COLUMN ROOM.ROTEL is '스터디룸 전화';
COMMENT ON COLUMN ROOM.ROSITE is '스터디룸 홈페이지';
COMMENT ON COLUMN ROOM.RORUNDAY is '스터디룸 운영요일/시간';
COMMENT ON COLUMN ROOM.ROMAP is '스터디룸 지도';
COMMENT ON COLUMN ROOM.ROLOCATION is '스터디룸 위치';
COMMENT ON COLUMN ROOM.ROCITY is 'ROCITY';
COMMENT ON COLUMN ROOM.ROGU is 'ROGU';
COMMENT ON COLUMN ROOM.RODONG is 'RODONG';
COMMENT ON COLUMN ROOM.RONALO is 'RONALO';
COMMENT ON COLUMN ROOM.ROCOST is '스터디룸 비용';
COMMENT ON COLUMN ROOM.ROFILE1 is '스터디룸 이미지';
COMMENT ON COLUMN ROOM.ROSIZE1 is '스터디룸 이미지크기';
COMMENT ON COLUMN ROOM.ROTHUMB is '스터디룸 썸네일';
COMMENT ON COLUMN ROOM.ROCONTENT is '스터디룸 내용';
COMMENT ON COLUMN ROOM.ROCOUNT is '스터디룸 룸인원';
COMMENT ON COLUMN ROOM.ROOPTION is '스터디룸 옵션';


/**********************************/
/* Table Name: 스터디룸 리뷰 */
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

COMMENT ON TABLE REVIEW is '스터디룸 리뷰';
COMMENT ON COLUMN REVIEW.RVNO is '스터디룸 리뷰 등록 번호';
COMMENT ON COLUMN REVIEW.RONO is '스터디룸 등록 번호';
COMMENT ON COLUMN REVIEW.MEMBERNO is '회원번호';
COMMENT ON COLUMN REVIEW.RVMEMNAME is 'RVMEMNAME';
COMMENT ON COLUMN REVIEW.RVDATE is '리뷰 등록일';
COMMENT ON COLUMN REVIEW.RVGOOD is '리뷰 평점';
COMMENT ON COLUMN REVIEW.RVCONT is '리뷰 내용';
COMMENT ON COLUMN REVIEW.RVFILE1 is '리뷰 이미지';
COMMENT ON COLUMN REVIEW.RVSIZE1 is '이미지 사이즈';
COMMENT ON COLUMN REVIEW.RVTHUMB is '이미지 썸네일';
COMMENT ON COLUMN REVIEW.RVUP is '리뷰 좋아요';
COMMENT ON COLUMN REVIEW.RVCNT is '리뷰 갯수';


/**********************************/
/* Table Name: 좋아요 */
/**********************************/
CREATE TABLE RVLIKE(
		RVLIKENO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		RVLIKECHK                     		NUMBER(10)		 NULL ,
		RVNO                          		NUMBER(7)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (RVNO) REFERENCES REVIEW (RVNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE RVLIKE is '좋아요';
COMMENT ON COLUMN RVLIKE.RVLIKENO is '좋아요 번호';
COMMENT ON COLUMN RVLIKE.RVLIKECHK is '좋아요 체크';
COMMENT ON COLUMN RVLIKE.RVNO is '공모전 번호';
COMMENT ON COLUMN RVLIKE.MEMBERNO is '회원번호';




