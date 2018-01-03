package dev.mvc.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.mvc.member.MemberVO;

public interface AdminDAOInter {
  
  
  /**
   * 마스터 검사 (관리자 첫등록시 마스터로 등록하기 위해)
   * <select id="check_master"  resultType="int" parameterType="String">
   * @param memauth
   * @return
   */
  public int check_master(String memauth);
  
  
  /**
   * 관리자 등록
   * <insert id="admin_create" parameterType="AdminVO">
   * @param adminVO
   * @return
   */
  public int admin_create(AdminVO adminVO);
  
  
  /**
   * 관리자 아이디 중복검사
   * <select id="check_admid" resultType="int" parameterType="String">
   * @param admid
   * @return
   */
  public int check_admid(String admid);
  
  
  /**
   * 관리자 이메일 중복검사
   * <select id="check_admemail" resultType="int" parameterType="String">
   * @param admemail
   * @return
   */
  public int check_admemail(String admemail);
    
  
  /**
   * 회원 전체 목록
   * <select id="admin_list" resultType="AdminVO" parameterType="HashMap">
   * @return
   */
  public List<AdminVO> admin_list(HashMap hashMap);
  
  
  /**
   * 검색된 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * @return
   */
  public int search_count(HashMap hashMap);  
  
  
  /**
   * 관리자 정보 관리자번호로 조회
   * <select id="admin_read" resultType="AdminVO" parameterType="int">
   * @param adminno
   * @return
   */
  public AdminVO admin_read(int adminno);
  
  
  /**
   * 관리자 정보 관리자아이디로 조회
   * <select id="admin_read_id" resultType="AdminVO" parameterType="String">
   * @param adminno
   * @return
   */
  public AdminVO admin_read_id(String admid);
  
  
  /**
   * 관리자 정보 변경
   * <update id="admin_update" parameterType="AdminVO">
   * @param adminVO
   * @return
   */
  public int admin_update(AdminVO adminVO);

  
  /**
   * 관리자 로그인
   * <select id="admin_login"  resultType="int" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public int admin_login(HashMap hashMap);
  
  
  /**
   * 아이디 찾기
   * <select id="find_admid" resultType="String" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public String find_admid(HashMap hashMap);
  
  
  /**
   * 비밀번호 찾기
   * <select id="find_admpasswd" resultType="String" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public String find_admpasswd(HashMap hashMap);
  
  
  /**
   * 비밀번호 찾기 새 비밀번호 변경
   * <update id="admpasswd_change" parameterType="AdminVO">
   * @param AdminVO
   * @return
   */
  public int admpasswd_change(AdminVO adminVO);
  
  
}
