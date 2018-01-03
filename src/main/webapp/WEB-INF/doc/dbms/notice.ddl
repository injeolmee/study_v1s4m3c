DROP TABLE notice CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: ������ �������� �Խ��� */
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

COMMENT ON TABLE notice is '������ �������� �Խ���';
COMMENT ON COLUMN notice.noticeno is '�������׹�ȣ';
COMMENT ON COLUMN notice.ntitle is '������������';
COMMENT ON COLUMN notice.nname is '���������ۼ���';
COMMENT ON COLUMN notice.ncontent is '�������׳���';
COMMENT ON COLUMN notice.nseqno is '����������¼���';
COMMENT ON COLUMN notice.ncnt is '����������ȸ��';
COMMENT ON COLUMN notice.ndate is '�������׵����';
COMMENT ON COLUMN notice.adminno is '�����ڹ�ȣ';




1. INSERT
INSERT INTO admnotice(anoticeno, anoticetitle, anoticecontent, anoticeseqno, anoticecnt, anoticedate, adminno, cateno)
VALUES((SELECT NVL(MAX(anoticeno), 0)+1 as anoticeno FROM admnotice), '���� ���� �ð� �ȳ�1', '02:00 ~ 06:00 ���� ������ �ֽ��ϴ�1', 1, 0, sysdate, 1, 51);
INSERT INTO admnotice(anoticeno, anoticetitle, anoticecontent, anoticeseqno, anoticecnt, anoticedate, adminno, cateno)
VALUES((SELECT NVL(MAX(anoticeno), 0)+1 as anoticeno FROM admnotice), '���� ���� �ð� �ȳ�2', '02:00 ~ 06:00 ���� ������ �ֽ��ϴ�2', 1, 0, sysdate, 1, 51);
INSERT INTO admnotice(anoticeno, anoticetitle, anoticecontent, anoticeseqno, anoticecnt, anoticedate, adminno, cateno)
VALUES((SELECT NVL(MAX(anoticeno), 0)+1 as anoticeno FROM admnotice), '���� ���� �ð� �ȳ�3', '02:00 ~ 06:00 ���� ������ �ֽ��ϴ�3', 1, 0, sysdate, 1, 51);


2. SELECT
SELECT anoticeno, anoticetitle, anoticecontent, anoticeseqno, anoticecnt, anoticedate, adminno, cateno
FROM admnotice 
ORDER BY anoticeno ASC;

ANOTICENO ANOTICETITLE ANOTICECONTENT             ANOTICESEQNO ANOTICECNT ANOTICEDATE           ADMINNO CATENO
 --------- ------------ -------------------------- ------------ ---------- --------------------- ------- ------
         1 ���� ���� �ð� �ȳ�1 02:00 ~ 06:00 ���� ������ �ֽ��ϴ�1            1          0 2017-11-17 12:21:56.0       1     51
         2 ���� ���� �ð� �ȳ�2 02:00 ~ 06:00 ���� ������ �ֽ��ϴ�2            1          0 2017-11-17 12:21:57.0       1     51
         3 ���� ���� �ð� �ȳ�3 02:00 ~ 06:00 ���� ������ �ֽ��ϴ�3            1          0 2017-11-17 12:21:58.0       1     51


SELECT anoticeno, anoticetitle, anoticecontent, anoticeseqno, anoticecnt, anoticedate, adminno, cateno
FROM admnotice 
WHERE anoticeno=1;


3. UPDATE
UPDATE admnotice
SET anoticetitle= '�������� ����', anoticecontent= '������ �ֽ��ϴ�', anoticeseqno=2
WHERE anoticeno=1;


4. DELETE
DELETE FROM admnotice;

DELETE FROM admnotice WHERE anoticeno = 1;


5. ��� �켱 ���� 
1) ����, 10 -> 1
UPDATE admnotice 
SET anoticeseqno = anoticeseqno - 1 
WHERE anoticeno=1; 
 
2) ����, 1 -> 10
UPDATE admnotice 
SET anoticeseqno = anoticeseqno + 1 
WHERE anoticeno=1;


6. ����¡
SELECT anoticeno, anoticetitle, anoticecontent, anoticeseqno, anoticecnt, anoticedate, adminno, cateno
FROM admnotice
WHERE anoticetitle LIKE '%�մ���%'
ORDER BY anoticeno DESC anoticeseqno ASC
LIMIT 0, 5;
