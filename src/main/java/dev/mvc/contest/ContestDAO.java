package dev.mvc.contest;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.contest.ContestDAO")
public class ContestDAO implements ContestDAOInter {
  
  @Autowired
  private SqlSessionTemplate mybatis;
  
  public ContestDAO() {
    
  }

  @Override
  public int create(ContestVO contestVO) {
    int count = mybatis.insert("contest.create", contestVO);
    
    return count;
  }
  
  @Override
  public List<ContestVO> list_all_contest(HashMap hashMap) {
    return mybatis.selectList("contest.list_all_contest", hashMap);
  }
  
  @Override
  public List<ContestVO> list_by_count() {
    return mybatis.selectList("contest.list_by_count");
  }
  
  @Override
  public List<ContestVO> list_by_good() {
    return mybatis.selectList("contest.list_by_good");
  }

  @Override
  public int min() {
    return mybatis.selectOne("contest.min");
  }
  
  @Override
  public int max() {
    return mybatis.selectOne("contest.max");
  }

  @Override
  public int increaseCnt(int conNo) {
    return mybatis.update("contest.increaseCnt", conNo);
  }
  
  @Override
  public int goodcnt_up(int conNo) {
    return mybatis.update("contest.goodcnt_up", conNo);
  }
  
  @Override
  public int goodcnt_down(int conNo) {
    return mybatis.update("contest.goodcnt_down", conNo);
  }

  @Override
  public int search_count(HashMap hashMap) {
    int count = mybatis.selectOne("contest.search_count", hashMap);
    
    return count;
  }

  @Override
  public ContestVO read(int conNo) {
    ContestVO contestVO = mybatis.selectOne("contest.read", conNo);
    
    return contestVO;
  }
  
  @Override
  public int total_count() {
    return mybatis.selectOne("contest.total_count");
  }
  
  @Override
  public int day_count() {
    return mybatis.selectOne("contest.day_count");
  }

  @Override
  public int update(ContestVO contestVO) {
    return mybatis.update("contest.update", contestVO);
  }
  
  @Override
  public int update_thumb(ContestVO contestVO) {
    return mybatis.update("contest.update_thumb", contestVO);
  }

  @Override
  public int update_img(ContestVO contestVO) {
    return mybatis.update("contest.update_img", contestVO);
  }

  @Override
  public int update_file(ContestVO contestVO) {
    return mybatis.update("contest.update_file", contestVO);
  }

  @Override
  public int delete(int conNo) {
    int count = mybatis.delete("contest.delete", conNo);
    
    return count;
  }
  
  
}
