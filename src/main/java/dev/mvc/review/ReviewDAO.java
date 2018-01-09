package dev.mvc.review;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.review.ReviewDAO")
public class ReviewDAO implements ReviewDAOInter {
  @Autowired 
  private SqlSessionTemplate mybatis;
  
  public ReviewDAO() {
    System.out.println("--> ReviewDAO created.");
  }

  @Override
  public int create(ReviewVO reviewVO) {
    int count = mybatis.insert("review.create", reviewVO); 
    return count;
  }

  @Override
  public List<ReviewVO> list(HashMap hashMap) {
    List<ReviewVO> list = mybatis.selectList("review.list", hashMap);
    return list;
  }

  @Override
  public int search_count(int rono) { 
    int cnt = mybatis.selectOne("review.search_count", rono);
    
    return cnt;
  }
  
  @Override
  public ReviewVO read(int rvno) {
    ReviewVO reviewVO = mybatis.selectOne("review.read", rvno);
    return reviewVO;
  }

  // ±Û ¼öÁ¤ Æû
  @Override
  public ReviewVO update(int rvno) {
    ReviewVO reviewVO = mybatis.selectOne("review.read", rvno);
    return null;
  }
  
  @Override
  public int update(ReviewVO reviewVO){
    return mybatis.update("review.update", reviewVO);
  }

  @Override
  public int delete(int rvno) {
    int count = mybatis.delete("review.delete", rvno);
    return count; 
  }

  @Override
  public int countByRono(int rono) {
    int count = mybatis.selectOne("review.countByRono", rono); 
    return count;
  }

  @Override
  public int deleteByRono(int rono) {
    int count = mybatis.delete("review.deleteByRono", rono); 
    return count;
  }
  
  
  @Override
  public int likecnt_up(int rvno) {
    return mybatis.update("review.likecnt_up", rvno);
  }
  
  @Override
  public int likecnt_down(int rvno) {
    return mybatis.update("review.likecnt_down", rvno);
  }

  @Override
  public int rvno() {
    
    return mybatis.selectOne("review.rvno");
  }

}
