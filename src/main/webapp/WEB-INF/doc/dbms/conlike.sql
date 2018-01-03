DROP TABLE conlike;

/**********************************/
/* Table Name: ���ƿ� */
/**********************************/
1) ���̺� ����
CREATE TABLE conlike(
    goodNo                              NUMBER(10)               NOT NULL    PRIMARY KEY,
    goodChk                             NUMBER(10)     DEFAULT 0     NULL ,
    conNo                               NUMBER(7)                    NULL ,
    memberno                            NUMBER(10)                   NULL ,
	  FOREIGN KEY (conNo) REFERENCES contest (conNo),
	  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE conlike is '���ƿ�';
COMMENT ON COLUMN conlike.goodNo is '���ƿ� ��ȣ';
COMMENT ON COLUMN conlike.goodChk is '���ƿ� üũ';
COMMENT ON COLUMN conlike.conNo is '������ ��ȣ';
COMMENT ON COLUMN conlike.memberno is 'ȸ����ȣ';

2) ����
INSERT INTO conlike(goodNo, goodChk, conNo, memberno)
VALUES ((SELECT NVL(MAX(goodNo), 0) + 1 as goodNo FROM conlike), 0, 1, 1);

3) ��ü ���
SELECT goodNo, goodChk, conNo, memberno
FROM conlike
ORDER BY goodNo DESC;