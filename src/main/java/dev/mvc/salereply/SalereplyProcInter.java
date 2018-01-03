package dev.mvc.salereply;

import java.util.HashMap;
import java.util.List;

public interface SalereplyProcInter {
  
  /**
   * <XMP>
   * ��� ���
   * <insert id="create" parameterType="SalereplyVO" >
   * </XMP>
   * @param salereplyVO
   * @return ����� ���ڵ� ����
   */
  public int create (SalereplyVO salereplyVO);
  
  /**
   * <XMP>
   * ��� ���
   * <select id="list_reply" resultType="SalereplyVO" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public List <SalereplyVO >list_reply (int saleno);

  /**
   * <XMP>
   * ���� ��� �� ��� ���� ����
   * <update id='updateAnsnum' parameterType="SalereplyVO">
   * </XMP>
   * @param salereplyVO
   * @return
   */
  public int updateAnsnum (SalereplyVO salereplyVO);
  
  /**
   * <XMP>
   * ���� ���
   * <insert id="reply" parameterType="SalereplyVO">
   * </XMP>
   * @param salereplyVO
   * @return
   */
  public int reply (SalereplyVO salereplyVO);
  
  /**
   * <XMP>
   * Ư�� ��� ��ȸ
   * <select id="read" parameterType="int" resultType="SalereplyVO">
   * </XMP>
   * @param saleno
   * @return
   */
  public SalereplyVO read(int sreplyno);
  
  /**
   * <XMP>
   * ��� ��� + ����¡
   * <select id="total_list_reply" resultType="SalereplyVO" parameterType="HashMap" >
   * </XMP>
   * @param hashMap
   * @return ��� ��ϰ� ����¡ ���
   */
  public List<SalereplyVO> total_list_reply (HashMap<String, Object> hashMap);
  
  /**
   * <XMP>
   * ��� �� ���ڵ� ���� ����
   * <select id="search_count" resultType="int" parameterType="int">
   * </XMP>
   * @param hashMap
   * @return count (����� �� ���ڵ� ����)
   */
  public int search_count (int saleno);
  
  /**
   * <XMP>
   * ����¡ ó�� (�⺻ �����)
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����]  
   * </XMP>
   * @param nowPage ����������
   * @param search_count �� ���ڵ� ����
   * @param saleno �Խñ� ��ȣ
   * @return ����¡ ���� ���ڿ�
   */
  public String paging(int nowPage, int search_count, int saleno);
  
  /**
   * <XMP>
   * ��� ����
   * <update id="update" parameterType="SalereplyVO">
   * </XMP>
   * @param salereplyVO
   * @return
   */
  public int update (SalereplyVO salereplyVO);
  
  /**
   * <XMP>
   * ��� ����
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param freplyno
   * @return
   */
  public int delete(int sreplyno);
  
  /**
   * <XMP>
   * �θ� ����� seqno�� 0���� ����
   * <update id="update_seqno" parameterType="int">
   * </XMP>
   * @param sreplyno
   * @return
   */
  public int update_seqno (int sreplyno);
  
  /**
   * <XMP>
   * �θ� ��ۿ� ���Ͽ� ���� ��� Content�� ������ ����ϱ� ���� ó��
   * <update id="update_parent" parameterType="int">
   * </XMP>
   * @param sreplyno
   * @return
   */
  public int update_parent (int sreplyno);
  
  /**
   * <XMP>
   * �Խñ� ������ ��� ��ü ����
   * <delete id="delete_all" parameterType="int">
   *</XMP>
   * @param saleno
   * @return
   */
  public int delete_all (int saleno);
}
