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
/* Table Name: 회원정보 */
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
/* Table Name: 거래 게시판 */
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
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (saleno) REFERENCES sale (saleno),
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
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (sharedno) REFERENCES shared (sharedno),
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
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (freeno) REFERENCES free (freeno),
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
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (freeno) REFERENCES free (freeno)
);

COMMENT ON TABLE FREELIKE is '자유게시판 좋아요';
COMMENT ON COLUMN FREELIKE.GOODNO is '좋아요 번호';
COMMENT ON COLUMN FREELIKE.GOODCHK is '좋아요 체크';
COMMENT ON COLUMN FREELIKE.MEMBERNO is '회원 번호';
COMMENT ON COLUMN FREELIKE.freeno is '게시판 번호';


