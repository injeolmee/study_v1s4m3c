package dev.mvc.sharedreply;

import java.util.HashMap;
import java.util.List;

import dev.mvc.salereply.SalereplyVO;

public interface SharedreplyDAOInter {
   
  /**
   * <XMP>
   * 댓글 등록
   * <insert id="create" parameterType="SharedreplyVO">
   * </XMP>
   * @param sharedreplyVO
   * @return 등록할 레코드 갯수
   */
  public int create (SharedreplyVO sharedreplyVO);
  
  /**
   * <XMP>
   * 대댓글 등록 시 댓글 순서 조절
   * <update id='updateAnsnum' parameterType="SharedreplyVO">  
   * </XMP>
   * @param salereplyVO
   * @return
   */
  public int updateAnsnum (SharedreplyVO sharedreplyVO);
  
  /**
   * <XMP>
   * 대댓글 등록
   * <insert id="reply" parameterType="SharedreplyVO">
   * </XMP>
   * @param salereplyVO
   * @return
   */
  public int reply (SharedreplyVO sharedreplyVO);

  /**
   * <XMP>
   * 특정 댓글 조회
   * <select id="read" resultType="SharedreplyVO" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public SharedreplyVO read(int shreplyno);
  
  /**
   * <XMP>
   * 댓글 목록 + 페이징
   * <select id="total_list_reply" resultType="SharedreplyVO" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public List<SharedreplyVO> total_list_reply (HashMap<String, Object> hashMap);
  
  /**
   * <XMP>
   * 댓글 총 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="int">
   * </XMP>
   * @return
   */
  public int search_count (int sharedno);
  
  /**
   * <XMP>
   * 댓글 수정
   * <update id="update" parameterType="SharedreplyVO">
   * </XMP>
   * @param sharedreplyVO
   * @return
   */
  public int update (SharedreplyVO sharedreplyVO);
  
  /**
   * <XMP>
   * 댓글 삭제
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param shreplyno
   * @return
   */
  public int delete(int shreplyno);
  
  /**
   * <XMP>
   * 부모 댓글의 seqno를 0으로 변경
   * <update id="update_seqno" parameterType="int">
   * </XMP>
   * @param shreplyno
   * @return
   */
  public int update_seqno (int shreplyno);
  
  /**
   * <XMP>
   * 부모 댓글에 대하여 삭제 대신 Content만 변경해 출력하기 위한 처리
   * <update id="update_parent" parameterType="int">
   * </XMP>
   * @param shreplyno
   * @return
   */
  public int update_parent (int shreplyno);
  
  /**
   * <XMP>
   * 게시글 삭제시 댓글 전체 삭제
   * <delete id="delete_all" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int delete_all (int sharedno);
  

}
