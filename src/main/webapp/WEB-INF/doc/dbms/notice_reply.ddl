
DROP TABLE NOTICE_REPLY CASCADE CONSTRAINTS;

SELECT * FROM NOTICE_REPLY;

/**********************************/
/* Table Name: �������� ��� */
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

COMMENT ON TABLE NOTICE_REPLY is '�������� ���';
COMMENT ON COLUMN NOTICE_REPLY.NREPNO is '������۹�ȣ';
COMMENT ON COLUMN NOTICE_REPLY.NREPCONT is '������۳���';
COMMENT ON COLUMN NOTICE_REPLY.NREPNAME is '��������ۼ���';
COMMENT ON COLUMN NOTICE_REPLY.NREPDATE is '������۵����';
COMMENT ON COLUMN NOTICE_REPLY.MEMBERNO is 'ȸ����ȣ';
COMMENT ON COLUMN NOTICE_REPLY.NOTICENO is '�������׹�ȣ';

insert into notice_reply(NREPNO, NREPCONT, NREPDATE, NREPNAME, memberno, NOTICENO) 
values((SELECT NVL(MAX(NREPNO), 0)+1 as NREPNO FROM notice_reply), '�ȳ��ϼ���', sysdate, 'test111', 1, 5);

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
