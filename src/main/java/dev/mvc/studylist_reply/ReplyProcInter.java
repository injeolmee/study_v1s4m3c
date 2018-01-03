package dev.mvc.studylist_reply;

import java.util.HashMap;
import java.util.List;

public interface ReplyProcInter {

  /**
   * <xml> 
   *  ���͵�׷캰 �� ��� ���
   * <insert id="create" parameterType="ReplyVO">
   * </xml>
   * 
   * @param ReplyVO
   * @return int
   */
  public int create(ReplyVO replyVO);
  
  /**<xml>
   *  ���͵�׷캰 ��� ����Ʈ
   *    <select id="list" resultType="ReplyVO" parameterType="int">
   * </xml>
   * @param StudyListVO
   * @return List
   */
  public List<ReplyVO> list(int stdlist_no);
  
  /**<xml>
   *  ���͵�׷캰 ��� ����Ʈ + paging 
   *    <select id="list2" resultType="ReplyVO" parameterType="hashmap">
   * </xml>
   * @param hashmap
   * @return List<ReplyVO>
   */
  public List<ReplyVO> list2(HashMap hashmap);
  
  /**<xml>
   *  ���͵𸮽�Ʈ�� ����� �ϳ��� ����� �о�´�.
   *  <select id="read" resultType="ReplyVO"  parameterType="Hashmap">
   * </xml>
   * @param hashmap
   * @return ReplyVO
   */
  public ReplyVO read(HashMap hashmap);
  
  /**
   * <xml> 
   *  ���͵�׷캰 ��� ����
   * <update id="update" parameterType="ReplyVO">
   * </xml>
   * 
   * @param ReplyVO
   * @return int
   */
  public int update(ReplyVO replyVO);
  
  /**
   * <xml> 
   *  ���͵�׷캰 ��� ����
   * <delete id="delete" parameterType="Hashmap">
   * </xml>
   * 
   * @param hashmap
   * @return int
   */
  public int delete(HashMap hashmap);
  
  /**
   * <xml> 
   *  ���͵�׷쿡 ���õ� ��� ��� ����
   * <delete id="delete_all" parameterType="int">
   * </xml>
   * 
   * @param stdlist_no
   * @return int
   */
  public int delete_all(int stdlist_no);
  
  /**
   * <xml> 
   *  ���͵�׷캰 �� ����� �ۼ��ڸ� Ȯ���Ѵ�.
   * <select id= "check_memberno" resultType="int" parameterType="Hashmap">
   * </xml>
   * 
   * @param ReplyVO
   * @return int
   */
  public int check_memberno(HashMap hashmap);
  
  /**
   * <xml> 
   *  ���͵�׷캰 ����� ������ �˻��Ѵ�.
   * <select id = 'search_count' resultType="int" parameterType="int">
   * </xml>
   * 
   * @param ReplyVO
   * @return int
   */
  public int search_count(int stdlist_no);
  
  
  /**  1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, int stdlist_no);
}
