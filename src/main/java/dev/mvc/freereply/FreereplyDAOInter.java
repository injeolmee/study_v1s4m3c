package dev.mvc.freereply;

import java.util.HashMap;
import java.util.List;

public interface FreereplyDAOInter {
  
  /**
   * <XMP>
   * 댓글 등록
   * <insert id="create" parameterType="FreereplyVO">
   * </XMP>
   * @param freereplyVO
   * @return 등록할 레코드 갯수
   */
  public int create (FreereplyVO freereplyVO);
  
  /**
   * <XMP>
   * 대댓글 등록 시 댓글 순서 조절
   * <update id='updateAnsnum' parameterType="FreereplyVO"> 
   * </XMP>
   * @param freereplyVO
   * @return
   */
  public int updateAnsnum (FreereplyVO freereplyVO);
  
  /**
   * <XMP>
   * 대댓글 등록
   * <insert id="reply" parameterType="FreereplyVO">
   * </XMP>
   * @param freereplyVO
   * @return
   */
  public int reply (FreereplyVO freereplyVO);

  /**
   * <XMP>
   * 특정 댓글 조회
   * <select id="read" resultType="FreereplyVO" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public FreereplyVO read(int freplyno);
  
  /**
   * <XMP>
   * 댓글 목록 + 페이징
   * <select id="total_list_reply" resultType="FreereplyVO" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public List<FreereplyVO> total_list_reply (HashMap<String, Object> hashMap);
  
  /**
   * <XMP>
   * 댓글 총 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="int">
   * </XMP>
   * @return
   */
  public int search_count (int freeno);
  
  /**
   * <XMP>
   * 댓글 수정
   * <update id="update" parameterType="FreereplyVO">
   * </XMP>
   * @param freereplyVO
   * @return
   */
  public int update (FreereplyVO freereplyVO);
  
  /**
   * <XMP>
   * 댓글 삭제
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param freplyno
   * @return
   */
  public int delete(int freplyno);
  
  /**
   * <XMP>
   * 부모 댓글의 seqno를 0으로 변경
   * <update id="update_seqno" parameterType="int">
   * </XMP>
   * @param freplyno
   * @return
   */
  public int update_seqno (int freplyno);
  
  /**
   * <XMP>
   * 부모 댓글에 대하여 삭제 대신 Content만 변경해 출력하기 위한 처리
   * <update id="update_parent" parameterType="int">
   * </XMP>
   * @param freplyno
   * @return
   */
  public int update_parent (int freplyno);

  /**
   * <XMP>
   * 게시글 삭제시 댓글 전체 삭제
   * <delete id="delete_all" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public int delete_all (int freeno);
}
