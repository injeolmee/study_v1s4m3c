package dev.mvc.std_recom;

import java.util.HashMap;
import java.util.List;

public interface Std_RecomProcInter {

  /**
   * <xml> 
   *  회원이 글을 읽게 되면 회원정보를 입력
   * <insert id="create" parameterType="std_recomVO">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int create(HashMap hashmap);
  
  /**
   * <xml> 
   *  이미 입력되어 있는 회원 인지 확인
   * <select id="std_recom_check" resultType="int" parameterType="Hashmap">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int std_recom_check(HashMap hashmap);
 
  /**
   * <xml> 
   *  회원마다 , 스터디그룹마다 좋아요 싫어요 여부 확인
   * <select id="check" resultType="std_recomVO" parameterType="Hashmap">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int check(HashMap hashmap);
  
  /**
   * <xml> 
   *  회원이 스터디그룹의 좋아요를 체크한다면 좋아요 체크여부를 1로 바꿈
   * <update id="good_ch_Y" parameterType="Hashmap">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int good_ch_Y(HashMap hashmap);
  
  /**
   * <xml> 
   *  회원이 스터디그룹의 좋아요가 체크된 상태에서 좋아요를 다시 체크한다면 좋아요 체크여부를 0로 바꿈
   * <update id="good_ch_N" parameterType="Hashmap">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int good_ch_N(HashMap hashmap);
  
  /**
   * <xml> 
   *  스터디그룹에 대한 좋아요 삭제
   *  <delete id="delete" parameterType="int">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int delete(int stdlist_no);
  
}
