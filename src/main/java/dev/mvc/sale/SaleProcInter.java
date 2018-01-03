package dev.mvc.sale;

import java.util.HashMap;
import java.util.List;

import dev.mvc.shared.SharedVO;

public interface SaleProcInter {
  
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
   * �Խñ� ���
   * <select id="list" resultType="SaleVO">
   * </XMP>
   * @return
   */
  public List <SaleVO> list();
  
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
   * �Խ��� ��� Grid��
   * </XMP>
   * @return
   */
  public List<SaleVO> list_grid(HashMap hashMap);
  
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
   * �н����� �˻�
   * <select id="passwd_check" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int passwd_check (SaleVO saleVO);
  
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
   * @param saleVO
   * @return
   */
  public int member_check (SaleVO saleVO);
  
}
