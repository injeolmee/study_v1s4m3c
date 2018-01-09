DROP TABLE sharedreply;
DELETE FROM sharedreply;

/**********************************/
/* Table Name: �ڷ� �Խ��� ��� */
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

COMMENT ON TABLE sharedreply is '�ڷ� �Խ��� ���';
COMMENT ON COLUMN sharedreply.shreplyno is '��� ��ȣ';
COMMENT ON COLUMN sharedreply.shreplycontent is '��� ����';
COMMENT ON COLUMN sharedreply.shreplyname is '��� �ۼ���';
COMMENT ON COLUMN sharedreply.shreplydate is '��� �����';
COMMENT ON COLUMN sharedreply.shreplygrpno is '��� �׷��ȣ';
COMMENT ON COLUMN sharedreply.shreplyindent is '���� ����';
COMMENT ON COLUMN sharedreply.shreplyansnum is '���� ����';
COMMENT ON COLUMN sharedreply.seqno is '��� ����';
COMMENT ON COLUMN sharedreply.sharedno is '�Խ��� ��ȣ';
COMMENT ON COLUMN sharedreply.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN sharedreply.ADMINNO is '�����ڹ�ȣ';


ALTER TABLE sharedreply ADD CONSTRAINT IDX_sharedreply_PK PRIMARY KEY (shreplyno);
ALTER TABLE sharedreply ADD CONSTRAINT IDX_sharedreply_FK0 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);
ALTER TABLE sharedreply ADD CONSTRAINT IDX_sharedreply_FK1 FOREIGN KEY (sharedno) REFERENCES shared (sharedno);
ALTER TABLE sharedreply ADD CONSTRAINT IDX_sharedreply_FK2 FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO);


1. ���

INSERT INTO sharedreply (shreplyno, shreplycontent, shreplyname, shreplydate, 
                                 shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno) 
VALUES ((SELECT NVL(MAX(shreplyno), 0) + 1 as shreplyno from sharedreply), '�󸸰���', '�մ���', sysdate, 
           (SELECT NVL(MAX(shreplygrpno), 0) + 1 as shreplygrpno from sharedreply), 0, 0, 1, 1, 1);
            
INSERT INTO sharedreply (shreplyno, shreplycontent, shreplyname, shreplydate, 
                                 shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno) 
VALUES ((SELECT NVL(MAX(shreplyno), 0) + 1 as shreplyno from sharedreply), '����ǰ �����մϴ�.', '����', sysdate, 
            (SELECT NVL(MAX(shreplygrpno), 0) + 1 as shreplygrpno from sharedreply), 0, 0, 1, 1, 1);
            
INSERT INTO sharedreply (shreplyno, shreplycontent, shreplyname, shreplydate, 
                                 shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno) 
VALUES ((SELECT NVL(MAX(shreplyno), 0) + 1 as shreplyno from sharedreply), '�ǰ� �δ�~', '�Ʒι�', sysdate, 
            (SELECT NVL(MAX(shreplygrpno), 0) + 1 as shreplygrpno from sharedreply), 0, 0, 1, 1, 1);


2. ��ȸ

1) ��ü ��� ��ȸ

SELECT shreplyno, shreplycontent, shreplyname, shreplydate, 
         shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno
FROM sharedreply;

 SHREPLYNO SHREPLYCONTENT SHREPLYNAME SHREPLYDATE           SHREPLYGRPNO SHREPLYINDENT SHREPLYANSNUM SHAREDNO MEMBERNO
 --------- -------------- ----------- --------------------- ------------ ------------- ------------- -------- --------
         1 �󸸰���           �մ���         2017-12-19 21:40:18.0            1             0             0        1        1
         2 ����ǰ �����մϴ�.     ����          2017-12-19 21:40:19.0            2             0             0        1        1
         3 �ǰ� �δ�~         �Ʒι�         2017-12-19 21:40:20.0            3             0             0        1        1

2) �Խñ۷� ��ȸ

SELECT shreplyno, shreplycontent, shreplyname, shreplydate, 
         shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno
FROM sharedreply
WHERE sharedno = 4
ORDER BY shreplyno ASC;

 SHREPLYNO SHREPLYCONTENT SHREPLYNAME SHREPLYDATE           SHREPLYGRPNO SHREPLYINDENT SHREPLYANSNUM SHAREDNO MEMBERNO
 --------- -------------- ----------- --------------------- ------------ ------------- ------------- -------- --------
         1 �󸸰���           �մ���         2017-12-19 21:40:18.0            1             0             0        1        1
         2 ����ǰ �����մϴ�.     ����          2017-12-19 21:40:19.0            2             0             0        1        1
         3 �ǰ� �δ�~         �Ʒι�         2017-12-19 21:40:20.0            3             0             0        1        1

3) Ư�� ��� ��ȸ
SELECT shreplyno, shreplycontent, shreplyname, shreplydate, 
         shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno
FROM sharedreply
WHERE shreplyno = 2;


3. ����

UPDATE sharedreply
SET shreplycontent = 'Ư����!', shreplydate = sysdate
WHERE shreplyno = 2;

 SHREPLYNO SHREPLYCONTENT SHREPLYNAME SHREPLYDATE           SHREPLYGRPNO SHREPLYINDENT SHREPLYANSNUM SHAREDNO MEMBERNO
 --------- -------------- ----------- --------------------- ------------ ------------- ------------- -------- --------
         2 Ư����!           ����          2017-12-19 21:40:19.0            2             0             0        1        1

4. ����

DELETE FROM sharedreply
WHERE shreplyno = 2;

5. COUNT ����

1) ���̵� �˻�

SELECT COUNT(*) as cnt
FROM sharedreply
WHERE memberno = 1;

 CNT
 ---
   2
   
2) �� ���ڵ� ���� ����

SELECT COUNT(shreplyno) as cnt
FROM sharedreply
WHERE sharedno = 1;
   
 CNT
 ---
   2

6. ����¡ + �亯�� ���� ���� ����

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
         1 �󸸰���           �մ���         2017-12-19 21:40:18.0            1             0             0        1        1 1
         3 �ǰ� �δ�~         �Ʒι�         2017-12-19 21:40:20.0            3             0             0        1        1 2

7. ���ο� �亯�� �ֽ� ��� ���� �亯 �̷��

UPDATE sharedreply
SET shreplyansnum = shreplyansnum + 1
WHERE sharedno = 1 AND shreplygrpno = 1 AND shreplyansnum > 1;

8. �θ� ����� ��� ���� ����� �����ϴ��� �˻�

SELECT COUNT(*) as cnt
FROM sharedreply
WHERE shreplygrpno = 3

9. ���۰� ���õǾ� �� ������ ������� �˻�

SELECT MAX(SHREPLYINDENT)
FROM sharedreply
WHERE sharedno = 4 AND shreplygrpno = 9;

