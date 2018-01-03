DROP TABLE free CASCADE CONSTRAINTS;

DELETE FROM free;

/**********************************/
/* Table Name: 자유 게시판 */
/**********************************/
CREATE TABLE free(
    freeno                            NUMBER(7)    NOT NULL,
    freetitle                         VARCHAR2(100)    NOT NULL,
    freecontent                       VARCHAR2(1000)     NOT NULL,
    freename                          VARCHAR2(50)     NOT NULL,
    freedate                          DATE     NOT NULL,
    freecnt                           NUMBER(6)    DEFAULT 0     NOT NULL,
    freelike                          NUMBER(6)    DEFAULT 0     NOT NULL,
    freepasswd                        VARCHAR2(50)     NULL ,
    MEMBERNO                          NUMBER(10)     NULL 
);

COMMENT ON TABLE free is '자유 게시판';
COMMENT ON COLUMN free.freeno is '게시판 번호';
COMMENT ON COLUMN free.freetitle is '제목';
COMMENT ON COLUMN free.freecontent is '내용';
COMMENT ON COLUMN free.freename is '이름';
COMMENT ON COLUMN free.freedate is '등록일';
COMMENT ON COLUMN free.freecnt is '조회수';
COMMENT ON COLUMN free.freelike is '추천수';
COMMENT ON COLUMN free.freepasswd is '패스워드';
COMMENT ON COLUMN free.MEMBERNO is '회원번호';

ALTER TABLE free ADD CONSTRAINT IDX_free_PK PRIMARY KEY (freeno);
ALTER TABLE free ADD CONSTRAINT IDX_free_FK0 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);

1. 등록 (INSERT)

INSERT INTO free(freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno)
VALUES ((SELECT NVL(MAX(freeno), 0)+1 as freeno FROM free), 
'TEST 입니다', '오늘 하루', '왕눈이', sysdate, 0, 0, '1234', 1);

INSERT INTO free(freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno)
VALUES ((SELECT NVL(MAX(freeno), 0)+1 as freeno FROM free),
            '웃긴 얘기 해줄까?', '시룬데', '아재1', sysdate, 0, 0, '1234', 1);

INSERT INTO free(freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno)
VALUES ((SELECT NVL(MAX(freeno), 0)+1 as freeno FROM free), 
            '과제 해야해요', '눔나시러진짜싫어왕싫어', '아로미', sysdate, 0, 0, '1234', 1);

2. 조회

1) 전체 목록 조회
SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
ORDER BY freeno DESC;

 FREENO FREETITLE  FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO
 ------ ---------- ----------- -------- --------------------- ------- -------- ---------- --------
      4 과제 해야해요    눔나시러진짜싫어왕싫어 아로미      2017-12-14 18:09:13.0       0        0 1234              1
      3 웃긴 얘기 해줄까? 시룬데         아재1      2017-12-14 18:09:12.0       0        0 1234              1
      2 TEST 입니다   오늘 하루       왕눈이      2017-12-14 18:09:11.0       0        0 1234              1
      1 TEST 입니다   오늘 하루       왕눈이      2017-12-14 18:09:01.0       0        0 1234              1

2) 특정 조건의 조회
SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
WHERE freeno = 2
ORDER BY freeno DESC;

 FREENO FREETITLE  FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ ---------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      2 웃긴 얘기 해줄까? 시룬데         아재1      2017-12-13 17:07:36.0       0        0 1234              1      1     1

3. 수정

1) 글만 수정
UPDATE free
SET freetitle = '심심해요', freecontent = '히히'
WHERE freeno = 2;

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
WHERE freeno = 2;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      2 심심해요      히히          아재1      2017-12-13 17:07:36.0       0        0 1234              1      1     1

4. 삭제

DELETE FROM free
WHERE freeno = 3;

select * FROM free ORDER BY freeno DESC;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO FREESEQNO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ --------- -----
      2 심심해요      히히          아재1      2017-12-13 17:07:36.0       0        0 1234              1      1         1     1
      1 TEST 입니다  오늘 하루       왕눈이      2017-12-13 17:07:35.0       0        0 1234              1      1         1     1

5. 조회수

1) 조회수 증가

UPDATE free
SET freecnt = freecnt + 1 
WHERE freeno = 2;

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
ORDER BY freeno DESC;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      2 심심해요      히히          아재1      2017-12-13 17:07:36.0       1        0 1234              1      1     1
      1 TEST 입니다  오늘 하루       왕눈이      2017-12-13 17:07:35.0       0        0 1234              1      1     1

3) 조회수 MAX

SELECT MAX(freecnt) as cnt
FROM free;

 CNT
 ---
   1

4) 조회수 MIN

SELECT MIN(freecnt) as cnt
FROM free;

 CNT
 ---
   0
   
6. 추천수

1) 추천수 증가

UPDATE free
SET freelike = freelike + 1
WHERE freeno = 1;

select freelike
FROM free
where freeno = 1;

 FREELIKE
 --------
        1

7. 패스워드 검사

SELECT COUNT(freeno) as cnt
FROM free
WHERE freeno = 2 AND freepasswd = '1234';

 CNT
 ---
   1

8. 검색 (title, content, name, title + content)

- 컬럼 대상 : freetitle, freecontent, freename

1) 검색하지 않고 전체 목록을 조회하는 경우

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
ORDER BY freeno DESC;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      2 심심해요      히히          아재1      2017-12-13 17:07:36.0       1        0 1234              1      1     1
      1 TEST 입니다  오늘 하루       왕눈이      2017-12-13 17:07:35.0       0        1 1234              1      1     1

2) 제목으로 검색

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
WHERE freetitle like '%심심%'
ORDER BY freeno DESC;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      2 심심해요      히히          아재1      2017-12-13 17:07:36.0       1        0 1234              1      1     1

3) 내용으로 검색

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
WHERE freecontent like '%히히%'
ORDER BY freeno DESC;

  FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      2 심심해요      히히          아재1      2017-12-13 17:07:36.0       1        0 1234              1      1     1

4) 이름으로 검색

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
WHERE freename like '%왕눈이%'
ORDER BY freeno DESC;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      1 TEST 입니다  오늘 하루       왕눈이      2017-12-13 17:07:35.0       0        1 1234              1      1     1

5) 제목 + 내용으로 검색

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
WHERE freetitle like '%심심%' OR freecontent like '%심심%'
ORDER BY freeno DESC;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      2 심심해요      히히          아재1      2017-12-13 17:07:36.0       1        0 1234              1      1     1

9. 페이징

1) 검색된 전체 레코드 수
SELECT COUNT(freeno) as freecnt 
FROM free
WHERE freename LIKE '%아로미%';

 FREECNT
 -------
       0

2) 페이징 구현
SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                   freepasswd, memberno, r 
FROM(
         SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                   freepasswd, memberno, rownum as r
         FROM (
                   SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                             freepasswd, memberno
                   FROM free
                   ORDER BY freeno DESC
         )
)
WHERE r >=0 and r <=1;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO R
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ ----- -
      2 심심해요      히히          아재1      2017-12-13 17:07:36.0       1        0 1234              1      1     1 1

10. 추천수 조회

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                   freepasswd, memberno, r 
FROM(
         SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                   freepasswd, memberno, rownum as r
         FROM (
                   SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                             freepasswd, memberno
                   FROM free
                   ORDER BY freelike DESC
         )
)
WHERE r >=0 and r <=3;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO R
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ ----- -
      1 TEST 입니다  오늘 하루       왕눈이      2017-12-13 17:07:35.0       0        1 1234              1      1     1 1
      2 심심해요      히히          아재1      2017-12-13 17:07:36.0       1        0 1234              1      1     1 2

11. 조회수 조회

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                   freepasswd, memberno, r 
FROM(
         SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                   freepasswd, memberno, rownum as r
         FROM (
                   SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                             freepasswd, memberno
                   FROM free
                   ORDER BY freecnt DESC
         )
)
WHERE r >=0 and r <=3;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO R
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ ----- -
      2 심심해요      히히          아재1      2017-12-13 17:07:36.0       1        0 1234              1      1     1 1
      1 TEST 입니다  오늘 하루       왕눈이      2017-12-13 17:07:35.0       0        1 1234              1      1     1 2

12. 이전글 / 다음글

(1) 이전글

SELECT NVL(MAX(freeno), 1) as freeno
FROM free
WHERE freeno < 1

 FREENO
 ------
      1

(2) 다음글

SELECT NVL(MIN(freeno), 3) as freeno
FROM free
WHERE freeno > 3

 FREENO
 ------
      4
