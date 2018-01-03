package dev.mvc.std_recom;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.std_recom.Std_RecomDAO")
public class Std_RecomDAO implements Std_RecomDAOInter {

  @Autowired
  private SqlSessionTemplate mybatis;

  public Std_RecomDAO() {
    System.out.println("--> std_recomDAO created.");
  }

  @Override
  public int create(HashMap hashmap) {

    return mybatis.insert("std_recom.create", hashmap);
  }

  @Override
  public int std_recom_check(HashMap hashmap) {

    return mybatis.selectOne("std_recom.std_recom_check", hashmap);
  }

  @Override
  public int check(HashMap hashmap) {

    return mybatis.selectOne("std_recom.check", hashmap);
  }

  @Override
  public int good_ch_Y(HashMap hashmap) {

    return mybatis.update("std_recom.good_ch_Y", hashmap);
  }

  @Override
  public int good_ch_N(HashMap hashmap) {

    return mybatis.update("std_recom.good_ch_N", hashmap);
  }

  @Override
  public int delete(int stdlist_no) {
    
    return mybatis.delete("std_recom.delete", stdlist_no);
  }

}
