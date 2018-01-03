DROP TABLE freelike;

/**********************************/
/* Table Name: 자유게시판 좋아요 */
/**********************************/
CREATE TABLE FREELIKE(
    GOODNO                            NUMBER(10)     NOT NULL,
    GOODCHK                           NUMBER(10)     DEFAULT 0     NULL ,
    MEMBERNO                          NUMBER(10)     NULL ,
    freeno                                  NUMBER(7)    NULL 
);

COMMENT ON TABLE FREELIKE is '자유게시판 좋아요';
COMMENT ON COLUMN FREELIKE.GOODNO is '좋아요 번호';
COMMENT ON COLUMN FREELIKE.GOODCHK is '좋아요 체크';
COMMENT ON COLUMN FREELIKE.MEMBERNO is '회원 번호';
COMMENT ON COLUMN FREELIKE.freeno is '게시판 번호';

ALTER TABLE FREELIKE ADD CONSTRAINT IDX_FREELIKE_PK PRIMARY KEY (GOODNO);
ALTER TABLE FREELIKE ADD CONSTRAINT IDX_FREELIKE_FK0 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);
ALTER TABLE FREELIKE ADD CONSTRAINT IDX_FREELIKE_FK1 FOREIGN KEY (freeno) REFERENCES free (freeno);

1. 등록
INSERT INTO freelike(goodno, goodchk, freeno, memberno)
VALUES ((SELECT NVL(MAX(goodno), 0) + 1 as goodno FROM freelike), 0, 2, 1);

2. 전체 목록
SELECT goodno, goodchk, freeno, memberno
FROM freelike
ORDER BY goodno DESC;

 GOODNO GOODCHK FREENO MEMBERNO
 ------ ------- ------ --------
      1       0      2        1