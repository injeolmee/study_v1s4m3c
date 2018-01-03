DROP TABLE salereply;

DELETE FROM salereply;

/**********************************/
/* Table Name: �ŷ� �Խ��� ��� */
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

COMMENT ON TABLE salereply is '�ŷ� �Խ��� ���';
COMMENT ON COLUMN salereply.sreplyno is '��� ��ȣ';
COMMENT ON COLUMN salereply.sreplycontent is '��� ����';
COMMENT ON COLUMN salereply.sreplyname is '��� �ۼ���';
COMMENT ON COLUMN salereply.sreplydate is '��� �����';
COMMENT ON COLUMN salereply.sreplygrpno is '��� �׷��ȣ';
COMMENT ON COLUMN salereply.sreplyindent is '���� ����';
COMMENT ON COLUMN salereply.sreplyansnum is '���� ����';
COMMENT ON COLUMN salereply.saleno is '�ŷ��Խ��� ��ȣ';
COMMENT ON COLUMN salereply.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN salereply.seqno is '��±���';

ALTER TABLE salereply ADD CONSTRAINT IDX_salereply_PK PRIMARY KEY (sreplyno);
ALTER TABLE salereply ADD CONSTRAINT IDX_salereply_FK0 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);
ALTER TABLE salereply ADD CONSTRAINT IDX_salereply_FK1 FOREIGN KEY (saleno) REFERENCES sale (saleno);

1. ���

INSERT INTO sharedreply (shreplyno, shreplycontent, shreplyname, shreplydate, 
                                shreplypasswd, shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno) 
VALUES ((SELECT NVL(MAX(shreplyno), 0) + 1 as shreplyno from sharedreply), '�󸸰���', '�մ���', sysdate, 
            '1234', (SELECT NVL(MAX(shreplygrpno), 0) + 1 as shreplygrpno from sharedreply), 0, 0, 1, 1, 1);
            
INSERT INTO sharedreply (shreplyno, shreplycontent, shreplyname, shreplydate, 
                                shreplypasswd, shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno) 
VALUES ((SELECT NVL(MAX(sreplyno), 0) + 1 as sreplyno from salereply), '����ǰ �����մϴ�.', '����', sysdate, 
            '1234', 1, 0, 0, 1, 2, 1);
            
INSERT INTO sharedreply (shreplyno, shreplycontent, shreplyname, shreplydate, 
                                shreplypasswd, shreplygrpno, shreplyindent, shreplyansnum, sharedno, MEMBERNO, seqno) 
VALUES ((SELECT NVL(MAX(sreplyno), 0) + 1 as sreplyno from salereply), '�ǰ� �δ�~', '�Ʒι�', sysdate, 
            '1234', 1, 0, 0, 1, 2, 1);

2. ��ȸ

1) ��ü ��� ��ȸ
SELECT sreplyno, sreplycontent, sreplyname, sreplydate, sreplygrpno, 
          sreplyindent, sreplyansnum, saleno, MEMBERNO, seqno
FROM salereply;

 SREPLYNO SREPLYCONTENT SREPLYNAME SREPLYDATE            SREPLYGRPNO SREPLYINDENT SREPLYANSNUM SALENO MEMBERNO
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ --------
        1 ����Է�����        ȸ��200      2017-12-18 12:22:47.0                    1            0            0      3        1
        2 �ٵ�����̴亯�̰�Ӿȳ��� ȸ��200      2017-12-18 12:22:56.0        2            0            0      3        1
        3 �󸸰���?         ȸ��200      2017-12-18 14:47:08.0                      3            0            0      2        1
        4 ?             ȸ��200      2017-12-18 14:48:11.0                            4            0            0      3        1
        5 ����ȵ�          ȸ��200      2017-12-18 15:19:37.0                      5            0            0      3        1
        6 �󸶿���?         ȸ��200      2017-12-18 15:25:30.0                      6            0            0      3        1


2) 1�� ��ȸ

SELECT sreplyno, sreplycontent, sreplyname, sreplydate, sreplygrpno, 
          sreplyindent, sreplyansnum, saleno, MEMBERNO, seqno
FROM salereply
WHERE saleno = 1
ORDER BY sreplyno ASC;

 SREPLYNO SREPLYCONTENT SREPLYNAME SREPLYDATE            SREPLYGRPNO SREPLYINDENT SREPLYANSNUM SALENO MEMBERNO
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ --------
        1 �Է��մϴ�.        ȸ��200      2017-12-18 16:30:40.0           1            0            0      3        1

 SREPLYNO SREPLYCONTENT SREPLYNAME SREPLYDATE            SREPLYGRPNO SREPLYINDENT SREPLYANSNUM SALENO MEMBERNO
 -------- ------------- ---------- --------------------- ----------- ------------ ------------ ------ --------
        1 ����Է�����        ȸ��200      2017-12-18 12:22:47.0                       1            0            0      3        1
        2 �ٵ�����̴亯�̰�Ӿȳ��� ȸ��200      2017-12-18 12:22:56.0           2            0            0      3        1
        4 ?             ȸ��200      2017-12-18 14:48:11.0                                 4            0            0      3        1
        5 ����ȵ�          ȸ��200      2017-12-18 15:19:37.0                           5            0            0      3        1
        6 �󸶿���?         ȸ��200      2017-12-18 15:25:30.0                           6            0            0      3        1
        7 4             ȸ��200      2017-12-18 16:09:03.0                                 2            0            0      3        1


SELECT sreplyno, sreplycontent, sreplyname, sreplydate, sreplygrpno, 
          sreplyindent, sreplyansnum, saleno, MEMBERNO, seqno
FROM salereply
WHERE saleno = 2 AND sreplyno = 2;

 SREPLYNO SREPLYCONTENT SREPLYNAME SREPLYDATE            SREPLYPASSWD SREPLYGRPNO SREPLYINDENT SREPLYANSNUM SALENO MEMBERNO ADMINNO
 -------- ------------- ---------- --------------------- ------------ ----------- ------------ ------------ ------ -------- -------
        2 ����ǰ �����մϴ�.    ����         2017-12-13 14:40:30.0 1234                   1            1            1      2        1       1

3. ����

UPDATE salereply
SET sreplycontent = 'Ư����!'
WHERE sreplyno = 1;

 SREPLYNO SREPLYCONTENT SREPLYNAME SREPLYDATE            SREPLYPASSWD SREPLYGRPNO SREPLYINDENT SREPLYANSNUM SALENO MEMBERNO ADMINNO
 -------- ------------- ---------- --------------------- ------------ ----------- ------------ ------------ ------ -------- -------
        1 Ư����!          ����         2017-12-13 14:40:18.0 1234                   1            1            1      2        1       1
        2 ����ǰ �����մϴ�.    ����         2017-12-13 14:40:30.0 1234                   1            1            1      2        1       1
        3 �ǰ� �δ�~        �Ʒι�        2017-12-13 14:40:31.0 1234                   1            1            1      2        1       1

4. ����

1) �Ϲ� ����� ���

DELETE FROM salereply
WHERE sreplyno = 1;

2) �θ� ���(= ������ �θ� ���)�� ��� content�� ������ �ӽû���ó��

UPDATE salereply
SET sreplycontent = '������ ����Դϴ�.'
WHERE sreplyno = 1;

5. COUNT ����

1) ���̵� �˻�

SELECT COUNT(*) as cnt
FROM salereply
WHERE memberno = 1;

 CNT
 ---
   2
   
2) �� ���ڵ� ���� ����

SELECT COUNT(sreplyno) as cnt
FROM salereply
WHERE saleno = 1;

6. ����¡ + �亯�� ���� ���� ����

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
        1 ����Է�����        ȸ��200      2017-12-18 12:22:47.0           1            0            0      3        1 1
        2 �ٵ�����̴亯�̰�Ӿȳ��� ȸ��200      2017-12-18 12:22:56.0           2            0            0      3        1 2
        7 4             ȸ��200      2017-12-18 16:09:03.0           2            0            0      3        1 3
        4 ?             ȸ��200      2017-12-18 14:48:11.0           4            0            0      3        1 4
        5 ����ȵ�          ȸ��200      2017-12-18 15:19:37.0           5            0            0      3        1 5
        6 �󸶿���?         ȸ��200      2017-12-18 15:25:30.0           6            0            0      3        1 6

7. ���ο� �亯�� �ֽ� ��� ���� �亯 �̷��

UPDATE salereply
SET sreplyansnum = sreplyansnum + 1
WHERE saleno=1 AND sreplygrpno = 1 AND sreplyansnum > 1;
