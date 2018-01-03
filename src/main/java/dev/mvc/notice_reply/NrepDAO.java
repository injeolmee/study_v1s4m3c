package dev.mvc.notice_reply;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.notice_reply.NrepDAO")
public class NrepDAO implements NrepDAOInter {
  
  @Autowired
  private SqlSessionTemplate mybatis;

  public NrepDAO() {
  }

  @Override
  public int nrep_create(NrepVO nrepVO) {
    return mybatis.insert("notice_reply.nrep_create", nrepVO);
  }

  @Override
  public List<NrepVO> nrep_list(HashMap hashMap) {
    return mybatis.selectList("notice_reply.nrep_list", hashMap);
  }

  @Override
  public int search_count(int noticeno) {
    return mybatis.selectOne("notice_reply.search_count", noticeno);
  }

  @Override
  public NrepVO nrep_read(HashMap hashMap) {
    return mybatis.selectOne("notice_reply.nrep_read", hashMap);
  }

  @Override
  public int nrep_update(NrepVO nrepVO) {
    return mybatis.update("notice_reply.nrep_update", nrepVO);
  }

  @Override
  public int nrep_delete(HashMap hashMap) {
    return mybatis.delete("notice_reply.nrep_delete", hashMap);
  }

  @Override
  public int nrep_deleteAll(int noticeno) {
    return mybatis.delete("notice_reply.nrep_delete", noticeno);
  }

  @Override
  public int nrep_ck_memberno(HashMap hashMap) {
    return mybatis.selectOne("notice_reply.nrep_ck_memberno", hashMap);
  }

}
