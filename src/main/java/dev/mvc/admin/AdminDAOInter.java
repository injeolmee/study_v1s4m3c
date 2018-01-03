package dev.mvc.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.mvc.member.MemberVO;

public interface AdminDAOInter {
  
  
  /**
   * ������ �˻� (������ ù��Ͻ� �����ͷ� ����ϱ� ����)
   * <select id="check_master"  resultType="int" parameterType="String">
   * @param memauth
   * @return
   */
  public int check_master(String memauth);
  
  
  /**
   * ������ ���
   * <insert id="admin_create" parameterType="AdminVO">
   * @param adminVO
   * @return
   */
  public int admin_create(AdminVO adminVO);
  
  
  /**
   * ������ ���̵� �ߺ��˻�
   * <select id="check_admid" resultType="int" parameterType="String">
   * @param admid
   * @return
   */
  public int check_admid(String admid);
  
  
  /**
   * ������ �̸��� �ߺ��˻�
   * <select id="check_admemail" resultType="int" parameterType="String">
   * @param admemail
   * @return
   */
  public int check_admemail(String admemail);
    
  
  /**
   * ȸ�� ��ü ���
   * <select id="admin_list" resultType="AdminVO" parameterType="HashMap">
   * @return
   */
  public List<AdminVO> admin_list(HashMap hashMap);
  
  
  /**
   * �˻��� ���ڵ� ����
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * @return
   */
  public int search_count(HashMap hashMap);  
  
  
  /**
   * ������ ���� �����ڹ�ȣ�� ��ȸ
   * <select id="admin_read" resultType="AdminVO" parameterType="int">
   * @param adminno
   * @return
   */
  public AdminVO admin_read(int adminno);
  
  
  /**
   * ������ ���� �����ھ��̵�� ��ȸ
   * <select id="admin_read_id" resultType="AdminVO" parameterType="String">
   * @param adminno
   * @return
   */
  public AdminVO admin_read_id(String admid);
  
  
  /**
   * ������ ���� ����
   * <update id="admin_update" parameterType="AdminVO">
   * @param adminVO
   * @return
   */
  public int admin_update(AdminVO adminVO);

  
  /**
   * ������ �α���
   * <select id="admin_login"  resultType="int" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public int admin_login(HashMap hashMap);
  
  
  /**
   * ���̵� ã��
   * <select id="find_admid" resultType="String" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public String find_admid(HashMap hashMap);
  
  
  /**
   * ��й�ȣ ã��
   * <select id="find_admpasswd" resultType="String" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public String find_admpasswd(HashMap hashMap);
  
  
  /**
   * ��й�ȣ ã�� �� ��й�ȣ ����
   * <update id="admpasswd_change" parameterType="AdminVO">
   * @param AdminVO
   * @return
   */
  public int admpasswd_change(AdminVO adminVO);
  
  
}
