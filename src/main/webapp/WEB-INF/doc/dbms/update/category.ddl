DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE category;
/**********************************/
/* Table Name: 카테고리 */
/**********************************/
CREATE TABLE category(
		cateno                        		NUMBER(10)		 PRIMARY KEY,
		catename                      		VARCHAR2(50)		 NOT NULL,
		cateseqno                     		NUMBER(5)		 NOT NULL,
		catevisible                   		VARCHAR2(1)		 DEFAULT 'Y'		 NOT NULL,
		grpno                            NUMBER(10)  NULL,
		FOREIGN KEY (grpno) REFERENCES categrp (grpno)
);

COMMENT ON TABLE category is '카테고리';
COMMENT ON COLUMN category.cateno is '카테고리번호';
COMMENT ON COLUMN category.catename is '카테고리이름';
COMMENT ON COLUMN category.cateseqno is '카테고리출력순서';
COMMENT ON COLUMN category.catevisible is '카테고리출력여부';
COMMENT ON COLUMN category.grpno is '카테그룹 번호';


1. INSERT
INSERT INTO category(cateno, catename, cateseqno, catevisible, grpno)
VALUES(1, '내스터디', 1 , 'Y', 1);


2. SELECT
SELECT cateno, catename, cateseqno, catevisible, grpno
FROM category 
ORDER BY cateno ASC;

 CATENO CATENAME                   CATESEQNO CATEVISIBLE
 ------ -------------------------- --------- -----------
      1 동석 내스터디1 - 권한[U]                   1 Y
      2 동석 내스터디2                           2 Y
      3 동석 내스터디3                           3 Y
      4 동석 내스터디4                           4 Y
     10 희홍 스터디그룹1 - 조회[B]                  1 Y
     11 희홍 스터디그룹2  - 등록[U]                 2 Y
     20 혜지 스터디룸1-등록[A] 조회[B] 댓글[U]         1 Y
     21 혜지 스터디룸2                           2 Y
     30 권협 공모전 - 등록[A] 조회[B] 댓글[U]         1 Y
     31 권협 취업 - 등록[A] 조회[B] 댓글[U]          2 Y
     40 진아 자유게시판 - 권한[U]                   1 Y
     41 진아 거래게시판 - 권한[U]                   2 Y
     42 진아 자료실 - 권한[U]                     3 Y
     50 지은 마이페이지 - 권한[U]                   1 Y
     51 지은 공지사항 - 등록[A] 조회[B]              2 Y
     52 지은 회원 목록 - 권한[A]                   3 Y
     53 지은 관리자 목록 - 권한[A]  

SELECT cateno, catename, cateseqno, catevisible, grpno
FROM category 
WHERE cateno=1;


3. UPDATE
UPDATE category
SET cateno = 2, catename = '나의 스터디', cateseqno = 2, catevisible = 'N'
WHERE cateno=1;


4. DELETE
DELETE FROM category WHERE cateno = 1;


5. 출력 우선 순위 
1) 높임, 10 -> 1
UPDATE category 
SET cateseqno = cateseqno - 1 
WHERE cateno=1; 
 
2) 낮춤, 1 -> 10
UPDATE category 
SET cateseqno = cateseqno + 1 
WHERE cateno=1;

SELECT COUNT(cateno) as cnt
FROM category
WHERE cateno = 1 and grpno = 8;
