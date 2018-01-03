DROP TABLE shared CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: �ڷ�� */
/**********************************/
CREATE TABLE shared(
    sharedno                          NUMBER(7)    NOT NULL,
    sharedtitle                       VARCHAR2(100)    NOT NULL,
    sharedcontent                     VARCHAR2(1000)     NOT NULL,
    sharedname                        VARCHAR2(50)     NOT NULL,
    sharedyoutube                     VARCHAR2(500)    NULL ,
    sharedseqno                       NUMBER(10)     NOT NULL,
    sharedfile                        VARCHAR2(500)    NULL ,
    sharedfstor                       VARCHAR2(500)    NULL ,
    sharedtum                         VARCHAR2(500)    NULL ,
    sharedsize                        NUMBER(30)     DEFAULT 0     NOT NULL,
    shareddate                        DATE     NOT NULL,
    sharedcnt                         NUMBER(6)    DEFAULT 0     NOT NULL,
    sharedlike                        NUMBER(6)    DEFAULT 0     NOT NULL,
    sharedpasswd                      VARCHAR2(50)     NULL ,
    MEMBERNO                          NUMBER(10)     NULL 
);

COMMENT ON TABLE shared is '�ڷ��';
COMMENT ON COLUMN shared.sharedno is '�Խ��� ��ȣ';
COMMENT ON COLUMN shared.sharedtitle is '����';
COMMENT ON COLUMN shared.sharedcontent is '����';
COMMENT ON COLUMN shared.sharedname is '�̸�';
COMMENT ON COLUMN shared.sharedyoutube is '��Ʃ��';
COMMENT ON COLUMN shared.sharedseqno is '��� ����';
COMMENT ON COLUMN shared.sharedfile is '����';
COMMENT ON COLUMN shared.sharedfstor is '���� ���ϸ�';
COMMENT ON COLUMN shared.sharedtum is '���� �����';
COMMENT ON COLUMN shared.sharedsize is '���� ũ��';
COMMENT ON COLUMN shared.shareddate is '�����';
COMMENT ON COLUMN shared.sharedcnt is '��ȸ��';
COMMENT ON COLUMN shared.sharedlike is '��õ��';
COMMENT ON COLUMN shared.sharedpasswd is '�н�����';
COMMENT ON COLUMN shared.MEMBERNO is 'ȸ����ȣ';


ALTER TABLE shared ADD CONSTRAINT IDX_shared_PK PRIMARY KEY (sharedno);
ALTER TABLE shared ADD CONSTRAINT IDX_shared_FK0 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);


select * from shared


1. ��� -- ����Ŭ�� �Ϸù�ȣ�� �ڵ� ������ �Ұ��ϱ⶧���� NVL ��! --

-- ��ü ��� ��� --
SELECT * FROM shared 

INSERT INTO shared (sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno, sharedfile, sharedfstor, sharedtum, 
                           sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno) 
VALUES ((SELECT NVL(MAX(sharedno), 0)+1 as sharedno FROM shared),
            '���⹮�� (1)', '���⹮�� 10��ġ �Դϴ�.', '�մ���', 'http://', 
            (SELECT NVL(MAX(sharedseqno), 0) + 1 as sharedseqno FROM shared),
            'image.jpg', 'image0.jpg', 'image_tum.jgp', 1000, sysdate, 0, 0, '1234', 1);
            
INSERT INTO shared (sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno, sharedfile, sharedfstor, sharedtum, 
                           sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno) 
VALUES ((SELECT NVL(MAX(sharedno), 0)+1 as sharedno FROM shared),
            '��ົ ����', '10�⵵���� �ݿ��߾��', '����', 'http://',
            (SELECT NVL(MAX(sharedseqno), 0) + 1 as sharedseqno FROM shared ),
            'book.jpg', 'book_fs.jpg', 'book_tum.jpg', 1000, sysdate, 0, 0, '1234', 1);

INSERT INTO shared (sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno, sharedfile, sharedfstor, sharedtum, 
                           sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno) 
VALUES ((SELECT NVL(MAX(sharedno), 0)+1 as sharedno FROM shared),
            '���߿Ϸ�', '�ҽ��÷Ⱦ��.', '�Ʒι�', 'http://',
            (SELECT NVL(MAX(sharedseqno), 0) + 1 as sharedseqno FROM shared ),
            'source2.jpg', 'source2_fs.jpg', 'source2_tum.jpg', 1000, sysdate, 0, 0, '1234', 2);
            
2. ��ȸ

1) ��ü ��� ��ȸ
SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
FROM shared
ORDER BY sharedno DESC;

 SHAREDNO SHAREDTITLE SHAREDCONTENT  SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE  SHAREDFSTOR    SHAREDTUM       SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- -------------- ---------- ------------- ----------- ----------- -------------- --------------- ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        3 ���߿Ϸ�        �ҽ��÷Ⱦ��.        �Ʒι�        http://                 3 source2.jpg source2_fs.jpg source2_tum.jpg       1000 2017-12-13 17:20:09.0         0          0 1234                1      3     1
        2 ��ົ ����      10�⵵���� �ݿ��߾��   ����         http://                 2 book.jpg    book_fs.jpg    book_tum.jpg          1000 2017-12-13 17:20:08.0         0          0 1234                1      3     1
        1 ���⹮�� (1)    ���⹮�� 10��ġ �Դϴ�. �մ���        http://                 1 image.jpg   image0.jpg     image_tum.jgp         1000 2017-12-13 17:20:07.0         0          0 1234                1      3     1

2) 1���� �� ����
SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
FROM shared
WHERE sharedno = 1
ORDER BY sharedno DESC;

 SHAREDNO SHAREDTITLE SHAREDCONTENT  SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE SHAREDFSTOR SHAREDTUM     SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- -------------- ---------- ------------- ----------- ---------- ----------- ------------- ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        1 ���⹮�� (1)    ���⹮�� 10��ġ �Դϴ�. �մ���        http://                 1 image.jpg  image0.jpg  image_tum.jgp       1000 2017-12-13 17:20:07.0         0          0 1234                1      3     1

3. ����

UPDATE shared
SET sharedtitle = '���⹮�� �����Ϸ�', sharedcontent = '�浿�� �����߾��!',  sharedyoutube = '',
     sharedfile='test.jpg', sharedfstor='test_fs.jpg', sharedsize=105, sharedtum = 'test_tum.jpg'
WHERE sharedno = 1;

SELECT * FROM shared WHERE sharedno = 1;
SELECT * FROM shared WHERE sharedno = 2;
SELECT * FROM shared WHERE sharedno = 3;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE SHAREDFSTOR SHAREDTUM    SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- ------------- ---------- ------------- ----------- ---------- ----------- ------------ ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        1 ���⹮�� �����Ϸ�   �浿�� �����߾��!    �մ���        http://                 1 test.jpg   test_fs.jpg test_tum.jpg        105 2017-12-13 17:20:07.0         0          0 1234                1      3     1

4. ��ȸ�� ����

UPDATE shared
SET sharedcnt = sharedcnt + 1
WHERE sharedno = 1;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE SHAREDFSTOR SHAREDTUM    SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- ------------- ---------- ------------- ----------- ---------- ----------- ------------ ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        1 ���⹮�� �����Ϸ�   �浿�� �����߾��!    �մ���        http://                 1 test.jpg   test_fs.jpg test_tum.jpg        105 2017-12-13 17:20:07.0         1          0 1234                1      3     1

5. ��õ�� ����

UPDATE shared
SET sharedlike = sharedlike + 1
WHERE sharedno = 1;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDMP3 SHAREDMP4 SHAREDFILE SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO ADMINNO
 -------- ----------- ------------- ---------- ------------- ----------- --------- --------- ---------- ---------- --------------------- --------- ---------- ------------ -------- ------ -------
        1 ���⹮�� �����Ϸ�   �浿�� �����߾��!    ������        http://                 1 NULL      movie.mp4 image.jpg        1000 2017-11-21 17:46:43.0         1          1 1234                1     42       1

6. �н����� �˻�

SELECT COUNT(*) as sharedcnt 
FROM shared
WHERE sharedno=1 AND sharedpasswd='1234';

 SHAREDCNT
 ---------
         1
         
7. �˻�

- �÷� ��� : sharedtitle, sharedcontent, sharedname

1) �˻��� ���� �ʰ� ��ü ����� ��ȸ�ϴ� ���

SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
FROM shared
ORDER BY sharedno DESC;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE  SHAREDFSTOR    SHAREDTUM       SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- ------------- ---------- ------------- ----------- ----------- -------------- --------------- ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        3 ���߿Ϸ�        �ҽ��÷Ⱦ��.       �Ʒι�        http://                 3 source2.jpg source2_fs.jpg source2_tum.jpg       1000 2017-12-13 17:20:09.0         0          0 1234                1      3     1
        2 ��ົ ����      10�⵵���� �ݿ��߾��  ����         http://                 2 book.jpg    book_fs.jpg    book_tum.jpg          1000 2017-12-13 17:20:08.0         0          0 1234                1      3     1
        1 ���⹮�� �����Ϸ�   �浿�� �����߾��!    �մ���        http://                 1 test.jpg    test_fs.jpg    test_tum.jpg           105 2017-12-13 17:20:07.0         1          1 1234                1      3     1

2) �̸����� �˻�

SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
FROM shared
WHERE sharedname LIKE '%�Ʒι�%'
ORDER BY sharedno DESC;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE  SHAREDFSTOR    SHAREDTUM       SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO ADMINNO
 -------- ----------- ------------- ---------- ------------- ----------- ----------- -------------- --------------- ---------- --------------------- --------- ---------- ------------ -------- ------ -------
        3 ���߿Ϸ�        �ҽ��÷Ⱦ��.       �Ʒι�        http://                 3 source2.jpg source2_fs.jpg source2_tum.jpg       1000 2017-12-04 10:29:31.0         0          0 1234                3     42       1


3) �������� �˻�

SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
FROM shared
WHERE sharedtitle LIKE '%����%'
ORDER BY sharedno DESC;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE SHAREDFSTOR SHAREDTUM    SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- ------------- ---------- ------------- ----------- ---------- ----------- ------------ ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        1 ���⹮�� �����Ϸ�   �浿�� �����߾��!    �մ���        http://                 1 test.jpg   test_fs.jpg test_tum.jpg        105 2017-12-13 17:20:07.0         1          1 1234                1      3     1

4) �������� �˻�

SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
FROM shared
WHERE sharedcontent LIKE '%�ҽ�%'
ORDER BY sharedno DESC;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE  SHAREDFSTOR    SHAREDTUM       SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO ADMINNO
 -------- ----------- ------------- ---------- ------------- ----------- ----------- -------------- --------------- ---------- --------------------- --------- ---------- ------------ -------- ------ -------
        3 ���߿Ϸ�        �ҽ��÷Ⱦ��.       �Ʒι�        http://                 3 source2.jpg source2_fs.jpg source2_tum.jpg       1000 2017-12-04 10:29:31.0         0          0 1234                3     42       1

5) ���� + �������� �˻�

SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
FROM shared
WHERE sharedtitle = '%�ݿ�%' OR sharedcontent LIKE '%�ݿ�%'
ORDER BY sharedno DESC;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE SHAREDFSTOR SHAREDTUM    SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- ------------- ---------- ------------- ----------- ---------- ----------- ------------ ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        2 ��ົ ����      10�⵵���� �ݿ��߾��  ����         http://                 2 book.jpg   book_fs.jpg book_tum.jpg       1000 2017-12-13 17:20:08.0         0          0 1234                1      3     1

8. ����¡

1) �˻��� ��ü ���ڵ� ��

SELECT COUNT(sharedno) as cnt 
FROM shared
WHERE sharedname LIKE '%�Ʒι�%';

  CNT
 ---
   1

2) ����¡ ����

SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
          sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno, r
FROM( 
         SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
                   sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno, rownum as r
         FROM (
                  SELECT sharedno, sharedtitle, sharedcontent, sharedname, sharedyoutube, sharedseqno,
                            sharedfile, sharedfstor, sharedtum, sharedsize, shareddate, sharedcnt, sharedlike, sharedpasswd, memberno
                  FROM shared
                  ORDER BY sharedno DESC
         )
)
WHERE rownum >=0 and rownum <=2;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE  SHAREDFSTOR    SHAREDTUM       SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO R
 -------- ----------- ------------- ---------- ------------- ----------- ----------- -------------- --------------- ---------- --------------------- --------- ---------- ------------ -------- ------ ----- -
        3 ���߿Ϸ�        �ҽ��÷Ⱦ��.       �Ʒι�        http://                 3 source2.jpg source2_fs.jpg source2_tum.jpg       1000 2017-12-13 17:20:09.0         0          0 1234                1      3     1 1
        2 ��ົ ����      10�⵵���� �ݿ��߾��  ����         http://                 2 book.jpg    book_fs.jpg    book_tum.jpg          1000 2017-12-13 17:20:08.0         0          0 1234                1      3     1 2

9. ����

DELETE FROM shared
WHERE sharedno = 1;

select * from shared;

 SHAREDNO SHAREDTITLE SHAREDCONTENT SHAREDNAME SHAREDYOUTUBE SHAREDSEQNO SHAREDFILE  SHAREDFSTOR    SHAREDTUM       SHAREDSIZE SHAREDDATE            SHAREDCNT SHAREDLIKE SHAREDPASSWD MEMBERNO CATENO GRPNO
 -------- ----------- ------------- ---------- ------------- ----------- ----------- -------------- --------------- ---------- --------------------- --------- ---------- ------------ -------- ------ -----
        2 ��ົ ����      10�⵵���� �ݿ��߾��  ����         http://                 2 book.jpg    book_fs.jpg    book_tum.jpg          1000 2017-12-13 17:20:08.0         0          0 1234                1      3     1
        3 ���߿Ϸ�        �ҽ��÷Ⱦ��.       �Ʒι�        http://                 3 source2.jpg source2_fs.jpg source2_tum.jpg       1000 2017-12-13 17:20:09.0         0          0 1234                1      3     1

10. ������ / ������

1) ������

SELECT MAX(sharedno) as sharedno
FROM shared
WHERE sharedno < 1
ORDER BY sharedno DESC;

 SHAREDNO
 --------
        2
        
2) ������

SELECT MIN(sharedno) as sharedno
FROM shared
WHERE sharedno > 3
ORDER BY sharedno DESC;

 SHAREDNO
 --------
        4


