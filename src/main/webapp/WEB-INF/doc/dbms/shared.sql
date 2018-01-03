DROP TABLE shared CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 자료실 */
/**********************************/
CREATE TABLE shared(
    sharedno                          NUMBER(7)    NOT NULL,
    sharedtitle                       VARCHAR2(100)    NOT NULL,
    sharedcontent                     VARCHAR2(1000)     NOT NULL,
    sharedname                        VARCHAR2(50)     NOT NULL,
    sharedyoutube                     VARCHAR2(500)    NULL ,
    sharedseqno                       NUMBER(10)     NOT NULL,
    sharedfile                        VARCHAR2(500)    NULL ,
    sharedfstor                       VARCHAR2(500)    NULL ,
    sharedtum                         VARCHAR2(500)    NULL ,
    sharedsize                        NUMBER(30)     DEFAULT 0     NOT NULL,
    shareddate                        DATE     NOT NULL,
    sharedcnt                         NUMBER(6)    DEFAULT 0     NOT NULL,
    sharedlike                        NUMBER(6)    DEFAULT 0     NOT NULL,
    sharedpasswd                      VARCHAR2(50)     NULL ,
    MEMBERNO                          NUMBER(10)     NULL 
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
COMMENT ON COLUMN shared.sharedpasswd is '패스워드';
COMMENT ON COLUMN shared.MEMBERNO is '회원번호';


ALTER TABLE shared ADD CONSTRAINT IDX_shared_PK PRIMARY KEY (sharedno);
ALTER TABLE shared ADD CONSTRAINT IDX_shared_FK0 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);


select * from shared


1. 등록 -- 오라클은 일련번호의 자동 생성이 불가하기때문에 NVL 씀! --

-- 전체 목록 출력 --
SELECT * FROM shared 

INSERT INTO shared (sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno, sharedfile, sharedfstor, sharedtum, 
                           sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno) 
VALUES ((SELECT NVL(MAX(sharedno), 0)+1 as sharedno FROM shared),
            '기출문제 (1)', '기출문제 10년치 입니다.', '왕눈이', 'http://', 
            (SELECT NVL(MAX(sharedseqno), 0) + 1 as sharedseqno FROM shared),
            'image.jpg', 'image0.jpg', 'image_tum.jgp', 1000, sysdate, 0, 0, '1234', 1);
            
INSERT INTO shared (sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno, sharedfile, sharedfstor, sharedtum, 
                           sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno) 
VALUES ((SELECT NVL(MAX(sharedno), 0)+1 as sharedno FROM shared),
            '요약본 정리', '10년도까지 반영했어요', '투투', 'http://',
            (SELECT NVL(MAX(sharedseqno), 0) + 1 as sharedseqno FROM shared ),
            'book.jpg', 'book_fs.jpg', 'book_tum.jpg', 1000, sysdate, 0, 0, '1234', 1);

INSERT INTO shared (sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno, sharedfile, sharedfstor, sharedtum, 
                           sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno) 
VALUES ((SELECT NVL(MAX(sharedno), 0)+1 as sharedno FROM shared),
            '개발완료', '소스올렸어요.', '아로미', 'http://',
            (SELECT NVL(MAX(sharedseqno), 0) + 1 as sharedseqno FROM shared ),
            'source2.jpg', 'source2_fs.jpg', 'source2_tum.jpg', 1000, sysdate, 0, 0, '1234', 2);
            
2. 조회

1) 전체 목록 조회
SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
FROM shared
ORDER BY sharedno DESC;

 SHAREDNO SHAREDTITLE SHAREDCONTENT  SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE  SHAREDFSTOR    SHAREDTUM       SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- -------------- ---------- ------------- ----------- ----------- -------------- --------------- ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        3 개발완료        소스올렸어요.        아로미        http://                 3 source2.jpg source2_fs.jpg source2_tum.jpg       1000 2017-12-13 17:20:09.0         0          0 1234                1      3     1
        2 요약본 정리      10년도까지 반영했어요   투투         http://                 2 book.jpg    book_fs.jpg    book_tum.jpg          1000 2017-12-13 17:20:08.0         0          0 1234                1      3     1
        1 기출문제 (1)    기출문제 10년치 입니다. 왕눈이        http://                 1 image.jpg   image0.jpg     image_tum.jgp         1000 2017-12-13 17:20:07.0         0          0 1234                1      3     1

2) 1건의 글 보기
SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
FROM shared
WHERE sharedno = 1
ORDER BY sharedno DESC;

 SHAREDNO SHAREDTITLE SHAREDCONTENT  SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE SHAREDFSTOR SHAREDTUM     SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- -------------- ---------- ------------- ----------- ---------- ----------- ------------- ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        1 기출문제 (1)    기출문제 10년치 입니다. 왕눈이        http://                 1 image.jpg  image0.jpg  image_tum.jgp       1000 2017-12-13 17:20:07.0         0          0 1234                1      3     1

3. 수정

UPDATE shared
SET sharedtitle = '기출문제 수정완료', sharedcontent = '길동님 수정했어요!',  sharedyoutube = '',
     sharedfile='test.jpg', sharedfstor='test_fs.jpg', sharedsize=105, sharedtum = 'test_tum.jpg'
WHERE sharedno = 1;

SELECT * FROM shared WHERE sharedno = 1;
SELECT * FROM shared WHERE sharedno = 2;
SELECT * FROM shared WHERE sharedno = 3;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE SHAREDFSTOR SHAREDTUM    SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- ------------- ---------- ------------- ----------- ---------- ----------- ------------ ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        1 기출문제 수정완료   길동님 수정했어요!    왕눈이        http://                 1 test.jpg   test_fs.jpg test_tum.jpg        105 2017-12-13 17:20:07.0         0          0 1234                1      3     1

4. 조회수 증가

UPDATE shared
SET sharedcnt = sharedcnt + 1
WHERE sharedno = 1;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE SHAREDFSTOR SHAREDTUM    SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- ------------- ---------- ------------- ----------- ---------- ----------- ------------ ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        1 기출문제 수정완료   길동님 수정했어요!    왕눈이        http://                 1 test.jpg   test_fs.jpg test_tum.jpg        105 2017-12-13 17:20:07.0         1          0 1234                1      3     1

5. 추천수 증가

UPDATE shared
SET sharedlike = sharedlike + 1
WHERE sharedno = 1;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDMP3 SHAREDMP4 SHAREDFILE SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO ADMINNO
 -------- ----------- ------------- ---------- ------------- ----------- --------- --------- ---------- ---------- --------------------- --------- ---------- ------------ -------- ------ -------
        1 기출문제 수정완료   길동님 수정했어요!    개구리        http://                 1 NULL      movie.mp4 image.jpg        1000 2017-11-21 17:46:43.0         1          1 1234                1     42       1

6. 패스워드 검사

SELECT COUNT(*) as sharedcnt 
FROM shared
WHERE sharedno=1 AND sharedpasswd='1234';

 SHAREDCNT
 ---------
         1
         
7. 검색

- 컬럼 대상 : sharedtitle, sharedcontent, sharedname

1) 검색을 하지 않고 전체 목록을 조회하는 경우

SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
FROM shared
ORDER BY sharedno DESC;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE  SHAREDFSTOR    SHAREDTUM       SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- ------------- ---------- ------------- ----------- ----------- -------------- --------------- ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        3 개발완료        소스올렸어요.       아로미        http://                 3 source2.jpg source2_fs.jpg source2_tum.jpg       1000 2017-12-13 17:20:09.0         0          0 1234                1      3     1
        2 요약본 정리      10년도까지 반영했어요  투투         http://                 2 book.jpg    book_fs.jpg    book_tum.jpg          1000 2017-12-13 17:20:08.0         0          0 1234                1      3     1
        1 기출문제 수정완료   길동님 수정했어요!    왕눈이        http://                 1 test.jpg    test_fs.jpg    test_tum.jpg           105 2017-12-13 17:20:07.0         1          1 1234                1      3     1

2) 이름으로 검색

SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
FROM shared
WHERE sharedname LIKE '%아로미%'
ORDER BY sharedno DESC;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE  SHAREDFSTOR    SHAREDTUM       SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO ADMINNO
 -------- ----------- ------------- ---------- ------------- ----------- ----------- -------------- --------------- ---------- --------------------- --------- ---------- ------------ -------- ------ -------
        3 개발완료        소스올렸어요.       아로미        http://                 3 source2.jpg source2_fs.jpg source2_tum.jpg       1000 2017-12-04 10:29:31.0         0          0 1234                3     42       1


3) 제목으로 검색

SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
FROM shared
WHERE sharedtitle LIKE '%기출%'
ORDER BY sharedno DESC;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE SHAREDFSTOR SHAREDTUM    SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- ------------- ---------- ------------- ----------- ---------- ----------- ------------ ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        1 기출문제 수정완료   길동님 수정했어요!    왕눈이        http://                 1 test.jpg   test_fs.jpg test_tum.jpg        105 2017-12-13 17:20:07.0         1          1 1234                1      3     1

4) 내용으로 검색

SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
FROM shared
WHERE sharedcontent LIKE '%소스%'
ORDER BY sharedno DESC;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE  SHAREDFSTOR    SHAREDTUM       SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO ADMINNO
 -------- ----------- ------------- ---------- ------------- ----------- ----------- -------------- --------------- ---------- --------------------- --------- ---------- ------------ -------- ------ -------
        3 개발완료        소스올렸어요.       아로미        http://                 3 source2.jpg source2_fs.jpg source2_tum.jpg       1000 2017-12-04 10:29:31.0         0          0 1234                3     42       1

5) 제목 + 내용으로 검색

SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
FROM shared
WHERE sharedtitle = '%반영%' OR sharedcontent LIKE '%반영%'
ORDER BY sharedno DESC;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE SHAREDFSTOR SHAREDTUM    SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- ------------- ---------- ------------- ----------- ---------- ----------- ------------ ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        2 요약본 정리      10년도까지 반영했어요  투투         http://                 2 book.jpg   book_fs.jpg book_tum.jpg       1000 2017-12-13 17:20:08.0         0          0 1234                1      3     1

8. 페이징

1) 검색된 전체 레코드 수

SELECT COUNT(sharedno) as cnt 
FROM shared
WHERE sharedname LIKE '%아로미%';

  CNT
 ---
   1

2) 페이징 구현

SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno, r
FROM( 
         SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
                   sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno, rownum as r
         FROM (
                  SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
                            sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
                  FROM shared
                  ORDER BY sharedno DESC
         )
)
WHERE rownum >=0 and rownum <=2;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE  SHAREDFSTOR    SHAREDTUM       SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO R
 -------- ----------- ------------- ---------- ------------- ----------- ----------- -------------- --------------- ---------- --------------------- --------- ---------- ------------ -------- ------ ----- -
        3 개발완료        소스올렸어요.       아로미        http://                 3 source2.jpg source2_fs.jpg source2_tum.jpg       1000 2017-12-13 17:20:09.0         0          0 1234                1      3     1 1
        2 요약본 정리      10년도까지 반영했어요  투투         http://                 2 book.jpg    book_fs.jpg    book_tum.jpg          1000 2017-12-13 17:20:08.0         0          0 1234                1      3     1 2

9. 삭제

DELETE FROM shared
WHERE sharedno = 1;

select * from shared;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE  SHAREDFSTOR    SHAREDTUM       SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- ------------- ---------- ------------- ----------- ----------- -------------- --------------- ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        2 요약본 정리      10년도까지 반영했어요  투투         http://                 2 book.jpg    book_fs.jpg    book_tum.jpg          1000 2017-12-13 17:20:08.0         0          0 1234                1      3     1
        3 개발완료        소스올렸어요.       아로미        http://                 3 source2.jpg source2_fs.jpg source2_tum.jpg       1000 2017-12-13 17:20:09.0         0          0 1234                1      3     1

10. 이전글 / 다음글

1) 이전글

SELECT MAX(sharedno) as sharedno
FROM shared
WHERE sharedno < 1
ORDER BY sharedno DESC;

 SHAREDNO
 --------
        2
        
2) 다음글

SELECT MIN(sharedno) as sharedno
FROM shared
WHERE sharedno > 3
ORDER BY sharedno DESC;

 SHAREDNO
 --------
        4


