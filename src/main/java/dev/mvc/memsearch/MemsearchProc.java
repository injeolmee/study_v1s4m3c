package dev.mvc.memsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.memsearch.MemsearchProc")
public class MemsearchProc implements MemsearchProcInter{
  
  @Autowired
  @Qualifier("dev.mvc.memsearch.MemsearchDAO")
  private MemsearchDAOInter memsearchdao;

  /**
   * ȸ�� ��ȣ�� ID�� Name�� ��ȸ�Ѵ�.
   * return�� MemsearchVO������ ��ȯ.
   */
  @Override
  public MemsearchVO search(int memberno) {
    return memsearchdao.search(memberno);
  }

  /**
   * ȸ�� ���̵�� ���������� �Ǵ�.
   * �����ϸ� 1, ������ 0
   */
  @Override
  public int exist_memid(String memid) { 
    return memsearchdao.exist_memid(memid);
  }

  /**
   * ȸ�� ���̵�� ȸ�� ��ȣ�� ��ȸ
   * ���� �������� ���� ȸ�� ���� �������� �˻�
   * 
   * ����� -> memberno ��ȸ
   * ������ -> 0�� ����
   */
  @Override
  public int search_memberno(String memid) {
    
    int exist_memid=memsearchdao.exist_memid(memid);
    
    if(exist_memid==1){
      return memsearchdao.search_memberno(memid);
    }else{
      return 0;
    }
  }
 
}
