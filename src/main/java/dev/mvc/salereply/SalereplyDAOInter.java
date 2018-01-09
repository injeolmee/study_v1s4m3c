package dev.mvc.salereply;

import java.util.HashMap;
import java.util.List;


public interface SalereplyDAOInter {
  
  /**
   * <XMP>
   * 댓글 등록
   * <insert id="create" parameterType="SalereplyVO" >
   * </XMP>
   * @param salereplyVO
   * @return 등록할 레코드 갯수
   */
  public int create (SalereplyVO salereplyVO);

  /**
   * <XMP>
   * 댓글 목록 (페이징 제외한 원본)
   * <select id="list_reply" resultType="SalereplyVO" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public List <SalereplyVO >list_reply (int saleno);
 
  /**
   * <XMP>
   * 대댓글 등록 시 댓글 순서 조절
   * <update id='updateAnsnum' parameterType="SalereplyVO">
   * </XMP>
   * @param salereplyVO
   * @return
   */
  public int updateAnsnum (SalereplyVO salereplyVO);
  
  /**
   * <XMP>
   * 대댓글 등록
   * <insert id="reply" parameterType="SalereplyVO">
   * </XMP>
   * @param salereplyVO
   * @return
   */
  public int reply (SalereplyVO salereplyVO);

  /**
   * <XMP>
   * 특정 댓글 조회
   * <select id="read" parameterType="int" resultType="SalereplyVO">
   * </XMP>
   * @param saleno
   * @return
   */
  public SalereplyVO read(int sreplyno);
  
  /**
   * <XMP>
   * 댓글 목록 + 페이징
   * <select id="total_list_reply" resultType="SalereplyVO" parameterType="HashMap" >
   * </XMP>
   * @param hashMap
   * @return
   */
  public List<SalereplyVO> total_list_reply (HashMap<String, Object> hashMap);
  
  /**
   * <XMP>
   * 댓글 총 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="int">
   * </XMP>
   * @return
   */
  public int search_count (int saleno);
  
  /**
   * <XMP>
   * 댓글 수정
   * <update id="update" parameterType="SalereplyVO">
   * </XMP>
   * @param salereplyVO
   * @return
   */
  public int update (SalereplyVO salereplyVO);
  
  /**
   * <XMP>
   * 댓글 삭제
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param freplyno
   * @return
   */
  public int delete(int sreplyno);
  
  /**
   * <XMP>
   * 부모 댓글의 seqno를 0으로 변경
   * <update id="update_seqno" parameterType="int">
   * </XMP>
   * @param sreplyno
   * @return
   */
  public int update_seqno (int sreplyno);
  
  /**
   * <XMP>
   * 부모 댓글에 대하여 삭제 대신 Content만 변경해 출력하기 위한 처리
   * <update id="update_parent" parameterType="int">
   * </XMP>
   * @param sreplyno
   * @return
   */
  public int update_parent (int sreplyno);
  
  /**
   * <XMP>
   * 게시글 삭제시 댓글 전체 삭제
   * <delete id="delete_all" parameterType="int">
   *</XMP>
   * @param saleno
   * @return
   */
  public int delete_all (int saleno);
  
  /**
   * <XMP>
   * 관리자 댓글 등록
   * <insert id="create_admin" parameterType="SalereplyVO" >
   * </XMP>
   * @param sharedreplyVO
   * @return
   */
  public int create_admin(SalereplyVO salereplyVO);

  /**
   * <XMP>
   * 관리자 대댓글 등록
   * <insert id="reply_admin" parameterType="SalereplyVO">
   * </XMP>
   * @param salereplyVO
   * @return
   */
  public int reply_admin(SalereplyVO salereplyVO);
  
  /**
   * <XMP>
   * 부모 댓글일 경우 하위 댓글이 존재하는지 검사
   * <select id="parent_check" parameterType="int" resultType="int">
   * </XMP>
   * @param sreplygrpno
   * @return
   */
  public int parent_check (int sreplygrpno);
  
  /**
   * <XMP>
   * 대댓글과 관련되어 맨 마지막 댓글인지 검사
   * <select id="reply_check" parameterType="HashMap" resultType="int">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int reply_check (HashMap hashMap);
  
  
}
