package dev.mvc.shared;

import java.util.HashMap;
import java.util.List;

public interface SharedProcInter {
  
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
   * �Խ��� ���
   * <select id="list" resultType="SharedVO">
   * </XMP>
   * @return �Խ��� ���
   */
  public List<SharedVO> list();
  
  /**
   * <XMP>
   * �Խ��� ��� Grid��
   * </XMP>
   * @return
   */
  public List<SharedVO> list_grid(HashMap hashMap);
  
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
   * ����¡ ó�� (�⺻ �����)
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����]  
   * </XMP>
   * @param nowPage ����������
   * @param word �˻���
   * @param serach �˻��� Value Option
   * @return ����¡ ���� ���ڿ�
   */
  public String paging(int nowPage, int search_count, String word, String search); 
  
  /**
   * <XMP>
   * ����¡ ó�� (Grid��)
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����]  
   * </XMP>
   * @param nowPage ����������
   * @param word �˻���
   * @param serach �˻��� Value Option
   * @return ����¡ ���� ���ڿ�
   */
  public String paging_grid(int nowPage, int search_count, String word, String search); 
  
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
   * ��õ�� ���
   * <update id="increaseLike" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int increaseLike (int sharedno);
  
  /**
   * <XMP>
   * �н����� �˻�
   * <select id="passwd_check" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int passwd_check (SharedVO sharedVO);
  
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
   * @param sharedVO
   * @return
   */
  public int member_check (SharedVO sharedVO);
  
  

}
