package dev.mvc.my_std_catelist;

import java.util.HashMap;

public interface My_std_catelistDAOInter {
  /*<insert id="insert" parameterType="int">
    INSERT INTO "STUDY"."MY_STD_CATELIST" (MYLISTNO, STDLIST_NO, CATENO)
    VALUES ((SELECT NVL(MAX(MYLISTNO), 0) + 1 as MYLISTNO FROM "STUDY"."MY_STD_CATELIST"), #{stdlist_no}, #{cateno})
  </insert>
  
  <delete id="delete" parameterType="int">
    DELETE FROM "STUDY"."MY_STD_CATELIST"
    WHERE STDLIST_NO=#{stdlist_no}
  </delete>*/
  
  /**
   * ���͵� ������ �ڵ� ����.
   * stdlist_no�� �ش��ϴ� ���� ī�װ� ����.
   * @param my_std_catelistVO
   * @return ����� �÷� ��
   */
  public int insert(HashMap<String, Integer> hashMap);
  
  
  /**
   * ���͵� ������ �ڵ� ����
   * stdlist_no�� �ش��ϴ� ���� ī�װ� ����.
   * @param stdlist_no
   * @return ����� �÷� ��
   */
  public int delete(int stdlist_no);
}
