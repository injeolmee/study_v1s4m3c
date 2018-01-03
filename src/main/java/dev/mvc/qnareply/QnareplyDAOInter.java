package dev.mvc.qnareply;

import java.util.HashMap;
import java.util.List;

public interface QnareplyDAOInter {

  /**
   * <XMP>
   * ��� ���
   * <insert id="create" parameterType="QnareplyVO">
   * </XMP>
   * @param qnareplyVO
   * @return
   */
  public int create(QnareplyVO qnareplyVO);
  
  /**
   * <XMP>
   * Ư�� ��� ��ȸ
   * </XMP>
   * @param qrno
   * @return
   */
  public QnareplyVO read(int qrno);
  
  /**
   * <XMP>
   * ��� ��� + ����¡
   * </XMP>
   * @param hashMap
   * @return
   */
  public List<QnareplyVO> list_all_qnareply(HashMap hashMap);
  
  /**
   * <XMP>
   * ��� �� ���ڵ� ����
   * </XMP>
   * @param qnano
   * @return
   */
  public int search_count(int qnano);
  
  /**
   * <XMP>
   * ��� ����
   * </XMP>
   * @param qnareplyVO
   * @return
   */
  public int update(QnareplyVO qnareplyVO);
  
  /**
   * <XMP>
   * ��� ����
   * </XMP>
   * @param qrno
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
