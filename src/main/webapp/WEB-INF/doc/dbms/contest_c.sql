DROP TABLE qna CASCADE CONSTRAINTS;
DROP TABLE contest CASCADE CONSTRAINTS;
DROP TABLE job CASCADE CONSTRAINTS;
DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE admin CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 공모전 정보 게시판 */
/**********************************/
1) 테이블 생성
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

COMMENT ON TABLE contest is '공모전 정보 게시판';
COMMENT ON COLUMN contest.conNo is '공모전 번호';
COMMENT ON COLUMN contest.conTitle is '공모전 제목';
COMMENT ON COLUMN contest.conStart is '공모전 시작일';
COMMENT ON COLUMN contest.conEnd is '공모전 종료일';
COMMENT ON COLUMN contest.conRemain_e is '종료일 기준 날짜 계산';
COMMENT ON COLUMN contest.conRemain_s is '시작일 기준 날짜 계산';
COMMENT ON COLUMN contest.conCont is '공모전 내용';
COMMENT ON COLUMN contest.conUrl is '공모전 관련 URL';
COMMENT ON COLUMN contest.conYou is '공모전 관련영상(YouTube)';
COMMENT ON COLUMN contest.conFile1 is '공모전 배너 파일';
COMMENT ON COLUMN contest.conSize1 is '공모전 배너 사이즈';
COMMENT ON COLUMN contest.conThumb is '공모전 배너 썸네일';
COMMENT ON COLUMN contest.conFile2 is '공모전 내용 파일';
COMMENT ON COLUMN contest.conSize2 is '공모전 내용 파일 사이즈';
COMMENT ON COLUMN contest.conFstor2 is '공모전 내용 실제파일명';
COMMENT ON COLUMN contest.conFile3 is '공모전 양식 파일';
COMMENT ON COLUMN contest.conSize3 is '공모전 양식 파일 사이즈';
COMMENT ON COLUMN contest.conFstor3 is '공모전 양식 실제파일명';
COMMENT ON COLUMN contest.conGood is '공모전 추천수';
COMMENT ON COLUMN contest.conCnt is '공모전 조회수';
COMMENT ON COLUMN contest.cdate is '공모전 등록일';
COMMENT ON COLUMN contest.conWord is '공모전 검색어';
COMMENT ON COLUMN contest.adminno is '관리자번호';
COMMENT ON COLUMN contest.cateno is '카테고리번호';
COMMENT ON COLUMN contest.memberno is '회원번호';

2) 삽입
INSERT INTO contest(conNo, adminno, cateno, memberno, conHost, conTitle, conStart, conEnd, conRemain_s, conRemain_e, conCont,
                    conUrl, conYou, conFile1, conSize1, conThumb, conFile2, conSize2, conFstor2,
                    conFile3, conSize3, conFstor3, conGood, conCnt, cdate, conWord)
VALUES ((SELECT NVL(MAX(conNo), 0) + 1 as conNo FROM contest), 1, 30, 1, 'KH 소프트웨어', '공모전 제목', '2017-11-01', '2017-12-08', 
        0, 0, '공모전 정보 내용 01', 'www.naver.com', 'youtube', 'thumb.jpg', 0, 'thumb_m.jpg', 'file2.jpg', 0, 'file2_real.jpg',
        'file3.jpg', 0, 'file3_real.jpg', 0, 0, sysdate, '공모전 정보 검색어');
   
SELECT MAX(conNo)
FROM contest;

SELECT MIN(conNo)
FROM contest;

SELECT NVL(MAX(conNo), 0) as conNo
FROM contest;

SELECT NVL(MIN(conNo), 0) as conNo
FROM contest;
        
-- D-DAY 수정
UPDATE contest
SET conRemain_e = TO_DATE(conEnd, 'yyyy-mm-dd') - TO_DATE(TO_CHAR(sysdate, 'yyyy-mm-dd'));

-- 시작일이 현재일보다 뒤에있을 때 수정
UPDATE contest
SET conRemain_s = TO_DATE(conStart, 'yyyy-mm-dd') - TO_DATE(TO_CHAR(sysdate, 'yyyy-mm-dd'));
        
UPDATE contest
SET conRemain_s = conRemain_s;

UPDATE contest
SET conThumb=''
WHERE conNo = 1;
        
3) 전체 목록(댓글 구현)
SELECT conNo, adminno, cateno, memberno, conHost, conTitle, conStart, conEnd, conRemain_s, conRemain_e,
       conCont, conUrl, conYou, conFile1, conSize1, conThumb, conFile2, conSize2, conFstor2,
       conFile3, conSize3, conFstor3, conGood, conCnt, cdate, conWord
FROM contest
ORDER BY conNo DESC;

SELECT conYou
FROM contest
ORDER BY conNo DESC;

-- 조회수에 따른 목록
SELECT conNo, cateno, conHost, conTitle, conStart, conEnd, conCont, conRemain_s, conRemain_e,
           conUrl, conYou, conFile1, conSize1, conThumb, conFile2, conSize2, conFstor2, 
           conFile3, conSize3, conFstor3, conGood, conCnt, cdate, conWord
FROM contest
ORDER BY conCnt DESC

4) 전체 카운트
SELECT COUNT(*) as count
FROM contest;

-- D-DAY 기준 카운트
SELECT COUNT(*) as count
FROM contest
WHERE conRemain_s <= 0 AND conRemain_e >= 0;
WHERE conStart <= sysdate AND conEnd >= sysdate;

5) 조회
SELECT conNo, adminno, cateno, memberno, conHost, conTitle, conStart, conEnd, conRemain_s, conRemain_e, conCont,
       conUrl, conYou, conFile1, conSize1, conThumb, conFile2, conSize2, conFstor2, 
       conFile3, conSize3, conFstor3, conGood, conCnt, cdate, conWord
FROM contest
WHERE conNo = 1;

6) 수정
UPDATE contest
SET conTitle = '공모전 정보 제목 수정', conCont = '공모전 정보 내용 수정',
WHERE conNo = 13;

-- 썸네일 파일 수정
UPDATE contest
SET conFile1 = "", conSize1 = 123, conThumb = ""
WHERE conNo = 1;

-- 이미지 파일 수정
UPDATE contest
SET conFile2 = "", conSize2 = 123, conFstor2 = ""
WHERE conNo = 1;

-- 일반 파일 수정
UPDATE contest
SET conFile3 = "", conSize3 = 123, conFstor3 = ""
WHERE conNo = 1;

UPDATE contest
SET cateno = 30
WHERE cateno = 1;

7) 삭제
DELETE FROM contest
WHERE conNo = 1;

8) 검색
SELECT conNo, adminno, cateno, memberno, conTitle, conCont, conGood, conThumb, 
       conFile1, conFstor1, conSize1, conCnt, cdate, conWord 
FROM contest
WHERE cateno = 30 
ORDER BY conNo DESC;

9) 검색 및 전체 레코드 갯수
-- 검색하지 않는 경우 레코드 갯수
SELECT COUNT(*) as cnt
FROM contest
WHERE cateno = 1;

SELECT COUNT(*) as count
FROM contest
WHERE cateno=30;

-- '수정' 검색 레코드 갯수
SELECT COUNT(*) as cnt
FROM contest
WHERE cateno = 30 AND conWord LIKE '%검색%';

10) 페이징
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
            WHERE conWord LIKE '%검색%'
            ORDER BY conNo DESC
      )
)
WHERE r >= 1 AND r <= 3;
