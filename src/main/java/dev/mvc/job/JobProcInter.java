package dev.mvc.job;

import java.util.HashMap;
import java.util.List;

public interface JobProcInter {

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
   * @throws Exception 
   */
  public List<JobVO> list_all_jobinfo(HashMap hashMap) throws Exception;
  
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
   * @throws Exception 
   */
  public JobVO read(int jobNo) throws Exception;
  
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
   * ���� ��
   * </XMP>
   * @param jobNo
   * @return
   */
  public JobVO update(int jobNo);
  
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
   * ����� ���� ��
   * </XMP>
   * @param jobNo
   * @return
   */
  public JobVO update_thumb(int jobNo);
  
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
   * ���� ���� ��
   * </XMP>
   * @param jobNo
   * @return
   */
  public JobVO update_file(int jobNo);
  
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
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String jobWord);
  
}
