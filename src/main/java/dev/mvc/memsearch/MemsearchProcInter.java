package dev.mvc.memsearch;

public interface MemsearchProcInter {
  /**
   * <select id="search" parameterType="int" resultType="MemsearchVO">
   * ȸ�� ��ȣ�� ���̵�, �̸� ��ȸ
   * @param memberno
   * @return MemsearchVO
   */
  public MemsearchVO search(int memberno);
  
  /**
   * <select id="reverse_search" parameterType="String" resultType="int">
   * ȸ�� ���̵�� ȸ�� ��ȣ �˻�.
   * @param memid
   * @return int, ȸ����ȣ 
   */
  public int exist_memid(String memid);
  
  /**
   * <xmp>
   * ȸ�� ID�� ȸ���� ��ȣ�� ��ȸ
   * </xmp>
   * @param memid
   * @return
   */
  public int search_memberno(String memid);
}
