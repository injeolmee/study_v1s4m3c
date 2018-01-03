package dev.mvc.studylist_reply;

import java.util.HashMap;
import java.util.List;

public interface ReplyProcInter {

  /**
   * <xml> 
   *  스터디그룹별 각 댓글 등록
   * <insert id="create" parameterType="ReplyVO">
   * </xml>
   * 
   * @param ReplyVO
   * @return int
   */
  public int create(ReplyVO replyVO);
  
  /**<xml>
   *  스터디그룹별 댓글 리스트
   *    <select id="list" resultType="ReplyVO" parameterType="int">
   * </xml>
   * @param StudyListVO
   * @return List
   */
  public List<ReplyVO> list(int stdlist_no);
  
  /**<xml>
   *  스터디그룹별 댓글 리스트 + paging 
   *    <select id="list2" resultType="ReplyVO" parameterType="hashmap">
   * </xml>
   * @param hashmap
   * @return List<ReplyVO>
   */
  public List<ReplyVO> list2(HashMap hashmap);
  
  /**<xml>
   *  스터디리스트별 댓글중 하나의 댓글을 읽어온다.
   *  <select id="read" resultType="ReplyVO"  parameterType="Hashmap">
   * </xml>
   * @param hashmap
   * @return ReplyVO
   */
  public ReplyVO read(HashMap hashmap);
  
  /**
   * <xml> 
   *  스터디그룹별 댓글 수정
   * <update id="update" parameterType="ReplyVO">
   * </xml>
   * 
   * @param ReplyVO
   * @return int
   */
  public int update(ReplyVO replyVO);
  
  /**
   * <xml> 
   *  스터디그룹별 댓글 삭제
   * <delete id="delete" parameterType="Hashmap">
   * </xml>
   * 
   * @param hashmap
   * @return int
   */
  public int delete(HashMap hashmap);
  
  /**
   * <xml> 
   *  스터디그룹에 관련된 모든 댓글 삭제
   * <delete id="delete_all" parameterType="int">
   * </xml>
   * 
   * @param stdlist_no
   * @return int
   */
  public int delete_all(int stdlist_no);
  
  /**
   * <xml> 
   *  스터디그룹별 각 댓글을 작성자를 확인한다.
   * <select id= "check_memberno" resultType="int" parameterType="Hashmap">
   * </xml>
   * 
   * @param ReplyVO
   * @return int
   */
  public int check_memberno(HashMap hashmap);
  
  /**
   * <xml> 
   *  스터디그룹별 댓글의 개수를 검색한다.
   * <select id = 'search_count' resultType="int" parameterType="int">
   * </xml>
   * 
   * @param ReplyVO
   * @return int
   */
  public int search_count(int stdlist_no);
  
  
  /**  1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @return 페이징 생성 문자열
   */ 
  public String paging(int search_count, int nowPage, int stdlist_no);
}
