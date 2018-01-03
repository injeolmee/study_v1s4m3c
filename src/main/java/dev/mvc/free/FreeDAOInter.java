package dev.mvc.free;

import java.util.HashMap;
import java.util.List;

public interface FreeDAOInter {
    
  /**
   * <XMP>
   * 게시글 등록
   * <insert id="create" parameterType="FreeVO">
   * </XMP>
   * @param freeVO
   * @return 등록할 게시글 갯수
   */
  public int create(FreeVO freeVO); 
  
  /**
   * <XMP>
   * 게시판 목록
   * <select id="list" resultType="FreeVO">
   * </XMP>
   * @return 게시판 목록
   */
  public List <FreeVO> list();
  
  /**
   * <XMP>
   * 게시글 조회
   * <select id="read" resultType="FreeVO" parameterType="int" >
   * </XMP>
   * @param freeno
   * @return
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
   * 패스워드 검사
   * <select id="passwd_check" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int passwd_check (HashMap<String, Object> hashMap);
  
  
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
   * 검색 기능
   * <select id="serach" resultType="FreeVO" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public List <FreeVO> list_search(HashMap hashMap);
  
  /**
   * <XMP>
   * 검색된 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="FreeVO">
   * </XMP>
   * @return
   */
  public int search_count(HashMap hashMap);  
  
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
  public int member_check (HashMap<String, Object> hashMap);
  
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
  
}
