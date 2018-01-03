package dev.mvc.category;

import java.util.HashMap;
import java.util.List;

public interface CategoryDAOInter {  
  /**
   * <xmp>
   * ī�װ� ���
   * <insert id="cate_create" parameterType="CategoryVO"> 
   * </xmp>
   * @param categoryVO
   * @return
   */
  public int cate_create(CategoryVO categoryVO);  
  
  
  /**
   * <xmp>
   * ī�װ� ���
   *  <select id="cate_list" resultType="CategoryVO" parameterType="int">
   * </xmp>
   * @return 
   */
  public List<CategoryVO> cate_list(int grpno);  
  
  
  /**
   * ī�ױ׷� ī���� ������ ����Ʈ
   * <select id="grp_cate_list" resultType="Categrp_CategoryVO">
   * @return
   */
  public List<Categrp_CategoryVO> grp_cate_list();
  
  
  /**
   * <xmp>
   * ī�װ� ��ȸ
   * <select id="cate_read" resultType="CategoryVO" parameterType="HashMap">
   * </xmp>
   * @param cateno ��ȸ�� ���ڵ� ��ȣ
   * @return ��ȸ�� ��ü
   */
  public CategoryVO cate_read(HashMap hashMap);    
  
  
  /**
   * <xmp>
   * ī�װ� ����
   * <update id ="cate_update" parameterType="CategoryVO">
   * </xmp>
   * @param CategoryVO
   * @return ���� ���ڵ� ����
   */
  public int cate_update(CategoryVO categoryVO);

  
  /**
   * <xmp>
   * ī�װ� ����
   * <delete id="cate_delete" parameterType= "HashMap">
   * </xmp>
   * @param cateno
   * @return ���� ���ڵ� ����
   */
  public int cate_delete(HashMap hashMap);
    
  
  /**
   * <xmp>
   * ī�װ� ��� �켱���� ����
   * <update id ="cateseqno_up" parameterType= "HashMap">
   * </xmp>
   * @return
   */
  public int cateseqno_up(HashMap hashMap);
    
  
  /**
   * <xmp>
   * ī�װ� ��� �켱���� ����
   *<update id ="cateseqno_down" parameterType= "HashMap">
   * </xmp>
   * @return
   */
  public int cateseqno_down(HashMap hashMap);
  
  

}
