package dev.mvc.recruit;

import java.util.HashMap;
import java.util.List;

import dev.mvc.studylist.StudyListVO;

public interface RecruitDAOInter {

  
  /**
   * <xml> 
   *  ��û �ޱ�
   * <insert id="create" parameterType="RecruitVO">
   * </xml>
   * 
   * @param RecruitVO
   * @return int
   */
  public int create(RecruitVO recruitVO);
  
  /**
   * <xml> 
   *  ���͵�׷��� ��ϰ� ���ÿ� ��������
   * <insert id="create" parameterType="RecruitVO">
   * </xml>
   * 
   * @param StudyListVO
   * @return int
   */
  public int create(StudyListVO studyListVO);
  
  
  /**<xml>
   * ��û ���̺� ����Ʈ
   *  ȸ�����̺� ��û���̺�
   *    <select id="recruit_list" resultType="Recruit_MemberVO" parameterType="int">
   * </xml>
   * @param Recruit_MemberVO
   * @return List
   */
  public List<Recruit_MemberVO> recruit_list(int stdlist_no);
  
  /**<xml>
   * ���͵𸮽�Ʈ ���� ����Ʈ
   *    <select id="recruit_list_Y" resultType="Recruit_MemberVO" parameterType="int">
   * </xml>
   * @param Recruit_MemberVO
   * @return List
   */
  public List<Recruit_MemberVO> recruit_list_Y(int stdlist_no);
  
  /**
   * <xmp>
   *  ���� ������ �ʱ�ȭ 
   *    <update id= "leader_auth" parameterType="Hashmap">
   * </xmp>
   * @param hashmap
   * @return int
   */
  public int leader_auth(HashMap hashmap);
  
  
  /**
   * <xmp>
   *   ȸ�� ����
   *   <update id = "confirm_Y" parameterType="Hashmap">
   * </xmp>
   * @param hashmap
   * @return int
   */
  public int confirm_Y(HashMap hashmap);
  
  /**
   * <xmp>
   *   ȸ�� ����
   *   <update id = "confirm_Y" parameterType="Hashmap">
   * </xmp>
   * @param hashmap
   * @return int
   */
  public int confirm_N(HashMap hashmap);
  
  /**
   * <xmp>
   *   ���͵�׷� ��û����Ʈ ��û �ߺ� �˻�
   *   <select id= "check_memberno" resultType="int" parameterType="Hashmap">
   * </xmp>
   * @param hashmap
   * @return int
   */
  public int check_memberno(HashMap hashmap);
  
  
  /**
   * <xml> 
   *  ���͵�׷쿡 ���õ� ��û����Ʈ ����
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
   *   ���� ȸ���� �ش� ���͵��� �������� �ƴ��� ��ȸ
   * </xmp>
   * @param hashMap
   * @return
   */
  public String check_leader(HashMap<String, Object> hashMap);
  
}
