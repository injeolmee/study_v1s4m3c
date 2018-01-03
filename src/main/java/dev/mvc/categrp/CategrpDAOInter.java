package dev.mvc.categrp;

import java.util.List;

public interface CategrpDAOInter {  
  /**
   * <xmp>
   * ī�ױ׷� ���
   * <insert id="grp_create" parameterType="CategrpVO"> 
   * </xmp>
   * @param categrpVO
   * @return
   */
  public int grp_create(CategrpVO categrpVO);  
  
  
  /**
   * <xmp>
   * ī�ױ׷� ���
   * <select id="grp_list" resultType="CategrpVO">
   * </xmp>
   * @return 
   */
  public List<CategrpVO> grp_list();  
  
  
  /**
   * <xmp>
   * ī�ױ׷� ��ȸ
   * <select id="grp_read" resultType="CategrpVO" parameterType="int">
   * </xmp>
   * @param grpno ��ȸ�� ���ڵ� ��ȣ
   * @return ��ȸ�� ��ü
   */
  public CategrpVO grp_read(int grpno);    
  
  
  /**
   * <xmp>
   * ī�ױ׷� ����
   * <update id ="grp_update" parameterType= "CategrpVO">
   * </xmp>
   * @param CategrpVO
   * @return ���� ���ڵ� ����
   */
  public int grp_update(CategrpVO categrpVO);

  
  /**
   * <xmp>
   * ī�ױ׷� ����
   * <delete id="grp_delete" parameterType= "int">
   * </xmp>
   * @param grpno
   * @return ���� ���ڵ� ����
   */
  public int grp_delete(int grpno);
    
  
  /**
   * <xmp>
   * ī�ױ׷� ��� �켱���� ����
   * <update id ="grpseqno_up" parameterType= "int">
   * </xmp>
   * @return
   */
  public int grpseqno_up(int grpno);
    
  
  /**
   * <xmp>
   * ī�ױ׷� ��� �켱���� ����
   * <update id ="grpseqno_down" parameterType= "int">
   * </xmp>
   * @return
   */
  public int grpseqno_down(int grpno);
  
  
  /**
   * <xmp>
   * ī�ױ׷� ��ȣ �ߺ� �˻�
   * <select id="grpno_check" resultType="int"  parameterType = "int">
   * </xmp>
   * @param grpno
   * @return �ߺ��� ī�װ� ��ȣ  0=�ߺ� �ƴ�, 1=�ߺ�
   */
  public int grpno_check(int grpno);
  
  

}
