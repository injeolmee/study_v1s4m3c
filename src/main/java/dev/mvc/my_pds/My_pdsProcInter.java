package dev.mvc.my_pds;

import java.util.HashMap;
import java.util.List;

import dev.mvc.my_std_catelist.My_std_catelistVO;

public interface My_pdsProcInter {

  /**
   * <select id="insert" parameterType="My_pdsVO">
   * �� ���͵� �� ���
   * @param my_pdsVO
   * @return ���ڵ� ����
   */
  public int insert(My_pdsVO my_pdsVO);
  
  /**
   * <select id="read" parameterType="HashMap" resultType="My_pdsVO">
   * �� ���͵� �� ��ȸ
   * @param hashMap
   * @return List<My_pdsVO>
   */
  public List<My_pdsVO> list(HashMap<String, Object> hashMap);
  
  /**
   * <select id="search_mylistno" parameterType="HashMap" resultType="int">
   * stdlist_no : ���͵� ��ȣ
   * cateno : ���� ī�װ� ��ȣ
   * �� ���ڸ� ���� mylistno�� ��ȸ�Ѵ�.
   * 
   * @param hashMap
   * @return mylistno
   */
  public My_std_catelistVO search_mylistno(HashMap<String, Integer> hashMap);
  
  /**
   * <select id="search_cateno/stdlist_no" parameterType="int" resultType="My_std_catelistVO">
   * mylistno�� ���� stdlist_no, cateno�� ��ȸ
   * @param mylistno
   * @return My_std_catelistVO - mylistno�� �ش��ϴ� ��
   */
  public My_std_catelistVO search_cateno_stdlist_no(int mylistno);
  
  /**
   * <select id="search_memname" parameterType="int" resultType="String">
   * ȸ����ȣ�� ���� ȸ���� �̸��� ��ȸ�Ѵ�.
   * @param memberno
   * @return ȸ���̸�
   */
  public String search_memname(int memberno);
  
  /**
   * <select id="read" parameterType="int" resultType="My_pdsVO">
   * �� ���� ��ȸ
   * @param pdsno
   * @return My_pdsVO �� ����
   */
  public My_pdsVO read(int pdsno);
  
  /**
   * <update id="update" parameterType="My_pdsVO">
   * �� ����
   * @return int (ó���� �÷� ����)
   */
  public int update(My_pdsVO my_pdsVO);
  
  /**
   * <select id="check_passwd" parameterType="String" resultType="HashMap">
   * �� �н����� ��ġ �˻�
   * @param passwd
   * @return ��ġ�ϸ� 1, �ƴϸ� 0
   */
  public int check_passwd(HashMap<String, Object> hashMap);
  
  /**
   * <delete id="delete" parameterType="int">
   * �� ����
   * @param pdsno
   * @return
   */
  public int delete(int pdsno);
  
  /**
   * <update id="inc_cnt" parameterType="int">
   * �� ��ȸ�� ����
   * @param pdsno
   * @return
   */
  public int inc_cnt(int pdsno);
  
  /**
   * <update id="inc_like" parameterType="int">
   * �� ��õ�� ����
   * @param pdsno
   * @return
   */
  public int inc_like(int pdsno);
  
  /**
   * <select id="lastest_pdsno" resultType="int">
   * ���� �ֱٿ� ��ϵ� pdsno�� �����´�.
   * @return ���� �ֱٿ� �߰��� pdsno
   */
  public int lastest_pdsno();
  
  /**
   * <delete id="del_file" parameterType="int">
   * Ư�� �Խñ��� ���ϸ� �����.
   * ex) �������� �̹� ��ϵ� ������ ������ ��.(�߰� ��� ����)
   * @param pdsno
   * @return
   */
  public int del_file(int pdsno);
  
  /**
   * <select id="search_count" parameterType="HashMap" resultType="int">
   * �˻� ��� ���� ��ȯ
   * @param hashMap
   * @return �˻� ��� ��
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * ����¡ �޼ҵ�
   * @param search_count
   * @param nowPage
   * @param cateno
   * @param stdlist_no
   * @return
   */
  public String paging(int search_count, int nowPage, int cateno, int stdlist_no);
  
}
