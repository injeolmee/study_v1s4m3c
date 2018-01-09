DROP TABLE sale CASCADE CONSTRAINTS;
DROP TABLE free CASCADE CONSTRAINTS;
DROP TABLE shared CASCADE CONSTRAINTS;
DROP TABLE admin;
DROP TABLE member;

/**********************************/
/* Table Name: �ŷ� �Խ��� */
/**********************************/
CREATE TABLE sale(
    saleno                            NUMBER(7)    NOT NULL,
    saletitle                         VARCHAR2(100)    NOT NULL,
    salecontent                       VARCHAR2(1000)     NOT NULL,
    salename                          VARCHAR2(50)     NOT NULL,
    saletname                        VARCHAR2(100)    NOT NULL,
    saledate                          DATE     NOT NULL,
    salecnt                           NUMBER(6)    DEFAULT 0     NOT NULL,
    saleprice                         NUMBER(30)     NOT NULL,
    saleaddress                       VARCHAR2(50)     NULL ,
    saletel                           VARCHAR2(50)     NULL ,
    saleemail                         VARCHAR2(50)     NULL ,
    salefile                          VARCHAR2(500)    NULL ,
    salefstor                         VARCHAR2(500)    NULL ,
    saletum                           VARCHAR2(500)    NULL ,
    salesize                          NUMBER(30)     DEFAULT 0     NOT NULL,
    saleseqno                         NUMBER(10)     DEFAULT 1     NOT NULL,
    MEMBERNO                          NUMBER(10)     NULL ,
    ADMINNO                           NUMBER(10)     NULL 
);

COMMENT ON TABLE sale is '�ŷ� �Խ���';
COMMENT ON COLUMN sale.saleno is '�Խ��� ��ȣ';
COMMENT ON COLUMN sale.saletitle is '����';
COMMENT ON COLUMN sale.salecontent is '����';
COMMENT ON COLUMN sale.salename is '�̸�';
COMMENT ON COLUMN sale.saletname is '��ǰ��';
COMMENT ON COLUMN sale.saledate is '�����';
COMMENT ON COLUMN sale.salecnt is '��ȸ��';
COMMENT ON COLUMN sale.saleprice is '����';
COMMENT ON COLUMN sale.saleaddress is '�ּ�';
COMMENT ON COLUMN sale.saletel is '�޴��� ��ȣ';
COMMENT ON COLUMN sale.saleemail is '�̸���';
COMMENT ON COLUMN sale.salefile is '����';
COMMENT ON COLUMN sale.salefstor is '���� ���ϸ�';
COMMENT ON COLUMN sale.saletum is '���� �����';
COMMENT ON COLUMN sale.salesize is '���� ũ��';
COMMENT ON COLUMN sale.saleseqno is '��� ����';
COMMENT ON COLUMN sale.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN sale.ADMINNO is '�����ڹ�ȣ';

ALTER TABLE sale ADD CONSTRAINT IDX_sale_PK PRIMARY KEY (saleno);
ALTER TABLE sale ADD CONSTRAINT IDX_sale_FK0 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);
ALTER TABLE sale ADD CONSTRAINT IDX_sale_FK1 FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO);

1. ���

INSERT INTO sale (saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice, saleaddress, 
                        saletel, saleemail, salepasswd, salefile, salefstor, saletum, salesize, saleseqno, memberno)
VALUES ((SELECT NVL(MAX(saleno), 0) + 1 as saleno from sale), '�˴ϴ�.', '���⹮����', '��۳����ּ���.', '����',
           sysdate, 0, 10000, '����� ������ 3��', '01022222222', 'tkekm@naver.com',
           '1234', 'tell.jpg', 'tell(0).jpg', 'tell_t.jsp', 1000, 1, 1);
           
INSERT INTO sale (saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice, saleaddress, 
                        saletel, saleemail, salepasswd, salefile, salefstor, saletum, salesize, saleseqno, memberno)
VALUES ((SELECT NVL(MAX(saleno), 0) + 1 as saleno from sale), '�߰�å ���.', '����1', '��۷�.', '������',
           sysdate, 0, 13000, '����� ������ 2��', '01033332222', 'tkekasuam@naver.com',
           '1234', 'te.jpg', 'te(0).jgp', 'te_t.jsp', 130, 1, 1);
           
INSERT INTO sale (saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice, saleaddress, 
                        saletel, saleemail, salepasswd, salefile, salefstor, saletum, salesize, saleseqno, memberno)
VALUES ((SELECT NVL(MAX(saleno), 0) + 1 as saleno from sale), '�ۡ�å ���.', '����ó����� �ó���', '��ϴ�.', '����',
           sysdate, 0, 10000, '����� ��ȭ 3��', '01022536222', 'gisla@naver.com',
           '1234', 'll.jpg', 'll(0).jgp', 'l_t.jsp', 1000, 1, 1);
           
2. ��ȸ

1) ��ü ��� ��ȸ
SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice, saleaddress, 
          saletel, saleemail, salefile, salefstor, saletum, salesize, saleseqno, memberno
FROM sale
ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALETNAME SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL           SALEPASSWD SALEFILE SALEFSTOR   SALETUM    SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- --------- -------- --------------------- ------- --------- ----------- ----------- ------------------- ---------- -------- ----------- ---------- -------- --------- -------- ------ -----
      3 �ۡ�å ���.  ����ó����� �ó���  ��ϴ�.      ����       2017-12-13 17:16:10.0       0     10000 ����� ��ȭ 3��   01022536222 gisla@naver.com     1234       ll.jpg   ll(0).jgp   l_t.jsp        1000         1        1      2     1
      2 �߰�å ���.   ����1         ��۷�.      ������      2017-12-13 17:15:19.0       0     13000 ����� ������ 2��  01033332222 tkekasuam@naver.com 1234       te.jpg   te(0).jgp   te_t.jsp        130         1        1      2     1
      1 �˴ϴ�.      ���⹮����       ��۳����ּ���.  ����       2017-12-13 17:15:14.0       0     10000 ����� ������ 3��  01022222222 tkekm@naver.com     1234       tell.jpg tell(0).jpg tell_t.jsp     1000         1        1      2     1

2) 1���� ��ȸ

SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice, saleaddress, 
          saletel, saleemail, salepasswd, salefile, salefstor, saletum, salesize, saleseqno, memberno
FROM sale
WHERE saleno = 1
ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALETNAME SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL       SALEPASSWD SALEFILE SALEFSTOR   SALETUM    SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- --------- -------- --------------------- ------- --------- ----------- ----------- --------------- ---------- -------- ----------- ---------- -------- --------- -------- ------ -----
      1 �˴ϴ�.      ���⹮����       ��۳����ּ���.  ����       2017-12-13 17:15:14.0       0     10000 ����� ������ 3��  01022222222 tkekm@naver.com 1234       tell.jpg tell(0).jpg tell_t.jsp     1000         1        1      2     1

3. ����

UPDATE sale
SET saletitle = '���߾��', salecontent = '�������', saletname='�Ǹŵ� ��ǰ�Դϴ�.', salename = '�մ���2', saleprice = 0,
     saleaddress = '�����', saletel = '�����', saleemail='rh@naver.com', salefile = null, salefstor = null,
     saletum = null, salesize = 0, saleseqno = 1
WHERE saleno = 1;

 SALENO SALETITLE SALECONTENT SALETNAME  SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL SALEEMAIL    SALEPASSWD SALEFILE  SALEFSTOR    SALETUM    SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- ---------- -------- --------------------- ------- --------- ----------- ------- ------------ ---------- --------- ------------ ---------- -------- --------- -------- ------ -----
      1 ���߾��      �������        �Ǹŵ� ��ǰ�Դϴ�. �մ���2     2017-12-13 17:15:14.0       0         0 �����         �����     rh@naver.com 1234       none1.jpg none1(0).jpg none01.jpg        0         1        1      2     1

4. ����

1) ����
delete FROM sale 
WHERE saleno = 2;

2) ���� ��� Ȯ��
select * from sale ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALENAME SALETNAME  SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL       SALEPASSWD SALEFILE  SALEFSTOR    SALETUM    SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- -------- ---------- --------------------- ------- --------- ----------- ----------- --------------- ---------- --------- ------------ ---------- -------- --------- -------- ------ -----
      3 �ۡ�å ���.  ����ó����� �ó���  ����       ��ϴ�.       2017-12-13 17:16:10.0       0     10000 ����� ��ȭ 3��   01022536222 gisla@naver.com 1234       ll.jpg    ll(0).jgp    l_t.jsp        1000         1        1      2     1
      1 ���߾��      �������        �մ���2     �Ǹŵ� ��ǰ�Դϴ�. 2017-12-13 17:15:14.0       0         0 �����         �����         rh@naver.com    1234       none1.jpg none1(0).jpg none01.jpg        0         1        1      2     1

5. ��ȸ�� ����

UPDATE sale
SET salecnt = salecnt + 1
WHERE saleno = 1;

 SALENO SALETITLE SALECONTENT SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL SALEEMAIL    SALEPASSWD SALEFILE  SALETUM    SALESIZE SALESEQNO MEMBERNO CATENO ADMINNO
 ------ --------- ----------- -------- --------------------- ------- --------- ----------- ------- ------------ ---------- --------- ---------- -------- --------- -------- ------ -------
      1 ���߾��      �������        �մ���2     2017-11-15 18:21:02.0       1         0 �����         �����     rh@naver.com 1234       none1.jpg none01.jpg        0         1     NULL   NULL    NULL

6. �н����� �˻�

SELECT COUNT(*) as salecnt
FROM sale
WHERE salepasswd = '1234';

 SALECNT
 -------
       2
       
7. �˻�

- �÷� ���: saletitle, salecontent, salename

1) �˻����� �ʰ� ��ü ����� ��ȸ�ϴ� ���

SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
          saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
          salesize, saleseqno, memberno
FROM sale
ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL       SALEPASSWD SALEFILE  SALETUM    SALESIZE SALESEQNO MEMBERNO CATENO ADMINNO
 ------ --------- ----------- -------- --------------------- ------- --------- ----------- ----------- --------------- ---------- --------- ---------- -------- --------- -------- ------ -------
      3 �ۡ�å ���.  ��ϴ�.        ����       2017-11-21 17:54:43.0       0     10000 ����� ��ȭ 3��   01022536222 gisla@naver.com 1234       gal.jpg   gel01.jpg      1000         1        3     41       2
      1 ���߾��      �������        �մ���2     2017-11-21 17:54:41.0       1         0 �����         �����         rh@naver.com    1234       none1.jpg none01.jpg        0         1        1     41       2

2) �������� �˻�

SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
          saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
          salesize, saleseqno, memberno
FROM sale
WHERE saletitle like '%å%'
ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALETNAME SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL       SALEPASSWD SALEFILE SALETUM SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- --------- -------- --------------------- ------- --------- ----------- ----------- --------------- ---------- -------- ------- -------- --------- -------- ------ -----
      3 �ۡ�å ���.  ����ó����� �ó���  ��ϴ�.      ����       2017-12-13 17:16:10.0       0     10000 ����� ��ȭ 3��   01022536222 gisla@naver.com 1234       ll.jpg   l_t.jsp     1000         1        1      2     1

3) �������� �˻�

SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
          saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
          salesize, saleseqno, memberno
FROM sale
WHERE salecontent like '%���%'
ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALETNAME  SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL SALEEMAIL    SALEPASSWD SALEFILE  SALETUM    SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- ---------- -------- --------------------- ------- --------- ----------- ------- ------------ ---------- --------- ---------- -------- --------- -------- ------ -----
      1 ���߾��      �������        �Ǹŵ� ��ǰ�Դϴ�. �մ���2     2017-12-13 17:15:14.0       1         0 �����         �����     rh@naver.com 1234       none1.jpg none01.jpg        0         1        1      2     1

4) �̸����� �˻�

SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
          saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
          salesize, saleseqno, memberno
FROM sale
WHERE salename like '%����%'
ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALETNAME SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL       SALEPASSWD SALEFILE SALETUM SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- --------- -------- --------------------- ------- --------- ----------- ----------- --------------- ---------- -------- ------- -------- --------- -------- ------ -----
      3 �ۡ�å ���.  ����ó����� �ó���  ��ϴ�.      ����       2017-12-13 17:16:10.0       0     10000 ����� ��ȭ 3��   01022536222 gisla@naver.com 1234       ll.jpg   l_t.jsp     1000         1        1      2     1

5) ���� + �������� �˻�

SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
          saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
          salesize, saleseqno, memberno
FROM sale
WHERE saletitle like '%å%' OR salecontent like '%å%'
ORDER BY saleno DESC;

 SALENO SALETITLE SALECONTENT SALETNAME SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL       SALEPASSWD SALEFILE SALETUM SALESIZE SALESEQNO MEMBERNO CATENO GRPNO
 ------ --------- ----------- --------- -------- --------------------- ------- --------- ----------- ----------- --------------- ---------- -------- ------- -------- --------- -------- ------ -----
      3 �ۡ�å ���.  ����ó����� �ó���  ��ϴ�.      ����       2017-12-13 17:16:10.0       0     10000 ����� ��ȭ 3��   01022536222 gisla@naver.com 1234       ll.jpg   l_t.jsp     1000         1        1      2     1

8. ����¡

1) �˻��� ��ü ���ڵ� ��

SELECT COUNT(saleno) as salecnt 
FROM sale
WHERE salename LIKE '%����%';

 SALECNT
 -------
       1
       
2) ����¡ ����
SELECT  saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
                   saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
                   salesize, saleseqno, memberno, r
FROM( 
         SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
                   saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
                   salesize, saleseqno, memberno, rownum as r
         FROM (
                  SELECT saleno, saletitle, salecontent, saletname, salename, saledate, salecnt, saleprice,
                            saleaddress, saletel, saleemail, salepasswd, salefile, saletum,
                            salesize, saleseqno, memberno
                  FROM sale
                  ORDER BY saleno DESC)
)
WHERE r >=0 and r <=1;

 SALENO SALETITLE SALECONTENT SALETNAME SALENAME SALEDATE              SALECNT SALEPRICE SALEADDRESS SALETEL     SALEEMAIL       SALEPASSWD SALEFILE SALETUM SALESIZE SALESEQNO MEMBERNO CATENO GRPNO R
 ------ --------- ----------- --------- -------- --------------------- ------- --------- ----------- ----------- --------------- ---------- -------- ------- -------- --------- -------- ------ ----- -
      3 �ۡ�å ���.  ����ó����� �ó���  ��ϴ�.      ����       2017-12-13 17:16:10.0       0     10000 ����� ��ȭ 3��   01022536222 gisla@naver.com 1234       ll.jpg   l_t.jsp     1000         1        1      2     1 1

