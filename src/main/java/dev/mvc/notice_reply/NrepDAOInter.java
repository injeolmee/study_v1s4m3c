package dev.mvc.notice_reply;

import java.util.HashMap;
import java.util.List;

public interface NrepDAOInter {
  
  
/**
 * 공지댓글 등록
 * <insert id="nrep_create" parameterType="NrepVO">
 * @param nrepVO
 * @return
 */
  public int nrep_create(NrepVO nrepVO);

  
  /**
   * 공지댓글 리스트
   * <select id="nrep_list" resultType="NrepVO" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public List<NrepVO> nrep_list(HashMap hashMap);
  
  
  /**
   * 공지사항 글별 댓글 개수
   * <select id = 'search_count' resultType="int" parameterType="int">
   * @param noticeno
   * @return
   */
  public int search_count(int noticeno);
  
  
  /**
   * 공지글별 댓글 중 하나의 댓글 리드
   * <select id="nrep_read" resultType="NrepVO"  parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public NrepVO nrep_read(HashMap hashMap);
  
  
  /**
   * 댓글수정
   * <update id="nrep_update" parameterType="NrepVO">
   * @param nrepVO
   * @return
   */
  public int nrep_update(NrepVO nrepVO);
  
  
  /**
   * 댓글 하나 삭제
   * <delete id="nrep_delete" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public int nrep_delete(HashMap hashMap);
  
  
  /**
   * 공지 글별 모든 댓글 삭제
   * <delete id="nrep_deleteAll" parameterType="int">
   * @param noticeno
   * @return
   */
  public int nrep_deleteAll(int noticeno);
  
  
  /**
   * 댓글 쓴 회원번호와 맞는지 검사
   * <select id= "nrep_ck_memberno" resultType="int" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public int nrep_ck_memberno(HashMap hashMap);
  
  
}
