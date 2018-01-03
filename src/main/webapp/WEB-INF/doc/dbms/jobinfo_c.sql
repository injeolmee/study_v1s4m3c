DROP TABLE qna CASCADE CONSTRAINTS;
DROP TABLE jobinfo CASCADE CONSTRAINTS;
DROP TABLE contest CASCADE CONSTRAINTS;
DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE admin CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 채용정보 */
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

COMMENT ON TABLE jobinfo is '채용정보';
COMMENT ON COLUMN jobinfo.jobNo is '채용번호';
COMMENT ON COLUMN jobinfo.comName is '회사명';
COMMENT ON COLUMN jobinfo.bossName is '대표자명';
COMMENT ON COLUMN jobinfo.labCnt is '근로자수';
COMMENT ON COLUMN jobinfo.jobStatus is '직무내용';
COMMENT ON COLUMN jobinfo.comAddr is '회사주소';
COMMENT ON COLUMN jobinfo.homepage is '홈페이지';
COMMENT ON COLUMN jobinfo.career is '경력';
COMMENT ON COLUMN jobinfo.edu is '학력';
COMMENT ON COLUMN jobinfo.jobLocal is '근무지역';
COMMENT ON COLUMN jobinfo.jcity is '근무지역 (시)';
COMMENT ON COLUMN jobinfo.jgu is '근무지역 (구, 동)';
COMMENT ON COLUMN jobinfo.jdong is '근무지역 (나머지 주소)';
COMMENT ON COLUMN jobinfo.jobPay is '임금';
COMMENT ON COLUMN jobinfo.payLow is '최저임금';
COMMENT ON COLUMN jobinfo.payHigh is '최고임금';
COMMENT ON COLUMN jobinfo.jobEmploy is '고용형태';
COMMENT ON COLUMN jobinfo.jobWork is '근무형태';
COMMENT ON COLUMN jobinfo.jobStart is '접수시작일';
COMMENT ON COLUMN jobinfo.jobEnd is '접수마감일';
COMMENT ON COLUMN jobinfo.jobRemain_s is '남은일수(시작일기준)';
COMMENT ON COLUMN jobinfo.jobRemain_e is '남은일수(종료일기준)';
COMMENT ON COLUMN jobinfo.jobCont is '추가사항(내용)';
COMMENT ON COLUMN jobinfo.jobFile1 is '썸네일명';
COMMENT ON COLUMN jobinfo.jobSize1 is '썸네일사이즈';
COMMENT ON COLUMN jobinfo.jobThumb is '썸네일실제파일명';
COMMENT ON COLUMN jobinfo.jobFile2 is '일반파일명';
COMMENT ON COLUMN jobinfo.jobSize2 is '일반파일사이즈';
COMMENT ON COLUMN jobinfo.jobFstor2 is '일반파일실제명';
COMMENT ON COLUMN jobinfo.jobCnt is '조회수';
COMMENT ON COLUMN jobinfo.jobWord is '검색키워드';
COMMENT ON COLUMN jobinfo.jdate is '등록일';
COMMENT ON COLUMN jobinfo.adminno is '관리자번호';
COMMENT ON COLUMN jobinfo.memberno is '회원번호';

2) 삽입
INSERT INTO jobinfo(jobNo, adminno, memberno, comName, bossName, labCnt, jobStatus, 
                        comAddr, homepage, career, edu, jobLocal, jcity, jgu, jdong, jobPay, payLow, payHigh, 
                        jobEmploy, jobWork, jobStart, jobEnd, jobRemain_s, jobRemain_e, jobCont, 
                        jobFile1, jobSize1, jobThumb, jobFile2, jobSize2, jobFstor2, jobCnt, jobWord, jdate)
VALUES ((SELECT NVL(MAX(jobNo), 0) + 1 as jobNo FROM jobinfo), 1, 1, 'KH 소프트웨어', '최권협', 100, '자바 개발', 
        '수원시 영통구 이의동 웰빙타운로70', 'www.naver.com', '경력1', '학력1', '서울 종로구 관철동 코아빌딩 1층', '서울', '종로구', '관철동 코아빌딩 1층', '관계없음', 1000000, 3000000, 
        '기간의 정함이 없는 근로계약', '신입', '2017-12-20', '2018-01-19', 0, 0, '추가사항 없음', '썸네일.jpg', 123, '썸네일_t.jpg', '일반파일.txt', 123, '실제파일명.txt', 0, '자바', sysdate);
         
3) 전체 목록(댓글 구현)
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


4) 전체 카운트
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

-- D-DAY 수정
UPDATE job
SET jobRemain_e = TO_DATE(jobEnd, 'yyyy-mm-dd') - TO_DATE(TO_CHAR(sysdate, 'yyyy-mm-dd'));

-- 시작일이 현재일보다 뒤에있을 때 수정
UPDATE job
SET jobRemain_s = TO_DATE(jobStart, 'yyyy-mm-dd') - TO_DATE(TO_CHAR(sysdate, 'yyyy-mm-dd'));
        
UPDATE job
SET conRemain_s = conRemain_s;

UPDATE job
SET jobThumb=''
WHERE jobNo = 1;

5) 조회
SELECT jobNo, adminno, cateno, memberno, jobHost, jobTitle, jobStart, jobEnd, jobRemain_s, jobRemain_e, jobCont,
       jobUrl, jobYou, jobFile1, jobSize1, jobThumb, jobFile2, jobSize2, jobFstor2,
       jobFile3, jobSize3, jobFstor3, jobCnt, jdate, jobWord
FROM job
WHERE jobNo = 1;

6) 수정
UPDATE job
SET jobTitle = '취업정보 제목 수정', jobCont = '취업정보 내용 수정',
    jobThumb = 'update_t.jpg', jobFile1 = 'update.jpg', jobSize1 = 150, jobWord = '수정'
WHERE jobNo = 1;

7) 삭제
DELETE FROM job
WHERE jobNo = 1;

8) 검색
SELECT jobNo, adminno, cateno, memberno, jobTitle, jobCont, jobGood, jobThumb, 
       jobFile1, jobFstor1, jobSize1, jobCnt, jdate, jobWord 
FROM job
WHERE cateno = 31 AND jobWord LIKE '%수정%'
ORDER BY jobNo DESC;

9) 검색 및 전체 레코드 갯수
-- 검색하지 않는 경우 레코드 갯수
SELECT COUNT(*) as cnt
FROM job
WHERE cateno = 31;

-- '수정' 검색 레코드 갯수
SELECT COUNT(*) as cnt
FROM job
WHERE cateno = 31 AND jobWord LIKE '%수정%';

10) 페이징
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
