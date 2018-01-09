package dev.mvc.my_pds;

import java.util.HashMap;
import java.util.List;

import dev.mvc.my_std_catelist.My_std_catelistVO;

public interface My_pdsProcInter {

  /**
   * <select id="insert" parameterType="My_pdsVO">
   * 내 스터디 글 등록
   * @param my_pdsVO
   * @return 레코드 갯수
   */
  public int insert(My_pdsVO my_pdsVO);
  
  /**
   * <select id="read" parameterType="HashMap" resultType="My_pdsVO">
   * 내 스터디 글 조회
   * @param hashMap
   * @return List<My_pdsVO>
   */
  public List<My_pdsVO> list(HashMap<String, Object> hashMap);
  
  /**
   * <select id="search_mylistno" parameterType="HashMap" resultType="int">
   * stdlist_no : 스터디 번호
   * cateno : 하위 카테고리 번호
   * 두 인자를 통해 mylistno를 조회한다.
   * 
   * @param hashMap
   * @return mylistno
   */
  public My_std_catelistVO search_mylistno(HashMap<String, Integer> hashMap);
  
  /**
   * <select id="search_cateno/stdlist_no" parameterType="int" resultType="My_std_catelistVO">
   * mylistno를 통해 stdlist_no, cateno를 조회
   * @param mylistno
   * @return My_std_catelistVO - mylistno에 해당하는 값
   */
  public My_std_catelistVO search_cateno_stdlist_no(int mylistno);
  
  /**
   * <select id="search_memname" parameterType="int" resultType="String">
   * 회원번호를 통해 회원의 이름을 조회한다.
   * @param memberno
   * @return 회원이름
   */
  public String search_memname(int memberno);
  
  /**
   * <select id="read" parameterType="int" resultType="My_pdsVO">
   * 글 정보 조회
   * @param pdsno
   * @return My_pdsVO 글 정보
   */
  public My_pdsVO read(int pdsno);
  
  /**
   * <update id="update" parameterType="My_pdsVO">
   * 글 수정
   * @return int (처리된 컬럼 갯수)
   */
  public int update(My_pdsVO my_pdsVO);
  
  /**
   * <select id="check_passwd" parameterType="String" resultType="HashMap">
   * 글 패스워드 일치 검사
   * @param passwd
   * @return 일치하면 1, 아니면 0
   */
  public int check_passwd(HashMap<String, Object> hashMap);
  
  /**
   * <delete id="delete" parameterType="int">
   * 글 삭제
   * @param pdsno
   * @return
   */
  public int delete(int pdsno);
  
  /**
   * <update id="inc_cnt" parameterType="int">
   * 글 조회수 증가
   * @param pdsno
   * @return
   */
  public int inc_cnt(int pdsno);
  
  /**
   * <update id="inc_like" parameterType="int">
   * 글 추천수 증가
   * @param pdsno
   * @return
   */
  public int inc_like(int pdsno);
  
  /**
   * <select id="lastest_pdsno" resultType="int">
   * 가장 최근에 등록된 pdsno를 가져온다.
   * @return 가장 최근에 추가된 pdsno
   */
  public int lastest_pdsno();
  
  /**
   * <delete id="del_file" parameterType="int">
   * 특정 게시글의 파일만 지운다.
   * ex) 수정에서 이미 등록된 파일을 삭제할 때.(추가 등록 없이)
   * @param pdsno
   * @return
   */
  public int del_file(int pdsno);
  
  /**
   * <select id="search_count" parameterType="HashMap" resultType="int">
   * 검색 결과 갯수 반환
   * @param hashMap
   * @return 검색 결과 수
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 페이징 메소드
   * @param search_count
   * @param nowPage
   * @param cateno
   * @param stdlist_no
   * @return
   */
  public String paging(int search_count, int nowPage, int cateno, int stdlist_no);
  
}
