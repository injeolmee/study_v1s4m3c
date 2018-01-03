DROP TABLE admin CASCADE CONSTRAINTS;
select * from admin;
delete from admin;

/**********************************/
/* Table Name: 관리자 */
/**********************************/
CREATE TABLE admin(
    adminno                           NUMBER(10)     PRIMARY KEY,
    admid                             VARCHAR2(50)    NOT NULL,
    admemail                          VARCHAR2(100)    NOT NULL,
    admpasswd                         VARCHAR2(50)     NOT NULL,
    admname                           VARCHAR2(20)     NOT NULL,
    admconfirm                        VARCHAR2(5)    DEFAULT 'N'     NOT NULL,
    admauth                           VARCHAR2(5)    DEFAULT 'N'     NOT NULL,
    admdate                           DATE     NOT NULL
);

COMMENT ON TABLE admin is '관리자';
COMMENT ON COLUMN admin.adminno is '관리자번호';
COMMENT ON COLUMN admin.admid is '관리자아이디';
COMMENT ON COLUMN admin.admemail is '관리자이메일';
COMMENT ON COLUMN admin.admpasswd is '관리자비밀번호';
COMMENT ON COLUMN admin.admname is '관리자이름';
COMMENT ON COLUMN admin.admconfirm is '관리자인증확인';
COMMENT ON COLUMN admin.admauth is '관리자권한';
COMMENT ON COLUMN admin.admdate is '관리자가입일';

    SELECT memid
    FROM member
    WHERE memname = '왕눈이2'  AND mememail = 'qydrnfldy@naver.com';



1. INSERT    
1) 등록
delete from admin
where adminno = 1;

INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'master', 'master@naver.com', '123456', '마스터', 'Y', 'M', sysdate);
INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin1', 'admin1@naver.com', '123456', '왕눈이1', 'Y', 'N', sysdate);
INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin2', 'admin2@naver.com', '123456', '왕눈이2', 'Y', 'A', sysdate);
INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin3', 'admin3@naver.com', '123456', '왕눈왕눈', 'Y', 'A', sysdate);
INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin4', 'admin4@naver.com', '123456', '투투투', 'Y', 'A', sysdate);
INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin5', 'admin5@naver.com', '123456', '투투1', 'Y', 'A', sysdate);
INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin6', 'admin6@naver.com', '123456', '아로미', 'Y', 'A', sysdate);
INSERT INTO admin(adminno, admid, admemail, admpasswd, admname, admconfirm, admauth, admdate)
VALUES((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin), 'admin7', 'admin7@naver.com', '123456', '아로미미', 'Y', 'A', sysdate);


2) 중복 이메일 검사 관련 SQL 
-- 0: 중복 아님, 1: 중복  
SELECT COUNT(admemail) as cnt
FROM admin
WHERE admemail = 'test10@gmail.com';

3) 이메일 인증
UPDATE admin
SET admconfirm = 'Y'
WHERE admemail='test10@gmail.com' AND admsignkey='ascsdawe12382347';  
  
4) MASTER 계정의 조회
SELECT COUNT(adminno) as cnt
FROM admin
WHERE admauth = 'M';
  
  select *
  from admin
  
2. SELECT
SELECT * FROM admin;
  
1) 회원 전체 목록 
SELECT adminno, admemail, admpasswd, admname, admconfirm, admauth, admdate
FROM admin
ORDER BY adminno ASC;

 ADMINNO ADMEMAIL        ADMPASSWD ADMNAME ADMSIGNKEY       ADMCONFIRM ADMAUTH ADMDATE
 ------- --------------- --------- ------- ---------------- ---------- ------- ---------------------
       1 test1@naver.com 1234      운영자1    ascsdawe12382347 N          N       2017-11-17 12:11:25.0
       2 test2@naver.com 1234      운영자2    ascsdawe12382347 N          N       2017-11-17 12:11:26.0
       3 test3@naver.com 1234      운영자3    ascsdawe12382347 N          N       2017-11-17 12:11:27.0

   
2) 회원 정보 보기
SELECT adminno, admemail, admpasswd, admname, admconfirm, admauth, admdate
FROM admin
WHERE adminno = 1;
  
  
  
3. UPDATE  
1) 회원 정보 수정
UPDATE admin
SET admauth='M'
WHERE adminno = 1;
  
2) 패스워드 변경
- 기존 패스워드 검사
- DAO: public int countPasswd(int adminno, String passwd){ ... }
SELECT count(*) as cnt
FROM admin
WHERE adminno = 1 AND admpasswd='1234';
 
- 패스워드 변경
- DAO: public int updatePasswd(int adminno, String passwd){ ... }
UPDATE admin
SET admpasswd='12345'
WHERE adminno=1;
  
  
4. DELETE  
1) 전체 회원 삭제
DELETE FROM admin;
 
2) 특정 회원 삭제
DELETE FROM admin
WHERE adminno = 1;
  
  
5. 로그인 관련 SQL 
- DAO: public int login(String email, String passwd){ ... }
 
- Email 정보를 이용한 조회
SELECT adminno, admemail, admpasswd, admname, admconfirm, admauth, admdate
FROM admin
WHERE admemail = 'test10@gmail.com';
   
SELECT count(*) as cnt
FROM admin
WHERE admemail = 'test10@gmail.com' AND admpasswd='1234';
 
  
6. 검색
- 목록은 제작시 검색을 기반으로 제작하며 전체 내용은 전체 검색과도 같습니다.

1) 선언: 
- WHERE admname LIKE '왕눈이'
admname 컬럼의 값이 '왕눈이'인 레코드 전부 출력

- WHERE admname LIKE '%왕눈이%'
admname 컬럼의 값중 '왕눈이'가 들어간 레코드 전부 출력

- WHERE admname LIKE '왕눈이%'
admname 컬럼의 값중 '왕눈이'로 시작하는 레코드 전부 출력

- WHERE admname LIKE '%왕눈이'
admname 컬럼의 값중 '왕눈이'로 종료하는 레코드 전부 출력
  
      
7. 페이징
SELECT adminno, admemail, admpasswd, admname, admconfirm, admauth, admdate
FROM admin
WHERE admname LIKE '%왕눈이%'
ORDER BY adminno DESC
LIMIT 0, 5;

     
* 컬럼의 추가
ALTER TABLE admin 
ADD COLUMN 추가할 컬럼명 VARCHAR(100) AFTER admconfirm;

