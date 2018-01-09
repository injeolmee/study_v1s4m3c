DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE category;
/**********************************/
/* Table Name: ī�װ� */
/**********************************/
CREATE TABLE category(
		cateno                        		NUMBER(10)		 PRIMARY KEY,
		catename                      		VARCHAR2(50)		 NOT NULL,
		cateseqno                     		NUMBER(5)		 NOT NULL,
		catevisible                   		VARCHAR2(1)		 DEFAULT 'Y'		 NOT NULL,
		grpno                            NUMBER(10)  NULL,
		FOREIGN KEY (grpno) REFERENCES categrp (grpno)
);

COMMENT ON TABLE category is 'ī�װ�';
COMMENT ON COLUMN category.cateno is 'ī�װ���ȣ';
COMMENT ON COLUMN category.catename is 'ī�װ��̸�';
COMMENT ON COLUMN category.cateseqno is 'ī�װ���¼���';
COMMENT ON COLUMN category.catevisible is 'ī�װ���¿���';
COMMENT ON COLUMN category.grpno is 'ī�ױ׷� ��ȣ';


1. INSERT
INSERT INTO category(cateno, catename, cateseqno, catevisible, grpno)
VALUES(1, '�����͵�', 1 , 'Y', 1);


2. SELECT
SELECT cateno, catename, cateseqno, catevisible, grpno
FROM category 
ORDER BY cateno ASC;

 CATENO CATENAME                   CATESEQNO CATEVISIBLE
 ------ -------------------------- --------- -----------
      1 ���� �����͵�1 - ����[U]                   1 Y
      2 ���� �����͵�2                           2 Y
      3 ���� �����͵�3                           3 Y
      4 ���� �����͵�4                           4 Y
     10 ��ȫ ���͵�׷�1 - ��ȸ[B]                  1 Y
     11 ��ȫ ���͵�׷�2  - ���[U]                 2 Y
     20 ���� ���͵��1-���[A] ��ȸ[B] ���[U]         1 Y
     21 ���� ���͵��2                           2 Y
     30 ���� ������ - ���[A] ��ȸ[B] ���[U]         1 Y
     31 ���� ��� - ���[A] ��ȸ[B] ���[U]          2 Y
     40 ���� �����Խ��� - ����[U]                   1 Y
     41 ���� �ŷ��Խ��� - ����[U]                   2 Y
     42 ���� �ڷ�� - ����[U]                     3 Y
     50 ���� ���������� - ����[U]                   1 Y
     51 ���� �������� - ���[A] ��ȸ[B]              2 Y
     52 ���� ȸ�� ��� - ����[A]                   3 Y
     53 ���� ������ ��� - ����[A]  

SELECT cateno, catename, cateseqno, catevisible, grpno
FROM category 
WHERE cateno=1;


3. UPDATE
UPDATE category
SET cateno = 2, catename = '���� ���͵�', cateseqno = 2, catevisible = 'N'
WHERE cateno=1;


4. DELETE
DELETE FROM category WHERE cateno = 1;


5. ��� �켱 ���� 
1) ����, 10 -> 1
UPDATE category 
SET cateseqno = cateseqno - 1 
WHERE cateno=1; 
 
2) ����, 1 -> 10
UPDATE category 
SET cateseqno = cateseqno + 1 
WHERE cateno=1;

SELECT COUNT(cateno) as cnt
FROM category
WHERE cateno = 1 and grpno = 8;
