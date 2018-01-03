package dev.mvc.memsearch;

import org.springframework.stereotype.Repository;

public interface MemsearchDAOInter {
  //<select id="search" parameterType="int" resultType="MemsearchVO">
  /**
   * <xmp>
   * <select id="search" parameterType="int" resultType="MemsearchVO">
   * 회원 번호로 아이디, 이름 조회
   * </xmp>
   * @param memberno
   * @return MemsearchVO
   */
  public MemsearchVO search(int memberno);
  
  //<select id="exist_memid" parameterType="String" resultType="int">
  /**
   * <xmp>
   * <select id="exist_memid" parameterType="String" resultType="int">
   * 회원 아이디로 존재 유무 판단.
   * Ex) 아이디 중복 여부 검사 or ID 존재 유무
   * </xmp>
   * @param memid(회원 ID)
   * @return int(존재유무)
   */
  public int exist_memid(String memid);
  
  //<select id="search_memberno" parameterType="String" resultType="int">
  /**
   * <xmp>
   * 회원 ID로 회원의 번호를 조회
   * </xmp>
   * @param memid
   * @return
   */
  public int search_memberno(String memid);
}
