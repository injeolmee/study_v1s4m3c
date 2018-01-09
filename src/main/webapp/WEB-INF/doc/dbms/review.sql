0) 테이블 삭제
DROP TABLE admin;
DROP TABLE category;
DROP TABLE room;
DROP TABLE review CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;


1) 테이블 생성
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


COMMENT ON TABLE review is '스터디룸 리뷰';
COMMENT ON COLUMN review.rvno is '스터디룸 리뷰 등록 번호';
COMMENT ON COLUMN review.rono is '스터디룸 등록 번호';
COMMENT ON COLUMN review.memberno is '회원번호';
COMMENT ON COLUMN review.rvdate is '리뷰 등록일';
COMMENT ON COLUMN review.rvgood is '리뷰 평점';
COMMENT ON COLUMN review.rvcont is '리뷰 내용';
COMMENT ON COLUMN review.rvfile1 is '리뷰 이미지';
COMMENT ON COLUMN review.rvsize1 is '이미지 사이즈';
COMMENT ON COLUMN review.rvthumb is '이미지 썸네일';
COMMENT ON COLUMN review.rvup is '리뷰 좋아요';
COMMENT ON COLUMN review.rvcnt is '리뷰 갯수';

select *
from review;


열 생성
ALTER TABLE review
set (rvup NUMBER(6)       DEFAULT 0          NOT NULL);


2) 삽입
INSERT INTO review(rvno, rono, memberno, rvmemname, rvdate, rvgood, rvcont, 
                              rvfile1, rvsize1, rvthumb, rvup, rvcnt)
VALUES((SELECT NVL(MAX(rono), 0) + 1 as rvno FROM review), 15, 1, '회원200', sysdate, 0, '내용', 
             '이미지', 0, '리뷰 썸네일', '좋아요', 0);
   

3) 목록
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

4) 수정
UPDATE review
SET rvgood=3, rvcont='ㅎㅎㅎ', rvthumb='tum.jpg', rvfile1='image.jpg', rvsize1=48281
 WHERE rvno = 24;


5) 삭제
DELETE FROM review;
WHERE rvno=1;


6) 페이징
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