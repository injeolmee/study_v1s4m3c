DROP TABLE conlike;

/**********************************/
/* Table Name: 좋아요 */
/**********************************/
1) 테이블 생성
CREATE TABLE conlike(
    goodNo                              NUMBER(10)               NOT NULL    PRIMARY KEY,
    goodChk                             NUMBER(10)     DEFAULT 0     NULL ,
    conNo                               NUMBER(7)                    NULL ,
    memberno                            NUMBER(10)                   NULL ,
	  FOREIGN KEY (conNo) REFERENCES contest (conNo),
	  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE conlike is '좋아요';
COMMENT ON COLUMN conlike.goodNo is '좋아요 번호';
COMMENT ON COLUMN conlike.goodChk is '좋아요 체크';
COMMENT ON COLUMN conlike.conNo is '공모전 번호';
COMMENT ON COLUMN conlike.memberno is '회원번호';

2) 삽입
INSERT INTO conlike(goodNo, goodChk, conNo, memberno)
VALUES ((SELECT NVL(MAX(goodNo), 0) + 1 as goodNo FROM conlike), 0, 1, 1);

3) 전체 목록
SELECT goodNo, goodChk, conNo, memberno
FROM conlike
ORDER BY goodNo DESC;