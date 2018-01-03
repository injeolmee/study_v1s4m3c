package dev.mvc.contest;

import java.util.HashMap;
import java.util.List;

public interface ContestDAOInter {
  
  /**
   * <XMP>
   * ������ ���� ���
   * <insert id="create" parameterType="ContestVO">
   * </XMP>
   * @param contestVO
   * @return
   */
  public int create(ContestVO contestVO);
  
  /**
   * <XMP>
   * ������ ��ü ���
   * <select id="list_all_contest" resultType="ContestVO">
   * </XMP>
   * @return
   */
  public List<ContestVO> list_all_contest(HashMap hashMap);
  
  /**
   * <XMP>
   * ������ ��ȸ���� ���� ���
   * <select id="list_by_count" resultType="ContestVO">
   * </XMP>
   * @return
   */
  public List<ContestVO> list_by_count();
  
  /**
   * <XMP>
   * ������ ��õ���� ���� ���
   * <select id="list_by_good" resultType="ContestVO">
   * </XMP>
   * @return
   */
  public List<ContestVO> list_by_good();
  
  /**
   * <XMP>
   * �Խñ��� ������ ��ȣ
   * <select id="max" resultType="int">
   * </XMP>
   * @return
   */
  public int max();
  
  /**
   * <XMP>
   * �Խñ��� ó�� ��ȣ
   * <select id="min" resultType="int">
   * </XMP>
   * @return
   */
  public int min();
  
  /**
   * <XMP>
   * ������ �� ��ȸ������
   * <update id="increaseCnt" parameterType="int">
   * </XMP>
   * @param conNo
   * @return
   */
  public int increaseCnt(int conNo);
  
  /**
   * <XMP>
   * ���ƿ� �� ����
   * </XMP>
   * @param conNo
   * @return
   */
  public int goodcnt_up(int conNo);
  
  /**
   * <XMP>
   * ���ƿ� �� ����
   * </XMP>
   * @param conNo
   * @return
   */
  public int goodcnt_down(int conNo);
  
  /**
   * <XMP>
   * category�� �˻��� ���ڵ� ����
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </XMP>
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * <select id="read" resultType="ContestVO" parameterType="int">
   * @param conNo �� ��ȣ
   * @return
   */
  public ContestVO read(int conNo);
  
  /**
   * <xmp>
   * ������ �� ��ü ����
   * <select id="total_count" resultType="int">
   * </xmp>
   * @return
   */
  public int total_count();
  
  /**
   * <XMP>
   * �������� ������ �� ����
   * <select id="day_count" resultType="int">
   * </XMP>
   * @return
   */
  public int day_count();
  
  /**
   * ����ó��
   * <update id="update" parameterType="ContestVO"> 
   * @param ContestVO
   * @return
   */
  public int update(ContestVO contestVO);
  
  /**
   * <XMP>
   * ����� ����
   * <update id="update_thumb" parameterType="ContestVO">
   * </XMP>
   * @param contestVO
   * @return
   */
  public int update_thumb(ContestVO contestVO);
  
  /**
   * <XMP>
   * �̹��� ����
   * <update id="update_img" parameterType="ContestVO">
   * </XMP>
   * @param contestVO
   * @return
   */
  public int update_img(ContestVO contestVO);
  
  /**
   * <XMP>
   * �Ϲ� ���� ����
   * <update id="update_file" parameterType="ContestVO">
   * </XMP>
   * @param contestVO
   * @return
   */
  public int update_file(ContestVO contestVO);
  
  /**
   * ���� ó��
   * <delete id="delete" parameterType="int">
   * @param conNo
   * @return
   */
  public int delete(int conNo);
  
  
  
}
