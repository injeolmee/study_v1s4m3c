DROP TABLE freelike;

/**********************************/
/* Table Name: �����Խ��� ���ƿ� */
/**********************************/
CREATE TABLE FREELIKE(
    GOODNO                            NUMBER(10)     NOT NULL,
    GOODCHK                           NUMBER(10)     DEFAULT 0     NULL ,
    MEMBERNO                          NUMBER(10)     NULL ,
    freeno                                  NUMBER(7)    NULL 
);

COMMENT ON TABLE FREELIKE is '�����Խ��� ���ƿ�';
COMMENT ON COLUMN FREELIKE.GOODNO is '���ƿ� ��ȣ';
COMMENT ON COLUMN FREELIKE.GOODCHK is '���ƿ� üũ';
COMMENT ON COLUMN FREELIKE.MEMBERNO is 'ȸ�� ��ȣ';
COMMENT ON COLUMN FREELIKE.freeno is '�Խ��� ��ȣ';

ALTER TABLE FREELIKE ADD CONSTRAINT IDX_FREELIKE_PK PRIMARY KEY (GOODNO);
ALTER TABLE FREELIKE ADD CONSTRAINT IDX_FREELIKE_FK0 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);
ALTER TABLE FREELIKE ADD CONSTRAINT IDX_FREELIKE_FK1 FOREIGN KEY (freeno) REFERENCES free (freeno);

1. ���
INSERT INTO freelike(goodno, goodchk, freeno, memberno)
VALUES ((SELECT NVL(MAX(goodno), 0) + 1 as goodno FROM freelike), 0, 2, 1);

2. ��ü ���
SELECT goodno, goodchk, freeno, memberno
FROM freelike
ORDER BY goodno DESC;

 GOODNO GOODCHK FREENO MEMBERNO
 ------ ------- ------ --------
      1       0      2        1