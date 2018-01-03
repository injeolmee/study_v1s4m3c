package dev.mvc.std_recom;

import java.util.HashMap;
import java.util.List;

public interface Std_RecomProcInter {

  /**
   * <xml> 
   *  ȸ���� ���� �а� �Ǹ� ȸ�������� �Է�
   * <insert id="create" parameterType="std_recomVO">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int create(HashMap hashmap);
  
  /**
   * <xml> 
   *  �̹� �ԷµǾ� �ִ� ȸ�� ���� Ȯ��
   * <select id="std_recom_check" resultType="int" parameterType="Hashmap">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int std_recom_check(HashMap hashmap);
 
  /**
   * <xml> 
   *  ȸ������ , ���͵�׷츶�� ���ƿ� �Ⱦ�� ���� Ȯ��
   * <select id="check" resultType="std_recomVO" parameterType="Hashmap">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int check(HashMap hashmap);
  
  /**
   * <xml> 
   *  ȸ���� ���͵�׷��� ���ƿ並 üũ�Ѵٸ� ���ƿ� üũ���θ� 1�� �ٲ�
   * <update id="good_ch_Y" parameterType="Hashmap">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int good_ch_Y(HashMap hashmap);
  
  /**
   * <xml> 
   *  ȸ���� ���͵�׷��� ���ƿ䰡 üũ�� ���¿��� ���ƿ並 �ٽ� üũ�Ѵٸ� ���ƿ� üũ���θ� 0�� �ٲ�
   * <update id="good_ch_N" parameterType="Hashmap">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int good_ch_N(HashMap hashmap);
  
  /**
   * <xml> 
   *  ���͵�׷쿡 ���� ���ƿ� ����
   *  <delete id="delete" parameterType="int">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int delete(int stdlist_no);
  
}
