package dev.mvc.my_std_catelist;

import java.util.HashMap;

public interface My_std_catelistProcInter {
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
