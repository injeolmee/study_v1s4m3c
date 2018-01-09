DROP TABLE freereply;
DELETE FROM freereply;
  
/**********************************/
/* Table Name: ���� �Խ��� ��� */
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

COMMENT ON TABLE freereply is '���� �Խ��� ���';
COMMENT ON COLUMN freereply.freplyno is '��� ��ȣ';
COMMENT ON COLUMN freereply.freplycontent is '��� ����';
COMMENT ON COLUMN freereply.freplyname is '��� �ۼ���';
COMMENT ON COLUMN freereply.freplydate is '��� �����';
COMMENT ON COLUMN freereply.freplygrpno is '��� �׷��ȣ';
COMMENT ON COLUMN freereply.freplyindent is '���� ����';
COMMENT ON COLUMN freereply.freplyansnum is '���� ����';
COMMENT ON COLUMN freereply.seqno is '��� ����';
COMMENT ON COLUMN freereply.freeno is '�Խ��� ��ȣ';
COMMENT ON COLUMN freereply.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN freereply.ADMINNO is '�����ڹ�ȣ';


ALTER TABLE freereply ADD CONSTRAINT IDX_freereply_PK PRIMARY KEY (freplyno);
ALTER TABLE freereply ADD CONSTRAINT IDX_freereply_FK0 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);
ALTER TABLE freereply ADD CONSTRAINT IDX_freereply_FK1 FOREIGN KEY (freeno) REFERENCES free (freeno);
ALTER TABLE freereply ADD CONSTRAINT IDX_freereply_FK2 FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO);


1. ���

INSERT INTO freereply (freplyno, freplycontent, freplyname, freplydate, freplygrpno, 
                              freplyindent, freplyansnum, freeno, MEMBERNO) 
VALUES ((SELECT NVL(MAX(freplyno), 0)+1 as freplyno FROM freereply), '��ճ׿�.', '�մ���', sysdate,
            (SELECT NVL(MAX(freplygrpno), 0)+1 as freplygrpno FROM freereply), 0, 0, 1, 1);

INSERT INTO freereply (freplyno, freplycontent, freplyname, freplydate, freplygrpno, 
                              freplyindent, freplyansnum, freeno, MEMBERNO) 
VALUES ((SELECT NVL(MAX(freplyno), 0)+1 as freplyno FROM freereply), '�����մϴ�..', '����', sysdate,
            (SELECT NVL(MAX(freplygrpno), 0)+1 as freplygrpno FROM freereply), 0, 0, 1, 1);
             
INSERT INTO freereply (freplyno, freplycontent, freplyname, freplydate, freplygrpno, 
                              freplyindent, freplyansnum, freeno, MEMBERNO) 
VALUES ((SELECT NVL(MAX(freplyno), 0)+1 as freplyno FROM freereply), '������Ϲٺ�', '�Ʒι�', sysdate,
             (SELECT NVL(MAX(freplygrpno), 0)+1 as freplygrpno FROM freereply), 0, 0, 1, 1);
             
2. ��ȸ

1. ��ü ��� ��ȸ

SELECT freplyno, freplycontent, freplyname, freplydate, freplygrpno, 
         freplyindent, freplyansnum, freeno, MEMBERNO, seqno
FROM freereply
ORDER BY freplyno ASC;

 FREPLYNO FREPLYCONTENT FREPLYNAME FREPLYDATE            FREPLYGRPNO FREPLYINDENT FREPLYANSNUM FREENO MEMBERNO
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ --------
        1 ��ճ׿�.         �մ���        2017-12-20 09:59:29.0           1            0            0      1        1
        2 �����մϴ�..       ����         2017-12-20 10:00:01.0           2            0            0      1        1
        3 ������Ϲٺ�        �Ʒι�        2017-12-20 10:00:02.0           3            0            0      1        1

2. Ư�� ���� ��ȸ

SELECT freplyno, freplycontent, freplyname, freplydate, freplygrpno, 
         freplyindent, freplyansnum, freeno, MEMBERNO
FROM freereply
WHERE freplyno = 1
ORDER BY freplyno ASC;

 FREPLYNO FREPLYCONTENT FREPLYNAME FREPLYDATE            FREPLYGRPNO FREPLYINDENT FREPLYANSNUM FREENO MEMBERNO
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ --------
        1 ��ճ׿�.         �մ���        2017-12-20 09:59:29.0           1            0            0      1        1

3. ����

UPDATE freereply
SET freplycontent = '���!'
WHERE freplyno = 1;

 FREPLYNO FREPLYCONTENT FREPLYNAME FREPLYDATE            FREPLYGRPNO FREPLYINDENT FREPLYANSNUM FREENO MEMBERNO
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ --------
        1 ���!           �մ���        2017-12-20 09:59:29.0           1            0            0      1        1

4. ����

1) �Ϲ� ����� ���

DELETE FROM freereply
WHERE freplyno = 3;

2) �θ� ���(= ������ �θ� ���)�� ��� content�� ������ �ӽû���ó��

UPDATE freereply
SET freplycontent = '������ ����Դϴ�.'
WHERE freplyno = 1;

 FREPLYNO FREPLYCONTENT FREPLYNAME FREPLYDATE            FREPLYGRPNO FREPLYINDENT FREPLYANSNUM FREENO MEMBERNO
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ --------
        1 ���!           �մ���        2017-12-20 09:59:29.0           1            0            0      1        1
        2 �����մϴ�..       ����         2017-12-20 10:00:01.0           2            0            0      1        1

3) �Խñ� ������ ��� ��ü ����

DELETE FROM freereply
WHERE freeno = 3;

5. COUNT ����

1) ���̵� �˻�

SELECT COUNT(*) as cnt
FROM freereply
WHERE memberno = 1;

 CNT
 ---
   2
   
2) �� ���ڵ� ���� ����

SELECT COUNT(freplyno) as cnt
FROM freereply
WHERE freeno = 1;
   
 CNT
 ---
   2

6. ����¡ + �亯�� ���� ���� ����

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
        1 ���!           �մ���        2017-12-20 09:59:29.0           1            0            0      1        1 1
        2 �����մϴ�..       ����         2017-12-20 10:00:01.0           2            0            0      1        1 2

7. ���ο� �亯�� �ֽ� ��� ���� �亯 �̷��

UPDATE freereply
SET freplyansnum = freplyansnum + 1
WHERE freeno=1 AND freplygrpno = 1 AND freplyansnum > 1;

8. �θ� ����� ��� ���� ����� �����ϴ��� �˻�

SELECT COUNT(*) as cnt
FROM freereply
WHERE freplygrpno = 2;

 CNT
 ---
   1

