package dev.mvc.shared;

import java.util.HashMap;
import java.util.List;

public interface SharedDAOInter {

  /**
   * <XMP>
   * 게시글 등록
   * <insert id="create" parameterType="SharedVO">
   * </XMP>
   * @param sharedVO
   * @return 등록할 게시글 갯수
   */
  public int create(SharedVO sharedVO);
  
  /**
   * <XMP>
   * 게시판 검색 기능
   * <select id="list" resultType="SharedVO" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public List<SharedVO> list_search(HashMap hashMap);
  
  /**
   * <XMP>
   * 검색된 레코드 갯수 산출
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * <XMP>
   * 게시글 조회
   * <select id="read" resultType="SharedVO" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public SharedVO read(int sharedno);
  
  /**
   * <XMP>
   * 조회수 상승
   * <update id="increaseCnt" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int increaseCnt (int sharedno);

  /**
   * <XMP>
   * 게시글 수정
   * <update id="update" parameterType="SharedVO">
   * </XMP>
   * @param sharedVO
   * @return
   */
  public int update (SharedVO sharedVO);
  
  /**
   * <XMP>
   * 게시글 삭제
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int delete (int sharedno);
  
  /**
   * <XMP>
   * 게시글 이전글 조회
   * <select id="read_pre" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int read_pre (int sharedno);
  
  /**
   * <XMP>
   * 게시글 다음글 조회
   * <select id="read_post" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int read_post (int sharedno);
  
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
   * <insert id="create_adminno" parameterType="SharedVO">
   * </XMP>
   * @param sharedVO
   * @return int
   */
  public int create_admin (SharedVO sharedVO);

}
