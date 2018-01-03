package dev.mvc.job;

import java.util.HashMap;
import java.util.List;

public interface JobDAOInter {
  
  /**
   * <XMP>
   * ä������ ���
   * <insert id="create" parameterType="JobVO">
   * </XMP>
   * @param jobVO
   * @return
   */
  public int create(JobVO jobVO);
  
  /**
   * <XMP>
   * ä������ ���
   * <select id="list_all_jobinfo" resultType="JobVO" parameterType="HashMap">
   * </XMP>
   * @return
   */
  public List<JobVO> list_all_jobinfo(HashMap hashMap);
  
  /**
   * <XMP>
   * ä������ ��ȸ���� ���� ���
   * <select id="list_by_count" resultType="JobVO">
   * </XMP> 
   * @return
   */
  public List<JobVO> list_by_count();
  
  /**
   * <XMP>
   * ä������ ��ȸ
   * <select id="read" resultType="JobVO" parameterType="int">
   * </XMP>
   * @param jobNo
   * @return
   */
  public JobVO read(int jobNo);
  
  /**
   * <XMP>
   * ä�������� ������ ��ȣ
   * <select id="max" resultType="int">
   * </XMP>
   * @return
   */
  public int max();
  
  /**
   * <XMP>
   * ä�������� ó�� ��ȣ
   * <select id="mini" resultType="int">
   * </XMP>
   * @return
   */
  public int mini();
  
  /**
   * <XMP>
   * ä������ �� ��ȸ�� ����
   * <update id="increaseCnt" parameterType="int">
   * </XMP>
   * @param jobNo
   * @return
   */
  public int increaseCnt(int jobNo);
  
  /**
   * <XMP>
   * �˻��� ���ڵ� ����
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * <XMP>
   * ����ó��
   * <update id="update" parameterType="JobVO">
   * </XMP>
   * @param jobVO
   * @return
   */
  public int update(JobVO jobVO);
  
  /**
   * <XMP>
   * ����ó�� �����
   * <update id="update_thumb" parameterType="JobVO">
   * </XMP>
   * @param jobVO
   * @return
   */
  public int update_thumb(JobVO jobVO);
  
  /**
   * <XMP>
   * ����ó�� ����
   * <update id="update_file" parameterType="JobVO">
   * </XMP>
   * @param jobVO
   * @return
   */
  public int update_file(JobVO jobVO);
  
  /**
   * <XMP>
   * ���� ó��
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param jobNo
   * @return
   */
  public int delete(int jobNo);
  
}
