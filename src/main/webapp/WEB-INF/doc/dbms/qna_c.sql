DROP TABLE qnaboard CASCADE CONSTRAINTS;
DROP TABLE contest CASCADE CONSTRAINTS;
DROP TABLE job CASCADE CONSTRAINTS;
DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE admin CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: QnA�Խ��� */
/**********************************/
CREATE TABLE qnaboard(
    qnano                             NUMBER(7)    NOT NULL    PRIMARY KEY,
    qnatitle                          VARCHAR2(200)    NOT NULL,
    wname                             VARCHAR2(100)    NOT NULL,
    qnacont                           VARCHAR2(4000)     NOT NULL,
    qnafile1                          VARCHAR2(400)    NULL ,
    qnasize1                          NUMBER(10)     DEFAULT 0     NULL ,
    qnafstor1                         VARCHAR2(400)    NULL ,
    qnagrp                            VARCHAR2(100)    NOT NULL,
    qdate                             DATE     NOT NULL,
    qnapwd                            VARCHAR2(200)     NOT NULL,
    qnacnt                            NUMBER(7)      DEFAULT 0      NOT NULL,
    qnaword                           VARCHAR2(100)    NULL ,
    memberno                          NUMBER(10)     NULL ,
    adminno                           NUMBER(10)     NULL ,
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (adminno) REFERENCES admin (adminno)
);

COMMENT ON TABLE qnaboard is 'QnA�Խ���';
COMMENT ON COLUMN qnaboard.qnano is '�Խ��� ��ȣ';
COMMENT ON COLUMN qnaboard.qnatitle is '�Խ��� ����';
COMMENT ON COLUMN qnaboard.wname is '�Խ��� �ۼ���';
COMMENT ON COLUMN qnaboard.qnacont is 'qna ����';
COMMENT ON COLUMN qnaboard.qnafile1 is '÷�����ϸ�';
COMMENT ON COLUMN qnaboard.qnasize1 is '÷������ ������';
COMMENT ON COLUMN qnaboard.qnafstor1 is '÷������ ������';
COMMENT ON COLUMN qnaboard.qnagrp is '�Խ��� ����';
COMMENT ON COLUMN qnaboard.qdate is '�Խ��� �����';
COMMENT ON COLUMN qnaboard.qnapwd is '�Խ��� ��й�ȣ';
COMMENT ON COLUMN qnaboard.qnacnt is '�Խ��� ��ȸ��';
COMMENT ON COLUMN qnaboard.qnaword is '�Խ��� �˻���';
COMMENT ON COLUMN qnaboard.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN qnaboard.adminno is '�����ڹ�ȣ';

2) ����
INSERT INTO qnaboard(qnano, adminno, memberno, qnaTitle, wname, qnaCont, qnafile1, qnasize1, qnafstor1, qnagrp, 
                qnavisible, qnapwd, qnacnt, qnaword, qdate)
VALUES ((SELECT NVL(MAX(qnano), 0) + 1 as qnano FROM qnaboard), 1, 1, 'QnA 01', '�ۼ���1', 'QnA ���� ���� 01', 
        'qna_img.jpg', 123, 'qna_img_t.jpg', '���͵��', 'Y', '1234', 0, 'QnA ���� �˻���', sysdate);
         
3) ��ü ���(��� ����)
SELECT qnano, adminno, memberno, qnaTitle, wname, qnaCont, qnafile1, qnasize1, qnafstor1, qnagrp, 
       qnavisible, qnapwd, qnacnt, qnaword, qdate
FROM qnaboard
ORDER BY qnano DESC;

4) ��ü ī��Ʈ
SELECT COUNT(*) as count
FROM qnaboard;

5) ��ȸ
SELECT qnano, adminno, memberno, qnaTitle, wname, qnaCont, qnafile1, qnasize1, qnafstor1, qnagrp, 
       qnavisible, qnapwd, qnacnt, qnaword, qdate
FROM qna
WHERE qnaNo = 1;

6) ����
UPDATE qna
SET qnaTitle = 'QnA ���� ����', qnaCont = 'QnA ���� ����', qnaWord = '����'
WHERE qnaNo = 1;

UPDATE qnaboard
SET qnacont='��5�� �ٹ��� ���� Ȯ��� ����Ͻ��迡 ���� �䱸 ����(����Ȱ�� ��)������ �츮���ܿ����� ������ �� ������ ��������� ������� ������Ͽ� ���� ����ȣ�� ���縦 ���������� �ǽ��ϰ� ������, �������� ��ȸ�� �䱸, ���ǰ� ���� ���������� ����Ͽ�, ��������ڰݰ��� ����� �ù����� ������ ����, �����ϰ� �ֽ��ϴ�.'
WHERE qnano=1;

7) ����
DELETE FROM qna
WHERE qnaNo = 1;

8) �˻�
SELECT qnaNo, adminno, cateno, memberno, qnaTitle, qnaCont, qnaCnt, qdate, qnaWord 
FROM qna
WHERE cateno = 32 AND qnaWord LIKE '%����%'
ORDER BY qnaNo DESC;

9) �˻� �� ��ü ���ڵ� ����
-- �˻����� �ʴ� ��� ���ڵ� ����
SELECT COUNT(*) as cnt
FROM qna
WHERE cateno = 32;

-- '����' �˻� ���ڵ� ����
SELECT COUNT(*) as cnt
FROM qna
WHERE cateno = 32 AND qnaWord LIKE '%����%';

10) ����¡
-- STEP 03.
SELECT qnaNo, adminno, cateno, memberno, qnaTitle, qnaCont, qnaCnt, qdate, qnaWord, r
FROM (
      SELECT qnaNo, adminno, cateno, memberno, qnaTitle, qnaCont, qnaCnt, qdate, qnaWord, rownum as r
      FROM (
            SELECT qnaNo, adminno, cateno, memberno, qnaTitle, qnaCont, qnaCnt, qdate, qnaWord
            FROM qna
            WHERE cateno = 32;
            ORDER BY qnaNo DESC
      )
)
WHERE r >= 1 AND r <= 3;
