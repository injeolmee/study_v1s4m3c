DROP TABLE qna CASCADE CONSTRAINTS;
DROP TABLE contest CASCADE CONSTRAINTS;
DROP TABLE job CASCADE CONSTRAINTS;
DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE admin CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: ������ ���� �Խ��� */
/**********************************/
1) ���̺� ����
CREATE TABLE contest (
    conNo                             NUMBER(7)                    NOT NULL    PRIMARY KEY,
    conHost                           VARCHAR2(200)                NOT NULL,
    conTitle                          VARCHAR2(200)                NOT NULL,
    conStart                          VARCHAR2(200)                NOT NULL,
    conEnd                            VARCHAR2(200)                NOT NULL,
    conRemain_s                       NUMBER(10)      DEFAULT 0    NULL,
    conRemain_e                       NUMBER(10)      DEFAULT 0    NULL,
    conCont                           VARCHAR2(4000)               NOT NULL,
    conUrl                            VARCHAR2(200)                NULL,
    conYou                            VARCHAR2(200)                NULL,
    conFile1                          VARCHAR2(50)                 NULL,
    conSize1                          NUMBER(10)                   NULL,
    conThumb                          VARCHAR2(100)                NULL,
    conFile2                          VARCHAR2(50)                 NULL,
    conSize2                          NUMBER(10)      DEFAULT 0    NULL,
    conFstor2                         VARCHAR2(100)                NULL,
    conFile3                          VARCHAR2(50)                 NULL,
    conSize3                          NUMBER(10)      DEFAULT 0    NULL,
    conFstor3                         VARCHAR2(100)                NULL,
    conGood                           NUMBER(7)       DEFAULT 0    NOT NULL,
    conCnt                            NUMBER(7)       DEFAULT 0    NOT NULL,
    cdate                             DATE                         NOT NULL,
    conWord                           VARCHAR2(100)                    NULL,
    adminno                           NUMBER(10)                       NULL,
    cateno                            NUMBER(10)                       NULL,
    memberno                          NUMBER(10)                       NULL,
    FOREIGN KEY (adminno) REFERENCES admin (adminno),
    FOREIGN KEY (cateno) REFERENCES category (cateno),
    FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE contest is '������ ���� �Խ���';
COMMENT ON COLUMN contest.conNo is '������ ��ȣ';
COMMENT ON COLUMN contest.conTitle is '������ ����';
COMMENT ON COLUMN contest.conStart is '������ ������';
COMMENT ON COLUMN contest.conEnd is '������ ������';
COMMENT ON COLUMN contest.conRemain_e is '������ ���� ��¥ ���';
COMMENT ON COLUMN contest.conRemain_s is '������ ���� ��¥ ���';
COMMENT ON COLUMN contest.conCont is '������ ����';
COMMENT ON COLUMN contest.conUrl is '������ ���� URL';
COMMENT ON COLUMN contest.conYou is '������ ���ÿ���(YouTube)';
COMMENT ON COLUMN contest.conFile1 is '������ ��� ����';
COMMENT ON COLUMN contest.conSize1 is '������ ��� ������';
COMMENT ON COLUMN contest.conThumb is '������ ��� �����';
COMMENT ON COLUMN contest.conFile2 is '������ ���� ����';
COMMENT ON COLUMN contest.conSize2 is '������ ���� ���� ������';
COMMENT ON COLUMN contest.conFstor2 is '������ ���� �������ϸ�';
COMMENT ON COLUMN contest.conFile3 is '������ ��� ����';
COMMENT ON COLUMN contest.conSize3 is '������ ��� ���� ������';
COMMENT ON COLUMN contest.conFstor3 is '������ ��� �������ϸ�';
COMMENT ON COLUMN contest.conGood is '������ ��õ��';
COMMENT ON COLUMN contest.conCnt is '������ ��ȸ��';
COMMENT ON COLUMN contest.cdate is '������ �����';
COMMENT ON COLUMN contest.conWord is '������ �˻���';
COMMENT ON COLUMN contest.adminno is '�����ڹ�ȣ';
COMMENT ON COLUMN contest.cateno is 'ī�װ���ȣ';
COMMENT ON COLUMN contest.memberno is 'ȸ����ȣ';

2) ����
INSERT INTO contest(conNo, adminno, cateno, memberno, conHost, conTitle, conStart, conEnd, conRemain_s, conRemain_e, conCont,
                    conUrl, conYou, conFile1, conSize1, conThumb, conFile2, conSize2, conFstor2,
                    conFile3, conSize3, conFstor3, conGood, conCnt, cdate, conWord)
VALUES ((SELECT NVL(MAX(conNo), 0) + 1 as conNo FROM contest), 1, 30, 1, 'KH ����Ʈ����', '������ ����', '2017-11-01', '2017-12-08', 
        0, 0, '������ ���� ���� 01', 'www.naver.com', 'youtube', 'thumb.jpg', 0, 'thumb_m.jpg', 'file2.jpg', 0, 'file2_real.jpg',
        'file3.jpg', 0, 'file3_real.jpg', 0, 0, sysdate, '������ ���� �˻���');
   
SELECT MAX(conNo)
FROM contest;

SELECT MIN(conNo)
FROM contest;

SELECT NVL(MAX(conNo), 0) as conNo
FROM contest;

SELECT NVL(MIN(conNo), 0) as conNo
FROM contest;
        
-- D-DAY ����
UPDATE contest
SET conRemain_e = TO_DATE(conEnd, 'yyyy-mm-dd') - TO_DATE(TO_CHAR(sysdate, 'yyyy-mm-dd'));

-- �������� �����Ϻ��� �ڿ����� �� ����
UPDATE contest
SET conRemain_s = TO_DATE(conStart, 'yyyy-mm-dd') - TO_DATE(TO_CHAR(sysdate, 'yyyy-mm-dd'));
        
UPDATE contest
SET conRemain_s = conRemain_s;

UPDATE contest
SET conThumb=''
WHERE conNo = 1;
        
3) ��ü ���(��� ����)
SELECT conNo, adminno, cateno, memberno, conHost, conTitle, conStart, conEnd, conRemain_s, conRemain_e,
       conCont, conUrl, conYou, conFile1, conSize1, conThumb, conFile2, conSize2, conFstor2,
       conFile3, conSize3, conFstor3, conGood, conCnt, cdate, conWord
FROM contest
ORDER BY conNo DESC;

SELECT conYou
FROM contest
ORDER BY conNo DESC;

-- ��ȸ���� ���� ���
SELECT conNo, cateno, conHost, conTitle, conStart, conEnd, conCont, conRemain_s, conRemain_e,
           conUrl, conYou, conFile1, conSize1, conThumb, conFile2, conSize2, conFstor2, 
           conFile3, conSize3, conFstor3, conGood, conCnt, cdate, conWord
FROM contest
ORDER BY conCnt DESC

4) ��ü ī��Ʈ
SELECT COUNT(*) as count
FROM contest;

-- D-DAY ���� ī��Ʈ
SELECT COUNT(*) as count
FROM contest
WHERE conRemain_s <= 0 AND conRemain_e >= 0;
WHERE conStart <= sysdate AND conEnd >= sysdate;

5) ��ȸ
SELECT conNo, adminno, cateno, memberno, conHost, conTitle, conStart, conEnd, conRemain_s, conRemain_e, conCont,
       conUrl, conYou, conFile1, conSize1, conThumb, conFile2, conSize2, conFstor2, 
       conFile3, conSize3, conFstor3, conGood, conCnt, cdate, conWord
FROM contest
WHERE conNo = 1;

6) ����
UPDATE contest
SET conTitle = '������ ���� ���� ����', conCont = '������ ���� ���� ����',
WHERE conNo = 13;

-- ����� ���� ����
UPDATE contest
SET conFile1 = "", conSize1 = 123, conThumb = ""
WHERE conNo = 1;

-- �̹��� ���� ����
UPDATE contest
SET conFile2 = "", conSize2 = 123, conFstor2 = ""
WHERE conNo = 1;

-- �Ϲ� ���� ����
UPDATE contest
SET conFile3 = "", conSize3 = 123, conFstor3 = ""
WHERE conNo = 1;

UPDATE contest
SET cateno = 30
WHERE cateno = 1;

7) ����
DELETE FROM contest
WHERE conNo = 1;

8) �˻�
SELECT conNo, adminno, cateno, memberno, conTitle, conCont, conGood, conThumb, 
       conFile1, conFstor1, conSize1, conCnt, cdate, conWord 
FROM contest
WHERE cateno = 30 
ORDER BY conNo DESC;

9) �˻� �� ��ü ���ڵ� ����
-- �˻����� �ʴ� ��� ���ڵ� ����
SELECT COUNT(*) as cnt
FROM contest
WHERE cateno = 1;

SELECT COUNT(*) as count
FROM contest
WHERE cateno=30;

-- '����' �˻� ���ڵ� ����
SELECT COUNT(*) as cnt
FROM contest
WHERE cateno = 30 AND conWord LIKE '%�˻�%';

10) ����¡
-- STEP 03.
SELECT conNo, adminno, cateno, memberno, conHost, conTitle, conStart, conEnd, conRemain_s, conRemain_e, conCont,
       conUrl, conYou, conFile1, conSize1, conThumb, conFile2, conSize2, conFstor2, 
       conFile3, conSize3, conFstor3, conGood, conCnt, cdate, conWord, r
FROM (
      SELECT conNo, adminno, cateno, memberno, conHost, conTitle, conStart, conEnd, conRemain_s, conRemain_e, conCont,
			       conUrl, conYou, conFile1, conSize1, conThumb, conFile2, conSize2, conFstor2, 
			       conFile3, conSize3, conFstor3, conGood, conCnt, cdate, conWord, rownum as r
      FROM (
            SELECT conNo, adminno, cateno, memberno, conHost, conTitle, conStart, conEnd, conRemain_s, conRemain_e, conCont,
						       conUrl, conYou, conFile1, conSize1, conThumb, conFile2, conSize2, conFstor2, 
						       conFile3, conSize3, conFstor3, conGood, conCnt, cdate, conWord
            FROM contest
            WHERE conWord LIKE '%�˻�%'
            ORDER BY conNo DESC
      )
)
WHERE r >= 1 AND r <= 3;
