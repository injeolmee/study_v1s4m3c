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
/* Table Name: QnA�Խ��� ��� �� ���� */
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

COMMENT ON TABLE qnareply is 'QnA ���';
COMMENT ON COLUMN qnareply.qrno is 'QnA ��� ��ȣ';
COMMENT ON COLUMN qnareply.qrcont is 'QnA ��� ����';
COMMENT ON COLUMN qnareply.qrname is 'QnA ��� �ۼ���';
COMMENT ON COLUMN qnareply.qrdate is 'QnA ��� �����';
COMMENT ON COLUMN qnareply.qnano is '�Խ��� ��ȣ';
COMMENT ON COLUMN qnareply.adminno is '�����ڹ�ȣ';
COMMENT ON COLUMN qnareply.memberno is 'ȸ����ȣ';


1. ���
INSERT INTO qnareply(qrno, qrcont, qrname, qrdate, qnano, adminno, memberno)
VALUES ((SELECT NVL(MAX(qrno), 0) + 1 as qrno FROM qnareply), 'QnA ��� �����Դϴ�.', '������1', sysdate,
        (SELECT NVL(MAX(qrgrpno), 0) + 1 as qrgrpno FROM qnareply), 0, 0, 1, 1, 1, 1);

  INSERT INTO qnareply(qrno, qrcont, qrname, qrdate, qnano, memberno)
 VALUES ((SELECT NVL(MAX(qrno), 0) + 1 as qrno FROM qnareply), '������', '�͸�', sysdate, 1, 2)
  
2. ��ȸ
1) ��ü ��� ��ȸ
SELECT qrno, qrcont, qrname, qrdate, qnano, adminno, memberno
FROM qnareply;

2) �Խñ۷� ��ȸ
SELECT qrno, qrcont, qrname, qrdate, qnano, adminno, memberno
FROM qnareply
WHERE qnano = 1
ORDER BY qrno ASC;

3) Ư�� ��� ��ȸ
SELECT qrno, qrcont, qrname, qrdate, qnano, adminno, memberno
FROM qnareply
WHERE qrno = 1;

3. ����
UPDATE qnareply
SET qrcont = 'QnA ��� ���� �����ǳ׿�', qrdate = sysdate
WHERE qrno = 1;

4. ����
DELETE FROM qnareply
WHERE qrno = 1;

5. COUNT ����
1) ���̵� �˻�
SELECT COUNT(*) as cnt
FROM qnareply
WHERE memberno = 1;

2) �� ���ڵ� ����
SELECT COUNT(qrno) as cnt
FROM qnareply
WHERE qnano = 1;

6. ����¡ + �亯�� ���� ���� ����
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

7. ���ο� �亯�� �ֽ� ��� ���� �亯 �̷��
UPDATE qnareply
SET qransnum = qransnum + 1
WHERE qnano = 1 AND qrgrpno = 1 AND qransnum > 1;


















