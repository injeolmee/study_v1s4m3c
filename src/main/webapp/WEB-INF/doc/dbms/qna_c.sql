DROP TABLE qnaboard CASCADE CONSTRAINTS;
DROP TABLE contest CASCADE CONSTRAINTS;
DROP TABLE job CASCADE CONSTRAINTS;
DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE admin CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: QnA게시판 */
/**********************************/
CREATE TABLE qnaboard(
    qnano                             NUMBER(7)    NOT NULL    PRIMARY KEY,
    qnatitle                          VARCHAR2(200)    NOT NULL,
    wname                             VARCHAR2(100)    NOT NULL,
    qnacont                           VARCHAR2(4000)     NOT NULL,
    qnafile1                          VARCHAR2(400)    NULL ,
    qnasize1                          NUMBER(10)     DEFAULT 0     NULL ,
    qnafstor1                         VARCHAR2(400)    NULL ,
    qnagrp                            VARCHAR2(100)    NOT NULL,
    qdate                             DATE     NOT NULL,
    qnapwd                            VARCHAR2(200)     NOT NULL,
    qnacnt                            NUMBER(7)      DEFAULT 0      NOT NULL,
    qnaword                           VARCHAR2(100)    NULL ,
    memberno                          NUMBER(10)     NULL ,
    adminno                           NUMBER(10)     NULL ,
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (adminno) REFERENCES admin (adminno)
);

COMMENT ON TABLE qnaboard is 'QnA게시판';
COMMENT ON COLUMN qnaboard.qnano is '게시판 번호';
COMMENT ON COLUMN qnaboard.qnatitle is '게시판 제목';
COMMENT ON COLUMN qnaboard.wname is '게시판 작성자';
COMMENT ON COLUMN qnaboard.qnacont is 'qna 내용';
COMMENT ON COLUMN qnaboard.qnafile1 is '첨부파일명';
COMMENT ON COLUMN qnaboard.qnasize1 is '첨부파일 사이즈';
COMMENT ON COLUMN qnaboard.qnafstor1 is '첨부파일 실제명';
COMMENT ON COLUMN qnaboard.qnagrp is '게시판 종류';
COMMENT ON COLUMN qnaboard.qdate is '게시판 등록일';
COMMENT ON COLUMN qnaboard.qnapwd is '게시판 비밀번호';
COMMENT ON COLUMN qnaboard.qnacnt is '게시판 조회수';
COMMENT ON COLUMN qnaboard.qnaword is '게시판 검색어';
COMMENT ON COLUMN qnaboard.memberno is '회원번호';
COMMENT ON COLUMN qnaboard.adminno is '관리자번호';

2) 삽입
INSERT INTO qnaboard(qnano, adminno, memberno, qnaTitle, wname, qnaCont, qnafile1, qnasize1, qnafstor1, qnagrp, 
                qnavisible, qnapwd, qnacnt, qnaword, qdate)
VALUES ((SELECT NVL(MAX(qnano), 0) + 1 as qnano FROM qnaboard), 1, 1, 'QnA 01', '작성자1', 'QnA 정보 내용 01', 
        'qna_img.jpg', 123, 'qna_img_t.jpg', '스터디룸', 'Y', '1234', 0, 'QnA 정보 검색어', sysdate);
         
3) 전체 목록(댓글 구현)
SELECT qnano, adminno, memberno, qnaTitle, wname, qnaCont, qnafile1, qnasize1, qnafstor1, qnagrp, 
       qnavisible, qnapwd, qnacnt, qnaword, qdate
FROM qnaboard
ORDER BY qnano DESC;

4) 전체 카운트
SELECT COUNT(*) as count
FROM qnaboard;

5) 조회
SELECT qnano, adminno, memberno, qnaTitle, wname, qnaCont, qnafile1, qnasize1, qnafstor1, qnagrp, 
       qnavisible, qnapwd, qnacnt, qnaword, qdate
FROM qna
WHERE qnaNo = 1;

6) 수정
UPDATE qna
SET qnaTitle = 'QnA 제목 수정', qnaCont = 'QnA 내용 수정', qnaWord = '수정'
WHERE qnaNo = 1;

UPDATE qnaboard
SET qnacont='주5일 근무제 도입 확대와 토요일시험에 대한 요구 증가(종교활동 등)등으로 우리공단에서는 수험자 및 시험장 임차기관을 대상으로 시행요일에 대한 고객선호도 조사를 지속적으로 실시하고 있으며, 조사결과와 사회적 요구, 고객의견 등을 종합적으로 고려하여, 국가기술자격검정 토요일 시범시행 제도를 도입, 시행하고 있습니다.'
WHERE qnano=1;

7) 삭제
DELETE FROM qna
WHERE qnaNo = 1;

8) 검색
SELECT qnaNo, adminno, cateno, memberno, qnaTitle, qnaCont, qnaCnt, qdate, qnaWord 
FROM qna
WHERE cateno = 32 AND qnaWord LIKE '%수정%'
ORDER BY qnaNo DESC;

9) 검색 및 전체 레코드 갯수
-- 검색하지 않는 경우 레코드 갯수
SELECT COUNT(*) as cnt
FROM qna
WHERE cateno = 32;

-- '수정' 검색 레코드 갯수
SELECT COUNT(*) as cnt
FROM qna
WHERE cateno = 32 AND qnaWord LIKE '%수정%';

10) 페이징
-- STEP 03.
SELECT qnaNo, adminno, cateno, memberno, qnaTitle, qnaCont, qnaCnt, qdate, qnaWord, r
FROM (
      SELECT qnaNo, adminno, cateno, memberno, qnaTitle, qnaCont, qnaCnt, qdate, qnaWord, rownum as r
      FROM (
            SELECT qnaNo, adminno, cateno, memberno, qnaTitle, qnaCont, qnaCnt, qdate, qnaWord
            FROM qna
            WHERE cateno = 32;
            ORDER BY qnaNo DESC
      )
)
WHERE r >= 1 AND r <= 3;
