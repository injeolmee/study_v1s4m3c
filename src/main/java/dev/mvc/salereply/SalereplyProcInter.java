package dev.mvc.salereply;

import java.util.HashMap;
import java.util.List;

public interface SalereplyProcInter {
  
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
   * 댓글 목록
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
   * @return 댓글 목록과 페이징 출력
   */
  public List<SalereplyVO> total_list_reply (HashMap<String, Object> hashMap);
  
  /**
   * <XMP>
   * 댓글 총 레코드 갯수 산출
   * <select id="search_count" resultType="int" parameterType="int">
   * </XMP>
   * @param hashMap
   * @return count (댓글의 총 레코드 갯수)
   */
  public int search_count (int saleno);
  
  /**
   * <XMP>
   * 페이징 처리 (기본 목록형)
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음]  
   * </XMP>
   * @param nowPage 현재페이지
   * @param search_count 총 레코드 갯수
   * @param saleno 게시글 번호
   * @return 페이징 생성 문자열
   */
  public String paging(int nowPage, int search_count, int saleno);
  
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
}
