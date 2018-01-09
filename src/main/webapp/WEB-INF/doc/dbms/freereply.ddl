DROP TABLE freereply;
DELETE FROM freereply;
  
/**********************************/
/* Table Name: 자유 게시판 댓글 */
/**********************************/
CREATE TABLE freereply(
    freplyno                          NUMBER(7)    NOT NULL,
    freplycontent                     VARCHAR2(500)    NOT NULL,
    freplyname                        VARCHAR2(100)    NOT NULL,
    freplydate                        DATE     NOT NULL,
    freplygrpno                       NUMBER(7)    NOT NULL,
    freplyindent                      NUMBER(7)    DEFAULT 0     NOT NULL,
    freplyansnum                      NUMBER(7)    DEFAULT 0     NOT NULL,
    seqno                             NUMBER(5)    DEFAULT 1     NULL ,
    freeno                            NUMBER(7)    NULL ,
    MEMBERNO                          NUMBER(10)     NULL ,
    ADMINNO                           NUMBER(10)     NULL 
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


ALTER TABLE freereply ADD CONSTRAINT IDX_freereply_PK PRIMARY KEY (freplyno);
ALTER TABLE freereply ADD CONSTRAINT IDX_freereply_FK0 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);
ALTER TABLE freereply ADD CONSTRAINT IDX_freereply_FK1 FOREIGN KEY (freeno) REFERENCES free (freeno);
ALTER TABLE freereply ADD CONSTRAINT IDX_freereply_FK2 FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO);


1. 등록

INSERT INTO freereply (freplyno, freplycontent, freplyname, freplydate, freplygrpno, 
                              freplyindent, freplyansnum, freeno, MEMBERNO) 
VALUES ((SELECT NVL(MAX(freplyno), 0)+1 as freplyno FROM freereply), '재밌네요.', '왕눈이', sysdate,
            (SELECT NVL(MAX(freplygrpno), 0)+1 as freplygrpno FROM freereply), 0, 0, 1, 1);

INSERT INTO freereply (freplyno, freplycontent, freplyname, freplydate, freplygrpno, 
                              freplyindent, freplyansnum, freeno, MEMBERNO) 
VALUES ((SELECT NVL(MAX(freplyno), 0)+1 as freplyno FROM freereply), '강추합니다..', '투투', sysdate,
            (SELECT NVL(MAX(freplygrpno), 0)+1 as freplygrpno FROM freereply), 0, 0, 1, 1);
             
INSERT INTO freereply (freplyno, freplycontent, freplyname, freplydate, freplygrpno, 
                              freplyindent, freplyansnum, freeno, MEMBERNO) 
VALUES ((SELECT NVL(MAX(freplyno), 0)+1 as freplyno FROM freereply), '지은언니바보', '아로미', sysdate,
             (SELECT NVL(MAX(freplygrpno), 0)+1 as freplygrpno FROM freereply), 0, 0, 1, 1);
             
2. 조회

1. 전체 목록 조회

SELECT freplyno, freplycontent, freplyname, freplydate, freplygrpno, 
         freplyindent, freplyansnum, freeno, MEMBERNO, seqno
FROM freereply
ORDER BY freplyno ASC;

 FREPLYNO FREPLYCONTENT FREPLYNAME FREPLYDATE            FREPLYGRPNO FREPLYINDENT FREPLYANSNUM FREENO MEMBERNO
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ --------
        1 재밌네요.         왕눈이        2017-12-20 09:59:29.0           1            0            0      1        1
        2 강추합니다..       투투         2017-12-20 10:00:01.0           2            0            0      1        1
        3 지은언니바보        아로미        2017-12-20 10:00:02.0           3            0            0      1        1

2. 특정 조건 조회

SELECT freplyno, freplycontent, freplyname, freplydate, freplygrpno, 
         freplyindent, freplyansnum, freeno, MEMBERNO
FROM freereply
WHERE freplyno = 1
ORDER BY freplyno ASC;

 FREPLYNO FREPLYCONTENT FREPLYNAME FREPLYDATE            FREPLYGRPNO FREPLYINDENT FREPLYANSNUM FREENO MEMBERNO
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ --------
        1 재밌네요.         왕눈이        2017-12-20 09:59:29.0           1            0            0      1        1

3. 수정

UPDATE freereply
SET freplycontent = '우왕!'
WHERE freplyno = 1;

 FREPLYNO FREPLYCONTENT FREPLYNAME FREPLYDATE            FREPLYGRPNO FREPLYINDENT FREPLYANSNUM FREENO MEMBERNO
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ --------
        1 우왕!           왕눈이        2017-12-20 09:59:29.0           1            0            0      1        1

4. 삭제

1) 일반 댓글일 경우

DELETE FROM freereply
WHERE freplyno = 3;

2) 부모 댓글(= 대댓글의 부모 댓글)일 경우 content만 변경해 임시삭제처리

UPDATE freereply
SET freplycontent = '삭제된 댓글입니다.'
WHERE freplyno = 1;

 FREPLYNO FREPLYCONTENT FREPLYNAME FREPLYDATE            FREPLYGRPNO FREPLYINDENT FREPLYANSNUM FREENO MEMBERNO
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ --------
        1 우왕!           왕눈이        2017-12-20 09:59:29.0           1            0            0      1        1
        2 강추합니다..       투투         2017-12-20 10:00:01.0           2            0            0      1        1

3) 게시글 삭제시 댓글 전체 삭제

DELETE FROM freereply
WHERE freeno = 3;

5. COUNT 관련

1) 아이디 검사

SELECT COUNT(*) as cnt
FROM freereply
WHERE memberno = 1;

 CNT
 ---
   2
   
2) 총 레코드 갯수 산출

SELECT COUNT(freplyno) as cnt
FROM freereply
WHERE freeno = 1;
   
 CNT
 ---
   2

6. 페이징 + 답변에 따른 정렬 순서

SELECT freplyno, freplycontent, freplyname, freplydate, freplygrpno, 
         freplyindent, freplyansnum, freeno, MEMBERNO, r
FROM (SELECT freplyno, freplycontent, freplyname, freplydate, freplygrpno, 
                   freplyindent, freplyansnum, freeno, MEMBERNO, rownum as r
         FROM (SELECT freplyno, freplycontent, freplyname, freplydate, freplygrpno, 
                            freplyindent, freplyansnum, freeno, MEMBERNO
                   FROM freereply
                   WHERE freeno = 1
                   ORDER BY freplygrpno ASC, freplyindent ASC, freplyansnum ASC
         )
)
WHERE r >= 1 AND r <= 10;

 FREPLYNO FREPLYCONTENT FREPLYNAME FREPLYDATE            FREPLYGRPNO FREPLYINDENT FREPLYANSNUM FREENO MEMBERNO R
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ -------- -
        1 우왕!           왕눈이        2017-12-20 09:59:29.0           1            0            0      1        1 1
        2 강추합니다..       투투         2017-12-20 10:00:01.0           2            0            0      1        1 2

7. 새로운 답변의 최신 등록 위한 답변 미루기

UPDATE freereply
SET freplyansnum = freplyansnum + 1
WHERE freeno=1 AND freplygrpno = 1 AND freplyansnum > 1;

8. 부모 댓글일 경우 하위 댓글이 존재하는지 검사

SELECT COUNT(*) as cnt
FROM freereply
WHERE freplygrpno = 2;

 CNT
 ---
   1

