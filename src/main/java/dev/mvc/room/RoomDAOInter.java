package dev.mvc.room;

import java.util.HashMap;
import java.util.List;

public interface RoomDAOInter {
  
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
   * <Xmp>
   * �˻��� ���ڵ� ����
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public int search_count(HashMap hashMap); 
  
  
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
   * @param rono
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
