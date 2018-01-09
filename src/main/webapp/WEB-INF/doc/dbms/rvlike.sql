DROP TABLE rvlike;

/**********************************/
/* Table Name: ���ƿ� */
/**********************************/
1) ���̺� ����
CREATE TABLE rvlike(
    rvlikeno                              NUMBER(10)               NOT NULL    PRIMARY KEY,
    rvlikechk                             NUMBER(10)     DEFAULT 0     NULL ,
    rvno                               NUMBER(7)                    NULL ,
    memberno                            NUMBER(10)                   NULL ,
	  FOREIGN KEY (rvno) REFERENCES review (rvno),
	  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE rvlike is '���ƿ�';
COMMENT ON COLUMN rvlike.rvlikeno is '���ƿ� ��ȣ';
COMMENT ON COLUMN rvlike.rvlikechk is '���ƿ� üũ';
COMMENT ON COLUMN rvlike.rvno is '������ ��ȣ';
COMMENT ON COLUMN rvlike.memberno is 'ȸ����ȣ';

2) ����
INSERT INTO rvlike(rvlikeno, rvlikechk, rvno, memberno)
VALUES ((SELECT NVL(MAX(rvlikeno), 0) + 1 as rvlikeno FROM rvlike), 0, 1, 2);

3) ��ü ���
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

