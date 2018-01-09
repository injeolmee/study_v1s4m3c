DROP TABLE sharedreply;
DELETE FROM sharedreply;

/**********************************/
/* Table Name: 자료 게시판 댓글 */
/**********************************/
CREATE TABLE sharedreply(
    shreplyno                         NUMBER(7)    NOT NULL,
    shreplycontent                    VARCHAR2(500)    NOT NULL,
    shreplyname                       VARCHAR2(100)    NOT NULL,
    shreplydate                       DATE     NOT NULL,
    shreplygrpno                      NUMBER(7)    NOT NULL,
    shreplyindent                     NUMBER(7)    DEFAULT 0     NOT NULL,
    shreplyansnum                     NUMBER(7)    DEFAULT 0     NOT NULL,
    seqno                             NUMBER(5)    DEFAULT 1     NULL ,
    sharedno                          NUMBER(7)    NULL ,
    MEMBERNO                          NUMBER(10)     NULL ,
    ADMINNO                           NUMBER(10)     NULL 
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


ALTER TABLE sharedreply ADD CONSTRAINT IDX_sharedreply_PK PRIMARY KEY (shreplyno);
ALTER TABLE sharedreply ADD CONSTRAINT IDX_sharedreply_FK0 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);
ALTER TABLE sharedreply ADD CONSTRAINT IDX_sharedreply_FK1 FOREIGN KEY (sharedno) REFERENCES shared (sharedno);
ALTER TABLE sharedreply ADD CONSTRAINT IDX_sharedreply_FK2 FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO);


1. 등록

INSERT INTO sharedreply (shreplyno, shreplycontent, shreplyname, shreplydate, 
                                 shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno) 
VALUES ((SELECT NVL(MAX(shreplyno), 0) + 1 as shreplyno from sharedreply), '얼만가요', '왕눈이', sysdate, 
           (SELECT NVL(MAX(shreplygrpno), 0) + 1 as shreplygrpno from sharedreply), 0, 0, 1, 1, 1);
            
INSERT INTO sharedreply (shreplyno, shreplycontent, shreplyname, shreplydate, 
                                 shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno) 
VALUES ((SELECT NVL(MAX(shreplyno), 0) + 1 as shreplyno from sharedreply), '이제품 강추합니다.', '투투', sysdate, 
            (SELECT NVL(MAX(shreplygrpno), 0) + 1 as shreplygrpno from sharedreply), 0, 0, 1, 1, 1);
            
INSERT INTO sharedreply (shreplyno, shreplycontent, shreplyname, shreplydate, 
                                 shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno) 
VALUES ((SELECT NVL(MAX(shreplyno), 0) + 1 as shreplyno from sharedreply), '되게 싸당~', '아로미', sysdate, 
            (SELECT NVL(MAX(shreplygrpno), 0) + 1 as shreplygrpno from sharedreply), 0, 0, 1, 1, 1);


2. 조회

1) 전체 목록 조회

SELECT shreplyno, shreplycontent, shreplyname, shreplydate, 
         shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno
FROM sharedreply;

 SHREPLYNO SHREPLYCONTENT SHREPLYNAME SHREPLYDATE           SHREPLYGRPNO SHREPLYINDENT SHREPLYANSNUM SHAREDNO MEMBERNO
 --------- -------------- ----------- --------------------- ------------ ------------- ------------- -------- --------
         1 얼만가요           왕눈이         2017-12-19 21:40:18.0            1             0             0        1        1
         2 이제품 강추합니다.     투투          2017-12-19 21:40:19.0            2             0             0        1        1
         3 되게 싸당~         아로미         2017-12-19 21:40:20.0            3             0             0        1        1

2) 게시글로 조회

SELECT shreplyno, shreplycontent, shreplyname, shreplydate, 
         shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno
FROM sharedreply
WHERE sharedno = 4
ORDER BY shreplyno ASC;

 SHREPLYNO SHREPLYCONTENT SHREPLYNAME SHREPLYDATE           SHREPLYGRPNO SHREPLYINDENT SHREPLYANSNUM SHAREDNO MEMBERNO
 --------- -------------- ----------- --------------------- ------------ ------------- ------------- -------- --------
         1 얼만가요           왕눈이         2017-12-19 21:40:18.0            1             0             0        1        1
         2 이제품 강추합니다.     투투          2017-12-19 21:40:19.0            2             0             0        1        1
         3 되게 싸당~         아로미         2017-12-19 21:40:20.0            3             0             0        1        1

3) 특정 댓글 조회
SELECT shreplyno, shreplycontent, shreplyname, shreplydate, 
         shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno
FROM sharedreply
WHERE shreplyno = 2;


3. 수정

UPDATE sharedreply
SET shreplycontent = '특가네!', shreplydate = sysdate
WHERE shreplyno = 2;

 SHREPLYNO SHREPLYCONTENT SHREPLYNAME SHREPLYDATE           SHREPLYGRPNO SHREPLYINDENT SHREPLYANSNUM SHAREDNO MEMBERNO
 --------- -------------- ----------- --------------------- ------------ ------------- ------------- -------- --------
         2 특가네!           투투          2017-12-19 21:40:19.0            2             0             0        1        1

4. 삭제

DELETE FROM sharedreply
WHERE shreplyno = 2;

5. COUNT 관련

1) 아이디 검사

SELECT COUNT(*) as cnt
FROM sharedreply
WHERE memberno = 1;

 CNT
 ---
   2
   
2) 총 레코드 갯수 산출

SELECT COUNT(shreplyno) as cnt
FROM sharedreply
WHERE sharedno = 1;
   
 CNT
 ---
   2

6. 페이징 + 답변에 따른 정렬 순서

SELECT shreplyno, shreplycontent, shreplyname, shreplydate, 
         shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno, r
FROM (SELECT shreplyno, shreplycontent, shreplyname, shreplydate, 
                   shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno. rownum as r
         FROM (SELECT shreplyno, shreplycontent, shreplyname, shreplydate, 
                            shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno
                   FROM sharedreply
                   WHERE sharedno = 1
                   ORDER BY shreplygrpno ASC, shreplyindent ASC, shreplyansnum DESC
         )
)
WHERE r >= 1 AND r <= 10;

 SHREPLYNO SHREPLYCONTENT SHREPLYNAME SHREPLYDATE           SHREPLYGRPNO SHREPLYINDENT SHREPLYANSNUM SHAREDNO MEMBERNO R
 --------- -------------- ----------- --------------------- ------------ ------------- ------------- -------- -------- -
         1 얼만가요           왕눈이         2017-12-19 21:40:18.0            1             0             0        1        1 1
         3 되게 싸당~         아로미         2017-12-19 21:40:20.0            3             0             0        1        1 2

7. 새로운 답변의 최신 등록 위한 답변 미루기

UPDATE sharedreply
SET shreplyansnum = shreplyansnum + 1
WHERE sharedno = 1 AND shreplygrpno = 1 AND shreplyansnum > 1;

8. 부모 댓글일 경우 하위 댓글이 존재하는지 검사

SELECT COUNT(*) as cnt
FROM sharedreply
WHERE shreplygrpno = 3

9. 대댓글과 관련되어 맨 마지막 댓글인지 검사

SELECT MAX(SHREPLYINDENT)
FROM sharedreply
WHERE sharedno = 4 AND shreplygrpno = 9;

