DROP TABLE rvlike;

/**********************************/
/* Table Name: 좋아요 */
/**********************************/
1) 테이블 생성
CREATE TABLE rvlike(
    rvlikeno                              NUMBER(10)               NOT NULL    PRIMARY KEY,
    rvlikechk                             NUMBER(10)     DEFAULT 0     NULL ,
    rvno                               NUMBER(7)                    NULL ,
    memberno                            NUMBER(10)                   NULL ,
	  FOREIGN KEY (rvno) REFERENCES review (rvno),
	  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE rvlike is '좋아요';
COMMENT ON COLUMN rvlike.rvlikeno is '좋아요 번호';
COMMENT ON COLUMN rvlike.rvlikechk is '좋아요 체크';
COMMENT ON COLUMN rvlike.rvno is '공모전 번호';
COMMENT ON COLUMN rvlike.memberno is '회원번호';

2) 삽입
INSERT INTO rvlike(rvlikeno, rvlikechk, rvno, memberno)
VALUES ((SELECT NVL(MAX(rvlikeno), 0) + 1 as rvlikeno FROM rvlike), 0, 1, 2);

3) 전체 목록
SELECT rvlikeno, rvlikechk, rvno, memberno
FROM rvlike
ORDER BY rvlikeno DESC;

SELECT rvlikechk
FROM rvlike
WHERE rvno = #{rvno} AND memberno = #{memberno}

UPDATE rvlike
SET rvno=5
WHERE rvlikeno =7;

delete from rvlike;
where rvlikeno=1;

SELECT rvlikechk
FROM rvlike
WHERE rvno = 1 AND memberno = 2;

