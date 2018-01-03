DROP TABLE free CASCADE CONSTRAINTS;

DELETE FROM free;

/**********************************/
/* Table Name: ���� �Խ��� */
/**********************************/
CREATE TABLE free(
    freeno                            NUMBER(7)    NOT NULL,
    freetitle                         VARCHAR2(100)    NOT NULL,
    freecontent                       VARCHAR2(1000)     NOT NULL,
    freename                          VARCHAR2(50)     NOT NULL,
    freedate                          DATE     NOT NULL,
    freecnt                           NUMBER(6)    DEFAULT 0     NOT NULL,
    freelike                          NUMBER(6)    DEFAULT 0     NOT NULL,
    freepasswd                        VARCHAR2(50)     NULL ,
    MEMBERNO                          NUMBER(10)     NULL 
);

COMMENT ON TABLE free is '���� �Խ���';
COMMENT ON COLUMN free.freeno is '�Խ��� ��ȣ';
COMMENT ON COLUMN free.freetitle is '����';
COMMENT ON COLUMN free.freecontent is '����';
COMMENT ON COLUMN free.freename is '�̸�';
COMMENT ON COLUMN free.freedate is '�����';
COMMENT ON COLUMN free.freecnt is '��ȸ��';
COMMENT ON COLUMN free.freelike is '��õ��';
COMMENT ON COLUMN free.freepasswd is '�н�����';
COMMENT ON COLUMN free.MEMBERNO is 'ȸ����ȣ';

ALTER TABLE free ADD CONSTRAINT IDX_free_PK PRIMARY KEY (freeno);
ALTER TABLE free ADD CONSTRAINT IDX_free_FK0 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);

1. ��� (INSERT)

INSERT INTO free(freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno)
VALUES ((SELECT NVL(MAX(freeno), 0)+1 as freeno FROM free), 
'TEST �Դϴ�', '���� �Ϸ�', '�մ���', sysdate, 0, 0, '1234', 1);

INSERT INTO free(freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno)
VALUES ((SELECT NVL(MAX(freeno), 0)+1 as freeno FROM free),
            '���� ��� ���ٱ�?', '�÷鵥', '����1', sysdate, 0, 0, '1234', 1);

INSERT INTO free(freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno)
VALUES ((SELECT NVL(MAX(freeno), 0)+1 as freeno FROM free), 
            '���� �ؾ��ؿ�', '�����÷���¥�Ⱦ�սȾ�', '�Ʒι�', sysdate, 0, 0, '1234', 1);

2. ��ȸ

1) ��ü ��� ��ȸ
SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
ORDER BY freeno DESC;

 FREENO FREETITLE  FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO
 ------ ---------- ----------- -------- --------------------- ------- -------- ---------- --------
      4 ���� �ؾ��ؿ�    �����÷���¥�Ⱦ�սȾ� �Ʒι�      2017-12-14 18:09:13.0       0        0 1234              1
      3 ���� ��� ���ٱ�? �÷鵥         ����1      2017-12-14 18:09:12.0       0        0 1234              1
      2 TEST �Դϴ�   ���� �Ϸ�       �մ���      2017-12-14 18:09:11.0       0        0 1234              1
      1 TEST �Դϴ�   ���� �Ϸ�       �մ���      2017-12-14 18:09:01.0       0        0 1234              1

2) Ư�� ������ ��ȸ
SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
WHERE freeno = 2
ORDER BY freeno DESC;

 FREENO FREETITLE  FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ ---------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      2 ���� ��� ���ٱ�? �÷鵥         ����1      2017-12-13 17:07:36.0       0        0 1234              1      1     1

3. ����

1) �۸� ����
UPDATE free
SET freetitle = '�ɽ��ؿ�', freecontent = '����'
WHERE freeno = 2;

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
WHERE freeno = 2;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      2 �ɽ��ؿ�      ����          ����1      2017-12-13 17:07:36.0       0        0 1234              1      1     1

4. ����

DELETE FROM free
WHERE freeno = 3;

select * FROM free ORDER BY freeno DESC;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO FREESEQNO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ --------- -----
      2 �ɽ��ؿ�      ����          ����1      2017-12-13 17:07:36.0       0        0 1234              1      1         1     1
      1 TEST �Դϴ�  ���� �Ϸ�       �մ���      2017-12-13 17:07:35.0       0        0 1234              1      1         1     1

5. ��ȸ��

1) ��ȸ�� ����

UPDATE free
SET freecnt = freecnt + 1 
WHERE freeno = 2;

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
ORDER BY freeno DESC;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      2 �ɽ��ؿ�      ����          ����1      2017-12-13 17:07:36.0       1        0 1234              1      1     1
      1 TEST �Դϴ�  ���� �Ϸ�       �մ���      2017-12-13 17:07:35.0       0        0 1234              1      1     1

3) ��ȸ�� MAX

SELECT MAX(freecnt) as cnt
FROM free;

 CNT
 ---
   1

4) ��ȸ�� MIN

SELECT MIN(freecnt) as cnt
FROM free;

 CNT
 ---
   0
   
6. ��õ��

1) ��õ�� ����

UPDATE free
SET freelike = freelike + 1
WHERE freeno = 1;

select freelike
FROM free
where freeno = 1;

 FREELIKE
 --------
        1

7. �н����� �˻�

SELECT COUNT(freeno) as cnt
FROM free
WHERE freeno = 2 AND freepasswd = '1234';

 CNT
 ---
   1

8. �˻� (title, content, name, title + content)

- �÷� ��� : freetitle, freecontent, freename

1) �˻����� �ʰ� ��ü ����� ��ȸ�ϴ� ���

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
ORDER BY freeno DESC;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      2 �ɽ��ؿ�      ����          ����1      2017-12-13 17:07:36.0       1        0 1234              1      1     1
      1 TEST �Դϴ�  ���� �Ϸ�       �մ���      2017-12-13 17:07:35.0       0        1 1234              1      1     1

2) �������� �˻�

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
WHERE freetitle like '%�ɽ�%'
ORDER BY freeno DESC;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      2 �ɽ��ؿ�      ����          ����1      2017-12-13 17:07:36.0       1        0 1234              1      1     1

3) �������� �˻�

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
WHERE freecontent like '%����%'
ORDER BY freeno DESC;

  FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      2 �ɽ��ؿ�      ����          ����1      2017-12-13 17:07:36.0       1        0 1234              1      1     1

4) �̸����� �˻�

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
WHERE freename like '%�մ���%'
ORDER BY freeno DESC;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      1 TEST �Դϴ�  ���� �Ϸ�       �մ���      2017-12-13 17:07:35.0       0        1 1234              1      1     1

5) ���� + �������� �˻�

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, freepasswd, memberno
FROM free
WHERE freetitle like '%�ɽ�%' OR freecontent like '%�ɽ�%'
ORDER BY freeno DESC;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ -----
      2 �ɽ��ؿ�      ����          ����1      2017-12-13 17:07:36.0       1        0 1234              1      1     1

9. ����¡

1) �˻��� ��ü ���ڵ� ��
SELECT COUNT(freeno) as freecnt 
FROM free
WHERE freename LIKE '%�Ʒι�%';

 FREECNT
 -------
       0

2) ����¡ ����
SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                   freepasswd, memberno, r 
FROM(
         SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                   freepasswd, memberno, rownum as r
         FROM (
                   SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                             freepasswd, memberno
                   FROM free
                   ORDER BY freeno DESC
         )
)
WHERE r >=0 and r <=1;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO R
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ ----- -
      2 �ɽ��ؿ�      ����          ����1      2017-12-13 17:07:36.0       1        0 1234              1      1     1 1

10. ��õ�� ��ȸ

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                   freepasswd, memberno, r 
FROM(
         SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                   freepasswd, memberno, rownum as r
         FROM (
                   SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                             freepasswd, memberno
                   FROM free
                   ORDER BY freelike DESC
         )
)
WHERE r >=0 and r <=3;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO R
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ ----- -
      1 TEST �Դϴ�  ���� �Ϸ�       �մ���      2017-12-13 17:07:35.0       0        1 1234              1      1     1 1
      2 �ɽ��ؿ�      ����          ����1      2017-12-13 17:07:36.0       1        0 1234              1      1     1 2

11. ��ȸ�� ��ȸ

SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                   freepasswd, memberno, r 
FROM(
         SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                   freepasswd, memberno, rownum as r
         FROM (
                   SELECT freeno, freetitle, freecontent, freename, freedate, freecnt, freelike, 
                             freepasswd, memberno
                   FROM free
                   ORDER BY freecnt DESC
         )
)
WHERE r >=0 and r <=3;

 FREENO FREETITLE FREECONTENT FREENAME FREEDATE              FREECNT FREELIKE FREEPASSWD MEMBERNO CATENO GRPNO R
 ------ --------- ----------- -------- --------------------- ------- -------- ---------- -------- ------ ----- -
      2 �ɽ��ؿ�      ����          ����1      2017-12-13 17:07:36.0       1        0 1234              1      1     1 1
      1 TEST �Դϴ�  ���� �Ϸ�       �մ���      2017-12-13 17:07:35.0       0        1 1234              1      1     1 2

12. ������ / ������

(1) ������

SELECT NVL(MAX(freeno), 1) as freeno
FROM free
WHERE freeno < 1

 FREENO
 ------
      1

(2) ������

SELECT NVL(MIN(freeno), 3) as freeno
FROM free
WHERE freeno > 3

 FREENO
 ------
      4
