DROP TABLE qna CASCADE CONSTRAINTS;
DROP TABLE jobinfo CASCADE CONSTRAINTS;
DROP TABLE contest CASCADE CONSTRAINTS;
DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE admin CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: ä������ */
/**********************************/
CREATE TABLE jobinfo(
    jobNo                             NUMBER(7)    NOT NULL    PRIMARY KEY,
    comName                           VARCHAR2(200)    NOT NULL,
    bossName                          VARCHAR2(200)    NOT NULL,
    labCnt                            NUMBER(7)    DEFAULT 0     NOT NULL,
    jobStatus                         VARCHAR2(200)    NOT NULL,
    comAddr                           VARCHAR2(1000)    NOT NULL,
    homepage                          VARCHAR2(200)    NULL ,
    career                            VARCHAR2(200)    NULL ,
    edu                               VARCHAR2(200)    NULL ,
    jobLocal                          VARCHAR2(200)    NOT NULL,
    jcity                             VARCHAR2(20)    NOT NULL,
    jgu                               VARCHAR2(20)    NOT NULL,
    jdong                             VARCHAR2(200)    NULL,
    jobPay                            VARCHAR2(20)   NOT NULL,
    payLow                            NUMBER(10)     NOT NULL,
    payHigh                           NUMBER(10)     NOT NULL,
    jobEmploy                         VARCHAR2(200)    NOT NULL,
    jobWork                           VARCHAR2(200)    NOT NULL,
    jobStart                          VARCHAR2(200)    NOT NULL,
    jobEnd                            VARCHAR2(200)    NOT NULL,
    jobRemain_s                       NUMBER(10)     DEFAULT 0     NULL ,
    jobRemain_e                       NUMBER(10)     DEFAULT 0     NULL ,
    jobCont                           VARCHAR2(2000)     NULL ,
    jobFile1                          VARCHAR2(50)     NULL ,
    jobSize1                          NUMBER(10)     DEFAULT 0     NULL ,
    jobThumb                          VARCHAR2(100)    NULL ,
    jobFile2                          VARCHAR2(50)     NULL ,
    jobSize2                          NUMBER(10)     DEFAULT 0     NULL ,
    jobFstor2                         VARCHAR2(100)    NULL ,
    jobCnt                            NUMBER(7)      DEFAULT 0      NOT NULL,
    jobWord                           VARCHAR2(100)    NULL ,
    jdate                             DATE     NOT NULL,
    adminno                           NUMBER(10)     NULL ,
    memberno                          NUMBER(10)     NULL ,
  FOREIGN KEY (adminno) REFERENCES admin (adminno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE jobinfo is 'ä������';
COMMENT ON COLUMN jobinfo.jobNo is 'ä���ȣ';
COMMENT ON COLUMN jobinfo.comName is 'ȸ���';
COMMENT ON COLUMN jobinfo.bossName is '��ǥ�ڸ�';
COMMENT ON COLUMN jobinfo.labCnt is '�ٷ��ڼ�';
COMMENT ON COLUMN jobinfo.jobStatus is '��������';
COMMENT ON COLUMN jobinfo.comAddr is 'ȸ���ּ�';
COMMENT ON COLUMN jobinfo.homepage is 'Ȩ������';
COMMENT ON COLUMN jobinfo.career is '���';
COMMENT ON COLUMN jobinfo.edu is '�з�';
COMMENT ON COLUMN jobinfo.jobLocal is '�ٹ�����';
COMMENT ON COLUMN jobinfo.jcity is '�ٹ����� (��)';
COMMENT ON COLUMN jobinfo.jgu is '�ٹ����� (��, ��)';
COMMENT ON COLUMN jobinfo.jdong is '�ٹ����� (������ �ּ�)';
COMMENT ON COLUMN jobinfo.jobPay is '�ӱ�';
COMMENT ON COLUMN jobinfo.payLow is '�����ӱ�';
COMMENT ON COLUMN jobinfo.payHigh is '�ְ��ӱ�';
COMMENT ON COLUMN jobinfo.jobEmploy is '�������';
COMMENT ON COLUMN jobinfo.jobWork is '�ٹ�����';
COMMENT ON COLUMN jobinfo.jobStart is '����������';
COMMENT ON COLUMN jobinfo.jobEnd is '����������';
COMMENT ON COLUMN jobinfo.jobRemain_s is '�����ϼ�(�����ϱ���)';
COMMENT ON COLUMN jobinfo.jobRemain_e is '�����ϼ�(�����ϱ���)';
COMMENT ON COLUMN jobinfo.jobCont is '�߰�����(����)';
COMMENT ON COLUMN jobinfo.jobFile1 is '����ϸ�';
COMMENT ON COLUMN jobinfo.jobSize1 is '����ϻ�����';
COMMENT ON COLUMN jobinfo.jobThumb is '����Ͻ������ϸ�';
COMMENT ON COLUMN jobinfo.jobFile2 is '�Ϲ����ϸ�';
COMMENT ON COLUMN jobinfo.jobSize2 is '�Ϲ����ϻ�����';
COMMENT ON COLUMN jobinfo.jobFstor2 is '�Ϲ����Ͻ�����';
COMMENT ON COLUMN jobinfo.jobCnt is '��ȸ��';
COMMENT ON COLUMN jobinfo.jobWord is '�˻�Ű����';
COMMENT ON COLUMN jobinfo.jdate is '�����';
COMMENT ON COLUMN jobinfo.adminno is '�����ڹ�ȣ';
COMMENT ON COLUMN jobinfo.memberno is 'ȸ����ȣ';

2) ����
INSERT INTO jobinfo(jobNo, adminno, memberno, comName, bossName, labCnt, jobStatus, 
                        comAddr, homepage, career, edu, jobLocal, jcity, jgu, jdong, jobPay, payLow, payHigh, 
                        jobEmploy, jobWork, jobStart, jobEnd, jobRemain_s, jobRemain_e, jobCont, 
                        jobFile1, jobSize1, jobThumb, jobFile2, jobSize2, jobFstor2, jobCnt, jobWord, jdate)
VALUES ((SELECT NVL(MAX(jobNo), 0) + 1 as jobNo FROM jobinfo), 1, 1, 'KH ����Ʈ����', '�ֱ���', 100, '�ڹ� ����', 
        '������ ���뱸 ���ǵ� ����Ÿ���70', 'www.naver.com', '���1', '�з�1', '���� ���α� ��ö�� �ھƺ��� 1��', '����', '���α�', '��ö�� �ھƺ��� 1��', '�������', 1000000, 3000000, 
        '�Ⱓ�� ������ ���� �ٷΰ��', '����', '2017-12-20', '2018-01-19', 0, 0, '�߰����� ����', '�����.jpg', 123, '�����_t.jpg', '�Ϲ�����.txt', 123, '�������ϸ�.txt', 0, '�ڹ�', sysdate);
         
3) ��ü ���(��� ����)
SELECT jobNo, adminno, memberno, comName, bossName, labCnt, jobStatus, 
       comAddr, homepage, career, edu, jobLocal, jcity, jgu, jdong, jobPay, payLow, payHigh, 
       jobEmploy, jobWork, jobStart, jobEnd, jobRemain_s, jobRemain_e, jobCont, 
       jobFile1, jobSize1, jobThumb, jobFile2, jobSize2, jobFstor2, jobCnt, jobWord, jdate
FROM jobinfo
ORDER BY jobNo DESC;

SELECT *
FROM jobinfo;


ALTER TABLE jobinfo
MODIFY (comAddr VARCHAR2(1000));


4) ��ü ī��Ʈ
SELECT COUNT(*) as count
FROM job;

SELECT MAX(jobNo)
FROM job;

SELECT MIN(jobNo)
FROM job;

SELECT NVL(MAX(jobNo), 0) as jobNo
FROM job;

SELECT NVL(MIN(jobNo), 0) as jobNo
FROM job;

-- D-DAY ����
UPDATE job
SET jobRemain_e = TO_DATE(jobEnd, 'yyyy-mm-dd') - TO_DATE(TO_CHAR(sysdate, 'yyyy-mm-dd'));

-- �������� �����Ϻ��� �ڿ����� �� ����
UPDATE job
SET jobRemain_s = TO_DATE(jobStart, 'yyyy-mm-dd') - TO_DATE(TO_CHAR(sysdate, 'yyyy-mm-dd'));
        
UPDATE job
SET conRemain_s = conRemain_s;

UPDATE job
SET jobThumb=''
WHERE jobNo = 1;

5) ��ȸ
SELECT jobNo, adminno, cateno, memberno, jobHost, jobTitle, jobStart, jobEnd, jobRemain_s, jobRemain_e, jobCont,
       jobUrl, jobYou, jobFile1, jobSize1, jobThumb, jobFile2, jobSize2, jobFstor2,
       jobFile3, jobSize3, jobFstor3, jobCnt, jdate, jobWord
FROM job
WHERE jobNo = 1;

6) ����
UPDATE job
SET jobTitle = '������� ���� ����', jobCont = '������� ���� ����',
    jobThumb = 'update_t.jpg', jobFile1 = 'update.jpg', jobSize1 = 150, jobWord = '����'
WHERE jobNo = 1;

7) ����
DELETE FROM job
WHERE jobNo = 1;

8) �˻�
SELECT jobNo, adminno, cateno, memberno, jobTitle, jobCont, jobGood, jobThumb, 
       jobFile1, jobFstor1, jobSize1, jobCnt, jdate, jobWord 
FROM job
WHERE cateno = 31 AND jobWord LIKE '%����%'
ORDER BY jobNo DESC;

9) �˻� �� ��ü ���ڵ� ����
-- �˻����� �ʴ� ��� ���ڵ� ����
SELECT COUNT(*) as cnt
FROM job
WHERE cateno = 31;

-- '����' �˻� ���ڵ� ����
SELECT COUNT(*) as cnt
FROM job
WHERE cateno = 31 AND jobWord LIKE '%����%';

10) ����¡
-- STEP 03.
SELECT jobNo, adminno, cateno, memberno, jobTitle, jobCont, jobGood, jobThumb, 
       jobFile1, jobFstor1, jobSize1, jobCnt, jdate, jobWord, r
FROM (
      SELECT jobNo, adminno, cateno, memberno, jobTitle, jobCont, jobGood, jobThumb, 
             jobFile1, jobFstor1, jobSize1, jobCnt, jdate, jobWord, rownum as r
      FROM (
            SELECT jobNo, adminno, cateno, memberno, jobTitle, jobCont, jobGood, jobThumb, 
                   jobFile1, jobFstor1, jobSize1, jobCnt, jdate, jobWord
            FROM job
            WHERE cateno = 31;
            ORDER BY jobNo DESC
      )
)
WHERE r >= 1 AND r <= 3;
