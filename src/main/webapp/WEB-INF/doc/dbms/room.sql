0) ���̺� ����
DROP TABLE admin;
DROP TABLE rvlike CASCADE CONSTRAINTS;
DROP TABLE review CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;

1) ���̺� ����
CREATE TABLE room(
    rono                              NUMBER(10)     NOT NULL ,
    adminno                           NUMBER(10)     NULL ,
    roname                            VARCHAR2(100)    NOT NULL,
    rotel                             VARCHAR2(30)     NOT NULL,
    rosite                            VARCHAR2(200)    NULL ,
    rorunday                          VARCHAR2(100)    NULL ,
    romap                             VARCHAR2(1000)    NOT NULL,
    rolocation                        VARCHAR2(100)     NOT NULL,
    rocity                        VARCHAR2(20)     NOT NULL,
    rogu                            VARCHAR2(20)     NOT NULL,
    rodong                        VARCHAR2(100)      NULL,
    ronalo                         VARCHAR2(200)      NULL,
    rocost                            VARCHAR2(50)     NULL ,
    rofile1                           VARCHAR2(800)    NULL ,
    rosize1                           NUMBER(10)    NULL ,
    rothumb                           VARCHAR2(800)    NULL ,
    rocontent                         VARCHAR2(1000)     NOT NULL,
    rocount                           VARCHAR2(300)    NULL ,
    rooption                          VARCHAR2(150)    NULL,
  PRIMARY KEY (rono),
  FOREIGN KEY (adminno) REFERENCES admin (adminno)
);

COMMENT ON TABLE room is '���͵��';
COMMENT ON COLUMN room.rono is '����Ƽ�� ��� ��ȣ';
COMMENT ON COLUMN room.adminno is '�����ڹ�ȣ';
COMMENT ON COLUMN room.roname is '���͵�� ��ȣ';
COMMENT ON COLUMN room.rotel is '���͵�� ��ȭ';
COMMENT ON COLUMN room.rosite is '���͵�� Ȩ������';
COMMENT ON COLUMN room.rorunday is '���͵�� �����/�ð�';
COMMENT ON COLUMN room.romap is '���͵�� ����';
COMMENT ON COLUMN room.rolocation is '���͵�� ��ġ';
COMMENT ON COLUMN room.rocost is '���͵�� ���';
COMMENT ON COLUMN room.rofile1 is '���͵�� �̹���';
COMMENT ON COLUMN room.rosize1 is '���͵�� �̹���ũ��';
COMMENT ON COLUMN room.rothumb is '���͵�� �����';
COMMENT ON COLUMN room.rocontent is '���͵�� ����';
COMMENT ON COLUMN room.rocount is '���͵�� ���ο�';
COMMENT ON COLUMN room.rooption is '���͵�� �ɼ�';

2) ����
INSERT INTO room(rono, adminno, roname, rotel, rosite, rorunday, romap,
                                 rolocation, rocity, rogu, rodong, rocost, rofile1, rosize1, rothumb, rocontent, rocount, rooption)
VALUES((SELECT NVL(MAX(rono), 0) + 1 as rono FROM room), 1, 'ī�� ��ȣ��', '��ȭ', 'Ȩ������', '��ð�', '����',
            '��ġ', '����', '��', '��', '���', '�̹���', 0, '���͵�� �����', '����', '�ο���', '�ɼ�');
   
            
3) ��� 
SELECT rono, adminno, roname, rotel, rosite, rorunday, romap, 
          rolocation, rocost, rofile1, rosize1, rothumb, rocontent, rocount, rooption
FROM room
ORDER BY rono DESC;

select rono, rolocation, rocity, rogu, rodong
from room;

 RONO     ROLOCATION     ROCITY     ROGU RODONG
 ----         --------------        ------        ----    ------------------------
    6       ����� ������ ������    �����    ������  ������
    2 ����� ������ ����     �����    ������  ����
    4 �泲 ���� ����     �泲     ����  ����
    3 ����� ������ ������ 1�� �����    ������  ������ 1��
    5 ����� ������ d      �����    ������  d
    7 ����� ������ ������    �����    ������  ������
   10 ��õ             ��ȭ��    ��ȭ��  �ֵ���ũ 24�� ��õ ��ȭ�� ��ȭ��
    8 ������ ���� ����    ������    ����  ����
    9 �泲             ������    ������  �ֵ���ũ 24�� �泲 ������ ������
   11 �λ걤����          ������    ������  �ֵ���ũ 24�� �λ걤���� ������ ������
   12 ��õ             ����     ������  �ֵ���ũ 24�� ��õ ���� ������
   13 �����            ������    1234 �ֵ���ũ 24��123 ����� ������ 1234

SELECT rono, rooption
FROM room
ORDER BY rono DESC;

select *
from room;

on delete from room
where rono = 3;

4) ����
UPDATE room
SET roname='', rotel='', rosite='', rorunday='', romap='', 
      rolocation='', rocost='', rofile1='', rosize1=0, rothumb='', rocontent='', rooption=''
WHERE rono=1;

4-1) �� ����
ALTER TABLE room
MODIFY (rooption VARCHAR2(150)  NULL);

4-2) �� ����
ALTER TABLE room
ADD (rocity VARCHAR2(20) NOT NULL);

ALTER TABLE room
ADD (rogu VARCHAR2(20) NOT NULL);

ALTER TABLE room
ADD (rodong VARCHAR2(100) NULL);

ALTER TABLE room
ADD (ronalo VARCHAR2(200) NULL);
 
5) ����
DELETE FROM room ON CASCADE CONSTRAINTS
WHERE rono = 3;

6) �˻�
SELECT rono, adminno, cateno, roname, rotel, rosite, rorunday, romap, 
          rolocation, rocost, rofile1, rosize1, rothumb, rocontent, rocount, rooption
FROM blog
WHERE cateno=7 AND location LIKE '%����%'
ORDER BY rono DESC;

7) ����¡
SELECT rono, adminno, cateno, roname, rotel, rosite, rorunday, romap, 
          rolocation, rocost, rofile1, rosize1, rothumb, rocontent, rocount, rooption, r
FROM(
         SELECT rono, adminno, cateno, roname, rotel, rosite, rorunday, romap, 
                   rolocation, rocost, rofile1, rosize1, rothumb, rocontent, rocount, rooption, rownum as r
         FROM(
                  SELECT rono, adminno, cateno, roname, rotel, rosite, rorunday, romap, 
                            rolocation, rocost, rofile1, rosize1, rothumb, rocontent, rocount, rooption
                  FROM room
                  WHERE cateno=20
                  ORDER BY rono DESC
         )
)
WHERE r >=1 AND r <= 3
ORDER BY rono DESC;
