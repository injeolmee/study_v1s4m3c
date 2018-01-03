package dev.mvc.studylist_reply;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.studylist_reply.ReplyDAO")
public class ReplyDAO implements ReplyDAOInter{

  @Autowired
  private SqlSessionTemplate mybatis;
  
  public ReplyDAO(){
    // System.out.println("--> ReplyDAO created.");
  }

  @Override
  public int create(ReplyVO replyVO) {
    
    return mybatis.insert("std_reply.create", replyVO);
  }

  @Override
  public List<ReplyVO> list(int stdlist_no) {
    
    return mybatis.selectList("std_reply.list", stdlist_no);
  }
  
  @Override
  public List<ReplyVO> list2(HashMap hashmap) {
    
    return mybatis.selectList("std_reply.list2", hashmap);
  }

  @Override
  public int update(ReplyVO replyVO) {
    
    return mybatis.update("std_reply.update", replyVO);
  }

  @Override
  public int delete(HashMap hashmap) {
    
    return mybatis.delete("std_reply.delete", hashmap);
  }

  @Override
  public int check_memberno(HashMap hashmap) {
    
    return mybatis.selectOne("std_reply.check_memberno", hashmap);
  }

  @Override
  public ReplyVO read(HashMap hashmap) {
    
    return mybatis.selectOne("std_reply.read", hashmap);
  }

  @Override
  public int search_count(int stdlist_no) {
    
    return mybatis.selectOne("std_reply.search_count", stdlist_no);
  }

  @Override
  public int delete_all(int stdlist_no) {
    
    return mybatis.delete("std_reply.delete_all", stdlist_no);
  }
}
