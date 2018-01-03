package dev.mvc.freelike;

import java.util.HashMap;

public interface FreelikeDAOInter {
  
  /**
   * <XMP>
   * ���ƿ� ���
   * <insert id="create" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return freelikeVO
   */
  public int create(HashMap hashMap);
  
  /**
   * <XMP>
   * �̹� �ԷµǾ� �ִ� ȸ�� ���� Ȯ��
   * <select id="good_chk" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param Hashmap
   * @return int
   */
  public int good_chk(HashMap hashMap);
  
  /**
   * <XMP>
   *  ȸ������ , ���͵�׷츶�� ���ƿ� �Ⱦ�� ���� Ȯ��
   * <select id="check" resultType="int" parameterType="Hashmap">
   * </XMP>
   * @param Hashmap
   * @return freelikeVO
   */
  public int check(HashMap hashMap);
  
  /**
   * <XMP>
   *  ȸ���� ���ƿ並 üũ�Ѵٸ� ���ƿ� üũ���θ� 1�� �ٲ�
   * <update id="good_chk_y" parameterType="Hashmap">
   * </XMP>
   * @param Hashmap
   * @return int
   */
  public int good_chk_y(HashMap hashMap);
  
  /**
   * <XMP>
   *  ȸ���� ���ƿ䰡 üũ�� ���¿��� ���ƿ並 �ٽ� üũ�Ѵٸ� ���ƿ� üũ���θ� 0�� �ٲ�
   * <update id="good_chk_n" parameterType="Hashmap">
   * </XMP>
   * @param Hashmap
   * @return int
   */
  public int good_chk_n(HashMap hashMap);
  
}