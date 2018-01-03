package dev.mvc.category;

import java.util.HashMap;
import java.util.List;

public interface CategoryDAOInter {  
  /**
   * <xmp>
   * 카테고리 등록
   * <insert id="cate_create" parameterType="CategoryVO"> 
   * </xmp>
   * @param categoryVO
   * @return
   */
  public int cate_create(CategoryVO categoryVO);  
  
  
  /**
   * <xmp>
   * 카테고리 목록
   *  <select id="cate_list" resultType="CategoryVO" parameterType="int">
   * </xmp>
   * @return 
   */
  public List<CategoryVO> cate_list(int grpno);  
  
  
  /**
   * 카테그룹 카데고리 합쳐진 리스트
   * <select id="grp_cate_list" resultType="Categrp_CategoryVO">
   * @return
   */
  public List<Categrp_CategoryVO> grp_cate_list();
  
  
  /**
   * <xmp>
   * 카테고리 조회
   * <select id="cate_read" resultType="CategoryVO" parameterType="HashMap">
   * </xmp>
   * @param cateno 조회할 레코드 번호
   * @return 조회된 객체
   */
  public CategoryVO cate_read(HashMap hashMap);    
  
  
  /**
   * <xmp>
   * 카테고리 수정
   * <update id ="cate_update" parameterType="CategoryVO">
   * </xmp>
   * @param CategoryVO
   * @return 수정 레코드 갯수
   */
  public int cate_update(CategoryVO categoryVO);

  
  /**
   * <xmp>
   * 카테고리 삭제
   * <delete id="cate_delete" parameterType= "HashMap">
   * </xmp>
   * @param cateno
   * @return 삭제 레코드 갯수
   */
  public int cate_delete(HashMap hashMap);
    
  
  /**
   * <xmp>
   * 카테고리 출력 우선순위 높임
   * <update id ="cateseqno_up" parameterType= "HashMap">
   * </xmp>
   * @return
   */
  public int cateseqno_up(HashMap hashMap);
    
  
  /**
   * <xmp>
   * 카테고리 출력 우선순위 낮춤
   *<update id ="cateseqno_down" parameterType= "HashMap">
   * </xmp>
   * @return
   */
  public int cateseqno_down(HashMap hashMap);
  
  

}
