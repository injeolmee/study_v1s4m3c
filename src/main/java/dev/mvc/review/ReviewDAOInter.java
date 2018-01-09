package dev.mvc.review;

import java.util.HashMap;
import java.util.List;

public interface ReviewDAOInter {
  
  /**
   * <Xmp>
   * ���� ���
   * <insert id="create" parameterType="ReviewVO">
   * </Xmp>
   * @param categoryVO
   * @return
   */
  public int create(ReviewVO reviewVO);
  
  
  /**
   * <Xmp>
   * ī�װ� �׷캰 ���
   * <select id="list" parameterType="HashMap">
   * </Xmp>
   * @param ro_mem_reviewVO
   * @return
   */
  public List<ReviewVO> list(HashMap hashMap);
 
  
  /**
   * ���ڵ� ����
   * <select id="search_count" resultType="int">
   * @return
   */
  public int search_count(int rono); 
  
  /**
   * ����� �б�
   * @param rvno
   * @return
   */
  public ReviewVO read(int rvno);
  
  
  /**
   * ����� ���� ��
   * <update id="update" parameterType="ReviewVO">
   * @param rvno
   * @return
   */
  public ReviewVO update(int rvno);
  
  
  /**
   * ����� ���� ó��
   * @param reviewVO
   * @return
   */
  public int update(ReviewVO reviewVO);
  
  /**
   * ����� ���� ó��
   * <delete id="delete" parameterType="int">
   * @param rvno
   * @return
   */
  public int delete(int rvno);
  
  /**
   * ����� rono�� ����
   * <delete id="delete" parameterType="int">
   * @param rono
   * @return
   */
  public int countByRono(int rono);
  
  /**
   * ����� rono�� ����
   * <delete id="delete" parameterType="int">
   * @param rono
   * @return
   */
  public int deleteByRono(int rono);
  
  /**
   * <XMP>
   * ���ƿ� �� ����
   * </XMP>
   * @param rvno
   * @return
   */
  public int likecnt_up(int rvno);
  
  /**
   * <XMP>
   * ���ƿ� �� ����
   * </XMP>
   * @param rvno
   * @return
   */
  public int likecnt_down(int rvno);
  
  
  /**
   * <XMP>
   * ��ϵ� ������ ����ū �����ȣ���� ������
   * <select id="rvno" resultType="int">
   * </XMP>
   * @param rvno
   * @return
   */
  public int rvno();
  
  
}
