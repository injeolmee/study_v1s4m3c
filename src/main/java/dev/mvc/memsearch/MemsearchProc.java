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
   * 회원 번호로 ID와 Name을 조회한다.
   * return은 MemsearchVO형으로 반환.
   */
  @Override
  public MemsearchVO search(int memberno) {
    return memsearchdao.search(memberno);
  }

  /**
   * 회원 아이디로 존재유무를 판단.
   * 존재하면 1, 없으면 0
   */
  @Override
  public int exist_memid(String memid) { 
    return memsearchdao.exist_memid(memid);
  }

  /**
   * 회원 아이디로 회원 번호를 조회
   * 수행 과정에서 먼저 회원 존재 유무부터 검사
   * 
   * 존재시 -> memberno 조회
   * 없을시 -> 0을 리턴
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
