package dev.mvc.freereply;

import java.util.HashMap;
import java.util.List;

public interface FreereplyProcInter {

  /**
   * <XMP>
   * ��� ���
   * <insert id="create" parameterType="FreereplyVO">
   * </XMP>
   * @param freereplyVO
   * @return ����� ���ڵ� ����
   */
  public int create (FreereplyVO freereplyVO);
  
  /**
   * <XMP>
   * ���� ��� �� ��� ���� ����
   * <update id='updateAnsnum' parameterType="FreereplyVO"> 
   * </XMP>
   * @param freereplyVO
   * @return
   */
  public int updateAnsnum (FreereplyVO freereplyVO);
  
  /**
   * <XMP>
   * ���� ���
   * <insert id="reply" parameterType="FreereplyVO">
   * </XMP>
   * @param freereplyVO
   * @return
   */
  public int reply (FreereplyVO freereplyVO);

  /**
   * <XMP>
   * Ư�� ��� ��ȸ
   * <select id="read" resultType="FreereplyVO" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public FreereplyVO read(int freplyno);
  
  /**
   * <XMP>
   * ��� ��� + ����¡
   * <select id="total_list_reply" resultType="FreereplyVO" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public List<FreereplyVO> total_list_reply (HashMap<String, Object> hashMap);
  
  /**
   * <XMP>
   * ��� �� ���ڵ� ����
   * <select id="search_count" resultType="int" parameterType="int">
   * </XMP>
   * @return
   */
  public int search_count (int freeno);
  
  /**
   * <XMP>
   * ����¡ ó�� (�⺻ �����)
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����]  
   * </XMP>
   * @param nowPage ����������
   * @param search_count �� ���ڵ� ����
   * @param freeno �Խñ� ��ȣ
   * @return ����¡ ���� ���ڿ�f
   */
  public String paging(int nowPage, int search_count, int freeno);
  
  /**
   * <XMP>
   * ��� ����
   * <update id="update" parameterType="FreereplyVO">
   * </XMP>
   * @param freereplyVO
   * @return
   */
  public int update (FreereplyVO freereplyVO);
  
  /**
   * <XMP>
   * ��� ����
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param freplyno
   * @return
   */
  public int delete(int freplyno);
  
  /**
   * <XMP>
   * �θ� ����� seqno�� 0���� ����
   * <update id="update_seqno" parameterType="int">
   * </XMP>
   * @param freplyno
   * @return
   */
  public int update_seqno (int freplyno);
  
  /**
   * <XMP>
   * �θ� ��ۿ� ���Ͽ� ���� ��� Content�� ������ ����ϱ� ���� ó��
   * <update id="update_parent" parameterType="int">
   * </XMP>
   * @param freplyno
   * @return
   */
  public int update_parent (int freplyno);
  
  /**
   * <XMP>
   * �Խñ� ������ ��� ��ü ����
   * <delete id="delete_all" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public int delete_all (int freeno);
}
