DROP TABLE member CASCADE CONSTRAINTS;

desc member;
delete from member;
select * from member;

/**********************************/
/* Table Name: 회원정보 */
/**********************************/
CREATE TABLE member(
    memberno                          NUMBER(10)     PRIMARY KEY,
    memid                               VARCHAR2(50)     NOT NULL,
    mememail                          VARCHAR2(100)     NOT NULL,
    mempasswd                         VARCHAR2(100)     NOT NULL,
    memname                           VARCHAR2(20)     NOT NULL,
    memconfirm                        VARCHAR2(5)    DEFAULT 'N'     NOT NULL,
    memauth                           VARCHAR2(5)    DEFAULT 'B'     NOT NULL,
    membirth                          VARCHAR2(50)     NOT NULL,
    memgender                         VARCHAR2(5)    NOT NULL,
    memaddress                        VARCHAR2(100)    NOT NULL,
    memphone                          VARCHAR2(50)     NULL,
    memsns                            VARCHAR2(100)    NULL ,
    memintro                          VARCHAR2(1000)     NULL ,
    memphoto                          VARCHAR2(500)    NULL ,
    memphoto_t                        VARCHAR2(500)    NULL ,
    memsize                           NUMBER(30)     DEFAULT 0     NULL,
    mbirthvb                          VARCHAR2(5)    DEFAULT 'Y'    NULL,
    mgendervb                         VARCHAR2(5)    DEFAULT 'Y'    NULL,
    maddressvb                        VARCHAR2(5)    DEFAULT 'Y'     NULL,
    mphonevb                          VARCHAR2(5)    DEFAULT 'Y'     NULL,
    msnsvb                            VARCHAR2(5)    DEFAULT 'Y'     NULL,
    mintrovb                          VARCHAR2(5)    DEFAULT 'Y'     NULL,
    mphotovb                          VARCHAR2(5)    DEFAULT 'Y'    NULL,
    memdate                           DATE     NOT NULL
);

COMMENT ON TABLE member is '회원정보';
COMMENT ON COLUMN member.memberno is '회원번호';
COMMENT ON COLUMN member.memid is '회원아이디';
COMMENT ON COLUMN member.mememail is '회원이메일';
COMMENT ON COLUMN member.mempasswd is '회원비밀번호';
COMMENT ON COLUMN member.memname is '회원이름';
COMMENT ON COLUMN member.memconfirm is '회원인증확인';
COMMENT ON COLUMN member.memauth is '회원권한';
COMMENT ON COLUMN member.membirth is '회원생년월일';
COMMENT ON COLUMN member.memgender is '회원성별';
COMMENT ON COLUMN member.memaddress is '회원주소';
COMMENT ON COLUMN member.memphone is '회원전화번호';
COMMENT ON COLUMN member.memsns is '회원SNS';
COMMENT ON COLUMN member.memintro is '회원소개';
COMMENT ON COLUMN member.memphoto is '회원사진파일';
COMMENT ON COLUMN member.memphoto_t is '회원사진썸네일';
COMMENT ON COLUMN member.memsize is '회원사진파일크기';
COMMENT ON COLUMN member.mbirthvb is '회원생년월일출력여부';
COMMENT ON COLUMN member.mgendervb is '회원성별출력여부';
COMMENT ON COLUMN member.maddressvb is '회원주소출력여부';
COMMENT ON COLUMN member.mphonevb is '회원전화번호출력여부';
COMMENT ON COLUMN member.msnsvb is '회원SNS출력여부';
COMMENT ON COLUMN member.mintrovb is '회원자기소개출력여부';
COMMENT ON COLUMN member.mphotovb is '회원사진출력여부';
COMMENT ON COLUMN member.memdate is '회원가입일';

alter table member modify(mempasswd varchar2(100));

1. INSERT    
1) 등록
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test1', 'test1@gmail.com', 'swKDQnsp/We4EtkPl/YE7g==', '지나',
'Y', 'U', '19891025', '여', '서울 강북구', '01012345678', '인스타그램', '안녕하세요', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test2', 'test2@gmail.com', 'swKDQnsp/We4EtkPl/YE7g==', '지나2',
'Y', 'B', '19891025', '남', '서울 강북구', '01012345678', '인스타그램', '안녕하세요', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test3', 'test3@gmail.com', 'swKDQnsp/We4EtkPl/YE7g==', '인절미',
'Y', 'U', '19891025', '남', '서울 강북구', '01012345678', '인스타그램', '안녕하세요', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test4', 'test4@gmail.com', 'swKDQnsp/We4EtkPl/YE7g==', '인절미2',
'Y', 'U', '19891025', '여', '서울 강북구', '01012345678', '인스타그램', '안녕하세요', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test5', 'test5@gmail.com', 'swKDQnsp/We4EtkPl/YE7g==', '찹쌀',
'Y', 'U', '19891025', '여', '서울 강북구', '01012345678', '인스타그램', '안녕하세요', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);
INSERT INTO member(memberno, memid, mememail, mempasswd, memname, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'test6', 'test6@gmail.com', 'swKDQnsp/We4EtkPl/YE7g==', '찹쌀떡',
'Y', 'U', '19891025', '여', '서울 강북구', '01012345678', '인스타그램', '안녕하세요', 'myphoto.jpg', 'myphoto_t.jpg', 0, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', sysdate);


2) 중복 아이디, 이메일 검사 관련 SQL 
  -- 0: 중복 아님, 1: 중복  
  SELECT COUNT(memid) as cnt
  FROM member
  WHERE memid = 'test1';
  
  SELECT COUNT(mememail) as cnt
  FROM member
  WHERE mememail = 'test10@gmail.com';

3) 이메일 인증
  UPDATE member
  SET memconfirm = 'Y'
  WHERE mememail='test10@gmail.com' AND memsignkey='ascsdawe12382347';
  
  DESC member;
  
  2. SELECT
  SELECT * FROM member;
  
  1) 회원 전체 목록 
   SELECT memberno, memid, mememail, mempasswd, memname, memsignkey, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
   memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate
   FROM member
   ORDER BY memberno ASC;
   
    MEMBERNO MEMEMAIL        MEMPASSWD MEMNAME MEMSIGNKEY       MEMCONFIRM MEMAUTH MEMBIRTH MEMGENDER MEMADDRESS MEMPHONE    MEMSNS MEMINTRO MEMPHOTO    MEMPHOTO_T    MEMSIZE MEMVISIBLE MEMDATE
 -------- --------------- --------- ------- ---------------- ---------- ------- -------- --------- ---------- ----------- ------ -------- ----------- ------------- ------- ---------- ---------------------
        1 test1@gmail.com 1234      회원1     ascsdawe12382347 Y          B       19891025 여         서울 강북구 수유동 01012345678 인스타그램  안녕하세요    myphoto.jpg myphoto_t.jpg       0 N          2017-11-17 12:08:08.0
        2 test2@gmail.com 1234      회원2     ascsdawe12382347 Y          B       19891025 여         서울 강북구 수유동 01012345678 인스타그램  안녕하세요    myphoto.jpg myphoto_t.jpg       0 N          2017-11-17 12:08:09.0
        3 test3@gmail.com 1234      회원3     ascsdawe12382347 Y          B       19891025 여         서울 강북구 수유동 01012345678 인스타그램  안녕하세요    myphoto.jpg myphoto_t.jpg       0 N          2017-11-17 12:08:10.0
    
   
  2) 회원 정보 보기
  SELECT memberno, memid, mememail, mempasswd, memname, memsignkey, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
  memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate
  FROM member
  WHERE memberno = 1;
  
  
  
  3. UPDATE  
  1) 회원 정보 수정
  UPDATE member
  SET mempasswd='swKDQnsp/We4EtkPl/YE7g=='
  WHERE memberno = 7;
  
  2) 패스워드 변경
  - 기존 패스워드 검사
  - DAO: public int countPasswd(int memberno, String passwd){ ... }
  SELECT count(*) as cnt
  FROM member
  WHERE memberno = 1 AND mempasswd='1234';
 
  - 패스워드 변경
  - DAO: public int updatePasswd(int memberno, String passwd){ ... }
  UPDATE member
  SET mempasswd='12345'
  WHERE memberno=1;
  
  
  4. DELETE  
  1) 전체 회원 삭제
  DELETE FROM member;
  
  2) 특정 회원 삭제
  DELETE FROM member
  WHERE memberno = 8;
  
  
  5. 로그인 관련 SQL 
  - DAO: public int login(String email, String passwd){ ... }
 
  - Email 정보를 이용한 조회
  SELECT memberno, memid, mememail, mempasswd, memname, memsignkey, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
  memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate
  FROM member
  WHERE memid = 'test1';
   
  SELECT count(*) as cnt
  FROM member
  WHERE memid = 'test1' AND mempasswd='1234';
 
  
  6. 검색
    - 목록은 제작시 검색을 기반으로 제작하며 전체 내용은
      전체 검색과도 같습니다.

  1) 선언: 
    - WHERE memname LIKE '왕눈이'
       memname 컬럼의 값이 '왕눈이'인 레코드 전부 출력

    - WHERE memname LIKE '%왕눈이%'
      memname 컬럼의 값중 '왕눈이'가 들어간 레코드 전부 출력

    - WHERE memname LIKE '왕눈이%'
      memname 컬럼의 값중 '왕눈이'로 시작하는 레코드 전부 출력

    - WHERE memname LIKE '%왕눈이'
      memname 컬럼의 값중 '왕눈이'로 종료하는 레코드 전부 출력
  
      
  7. 페이징
  SELECT memberno, memid, mememail, mempasswd, memname, memsignkey, memconfirm, memauth, membirth, memgender, memaddress, memphone, 
  memsns, memintro, memphoto, memphoto_t, memsize, mbirthvb, mgendervb, maddressvb, mphonevb, msnsvb, mintrovb, mphotovb, memdate
  FROM member
  WHERE memname LIKE '%왕눈이%'
  ORDER BY memberno DESC
  LIMIT 0, 5;

     
* 컬럼의 추가
  ALTER TABLE member 
  ADD COLUMN 추가할 컬럼명 VARCHAR(100) AFTER memvisible;
  
  SELECT memberno, memid, mememail, r
  FROM (select memberno, memid, mememail, rownum as r
            from(
				            select memberno, memid, mememail
				            from member
                  )
            
  )
  where r >=4 and r <=6
  

  select memberno, memid, mememail, rownum
            from(
                    select memberno, memid, mememail
                    from member
                  )
  where rownum>=4 and rownum <=6
  
  
  