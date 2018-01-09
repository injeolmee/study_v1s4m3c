package dev.mvc.free;

import java.util.HashMap;
import java.util.List;

public interface FreeProcInter {

  /**
   * <XMP>
   * 게시글 등록
   * <insert id="create" parameterType="FreeVO">
   * </XMP>
   * @param freeVO
   * @return int
   */
  public int create(FreeVO freeVO); 
  
  /**
   * <XMP>
   * 게시판 목록 + 검색 + 페이징
   * <select id="list" resultType="FreeVO">
   * </XMP>
   * @return list
   */
  public List <FreeVO> list();
  
  /**
   * <XMP>
   * 게시글 조회
   * <select id="read" resultType="FreeVO" parameterType="int" >
   * </XMP>
   * @param freeno
   * @return freeVO
   */
  public FreeVO read (int freeno);
  
  /**
   * <XMP>
   * 게시글 수정
   * <update id="update" parameterType="FreeVO">
   * </XMP>
   * @param freeVO
   * @return
   */
  public int update(FreeVO freeVO);
    
  /**
   * <XMP>
   * 조회수 증가
   *  <update id="increaseCnt" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public int increaseCnt (int freeno);
  
  /**
   * <XMP>
   * 게시글 삭제
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public int delete (int freeno);
  
  /**
   * <XMP>
   * 검색 리스트 출력
   * <select id="serach" resultType="FreeVO" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public List <FreeVO> list_search(HashMap hashMap);
  
  /**
   * 검색된 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="BlogVO">
   * @return
   */
  public int search_count(HashMap hashMap);  
   
  /**
   * <XMP>
   * 페이징 처리
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음]  
   * </XMP>
   * @param nowPage 현재페이지
   * @param word 검색어
   * @param serach 검색할 Value Option
   * @return 페이징 생성 문자열
   */
  public String paging(int nowPage, int search_count, String word, String serach); 
  
  /**
   * <XMP>
   * 추천수 Max 순 출력 리스트
   *  <select id="list_like" resultType="FreeVO" >
   *  <XMP>
   * @return
   */
  public List <FreeVO> list_like(HashMap<String, Object> hashMap2);
  
  /**
   * <XMP>
   * 조회수 Max 순 출력 리스트
   * <select id="list_cnt" resultType="FreeVO" parameterType="HashMap">
   * </XMP>
   * @param hashMap3
   * @return
   */
  public List <FreeVO> list_cnt(HashMap<String, Object> hashMap3);
  
  /**
   * <XMP>
   * 이전글 조회
   * <select id="read_pre" resultType="int" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public int read_pre (int freeno);
  
  /**
   * <XMP>
   * 다음글 조회
   * <select id="read_post" resultType="int" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public int read_post (int freeno);
  
  /**
   * <XMP>
   * 아이디 체크
   * <select id="member_check" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int member_check (FreeVO freeVO);
  
  /**
   * <XMP>
   * 좋아요가 0에서 1로 변경
   * <update id="increaseLike" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public int increaseLike(int freeno);
  
  /**
   * <XMP>
   * 좋아요가 1에서 0으로 변경
   * <update id="decreaseLike" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public int decreaseLike(int freeno);
  
  /**
   * <XMP>
   * 관리자 게시글 등록
   * <insert id="create_admin" parameterType="FreeVO">
   * </XMP>
   * @param freeVO
   * @return
   */
  public int create_admin(FreeVO freeVO);
}
