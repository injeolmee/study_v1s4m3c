package dev.mvc.contest;

import java.util.HashMap;
import java.util.List;

public interface ContestProcInter {

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
   * @throws Exception 
   */
  public List<ContestVO> list_all_contest(HashMap hashMap) throws Exception;
  
  /**
   * <XMP>
   * ������ ��ü ���
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
   * �Խñ��� ó�� ��ȣ
   * <select id="min" resultType="int">
   * </XMP>
   * @return
   */
  public int min();
  
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
   * �Ѱ��� ���ڵ� ��ȸ
   * <select id="read" resultType="ContestVO" parameterType="int">
   * @param conNo �� ��ȣ
   * @return
   * @throws Exception 
   */
  public ContestVO read(int conNo) throws Exception;
  
  /**
   * ���� ��
   * @param conNo
   * @return
   */
  public ContestVO update(int conNo);
  
  /**
   * ����ó��
   * <update id="update" parameterType="ContestVO"> 
   * @param ContestVO
   * @return
   */
  public int update(ContestVO contestVO);
  
  /**
   * <XMP>
   * ����� ���� ��
   * <update id="update_thumb" parameterType="ContestVO">
   * </XMP>
   * @param conNo
   * @return
   */
  public ContestVO update_thumb(int conNo);
  
  /**
   * <XMP>
   * ����� ���� ����
   * <update id="update_thumb" parameterType="ContestVO">
   * </XMP>
   * @param contestVO
   * @return
   */
  public int update_thumb(ContestVO contestVO);
  
  
  /**
   * <XMP>
   * �̹��� ���� ��
   * <update id="update_img" parameterType="ContestVO">
   * </XMP>
   * @param conNo
   * @return
   */
  public ContestVO update_img(int conNo);
  
  /**
   * <XMP>
   * �̹��� ���� ó��
   * <update id="update_img" parameterType="ContestVO">
   * </XMP>
   * @param contestVO
   * @return
   */
  public int update_img(ContestVO contestVO);
  
  /**
   * <XMP>
   * �Ϲ� ���� ���� ��
   * <update id="update_file" parameterType="ContestVO">
   * </XMP>
   * @param conNo
   * @return
   */
  public ContestVO update_file(int conNo);
  
  /**
   * <XMP>
   * �Ϲ� ���� ���� ó��
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
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String conWord);
  
}
