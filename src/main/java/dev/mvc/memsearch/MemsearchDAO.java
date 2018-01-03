package dev.mvc.memsearch;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.memsearch.MemsearchDAO")
public class MemsearchDAO implements MemsearchDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis;

  @Override
  public MemsearchVO search(int memberno) {
    return mybatis.selectOne("memsearch.search", memberno);
  }

  @Override
  public int exist_memid(String memid) {
    return mybatis.selectOne("memsearch.exist_memid", memid);
  }

  @Override
  public int search_memberno(String memid) { 
    return mybatis.selectOne("memsearch.search_memberno", memid);
  }
  
}
