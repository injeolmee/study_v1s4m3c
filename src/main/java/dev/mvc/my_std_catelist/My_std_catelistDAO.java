package dev.mvc.my_std_catelist;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.my_std_catelist.My_std_catelistDAO")
public class My_std_catelistDAO implements My_std_catelistDAOInter{

  @Autowired
  private SqlSessionTemplate mybatis;
  
  @Override
  public int insert(HashMap<String, Integer> hashMap) {
    return mybatis.insert("my_std_catelist.insert", hashMap);
  }

  @Override
  public int delete(int stdlist_no) {
    return mybatis.delete("my_std_catelist.delete", stdlist_no);
  }
  
}
