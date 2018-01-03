package dev.mvc.qnareply;

import java.util.HashMap;
import java.util.List;

public interface QnareplyProcInter {
  /**
   * <XMP>
   * ��� ���
   * <insert id="create" parameterType="QnareplyVO">
   * </XMP>
   * @param sharedreplyVO
   * @return ����� ���ڵ� ����
   */
  public int create (QnareplyVO qnareplyVO);

  /**
   * <XMP>
   * Ư�� ��� ��ȸ
   * <select id="read" resultType="QnareplyVO" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public QnareplyVO read(int qrno);
  
  /**
   * <XMP>
   * ��� ��� + ����¡
   * <select id="total_list_reply" resultType="QnareplyVO" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public List<QnareplyVO> list_all_qnareply (HashMap hashMap);
  
  /**
   * <XMP>
   * ��� �� ���ڵ� ����
   * <select id="search_count" resultType="int" parameterType="int">
   * </XMP>
   * @return
   */
  public int search_count (int qnano);
  
  /**
   * <XMP>
   * ����¡ ó�� (�⺻ �����)
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����]  
   * </XMP>
   * @param nowPage ����������
   * @param search_count �� ���ڵ� ����
   * @param qnano �Խñ� ��ȣ
   * @return ����¡ ���� ���ڿ�
   */
  public String paging(int nowPage, int search_count, int qnano);
 
  /**
   * <XMP>
   * ��� ����
   * <update id="update" parameterType="QnareplyVO">
   * </XMP>
   * @param sharedreplyVO
   * @return
   */
  public int update (QnareplyVO qnareplyVO);
  
  /**
   * <XMP>
   * ��� ����
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param shreplyno
   * @return
   */
  public int delete(int qrno);
  
  /**
   * <XMP>
   * �Խñ� ������ ��� ��ü ����
   * <delete id="delete_all" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int delete_all (int qnano);

}
