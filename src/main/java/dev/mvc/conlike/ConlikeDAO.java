package dev.mvc.conlike;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.conlike.ConlikeDAO")
public class ConlikeDAO implements ConlikeDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis;
  
  public ConlikeDAO() {
    System.out.println("---> ConlikeDAO created.");
  }
  
  @Override
  public int create(HashMap hashMap) {
    int count = mybatis.insert("conlike.create", hashMap);
    
    return count;
  }

  @Override
  public int good_chk(HashMap hashMap) {
    return mybatis.selectOne("conlike.good_chk", hashMap);
  }

  @Override
  public int check(HashMap hashMap) {
    return mybatis.selectOne("conlike.check", hashMap);
  }

  @Override
  public int good_chk_y(HashMap hashMap) {
    return mybatis.update("conlike.good_chk_y", hashMap);
  }

  @Override
  public int good_chk_n(HashMap hashMap) {
    return mybatis.update("conlike.good_chk_n", hashMap);
  }
  
}
