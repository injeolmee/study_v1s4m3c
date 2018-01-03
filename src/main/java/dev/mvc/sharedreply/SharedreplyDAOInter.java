package dev.mvc.sharedreply;

import java.util.HashMap;
import java.util.List;

import dev.mvc.salereply.SalereplyVO;

public interface SharedreplyDAOInter {
   
  /**
   * <XMP>
   * ��� ���
   * <insert id="create" parameterType="SharedreplyVO">
   * </XMP>
   * @param sharedreplyVO
   * @return ����� ���ڵ� ����
   */
  public int create (SharedreplyVO sharedreplyVO);
  
  /**
   * <XMP>
   * ���� ��� �� ��� ���� ����
   * <update id='updateAnsnum' parameterType="SharedreplyVO">  
   * </XMP>
   * @param salereplyVO
   * @return
   */
  public int updateAnsnum (SharedreplyVO sharedreplyVO);
  
  /**
   * <XMP>
   * ���� ���
   * <insert id="reply" parameterType="SharedreplyVO">
   * </XMP>
   * @param salereplyVO
   * @return
   */
  public int reply (SharedreplyVO sharedreplyVO);

  /**
   * <XMP>
   * Ư�� ��� ��ȸ
   * <select id="read" resultType="SharedreplyVO" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public SharedreplyVO read(int shreplyno);
  
  /**
   * <XMP>
   * ��� ��� + ����¡
   * <select id="total_list_reply" resultType="SharedreplyVO" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public List<SharedreplyVO> total_list_reply (HashMap<String, Object> hashMap);
  
  /**
   * <XMP>
   * ��� �� ���ڵ� ����
   * <select id="search_count" resultType="int" parameterType="int">
   * </XMP>
   * @return
   */
  public int search_count (int sharedno);
  
  /**
   * <XMP>
   * ��� ����
   * <update id="update" parameterType="SharedreplyVO">
   * </XMP>
   * @param sharedreplyVO
   * @return
   */
  public int update (SharedreplyVO sharedreplyVO);
  
  /**
   * <XMP>
   * ��� ����
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param shreplyno
   * @return
   */
  public int delete(int shreplyno);
  
  /**
   * <XMP>
   * �θ� ����� seqno�� 0���� ����
   * <update id="update_seqno" parameterType="int">
   * </XMP>
   * @param shreplyno
   * @return
   */
  public int update_seqno (int shreplyno);
  
  /**
   * <XMP>
   * �θ� ��ۿ� ���Ͽ� ���� ��� Content�� ������ ����ϱ� ���� ó��
   * <update id="update_parent" parameterType="int">
   * </XMP>
   * @param shreplyno
   * @return
   */
  public int update_parent (int shreplyno);
  
  /**
   * <XMP>
   * �Խñ� ������ ��� ��ü ����
   * <delete id="delete_all" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int delete_all (int sharedno);
  

}
