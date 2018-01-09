package dev.mvc.review;

import java.util.HashMap;
import java.util.List;

public interface ReviewProcInter {
  
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
   * <select id="list" parameterType="Ro_Mem_ReviewVO">
   * </Xmp>
   * @param reviewVO
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
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param rono ���͵�� �Խñ� ��ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int rono, int search_count, int nowPage);
  
  
  /**
   * ����� �б�
   * @param rvno
   * @return
   */
  public ReviewVO read(int rvno);
  
  
  /**
   * <XMP>
   * ����� ���ƿ� �� ����
   * </XMP>
   * @param rvno
   * @return
   */
  public int likecnt_up(int rvno);
  
  /**
   * <XMP>
   * ����� ���ƿ� �� ����
   * </XMP>
   * @param rvno
   * @return
   */
  public int likecnt_down(int rvno);
  
  
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
   * ��ϵ� ������ ����ū �����ȣ���� ������
   * <select id="rvno" resultType="int">
   * </XMP>
   * @param rvno
   * @return
   */
  public int rvno();
  
}
