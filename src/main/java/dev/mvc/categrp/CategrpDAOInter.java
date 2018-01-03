package dev.mvc.categrp;

import java.util.List;

public interface CategrpDAOInter {  
  /**
   * <xmp>
   * 카테그룹 등록
   * <insert id="grp_create" parameterType="CategrpVO"> 
   * </xmp>
   * @param categrpVO
   * @return
   */
  public int grp_create(CategrpVO categrpVO);  
  
  
  /**
   * <xmp>
   * 카테그룹 목록
   * <select id="grp_list" resultType="CategrpVO">
   * </xmp>
   * @return 
   */
  public List<CategrpVO> grp_list();  
  
  
  /**
   * <xmp>
   * 카테그룹 조회
   * <select id="grp_read" resultType="CategrpVO" parameterType="int">
   * </xmp>
   * @param grpno 조회할 레코드 번호
   * @return 조회된 객체
   */
  public CategrpVO grp_read(int grpno);    
  
  
  /**
   * <xmp>
   * 카테그룹 수정
   * <update id ="grp_update" parameterType= "CategrpVO">
   * </xmp>
   * @param CategrpVO
   * @return 수정 레코드 갯수
   */
  public int grp_update(CategrpVO categrpVO);

  
  /**
   * <xmp>
   * 카테그룹 삭제
   * <delete id="grp_delete" parameterType= "int">
   * </xmp>
   * @param grpno
   * @return 삭제 레코드 갯수
   */
  public int grp_delete(int grpno);
    
  
  /**
   * <xmp>
   * 카테그룹 출력 우선순위 높임
   * <update id ="grpseqno_up" parameterType= "int">
   * </xmp>
   * @return
   */
  public int grpseqno_up(int grpno);
    
  
  /**
   * <xmp>
   * 카테그룹 출력 우선순위 낮춤
   * <update id ="grpseqno_down" parameterType= "int">
   * </xmp>
   * @return
   */
  public int grpseqno_down(int grpno);
  
  
  /**
   * <xmp>
   * 카테그룹 번호 중복 검사
   * <select id="grpno_check" resultType="int"  parameterType = "int">
   * </xmp>
   * @param grpno
   * @return 중복된 카테고리 번호  0=중복 아님, 1=중복
   */
  public int grpno_check(int grpno);
  
  

}
