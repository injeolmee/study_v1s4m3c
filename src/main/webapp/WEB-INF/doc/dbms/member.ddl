DROP TABLE member CASCADE CONSTRAINTS;

desc member;
delete from member;
select * from member;

/**********************************/
/* Table Name: ȸ������ */
/**********************************/
CREATE TABLE member(
    memberno                          NUMBER(10)     PRIMARY KEY,
    memid                               VARCHAR2(50)     NOT NULL,
    mememail                          VARCHAR2(100)     NOT NULL,
    mempasswd                         VARCHAR2(100)     NOT NULL,
    memname                           VARCHAR2(20)     NOT NULL,
    memconfirm                        VARCHAR2(5)    DEFAULT 'N'     NOT NULL,
    memauth                           VARCHAR2(5)    DEFAULT 'B'     NOT NULL,
    membirth                          VARCHAR2(50)     NOT NULL,
    memgender                         VARCHAR2(5)    NOT NULL,
    memaddress                        VARCHAR2(100)    NOT NULL,
    memphone                          VARCHAR2(50)     NULL,
    memsns                            VARCHAR2(100)    NULL ,
    memintro                          VARCHAR2(1000)     NULL ,
    memphoto                          VARCHAR2(500)    NULL ,
    memphoto_t                        VARCHAR2(500)    NULL ,
    memsize                           NUMBER(30)     DEFAULT 0     NULL,
    mbirthvb                          VARCHAR2(5)    DEFAULT 'Y'    NULL,
    mgendervb                         VARCHAR2(5)    DEFAULT 'Y'    NULL,
    maddressvb                        VARCHAR2(5)    DEFAULT 'Y'     NULL,
    mphonevb                          VARCHAR2(5)    DEFAULT 'Y'     NULL,
    msnsvb                            VARCHAR2(5)    DEFAULT 'Y'     NULL,
    mintrovb                          VARCHAR2(5)    DEFAULT 'Y'     NULL,
    mphotovb                          VARCHAR2(5)    DEFAULT 'Y'    NULL,
    memdate                           DATE     NOT NULL
);

COMMENT ON TABLE member is 'ȸ������';
COMMENT ON COLUMN member.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN member.memid is 'ȸ�����̵�';
COMMENT ON COLUMN member.mememail is 'ȸ���̸���';
COMMENT ON COLUMN member.mempasswd is 'ȸ����й�ȣ';
COMMENT ON COLUMN member.memname is 'ȸ���̸�';
COMMENT ON COLUMN member.memconfirm is 'ȸ������Ȯ��';
COMMENT ON COLUMN member.memauth is 'ȸ������';
COMMENT ON COLUMN member.membirth is 'ȸ���������';
COMMENT ON COLUMN member.memgender is 'ȸ������';
COMMENT ON COLUMN member.memaddress is 'ȸ���ּ�';
COMMENT ON COLUMN member.memphone is 'ȸ����ȭ��ȣ';
COMMENT ON COLUMN member.memsns is 'ȸ��SNS';
COMMENT ON COLUMN member.memintro is 'ȸ���Ұ�';
COMMENT ON COLUMN member.memphoto is 'ȸ����������';
COMMENT ON COLUMN member.memphoto_t is 'ȸ�����������';
COMMENT ON COLUMN member.memsize is 'ȸ����������ũ��';
COMMENT ON COLUMN member.mbirthvb is 'ȸ�����������¿���';
COMMENT ON COLUMN member.mgendervb is 'ȸ��������¿���';
COMMENT ON COLUMN member.maddressvb is 'ȸ���ּ���¿���';
COMMENT ON COLUMN member.mphonevb is 'ȸ����ȭ��ȣ��¿���';
COMMENT ON COLUMN member.msnsvb is 'ȸ��SNS��¿���';
COMMENT ON COLUMN member.mintrovb is 'ȸ���ڱ�Ұ���¿���';
COMMENT ON COLUMN member.mphotovb is 'ȸ��������¿���';
COMMENT ON COLUMN member.memdate is 'ȸ��������';

alter table member modify(mempasswd varchar2(100));

1. INSERT    
1) ���
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test1', 'test1@gmail.com', 'swKDQnsp/We4EtkPl/YE7g==', '����',
'Y', 'U', '19891025', '��', '���� ���ϱ�', '01012345678', '�ν�Ÿ�׷�', '�ȳ��ϼ���', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test2', 'test2@gmail.com', 'swKDQnsp/We4EtkPl/YE7g==', '����2',
'Y', 'B', '19891025', '��', '���� ���ϱ�', '01012345678', '�ν�Ÿ�׷�', '�ȳ��ϼ���', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test3', 'test3@gmail.com', 'swKDQnsp/We4EtkPl/YE7g==', '������',
'Y', 'U', '19891025', '��', '���� ���ϱ�', '01012345678', '�ν�Ÿ�׷�', '�ȳ��ϼ���', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test4', 'test4@gmail.com', 'swKDQnsp/We4EtkPl/YE7g==', '������2',
'Y', 'U', '19891025', '��', '���� ���ϱ�', '01012345678', '�ν�Ÿ�׷�', '�ȳ��ϼ���', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test5', 'test5@gmail.com', 'swKDQnsp/We4EtkPl/YE7g==', '����',
'Y', 'U', '19891025', '��', '���� ���ϱ�', '01012345678', '�ν�Ÿ�׷�', '�ȳ��ϼ���', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test6', 'test6@gmail.com', 'swKDQnsp/We4EtkPl/YE7g==', '���Ҷ�',
'Y', 'U', '19891025', '��', '���� ���ϱ�', '01012345678', '�ν�Ÿ�׷�', '�ȳ��ϼ���', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);


2) �ߺ� ���̵�, �̸��� �˻� ���� SQL 
  -- 0: �ߺ� �ƴ�, 1: �ߺ�  
  SELECT COUNT(memid) as cnt
  FROM member
  WHERE memid = 'test1';
  
  SELECT COUNT(mememail) as cnt
  FROM member
  WHERE mememail = 'test10@gmail.com';

3) �̸��� ����
  UPDATE member
  SET memconfirm = 'Y'
  WHERE mememail='test10@gmail.com' AND memsignkey='ascsdawe12382347';
  
  DESC member;
  
  2. SELECT
  SELECT * FROM member;
  
  1) ȸ�� ��ü ��� 
   SELECT memberno, memid, mememail, mempasswd, memname, memsignkey, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
   memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate
   FROM member
   ORDER BY memberno ASC;
   
    MEMBERNO MEMEMAIL        MEMPASSWD MEMNAME MEMSIGNKEY       MEMCONFIRM MEMAUTH MEMBIRTH MEMGENDER MEMADDRESS MEMPHONE    MEMSNS MEMINTRO MEMPHOTO    MEMPHOTO_T    MEMSIZE MEMVISIBLE MEMDATE
 -------- --------------- --------- ------- ---------------- ---------- ------- -------- --------- ---------- ----------- ------ -------- ----------- ------------- ------- ---------- ---------------------
        1 test1@gmail.com 1234      ȸ��1     ascsdawe12382347 Y          B       19891025 ��         ���� ���ϱ� ������ 01012345678 �ν�Ÿ�׷�  �ȳ��ϼ���    myphoto.jpg myphoto_t.jpg       0 N          2017-11-17 12:08:08.0
        2 test2@gmail.com 1234      ȸ��2     ascsdawe12382347 Y          B       19891025 ��         ���� ���ϱ� ������ 01012345678 �ν�Ÿ�׷�  �ȳ��ϼ���    myphoto.jpg myphoto_t.jpg       0 N          2017-11-17 12:08:09.0
        3 test3@gmail.com 1234      ȸ��3     ascsdawe12382347 Y          B       19891025 ��         ���� ���ϱ� ������ 01012345678 �ν�Ÿ�׷�  �ȳ��ϼ���    myphoto.jpg myphoto_t.jpg       0 N          2017-11-17 12:08:10.0
    
   
  2) ȸ�� ���� ����
  SELECT memberno, memid, mememail, mempasswd, memname, memsignkey, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
  memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate
  FROM member
  WHERE memberno = 1;
  
  
  
  3. UPDATE  
  1) ȸ�� ���� ����
  UPDATE member
  SET mempasswd='swKDQnsp/We4EtkPl/YE7g=='
  WHERE memberno = 7;
  
  2) �н����� ����
  - ���� �н����� �˻�
  - DAO: public int countPasswd(int memberno, String passwd){ ... }
  SELECT count(*) as cnt
  FROM member
  WHERE memberno = 1 AND mempasswd='1234';
 
  - �н����� ����
  - DAO: public int updatePasswd(int memberno, String passwd){ ... }
  UPDATE member
  SET mempasswd='12345'
  WHERE memberno=1;
  
  
  4. DELETE  
  1) ��ü ȸ�� ����
  DELETE FROM member;
  
  2) Ư�� ȸ�� ����
  DELETE FROM member
  WHERE memberno = 8;
  
  
  5. �α��� ���� SQL 
  - DAO: public int login(String email, String passwd){ ... }
 
  - Email ������ �̿��� ��ȸ
  SELECT memberno, memid, mememail, mempasswd, memname, memsignkey, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
  memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate
  FROM member
  WHERE memid = 'test1';
   
  SELECT count(*) as cnt
  FROM member
  WHERE memid = 'test1' AND mempasswd='1234';
 
  
  6. �˻�
    - ����� ���۽� �˻��� ������� �����ϸ� ��ü ������
      ��ü �˻����� �����ϴ�.

  1) ����: 
    - WHERE memname LIKE '�մ���'
       memname �÷��� ���� '�մ���'�� ���ڵ� ���� ���

    - WHERE memname LIKE '%�մ���%'
      memname �÷��� ���� '�մ���'�� �� ���ڵ� ���� ���

    - WHERE memname LIKE '�մ���%'
      memname �÷��� ���� '�մ���'�� �����ϴ� ���ڵ� ���� ���

    - WHERE memname LIKE '%�մ���'
      memname �÷��� ���� '�մ���'�� �����ϴ� ���ڵ� ���� ���
  
      
  7. ����¡
  SELECT memberno, memid, mememail, mempasswd, memname, memsignkey, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
  memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate
  FROM member
  WHERE memname LIKE '%�մ���%'
  ORDER BY memberno DESC
  LIMIT 0, 5;

     
* �÷��� �߰�
  ALTER TABLE member 
  ADD COLUMN �߰��� �÷��� VARCHAR(100) AFTER memvisible;
  
  SELECT memberno, memid, mememail, r
  FROM (select memberno, memid, mememail, rownum as r
            from(
				            select memberno, memid, mememail
				            from member
                  )
            
  )
  where r >=4 and r <=6
  

  select memberno, memid, mememail, rownum
            from(
                    select memberno, memid, mememail
                    from member
                  )
  where rownum>=4 and rownum <=6
  
  
  