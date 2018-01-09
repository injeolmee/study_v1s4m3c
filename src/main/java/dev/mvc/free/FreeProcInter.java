package dev.mvc.free;

import java.util.HashMap;
import java.util.List;

public interface FreeProcInter {

  /**
   * <XMP>
   * �Խñ� ���
   * <insert id="create" parameterType="FreeVO">
   * </XMP>
   * @param freeVO
   * @return int
   */
  public int create(FreeVO freeVO); 
  
  /**
   * <XMP>
   * �Խ��� ��� + �˻� + ����¡
   * <select id="list" resultType="FreeVO">
   * </XMP>
   * @return list
   */
  public List <FreeVO> list();
  
  /**
   * <XMP>
   * �Խñ� ��ȸ
   * <select id="read" resultType="FreeVO" parameterType="int" >
   * </XMP>
   * @param freeno
   * @return freeVO
   */
  public FreeVO read (int freeno);
  
  /**
   * <XMP>
   * �Խñ� ����
   * <update id="update" parameterType="FreeVO">
   * </XMP>
   * @param freeVO
   * @return
   */
  public int update(FreeVO freeVO);
    
  /**
   * <XMP>
   * ��ȸ�� ����
   *  <update id="increaseCnt" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public int increaseCnt (int freeno);
  
  /**
   * <XMP>
   * �Խñ� ����
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public int delete (int freeno);
  
  /**
   * <XMP>
   * �˻� ����Ʈ ���
   * <select id="serach" resultType="FreeVO" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public List <FreeVO> list_search(HashMap hashMap);
  
  /**
   * �˻��� ���ڵ� ����
   * <select id="search_count" resultType="int" parameterType="BlogVO">
   * @return
   */
  public int search_count(HashMap hashMap);  
   
  /**
   * <XMP>
   * ����¡ ó��
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����]  
   * </XMP>
   * @param nowPage ����������
   * @param word �˻���
   * @param serach �˻��� Value Option
   * @return ����¡ ���� ���ڿ�
   */
  public String paging(int nowPage, int search_count, String word, String serach); 
  
  /**
   * <XMP>
   * ��õ�� Max �� ��� ����Ʈ
   *  <select id="list_like" resultType="FreeVO" >
   *  <XMP>
   * @return
   */
  public List <FreeVO> list_like(HashMap<String, Object> hashMap2);
  
  /**
   * <XMP>
   * ��ȸ�� Max �� ��� ����Ʈ
   * <select id="list_cnt" resultType="FreeVO" parameterType="HashMap">
   * </XMP>
   * @param hashMap3
   * @return
   */
  public List <FreeVO> list_cnt(HashMap<String, Object> hashMap3);
  
  /**
   * <XMP>
   * ������ ��ȸ
   * <select id="read_pre" resultType="int" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public int read_pre (int freeno);
  
  /**
   * <XMP>
   * ������ ��ȸ
   * <select id="read_post" resultType="int" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public int read_post (int freeno);
  
  /**
   * <XMP>
   * ���̵� üũ
   * <select id="member_check" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int member_check (FreeVO freeVO);
  
  /**
   * <XMP>
   * ���ƿ䰡 0���� 1�� ����
   * <update id="increaseLike" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public int increaseLike(int freeno);
  
  /**
   * <XMP>
   * ���ƿ䰡 1���� 0���� ����
   * <update id="decreaseLike" parameterType="int">
   * </XMP>
   * @param freeno
   * @return
   */
  public int decreaseLike(int freeno);
  
  /**
   * <XMP>
   * ������ �Խñ� ���
   * <insert id="create_admin" parameterType="FreeVO">
   * </XMP>
   * @param freeVO
   * @return
   */
  public int create_admin(FreeVO freeVO);
}
