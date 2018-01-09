/**********************************/
/* Table Name: 카테고리 */
/**********************************/
CREATE TABLE category(
      cateno                              NUMBER(10)       NOT NULL       PRIMARY KEY,
      catename                            VARCHAR2(50)       NOT NULL,
      cateseqno                           NUMBER(5)       NOT NULL,
      catevisible                         VARCHAR2(1)       DEFAULT 'Y'       NOT NULL
);

COMMENT ON TABLE category is '카테고리';
COMMENT ON COLUMN category.cateno is '카테고리번호';
COMMENT ON COLUMN category.catename is '카테고리이름';
COMMENT ON COLUMN category.cateseqno is '카테고리출력순서';
COMMENT ON COLUMN category.catevisible is '카테고리출력여부';

INSERT INTO "STUDY"."CATEGORY" (CATENO, CATENAME, CATESEQNO, CATEVISIBLE) VALUES ('1', '내스터디', '1', 'Y');
INSERT INTO "STUDY"."CATEGORY" (CATENO, CATENAME, CATESEQNO, CATEVISIBLE) VALUES ('2', '내스터디-공지사항', '2', 'Y');
INSERT INTO "STUDY"."CATEGORY" (CATENO, CATENAME, CATESEQNO, CATEVISIBLE) VALUES ('3', '내스터디-자유게시판', '3', 'Y');
INSERT INTO "STUDY"."CATEGORY" (CATENO, CATENAME, CATESEQNO, CATEVISIBLE) VALUES ('4', '내스터디-자료실', '4', 'Y');
INSERT INTO "STUDY"."CATEGORY" (CATENO, CATENAME, CATESEQNO, CATEVISIBLE) VALUES ('5', '내스터디-스터디 관리', '5', 'Y');



/**********************************/
/* Table Name: 내스터디-카테고리 리스트 테이블 */
/**********************************/
CREATE TABLE my_std_catelist(
      mylistno                            NUMBER(10)       NOT NULL       PRIMARY KEY,
      stdlist_no                          NUMBER(10)       NULL ,
      cateno                              NUMBER(10)       NULL ,
  FOREIGN KEY (stdlist_no) REFERENCES studylist (stdlist_no),
  FOREIGN KEY (cateno) REFERENCES category (cateno)
);

COMMENT ON TABLE my_std_catelist is '내스터디-카테고리 리스트 테이블';
COMMENT ON COLUMN my_std_catelist.mylistno is '내스터디리스트 구분 번호';
COMMENT ON COLUMN my_std_catelist.stdlist_no is '스터디번호';
COMMENT ON COLUMN my_std_catelist.cateno is '카테고리번호';


/**********************************/
/* Table Name: 내 스터디 글 목록 */
/**********************************/
CREATE TABLE my_pds(
      pdsno                               NUMBER(10)       NOT NULL       PRIMARY KEY,
      pdstitle                            VARCHAR2(500)       NOT NULL,
      pdspasswd                           VARCHAR2(100)       NULL ,
      pdscontent                          VARCHAR2(4000)       NULL ,
      pdsfile1                            VARCHAR2(300)       NULL ,
      pdsfilesize                         NUMBER(30)       DEFAULT 0       NULL ,
      pdsthumb                            VARCHAR2(100)       NULL ,
      pdsword                             VARCHAR2(100)       NULL ,
      pdslike                             NUMBER(10)       DEFAULT 0       NULL ,
      pdscnt                              NUMBER(10)       DEFAULT 0       NULL ,
      pdsdate                             DATE       NOT NULL,
      memberno                            NUMBER(10)       NULL ,
      mylistno                            NUMBER(10)       NULL ,
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (mylistno) REFERENCES my_std_catelist (mylistno)
);

COMMENT ON TABLE my_pds is '내 스터디 글 목록';
COMMENT ON COLUMN my_pds.pdsno is '글 번호';
COMMENT ON COLUMN my_pds.pdstitle is '글 제목';
COMMENT ON COLUMN my_pds.pdspasswd is '글 비밀번호';
COMMENT ON COLUMN my_pds.pdscontent is '글 내용';
COMMENT ON COLUMN my_pds.pdsfile1 is '글 첨부파일명';
COMMENT ON COLUMN my_pds.pdsfilesize is '글 사진 사이즈';
COMMENT ON COLUMN my_pds.pdsthumb is '글 사진 thumb';
COMMENT ON COLUMN my_pds.pdsword is '글 검색어';
COMMENT ON COLUMN my_pds.pdslike is '글 추천수';
COMMENT ON COLUMN my_pds.pdscnt is '글 조회수';
COMMENT ON COLUMN my_pds.pdsdate is '글 작성일';
COMMENT ON COLUMN my_pds.memberno is '회원번호';
COMMENT ON COLUMN my_pds.mylistno is '내스터디리스트 구분 번호';