package dev.mvc.memsearch;

public interface MemsearchProcInter {
  /**
   * <select id="search" parameterType="int" resultType="MemsearchVO">
   * 회원 번호로 아이디, 이름 조회
   * @param memberno
   * @return MemsearchVO
   */
  public MemsearchVO search(int memberno);
  
  /**
   * <select id="reverse_search" parameterType="String" resultType="int">
   * 회원 아이디로 회원 번호 검색.
   * @param memid
   * @return int, 회원번호 
   */
  public int exist_memid(String memid);
  
  /**
   * <xmp>
   * 회원 ID로 회원의 번호를 조회
   * </xmp>
   * @param memid
   * @return
   */
  public int search_memberno(String memid);
}
