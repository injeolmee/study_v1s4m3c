DROP TABLE salereply;

DELETE FROM salereply;

/**********************************/
/* Table Name: 거래 게시판 댓글 */
/**********************************/
CREATE TABLE salereply(
    sreplyno                          NUMBER(7)    NOT NULL,
    sreplycontent                     VARCHAR2(500)    NOT NULL,
    sreplyname                        VARCHAR2(100)    NOT NULL,
    sreplydate                        DATE     NOT NULL,
    sreplygrpno                       NUMBER(7)    NOT NULL,
    sreplyindent                      NUMBER(7)    DEFAULT 0     NOT NULL,
    sreplyansnum                      NUMBER(7)    DEFAULT 0     NOT NULL,
    saleno                            NUMBER(7)    NULL ,
    MEMBERNO                          NUMBER(10)     NULL,
    seqno                                NUMBER(5) default 1         NULL
);

COMMENT ON TABLE salereply is '거래 게시판 댓글';
COMMENT ON COLUMN salereply.sreplyno is '댓글 번호';
COMMENT ON COLUMN salereply.sreplycontent is '댓글 내용';
COMMENT ON COLUMN salereply.sreplyname is '댓글 작성자';
COMMENT ON COLUMN salereply.sreplydate is '댓글 등록일';
COMMENT ON COLUMN salereply.sreplygrpno is '댓글 그룹번호';
COMMENT ON COLUMN salereply.sreplyindent is '대댓글 차수';
COMMENT ON COLUMN salereply.sreplyansnum is '대댓글 순서';
COMMENT ON COLUMN salereply.saleno is '거래게시판 번호';
COMMENT ON COLUMN salereply.MEMBERNO is '회원번호';
COMMENT ON COLUMN salereply.seqno is '출력권한';

ALTER TABLE salereply ADD CONSTRAINT IDX_salereply_PK PRIMARY KEY (sreplyno);
ALTER TABLE salereply ADD CONSTRAINT IDX_salereply_FK0 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);
ALTER TABLE salereply ADD CONSTRAINT IDX_salereply_FK1 FOREIGN KEY (saleno) REFERENCES sale (saleno);

1. 등록

INSERT INTO sharedreply (shreplyno, shreplycontent, shreplyname, shreplydate, 
                                shreplypasswd, shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno) 
VALUES ((SELECT NVL(MAX(shreplyno), 0) + 1 as shreplyno from sharedreply), '얼만가요', '왕눈이', sysdate, 
            '1234', (SELECT NVL(MAX(shreplygrpno), 0) + 1 as shreplygrpno from sharedreply), 0, 0, 1, 1, 1);
            
INSERT INTO sharedreply (shreplyno, shreplycontent, shreplyname, shreplydate, 
                                shreplypasswd, shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno) 
VALUES ((SELECT NVL(MAX(sreplyno), 0) + 1 as sreplyno from salereply), '이제품 강추합니다.', '투투', sysdate, 
            '1234', 1, 0, 0, 1, 2, 1);
            
INSERT INTO sharedreply (shreplyno, shreplycontent, shreplyname, shreplydate, 
                                shreplypasswd, shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno) 
VALUES ((SELECT NVL(MAX(sreplyno), 0) + 1 as sreplyno from salereply), '되게 싸당~', '아로미', sysdate, 
            '1234', 1, 0, 0, 1, 2, 1);

2. 조회

1) 전체 목록 조회
SELECT sreplyno, sreplycontent, sreplyname, sreplydate, sreplygrpno, 
          sreplyindent, sreplyansnum, saleno, MEMBERNO, seqno
FROM salereply;

 SREPLYNO SREPLYCONTENT SREPLYNAME SREPLYDATE            SREPLYGRPNO SREPLYINDENT SREPLYANSNUM SALENO MEMBERNO
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ --------
        1 댓글입력하자        회원200      2017-12-18 12:22:47.0                    1            0            0      3        1
        2 근데댓글이답변이계속안나와 회원200      2017-12-18 12:22:56.0        2            0            0      3        1
        3 얼만가요?         회원200      2017-12-18 14:47:08.0                      3            0            0      2        1
        4 ?             회원200      2017-12-18 14:48:11.0                            4            0            0      3        1
        5 적용안돼          회원200      2017-12-18 15:19:37.0                      5            0            0      3        1
        6 얼마에요?         회원200      2017-12-18 15:25:30.0                      6            0            0      3        1


2) 1건 조회

SELECT sreplyno, sreplycontent, sreplyname, sreplydate, sreplygrpno, 
          sreplyindent, sreplyansnum, saleno, MEMBERNO, seqno
FROM salereply
WHERE saleno = 1
ORDER BY sreplyno ASC;

 SREPLYNO SREPLYCONTENT SREPLYNAME SREPLYDATE            SREPLYGRPNO SREPLYINDENT SREPLYANSNUM SALENO MEMBERNO
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ --------
        1 입력합니다.        회원200      2017-12-18 16:30:40.0           1            0            0      3        1

 SREPLYNO SREPLYCONTENT SREPLYNAME SREPLYDATE            SREPLYGRPNO SREPLYINDENT SREPLYANSNUM SALENO MEMBERNO
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ --------
        1 댓글입력하자        회원200      2017-12-18 12:22:47.0                       1            0            0      3        1
        2 근데댓글이답변이계속안나와 회원200      2017-12-18 12:22:56.0           2            0            0      3        1
        4 ?             회원200      2017-12-18 14:48:11.0                                 4            0            0      3        1
        5 적용안돼          회원200      2017-12-18 15:19:37.0                           5            0            0      3        1
        6 얼마에요?         회원200      2017-12-18 15:25:30.0                           6            0            0      3        1
        7 4             회원200      2017-12-18 16:09:03.0                                 2            0            0      3        1


SELECT sreplyno, sreplycontent, sreplyname, sreplydate, sreplygrpno, 
          sreplyindent, sreplyansnum, saleno, MEMBERNO, seqno
FROM salereply
WHERE saleno = 2 AND sreplyno = 2;

 SREPLYNO SREPLYCONTENT SREPLYNAME SREPLYDATE            SREPLYPASSWD SREPLYGRPNO SREPLYINDENT SREPLYANSNUM SALENO MEMBERNO ADMINNO
 -------- ------------- ---------- --------------------- ------------ ----------- ------------ ------------ ------ -------- -------
        2 이제품 강추합니다.    투투         2017-12-13 14:40:30.0 1234                   1            1            1      2        1       1

3. 수정

UPDATE salereply
SET sreplycontent = '특가네!'
WHERE sreplyno = 1;

 SREPLYNO SREPLYCONTENT SREPLYNAME SREPLYDATE            SREPLYPASSWD SREPLYGRPNO SREPLYINDENT SREPLYANSNUM SALENO MEMBERNO ADMINNO
 -------- ------------- ---------- --------------------- ------------ ----------- ------------ ------------ ------ -------- -------
        1 특가네!          투투         2017-12-13 14:40:18.0 1234                   1            1            1      2        1       1
        2 이제품 강추합니다.    투투         2017-12-13 14:40:30.0 1234                   1            1            1      2        1       1
        3 되게 싸당~        아로미        2017-12-13 14:40:31.0 1234                   1            1            1      2        1       1

4. 삭제

1) 일반 댓글일 경우

DELETE FROM salereply
WHERE sreplyno = 1;

2) 부모 댓글(= 대댓글의 부모 댓글)일 경우 content만 변경해 임시삭제처리

UPDATE salereply
SET sreplycontent = '삭제된 댓글입니다.'
WHERE sreplyno = 1;

5. COUNT 관련

1) 아이디 검사

SELECT COUNT(*) as cnt
FROM salereply
WHERE memberno = 1;

 CNT
 ---
   2
   
2) 총 레코드 갯수 산출

SELECT COUNT(sreplyno) as cnt
FROM salereply
WHERE saleno = 1;

6. 페이징 + 답변에 따른 정렬 순서

SELECT sreplyno, sreplycontent, sreplyname, sreplydate, sreplygrpno, 
          sreplyindent, sreplyansnum, saleno, MEMBERNO, seqno, r
FROM (SELECT sreplyno, sreplycontent, sreplyname, sreplydate, sreplygrpno, 
                   sreplyindent, sreplyansnum, saleno, MEMBERNO, seqno, rownum as r
         FROM (SELECT sreplyno, sreplycontent, sreplyname, sreplydate, sreplygrpno, 
                            sreplyindent, sreplyansnum, saleno, MEMBERNO, seqno
                   FROM salereply
                   WHERE saleno = 3
                   ORDER BY sreplygrpno ASC, sreplyindent ASC, sreplyansnum DESC
         )
)
WHERE r >= 1 AND r <= 10;

 SREPLYNO SREPLYCONTENT SREPLYNAME SREPLYDATE            SREPLYGRPNO SREPLYINDENT SREPLYANSNUM SALENO MEMBERNO R
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ -------- -
        1 댓글입력하자        회원200      2017-12-18 12:22:47.0           1            0            0      3        1 1
        2 근데댓글이답변이계속안나와 회원200      2017-12-18 12:22:56.0           2            0            0      3        1 2
        7 4             회원200      2017-12-18 16:09:03.0           2            0            0      3        1 3
        4 ?             회원200      2017-12-18 14:48:11.0           4            0            0      3        1 4
        5 적용안돼          회원200      2017-12-18 15:19:37.0           5            0            0      3        1 5
        6 얼마에요?         회원200      2017-12-18 15:25:30.0           6            0            0      3        1 6

7. 새로운 답변의 최신 등록 위한 답변 미루기

UPDATE salereply
SET sreplyansnum = sreplyansnum + 1
WHERE saleno=1 AND sreplygrpno = 1 AND sreplyansnum > 1;
