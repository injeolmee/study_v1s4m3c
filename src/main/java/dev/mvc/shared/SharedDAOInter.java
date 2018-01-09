package dev.mvc.shared;

import java.util.HashMap;
import java.util.List;

public interface SharedDAOInter {

  /**
   * <XMP>
   * �Խñ� ���
   * <insert id="create" parameterType="SharedVO">
   * </XMP>
   * @param sharedVO
   * @return ����� �Խñ� ����
   */
  public int create(SharedVO sharedVO);
  
  /**
   * <XMP>
   * �Խ��� �˻� ���
   * <select id="list" resultType="SharedVO" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public List<SharedVO> list_search(HashMap hashMap);
  
  /**
   * <XMP>
   * �˻��� ���ڵ� ���� ����
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * <XMP>
   * �Խñ� ��ȸ
   * <select id="read" resultType="SharedVO" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public SharedVO read(int sharedno);
  
  /**
   * <XMP>
   * ��ȸ�� ���
   * <update id="increaseCnt" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int increaseCnt (int sharedno);

  /**
   * <XMP>
   * �Խñ� ����
   * <update id="update" parameterType="SharedVO">
   * </XMP>
   * @param sharedVO
   * @return
   */
  public int update (SharedVO sharedVO);
  
  /**
   * <XMP>
   * �Խñ� ����
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int delete (int sharedno);
  
  /**
   * <XMP>
   * �Խñ� ������ ��ȸ
   * <select id="read_pre" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int read_pre (int sharedno);
  
  /**
   * <XMP>
   * �Խñ� ������ ��ȸ
   * <select id="read_post" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int read_post (int sharedno);
  
  /**
   * <XMP>
   * ���̵� �˻�
   * <select id="member_check" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int member_check (HashMap<String, Object> hashMap);
  
  /**
   * <XMP>
   * ������ �Խñ� ���
   * <insert id="create_adminno" parameterType="SharedVO">
   * </XMP>
   * @param sharedVO
   * @return int
   */
  public int create_admin (SharedVO sharedVO);

}
