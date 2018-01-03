package dev.mvc.memsearch;

import org.springframework.stereotype.Repository;

public interface MemsearchDAOInter {
  //<select id="search" parameterType="int" resultType="MemsearchVO">
  /**
   * <xmp>
   * <select id="search" parameterType="int" resultType="MemsearchVO">
   * ȸ�� ��ȣ�� ���̵�, �̸� ��ȸ
   * </xmp>
   * @param memberno
   * @return MemsearchVO
   */
  public MemsearchVO search(int memberno);
  
  //<select id="exist_memid" parameterType="String" resultType="int">
  /**
   * <xmp>
   * <select id="exist_memid" parameterType="String" resultType="int">
   * ȸ�� ���̵�� ���� ���� �Ǵ�.
   * Ex) ���̵� �ߺ� ���� �˻� or ID ���� ����
   * </xmp>
   * @param memid(ȸ�� ID)
   * @return int(��������)
   */
  public int exist_memid(String memid);
  
  //<select id="search_memberno" parameterType="String" resultType="int">
  /**
   * <xmp>
   * ȸ�� ID�� ȸ���� ��ȣ�� ��ȸ
   * </xmp>
   * @param memid
   * @return
   */
  public int search_memberno(String memid);
}
