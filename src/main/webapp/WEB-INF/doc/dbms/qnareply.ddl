DROP TABLE qnareply CASCADE CONSTRAINTS;
DROP TABLE qnaboard CASCADE CONSTRAINTS;
DROP TABLE jobinfo CASCADE CONSTRAINTS;
DROP TABLE conlike CASCADE CONSTRAINTS;
DROP TABLE contest CASCADE CONSTRAINTS;
DROP TABLE admin CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;

DROP TABLE qnaboard CASCADE CONSTRAINTS;
DROP TABLE contest CASCADE CONSTRAINTS;
DROP TABLE job CASCADE CONSTRAINTS;
DROP TABLE admin CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: QnA게시판 댓글 및 대댓글 */
/**********************************/
CREATE TABLE qnareply(
    qrno                              NUMBER(7)    NOT NULL    PRIMARY KEY,
    qrcont                            VARCHAR2(500)    NOT NULL,
    qrname                            VARCHAR2(100)    NOT NULL,
    qrdate                            DATE     NOT NULL,
    qnano                             NUMBER(7)    NULL ,
    adminno                           NUMBER(10)     NULL ,
    memberno                          NUMBER(10)     NULL ,
  FOREIGN KEY (qnano) REFERENCES qnaboard (qnano) ON DELETE CASCADE,
  FOREIGN KEY (adminno) REFERENCES admin (adminno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE qnareply is 'QnA 댓글';
COMMENT ON COLUMN qnareply.qrno is 'QnA 댓글 번호';
COMMENT ON COLUMN qnareply.qrcont is 'QnA 댓글 내용';
COMMENT ON COLUMN qnareply.qrname is 'QnA 댓글 작성자';
COMMENT ON COLUMN qnareply.qrdate is 'QnA 댓글 등록일';
COMMENT ON COLUMN qnareply.qnano is '게시판 번호';
COMMENT ON COLUMN qnareply.adminno is '관리자번호';
COMMENT ON COLUMN qnareply.memberno is '회원번호';


1. 등록
INSERT INTO qnareply(qrno, qrcont, qrname, qrdate, qnano, adminno, memberno)
VALUES ((SELECT NVL(MAX(qrno), 0) + 1 as qrno FROM qnareply), 'QnA 댓글 내용입니다.', '질문자1', sysdate,
        (SELECT NVL(MAX(qrgrpno), 0) + 1 as qrgrpno FROM qnareply), 0, 0, 1, 1, 1, 1);

  INSERT INTO qnareply(qrno, qrcont, qrname, qrdate, qnano, memberno)
 VALUES ((SELECT NVL(MAX(qrno), 0) + 1 as qrno FROM qnareply), 'ㅎㅎㅎ', '익먕', sysdate, 1, 2)
  
2. 조회
1) 전체 목록 조회
SELECT qrno, qrcont, qrname, qrdate, qnano, adminno, memberno
FROM qnareply;

2) 게시글로 조회
SELECT qrno, qrcont, qrname, qrdate, qnano, adminno, memberno
FROM qnareply
WHERE qnano = 1
ORDER BY qrno ASC;

3) 특정 댓글 조회
SELECT qrno, qrcont, qrname, qrdate, qnano, adminno, memberno
FROM qnareply
WHERE qrno = 1;

3. 수정
UPDATE qnareply
SET qrcont = 'QnA 댓글 내용 수정되네요', qrdate = sysdate
WHERE qrno = 1;

4. 삭제
DELETE FROM qnareply
WHERE qrno = 1;

5. COUNT 관련
1) 아이디 검사
SELECT COUNT(*) as cnt
FROM qnareply
WHERE memberno = 1;

2) 총 레코드 갯수
SELECT COUNT(qrno) as cnt
FROM qnareply
WHERE qnano = 1;

6. 페이징 + 답변에 따른 정렬 순서
SELECT qrno, qrcont, qrname, qrdate, qrgrpno, qrindent, qransnum, qnano, adminno, memberno, qrseqno, r
FROM (
      SELECT qrno, qrcont, qrname, qrdate, qrgrpno, qrindent, qransnum, qnano, adminno, memberno, qrseqno, rownum as r
      FROM (
            SELECT qrno, qrcont, qrname, qrdate, qrgrpno, qrindent, qransnum, qnano, adminno, memberno, qrseqno
            FROM qnareply
            WHERE qnano = 1
            ORDER BY qrno ASC, qrindent ASC, qransnum DESC
      )
)
WHERE r >= 1 AND r <= 10;

7. 새로운 답변의 최신 등록 위한 답변 미루기
UPDATE qnareply
SET qransnum = qransnum + 1
WHERE qnano = 1 AND qrgrpno = 1 AND qransnum > 1;


















