package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberProcInter {
  
  /**
   * <xmp>
   * 회원 등록
   * <insert id="memberjoin" parameterType="MemberVO">
   * </xmp>
   * @param memberVO
   * @return
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
   * 키를 조합합니다. 예) ABC + 현재시간: ABC1234567890123
   * @return
   */
  public String key();
  
  
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
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @param search 검색 옵션
   * @return 페이징 생성 문자열
   */ 
  public String paging(int search_count, int nowPage, String word, String search);
  
  
  
  /**
   * 회원 정보 회원번호로 조회
   *   <select id="mem_read" resultType="MemberVO" parameterType="int">
   * @param mno
   * @return
   */
  public MemberVO mem_read(int memberno);
  
  
  /**
   * 회원 정보 이메일로 조회
   * <select id="mem_read_id" resultType="MemberVO" parameterType="String"> 
   * @param id
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
   * 로그인 처리
   * <select id="login"  resultType="int"  parameterType="Map">
   * @param map
   * @return
   */
  public int login(String memid, String mempasswd);
  
  
  /**
   * 아이디 찾기
   * <select id="find_memid" resultType="String" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public String find_memid(HashMap hashMap);
  
  
  /**
   * 아이디 찾기
   * <select id="find_mempasswd" resultType="String" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public String find_mempasswd(HashMap hashMap);
  
  
  /**
   * 비밀번호 찾기 새 비밀번호 변경
   * <update id="mempasswd_change" parameterType="MemberVO">
   * @param memberVO
   * @return
   */
  public int mempasswd_change(MemberVO memberVO);
  
  

}
