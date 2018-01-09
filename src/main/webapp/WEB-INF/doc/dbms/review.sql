0) ���̺� ����
DROP TABLE admin;
DROP TABLE category;
DROP TABLE room;
DROP TABLE review CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;


1) ���̺� ����
CREATE TABLE review(
    rvno                              NUMBER(10)     NOT NULL    PRIMARY KEY,
    rono                              NUMBER(10)     NOT NULL,
    memberno                          NUMBER(10)     NULL ,
    rvmemname                   VARCHAR2(40)   NULL,
    rvdate                            DATE     NOT NULL,
    rvgood                            NUMBER(10)   DEFAULT 0   NOT NULL ,
    rvcont                            VARCHAR2(1000)     NOT NULL,
    rvfile1                           VARCHAR2(800)    NULL ,
    rvsize1                           NUMBER(10)    NULL ,
    rvthumb                           VARCHAR2(800)    NULL ,
    rvup                              NUMBER(6)     DEFAULT 0     NOT NULL,
    rvcnt                             NUMBER(6)       DEFAULT 0          NOT NULL,
  FOREIGN KEY (rono) REFERENCES room (rono),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);


COMMENT ON TABLE review is '���͵�� ����';
COMMENT ON COLUMN review.rvno is '���͵�� ���� ��� ��ȣ';
COMMENT ON COLUMN review.rono is '���͵�� ��� ��ȣ';
COMMENT ON COLUMN review.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN review.rvdate is '���� �����';
COMMENT ON COLUMN review.rvgood is '���� ����';
COMMENT ON COLUMN review.rvcont is '���� ����';
COMMENT ON COLUMN review.rvfile1 is '���� �̹���';
COMMENT ON COLUMN review.rvsize1 is '�̹��� ������';
COMMENT ON COLUMN review.rvthumb is '�̹��� �����';
COMMENT ON COLUMN review.rvup is '���� ���ƿ�';
COMMENT ON COLUMN review.rvcnt is '���� ����';

select *
from review;


�� ����
ALTER TABLE review
set (rvup NUMBER(6)       DEFAULT 0          NOT NULL);


2) ����
INSERT INTO review(rvno, rono, memberno, rvmemname, rvdate, rvgood, rvcont, 
                              rvfile1, rvsize1, rvthumb, rvup, rvcnt)
VALUES((SELECT NVL(MAX(rono), 0) + 1 as rvno FROM review), 15, 1, 'ȸ��200', sysdate, 0, '����', 
             '�̹���', 0, '���� �����', '���ƿ�', 0);
   

3) ���
SELECT rvno, memberno, rvdate, rvgood, rvcont, rvfile1, rvsize1, rvthumb, rvup
FROM review
WHERE rono = 3;
ORDER BY rvno DESC;

SELECT *
FROM review;

SELECT COUNT(*) as rvcnt
FROM review
WHERE rono = 3;

UPDATE review
SET rvup = rvup - 1
WHERE rvno = 1;

4) ����
UPDATE review
SET rvgood=3, rvcont='������', rvthumb='tum.jpg', rvfile1='image.jpg', rvsize1=48281
 WHERE rvno = 24;


5) ����
DELETE FROM review;
WHERE rvno=1;


6) ����¡
SELECT rvno, memberno, rvdate, rvgood, rvcont, rvfile1, rvsize1, rvthumb, rvup, rvdown, r
FROM(
         SELECT rvno, memberno, rvdate, rvgood, rvcont, rvfile1, rvsize1, rvthumb, rvup, rvdown, rownum as r
         FROM(
                  SELECT rvno, memberno, rvdate, rvgood, rvcont, rvfile1, rvsize1, rvthumb, rvup, rvdown
                  FROM review
                  ORDER BY rvno DESC
         )
)
WHERE r >=1 AND r <= 3;