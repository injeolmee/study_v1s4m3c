package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberProcInter {
  
  /**
   * <xmp>
   * ȸ�� ���
   * <insert id="memberjoin" parameterType="MemberVO">
   * </xmp>
   * @param memberVO
   * @return
   */
  public int memberjoin(MemberVO memberVO);
  
  
  /**
   * <xmp>
   * ȸ�� �̸��� �ߺ� �˻�
   * <select id="check_id" resultType="int" parameterType="String">
   * </xmp>
   * @param memid
   * @return �ߺ��� �̸���  0=�ߺ� �ƴ�, 1=�ߺ�
   */
  public int check_id(String memid);
  
  
  /**
   * <xmp>
   * ȸ�� �̸��� �ߺ� �˻�
   * <select id="check_email" resultType="int" parameterType="String">
   * </xmp>
   * @param mememail
   * @return �ߺ��� �̸���  0=�ߺ� �ƴ�, 1=�ߺ�
   */
  public int check_email(String mememail);
  
  
  /**
   * Ű�� �����մϴ�. ��) ABC + ����ð�: ABC1234567890123
   * @return
   */
  public String key();
  
  
  /**
   * ȸ�� ��ü ���
   * <select id="mem_list" resultType="MemberVO" parameterType="HashMap">
   * @return
   */
  public List<MemberVO> mem_list(HashMap hashMap);
  
  
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
   * ȸ�� ���� ȸ����ȣ�� ��ȸ
   *   <select id="mem_read" resultType="MemberVO" parameterType="int">
   * @param mno
   * @return
   */
  public MemberVO mem_read(int memberno);
  
  
  /**
   * ȸ�� ���� �̸��Ϸ� ��ȸ
   * <select id="mem_read_id" resultType="MemberVO" parameterType="String"> 
   * @param id
   * @return
   */
  public MemberVO mem_read_id(String memid);
  
  
  /**
   * ȸ�� ���� ����
   * <update id="mem_update" parameterType="MemberVO">
   * @param memberVO
   * @return
   */
  public int mem_update(MemberVO memberVO);
  
  
  /**
   * �α��� ó��
   * <select id="login"  resultType="int"  parameterType="Map">
   * @param map
   * @return
   */
  public int login(String memid, String mempasswd);
  
  
  /**
   * ���̵� ã��
   * <select id="find_memid" resultType="String" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public String find_memid(HashMap hashMap);
  
  
  /**
   * ���̵� ã��
   * <select id="find_mempasswd" resultType="String" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public String find_mempasswd(HashMap hashMap);
  
  
  /**
   * ��й�ȣ ã�� �� ��й�ȣ ����
   * <update id="mempasswd_change" parameterType="MemberVO">
   * @param memberVO
   * @return
   */
  public int mempasswd_change(MemberVO memberVO);
  
  

}
