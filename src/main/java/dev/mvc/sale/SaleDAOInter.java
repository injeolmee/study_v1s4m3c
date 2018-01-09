package dev.mvc.sale;

import java.util.HashMap;
import java.util.List;

public interface SaleDAOInter {

  /**
   * <XMP>
   * �Խñ� ���
   * <insert id="create" parameterType="SaleVO">
   * </XMP>
   * @param saleVO
   * @return
   */
  public int create (SaleVO saleVO);
  
  /**
   * <XMP>
   * �˻� + ��� + ����¡
   * <select id="list" resultType="SaleVO" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public List <SaleVO> list_search (HashMap hashMap);
  
  /**
   * <XMP>
   * �˻� ���ڵ� ���� ����
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int search_count (HashMap hashMap);

  /**
   * <XMP>
   * �Խñ� ��ȸ
   * <select id="read" resultType="SaleVO" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public SaleVO read (int saleno);

  /**
   * <XMP>
   * �Խñ� ������ ��ȸ
   * <select id="read_pre" resultType="int" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public int read_pre (int saleno);
  
  /**
   * <XMP>
   * �Խñ� ������ ��ȸ
   * <select id="read_post" resultType="int" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public int read_post (int saleno);
  
  /**
   * <XMP>
   * ��ȸ�� ���
   * <update id="increaseCnt" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public int increaseCnt (int saleno);
  
  /**
   * <XMP>
   * �Խñ� ����
   * <update id="update" parameterType="SaleVO">
   * </XMP>
   * @param saleVO
   * @return
   */
  public int update(SaleVO saleVO);
  
  /**
   * <XMP>
   * �Խñ� ����
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public int delete(int saleno);
  
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
   * <insert id="create_admin" parameterType="SaleVO">
   * </XMP>
   * @param saleVO
   * @return int
   */
  public int create_admin(SaleVO saleVO);





}
