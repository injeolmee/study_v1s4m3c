package dev.mvc.rvlike;

import java.util.HashMap;

public interface RvlikeProcInter {

  /**
   * <XMP>
   * 좋아요 등록
   * <insert id="create" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int create(HashMap hashMap);
  
  /**
   * <xml> 
   *  이미 입력되어 있는 회원 인지 확인
   * <select id="like_chk" resultType="int" parameterType="HashMap">
   * </xml>
   * @param Hashmap
   * @return int
   */
  public int like_chk(HashMap hashMap);
  
  
  /**
   * <xml> 
   *  회원마다 , 스터디그룹마다 좋아요 싫어요 여부 확인
   * <select id="check" resultType="int" parameterType="Hashmap">
   * </xml>
   * 
   * @param Hashmap
   * @return conlikeVO
   */
  public int check(HashMap hashMap);
  
  /**
   * <xml> 
   *  회원이 스터디그룹의 좋아요를 체크한다면 좋아요 체크여부를 1로 바꿈
   * <update id="like_chk_y" parameterType="Hashmap">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int like_chk_y(HashMap hashMap);
  
  /**
   * <xml> 
   *  회원이 스터디그룹의 좋아요가 체크된 상태에서 좋아요를 다시 체크한다면 좋아요 체크여부를 0로 바꿈
   * <update id="like_chk_n" parameterType="Hashmap">
   * </xml>
   * 
   * @param Hashmap
   * @return int
   */
  public int like_chk_n(HashMap hashMap);
  
  
  /**
   * 좋아요 rvno별 삭제
   * <delete id="delete" parameterType="int">
   * @param rvno
   * @return
   */
  public int deleteByRvno(int rvno);
  
}
