package dev.mvc.qnareply;

import java.util.HashMap;
import java.util.List;

public interface QnareplyDAOInter {

  /**
   * <XMP>
   * ´ñ±Û µî·Ï
   * <insert id="create" parameterType="QnareplyVO">
   * </XMP>
   * @param qnareplyVO
   * @return
   */
  public int create(QnareplyVO qnareplyVO);
  
  /**
   * <XMP>
   * Æ¯Á¤ ´ñ±Û Á¶È¸
   * </XMP>
   * @param qrno
   * @return
   */
  public QnareplyVO read(int qrno);
  
  /**
   * <XMP>
   * ´ñ±Û ¸ñ·Ï + ÆäÀÌÂ¡
   * </XMP>
   * @param hashMap
   * @return
   */
  public List<QnareplyVO> list_all_qnareply(HashMap hashMap);
  
  /**
   * <XMP>
   * ´ñ±Û ÃÑ ·¹ÄÚµå °¹¼ö
   * </XMP>
   * @param qnano
   * @return
   */
  public int search_count(int qnano);
  
  /**
   * <XMP>
   * ´ñ±Û ¼öÁ¤
   * </XMP>
   * @param qnareplyVO
   * @return
   */
  public int update(QnareplyVO qnareplyVO);
  
  /**
   * <XMP>
   * ´ñ±Û »èÁ¦
   * </XMP>
   * @param qrno
   * @return
   */
  public int delete(int qrno);
  
  /**
   * <XMP>
   * °Ô½Ã±Û »èÁ¦½Ã ´ñ±Û ÀüÃ¼ »èÁ¦
   * <delete id="delete_all" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int delete_all (int qnano);
  
  
  
  
  
  
  
}
