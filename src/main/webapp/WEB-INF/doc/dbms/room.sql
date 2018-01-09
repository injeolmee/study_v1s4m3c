0) 테이블 삭제
DROP TABLE admin;
DROP TABLE rvlike CASCADE CONSTRAINTS;
DROP TABLE review CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;

1) 테이블 생성
CREATE TABLE room(
    rono                              NUMBER(10)     NOT NULL ,
    adminno                           NUMBER(10)     NULL ,
    roname                            VARCHAR2(100)    NOT NULL,
    rotel                             VARCHAR2(30)     NOT NULL,
    rosite                            VARCHAR2(200)    NULL ,
    rorunday                          VARCHAR2(100)    NULL ,
    romap                             VARCHAR2(1000)    NOT NULL,
    rolocation                        VARCHAR2(100)     NOT NULL,
    rocity                        VARCHAR2(20)     NOT NULL,
    rogu                            VARCHAR2(20)     NOT NULL,
    rodong                        VARCHAR2(100)      NULL,
    ronalo                         VARCHAR2(200)      NULL,
    rocost                            VARCHAR2(50)     NULL ,
    rofile1                           VARCHAR2(800)    NULL ,
    rosize1                           NUMBER(10)    NULL ,
    rothumb                           VARCHAR2(800)    NULL ,
    rocontent                         VARCHAR2(1000)     NOT NULL,
    rocount                           VARCHAR2(300)    NULL ,
    rooption                          VARCHAR2(150)    NULL,
  PRIMARY KEY (rono),
  FOREIGN KEY (adminno) REFERENCES admin (adminno)
);

COMMENT ON TABLE room is '스터디룸';
COMMENT ON COLUMN room.rono is '스터티룸 등록 번호';
COMMENT ON COLUMN room.adminno is '관리자번호';
COMMENT ON COLUMN room.roname is '스터디룸 상호';
COMMENT ON COLUMN room.rotel is '스터디룸 전화';
COMMENT ON COLUMN room.rosite is '스터디룸 홈페이지';
COMMENT ON COLUMN room.rorunday is '스터디룸 운영요일/시간';
COMMENT ON COLUMN room.romap is '스터디룸 지도';
COMMENT ON COLUMN room.rolocation is '스터디룸 위치';
COMMENT ON COLUMN room.rocost is '스터디룸 비용';
COMMENT ON COLUMN room.rofile1 is '스터디룸 이미지';
COMMENT ON COLUMN room.rosize1 is '스터디룸 이미지크기';
COMMENT ON COLUMN room.rothumb is '스터디룸 썸네일';
COMMENT ON COLUMN room.rocontent is '스터디룸 내용';
COMMENT ON COLUMN room.rocount is '스터디룸 룸인원';
COMMENT ON COLUMN room.rooption is '스터디룸 옵션';

2) 삽입
INSERT INTO room(rono, adminno, roname, rotel, rosite, rorunday, romap,
                                 rolocation, rocity, rogu, rodong, rocost, rofile1, rosize1, rothumb, rocontent, rocount, rooption)
VALUES((SELECT NVL(MAX(rono), 0) + 1 as rono FROM room), 1, '카페 상호명', '전화', '홈페이지', '운영시간', '지도',
            '위치', '도시', '구', '동', '비용', '이미지', 0, '스터디룸 썸네일', '내용', '인원수', '옵션');
   
            
3) 목록 
SELECT rono, adminno, roname, rotel, rosite, rorunday, romap, 
          rolocation, rocost, rofile1, rosize1, rothumb, rocontent, rocount, rooption
FROM room
ORDER BY rono DESC;

select rono, rolocation, rocity, rogu, rodong
from room;

 RONO     ROLOCATION     ROCITY     ROGU RODONG
 ----         --------------        ------        ----    ------------------------
    6       서울시 강남구 강남로    서울시    강남구  강남로
    2 서울시 강남구 ㅇㅇ     서울시    강남구  ㅇㅇ
    4 충남 계룡시 계룡로     충남     계룡시  계룡로
    3 서울시 강남구 강남로 1길 서울시    강남구  강남로 1길
    5 서울시 강남구 d      서울시    강남구  d
    7 서울시 강남구 강남로    서울시    강남구  강남로
   10 인천             강화군    강화로  솔데스크 24시 인천 강화군 강화로
    8 강원도 고성군 고성로    강원도    고성군  고성로
    9 경남             거제시    거제로  솔데스크 24시 경남 거제시 거제로
   11 부산광역시          강서구    강서로  솔데스크 24시 부산광역시 강서구 강서로
   12 인천             남구     남구로  솔데스크 24시 인천 남구 남구로
   13 서울시            강남구    1234 솔데스크 24시123 서울시 강남구 1234

SELECT rono, rooption
FROM room
ORDER BY rono DESC;

select *
from room;

on delete from room
where rono = 3;

4) 수정
UPDATE room
SET roname='', rotel='', rosite='', rorunday='', romap='', 
      rolocation='', rocost='', rofile1='', rosize1=0, rothumb='', rocontent='', rooption=''
WHERE rono=1;

4-1) 열 수정
ALTER TABLE room
MODIFY (rooption VARCHAR2(150)  NULL);

4-2) 열 생성
ALTER TABLE room
ADD (rocity VARCHAR2(20) NOT NULL);

ALTER TABLE room
ADD (rogu VARCHAR2(20) NOT NULL);

ALTER TABLE room
ADD (rodong VARCHAR2(100) NULL);

ALTER TABLE room
ADD (ronalo VARCHAR2(200) NULL);
 
5) 삭제
DELETE FROM room ON CASCADE CONSTRAINTS
WHERE rono = 3;

6) 검색
SELECT rono, adminno, cateno, roname, rotel, rosite, rorunday, romap, 
          rolocation, rocost, rofile1, rosize1, rothumb, rocontent, rocount, rooption
FROM blog
WHERE cateno=7 AND location LIKE '%서울%'
ORDER BY rono DESC;

7) 페이징
SELECT rono, adminno, cateno, roname, rotel, rosite, rorunday, romap, 
          rolocation, rocost, rofile1, rosize1, rothumb, rocontent, rocount, rooption, r
FROM(
         SELECT rono, adminno, cateno, roname, rotel, rosite, rorunday, romap, 
                   rolocation, rocost, rofile1, rosize1, rothumb, rocontent, rocount, rooption, rownum as r
         FROM(
                  SELECT rono, adminno, cateno, roname, rotel, rosite, rorunday, romap, 
                            rolocation, rocost, rofile1, rosize1, rothumb, rocontent, rocount, rooption
                  FROM room
                  WHERE cateno=20
                  ORDER BY rono DESC
         )
)
WHERE r >=1 AND r <= 3
ORDER BY rono DESC;
