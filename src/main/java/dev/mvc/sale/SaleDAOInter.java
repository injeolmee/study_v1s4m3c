package dev.mvc.sale;

import java.util.HashMap;
import java.util.List;

public interface SaleDAOInter {

  /**
   * <XMP>
   * 게시글 등록
   * <insert id="create" parameterType="SaleVO">
   * </XMP>
   * @param saleVO
   * @return
   */
  public int create (SaleVO saleVO);
  
  /**
   * <XMP>
   * 검색 + 목록 + 페이징
   * <select id="list" resultType="SaleVO" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public List <SaleVO> list_search (HashMap hashMap);
  
  /**
   * <XMP>
   * 검색 레코드 갯수 산출
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int search_count (HashMap hashMap);

  /**
   * <XMP>
   * 게시글 조회
   * <select id="read" resultType="SaleVO" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public SaleVO read (int saleno);

  /**
   * <XMP>
   * 게시글 이전글 조회
   * <select id="read_pre" resultType="int" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public int read_pre (int saleno);
  
  /**
   * <XMP>
   * 게시글 다음글 조회
   * <select id="read_post" resultType="int" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public int read_post (int saleno);
  
  /**
   * <XMP>
   * 조회수 상승
   * <update id="increaseCnt" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public int increaseCnt (int saleno);
  
  /**
   * <XMP>
   * 게시글 수정
   * <update id="update" parameterType="SaleVO">
   * </XMP>
   * @param saleVO
   * @return
   */
  public int update(SaleVO saleVO);
  
  /**
   * <XMP>
   * 게시글 삭제
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public int delete(int saleno);
  
  /**
   * <XMP>
   * 아이디 검사
   * <select id="member_check" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int member_check (HashMap<String, Object> hashMap);
  
  /**
   * <XMP>
   * 관리자 게시글 등록
   * <insert id="create_admin" parameterType="SaleVO">
   * </XMP>
   * @param saleVO
   * @return int
   */
  public int create_admin(SaleVO saleVO);





}
