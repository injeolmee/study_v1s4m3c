DROP TABLE sale CASCADE CONSTRAINTS;
DROP TABLE free CASCADE CONSTRAINTS;
DROP TABLE shared CASCADE CONSTRAINTS;
DROP TABLE admin;
DROP TABLE member;

/**********************************/
/* Table Name: 거래 게시판 */
/**********************************/
CREATE TABLE sale(
    saleno                            NUMBER(7)    NOT NULL,
    saletitle                         VARCHAR2(100)    NOT NULL,
    salecontent                       VARCHAR2(1000)     NOT NULL,
    salename                          VARCHAR2(50)     NOT NULL,
    saletname                        VARCHAR2(100)    NOT NULL,
    saledate                          DATE     NOT NULL,
    salecnt                           NUMBER(6)    DEFAULT 0     NOT NULL,
    saleprice                         NUMBER(30)     NOT NULL,
    saleaddress                       VARCHAR2(50)     NULL ,
    saletel                           VARCHAR2(50)     NULL ,
    saleemail                         VARCHAR2(50)     NULL ,
    salefile                          VARCHAR2(500)    NULL ,
    salefstor                         VARCHAR2(500)    NULL ,
    saletum                           VARCHAR2(500)    NULL ,
    salesize                          NUMBER(30)     DEFAULT 0     NOT NULL,
    saleseqno                         NUMBER(10)     DEFAULT 1     NOT NULL,
    MEMBERNO                          NUMBER(10)     NULL ,
    ADMINNO                           NUMBER(10)     NULL 
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

ALTER TABLE sale ADD CONSTRAINT IDX_sale_PK PRIMARY KEY (saleno);
ALTER TABLE sale ADD CONSTRAINT IDX_sale_FK0 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);
ALTER TABLE sale ADD CONSTRAINT IDX_sale_FK1 FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO);

1. 등록

INSERT INTO sale (saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice, saleaddress, 
                        saletel, saleemail, salepasswd, salefile, salefstor, saletum, salesize, saleseqno, memberno)
VALUES ((SELECT NVL(MAX(saleno), 0) + 1 as saleno from sale), '팝니다.', '기출문제집', '댓글남겨주세요.', '투투',
           sysdate, 0, 10000, '서울시 강동구 3동', '01022222222', 'tkekm@naver.com',
           '1234', 'tell.jpg', 'tell(0).jpg', 'tell_t.jsp', 1000, 1, 1);
           
INSERT INTO sale (saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice, saleaddress, 
                        saletel, saleemail, salepasswd, salefile, salefstor, saletum, salesize, saleseqno, memberno)
VALUES ((SELECT NVL(MAX(saleno), 0) + 1 as saleno from sale), '중고책 사요.', '기출1', '댓글로.', '개구리',
           sysdate, 0, 13000, '서울시 강서구 2동', '01033332222', 'tkekasuam@naver.com',
           '1234', 'te.jpg', 'te(0).jgp', 'te_t.jsp', 130, 1, 1);
           
INSERT INTO sale (saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice, saleaddress, 
                        saletel, saleemail, salepasswd, salefile, salefstor, saletum, salesize, saleseqno, memberno)
VALUES ((SELECT NVL(MAX(saleno), 0) + 1 as saleno from sale), '○○책 어때요.', '정보처리기사 시나공', '삽니다.', '투투',
           sysdate, 0, 10000, '서울시 혜화 3동', '01022536222', 'gisla@naver.com',
           '1234', 'll.jpg', 'll(0).jgp', 'l_t.jsp', 1000, 1, 1);
           
2. 조회

1) 전체 목록 조회
SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice, saleaddress, 
          saletel, saleemail, salefile, salefstor, saletum, salesize, saleseqno, memberno
FROM sale
ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALETNAME SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL           SALEPASSWD SALEFILE SALEFSTOR   SALETUM    SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- --------- -------- --------------------- ------- --------- ----------- ----------- ------------------- ---------- -------- ----------- ---------- -------- --------- -------- ------ -----
      3 ○○책 어때요.  정보처리기사 시나공  삽니다.      투투       2017-12-13 17:16:10.0       0     10000 서울시 혜화 3동   01022536222 gisla@naver.com     1234       ll.jpg   ll(0).jgp   l_t.jsp        1000         1        1      2     1
      2 중고책 사요.   기출1         댓글로.      개구리      2017-12-13 17:15:19.0       0     13000 서울시 강서구 2동  01033332222 tkekasuam@naver.com 1234       te.jpg   te(0).jgp   te_t.jsp        130         1        1      2     1
      1 팝니다.      기출문제집       댓글남겨주세요.  투투       2017-12-13 17:15:14.0       0     10000 서울시 강동구 3동  01022222222 tkekm@naver.com     1234       tell.jpg tell(0).jpg tell_t.jsp     1000         1        1      2     1

2) 1개만 조회

SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice, saleaddress, 
          saletel, saleemail, salepasswd, salefile, salefstor, saletum, salesize, saleseqno, memberno
FROM sale
WHERE saleno = 1
ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALETNAME SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL       SALEPASSWD SALEFILE SALEFSTOR   SALETUM    SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- --------- -------- --------------------- ------- --------- ----------- ----------- --------------- ---------- -------- ----------- ---------- -------- --------- -------- ------ -----
      1 팝니다.      기출문제집       댓글남겨주세요.  투투       2017-12-13 17:15:14.0       0     10000 서울시 강동구 3동  01022222222 tkekm@naver.com 1234       tell.jpg tell(0).jpg tell_t.jsp     1000         1        1      2     1

3. 수정

UPDATE sale
SET saletitle = '구했어요', salecontent = '쪽지노노', saletname='판매된 상품입니다.', salename = '왕눈이2', saleprice = 0,
     saleaddress = '비공개', saletel = '비공개', saleemail='rh@naver.com', salefile = null, salefstor = null,
     saletum = null, salesize = 0, saleseqno = 1
WHERE saleno = 1;

 SALENO SALETITLE SALECONTENT SALETNAME  SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL SALEEMAIL    SALEPASSWD SALEFILE  SALEFSTOR    SALETUM    SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- ---------- -------- --------------------- ------- --------- ----------- ------- ------------ ---------- --------- ------------ ---------- -------- --------- -------- ------ -----
      1 구했어요      쪽지노노        판매된 상품입니다. 왕눈이2     2017-12-13 17:15:14.0       0         0 비공개         비공개     rh@naver.com 1234       none1.jpg none1(0).jpg none01.jpg        0         1        1      2     1

4. 삭제

1) 삭제
delete FROM sale 
WHERE saleno = 2;

2) 삭제 결과 확인
select * from sale ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALENAME SALETNAME  SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL       SALEPASSWD SALEFILE  SALEFSTOR    SALETUM    SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- ---------- --------------------- ------- --------- ----------- ----------- --------------- ---------- --------- ------------ ---------- -------- --------- -------- ------ -----
      3 ○○책 어때요.  정보처리기사 시나공  투투       삽니다.       2017-12-13 17:16:10.0       0     10000 서울시 혜화 3동   01022536222 gisla@naver.com 1234       ll.jpg    ll(0).jgp    l_t.jsp        1000         1        1      2     1
      1 구했어요      쪽지노노        왕눈이2     판매된 상품입니다. 2017-12-13 17:15:14.0       0         0 비공개         비공개         rh@naver.com    1234       none1.jpg none1(0).jpg none01.jpg        0         1        1      2     1

5. 조회수 증가

UPDATE sale
SET salecnt = salecnt + 1
WHERE saleno = 1;

 SALENO SALETITLE SALECONTENT SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL SALEEMAIL    SALEPASSWD SALEFILE  SALETUM    SALESIZE SALESEQNO MEMBERNO CATENO ADMINNO
 ------ --------- ----------- -------- --------------------- ------- --------- ----------- ------- ------------ ---------- --------- ---------- -------- --------- -------- ------ -------
      1 구했어요      쪽지노노        왕눈이2     2017-11-15 18:21:02.0       1         0 비공개         비공개     rh@naver.com 1234       none1.jpg none01.jpg        0         1     NULL   NULL    NULL

6. 패스워드 검사

SELECT COUNT(*) as salecnt
FROM sale
WHERE salepasswd = '1234';

 SALECNT
 -------
       2
       
7. 검색

- 컬럼 대상: saletitle, salecontent, salename

1) 검색하지 않고 전체 목록을 조회하는 경우

SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
          saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
          salesize, saleseqno, memberno
FROM sale
ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL       SALEPASSWD SALEFILE  SALETUM    SALESIZE SALESEQNO MEMBERNO CATENO ADMINNO
 ------ --------- ----------- -------- --------------------- ------- --------- ----------- ----------- --------------- ---------- --------- ---------- -------- --------- -------- ------ -------
      3 ○○책 어때요.  삽니다.        투투       2017-11-21 17:54:43.0       0     10000 서울시 혜화 3동   01022536222 gisla@naver.com 1234       gal.jpg   gel01.jpg      1000         1        3     41       2
      1 구했어요      쪽지노노        왕눈이2     2017-11-21 17:54:41.0       1         0 비공개         비공개         rh@naver.com    1234       none1.jpg none01.jpg        0         1        1     41       2

2) 제목으로 검색

SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
          saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
          salesize, saleseqno, memberno
FROM sale
WHERE saletitle like '%책%'
ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALETNAME SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL       SALEPASSWD SALEFILE SALETUM SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- --------- -------- --------------------- ------- --------- ----------- ----------- --------------- ---------- -------- ------- -------- --------- -------- ------ -----
      3 ○○책 어때요.  정보처리기사 시나공  삽니다.      투투       2017-12-13 17:16:10.0       0     10000 서울시 혜화 3동   01022536222 gisla@naver.com 1234       ll.jpg   l_t.jsp     1000         1        1      2     1

3) 내용으로 검색

SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
          saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
          salesize, saleseqno, memberno
FROM sale
WHERE salecontent like '%노노%'
ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALETNAME  SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL SALEEMAIL    SALEPASSWD SALEFILE  SALETUM    SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- ---------- -------- --------------------- ------- --------- ----------- ------- ------------ ---------- --------- ---------- -------- --------- -------- ------ -----
      1 구했어요      쪽지노노        판매된 상품입니다. 왕눈이2     2017-12-13 17:15:14.0       1         0 비공개         비공개     rh@naver.com 1234       none1.jpg none01.jpg        0         1        1      2     1

4) 이름으로 검색

SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
          saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
          salesize, saleseqno, memberno
FROM sale
WHERE salename like '%투투%'
ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALETNAME SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL       SALEPASSWD SALEFILE SALETUM SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- --------- -------- --------------------- ------- --------- ----------- ----------- --------------- ---------- -------- ------- -------- --------- -------- ------ -----
      3 ○○책 어때요.  정보처리기사 시나공  삽니다.      투투       2017-12-13 17:16:10.0       0     10000 서울시 혜화 3동   01022536222 gisla@naver.com 1234       ll.jpg   l_t.jsp     1000         1        1      2     1

5) 제목 + 내용으로 검색

SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
          saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
          salesize, saleseqno, memberno
FROM sale
WHERE saletitle like '%책%' OR salecontent like '%책%'
ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALETNAME SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL       SALEPASSWD SALEFILE SALETUM SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- --------- -------- --------------------- ------- --------- ----------- ----------- --------------- ---------- -------- ------- -------- --------- -------- ------ -----
      3 ○○책 어때요.  정보처리기사 시나공  삽니다.      투투       2017-12-13 17:16:10.0       0     10000 서울시 혜화 3동   01022536222 gisla@naver.com 1234       ll.jpg   l_t.jsp     1000         1        1      2     1

8. 페이징

1) 검색된 전체 레코드 수

SELECT COUNT(saleno) as salecnt 
FROM sale
WHERE salename LIKE '%투투%';

 SALECNT
 -------
       1
       
2) 페이징 구현
SELECT  saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
                   saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
                   salesize, saleseqno, memberno, r
FROM( 
         SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
                   saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
                   salesize, saleseqno, memberno, rownum as r
         FROM (
                  SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
                            saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
                            salesize, saleseqno, memberno
                  FROM sale
                  ORDER BY saleno DESC)
)
WHERE r >=0 and r <=1;

 SALENO SALETITLE SALECONTENT SALETNAME SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL       SALEPASSWD SALEFILE SALETUM SALESIZE SALESEQNO MEMBERNO CATENO GRPNO R
 ------ --------- ----------- --------- -------- --------------------- ------- --------- ----------- ----------- --------------- ---------- -------- ------- -------- --------- -------- ------ ----- -
      3 ○○책 어때요.  정보처리기사 시나공  삽니다.      투투       2017-12-13 17:16:10.0       0     10000 서울시 혜화 3동   01022536222 gisla@naver.com 1234       ll.jpg   l_t.jsp     1000         1        1      2     1 1

