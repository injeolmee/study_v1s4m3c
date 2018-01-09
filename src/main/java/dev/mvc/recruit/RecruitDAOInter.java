package dev.mvc.recruit;

import java.util.HashMap;
import java.util.List;

import dev.mvc.studylist.StudyListVO;

public interface RecruitDAOInter {

  
  /**
   * <xml> 
   *  신청 받기
   * <insert id="create" parameterType="RecruitVO">
   * </xml>
   * 
   * @param RecruitVO
   * @return int
   */
  public int create(RecruitVO recruitVO);
  
  /**
   * <xml> 
   *  스터디그룹을 등록과 동시에 모집시작
   * <insert id="create" parameterType="RecruitVO">
   * </xml>
   * 
   * @param StudyListVO
   * @return int
   */
  public int create(StudyListVO studyListVO);
  
  
  /**<xml>
   * 신청 테이블 리스트
   *  회워테이블 신청테이블
   *    <select id="recruit_list" resultType="Recruit_MemberVO" parameterType="int">
   * </xml>
   * @param Recruit_MemberVO
   * @return List
   */
  public List<Recruit_MemberVO> recruit_list(int stdlist_no);
  
  /**<xml>
   * 스터디리스트 구성 리스트
   *    <select id="recruit_list_Y" resultType="Recruit_MemberVO" parameterType="int">
   * </xml>
   * @param Recruit_MemberVO
   * @return List
   */
  public List<Recruit_MemberVO> recruit_list_Y(int stdlist_no);
  
  /**
   * <xmp>
   *  팀장 권한을 초기화 
   *    <update id= "leader_auth" parameterType="Hashmap">
   * </xmp>
   * @param hashmap
   * @return int
   */
  public int leader_auth(HashMap hashmap);
  
  
  /**
   * <xmp>
   *   회원 승인
   *   <update id = "confirm_Y" parameterType="Hashmap">
   * </xmp>
   * @param hashmap
   * @return int
   */
  public int confirm_Y(HashMap hashmap);
  
  /**
   * <xmp>
   *   회원 승인
   *   <update id = "confirm_Y" parameterType="Hashmap">
   * </xmp>
   * @param hashmap
   * @return int
   */
  public int confirm_N(HashMap hashmap);
  
  /**
   * <xmp>
   *   스터디그룹 신청리스트 신청 중복 검사
   *   <select id= "check_memberno" resultType="int" parameterType="Hashmap">
   * </xmp>
   * @param hashmap
   * @return int
   */
  public int check_memberno(HashMap hashmap);
  
  
  /**
   * <xml> 
   *  스터디그룹에 관련된 신청리스트 삭제
   * <delete id="delete" parameterType="int">
   * </xml>
   * 
   * @param stdlist_no
   * @return int
   */
  public int delete(int stdlist_no);
  
  
  /**
   * <xmp>
   *   <select id= "check_leader" resultType="String" parameterType="Hashmap">
   *   현재 회원이 해당 스터디의 팀장인지 아닌지 조회
   * </xmp>
   * @param hashMap
   * @return
   */
  public String check_leader(HashMap<String, Object> hashMap);
  
}
