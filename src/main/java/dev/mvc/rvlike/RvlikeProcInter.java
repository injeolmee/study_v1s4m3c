package dev.mvc.rvlike;

import java.util.HashMap;

public interface RvlikeProcInter {

  /**
   * <XMP>
   * ���ƿ� ���
   * <insert id="create" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int create(HashMap hashMap);
  
  /**
   * <xml> 
   *  �̹� �ԷµǾ� �ִ� ȸ�� ���� Ȯ��
   * <select id="like_chk" resultType="int" parameterType="HashMap">
   * </xml>
   * @param Hashmap
   * @return int
   */
  public int like_chk(HashMap hashMap);
  
  
  /**
   * <xml> 
   *  ȸ������ , ���͵�׷츶�� ���ƿ� �Ⱦ�� ���� Ȯ��
   * <select id="check" resultType="int" parameterType="Hashmap">
   * </xml>
   * 
   * @param Hashmap
   * @return conlikeVO
   */
  public int check(HashMap hashMap);
  
  /**
   * <xml> 
   *  ȸ���� ���͵�׷��� ���ƿ並 üũ�Ѵٸ� ���ƿ� üũ���θ� 1�� �ٲ�
   * <update id="like_chk_y" parameterType="Hashmap">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int like_chk_y(HashMap hashMap);
  
  /**
   * <xml> 
   *  ȸ���� ���͵�׷��� ���ƿ䰡 üũ�� ���¿��� ���ƿ並 �ٽ� üũ�Ѵٸ� ���ƿ� üũ���θ� 0�� �ٲ�
   * <update id="like_chk_n" parameterType="Hashmap">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int like_chk_n(HashMap hashMap);
  
  
  /**
   * ���ƿ� rvno�� ����
   * <delete id="delete" parameterType="int">
   * @param rvno
   * @return
   */
  public int deleteByRvno(int rvno);
  
}
