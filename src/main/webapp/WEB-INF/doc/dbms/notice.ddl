DROP TABLE notice CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 관리자 공지사항 게시판 */
/**********************************/
CREATE TABLE notice(
    noticeno                         NUMBER(10)     PRIMARY KEY,
    ntitle                              VARCHAR2(500)    NOT NULL,
    nname                             VARCHAR2(100)    NOT NULL,
    ncontent                    VARCHAR2(4000)     NOT NULL,
    nseqno                      NUMBER(10)     DEFAULT 1     NOT NULL,
    ncnt                        NUMBER(10)     DEFAULT 0     NOT NULL,
    ndate                       DATE     NOT NULL,
    adminno                           NUMBER(10)     NOT NULL,
    FOREIGN KEY (adminno) REFERENCES admin (adminno)
);

COMMENT ON TABLE notice is '관리자 공지사항 게시판';
COMMENT ON COLUMN notice.noticeno is '공지사항번호';
COMMENT ON COLUMN notice.ntitle is '공지사항제목';
COMMENT ON COLUMN notice.nname is '공지사항작성자';
COMMENT ON COLUMN notice.ncontent is '공지사항내용';
COMMENT ON COLUMN notice.nseqno is '공지사항출력순서';
COMMENT ON COLUMN notice.ncnt is '공지사항조회수';
COMMENT ON COLUMN notice.ndate is '공지사항등록일';
COMMENT ON COLUMN notice.adminno is '관리자번호';




1. INSERT
INSERT INTO admnotice(anoticeno, anoticetitle, anoticecontent, anoticeseqno, anoticecnt, anoticedate, adminno, cateno)
VALUES((SELECT NVL(MAX(anoticeno), 0)+1 as anoticeno FROM admnotice), '서버 점검 시간 안내1', '02:00 ~ 06:00 서버 점검이 있습니다1', 1, 0, sysdate, 1, 51);
INSERT INTO admnotice(anoticeno, anoticetitle, anoticecontent, anoticeseqno, anoticecnt, anoticedate, adminno, cateno)
VALUES((SELECT NVL(MAX(anoticeno), 0)+1 as anoticeno FROM admnotice), '서버 점검 시간 안내2', '02:00 ~ 06:00 서버 점검이 있습니다2', 1, 0, sysdate, 1, 51);
INSERT INTO admnotice(anoticeno, anoticetitle, anoticecontent, anoticeseqno, anoticecnt, anoticedate, adminno, cateno)
VALUES((SELECT NVL(MAX(anoticeno), 0)+1 as anoticeno FROM admnotice), '서버 점검 시간 안내3', '02:00 ~ 06:00 서버 점검이 있습니다3', 1, 0, sysdate, 1, 51);


2. SELECT
SELECT anoticeno, anoticetitle, anoticecontent, anoticeseqno, anoticecnt, anoticedate, adminno, cateno
FROM admnotice 
ORDER BY anoticeno ASC;

ANOTICENO ANOTICETITLE ANOTICECONTENT             ANOTICESEQNO ANOTICECNT ANOTICEDATE           ADMINNO CATENO
 --------- ------------ -------------------------- ------------ ---------- --------------------- ------- ------
         1 서버 점검 시간 안내1 02:00 ~ 06:00 서버 점검이 있습니다1            1          0 2017-11-17 12:21:56.0       1     51
         2 서버 점검 시간 안내2 02:00 ~ 06:00 서버 점검이 있습니다2            1          0 2017-11-17 12:21:57.0       1     51
         3 서버 점검 시간 안내3 02:00 ~ 06:00 서버 점검이 있습니다3            1          0 2017-11-17 12:21:58.0       1     51


SELECT anoticeno, anoticetitle, anoticecontent, anoticeseqno, anoticecnt, anoticedate, adminno, cateno
FROM admnotice 
WHERE anoticeno=1;


3. UPDATE
UPDATE admnotice
SET anoticetitle= '서버점검 변경', anoticecontent= '변경이 있습니다', anoticeseqno=2
WHERE anoticeno=1;


4. DELETE
DELETE FROM admnotice;

DELETE FROM admnotice WHERE anoticeno = 1;


5. 출력 우선 순위 
1) 높임, 10 -> 1
UPDATE admnotice 
SET anoticeseqno = anoticeseqno - 1 
WHERE anoticeno=1; 
 
2) 낮춤, 1 -> 10
UPDATE admnotice 
SET anoticeseqno = anoticeseqno + 1 
WHERE anoticeno=1;


6. 페이징
SELECT anoticeno, anoticetitle, anoticecontent, anoticeseqno, anoticecnt, anoticedate, adminno, cateno
FROM admnotice
WHERE anoticetitle LIKE '%왕눈이%'
ORDER BY anoticeno DESC anoticeseqno ASC
LIMIT 0, 5;
