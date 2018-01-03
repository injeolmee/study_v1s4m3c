
DROP TABLE NOTICE_REPLY CASCADE CONSTRAINTS;

SELECT * FROM NOTICE_REPLY;

/**********************************/
/* Table Name: 공지사항 댓글 */
/**********************************/
CREATE TABLE NOTICE_REPLY(
    NREPNO                          NUMBER(10)     PRIMARY KEY,
    NREPCONT                          VARCHAR2(1000)     NOT NULL,
    NREPNAME                          VARCHAR2(100)    NOT NULL,
    NREPDATE                          DATE     NOT NULL,
    MEMBERNO                          NUMBER(10)     NULL ,
    NOTICENO                          NUMBER(10)     NULL,
    FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
    FOREIGN KEY (NOTICENO) REFERENCES NOTICE (NOTICENO)
);

COMMENT ON TABLE NOTICE_REPLY is '공지사항 댓글';
COMMENT ON COLUMN NOTICE_REPLY.NREPNO is '공지댓글번호';
COMMENT ON COLUMN NOTICE_REPLY.NREPCONT is '공지댓글내용';
COMMENT ON COLUMN NOTICE_REPLY.NREPNAME is '공지댓글작성자';
COMMENT ON COLUMN NOTICE_REPLY.NREPDATE is '공지댓글등록일';
COMMENT ON COLUMN NOTICE_REPLY.MEMBERNO is '회원번호';
COMMENT ON COLUMN NOTICE_REPLY.NOTICENO is '공지사항번호';

insert into notice_reply(NREPNO, NREPCONT, NREPDATE, NREPNAME, memberno, NOTICENO) 
values((SELECT NVL(MAX(NREPNO), 0)+1 as NREPNO FROM notice_reply), '안녕하세요', sysdate, 'test111', 1, 5);

  select NREPNO, NREPCONT, NREPDATE, NREPNAME, memberno, NOTICENO, r
  from(
        select NREPNO, NREPCONT, NREPDATE, NREPNAME, memberno, NOTICENO, rownum as r
          from(
                select NREPNO, NREPCONT, NREPDATE, NREPNAME, memberno, NOTICENO
                from notice_reply
                where NOTICENO = 5
                order by NREPNO desc
              )
      )
      WHERE r >= 1 AND r <= 3
