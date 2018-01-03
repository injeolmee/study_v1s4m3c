DROP TABLE admin CASCADE CONSTRAINTS;
select * from admin;
delete from admin;

/**********************************/
/* Table Name: ������ */
/**********************************/
CREATE TABLE admin(
    adminno                           NUMBER(10)     PRIMARY KEY,
    admid                             VARCHAR2(50)    NOT NULL,
    admemail                          VARCHAR2(100)    NOT NULL,
    admpasswd                         VARCHAR2(50)     NOT NULL,
    admname                           VARCHAR2(20)     NOT NULL,
    admconfirm                        VARCHAR2(5)    DEFAULT 'N'     NOT NULL,
    admauth                           VARCHAR2(5)    DEFAULT 'N'     NOT NULL,
    admdate                           DATE     NOT NULL
);

COMMENT ON TABLE admin is '������';
COMMENT ON COLUMN admin.adminno is '�����ڹ�ȣ';
COMMENT ON COLUMN admin.admid is '�����ھ��̵�';
COMMENT ON COLUMN admin.admemail is '�������̸���';
COMMENT ON COLUMN admin.admpasswd is '�����ں�й�ȣ';
COMMENT ON COLUMN admin.admname is '�������̸�';
COMMENT ON COLUMN admin.admconfirm is '����������Ȯ��';
COMMENT ON COLUMN admin.admauth is '�����ڱ���';
COMMENT ON COLUMN admin.admdate is '�����ڰ�����';

    SELECT memid
    FROM member
    WHERE memname = '�մ���2'  AND mememail = 'qydrnfldy@naver.com';



1. INSERT    
1) ���
delete from admin
where adminno = 1;

INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'master', 'master@naver.com', '123456', '������', 'Y', 'M', sysdate);
INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin1', 'admin1@naver.com', '123456', '�մ���1', 'Y', 'N', sysdate);
INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin2', 'admin2@naver.com', '123456', '�մ���2', 'Y', 'A', sysdate);
INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin3', 'admin3@naver.com', '123456', '�մ��մ�', 'Y', 'A', sysdate);
INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin4', 'admin4@naver.com', '123456', '������', 'Y', 'A', sysdate);
INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin5', 'admin5@naver.com', '123456', '����1', 'Y', 'A', sysdate);
INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin6', 'admin6@naver.com', '123456', '�Ʒι�', 'Y', 'A', sysdate);
INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin7', 'admin7@naver.com', '123456', '�Ʒι̹�', 'Y', 'A', sysdate);


2) �ߺ� �̸��� �˻� ���� SQL 
-- 0: �ߺ� �ƴ�, 1: �ߺ�  
SELECT COUNT(admemail) as cnt
FROM admin
WHERE admemail = 'test10@gmail.com';

3) �̸��� ����
UPDATE admin
SET admconfirm = 'Y'
WHERE admemail='test10@gmail.com' AND admsignkey='ascsdawe12382347';  
  
4) MASTER ������ ��ȸ
SELECT COUNT(adminno) as cnt
FROM admin
WHERE admauth = 'M';
  
  select *
  from admin
  
2. SELECT
SELECT * FROM admin;
  
1) ȸ�� ��ü ��� 
SELECT adminno, admemail, admpasswd, admname, admconfirm, admauth, admdate
FROM admin
ORDER BY adminno ASC;

 ADMINNO ADMEMAIL        ADMPASSWD ADMNAME ADMSIGNKEY       ADMCONFIRM ADMAUTH ADMDATE
 ------- --------------- --------- ------- ---------------- ---------- ------- ---------------------
       1 test1@naver.com 1234      ���1    ascsdawe12382347 N          N       2017-11-17 12:11:25.0
       2 test2@naver.com 1234      ���2    ascsdawe12382347 N          N       2017-11-17 12:11:26.0
       3 test3@naver.com 1234      ���3    ascsdawe12382347 N          N       2017-11-17 12:11:27.0

   
2) ȸ�� ���� ����
SELECT adminno, admemail, admpasswd, admname, admconfirm, admauth, admdate
FROM admin
WHERE adminno = 1;
  
  
  
3. UPDATE  
1) ȸ�� ���� ����
UPDATE admin
SET admauth='M'
WHERE adminno = 1;
  
2) �н����� ����
- ���� �н����� �˻�
- DAO: public int countPasswd(int adminno, String passwd){ ... }
SELECT count(*) as cnt
FROM admin
WHERE adminno = 1 AND admpasswd='1234';
 
- �н����� ����
- DAO: public int updatePasswd(int adminno, String passwd){ ... }
UPDATE admin
SET admpasswd='12345'
WHERE adminno=1;
  
  
4. DELETE  
1) ��ü ȸ�� ����
DELETE FROM admin;
 
2) Ư�� ȸ�� ����
DELETE FROM admin
WHERE adminno = 1;
  
  
5. �α��� ���� SQL 
- DAO: public int login(String email, String passwd){ ... }
 
- Email ������ �̿��� ��ȸ
SELECT adminno, admemail, admpasswd, admname, admconfirm, admauth, admdate
FROM admin
WHERE admemail = 'test10@gmail.com';
   
SELECT count(*) as cnt
FROM admin
WHERE admemail = 'test10@gmail.com' AND admpasswd='1234';
 
  
6. �˻�
- ����� ���۽� �˻��� ������� �����ϸ� ��ü ������ ��ü �˻����� �����ϴ�.

1) ����: 
- WHERE admname LIKE '�մ���'
admname �÷��� ���� '�մ���'�� ���ڵ� ���� ���

- WHERE admname LIKE '%�մ���%'
admname �÷��� ���� '�մ���'�� �� ���ڵ� ���� ���

- WHERE admname LIKE '�մ���%'
admname �÷��� ���� '�մ���'�� �����ϴ� ���ڵ� ���� ���

- WHERE admname LIKE '%�մ���'
admname �÷��� ���� '�մ���'�� �����ϴ� ���ڵ� ���� ���
  
      
7. ����¡
SELECT adminno, admemail, admpasswd, admname, admconfirm, admauth, admdate
FROM admin
WHERE admname LIKE '%�մ���%'
ORDER BY adminno DESC
LIMIT 0, 5;

     
* �÷��� �߰�
ALTER TABLE admin 
ADD COLUMN �߰��� �÷��� VARCHAR(100) AFTER admconfirm;

