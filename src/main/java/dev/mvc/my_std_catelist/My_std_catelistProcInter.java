package dev.mvc.my_std_catelist;

import java.util.HashMap;

public interface My_std_catelistProcInter {
  /**
   * 스터디 생성시 자동 생성.
   * stdlist_no에 해당하는 하위 카테고리 생성.
   * @param my_std_catelistVO
   * @return 적용된 컬럼 수
   */
  public int insert(HashMap<String, Integer> hashMap);
  
  /**
   * 스터디 삭제시 자동 실행
   * stdlist_no에 해당하는 하위 카테고리 삭제.
   * @param stdlist_no
   * @return 적용된 컬럼 수
   */
  public int delete(int stdlist_no);
}
