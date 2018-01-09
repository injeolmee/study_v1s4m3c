package dev.mvc.room;

import java.util.HashMap;
import java.util.List;

public interface RoomProcInter {
  /**
   * <Xmp>
   * ���͵�� �� ���
   * <insert id="create" parameterType="RoomVO">
   * </Xmp>
   * @param roomVO
   * @return int
   */
  public int create(RoomVO roomVO);
  
  
  /**
   * <Xmp>
   * ��� (�˻� & ����¡)
   * <select id="list" resultType="RoomVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public List<RoomVO> list(HashMap hashMap);
  
  
  /**
   * �˻��� ���ڵ� ����
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * @return
   */
  public int search_count(HashMap hashMap); 
  
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param ronalo �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String ronalo); 
  
  
  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * <select id="read" resultType="RoomVO" parameterType="int">
   * @param rono �� ��ȣ
   * @return
   */
  public RoomVO read(int rono);
  
  
  /**
   * <Xmp>
   * ������
   * <update id="update" parameterType="int">
   * </Xmp> 
   * @param room
   * @return
   */
  public RoomVO update(int rono);
  
  
  /**
   * <Xmp>
   * ����ó��
   * <update id="update" parameterType="RoomVO"> 
   * </Xmp>
   * @param vo
   * @return
   */
  public int update(RoomVO vo);
  
  
  /**
   * ���� ó��
   * <delete id="delete" parameterType="int">
   * @param rono
   * @return
   */
  public int delete(int rono);

}
