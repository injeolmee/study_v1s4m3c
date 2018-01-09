DROP TABLE categrp CASCADE CONSTRAINTS;
DROP TABLE categrp;
/**********************************/
/* Table Name: 카테고리 */
/**********************************/
CREATE TABLE categrp(
		grpno                        		NUMBER(10)		  PRIMARY KEY,
		grpname                      		VARCHAR2(50)		 NOT NULL,
		grpseqno                     		NUMBER(5)		 NOT NULL,
		grpvisible                   		VARCHAR2(1)		 DEFAULT 'Y'		 NOT NULL
);

COMMENT ON TABLE categrp is '카테그룹';
COMMENT ON COLUMN categrp.grpno is '카테그룹번호';
COMMENT ON COLUMN categrp.grpname is '카테그룹이름';
COMMENT ON COLUMN categrp.grpseqno is '카테그룹출력순서';
COMMENT ON COLUMN categrp.grpvisible is '카테그룹출력여부';


1. INSERT
INSERT INTO categrp(grpno, grpname, grpseqno, grpvisible)
VALUES(1, '내스터디', 1 , 'Y');


2. SELECT
SELECT grpno, grpname, grpseqno, grpvisible
FROM categrp 
ORDER BY grpno ASC;



SELECT grpno, grpname, grpseqno, grpvisible
FROM categrp 
WHERE grpno=1;


3. UPDATE
UPDATE categrp
SET grpno = 2, grpname = '나의 스터디', grpseqno = 2, grpvisible = 'N'
WHERE grpno=1;


4. DELETE
DELETE FROM categrp WHERE grpno = 1;


5. 출력 우선 순위 
1) 높임, 10 -> 1
UPDATE categrp 
SET grpseqno = grpseqno - 1 
WHERE grpno=1; 
 
2) 낮춤, 1 -> 10
UPDATE categrp 
SET grpseqno = grpseqno + 1 
WHERE grpno=1;


