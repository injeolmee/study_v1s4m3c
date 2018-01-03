package dev.mvc.qnareply;

import java.util.HashMap;
import java.util.List;

public interface QnareplyProcInter {
  /**
   * <XMP>
   * 댓글 등록
   * <insert id="create" parameterType="QnareplyVO">
   * </XMP>
   * @param sharedreplyVO
   * @return 등록할 레코드 갯수
   */
  public int create (QnareplyVO qnareplyVO);

  /**
   * <XMP>
   * 특정 댓글 조회
   * <select id="read" resultType="QnareplyVO" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public QnareplyVO read(int qrno);
  
  /**
   * <XMP>
   * 댓글 목록 + 페이징
   * <select id="total_list_reply" resultType="QnareplyVO" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public List<QnareplyVO> list_all_qnareply (HashMap hashMap);
  
  /**
   * <XMP>
   * 댓글 총 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="int">
   * </XMP>
   * @return
   */
  public int search_count (int qnano);
  
  /**
   * <XMP>
   * 페이징 처리 (기본 목록형)
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음]  
   * </XMP>
   * @param nowPage 현재페이지
   * @param search_count 총 레코드 갯수
   * @param qnano 게시글 번호
   * @return 페이징 생성 문자열
   */
  public String paging(int nowPage, int search_count, int qnano);
 
  /**
   * <XMP>
   * 댓글 수정
   * <update id="update" parameterType="QnareplyVO">
   * </XMP>
   * @param sharedreplyVO
   * @return
   */
  public int update (QnareplyVO qnareplyVO);
  
  /**
   * <XMP>
   * 댓글 삭제
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param shreplyno
   * @return
   */
  public int delete(int qrno);
  
  /**
   * <XMP>
   * 게시글 삭제시 댓글 전체 삭제
   * <delete id="delete_all" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int delete_all (int qnano);

}
