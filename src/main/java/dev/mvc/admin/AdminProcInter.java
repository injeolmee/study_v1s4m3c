package dev.mvc.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.mvc.member.MemberVO;

public interface AdminProcInter {
  
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
   * Ű�� �����մϴ�. ��) ABC + ����ð�: ABC1234567890123
   * @return
   */
  public String key();
  

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
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @param search �˻� �ɼ�
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String word, String search);
  
  
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
   * @param admid
   * @param admpasswd
   * @return
   */
  public int admin_login(String admid, String admpasswd);
  
  
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
