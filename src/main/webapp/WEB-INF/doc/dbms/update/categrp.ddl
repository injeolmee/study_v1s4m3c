DROP TABLE categrp CASCADE CONSTRAINTS;
DROP TABLE categrp;
/**********************************/
/* Table Name: ī�װ� */
/**********************************/
CREATE TABLE categrp(
		grpno                        		NUMBER(10)		  PRIMARY KEY,
		grpname                      		VARCHAR2(50)		 NOT NULL,
		grpseqno                     		NUMBER(5)		 NOT NULL,
		grpvisible                   		VARCHAR2(1)		 DEFAULT 'Y'		 NOT NULL
);

COMMENT ON TABLE categrp is 'ī�ױ׷�';
COMMENT ON COLUMN categrp.grpno is 'ī�ױ׷��ȣ';
COMMENT ON COLUMN categrp.grpname is 'ī�ױ׷��̸�';
COMMENT ON COLUMN categrp.grpseqno is 'ī�ױ׷���¼���';
COMMENT ON COLUMN categrp.grpvisible is 'ī�ױ׷���¿���';


1. INSERT
INSERT INTO categrp(grpno, grpname, grpseqno, grpvisible)
VALUES(1, '�����͵�', 1 , 'Y');


2. SELECT
SELECT grpno, grpname, grpseqno, grpvisible
FROM categrp 
ORDER BY grpno ASC;



SELECT grpno, grpname, grpseqno, grpvisible
FROM categrp 
WHERE grpno=1;


3. UPDATE
UPDATE categrp
SET grpno = 2, grpname = '���� ���͵�', grpseqno = 2, grpvisible = 'N'
WHERE grpno=1;


4. DELETE
DELETE FROM categrp WHERE grpno = 1;


5. ��� �켱 ���� 
1) ����, 10 -> 1
UPDATE categrp 
SET grpseqno = grpseqno - 1 
WHERE grpno=1; 
 
2) ����, 1 -> 10
UPDATE categrp 
SET grpseqno = grpseqno + 1 
WHERE grpno=1;


