package dev.mvc.rvlike;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.rvlike.RvlikeDAO")
public class RvlikeDAO implements RvlikeDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis;
  
  public RvlikeDAO() {
    System.out.println("---> RvlikeDAO created.");
  }
  
  @Override
  public int create(HashMap hashMap) {
    int count = mybatis.insert("rvlike.create", hashMap);
    
    return count;
  }

  @Override
  public int like_chk(HashMap hashMap) {
    return mybatis.selectOne("rvlike.like_chk", hashMap);
  }

  @Override
  public int check(HashMap hashMap) {
    return mybatis.selectOne("rvlike.check", hashMap);
  }

  @Override
  public int like_chk_y(HashMap hashMap) {
    return mybatis.update("rvlike.like_chk_y", hashMap);
  }

  @Override
  public int like_chk_n(HashMap hashMap) {
    return mybatis.update("rvlike.like_chk_n", hashMap);
  }
  
}
