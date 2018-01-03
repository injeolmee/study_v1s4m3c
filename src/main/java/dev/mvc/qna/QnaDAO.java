package dev.mvc.qna;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.qna.QnaDAO")
public class QnaDAO implements QnaDAOInter {

  @Autowired
  private SqlSessionTemplate mybatis;
  
  public QnaDAO() {
    
  }

  @Override
  public int create(QnaVO qnaVO) {
    return mybatis.insert("qna.create", qnaVO);
  }

  @Override
  public List<QnaVO> list_all_qna(HashMap hashMap) {
    return mybatis.selectList("qna.list_all_qna", hashMap);
  }

  @Override
  public QnaVO read(int qnano) {
    return mybatis.selectOne("qna.read", qnano);
  }
  
  @Override
  public int update(QnaVO qnaVO) {
    return mybatis.update("qna.update", qnaVO);
  }
  
  @Override
  public int delete(int qnano) {
    return mybatis.delete("qna.delete", qnano);
  }

  @Override
  public int increaseCnt(int qnano) {
    return mybatis.update("qna.increaseCnt", qnano);
  }
  
  @Override
  public int total_count() {
    return mybatis.selectOne("qna.total_count");
  }
  
  @Override
  public int search_count(HashMap hashMap) {
    return mybatis.selectOne("qna.search_count", hashMap);
  }

  @Override
  public int pwdchk(String qnapwd) {
    return mybatis.selectOne("qna.pwdchk", qnapwd);
  }
  
}
