package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.notice.NoticeDAO")
public class NoticeDAO implements NoticeDAOInter {
  
  @Autowired
  private SqlSessionTemplate mybatis;

  public NoticeDAO() {
  }

  @Override
  public int notice_create(NoticeVO noticeVO) {
    return mybatis.insert("notice.notice_create", noticeVO);
  }

  @Override
  public List<NoticeVO> notice_list(HashMap hashMap) {
    return mybatis.selectList("notice.notice_list", hashMap);
  }

  @Override
  public int search_count(HashMap hashMap) {
    return mybatis.selectOne("notice.search_count", hashMap);
  }

  @Override
  public NoticeVO notice_read(int noticeno) {
    return mybatis.selectOne("notice.notice_read", noticeno);
  }

  @Override
  public int notice_update(NoticeVO noticeVO) {
    return mybatis.update("notice.notice_update", noticeVO);
  }

  @Override
  public int notice_delete(int noticeno) {
    return mybatis.delete("notice.notice_delete", noticeno);
  }

  @Override
  public int nseqno_up(int noticeno) {
    return mybatis.update("notice.nseqno_up", noticeno);
  }

  @Override
  public int nseqno_down(int noticeno) {
    return mybatis.update("notice.nseqno_down", noticeno);
  }

  @Override
  public int ncnt_up(int noticeno) {
    return mybatis.update("notice.ncnt_up", noticeno);
  }

}
