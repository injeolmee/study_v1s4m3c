package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberDAOInter {
    
  /**
   * <xmp>
   * 회원 등록
   * <insert id="memberjoin" parameterType="MemberVO">
   * </xmp>
   * @param memberVO
   * @return 등록된 레코드 수 
   */
  public int memberjoin(MemberVO memberVO);
  
  
  /**
   * <xmp>
   * 회원 이메일 중복 검사
   * <select id="check_id" resultType="int" parameterType="String">
   * </xmp>
   * @param memid
   * @return 중복된 이메일  0=중복 아님, 1=중복
   */
  public int check_id(String memid);
  
  
  /**
   * <xmp>
   * 회원 이메일 중복 검사
   * <select id="check_email" resultType="int" parameterType="String">
   * </xmp>
   * @param mememail
   * @return 중복된 이메일  0=중복 아님, 1=중복
   */
  public int check_email(String mememail);
  
  
  /**
   * 회원 전체 목록
   * <select id="mem_list" resultType="MemberVO" parameterType="HashMap">
   * @return
   */
  public List<MemberVO> mem_list(HashMap hashMap);
  
  
  /**
   * 검색된 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * @return
   */
  public int search_count(HashMap hashMap);  
  
  
  /**
   * 회원 정보 회원번호로 조회
   *   <select id="mem_read" resultType="MemberVO" parameterType="int">
   * @param memberno
   * @return
   */
  public MemberVO mem_read(int memberno);
  
  
  /**
   * 회원 정보 아이디로 조회
   * <select id="mem_read_id" resultType="MemberVO" parameterType="String"> 
   * @param memid
   * @return
   */
  public MemberVO mem_read_id(String memid);
  
  
  /**
   * 회원 정보 변경
   * <update id="mem_update" parameterType="MemberVO">
   * @param memberVO
   * @return
   */
  public int mem_update(MemberVO memberVO);
  
  
  /**
   * 로그인 
   * <select id="login"  resultType="int"  parameterType="Map">
   * @param map
   * @return
   */
  public int login(Map map);
  
  
  /**
   * 아이디 찾기
   * <select id="find_memid" resultType="String" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public String find_memid(HashMap hashMap);
  
  
  /**
   * 비밀번호 찾기
   * <select id="find_mempasswd" resultType="String" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public String find_mempasswd(HashMap hashMap);
  
  
  /**
   * 비밀번호 찾기 새 비밀번호 변경
   * <update id="mempasswd_change" parameterType=""MemberVO"">
   * @param memberVO
   * @return
   */
  public int mempasswd_change(MemberVO memberVO);
  

}
